package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Course implements Serializable{
	
	private int id;

	private int maxAmountStudents;
	
	private int numberOfStudentsInCourse;

	private String name;
	
	private String objectives;
	
	private String content;

	private Date creationDate;

	private Date initialRegistrationDate;

	private Date finalRegistrationDate;

	private Date initialCourseDate;

	private Date finalCourseDate;
	
	private Person professor;
	
	private List<Material> materials = new ArrayList<Material>();
	
	private List<Forum> foruns = new ArrayList<Forum>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxAmountStudents() {
		return maxAmountStudents;
	}

	public void setMaxAmountStudents(int maxAmountStudents) {
		this.maxAmountStudents = maxAmountStudents;
	}

	public int getNumberOfStudentsInCourse() {
		return numberOfStudentsInCourse;
	}

	public void setNumberOfStudentsInCourse(int numberOfStudentsInCourse) {
		this.numberOfStudentsInCourse = numberOfStudentsInCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getInitialRegistrationDate() {
		return initialRegistrationDate;
	}

	public void setInitialRegistrationDate(Date initialRegistrationDate) {
		this.initialRegistrationDate = initialRegistrationDate;
	}

	public Date getFinalRegistrationDate() {
		return finalRegistrationDate;
	}

	public void setFinalRegistrationDate(Date finalRegistrationDate) {
		this.finalRegistrationDate = finalRegistrationDate;
	}

	public Date getInitialCourseDate() {
		return initialCourseDate;
	}

	public void setInitialCourseDate(Date initialCourseDate) {
		this.initialCourseDate = initialCourseDate;
	}

	public Date getFinalCourseDate() {
		return finalCourseDate;
	}

	public void setFinalCourseDate(Date finalCourseDate) {
		this.finalCourseDate = finalCourseDate;
	}

	
	public Person getProfessor() {
		return professor;
	}

	public void setProfessor(Person professor) {
		this.professor = professor;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Forum> getForuns() {
		return foruns;
	}

	public void setForuns(List<Forum> foruns) {
		this.foruns = foruns;
	}
	

}
