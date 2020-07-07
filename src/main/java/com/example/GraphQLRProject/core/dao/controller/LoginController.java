package com.example.GraphQLRProject.core.dao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraphQLRProject.core.dao.model.Credenciais;
import com.example.GraphQLRProject.core.dao.model.LoggedUserStore;
import com.example.GraphQLRProject.core.dao.model.Usuario;
import com.example.GraphQLRProject.core.dao.service.UsuarioService;
import com.example.GraphQLRProject.core.security.JwtTokenProvider;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LoggedUserStore userStore;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Credenciais credenciais) {
		Usuario usuario = usuarioService.findUsuarioByLogin(credenciais.getLogin());
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + credenciais.getLogin() + " não encontrado");
		} else {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, credenciais));
				String username = credenciais.getLogin();
				String token = jwtTokenProvider.createToken(credenciais.getLogin(), usuario.getPermissoesCodigo(),
						usuario.getPrimeiroAcesso());
				userStore.addUser(username, token);
				return new ResponseEntity<String>(token, HttpStatus.OK);
			} catch (SessionException e) {
				throw e;
			} catch (AuthenticationException e) {
				return new ResponseEntity<String>("Login e/ou senha inválidos", HttpStatus.FORBIDDEN);
			}
		}

	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = jwtTokenProvider.resolveToken(request);
		String username = jwtTokenProvider.getUsername(token);
		if (session != null) {
			session.invalidate();
			this.userStore.removeUser(username);
		}
	}

	@PostMapping("/newUser")
	public Usuario criarUsuario(@RequestBody Credenciais credenciais) {
		Usuario usuario = new Usuario();
		usuario.setLogin(credenciais.getLogin());
		usuario.setSenha(credenciais.getSenha());
		return usuarioService.save(usuario);
	}
}
