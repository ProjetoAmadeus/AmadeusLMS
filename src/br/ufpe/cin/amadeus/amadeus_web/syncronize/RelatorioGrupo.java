package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.util.List;

public class RelatorioGrupo {

	private String nome;
	private List<Boolean> status;
	
	public RelatorioGrupo(String nome, List<Boolean> status) {
		this.nome = nome;
		this.status = status;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the status
	 */
	public List<Boolean> getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(List<Boolean> status) {
		this.status = status;
	}
}
