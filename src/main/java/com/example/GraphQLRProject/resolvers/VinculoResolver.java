package com.example.GraphQLRProject.resolvers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.GraphQLRProject.model.Pessoa;
import com.example.GraphQLRProject.model.Vinculo;
import com.example.GraphQLRProject.service.VinculoService;

@Component
public class VinculoResolver implements GraphQLResolver<Vinculo> {

	@Resource
	private VinculoService vinculoService;

	public List<Vinculo> vinculos(Pessoa pessoa) {
		return vinculoService.findVinculoByPessoaId(pessoa.getId());
	}

	public Vinculo vinculoAtivo(Pessoa pessoa) {
		return vinculoService.findVinculoAtivoByPessoaId(pessoa.getId());
	}
}
