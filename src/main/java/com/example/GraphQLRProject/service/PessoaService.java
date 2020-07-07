package com.example.GraphQLRProject.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.GraphQLRProject.core.generics.GenericService;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService extends GenericService<Pessoa, PessoaRepository>{

	public Pessoa findByCpf(String cpf) {
		return this.repository.findByCpf(cpf);
	}
	
}
