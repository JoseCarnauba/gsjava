package br.com.fiap.banco.model;

public class Medico {
	
	private int idmed;
	private int idhosp;
	private String nome;
	private int crm;
	private String especialidade;
	
	public Medico() {}
	
	public Medico(int idmed, int idhosp, String nome, int crm, String especialidade) {
		this.idmed = idmed;
		this.idhosp = idhosp;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		
		
	}
	public int getIdmed() {
		return idmed;
	}
	public void setIdmed(int idmed) {
		this.idmed = idmed;
	}
	public int getIdhosp() {
		return idhosp;
	}
	public void setIdhosp(int idhosp) {
		this.idhosp = idhosp;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}	

}


