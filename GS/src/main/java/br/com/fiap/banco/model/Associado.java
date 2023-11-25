package br.com.fiap.banco.model;

public class Associado {
	
	private int id;
	private String nome;
	private int telefone;
	private int convenio;
	
	public Associado() {}
	
	public Associado(int id, String nome, int telefone, int convenio) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.convenio = convenio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public int getConvenio() {
		return convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	public int getCodigo() {
		// TODO Auto-generated method stub
		return 0;
	}


}
