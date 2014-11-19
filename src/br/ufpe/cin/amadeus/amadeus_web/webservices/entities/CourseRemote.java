package br.ufpe.cin.amadeus.amadeus_web.webservices.entities;

import java.io.Serializable;
import java.util.Date;

public class CourseRemote implements Serializable {

	private int id;

	private int maxAmountStudentes;

	private int numberOfStudentsInCourse;

	private String name;

	private String objectives;

	private String content;

	private Date creationDate;

	private Date initialRegistrationDate;

	private Date finalRegistrationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxAmountStudentes() {
		return maxAmountStudentes;
	}

	public void setMaxAmountStudentes(int maxAmountStudentes) {
		this.maxAmountStudentes = maxAmountStudentes;
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

	private Date initialCourseDate;

	private Date finalCourseDate;

	public int getNumberOfStudentsInCourse() {
		return numberOfStudentsInCourse;
	}

	public void setNumberOfStudentsInCourse(int numberOfStudentsInCourse) {
		this.numberOfStudentsInCourse = numberOfStudentsInCourse;
	}

}
