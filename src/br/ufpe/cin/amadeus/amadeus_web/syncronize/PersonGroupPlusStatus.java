package br.ufpe.cin.amadeus.amadeus_web.syncronize;



public class PersonGroupPlusStatus {
	
	private int id;	
	private String name;	
	private int papel;	
	private boolean status;
	
	public PersonGroupPlusStatus (int id, String name, int papel, boolean status){
		this.id = id;
		this.setName(name);
		this.setPapel(papel);
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPapel(int papel) {
		this.papel = papel;
	}

	public int getPapel() {
		return papel;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}
}
