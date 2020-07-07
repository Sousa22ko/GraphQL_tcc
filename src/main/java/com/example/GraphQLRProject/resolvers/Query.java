package com.example.GraphQLRProject.resolvers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.service.PessoaService;

@Component
public class Query implements GraphQLQueryResolver {

	@Resource
	private PessoaService pessoaService;

	public List<Pessoa> findAllPessoas() {
		return pessoaService.findAll();
	}

	public Pessoa findPessoaById(Integer id) {
		return pessoaService.findById(id);
	}
}
