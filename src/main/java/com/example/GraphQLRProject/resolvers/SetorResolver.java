package com.example.GraphQLRProject.resolvers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.GraphQLRProject.model.Setor;
import com.example.GraphQLRProject.model.Vinculo;
import com.example.GraphQLRProject.service.SetorService;

@Component
public class SetorResolver implements GraphQLResolver<Setor> {

	@Resource
	private SetorService setorService;

	public Setor vinculos(Vinculo vinculo) {
		return setorService.findSetorByVinculoId(vinculo.getId());
	}
}
