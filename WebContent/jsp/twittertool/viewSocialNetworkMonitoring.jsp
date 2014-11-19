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

<logic:notPresent name="user"> 
	<logic:redirect action="course.do?method=showViewCourseNotLogged&courseId=${course.id}"/>			
</logic:notPresent>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/managementCourse.js"></script>
	<link href="themes/default/css/table.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="viewCourse.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li>${course.name}</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="1" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
			
			<!-- Added By Nailson Cunha -->
			<div id="twitter-container">
				<jsp:include page="/jsp/twittertool/userTwitter.jsp" />
			</div>
		</div>						
		<div id="pContent" class="pContent">
			<div id="social-network-monitoring">
				<h2>Social Network Monitoring</h2>
				<div id="social-network-monitoring-content">
					<img src="themes/default/img/twitter_icon.jpg" /><br />
					<label for="ipt-hashtag" id="lbl-hashtag" class="lbl-hashtag">Hashtag:&nbsp;</label><small id="hash-alert"></small><br />
					<input type="text" id="ipt-hashtag" name="ipt-hashtag" class="ipt-hashtag" /><br />	
					
					<label id="lbl-monitoring" class="lbl-monitoring">Monitoring:</label><br />
					<input type="button" class="monitoring-start" id="monitoring-start" value="Start" />			
					<input type="button" class="monitoring-stop" id="monitoring-stop" value="Stop" />
					<br /><br />
					<div id="waiting"></div>
				</div>
			</div>
		</div>
		<div id="pRightMenu" class="pRightMenu">
			<jsp:include page="/jsp/messenger/messenger.jsp" />	
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
</html>