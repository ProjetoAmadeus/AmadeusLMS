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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
/**
 * Classe que encapsula os dados de um módulo de um curso
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Module implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	private String name;

	@Column(length=1500)
	private String description;

	private boolean visible;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Material> materials = new ArrayList<Material>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<MaterialRequest> materialRequests = new ArrayList<MaterialRequest>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Poll> polls = new ArrayList<Poll>();

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Forum> forums = new ArrayList<Forum>();
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Homework> homeworks = new ArrayList<Homework>();

	@ManyToOne
	@JoinColumn(name = "COURSE_ID", nullable = false,
			updatable = false, insertable = false)
	private Course course;

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
    @OrderBy("description")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<CourseScores> studentsScores = new ArrayList<CourseScores>();

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Game> games = new ArrayList<Game>();
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<VideoIriz> videos = new ArrayList<VideoIriz>();
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<LearningObject> learningObjects = new ArrayList<LearningObject>();
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<ExternalLink> externalLinks = new ArrayList<ExternalLink>();
	
	
	private int position;
	
	
	public Module() {

	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Forum> getForums() {
		return forums;
	}

	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}

	public List<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public List<Poll> getPolls() {
		return polls;
	}

	public void setPolls(List<Poll> polls) {
		this.polls = polls;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<CourseScores> getStudentsScores() {
		return studentsScores;
	}

	public void setStudentsScores(List<CourseScores> studentsScores) {
		this.studentsScores = studentsScores;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if(obj instanceof Module && obj != null
				&& ((Module)obj).getId() == this.id ){
			retorno = true;
		}
		
		return retorno;
	}

	public List<MaterialRequest> getMaterialRequests() {
		return materialRequests;
	}

	public void setMaterialRequests(List<MaterialRequest> materialRequests) {
		this.materialRequests = materialRequests;
	}

	public List<VideoIriz> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoIriz> videos) {
		this.videos = videos;
	}
	
	public List<LearningObject> getLearningObjects() {
		return learningObjects;
	}

	public void setLearningObjects(List<LearningObject> learningObjects) {
		this.learningObjects = learningObjects;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public List<ExternalLink> getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(List<ExternalLink> externalLinks) {
		this.externalLinks = externalLinks;
	}

}
