package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Estado;

public class EstadoDao {
	private Connection connection;

	public EstadoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Estado estado) {
		String sql = "insert into estados (uf,nome) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, estado.getUf());
			stmt.setString(2, estado.getNome());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Estado estado) {
		String sql = "update estados set uf=?, nome=? where id=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, estado.getUf());
			stmt.setString(2, estado.getNome());
			stmt.setLong(3, estado.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Estado estado) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from estados where id=?");

			stmt.setLong(1, estado.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Estado> getLista() {
		try{
			List<Estado> estados = new ArrayList<Estado>();
			PreparedStatement stmt = this.connection.
					 prepareStatement("select * from estados");
					 ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Estado estado = new Estado();
				estado.setId(rs.getLong("id"));
				estado.setUf(rs.getString("uf"));
				estado.setNome(rs.getString("nome"));
				estados.add(estado);
			}
		rs.close();
		stmt.close();
		return estados;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
