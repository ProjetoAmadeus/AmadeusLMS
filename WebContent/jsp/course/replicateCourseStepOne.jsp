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
				<dl class="insert2">
					<html:form action="/replicateCourseStepOne" focus="name">
					<html:errors/>
					<dt><bean:message key="courseForm.courseName"/></dt>
						<dd class="field"><html:text property="name" value="${course.name} NOVO" styleClass="formfield" styleId="realname"/></dd>
						<dd class="description"><bean:message key="courseForm.courseNameDescription"/></dd>
					<dt><bean:message key="courseForm.objectives"/></dt>
						<dd class="field"><html:textarea property="objectives" value="${course.objectives}" styleClass="formfield" styleId="courseObjectives"/></dd>
						<dd class="description"><bean:message key="courseForm.courseObjectives"/></dd>
					<dt><bean:message key="courseForm.content"/></dt>
						<dd class="field"><html:textarea property="content" value="${course.content}" styleClass="formfield" styleId="courseContent"/></dd>
						<dd class="description"><bean:message key="courseForm.courseContent"/></dd>
					<dt><bean:message key="courseForm.maxAmountStudents"/></dt> 
						<dd class="field"><html:text property="maxAmountStudents" value="${course.maxAmountStudents}" styleClass="formfield" styleId="courseStudents"/></dd>
						<dd class="description"><bean:message key="courseForm.maxAmountStudents"/></dd>
				<dt><bean:message key="courseForm.initialRegistrationDate"/></dt> 
						<dd class="field2"><html:text property="initialRegistrationDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
									      <html:text property="initialRegistrationMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
										  <html:text property="initialRegistrationYear" styleClass="date" styleId="ano" maxlength="4"/></dd>
						<dd class="description"><bean:message key="courseForm.initialRegistrationDateDescription"/></dd>
					<dt><bean:message key="courseForm.finalRegistrationDate"/></dt>
						<dd class="field2"><html:text property="finalRegistrationDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
							  			  <html:text property="finalRegistrationMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
										  <html:text property="finalRegistrationYear" styleClass="date" styleId="ano" maxlength="4"/></dd>
						<dd class="description"><bean:message key="courseForm.finalRegistrationDateDescription"/></dd>
					<dt><bean:message key="courseForm.initialCourseDate"/></dt>
						<dd class="field2"><html:text property="initialCourseDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
										  <html:text property="initialCourseMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
						                  <html:text property="initialCourseYear" styleClass="date" styleId="ano" maxlength="4"/></dd>
						<dd class="description"><bean:message key="courseForm.initialCourseDateDescription"/></dd>
					<dt><bean:message key="courseForm.finalCourseDate"/></dt>
						<dd class="field2"><html:text property="finalCourseDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
		              					  <html:text property="finalCourseMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
						                  <html:text property="finalCourseYear" styleClass="date" styleId="ano" maxlength="4"/></dd>
						<dd class="description"><bean:message key="courseForm.finalCourseDateDescription"/></dd>
						<html:hidden property="courseId" value="${course.id}"/>
					<dt><bean:message key="courseForm.keywords"/></dt>
						<dd class="field"><html:textarea property="keywords" value="${keywordsStr}" styleClass="formfield" styleId="realname"/></dd>
						<dd class="description"><bean:message key="courseForm.stepTwoDescription"/></dd>
					<dt class="field"><html:submit property="replicateCourseStepOne" styleClass="button"><bean:message key="courseForm.nextStepReplication"/></html:submit></dt>
					<br/><ul id="required"><li><bean:message key="general.required"/></li></ul>
					</html:form>
				</dl>
					
			</div>	
					
			<jsp:include page="/jsp/conf/footer.jsp" />

		</div>
</body>	
	
</html:html>