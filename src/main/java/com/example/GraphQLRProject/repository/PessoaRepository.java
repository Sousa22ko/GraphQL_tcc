package com.example.GraphQLRProject.repository;

import org.springframework.data.jpa.repository.Query;

import com.example.GraphQLRProject.core.GenericRepository;
import com.example.GraphQLRProject.model.Pessoa;

public interface PessoaRepository extends GenericRepository<Pessoa> {
	
	@Query("Select p from Pessoa p where p.cpf = ?1")
	Pessoa findByCpf(String cpf);

}
