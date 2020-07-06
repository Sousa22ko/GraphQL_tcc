package com.example.GraphQLRProject.model.input;

import java.util.Date;

import com.example.GraphQLRProject.model.Pessoa;

public class PessoaInput {
		
	private String nome;
	
	private String cpf;
	 
	private String email;
	
	private Date dataNascimento;
	
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
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Pessoa toPessoa() {
		return new Pessoa(this.nome, this.cpf, this.email, this.dataNascimento);
	}
}
