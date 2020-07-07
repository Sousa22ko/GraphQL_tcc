package com.example.GraphQLRProject.core.dao.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.GraphQLRProject.core.dao.model.Usuario;
import com.example.GraphQLRProject.core.dao.repository.UsuarioRepository;
import com.example.GraphQLRProject.core.generics.GenericService;

@Service
@Transactional
public class UsuarioService extends GenericService<Usuario, UsuarioRepository> {

	/**
	 * Insere o usuário no banco, encriptando antes sua senha.
	 */
	@Override
	public Usuario save(Usuario usuario) {
		validate(usuario);
		System.err.println(usuario.getSenha());
		String senha = usuario.getSenha();
		usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt(12)));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 6);

		Usuario usuarioSalvo = repository.save(usuario);
		return usuarioSalvo;
	}

	public Usuario salvarSemEncriptar(Usuario usuario) {
		return this.repository.save(usuario);
	}

	public Usuario findUsuarioByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	public List<Usuario> findAllUsuarioByLogin(String login) {
		return this.repository.findAllByLogin(login);
	}
}