/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.basics;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HomeworkMobile {

	public static final String FORUM = "FORUM";
	public static final String HOMEWORK = "HOMEWORK";
	public static final String GAME = "GAME";
	public static final String POLL = "POLL";
	public static final String MULTIMEDIA = "MULTIMEDIA";
	public static final String VIDEO = "VIDEO";	
	public static final String LEARNING_OBJECT = "LEARNING_OBJECT";
	
	private int id;

	private String name;

	private String description;

	private Date initDate;

	private Date deadline;

	private Date alowPostponing;
	
	private int idCourse;
	
	private String typeActivity;

	private String infoExtra;
	
	private String url;

	public HomeworkMobile() {}

	public HomeworkMobile(int id, String name, String description, Date initDate,
			Date deadline, Date alowPostponing, int idCourse) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.initDate = initDate;
		this.deadline = deadline;
		this.alowPostponing = alowPostponing;
		this.idCourse = idCourse;
	}

	public String getTypeActivity() {
		return typeActivity;
	}

	public void setTypeActivity(String typeActivity) {
		this.typeActivity = typeActivity;
	}
	
	public String getInfoExtra() {
		return infoExtra;
	}

	public void setInfoExtra(String infoExtra) {
		this.infoExtra = infoExtra;
	}
	
	public Date getAlowPostponing() {
		return alowPostponing;
	}

	public void setAlowPostponing(Date alowPostponing) {
		this.alowPostponing = alowPostponing;
	}

	public String getDeadline() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		
		if (deadline != null){
		  return df.format(deadline);	
		}		
		return "Sem Prazo Final!";
	}
	
	public Date getDeadlineDate() {
		return this.deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInitDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		String data = df.format(initDate);
		return data;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}