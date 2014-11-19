package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;

@SuppressWarnings("serial")
@XmlRootElement
public class Service implements Serializable{
	
	private List<Course> listCourses = new ArrayList<Course>();
	private List<PersonRoleCourse> listPersons = new ArrayList<PersonRoleCourse>();
	private Archive archive;
	private boolean validAccessInfo;
	private int countCourses;
	private List<AmadeusDroidHistoric> listHistoric;
	private List<Forum> listForum;
	private Message message;
				
	public List<Course> getListCourses() {
		return listCourses;
	}
	public void setListCourses(List<Course> listCourses) {
		this.listCourses = listCourses;
	}
	
	public boolean isValidAccessInfo() {
		return validAccessInfo;
	}
	public void setValidAccessInfo(boolean validAccessInfo) {
		this.validAccessInfo = validAccessInfo;
	}
	public int getCountCourses() {
		return countCourses;
	}
	public void setCountCourses(int countCourses) {
		this.countCourses = countCourses;
	}
	public List<PersonRoleCourse> getListPersons() {
		return listPersons;
	}
	public void setListPersons(List<PersonRoleCourse> listPersons) {
		this.listPersons = listPersons;
	}
	public Archive getArchive() {
		return archive;
	}
	public void setArchive(Archive archive) {
		this.archive = archive;
	}
	public List<AmadeusDroidHistoric> getListHistoric() {
		return listHistoric;
	}
	public void setListHistoric(List<AmadeusDroidHistoric> listHistoric) {
		this.listHistoric = listHistoric;
	}
	public List<Forum> getListForum() {
		return listForum;
	}
	public void setListForum(List<Forum> listForum) {
		this.listForum = listForum;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	
			
}
