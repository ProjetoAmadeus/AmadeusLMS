package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Groups implements Serializable{


	private int id;
	
	private Date date;
	
	private Person fundador;
	private Course curso;
	private String name;
	private List<Person_Groups> membros = new ArrayList<Person_Groups>();
	
	public Groups (){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getFundador() {
		return fundador;
	}

	public void setFundador(Person usuario) {
		this.fundador = usuario;
	}
	
	public Course getCurso() {
		return curso;
	}

	public void setCurso(Course curso) {
		this.curso = curso;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * Metodo que cria um Groups ja com a data de criacao setada.
	 * @return
	 */
	public static Groups getGroups(){
		Groups group = new Groups();
		group.setDate(new Date());
		return group;
	}

	public void setMembros(List<Person_Groups> membros) {
		this.membros = membros;
	}

	public List<Person_Groups> getMembros() {
		return membros;
	}
}


