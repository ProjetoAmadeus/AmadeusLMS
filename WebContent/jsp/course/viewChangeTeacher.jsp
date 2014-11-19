<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@page import="java.util.ArrayList, br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo, br.ufpe.cin.amadeus.amadeus_web.domain.register.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo" %>
<%@ page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/>			
</logic:notPresent>

<%
	AccessInfo user = (AccessInfo)session.getAttribute("user");
    ProfileType profile = user.getTypeProfile();
	if (!profile.equals(ProfileType.ADMIN) && !profile.equals(ProfileType.PROFESSOR)) {%>
	<logic:redirect action="system.do?method=showViewWelcome"/>		
<%  } 
	
%>

<html:html>
	<jsp:include page="/jsp/conf/header.jsp" />
	
	<body>
		<div id="all">
		<div id="header">
			
			<jsp:include page="/jsp/conf/login.jsp" />
		
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="page_title">
			<h2><bean:message key="courseForm.heading"/></h2>
		</div>
		
		<div id="breadcrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="courseForm.heading"/></li>
			</ul>
		</div>
		<div id="side_menu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link forward="fProject"><bean:message key="institutionalLinks.project"/></html:link></li>
			    </ul>
			</div>
		</div>						
			<div id="content">
				<form action="/amadeuslms/course.do?method=changeTeacher">
					<input type="hidden" name="method" value="changeTeacher"/>
					<input type="hidden" name="courseId" value="${courseId}" >
					<dt><bean:message key="courseForm.teacherName"/></dt>
						<dd><select  name="teacher">
						<c:forEach var="teacher" items="${teacherList}">
						<option value="${teacher.person.id}"> ${teacher.person.name} </option>
						</c:forEach>
							</select></dd>							
						<dd class="description"><bean:message key="editUserForm.teacherDescription"/></dd>
						<input type="submit" class="button" value="<bean:message key="courseForm.nextStep"/>">
				</form>
			</div>
        </div>
			
	 </body>
			
</html:html>