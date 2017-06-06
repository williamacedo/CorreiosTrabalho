package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.CidadeDao;
import br.com.caelum.jdbc.modelo.Cidade;


public class CidadeSelect {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CidadeDao dao = new CidadeDao();
		List<Cidade> cidades = dao.getLista();
		
		for (Cidade cidade: cidades) {
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Estado: " + cidade.getEstado().getNome());
			}
	}

}


