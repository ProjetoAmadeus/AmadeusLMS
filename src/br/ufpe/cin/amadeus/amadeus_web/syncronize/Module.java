package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Module implements Serializable{
	
	private int id;
	
	private int countMaterials;

	private String name;
	
	private String description;

	private boolean visible;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public int getCountMaterials() {
		return countMaterials;
	}

	public void setCountMaterials(int countMaterials) {
		this.countMaterials = countMaterials;
	}
	
	
}
