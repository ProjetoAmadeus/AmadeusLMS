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
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/settings.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#tabs").tabs({
				
			});
		});
	</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Configurações do Sistema</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li>Configurações do Sistema</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">			
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li>
						<html:link href="settingsActions.do?method=showViewMobileSettings"><img border="0" src="themes/default/imgs/menu/mobile.png" />&nbsp;Mobile</html:link>
					</li>
					<li>
						<img src="themes/default/imgs/menu/web.png"/>&nbsp;<b>Web</b>
					</li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent2">
			<div id="infoMessage" class="msgBoxSuccess" style="display: none;"></div>
			<div id="tabs">
				<ul>
					<li><a href="#system">Sistema</a></li>
					<li><a href="#mailSender">Mail Sender</a></li>
					<li><a href="#security">Segurança</a></li>
				</ul>
				<div id="system">
					<jsp:include page="web/system.jsp" />
				</div>
				<div id="mailSender">
					<jsp:include page="web/mailSender.jsp" />
				</div>
				<div id="security">
					<jsp:include page="web/security.jsp" />
				</div>
			</div>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>