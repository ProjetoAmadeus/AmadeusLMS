<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList"%>

<%@ page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course" %>
<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade" %>
<%@ page import="br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo" %>

<% 	
	
	AccessInfo user = (AccessInfo)session.getAttribute("user");
	
	List<Course> list0 = new ArrayList<Course>();
	List<Course> list1 = new ArrayList<Course>();
	List<Course> list2 = new ArrayList<Course>();
	List<Course> list3 = new ArrayList<Course>();
	
	List<Course>[][] listComplete = null;
	
	String criteria = (String)request.getAttribute("criteria");
	
	String pageNumber = "";
	String numberOfResultsStr = "";
	int pageInt = 0;
	int nextPageInt = 0;
	int previousPageInt = 0;
	int numberOfResults = 0;
	
	pageNumber = (String)request.getParameter("page_number");
	
	int pagesNumber = 0;
		
	boolean isLogged = false;
	if(user != null){
		isLogged = true;
	}
	
	if(pageNumber == null){
		if (criteria == null) {	
			criteria = (String)request.getParameter("keyword_course");
			Facade facade = Facade.getInstance();
			List<Course>[] founded = facade.getCoursesByRule(criteria);
			numberOfResults = founded[0].size() + founded[1].size() + founded[2].size() + founded[3].size();
			pagesNumber = numberOfResults/10 + 1; 
			listComplete = facade.classifyCoursesByPage(founded , pagesNumber);
		} else {
			listComplete = (List<Course>[][])request.getAttribute("foundCourses");
			pagesNumber = (Integer)request.getAttribute("numberPages");
			numberOfResults = (Integer)request.getAttribute("numberOfResults");
		}
		session.setAttribute("criteria", criteria);
		session.setAttribute("numberPages", pagesNumber);
		session.setAttribute("foundCourses", listComplete);
		session.setAttribute("numberOfResults", numberOfResults);
		if(numberOfResults != 0){
			list0 = listComplete[0][0];
			list1 = listComplete[1][0];
			list2 = listComplete[2][0];
			list3 = listComplete[3][0];
		}
	} else {
		pagesNumber = (Integer)session.getAttribute("numberPages");
		numberOfResults = (Integer)session.getAttribute("numberOfResults");
		listComplete = (List<Course>[][])session.getAttribute("foundCourses");
		criteria = (String)session.getAttribute("criteria");
		pageInt = Integer.parseInt(pageNumber);
		if(numberOfResults != 0){
			list0 = listComplete[0][pageInt];
			list1 = listComplete[1][pageInt];
			list2 = listComplete[2][pageInt];
			list3 = listComplete[3][pageInt];
		}
	}
	
	nextPageInt = pageInt + 1;
	previousPageInt = pageInt - 1;
	
	numberOfResultsStr = String.valueOf(numberOfResults);
	
	pageContext.setAttribute("courses0", list0);
	pageContext.setAttribute("courses1", list1);
	pageContext.setAttribute("courses2", list2);
	pageContext.setAttribute("courses3", list3);
%>

<html:html>
	<jsp:include page="/jsp/conf/header.jsp" />
	<body>
		<div id="all">
		<div id="header">
		<% if (isLogged){%>
			<jsp:include page="/jsp/conf/login.jsp" />
		<% }else{%>
		<div id="login">
			<dl id="formlogin">
				<html:form action="/validateLogin" focus="login" >				
		  		<dt><html:text property="login"  styleClass="inputlogin" size="15" maxlength="15" /> </dt>
      			<dt><html:password property="password" styleClass="inputlogin" size="15" maxlength="15" />&nbsp;
					<html:submit property="logonForm" styleClass="button"><bean:message key="general.submit"/></html:submit></dt>
	       		</html:form>
			</dl>
		  </div>
		<% }%>	
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="page_title">
			<h2><bean:message key="listCourses.title"/></h2>
		</div>
		<div id="breadcrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="listCourses.heading"/><%=' ' + criteria%></li>
			</ul>
		</div>
		
		<div id="side_menu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
				</ul>
			</div>
		</div>
		  <div id="content">
		  <dl>
		  <dt>		  	<bean:message key="listCourses.coursesResult" arg0="<%=numberOfResultsStr %>" arg1="<%=criteria %>"/></dt>
		  <%if((pageInt+1) <= numberOfResults/10) {%>
		  	<dt>Exibindo <%=(pageInt*10)+1%>-<%=((pageInt+1)*10)%></dt>
		  <%} else if(numberOfResults == 0) { %>
		  		<dt>Exibindo 0-0</dt>
		  <%} else {%>
		  		<dt>Exibindo <%=(pageInt*10)+1%>-<%=(pageInt*10)+(numberOfResults%10)%></dt>
		  <%} %>
		  </dl>
		  <ul id="resultbusca">
		  	<%if(!list0.isEmpty()){ %>
			<dl><dt><bean:message key="listCourses.newCourses"/></dt></dl>
				<logic:iterate id="course" name="courses0" indexId="count" type="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course">
							<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" ><%=course.getName()%></html:link></li>
							<a href="user.do?method=showViewPublicData&userId=<%=course.getProfessor().getAccessInfo().getId()%>" >
							<%Integer noStudents = course.getNumberOfStudentsInCourse();
							  String noStudentsS = noStudents.toString();%>
							<%=course.getProfessor().getName()%></a>
							<%if(noStudents == 1) {%>
								<bean:message key="listCourses.student" arg0="<%=noStudentsS%>"/>
							<%}else{%>
								<bean:message key="listCourses.students" arg0="<%=noStudentsS%>"/>							
							<%} %>
				</logic:iterate>
			<% } %>
			
			<%if(!list1.isEmpty()){ %>
			<dl><dt><bean:message key="listCourses.inRegistrationPeriod"/></dt></dl>
				<logic:iterate id="course" name="courses1" indexId="count" type="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course">
							<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" ><%=course.getName()%></html:link></li>
							<a href="user.do?method=showViewPublicData&userId=<%=course.getProfessor().getAccessInfo().getId()%>" >
							<%Integer noStudents = course.getNumberOfStudentsInCourse();
							  String noStudentsS = noStudents.toString();%>
							<%=course.getProfessor().getName()%></a>
							<%if(noStudents == 1) {%>
								<bean:message key="listCourses.student" arg0="<%=noStudentsS%>"/>
							<%}else{%>
								<bean:message key="listCourses.students" arg0="<%=noStudentsS%>"/>							
							<%} %>
				</logic:iterate>
			<% } %>
				
			<% if(!list2.isEmpty()) { %>
			<dl><dt><bean:message key="listCourses.inClassPeriod" /></dt></dl>
				<logic:iterate id="course" name="courses2" indexId="count" type="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course">
								<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" ><%=course.getName()%></html:link></li>
							<a href="user.do?method=showViewPublicData&userId=<%=course.getProfessor().getAccessInfo().getId()%>" >
							<%Integer noStudents = course.getNumberOfStudentsInCourse();
							  String noStudentsS = noStudents.toString();%>
							<%=course.getProfessor().getName()%></a>
							<%if(noStudents == 1) {%>
								<bean:message key="listCourses.student" arg0="<%=noStudentsS%>"/>
							<%}else{%>
								<bean:message key="listCourses.students" arg0="<%=noStudentsS%>"/>							
							<%} %>
				</logic:iterate>
			<% } %>
				
			<% if(!list3.isEmpty()) {%>
			<dl><dt><bean:message key="listCourses.inFinishedClassPeriod"/></dt></dl>
				<logic:iterate id="course" name="courses3" indexId="count" type="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course">
								<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" ><%=course.getName()%></html:link></li>
							<a href="user.do?method=showViewPublicData&userId=<%=course.getProfessor().getAccessInfo().getId()%>" >
							<%Integer noStudents = course.getNumberOfStudentsInCourse();
							  String noStudentsS = noStudents.toString();%>
							<%=course.getProfessor().getName()%></a>
							<%if(noStudents == 1) {%>
								<bean:message key="listCourses.student" arg0="<%=noStudentsS%>"/>
							<%}else{%>
								<bean:message key="listCourses.students" arg0="<%=noStudentsS%>"/>							
							<%} %>
				</logic:iterate>
			<% } %>
				
			</ul>
			
			<dl>
				<% if(previousPageInt >= 0){%>
					<a href="fSearchCourse.do?page_number=<%=previousPageInt%>"><bean:message key="general.previous"/></a>
				<%}%>
				<% if(nextPageInt <= (pagesNumber-1)){%>
					<a href="fSearchCourse.do?page_number=<%=nextPageInt%>"><bean:message key="general.next"/></a>
				<%}%>
				
			</dl>
						
			<dl class="insert2">
					<html:errors property="courseName" />
					<html:form action="/searchCourse" focus="courseName" >
						<dt><bean:message key="courses.search"/>:</dt>
						<dd class="field"><html:text property="courseName" styleClass="formfield2" ></html:text></dd>
						<dd class="description"></dd>
						<input type="text" style="display: none" /> <!--  necessário para bom funcionamento no IE -->
						<dt class="field"><html:submit property="searchCourse" styleClass="button"><bean:message key="courses.searchButton"/></html:submit></dt>
					</html:form>
			</dl>
			
			</div>
			<div id="footnote">
				<dl><bean:message key="copyright.title"/></dl>
			</div>
		</div>
	</body>
	
</html:html>