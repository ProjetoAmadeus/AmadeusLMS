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
			<h2><bean:message key="teachingRequest.title"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="teachingRequest.heading"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewMyProfile"><img src="themes/default/imgs/menu/user-info-16x16.png" border="0" title="Meu Perfil" />&nbsp;<bean:message key="userPrivateData.title2"/></html:link></li>
					<li>
						<a href="viewEditUser.do?method=<bean:message key="editUser.heading"/>">
							<img src="themes/default/imgs/menu/user-edit-16x16.png" border="0" title="Editar Perfil" />&nbsp;<bean:message key="editUser.heading"/>
						</a>
					</li>
					<li>
						<html:link action="user.do?method=showViewEditPassword">
							<img src="themes/default/imgs/menu/user-password-16x16.png" border="0" title="Trocar Senha" />&nbsp;<bean:message key="editPassword.heading"/>
						</html:link>
					</li>
					<li>
				   		<img src="themes/default/imgs/menu/user-card-16x16.png" border="0" title="Solicitar Docência" />&nbsp;<b><bean:message key="teachingRequest.heading"/></b>
				   	</li>
					<li>
						<html:link action="user.do?method=showViewClassMates">
							<img src="themes/default/imgs/menu/user-group-16x16.png" border="0" title="Colegas de Sala" />&nbsp;<bean:message key="sideMenu.classmates"/>
						</html:link>
					</li>
	         		<li>
	         			<html:link action="openIDActions.do?method=showViewManagerOpenIDs">
	         				<img src="themes/default/imgs/menu/openid.png" border="0" title="Google Account"/>&nbsp;Google Account
		        		</html:link>
		        	</li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<html:errors />
			<html:form action="/teachingRequest.do">
			<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td class="formAttribute">*<bean:message key="requestForm.degree"/></td>
					<td>
					<html:select property="degree" name="resume">
						<html:option value=""><bean:message key="requestForm.degree.select"/></html:option>
						<html:option value="degree1"><bean:message key="requestForm.degree.degree1"/></html:option>
						<html:option value="degree2"><bean:message key="requestForm.degree.degree2"/></html:option>
						<html:option value="degree3"><bean:message key="requestForm.degree.degree3"/></html:option>
						<html:option value="specialization"><bean:message key="requestForm.degree.specialization"/></html:option>
						<html:option value="master"><bean:message key="requestForm.degree.master"/></html:option>
						<html:option value="phd"><bean:message key="requestForm.degree.phd"/></html:option>
						<html:option value="other" ><bean:message key="requestForm.degree.other"/></html:option>
					</html:select>
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="requestForm.degreeDescription"/></td>
				</tr>
				
				<tr>
					<td class="formAttribute">*<bean:message key="requestForm.year"/></td>
					<td><html:text property="year" name="resume" maxlength="4" size="5"/></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="requestForm.yearDescription"/></td>
				</tr>
					
				<tr>
					<td class="formAttribute">*<bean:message key="requestForm.instituition"/></td>	
					<td><html:text property="instituition" name="resume" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="requestForm.instituitionDescription"/></td>
				</tr>
					
				<tr>
					<td class="formAttribute">*<bean:message key="requestForm.description"/></td>	
					<td><html:textarea property="description" name="resume" styleClass="textarea"/></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="requestForm.descriptionDescription"/></td>
				</tr>
					
				<tr>
					<td class="formAttribute">*<bean:message key="requestForm.interest"/></td>	
					<td><html:textarea property="interest" styleClass="textarea"/></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="requestForm.interestDescription"/></td>
				</tr>
				
				<tr>
					<td></td><td align="right"><html:submit property="teachingRequest" styleClass="button"><bean:message key="teachingRequest.sendRequest"/></html:submit></td>
				</tr>
			</table>
			</html:form>									
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>