/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Classe que encapsula dados de uma avaliação de curso
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
public class CourseEvaluation implements Serializable{

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private int id;

	private Date initDate;
	
	@OneToMany
//	@JoinColumn(name="COURSE_EVALUATION_ID", nullable = false)
	@org.hibernate.annotations.IndexColumn(name = "CRITERION_POSITION")
	private List<Criterion> criteria = new ArrayList<Criterion>();

	@OneToMany
//	@JoinColumn(name="COURSE_EVALUATION_ID", nullable = false)
	@org.hibernate.annotations.IndexColumn(name = "COMMENTARY_POSITION")
	private List<Commentary> commentaries = new ArrayList<Commentary>();

	@OneToMany
//	@JoinColumn(name="COURSE_EVALUATION_ID", nullable = false)
	private Set<Person> students = new HashSet<Person>();

	public CourseEvaluation() {

	}

	public List<Commentary> getCommentaries() {
		return commentaries;
	}

	public void setCommentaries(List<Commentary> commentaries) {
		this.commentaries = commentaries;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Set<Person> getStudents() {
		return students;
	}

	public void setStudents(Set<Person> students) {
		this.students = students;
	}

}
