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

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript">userStatus(${userProfile.id});</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<logic:present name="user">
				<jsp:include page="/jsp/conf/login.jsp" />
			</logic:present>
			<logic:notPresent name="user">
				<div id="login">
					<dl id="formlogin">
					  <html:form action="/validateLogin" focus="login" >				
				  		<dt><html:text property="login"  styleClass="inputlogin" size="15" maxlength="15" /> </dt>
		      			<dt><html:password property="password" styleClass="inputlogin" size="15" maxlength="15" />&nbsp;
							<html:submit property="logonForm" styleClass="button"><bean:message key="general.submit"/></html:submit></dt>
	       		      </html:form>
					</dl>
				</div>
		    </logic:notPresent>
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="userPublicData.title" /></h2>
		</div>
		
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="userPublicData.heading"/><bean:write name="userProfile" property="person.name"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<logic:present name="user"> 
						<li><html:link action="user.do?method=showViewInsertUser" styleClass="insert"><bean:message key="sideMenu.sendMessage"/></html:link></li>
						<!-- <li><bean:message key="sideMenu.addContact"/></li> -->
					</logic:present>
					<logic:notPresent name="user">
					<c:if test="${webSettings.securityAutoSigning}">
			           	<li><html:link action="user.do?method=showViewInsertUser" styleClass="insert"><bean:message key="userRegistrationForm.heading"/></html:link></li>
	       		    </c:if>
	       		    </logic:notPresent>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<table border="0" cellpadding="0" cellspacing="0" width="100%;">
				<tr>
					<td style="width: 125px;">
						<div class="normalPicture">
							<img class="normalPhoto" src="user.do?method=showPhoto&id=${userProfile.id}" />
						</div>
					</td>
					<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%;">
						<tr>
							<td style="width: 35px;" class="formAttribute">Status:</td>
							<td><label id="userStatus${userProfile.id}" class="offlineUser" title="">&nbsp;</label></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td class="formAttribute">Nome:</td>
							<td><bean:write name="userProfile" property="person.name" filter="false"/></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td class="formAttribute"><bean:message key="editUserForm.email"/>:</td>
							<td><bean:write name="userProfile" property="person.email" filter="false"/></td>
						</tr>
					</table>		
					</td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.gender" />:</td>
					<td>
					<logic:equal value="M" name="userProfile" property="person.gender">
						<bean:message key="userPrivateData.gender.male"/>
					</logic:equal>
					<logic:equal value="F" name="userProfile" property="person.gender">
						<bean:message key="userPrivateData.gender.female"/>
					</logic:equal>
					</td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.birthDate" />:</td>
					<td><bean:write name="userProfile" property="person.birthDate" format="dd / MM / yyyy" /></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.stateCity" />:</td>
					<td><bean:write name="userProfile" property="person.state" filter="false" /> - <bean:write name="userProfile" property="person.city" filter="fasle" /></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.degree" />:</td>
					<td>
						<c:if test="${ (userProfile.person.resume.degree != '') && (userProfile.person.resume.degree != null) }">
							<bean:message name="degree" />
						</c:if>
					</td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.year" />:</td>
					<td><bean:write name="userProfile" property="person.resume.year" filter="false" /></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.instituition" />:</td>
					<td><bean:write name="userProfile" property="person.resume.instituition" filter="false" /></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td  class="formAttribute"><bean:message key="editUserForm.description" />:</td>
					<td><bean:write name="userProfile" property="person.resume.description" filter="false" /></td>
				</tr>
			</table>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>