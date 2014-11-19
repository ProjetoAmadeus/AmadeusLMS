package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Material implements Serializable{
	
	private int id;

	private static final int MAX_FILE_SIZE = 500000;
	
	private Archive archive;
	
	private String archiveName;
	
	private String extension;
	
	private Module module;
	
	private boolean allowLateDeliveries;

	private Date creationDate;
	
	private Date correctedDate;
	
	private Float grade;
	
	private int course_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isAllowLateDeliveries() {
		return allowLateDeliveries;
	}

	public void setAllowLateDeliveries(boolean allowLateDeliveries) {
		this.allowLateDeliveries = allowLateDeliveries;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCorrectedDate() {
		return correctedDate;
	}

	public void setCorrectedDate(Date correctedDate) {
		this.correctedDate = correctedDate;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public static int getMaxFileSize() {
		return MAX_FILE_SIZE;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
	

}
