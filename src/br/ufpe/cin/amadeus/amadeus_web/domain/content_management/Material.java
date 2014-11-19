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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
/**
 * Classe que encapsula os dados de um material de um curso
 * 
 * 
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Material implements Serializable{


	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	private static final int MAX_FILE_SIZE = 500000;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name="ARCHIVE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
		value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private Archive archive;

	private String archiveName;
	
	private String extension;
	
	private boolean allowLateDeliveries;

	private Date creationDate;
	
	private Date correctedDate;
	
	private Float grade;
	
	@OneToOne
	@JoinColumn (name="AUTHOR_ID")
	private Person author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="REQUEST_ID")
	private MaterialRequest requestedMaterial;

	@ManyToOne
	@JoinColumn(name = "MODULE_ID", nullable = false,
			updatable = false, insertable = false)
	private Module module;
	
	public Material() {

	}

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

	public boolean isAllowLateDeliveries() {
		return allowLateDeliveries;
	}

	public void setAllowLateDeliveries(boolean allowLateDeliveries) {
		this.allowLateDeliveries = allowLateDeliveries;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date postDate) {
		this.creationDate = postDate;
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
	
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public MaterialRequest getRequestedMaterial() {
		return requestedMaterial;
	}

	public void setRequestedMaterial(MaterialRequest requestedMaterial) {
		this.requestedMaterial = requestedMaterial;
	}

	public static int getMAX_FILE_SIZE() {
		return MAX_FILE_SIZE;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
