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
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html:html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type='text/javascript' src='js/managementActivity.js'></script>
	<script type="text/javascript" src="js/requestAssistance.js"></script>

	<script type="text/javascript">
		function minusPlusP(id){
			var valueLink = $("#link"+id).attr("class");
			$("#pendingTask"+id).toggle("clip");
			if (valueLink.indexOf('imgPlus') != -1) {
				$("#link"+id).removeClass("imgPlus");
				$("#link"+id).addClass("imgMinus");
			} else { 
				$("#link"+id).removeClass("imgMinus");
				$("#link"+id).addClass("imgPlus"); 
			};
		}
	</script>
</head>	
<body onload="init('<bean:message key="request.approve"/>','<bean:message key="general.cancel"/>');">
	<div id="pBody" class="pBody">	
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
        </div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="pendingTasks.title"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="sideMenu.tasks"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewMyProfile"><bean:message key="sideMenu.myProfile"/></html:link></li>
					<li><html:link action="fEditUser"><bean:message key="editUser.heading"/></html:link></li>
					<li><html:link action="user.do?method=showViewEditPassword"><bean:message key="editPassword.heading"/></html:link></li>
					<li><html:link action="user.do?method=showViewClassMates"><bean:message key="sideMenu.classmates"/></html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<dl class="pendingTitle"><bean:message key="pendingTasks.approveAssistance"/></dl>
			<html:errors />
			<c:if test="${requestsSize != 0}">
				<logic:iterate id="userRequest" name="requests">
					<div id="request${userRequest.id}" class="requestColapsed pinball-scoop">
						<a onclick="showDetails(${userRequest.id});" class="pinball-sinkhole"></a>
						<bean:message key="general.name"/>: ${userRequest.person.name}<br />
						<bean:message key="courseForm.course"/>: ${userRequest.course.name}<br />
						<div id="reqInfo${userRequest.id}" style="display: none" >
							<bean:message key="requestForm.degree"/>: {userRequest.person.resume.degree}<br />
							<bean:message key="requestForm.year"/>: ${userRequest.person.resume.year}<br />
							<bean:message key="requestForm.instituition"/>: ${userRequest.person.resume.instituition}<br />
							<bean:message key="requestForm.interest"/>: ${userRequest.interest}<br />
							<bean:message key="requestForm.description"/>: ${userRequest.person.resume.description}<br />
						</div>
						<div id="reqJust${userRequest.id}" style="display: none">
							<bean:message key="pendingTasks.justification"/>:<br />
							<textarea cols="32" id="reqJustification${userRequest.id}"></textarea>
						</div>
						<div id="reqBtns${userRequest.id}" style="display: none" class="moduleControl">
							<input type="button" id="reqBtnLeft${userRequest.id}" value="<bean:message key="request.approve"/>" onclick="approveRequest(${userRequest.id})"/>
							<input type="button" id="reqBtnRight${userRequest.id}" value="<bean:message key="request.reprove"/>" onclick="showJustBox(${userRequest.id})"/>
						</div>
					</div>
				</logic:iterate>
			</c:if>
			<c:if test="${requestsSize eq 0}">
				<ul>
					<li><h3><bean:message key="pendingTasks.notHave"/></h3></li>
				</ul>
			</c:if>
			<div class="containerTitle">
				<label class="labelAttribute"><bean:message key="pedding.tasks.activites"/></label>
			</div>
			<div style="padding: 5px; border-color: #eeeeee; border-style: solid; border-width: 0px 1px 1px 1px;">
			
			<c:forEach var="task" items="${tasksMaterialRequest}" varStatus="status">
				<div class="expansibleBoxTitle">
					<div class="expansibleBoxTitleCollLeft">
						<bean:write name="task" property="name"/>
					</div>
					<div id="link${task.id}" onclick="minusPlusP(${task.id});" class="expansibleBoxTitleCollRight imgPlus"></div>
				</div>
				<div style="padding: 5px; border-color: #eeeeee; border-style: solid; border-width: 0px 1px 1px 1px; display: none;" id="pendingTask${task.id}">
					<script type="text/javascript">
						showViewMaterialRequestActivity(${task.id},${task.id},true);
					</script>
					<div id="editOption${task.id}"></div>
				</div>
				<c:if test="${!status.last}"><br /></c:if>
			</c:forEach>
			
			<br />
			<c:forEach var="task" items="${tasksEvaluation}" varStatus="status">
				<div class="expansibleBoxTitle">
					<div class="expansibleBoxTitleCollLeft">
						<bean:write name="task" property="description"/>
					</div>
					<div id="link${task.id}" onclick="minusPlusP(${task.id});" class="expansibleBoxTitleCollRight imgPlus"></div>
				</div>
				<div style="padding: 5px; border-color: #eeeeee; border-style: solid; border-width: 0px 1px 1px 1px; display: none;" id="pendingTask${task.id}">
					<script type="text/javascript">
						showViewEvaluationActivity(${task.id},${task.id},true);
					</script>
					<div id="editOption${task.id}"></div>
				</div>
				<c:if test="${!status.last}"><br /></c:if>
			</c:forEach>
			
			</div>	
		</div>
		<div id="pRightMenu" class="pRightMenu"></div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html:html>