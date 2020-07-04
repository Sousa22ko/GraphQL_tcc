package com.example.GraphQLRProject.core;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.model.input.PessoaInput;

@Component
public class Mutation implements GraphQLMutationResolver{
	
	Pessoa savePessoa(PessoaInput obj) {
		return new Pessoa("teste", "123.654.789-88", "email@wqeqweq.teste");
	}

}
