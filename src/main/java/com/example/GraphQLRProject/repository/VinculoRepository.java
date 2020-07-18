package com.example.GraphQLRProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.GraphQLRProject.core.generics.GenericRepository;
import com.example.GraphQLRProject.model.Vinculo;

public interface VinculoRepository extends GenericRepository<Vinculo> {

	@Query("Select v from Vinculo v join Pessoa p where p.id = ?1")
	public List<Vinculo> findVinculoByPessoaId(Integer idPessoa);
	
	@Query("Select v from Vinculo v join Pessoa p where p.id = ?1 and v.statusVinculo = true")
	public Vinculo findVinculoAtivoByPessoaId(Integer idPessoa);
}
