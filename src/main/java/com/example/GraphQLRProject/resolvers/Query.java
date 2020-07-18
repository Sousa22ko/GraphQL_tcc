package com.example.GraphQLRProject.resolvers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.model.Setor;
import com.example.GraphQLRProject.service.PessoaService;
import com.example.GraphQLRProject.service.SetorService;

@Component
public class Query implements GraphQLQueryResolver {

	@Resource
	private PessoaService pessoaService;

	@Resource
	private SetorService setorService;

	public List<Pessoa> findAllPessoas() {
		return pessoaService.findAll();
	}

	public Pessoa findPessoaById(Integer id) {
		return pessoaService.findById(id);
	}

	public List<Setor> findAllSetores() {
		return setorService.findAll();
	}
	
	public Setor findSetorById(Integer id) {
		return setorService.findById(id);
	}
}
