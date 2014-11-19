package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class StudentHaveGroup  implements Serializable {
	
	private String name;
	private int id;
	private boolean haveGroup;
	private int position;

	public StudentHaveGroup() {
		// TODO Auto-generated constructor stub
	}
	
	public StudentHaveGroup(String name, int id, boolean b, int position) {
		this.name = name;
		this.id = id;
		this.haveGroup = b;
		this.setPosition(position);
		
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isHaveGroup() {
		return haveGroup;
	}
	
	public void setHaveGroup(boolean haveGroup) {
		this.haveGroup = haveGroup;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
}
