package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Pessoa;

public class PessoaDao {
	private Connection connection;

	public PessoaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Pessoa pessoa) {
		String sql = "insert into pessoas (cpf,nome, email, celular, id_logradouro, numero, complemento) values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, pessoa.getCpf());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getCelular());
			stmt.setLong(5, pessoa.getLogradouro().getId());
			stmt.setString(6, pessoa.getNumero());
			stmt.setString(7, pessoa.getComplemento());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void altera(Pessoa pessoa) {
		String sql = "update pessoas set cpf=?, nome=?, email=?, celular=?, id_logradouro=?, numero=?, complemento=? where id=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, pessoa.getCpf());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getCelular());
			stmt.setLong(5, pessoa.getLogradouro().getId());
			stmt.setString(6, pessoa.getNumero());
			stmt.setString(7, pessoa.getComplemento());
			stmt.setLong(8, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void remove(Pessoa pessoa) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from pessoa where id=?");

			stmt.setLong(1, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Pessoa> getLista() {
		try{
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			PreparedStatement stmt = this.connection.
					 prepareStatement("select p.cpf, p.nome,p.email, p.celular, l.nome, p.numero, p.complemento  from pessoas p inner join logradouros l on p.id_logradouro=l.id limit 100");
					 ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Pessoa pessoa = new Pessoa();
				pessoa.setCpf(rs.getString("p.cpf"));
				pessoa.setNome(rs.getString("p.nome"));
				pessoa.setEmail(rs.getString("p.email"));
				pessoa.setCelular(rs.getString("p.celular"));
				pessoa.getLogradouro().setNome(rs.getString("l.nome"));
				pessoa.setNumero(rs.getString("p.numero"));
				pessoa.setComplemento(rs.getString("p.complemento"));
				pessoas.add(pessoa);
			}
		rs.close();
		stmt.close();
		return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
