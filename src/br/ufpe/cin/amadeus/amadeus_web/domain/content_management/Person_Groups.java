package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Classe que encapsula os dados da agregação PersonGroup no sistema
 * 
 * @author Jesus
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Person_Groups implements Serializable{

	@Id @GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;
	
	private Date date;
	
	@OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	@JoinColumn(name="PERSON_ID")
	private Person pessoa;
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID", nullable = false,
			updatable = false, insertable = false)
	private Groups groups;
	
	private int papel;
	
	public final static int PAPEL_FUNDADOR = 0;
	public final static int PAPEL_MODERADOR = 1;
	public final static int PAPEL_MEMBRO = 2;
	
	public Person_Groups (){
		
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

	public Person getPessoa() {
		return pessoa;
	}

	public void setPessoa(Person pessoa) {
		this.pessoa = pessoa;
	}
	
	/**
	 * Metodo que cria um Person_Groups ja com a data de criacao setada.
	 * @return
	 */
	public static Person_Groups getPersonGroups(){
		Person_Groups group = new Person_Groups();
		group.setDate(new Date());
		return group;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Groups getGroups() {
		return groups;
	}
	
	public void setPapel(int papel) {
		this.papel = papel;
	}

	public int getPapel() {
		return papel;
	}
}
