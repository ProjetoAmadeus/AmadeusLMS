package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

@Embeddable
public class PersonForumPK implements Serializable {

 private static final long serialVersionUID = -5869094934725857817L;
 @ManyToOne(fetch = FetchType.LAZY, optional = false)
 private Person person;
 @ManyToOne(fetch = FetchType.LAZY, optional = false)
 private Forum forum;

 // Precisamos implementar corretamente o equals() na classe de Id composta.
 public boolean equals(Object o) {
 if (this == o)
 return true;
 if (o == null || getClass() != o.getClass())
 return false;
 if (!(o instanceof PersonForumPK))
 return false;

 PersonForumPK that = (PersonForumPK) o;

 if (this.person != null ? !this.person.equals(that.person) : that.person != null)
 return false;
 if (this.forum != null ? !this.forum.equals(that.forum) : that.forum != null)
 return false;

 return true;
 }

 public int hashCode() {
 int result;
 result = (this.person != null ? this.person.hashCode() : 0);
 result = 31 * result + (this.forum != null ? this.forum.hashCode() : 0);
 return result;
 }

public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}

public Forum getForum() {
	return forum;
}

public void setForum(Forum forum) {
	this.forum = forum;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
 
 
}

