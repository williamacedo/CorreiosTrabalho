package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Bairro;

public class BairroDao {
	private Connection connection;

	public BairroDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Bairro bairro) {
		String sql = "insert into bairros (nome,id_cidade) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, bairro.getNome());
			stmt.setLong(2, bairro.getCidade().getId());
			

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Bairro bairro) {
		String sql = "update estados set uf=?, nome=? where id=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, bairro.getNome());
			stmt.setLong(2, bairro.getCidade().getId());
			stmt.setLong(3, bairro.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Bairro bairro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from estados where id=?");

			stmt.setLong(1, bairro.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Bairro> getLista() {
		try{
			List<Bairro> bairros = new ArrayList<Bairro>();
			PreparedStatement stmt = this.connection.
					 prepareStatement("select b.nome, c.nome from bairros b inner join cidades c on b.id_cidade=c.id limit 100");
					 ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Bairro bairro = new Bairro();
				bairro.setNome(rs.getString("b.nome"));
				bairro.getCidade().setNome(rs.getString("c.nome"));
				bairros.add(bairro);
			}
		rs.close();
		stmt.close();
		return bairros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
