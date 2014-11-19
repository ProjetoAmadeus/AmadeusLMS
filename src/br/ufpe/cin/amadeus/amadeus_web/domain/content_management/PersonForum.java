package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

@Entity
@Table(name = "personforum")
@AssociationOverrides(
 { @AssociationOverride(name = "pk.person", joinColumns = @JoinColumn(name = "person_id")),
 @AssociationOverride(name = "pk.forum", joinColumns = @JoinColumn(name = "forum_id")) })

public class PersonForum {
	@EmbeddedId
	 private PersonForumPK pk = new PersonForumPK();
	
	@Column(name="lasttimeinforum")
	private Date lastTimeInForum;
	
	public PersonForumPK getPk() {
		return pk;
	}

	public void setPk(PersonForumPK pk) {
		this.pk = pk;
	}

	public Date getLastTimeInForum() {
		return lastTimeInForum;
	}

	public void setLastTimeInForum(Date lastTimeInForum) {
		this.lastTimeInForum = lastTimeInForum;
	}
	
	@Transient
	 public Person getPerson() {	
		return this.getPk().getPerson();	
	 }
	
	 @Transient	
	 public Forum getForum() {	
		 return this.getPk().getForum();	
	 }
	 

	public boolean equals(Object o) {
		 if (this == o)
		 return true;
		 if (o == null || getClass() != o.getClass())
		 return false;
		 
		 PersonForum that = (PersonForum) o;	 
		
		 if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
		
		 return false;		 
		
		 return true;
		
		 }
	
	public int hashCode() {
		 return (getPk() != null ? getPk().hashCode() : 0);
		 }



}
