package com.example.GraphQLRProject.resolvers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.GraphQLRProject.core.dao.service.UsuarioService;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.model.input.PessoaInput;
import com.example.GraphQLRProject.service.PessoaService;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Resource
	private PessoaService pessoaService;

	@Resource
	private UsuarioService usuarioService;

	public Pessoa savePessoa(PessoaInput novo) {
		return pessoaService.save(novo.toPessoa());
	}

	public Pessoa editPessoa(PessoaInput obj) throws Exception {
		Pessoa p = pessoaService.findByCpf(obj.getCpf());

		if (p == null)
			throw new Exception("Pessoa não encontrada, digite corretamente o CPF");

		return pessoaService.save(p.updateEntity(obj));
	}

	public Pessoa deletePessoa(Integer id) throws Exception {
		Pessoa p = pessoaService.findById(id);

		if (p == null)
			throw new Exception("Pessoa não encontrada, digite corretamente o id");

		pessoaService.deleteById(p.getId());
		return p;
	}
}
