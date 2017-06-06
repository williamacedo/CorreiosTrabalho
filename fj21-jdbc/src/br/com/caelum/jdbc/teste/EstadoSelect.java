package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.EstadoDao;
import br.com.caelum.jdbc.modelo.Estado;

public class EstadoSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EstadoDao dao = new EstadoDao();
		List<Estado> estados = dao.getLista();
		
		for (Estado estado : estados) {
			System.out.println("Cpf: " + estado.getUf());
			System.out.println("Nome: " + estado.getNome());
			}
	}

}
