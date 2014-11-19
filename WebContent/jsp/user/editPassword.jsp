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
<%@ taglib uri="/WEB-INF/struts-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>


<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
           </div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="editPassword.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="editPassword.heading"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewMyProfile"><img src="themes/default/imgs/menu/user-info-16x16.png" border="0" title="Meu Perfil" />&nbsp;<bean:message key="userPrivateData.title2"/></html:link></li>
					<li><a href="viewEditUser.do?method=<bean:message key="editUser.heading"/>"><img src="themes/default/imgs/menu/user-edit-16x16.png" border="0" title="Editar Perfil" />&nbsp;<bean:message key="editUser.heading"/></a></li>
					<li><img src="themes/default/imgs/menu/user-password-16x16.png" border="0" title="Trocar Senha" />&nbsp;<b><bean:message key="editPassword.heading"/></b></li>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR') && canRequestTeaching}">
				   	<li><html:link action="user.do?method=showViewTeachingRequest"><img src="themes/default/imgs/menu/user-card-16x16.png" border="0" title="Solicitar Docência" />&nbsp;<bean:message key="teachingRequest.heading"/></html:link></li>
					</c:if>
					<li><html:link action="user.do?method=showViewClassMates"><img src="themes/default/imgs/menu/user-group-16x16.png" border="0" title="Colegas de Sala" />&nbsp;<bean:message key="sideMenu.classmates"/></html:link></li>
	         		<li><html:link action="openIDActions.do?method=showViewManagerOpenIDs"><img src="themes/default/imgs/menu/openid.png" border="0" title="Google Account"/>&nbsp;Google Account</html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<html:errors property="currentPassword" />
			<html:errors property="newPassword" />
			<html:errors property="newPasswordConfirmation" />
			<logic:messagesPresent message="true">
				<ul>
					<html:messages id="message" message="true">
						<li><bean:write name="message" /></li>
					</html:messages>
				</ul>
			</logic:messagesPresent>
			<html:form action="/editPassword" focus="currentPassword">
			<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td class="formAttribute">*<bean:message key="editPassword.currentPassword"/>:</td>
					<td>
						<html:password property="currentPassword" style="width: 100%;" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editPassword.currentPasswordDescription"/></td>
				</tr>
				
				<tr>
					<td class="formAttribute">*<bean:message key="editPassword.newPassword"/>:</td>
					<td>
						<html:password property="newPassword" style="width: 100%;" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editPassword.newPasswordDescription"/></td>
				</tr>
				
				<tr>
					<td class="formAttribute">*<bean:message key="editPassword.newPasswordConfirmation"/>:</td>
					<td>
						<html:password property="newPasswordConfirmation" style="width: 100%;" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editPassword.newPasswordConfirmationDescription"/></td>
				</tr>
				
				<tr>
					<td></td><td align="right"><html:submit property="editPassword" styleClass="button"><bean:message key="editPassword.edit"/></html:submit></td>
				</tr>
			</table>
			</html:form>
	  	</div>			
		<jsp:include page="/jsp/conf/footer.jsp" /> 
	</div>
</body>
</html>