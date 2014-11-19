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
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

public class CourseMobile {
	
	private int id;

	private String name;

	private List<ModuleMobile> modules;

	private List<String> teachers;

	private List<String> helpers;

	private List<NoticeMobile> notices;

	private String objectives;

	private String content;

	private int maxAmountStudents;

	private Date initialCourseDate;

	private Date finalCourseDate;

	private HashSet<KeywordMobile> keywords;

	private String color;

	private boolean sms;

	private int count;
	
	private int idCourse;
	
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public CourseMobile() {
		super();
	}

	public CourseMobile(int id, String name, List<ModuleMobile> modules,
			List<String> teachers, List<String> helpers, List<NoticeMobile> notices,
			String objectives, String content, int maxAmountStudents,
			Date initialCourseDate, Date finalCourseDate,
			HashSet<KeywordMobile> keywords, String color, boolean sms, int count) {
		super();
		this.id = id;
		this.name = name;
		this.modules = modules;
		this.teachers = teachers;
		this.helpers = helpers;
		this.notices = notices;
		this.objectives = objectives;
		this.content = content;
		this.maxAmountStudents = maxAmountStudents;
		this.initialCourseDate = initialCourseDate;
		this.finalCourseDate = finalCourseDate;
		this.keywords = keywords;
		this.color = color;
		this.sms = sms;
		this.count = count;
	}

	public List<NoticeMobile> getNotices() {
		return notices;
	}

	public void setNotices(List<NoticeMobile> notices) {
		this.notices = notices;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getFinalCourseDate() {
		return finalCourseDate;
	}

	public void setFinalCourseDate(Date finalCourseDate) {
		this.finalCourseDate = finalCourseDate;
	}

	public List<String> getHelpers() {
		return helpers;
	}

	public void setHelpers(List<String> helpers) {
		this.helpers = helpers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInitialCourseDate() {
		return initialCourseDate;
	}

	public void setInitialCourseDate(Date initialCourseDate) {
		this.initialCourseDate = initialCourseDate;
	}

	public int getMaxAmountStudents() {
		return maxAmountStudents;
	}

	public void setMaxAmountStudents(int maxAmountStudents) {
		this.maxAmountStudents = maxAmountStudents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public boolean getSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public List<String> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}

	public HashSet<KeywordMobile> getKeywords() {
		return keywords;
	}

	public void setKeywords(HashSet<KeywordMobile> keywords) {
		this.keywords = keywords;
	}

	public List<ModuleMobile> getModules() {
		return modules;
	}

	public void setModules(List<ModuleMobile> modules) {
		this.modules = modules;
	}
	
	public String getDataInicio() {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String retorno = sd.format(this.initialCourseDate);
		return retorno;
	}
	
	public String getDataFim() {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String retorno = sd.format(this.finalCourseDate);
		return retorno;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}
	
}