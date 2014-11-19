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
	<logic:redirect action="system.do?method=showViewWelcome" />
</logic:notPresent>

<c:if test="${!isProfileAdmin}">
	<logic:redirect forward="fNotAllowed" />
</c:if>

<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" src="js/requestTeacher.js"></script>

<html:html>
<script type="text/javascript">
	function lfApproveRequest(userRequestId) {
		approveRequest(userRequestId,"<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif />Por favor aguarde...","Operação realizada com sucesso!");
	}

	function lfShowJustBox(userRequestId) {
		showJustBox(userRequestId,"<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif />Por favor aguarde...","Operação realizada com sucesso!");
	}
</script>

<jsp:include page="/jsp/conf/header.jsp" />
	<body onload="init('<bean:message key="request.approve"/>','<bean:message key="general.cancel"/>');">
	<div id="all">
		<div id="header">
			<jsp:include page="/jsp/conf/login.jsp" />
        </div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="page_title">
			<h2><bean:message key="pendingTasks.title"/></h2>
		</div>
		<div id="breadcrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="sideMenu.tasks"/></li>
			</ul>
		</div>
		<div id="side_menu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewMyProfile"><bean:message key="sideMenu.myProfile"/></html:link></li>
					<li><html:link action="fEditUser"><bean:message key="editUser.heading"/></html:link></li>
					<li><html:link action="user.do?method=showViewEditPassword"><bean:message key="editPassword.heading"/></html:link></li>
					<li><html:link action="user.do?method=showViewClassMates"><bean:message key="sideMenu.classmates"/></html:link></li>
			    </ul>
			</div>
		</div>
		<div id="content">
			<dl class="pendingTitle"><bean:message key="pendingTasks.approveTeaching"/></dl>
			<c:if test="${requestsSize != 0}">
				<logic:iterate id="userRequest" name="requests">
					<div id="request${userRequest.id}" class="requestColapsed pinball-scoop">
						<a onclick="showDetails(${userRequest.id});" class="pinball-sinkhole"></a>
						<bean:message key="general.name"/>: ${userRequest.person.name}<br />
						<div id="reqInfo${userRequest.id}" style="display: none">
							<bean:message key="requestForm.degree"/>: ${userRequest.person.resume.degree}<br />
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
							<input type="button" id="reqBtnLeft${userRequest.id}" value="<bean:message key="request.approve"/>" onclick="lfApproveRequest(${userRequest.id});"/>
							<input type="button" id="reqBtnRight${userRequest.id}" value="<bean:message key="request.reprove"/>" onclick="lfShowJustBox(${userRequest.id})"/>
						</div>
					</div>
				</logic:iterate>
			</c:if>
			<c:if test="${requestsSize eq 0}">
				<ul>
					<li><h3><bean:message key="pendingTasks.notHave"/></h3></li>
				</ul>
			</c:if>					
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html:html>