package com.example.GraphQLRProject.core.security;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.GraphQLRProject.core.dao.model.Usuario;

public class AuthenticationHelper {

	/**
	 * Verifica se senha informada é a do usuário.
	 *
	 * @param usuario Usuário.
	 * @param senha   Senha a ser validada.
	 * @return Boolean indicando se a senha é compatível ou não.
	 */
	public static boolean verificarSenha(Usuario usuario, String senha) {
		return BCrypt.checkpw(senha, usuario.getSenha());
	}

	/**
	 * Verifica se senha informada é compatível com o hash informado.
	 *
	 * @param senha Senha a ser validada.
	 * @param hash  Hash que irá validar a senha.
	 * @return Boolean indicando se a senha é compatível ou não.
	 */
	public static boolean verificarSenha(String senha, String hash) {
		return BCrypt.checkpw(senha, hash);
	}

	/**
	 * Gera uma senha encriptada a partir de uma string.
	 *
	 * @param senha Senha a ser encriptada.
	 * @return Hash com a senha encriptada.
	 */
	public static String gerarSenha(String senha) {
		return BCrypt.hashpw(senha, BCrypt.gensalt(12));
	}

	/**
	 * Gera uma sequência numérica aleatória.
	 *
	 * @return String com números aleatórios.
	 */
	private static String gerarNumerosAleatorios() {
		String saida = "";
		for (int i = 0; i < 6; i++) {
			saida += (int) (Math.random() * 10);
		}
		return saida;
	}

	/**
	 * Gera uma sequência de letras maiúsculas e minúsculas.
	 *
	 * @return String com letras aleatórias.
	 */
	private static String gerarLetrasAleatorias() {
		String saida = "";
		String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
		String letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 3; i++) {
			saida += letrasMaiusculas.charAt((int) (Math.random() * letrasMaiusculas.length()));
		}
		for (int i = 3; i < 6; i++) {
			saida += letrasMinusculas.charAt((int) (Math.random() * letrasMinusculas.length()));
		}
		return saida;
	}

	/**
	 * Gera caracteres especiais aleatórios e válidos para senha de usuário.
	 *
	 * @return
	 */
	private static String gerarCaracteresEspeciaisAleatorios() {
		String saida = "";
		String listaCaracteres = "#!@$%&*_-+:;=|(){}[],.?\\";
		for (int i = 0; i < 6; i++) {
			saida += listaCaracteres.charAt((int) (Math.random() * listaCaracteres.length()));
		}
		return saida;
	}

	/**
	 * Embaralha as letras de uma palavra
	 *
	 * @param s Palavra
	 * @return
	 */
	private static String embaralhar(String s) {
		char a[] = s.toCharArray();

		for (int i = 0; i < a.length; i++) {
			int j = (new Random()).nextInt(a.length);
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}

		return new String(a);
	}

	/**
	 * Gera uma nova senha de 18 caracteres contendo 3 letras maiúsculas, 3 letras
	 * minúsculas, 6 números e 6 caracteres especiais
	 *
	 * @return
	 */
	public static String gerarNovaSenha() {
		String senha = AuthenticationHelper.gerarLetrasAleatorias();
		senha += AuthenticationHelper.gerarNumerosAleatorios();
		senha += AuthenticationHelper.gerarCaracteresEspeciaisAleatorios();
		senha = AuthenticationHelper.embaralhar(senha);
		return senha;
	}
}
