package com.example.GraphQLRProject.core;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.repository.PessoaRepository;

@Component
public class Query implements GraphQLQueryResolver {

	@Resource
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAllPessoas() {
		return pessoaRepository.findAll();
	}

	public Pessoa findPessoaById(Integer id) {
		return pessoaRepository.findById(id).get();
	}
}
