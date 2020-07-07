package com.example.GraphQLRProject.core.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.GraphQLRProject.core.security.JwtTokenProvider;
import com.example.GraphQLRProject.util.JSONProcessor;
import com.example.GraphQLRProject.util.ResponseUtil;

@Component
@Order(1)
public class JwtTokenFilter extends UsernamePasswordAuthenticationFilter {

	private JwtTokenProvider jwtTokenProvider;

	private List<String> rotasPublica = new ArrayList<String>();

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
		
		rotasPublica.add("/auth/login");
		rotasPublica.add("/auth/logout");
		rotasPublica.add("/auth/newUser");
		// rotas do graphiql
		rotasPublica.add("/graphiql");
		rotasPublica.add("/favicon.ico");
		rotasPublica.add("/vendor");	
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		String token = null;
		try {
			token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
				SecurityContextHolder.getContext().setAuthentication(auth);
				chain.doFilter(request, response);
			} else if (isRotaPublica((HttpServletRequest) request)) {
				chain.doFilter(request, response);
			} else {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.setStatus(403);
				resp.setContentType("application/json");
				ResponseUtil.write(resp, JSONProcessor.toJSON("Acesso não autorizado"));
			}
		} catch (InsufficientAuthenticationException error) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.getWriter().println(error.getMessage());
		}
	}

	private Boolean isRotaPublica(HttpServletRequest request) {
		String uri = request.getRequestURI();
		for (String rota : rotasPublica) {
			if (uri.contains(rota))
				return true;
		}
		return false;
	}
}
