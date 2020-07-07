package com.example.GraphQLRProject.core.dao.model;

import java.io.Serializable;

public class Credenciais implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String senha;

	public Credenciais() {
		super();
	}

	public Credenciais(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}