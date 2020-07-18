package com.example.GraphQLRProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.GraphQLRProject.core.generics.GenericService;
import com.example.GraphQLRProject.model.Vinculo;
import com.example.GraphQLRProject.repository.VinculoRepository;

@Service
@Transactional
public class VinculoService extends GenericService<Vinculo, VinculoRepository> {

	public List<Vinculo> findVinculoByPessoaId(Integer id) {
		return this.repository.findVinculoByPessoaId(id);
	}

	public Vinculo findVinculoAtivoByPessoaId(Integer id) {
		return this.repository.findVinculoAtivoByPessoaId(id);
	}
}
