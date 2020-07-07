package com.example.GraphQLRProject.core.dao.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.GraphQLRProject.core.dao.model.Papel;
import com.example.GraphQLRProject.core.dao.repository.PapelRepository;
import com.example.GraphQLRProject.core.generics.GenericService;
import com.example.GraphQLRProject.core.security.enums.PapelResponsabilidade;

@Service
@Transactional
public class PapelService extends GenericService<Papel, PapelRepository> {

	private Map<String, Papel> mapaDePapeis;
	
	public Papel findByCodigo(String codigo) {
		return repository.findByCodigoAndAtivoTrue(codigo);
	}

	public List<Papel> findBy(String fieldName, String value) {
		switch (fieldName) {
		case "codigo":
			return repository.findByCodigoLike(value);
		default:
			return repository.findAll();
		}
	}

	public Map<String, Papel> getMapaDePapeis() {
		if (mapaDePapeis == null)
			initMapaDePapeis();
		return mapaDePapeis;
	}

	/**
	 * Carregar papeis para serem utilizados pelo filtro
	 */
	synchronized public void initMapaDePapeis() {
		mapaDePapeis = new HashMap<String, Papel>();
		for (Papel papel : repository.findAll()) {
			mapaDePapeis.put(papel.getCodigo(), papel);
		}
	}

	@Override
	public Papel save(Papel obj) {
		Papel papel = super.save(obj);
		initMapaDePapeis();
		return papel;
	}

	@Override
	public Papel update(Papel obj) {
		Papel papel = super.update(obj);
		initMapaDePapeis();
		return papel;
	}

	public Papel findByResponsabilidadeAndSetor(PapelResponsabilidade responsabilidade, Integer idSetor) {
		return repository.findByResponsabilidadeAndSetor(responsabilidade, idSetor);
	}
}
