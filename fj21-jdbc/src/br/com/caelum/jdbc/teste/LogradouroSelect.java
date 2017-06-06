package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.LogradouroDao;
import br.com.caelum.jdbc.modelo.Logradouro;

public class LogradouroSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogradouroDao dao = new LogradouroDao();
		List<Logradouro> logradouros = dao.getLista();
		
		for (Logradouro logradouro : logradouros) {
			System.out.println("Nome: " + logradouro.getNome());
			System.out.println("Cep: " + logradouro.getCep());
			System.out.println("Logradouro: " + logradouro.getTipologradouro().getNome());
			System.out.println("Bairro: " + logradouro.getBairros().getNome());
			}

	}

}
