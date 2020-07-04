package com.example.GraphQLRProject.model.input;

import com.example.GraphQLRProject.model.Pessoa;

public class PessoaInput {
	
	private String nome;
	
	private String cpf;
	 
	private String email;
	
	private Boolean statusCadastro;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatusCadastro() {
		return statusCadastro;
	}

	public void setStatusCadastro(Boolean statusCadastro) {
		this.statusCadastro = statusCadastro;
	}
	
	public Pessoa toPessoa() {
		return new Pessoa(this.nome, this.cpf, this.email);
	}
}
