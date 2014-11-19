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
	      			<dt><html:password property="password" styleClass="inputlogin" size="15" maxlength="15" />&nbsp;<html:submit property="logonForm" styleClass="button"><bean:message key="general.submit"/></html:submit></dt>
		       		</html:form>
				</dl>
			</div>
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="userRegistrationForm.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewWelcome"><bean:message key="menu.name"/></html:link></li>
				<li><bean:message key="userRegistrationForm.heading"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link forward="fRemindPassword" styleClass="insert"><bean:message key="remindPassword.heading"/></html:link></li>
			    </ul>
			</div>
		</div>						
		<div id="pContent" class="pContent">
			<dl class="insert2">
				<html:form action="/insertUser" focus="name" >
				<html:errors/>
				<dt><bean:message key="general.fullName"/></dt>
					<dd class="field"><html:text property="name" styleClass="formfield" styleId="realname"/></dd>
					<dd class="description"><bean:message key="userRegistrationForm.fullNameDescription"/></dd>
				<dt><bean:message key="general.email"/></dt>
					<dd class="field"><html:text property="email" styleClass="formfield" styleId="mail"/></dd>
					<dd class="description"><bean:message key="userRegistrationForm.emailDescription"/></dd>
				<dt><bean:message key="general.login"/></dt>
					<dd class="field"><html:text property="login" styleClass="formfield" size="15" maxlength="15" onblur="verificaEspaco(formUserRegister.login.value);" styleId="username" /></dd>
					<dd class="description"><bean:message key="userRegistrationForm.loginDescription"/></dd>
				<dt><bean:message key="general.password"/></dt>
					<dd class="field"><html:password property="password" styleClass="formfield" size="15" maxlength="15" styleId="password"/></dd>
					<dd class="description"><bean:message key="userRegistrationForm.passwordDescription"/></dd>
				<dt><bean:message key="userRegistrationForm.passwordConfirmation"/></dt>
					<dd class="field"><html:password property="passwordConfirmation" styleClass="formfield" size="15" maxlength="15" styleId="passwordConfirmation"/></dd>
					<dd class="description"><bean:message key="userRegistrationForm.passwordConfirmationDescription"/></dd>
				    <dt class="field"><html:submit property="insertUser" styleClass="button"><bean:message key="general.register"/></html:submit></dt>
					<br/><br/><ul id="required"><li><bean:message key="general.required"/></li></ul>
				</html:form>
			</dl>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
</html>