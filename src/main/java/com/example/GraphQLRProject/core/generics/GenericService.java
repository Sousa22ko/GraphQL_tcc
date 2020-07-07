package com.example.GraphQLRProject.core.generics;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService<T extends GenericEntity, R extends GenericRepository<T>> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	protected R repository;

	/**
	 * Metodo para implementar regras de negocio relacionadas a validacao dos
	 * métodos save e update
	 *
	 * @param entity
	 * @throws NegocioException
	 */
	public void validate(T entity) {
	}

	/**
	 * Persiste uma entidade no banco de dados.
	 * 
	 * @param obj Objeto a ser persistido.
	 * @throws NegocioException
	 */
	public T save(T obj) {
		T entity = obj;
		this.validate(entity);
		entity = repository.save(obj);
		return entity;
	}

	/**
	 * Altera uma entidade já existente no banco de dados.
	 * 
	 * @param obj Objeto a ser alterado.\
	 * @throws NegocioException
	 */
	public T update(T obj) {
		T entity = obj;
		this.validate(entity);
		entity = repository.save(entity);
		return entity;
	}

	/**
	 * Remove uma entidade no banco de dados.
	 * 
	 * @param obj Objeto a ser removido.
	 * @throws NegocioException
	 */
	public void delete(T obj) {
		repository.delete(obj);
	}

	/**
	 * Remove uma entidade no banco de dados a partir do seu ID.
	 * 
	 * @param id ID da entidade a ser removida.
	 * @throws NegocioException
	 */
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public void softDelete(T entity) {
		repository.delete(entity);
	}

	/**
	 * Encontra uma entidade no banco de dados a partir do seu ID.
	 * 
	 * @param id ID da entidade a ser procurada.
	 * @return Objeto referente a entidade.
	 */
	public T findById(Integer id) {
		return repository.findById(id).get();
	}

	/**
	 * Encontra todos os objetos no banco de dados referentes a uma entidade.
	 * 
	 * @return Lista contendo todas as entidades.
	 */
	public List<T> findAll() {
		return repository.findAll();
	}

	/**
	 * Conta quantas entradas no banco de dados ainda estão ativas (em uso).
	 * 
	 * @return Número de entidades ativas.
	 */
	public long countAtivo() {
		return repository.countAtivo();
	}
}
