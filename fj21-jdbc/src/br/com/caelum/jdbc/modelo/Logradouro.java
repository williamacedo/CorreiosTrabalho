package br.com.caelum.jdbc.modelo;




public class Logradouro {
	private Long id;
	private String cep;
	private String nome;
	private TipoLogradouro tipologradouro = new TipoLogradouro();
	private Bairro bairros = new Bairro();


	public TipoLogradouro getTipologradouro() {
		return tipologradouro;
	}

	public void setTipologradouro(TipoLogradouro tipologradouro) {
		this.tipologradouro = tipologradouro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Bairro getBairros() {
		return bairros;
	}

	public void setBairros(Bairro bairros) {
		this.bairros = bairros;
	}
}
