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

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<link href="<html:rewrite page="/themes/default/css/displaytag.css" />" rel="stylesheet" type="text/css"></link>
	<jsp:include page="/jsp/conf/header.jsp" />
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="onlineUser.title"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="onlineUser.title"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><b><bean:message key="onlineUser.title"/></b></li>
					<li><html:link action="user.do?method=showViewMyProfile"><bean:message key="userPrivateData.title2"/></html:link></li>
					<li><a href="viewEditUser.do?method=<bean:message key="editUser.heading"/>"><bean:message key="editUser.heading"/></a></li>
					<li><html:link action="user.do?method=showViewEditPassword"><bean:message key="editPassword.heading"/></html:link></li>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR') && canRequestTeaching}">
				   	<li><html:link action="user.do?method=showViewTeachingRequest"><bean:message key="teachingRequest.heading"/></html:link></li>
					</c:if>
					<li><html:link action="user.do?method=showViewClassMates"><bean:message key="sideMenu.classmates"/></html:link></li>
					<c:if test="${user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR'}">
	        				<li><html:link forward="fInsertCourseStepOne"><bean:message key="sideMenu.createNewCourse"/></html:link></li>
	         		</c:if>
	         		<li>
	         			<html:link action="openIDActions.do?method=showViewManagerOpenIDs">
	         			<img src="themes/default/imgs/menu/openid.png" style="vertical-align: middle;" border="0" title="OpenID" />&nbsp;Google Account
		        		</html:link>
		        	</li>	
				</ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<div class="line">
				<display:table name="onlineUsers" pagesize="20" class="displaytag" id="onlineUser" style="width: 100%;" requestURI="user.do?method=showViewOnlineUsers">
				<display:setProperty name="basic.show.header" value="false"/>
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				<display:column title="OnlineUsers" sortable="true" headerClass="sortable">
					<c:if test="${user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR'}">
						<html:link action="user.do?method=showViewMyProfile&userId=${onlineUser.id}">
						<div style="background-color:white; border:1px solid #D0D0D0; float:left; margin-right:0.7em; padding:2px;">
						<img src="user.do?method=showPhoto&id=${onlineUser.id}" height="30px" width="30px" border="0" />
						</div>
						</html:link>
					</c:if>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR')}">
						<html:link action="user.do?method=showViewPublicData&userId=${onlineUser.id}">
						<div style="background-color:white; border:1px solid #D0D0D0; float:left; margin-right:0.7em; padding:2px;">
						<img src="user.do?method=showPhoto&id=${onlineUser.id}" height="30px" width="30px" border="0" />
						</div>
						</html:link>
					</c:if>
					<label id="userStatus${onlineUser.id}" class="offlineUser" title="">&nbsp;</label>
					${onlineUser.person.name}
					<br /><br />
					<font color="#888888">${onlineUser.person.email}</font>
					<script type="text/javascript">userStatus(${onlineUser.id});</script>
				</display:column>
				</display:table>
			</div>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>