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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>

<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<meta http-equiv="refresh" content="5;url=<%=request.getContextPath()%>/system.do?method=showViewWelcome" />
	<jsp:include page="/jsp/conf/header.jsp" />
</head>
<body>
<input type="hidden" id="keyAccessDenied" value="<iseocdsfc83f93rhnfv983nnds838hfoihos83hjod9uhwjjhvf83wnhfv983nhfg98h398udfhhdia82jsd2>" />
	<div class="accessDenied">
		<br /><br />
		<div class="dAccessDenied">
			<div class="addtf"></div><div class="addtc"></div><div class="addtr"></div><div class="addcf"></div>
			<div class="addcc">
				<h1>
				<img src="themes/default/imgs/amadeus/accessDenied.png"/>
				&nbsp;&nbsp;<bean:message key="accessDenied.title"/></h1>
				<br />
				<h3><bean:message key="accessDenied.msgRedirect"/></h3>
			</div>
			<div class="addcr"></div><div class="addbf"></div><div class="addbc"></div><div class="addbr"></div>
		</div>
	</div>
</body>
</html>