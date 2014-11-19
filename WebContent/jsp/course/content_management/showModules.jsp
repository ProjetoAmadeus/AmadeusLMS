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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html:html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript" src="js/managementModule.js"></script>
	<script type="text/javascript" src="js/managementActivity.js"></script>
	<script type="text/javascript" src="js/managementCourse.js"></script>
	<link href="themes/default/css/table.css" rel="stylesheet" type="text/css"/>
	<link href="themes/default/css/module.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Gestão de Conteúdo</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}">${course.name}</html:link></li>
				<li><bean:message key="menu.modules"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="2" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>						
		<div id="pContent" class="pContent">
			<c:if test="${user.typeProfile eq 'ADMIN' || userRoleInCourse eq 'TEACHER' || userRoleInCourse eq 'ASSISTANT'}">
			<table border="0" cellpadding="0" cellspacing="0" width="100%;" style="margin-bottom: 10px;">
				<tr align="right">
					<td>
						<html:link action="/course.do?method=showViewSendMail&courseId=${course.id}">
						<img border="0" src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />
						</html:link>
					</td>
				</tr>
			</table>
			</c:if>
			<div class="modAll">
				<div class="modBody">	
					<div class="modTitle"><bean:write name="course" property="name" filter="false"/></div>
					<div class="modTeachers">
					<bean:message key="module.teachers"/>:<br />
					<label id="userStatus${course.professor.accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;${course.professor.name}.
					<script type="text/javascript">userStatus(${course.professor.accessInfo.id});</script>
					</div>
					<div class="modAssistants">
						<logic:notEmpty name="assistants">
						    <bean:message key="module.assistants"/>:  
						    <c:forEach var="assistant" items="${assistants}" varStatus="status">
						    	<br /><label id="userStatus${assistant.accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;${assistant.name}
						    	<script type="text/javascript">userStatus(${assistant.accessInfo.id});</script>
						    </c:forEach>
					    </logic:notEmpty>
					</div>
					<div class="modData">
						<bean:message key="module.initialDate"/>: <bean:write property="initialCourseDate" name="course" format="dd/MM/yyyy"/>
					</div>
					<div class="modData">
						<bean:message key="module.finalDate"/>: <bean:write property="finalCourseDate" name="course" format="dd/MM/yyyy"/>
					</div>
				</div>
				<div id="form">
					<jsp:include page="/jsp/course/content_management/listModules.jsp"/>
				</div>
			</div>
		</div>
		<div id="pRightMenu" class="pRightMenu"></div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
	
</html:html>