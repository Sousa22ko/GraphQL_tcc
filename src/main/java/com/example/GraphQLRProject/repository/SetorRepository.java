package com.example.GraphQLRProject.repository;

import org.springframework.data.jpa.repository.Query;

import com.example.GraphQLRProject.core.generics.GenericRepository;
import com.example.GraphQLRProject.model.Setor;

public interface SetorRepository extends GenericRepository<Setor> {

	@Query("Select s from Setor s join Vinculo v where v.id = ?1")
	public Setor findSetorByVinculoId(Integer idVinculo);
}
