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

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<div id="login">
				<dl id="formlogin">
					<html:form action="/validateLogin" focus="login" >				
			  		<dt><html:text property="login"  styleClass="inputlogin" size="15" maxlength="15" /> </dt>
	      			<dt><html:password property="password" styleClass="inputlogin" size="15" maxlength="15" />&nbsp;
						<html:submit property="logonForm" styleClass="button"><bean:message key="general.submit"/></html:submit></dt>
		            </html:form>
				</dl>
			</div>
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="remindPassword.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewWelcome"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="remindPassword.heading"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewInsertUser" styleClass="insert"><bean:message key="userRegistrationForm.heading"/></html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<dl class="insert2">
				<html:errors property="invalidEmail"/>
				<html:errors property="email"/>
				<html:form action="/remindPassword" focus="email">
				<dt><bean:message key="remindPassword.email"/></dt>
					<dd><html:text property="email" styleClass="formfield2" styleId="username"/></dd>
					<dd class="description"><bean:message key="remindPassword.emailDescription"/></dd>
					<dt class="field"><html:submit property="remindPassword" styleClass="button"><bean:message key="remindPassword.remind"/></html:submit></dt>
				</html:form>
			</dl>		
		</div>
		<div id="pRightMenu" class="pRightMenu">
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>