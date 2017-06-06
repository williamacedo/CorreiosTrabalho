package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Cidade;

public class CidadeDao {
	private Connection connection;

	public CidadeDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Cidade cidade) {
		String sql = "insert into cidades (nome,id_estado) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, cidade.getNome());
			stmt.setLong(2, cidade.getEstado().getId());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Cidade cidade) {
		String sql = "update cidades set nome=?, id_estado=? where id=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cidade.getNome());
			stmt.setLong(2, cidade.getEstado().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Cidade cidade) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("delete from cidades where id=?");

			stmt.setLong(1, cidade.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cidade> getLista() {
		try {
			List<Cidade> cidades = new ArrayList<Cidade>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select c.nome, e.nome from cidades c inner join estados e on c.id_estado=e.id limit 100");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				
				cidade.setNome(rs.getString("c.nome"));
				cidade.getEstado().setNome(rs.getString("e.nome"));
				
				cidades.add(cidade);
			}
			rs.close();
			stmt.close();
			return cidades;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
