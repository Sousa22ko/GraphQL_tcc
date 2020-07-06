package com.example.GraphQLRProject.core;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.model.input.PessoaInput;
import com.example.GraphQLRProject.repository.PessoaRepository;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Resource
	PessoaRepository pessoaRepository;

	public Pessoa savePessoa(PessoaInput novo) {
		return pessoaRepository.save(novo.toPessoa());
	}

	public Pessoa editPessoa(PessoaInput obj) throws Exception {
		Pessoa p = pessoaRepository.findByCpf(obj.getCpf());

		if (p == null)
			throw new Exception("Pessoa não encontrada, digite corretamente o CPF");

		return pessoaRepository.save(p.updateEntity(obj));
	}
	
	public Pessoa deletePessoa(Integer id) throws Exception {
		Pessoa p = pessoaRepository.findById(id).get();
			
		if (p == null)
			throw new Exception("Pessoa não encontrada, digite corretamente o id");

		pessoaRepository.deleteById(p.getId());
		return p;
	}
}
