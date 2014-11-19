<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>		
	
<html:html>
	<jsp:include page="/jsp/conf/header.jsp" />
<body>
	<div id="all">
		<div id="header">
			<jsp:include page="/jsp/conf/login.jsp" />
        </div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="page_title">
			<h2><bean:message key="assistanceRequest.title"/></h2>
		</div>
		<div id="breadcrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" >${course.name}</html:link></li>
				<li><bean:message key="assistanceRequest.heading"/></li>
			</ul>
		</div>
		<div id="side_menu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="course.do?method=showViewShowModules&idCourse=${course.id}"><bean:message key="courseForm.courseContent"/></html:link></li>
					<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}" ><bean:message key="viewCourse.heading"/></html:link></li>
					<li><html:link action="course.do?method=showViewCourseParticipants&courseId=${course.id}"><bean:message key="sideMenu.listParticipants"/></html:link></li>
			    </ul>
			</div>
		</div>
		<div id="content">
			<dl class="insert2">
				<html:form action="/assistanceRequest.do">									
					
					<html:errors />
					<dt><bean:message key="editUserForm.degree"/></dt>
						<dd><html:select property="degree" styleClass="formfield" styleId="username">
							<html:option value=""><bean:message key="editUserForm.degree.select"/></html:option>
							<html:option value="degree1"><bean:message key="editUserForm.degree.degree1"/></html:option>
							<html:option value="degree2"><bean:message key="editUserForm.degree.degree2"/></html:option>
							<html:option value="degree3"><bean:message key="editUserForm.degree.degree3"/></html:option>
							<html:option value="specialization"><bean:message key="editUserForm.degree.specialization"/></html:option>
							<html:option value="master"><bean:message key="editUserForm.degree.master"/></html:option>
							<html:option value="phd"><bean:message key="editUserForm.degree.phd"/></html:option>
							<html:option value="other" ><bean:message key="editUserForm.degree.other"/></html:option>
							</html:select></dd>							
						<dd class="description"><bean:message key="editUserForm.degreeDescription"/></dd>
					
					<dt><bean:message key="editUserForm.year"/></dt>	
						<dd><html:text property="year" styleClass="formfield" maxlength="4" styleId="realname"/></dd>	
						<dd class="description"><bean:message key="editUserForm.yearDescription"/></dd>
					
					<dt><bean:message key="editUserForm.instituition"/></dt>	
						<dd><html:text property="instituition" styleClass="formfield" styleId="realname"/></dd>	
						<dd class="description"><bean:message key="editUserForm.instituitionDescription"/></dd>
					
					<dt><bean:message key="editUserForm.description"/></dt>	
						<dd><html:textarea property="description" styleClass="formfield" styleId="realname"/></dd>	
						<dd class="description"><bean:message key="editUserForm.descriptionDescription"/></dd>
					
					<dt><bean:message key="editUserForm.interest"/></dt>	
						<dd><html:textarea property="interest" styleClass="formfield" styleId="realname"/></dd>	
						<dd class="description"><bean:message key="editUserForm.interestDescription"/></dd>
					<html:hidden property="courseId" value="${courseId}"/>
					<dt class="field"><html:submit property="assistanceRequest" styleClass="button"><bean:message key="assistanceRequest.sendRequest"/></html:submit></dt>
					<br/><ul id="required"><li><bean:message key="general.required"/></li></ul>			
				</html:form>						
			</dl>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html:html>
