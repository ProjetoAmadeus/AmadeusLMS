package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class TimelineItem  implements Serializable {

	private int idGroup;
	private String date;
	private int frequency;
	private String classe;
	
	public TimelineItem() {
		
	}
	
	public int getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
}
