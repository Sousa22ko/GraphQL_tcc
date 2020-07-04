package com.example.GraphQLRProject.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.repository.PessoaRepository;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	PessoaRepository pessoaRepository;

	List<Pessoa> findAllPessoas() {
		return pessoaRepository.findAll();
	}
}
