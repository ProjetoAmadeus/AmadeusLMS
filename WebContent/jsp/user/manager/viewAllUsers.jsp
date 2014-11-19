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
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"/>
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
				<li>Usuários do Sistema</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><b><img src="themes/default/imgs/menu/users-16x16.png" title="Usuários do Sitema" />&nbsp;Usuários do Sistema</b></li>
					<li><html:link action="user.do?method=showViewUserNewInManagerUsers"><img src="themes/default/imgs/menu/user-16x16.png" title="Nova Conta" border="0" />&nbsp;Nova Conta</html:link></li>
					<li><html:link action="user.do?method=showViewSendMailInManagerUsers"><img src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" border="0" />&nbsp;Enviar Email</html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<div class="expansibleBoxTitle">
				<div class="expansibleBoxTitleCollLeft">
				<label class="labelAttribute">Pesquisa Avançada...</label>
				</div>
				<div id="advancedSearch" onclick="minusPlus('#advancedSearch','#divAdvancedSearch');" class="expansibleBoxTitleCollRight imgPlus"></div>
			</div>
			<div style="padding: 5px; border-color: #eeeeee; border-style: solid; border-width: 0px 1px 1px 1px; display: none;" id="divAdvancedSearch">
				<form action="managerUser.do" method="get">
				<input type="hidden" name="method" value="searchUsers"/>
				<table border="0" width="100%">
					<tr>
						<td align="right">
							<label class="labelAttribute">Nome:</label>
						</td>
						<td>
							<input name="userName" type="text" style="width: 220px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="labelAttribute">Tipo de Usuário:</label>
						</td>
						<td>
							<select name="userType">
								<option value="-1">Todos</option>
								<option value="1">Aluno</option>
								<option value="2">Professor</option>
								<option value="0">Admin</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="Pesquisar"/>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<display:table pagesize="20" name="users" class="displaytag" id="user" requestURI="user.do?method=showViewAllUsersInManagerUsers">
				<display:setProperty name="basic.show.header" value="false"/>
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				<display:column title="Usuários do Sistema" sortable="true" headerClass="sortable">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td rowspan="3" width="30px" valign="top">
							<html:link action="user.do?method=showViewUserProfileInManagerUsers&userId=${user.id}">
							<div class="smallPicture">
								<img class="smallPhoto" src="user.do?method=showPhoto&id=${user.id}" />
							</div>
							</html:link>
						</td>		
						<td width="100px">
							<label id="userStatus${user.id}" class="offlineUser" title="">&nbsp;</label>
						</td>
						<td colspan="2" align="right">
							<!-- 
							<html:link action="user.do?method=showViewUserProfileInManagerUsers&userId=${user.id}">
								<img border="0" src="themes/default/imgs/menu/user-info-16x16.png" title="Perfil do Usuário" />
							</html:link>
							&nbsp; -->
							<html:link action="/user.do?method=showViewEditUserInManagerUsers&userId=${user.id}">
								<img border="0" src="themes/default/imgs/menu/edit-16x16.png" title="Editar Perfil do Usuário" />
							</html:link>
							&nbsp;
							<html:link action="/user.do?method=showViewSendMailInManagerUsers&to=${user.person.email}">
								<img border="0" src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />
							</html:link>
							&nbsp;
							<c:if test="${!(user.typeProfile eq 'INACTIVE')}">
							<html:link action="/managerUser.do?method=removeUser&to=${user.id}">
							<img border="0" src="themes/default/imgs/menu/remove-16x16.png" title="Remover Usuário" />
							</html:link>
							</c:if>
							<c:if test="${(user.typeProfile eq 'INACTIVE')}">
							<html:link action="/managerUser.do?method=allowUserLogon&to=${user.id}">
							<img border="0" src="themes/default/imgs/menu/allow_permission-16x16.png" title="Permitir Acesso ao Usuário" />
							</html:link>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 19px;">
							<bean:write name="user" property="person.name" filter="false"/>
						</td>
						<td width="45px" align="center">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 19px;">
							<font class="smallPictureEmail"><bean:write name="user" property="person.email" filter="false"/></font>
							<script type="text/javascript">userStatus(${user.id});</script>
						</td>
						<td width="45px" align="center">
							&nbsp;
						</td>
					</tr>
				</table>
			</display:column>
			</display:table>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>