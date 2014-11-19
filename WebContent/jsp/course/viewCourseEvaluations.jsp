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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Avaliações do Curso</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}"><bean:write name="course" property="name"/></html:link></li>
				<li>Avaliações do Curso</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="4" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>
		<div id="pContent" class="pContent2">
			<div style="overflow-x: scroll; min-height: 400px;">
			<c:set var="moreDivWidth" value="0"/>
			<c:forEach var="module" items="${course.modules}">
				<c:if test="${ (fn:length(module.evaluations) + fn:length(module.materialRequests)) != 0 }">
				
				<c:set var="divWidth" value="${ ((fn:length(module.evaluations) + fn:length(module.materialRequests)) * 100) + 150 }" />
				<c:if test="${ divWidth > moreDivWidth }">
					<c:set var="moreDivWidth" value="${divWidth}" />
				</c:if>
				<div style="width: ${moreDivWidth}px; float: left;">&nbsp;</div>
				<div style="width: ${moreDivWidth}px; padding: 5px 5px 5px 5px; background-color: #fbfbfb; border-color: #f0f0f0; border-style: solid; border-width: 1px 1px 1px 1px; float: left;"><label class="labelAttribute">${module.name}</label></div>
				<div style="width: ${divWidth}px;">
				<div style="padding-left: 10px; width: 140px; float: left;">Aluno</div>
				<c:forEach var="evaluation" items="${module.evaluations}">
					<div style="width: 100px; float: left; text-align: center; overflow: hidden;">${evaluation.description}</div>
				</c:forEach>
				<c:forEach var="materialRequest" items="${module.materialRequests}">
					<div style="width: 100px; float: left; text-align: center; overflow: hidden;">${materialRequest.name}</div>
				</c:forEach>
				<!-- <div style="width: 100px; float: left; text-align: center; overflow: hidden;">Média</div> -->
				<c:forEach var="participant" items="${participants}">
					<div style="padding-left: 10px; width: 140px; float: left; height: 14px; overflow: hidden;">${participant.name}</div>
					<c:forEach var="evaluation" items="${module.evaluations}">
					<div style="width: 100px; float: left; text-align: center;">
						<c:import url="/evaluation.do?method=getUserEvaluationGrade&personId=${participant.id}&evaluationId=${evaluation.id}" />
					</div>
					</c:forEach>
					<c:forEach var="materialRequest" items="${module.materialRequests}">
					<div style="width: 100px; float: left; text-align: center;">
						<c:import url="/materialActivity.do?method=getUserMaterialGrade&personId=${participant.id}&materialRequestId=${materialRequest.id}"/>
					</div>
					
					</c:forEach>
				</c:forEach>
				</div>
				
				</c:if>
			</c:forEach>
			</div>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>