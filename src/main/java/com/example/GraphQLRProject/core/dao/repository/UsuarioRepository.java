package com.example.GraphQLRProject.core.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.GraphQLRProject.core.dao.model.Usuario;
import com.example.GraphQLRProject.core.generics.GenericRepository;

public interface UsuarioRepository extends GenericRepository<Usuario> {

	@Query(value = "select * from usuario where login = ?1 and ativo = true", nativeQuery = true)
	public Usuario findByLogin(String login);

	@Query("select u from Usuario u where u.login like %?1% and u.ativo = true")
	public List<Usuario> findAllByLogin(String login);
}
