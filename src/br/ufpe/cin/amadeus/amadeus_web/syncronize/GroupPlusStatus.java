package br.ufpe.cin.amadeus.amadeus_web.syncronize;


public class GroupPlusStatus {
	
	private int id;
	private String name;
	private int qtdMembros;
	private boolean status;
	
	public GroupPlusStatus(int id, String name, int qtdMembros, boolean status) {
		this.setId(id);
		this.setName(name);
		this.setQtdMembros(qtdMembros);
		this.setStatus(status);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setQtdMembros(int qtdMembros) {
		this.qtdMembros = qtdMembros;
	}

	public int getQtdMembros() {
		return qtdMembros;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}
}
