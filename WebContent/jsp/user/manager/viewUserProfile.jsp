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
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript">userStatus(${userProfile.id});</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Gerenciar Usuários</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="user.do?method=showViewAllUsersInManagerUsers">Gerenciar Usuários</html:link></li>
				<li>Perfil do Usuário</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewAllUsersInManagerUsers"><img src="themes/default/imgs/menu/users-16x16.png" border="0" title="Usuários do Sitema" />&nbsp;Usuários do Sistema</html:link></li>
					<li><b><img src="themes/default/imgs/menu/user-info-16x16.png" title="Perfil do Usuário" />&nbsp;Perfil do Usuário</b></li>
					<li><html:link action="user.do?method=showViewUserNewInManagerUsers"><img src="themes/default/imgs/menu/user-16x16.png" title="Nova Conta" />&nbsp;Nova Conta</html:link></li>
					<li><html:link action="user.do?method=showViewSendMailInManagerUsers"><img src="themes/default/imgs/menu/email-16x16.png" border="0" title="Enviar E-mail" />&nbsp;Enviar Email</html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<table border="0" cellpadding="0" cellspacing="0" width="100%;">
				<tr align="right">
					<td colspan="2">
						<html:link action="/user.do?method=showViewEditUserInManagerUsers&userId=${userProfile.id}">
							<img border="0" src="themes/default/imgs/menu/edit-16x16.png" title="Editar Perfil do Usuário" />
						</html:link>
						&nbsp;
						<html:link action="/user.do?method=showViewSendMailInManagerUsers&to=${userProfile.person.email}">
							<img border="0" src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />
						</html:link>
					</td>
				</tr>
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
							<td class="formAttribute"><bean:message key="general.login"/>:</td>
							<td><bean:write name="userProfile" property="login" filter="false"/></td>
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
					<td class="formAttribute">Tipo de Usuário:</td>
					<td><bean:write name="userProfile" property="typeProfile" filter="false"/></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.cpf" />:</td>
					<td><bean:write name="userProfile" property="person.cpf" filter="false"/></td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute" align="right"><bean:message key="editUserForm.phoneNumber" />:</td>
					<td><bean:write name="userProfile" property="person.phoneNumber" filter="false"/></td>
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
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>