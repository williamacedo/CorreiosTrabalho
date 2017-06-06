package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Logradouro;

public class LogradouroDao {
	private Connection connection;

	public LogradouroDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Logradouro logradouro) {
		String sql = "insert into logradouros (cep,nome,id_tipo_logradouro, id_cidade) values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, logradouro.getCep());
			stmt.setString(2, logradouro.getNome());
			stmt.setLong(3, logradouro.getTipologradouro().getId());
			stmt.setLong(4, logradouro.getBairros().getId());
			
			

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Logradouro logradouro) {
		String sql = "update logradouros set cep=?, nome=?, id_tipo_logradouro=?, id_bairro=? where id=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, logradouro.getCep());
			stmt.setString(2, logradouro.getNome());
			stmt.setLong(3, logradouro.getTipologradouro().getId());
			stmt.setLong(4, logradouro.getBairros().getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Logradouro logradouro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from estados where id=?");

			stmt.setLong(1, logradouro.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Logradouro> getLista() {
		try{
			List<Logradouro> logradouros = new ArrayList<Logradouro>();
			PreparedStatement stmt = this.connection.
					 prepareStatement("select l.cep, l.nome,t.nome, b.nome from logradouros l inner join tipos_logradouros t inner join bairros b on l.id_tipo_logradouro=t.id AND l.id_bairro=b.id limit 100");
					 ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Logradouro logradouro = new Logradouro();
				logradouro.setCep(rs.getString("l.cep"));
				logradouro.setNome(rs.getString("l.nome"));
				logradouro.getTipologradouro().setNome(rs.getString("t.nome"));
				logradouro.getBairros().setNome(rs.getString("b.nome"));
				logradouros.add(logradouro);
			}
		rs.close();
		stmt.close();
		return logradouros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
