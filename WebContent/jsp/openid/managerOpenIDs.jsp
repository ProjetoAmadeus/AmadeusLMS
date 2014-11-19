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
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript">
		function minusPlus(id){
			var valueLink = $("#link").attr("class");
			$("#addNewOpenID").toggle("clip");
			if (valueLink.indexOf('imgPlus') != -1) {
				$("#link").removeClass("imgPlus");
				$("#link").addClass("imgMinus");
			} else { 
				$("#link").removeClass("imgMinus");
				$("#link").addClass("imgPlus"); 
			};
		}
	</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Google Account</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li>Google Account</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li>
						<html:link action="user.do?method=showViewMyProfile">
							<img src="themes/default/imgs/menu/user-info-16x16.png" border="0" title="Meu Perfil" />&nbsp;<bean:message key="userPrivateData.title2"/>
						</html:link>
					</li>
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
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR') && canRequestTeaching}">
				   	<li>
				   		<html:link action="user.do?method=showViewTeachingRequest">
				   			<img src="themes/default/imgs/menu/user-card-16x16.png" border="0" title="Solicitar Docência" />&nbsp;<bean:message key="teachingRequest.heading"/>
				   		</html:link>
				   	</li>
					</c:if>
					<li>
						<html:link action="user.do?method=showViewClassMates">
							<img src="themes/default/imgs/menu/user-group-16x16.png" border="0" title="Colegas de Sala" />&nbsp;<bean:message key="sideMenu.classmates"/>
						</html:link>
					</li>
	         		<li>
	         			<img src="themes/default/imgs/menu/openid.png" border="0" title="Google Account"/>&nbsp;<b>Google Account</b>
		        	</li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<c:if test="${messageInformation != null && messageInformation != ''}">
				<div class="msgBoxImportant">
					<img src="themes/default/imgs/menu/openid.png" title="OpenID" />&nbsp;${messageInformation}
				</div>
			</c:if>
			<display:table name="openIDs" class="displaytag" id="openID" style="width: 100%;">
				<display:setProperty name="basic.show.header" value="false"/>
				<display:column title="OpenID" sortable="true" headerClass="sortable" style="text-align: left; width: 60px;">
					<img src="themes/default/imgs/openid/google.png" />
				</display:column>
				<display:column title="Nome" sortable="true" headerClass="sortable" style="text-align: left;">
					${openID.name}
				</display:column>
				<display:column title="E-mail" sortable="true" headerClass="sortable" style="text-align: left;">
					${openID.email}
				</display:column>
				<display:column title="Ação" sortable="true" headerClass="sortable" style="text-align: center; cursor: pointer;">
					<img src="themes/default/imgs/icons/cross_small.png" title="<bean:message key="general.delete" />"
					onclick="deleteGoogleOpenId('openIDActions.do?method=deleteGoogleOpenId&openIDId=${openID.id}');" />
				</display:column>
			</display:table>
			<br />
			<div class="openIDBoxBody">
				<div class="expansibleBoxTitle">
					<div class="expansibleBoxTitleCollLeft">
						Adicionar o Google Account a sua conta do Amadeus.&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div id="link" onclick="minusPlus();" class="expansibleBoxTitleCollRight imgPlus"></div>
				</div>
				<div id="addNewOpenID" class="expansibleBoxContent">
					<div class="addNewGoogleOpenID">
						<div class="line">
							<img src="themes/default/imgs/openid/google.png" style="cursor: pointer;"
							onclick="requestAddNewGoogleOpenId('openIDActions.do?method=googleAccountsRequest');"/>
							&nbsp;&nbsp;&nbsp;
							<a href="http://openid.net/" target="_black">O que é OpenID</a>
							<img src="themes/default/imgs/amadeus/what.gif" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="pRightMenu" class="pRightMenu"></div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>