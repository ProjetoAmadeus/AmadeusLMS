package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Groups;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;


@SuppressWarnings("serial")
@XmlRootElement
public class Person_Groups implements Serializable{

	private int id;
	
	private Person pessoa;
	
	private Groups groups;
	
	private boolean moderador;
	
	public Person_Groups (){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPessoa() {
		return pessoa;
	}

	public void setPessoa(Person pessoa) {
		this.pessoa = pessoa;
	}
	
	public void setModerador(boolean moderador) {
		this.moderador = moderador;
	}

	public boolean isModerador() {
		return moderador;
	}
	
	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}
}
