package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.util.Date;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;

public class LogVisualizacao {

	private Date date;
	private Integer codigo;
	private String materialName;
	
	public LogVisualizacao(Log log) {
		this.date = log.getDate();
		this.codigo = log.getCodigo();
	}
	
	public LogVisualizacao(Log log, String materialName) {
		this.date = log.getDate();
		this.codigo = log.getCodigo();
		this.materialName = materialName;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param materialName the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}	
	
}
