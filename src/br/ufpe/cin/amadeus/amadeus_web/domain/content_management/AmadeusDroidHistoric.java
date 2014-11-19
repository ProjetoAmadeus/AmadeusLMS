package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "AMADEUS_DROID_HISTORIC")
public class AmadeusDroidHistoric {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="COURSE_NAME")
	private String courseName;
	
	@Column(name="USER_NAME")
	private String userName;
	
	private transient String userLogin;
	
	@Column(name="ACTION_VALUE")
	private String actionValue;
	
	@Column(name="RESOURSE_NAME")
	private String resourceName;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	

}
