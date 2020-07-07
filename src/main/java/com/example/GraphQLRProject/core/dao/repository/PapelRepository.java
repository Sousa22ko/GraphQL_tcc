package com.example.GraphQLRProject.core.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.GraphQLRProject.core.dao.model.Papel;
import com.example.GraphQLRProject.core.generics.GenericRepository;
import com.example.GraphQLRProject.core.security.enums.PapelResponsabilidade;

public interface PapelRepository extends GenericRepository<Papel> {
	@Query(value = "SELECT id_papel, ativo, data_criacao, data_modificacao, codigo, descricao\n"
			+ "  FROM papel where codigo like 'ADMIN'\n" + "", nativeQuery = true)
	Papel findByAdmin();

	@Query("select p from Papel p where p.ativo = true")
	List<Papel> papeisAtivos();

	@Query("select p from Papel p where p.codigo like %?1% and p.ativo = true")
	List<Papel> findByCodigoLike(String codigo);

	Papel findByCodigoAndAtivoTrue(String codigo);

	@Query("select distinct(p) from Papel p where p.responsabilidade = ?1 and p.setor.id = ?2 and p.ativo = true")
	Papel findByResponsabilidadeAndSetor(PapelResponsabilidade responsabilidade, Integer idSetor);

	@Query(
		"SELECT DISTINCT(p) from Papel p " +
		"WHERE p.responsabilidade IN :responsabilidades " +
		"AND p.setor.id = :idSetor " +
		"AND p.ativo = true"
	)
	List<Papel> findByResponsabilidadesAndSetor(List<PapelResponsabilidade> responsabilidades, Integer idSetor);

	@Query(
		"SELECT DISTINCT(papeis) from Usuario u " +
		"JOIN u.permissoes papeis " +
		"WHERE u.id = ?1 " +
		"AND papeis.ativo = true"
	)
	List<Papel> findByUsuario(int idUsuario);
}