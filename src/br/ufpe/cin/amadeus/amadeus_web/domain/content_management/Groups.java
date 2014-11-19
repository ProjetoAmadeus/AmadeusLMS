package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Classe que encapsula os dados de um grupo no sistema
 * 
 * @author Jesus
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name="GROUPS")
public class Groups implements Serializable{

	@Id @GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;
	
	private Date date;
	
	@OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	@JoinColumn(name="FUNDADOR_ID")
	private Person fundador;
	
	@OneToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
	@JoinColumn(name="COURSE_ID")
	private Course curso;
	
	private String name;
			
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )			
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
	
	public List<Person> getPersons() {
		
		List<Person> result = new ArrayList<Person>();
		
		for(Person_Groups membro : this.membros)
		{
			result.add(membro.getPessoa());
		}
		
		return result;
	}
}

