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
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<html:html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="listParticipants.title"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}"><bean:write name="course" property="name"/></html:link></li>
				<li><bean:message key="listParticipants.heading"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="3" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>						
		<div id="pContent" class="pContent">
		
			<div class="containerTitle">
				<label class="labelAttribute"><bean:message key="viewCourse.teachers"/></label>
			</div>
			<display:table name="teachers" class="displaytag" id="teacher">
			<display:setProperty name="basic.show.header" value="false"/>
			<display:column title="teacher" sortable="true" headerClass="sortable">
				<c:if test="${(user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER')}">
					<html:link action="user.do?method=showViewMyProfile&userId=${teacher.accessInfo.id}">
					<div class="smallPicture">
						<img class="smallPhoto" src="user.do?method=showPhoto&id=${teacher.accessInfo.id}" />
					</div>
					</html:link>
				</c:if>
				<c:if test="${!((user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER'))}">
					<html:link action="user.do?method=showViewPublicData&userId=${teacher.accessInfo.id}">
					<div class="smallPicture">
						<img class="smallPhoto" src="user.do?method=showPhoto&id=${teacher.accessInfo.id}" />
					</div>
					</html:link>
				</c:if>
				<label id="userStatus${teacher.accessInfo.id}" class="offlineUser" title="">&nbsp;</label>
				<bean:write name="teacher" property="name" filter="false"/>
				<br /><br />
				<font class="smallPictureEmail"><bean:write name="teacher" property="email" filter="false"/></font>
				<script type="text/javascript">userStatus(${teacher.accessInfo.id});</script>
			</display:column>
			</display:table>				
			<br />
			
			<logic:notEmpty name="assistants">
				<div class="containerTitle">
					<label class="labelAttribute"><bean:message key="viewCourse.assistant"/></label>
				</div>
				<display:table name="assistants" class="displaytag" id="assistant">
				<display:setProperty name="basic.show.header" value="false"/>
				<display:column title="assistants" sortable="true" headerClass="sortable">
					<c:if test="${(user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER')}">
						<html:link action="user.do?method=showViewMyProfile&userId=${assistant.accessInfo.id}">
						<div class="smallPicture">
							<img class="smallPhoto" src="user.do?method=showPhoto&id=${assistant.accessInfo.id}" />
						</div>
						</html:link>
					</c:if>
					<c:if test="${!((user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER'))}">
						<html:link action="user.do?method=showViewPublicData&userId=${assistant.accessInfo.id}">
						<div class="smallPicture">
							<img class="smallPhoto" src="user.do?method=showPhoto&id=${assistant.accessInfo.id}" />
						</div>
						</html:link>
					</c:if>
					<label id="userStatus${assistant.accessInfo.id}" class="offlineUser" title="">&nbsp;</label>
					<bean:write name="assistant" property="name" filter="false"/>
					<br /><br />
					<font class="smallPictureEmail"><bean:write name="assistant" property="email" filter="false"/></font>
					<script type="text/javascript">userStatus(${assistant.accessInfo.id});</script>
				</display:column>
				</display:table>	
				<br />						
			</logic:notEmpty>
			
			<logic:notEmpty name="participants">
				<div class="containerTitle">
					<label class="labelAttribute"><bean:message key="viewCourse.students_"/></label>
				</div>
				<display:table name="participants" class="displaytag" id="participant" requestURI="course.do?method=showViewCourseParticipants">
				<display:setProperty name="basic.show.header" value="false"/>
				<display:column title="participant" sortable="true" headerClass="sortable">
					<c:if test="${(user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER')}">
						<html:link action="user.do?method=showViewMyProfile&userId=${participant.accessInfo.id}">
						<div class="smallPicture">
							<img class="smallPhoto" src="user.do?method=showPhoto&id=${participant.accessInfo.id}" />
						</div>
						</html:link>
						<span style="float: right;">
							<html:link action="course.do?method=unregisterStudentCourse&studentId=${participant.id}&courseId=${course.id}">
								<img border="0" src="themes/default/imgs/icons/cross_small.png" title="Desmatricular" />
							</html:link>
						</span>
					</c:if>
					<c:if test="${!((user.typeProfile eq 'ADMIN') || (userRoleInCourse.roleType eq 'TEACHER'))}">
						<html:link action="user.do?method=showViewPublicData&userId=${participant.accessInfo.id}">
						<div class="smallPicture">
							<img class="smallPhoto" src="user.do?method=showPhoto&id=${participant.accessInfo.id}" />
						</div>
						</html:link>
					</c:if>
					<label id="userStatus${participant.accessInfo.id}" class="offlineUser" title="Offline">&nbsp;</label>
					<bean:write name="participant" property="name" filter="false"/>	
					<br /><br />
					<font class="smallPictureEmail"><bean:write name="participant" property="email" filter="false"/></font>
					<script type="text/javascript">userStatus(${participant.accessInfo.id});</script>
					
				</display:column>
				</display:table>
				<br />
			</logic:notEmpty>
		</div>
		<div id="pRightMenu" class="pRightMenu"></div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
</html:html>