package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.BairroDao;
import br.com.caelum.jdbc.modelo.Bairro;

public class BairroSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BairroDao dao = new BairroDao();
		List<Bairro> bairros = dao.getLista();
		
		for (Bairro bairro: bairros) {
			System.out.println("Nome: " + bairro.getNome());
			System.out.println("Estado: " + bairro.getCidade().getNome());
			}
	}

}


