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
	<logic:redirect action="system.do?method=showViewMenu"/>			
</logic:notPresent>

<html:html>
	<jsp:include page="/jsp/conf/header.jsp" />
	<body>
		<div id="all">
		
		<%
	AccessInfo user = (AccessInfo)session.getAttribute("user");
    ProfileType profile = user.getTypeProfile();
	if (!profile.equals(ProfileType.ADMIN) && !profile.equals(ProfileType.PROFESSOR)) {%>
	<logic:redirect action="system.do?method=showViewWelcome"/>		
<%  } 
	
%>
		
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
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}">${course.name}</html:link></li>
				<li><bean:message key="courseForm.socialInteractionMonitoring"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="2" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>					
			<div id="content">
				<dl class="insert2">
				<h3 style="text-align:center"><bean:message key="courseForm.socialInteractionMonitoring"/></h3>
					<html:form action="/socialInteractionMonitoring" >
					<html:errors/>
					<html:hidden property="id" name="course" styleId="courseId"/>
						<dt><bean:message key="courseForm.socialInteractionMonitoringInitialDay"/></dt>
						<dd class="field2">
							<html:text property="initialMonitoringDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
						    <html:text property="initialMonitoringMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
							<html:text property="initialMonitoringYear" styleClass="date" styleId="ano" maxlength="4"/>
						</dd>	
						<dt><bean:message key="courseForm.socialInteractionMonitoringFinalDay"/></dt>
						<dd class="field2">
							<html:text property="finalMonitoringDay" styleClass="date2" styleId="dia" maxlength="2"/> / 
						    <html:text property="finalMonitoringMonth" styleClass="date2" styleId="mes" maxlength="2"/> / 
							<html:text property="finalMonitoringYear" styleClass="date" styleId="ano" maxlength="4"/>
						</dd>
						<dt><bean:message key="general.tools" /></dt>
							<div style="padding-left: 45px;">
								<html:checkbox property="forum" value="Forum">
									Fórum
								</html:checkbox>

								<html:checkbox property="message" value="Message">
									Mensagem
								</html:checkbox>
							
								<html:checkbox property="chat" value="Chat">
									Chat
								</html:checkbox>
								<html:checkbox property="twitter" value="Twitter">
									Twitter
								</html:checkbox>
							</div>	
						<dt><bean:message key="courseForm.socialInteractionMonitoringMetrics" /></dt>
						<dd class="field2">
							<html:select property="socialMethod">
								<option value="0"><bean:message key="activities.select" /></option>
								<option value="1">Density (group)</option>
								<option value="2">Heterogeneity Degree (group)</option>
								<option value="3">Visibility Degree (student)</option>
								<option value="4">Isolation Degree (student)</option>
								<option value="5">Engagement Degree (student)</option>
								<option value="6">Degree of Information Mediation (student)</option>
								<option value="7">Prestige (student)</option>
							</html:select>				
						</dd>
						<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
						<dt style="text-align:center" ><html:submit property="socialInteractionMonitoring" styleClass="button"><bean:message key="general.generatepdfreport"/></html:submit>
					</html:form> 
					
				</dl>
					
			</div>	
					
			<jsp:include page="/jsp/conf/footer.jsp" />

		</div>
</body>	
	
</html:html>
