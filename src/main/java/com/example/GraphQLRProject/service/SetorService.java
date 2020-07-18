package com.example.GraphQLRProject.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.GraphQLRProject.core.generics.GenericService;
import com.example.GraphQLRProject.model.Setor;
import com.example.GraphQLRProject.repository.SetorRepository;

@Service
@Transactional
public class SetorService extends GenericService<Setor, SetorRepository>{

	public Setor findSetorByVinculoId(Integer idVinculo) {
		return this.repository.findSetorByVinculoId(idVinculo);
	}
	
}
