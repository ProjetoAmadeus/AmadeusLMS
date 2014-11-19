/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.basics;

import java.util.Date;

public class NoticeMobile {
	
	public static final int TIPO_ATIVIDADE = 0;	
	public static final int TIPO_CURSO = 1;
	public static final int TIPO_MATERIAL = 2;
	public static final int TIPO_ENQUETE = 3;
	public static final int TIPO_AMADEUS = 4;
	
	private int id;
	
	private String title;
	
	private String content;
	
	private int idModule;
	
	private int idCourse;
	
	private int type;
	
	private boolean read;
	
	private int idElement;
	
	private Date dateCreation;
	
	public int getIdElement() {
		return idElement;
	}

	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}

	public NoticeMobile() {}
	
	public NoticeMobile(int id, String title, String content, int idModule, int idCourse, int type, boolean read, int idElement, Date data_criacao) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.idModule = idModule;
		this.idCourse = idCourse;
		this.type = type;
		this.read = read;
		this.idElement = idElement;
		this.dateCreation = data_criacao;
	}
	
	public NoticeMobile(String title, String content, int idModule, int idCourse, int type, boolean read, int idElement, Date data_criacao) {
		super();
		this.title = title;
		this.content = content;
		this.idModule = idModule;
		this.idCourse = idCourse;
		this.type = type;
		this.read = read;
		this.idElement = idElement;
		this.dateCreation = data_criacao;
	}

	public String getContent() {
		return " " + content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getIdModule() {
		return idModule;
	}
	
	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isRead() {
		return read;
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date data_criacao) {
		this.dateCreation = data_criacao;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}
		
}