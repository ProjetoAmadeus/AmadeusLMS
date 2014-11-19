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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Objeto de Aprendizagem</title>
	<c:if test="${param.urlType eq 'site'}">
		<META HTTP-EQUIV="Refresh" CONTENT="0 ; URL= ${param.url}">
	</c:if>
	
	<script type="text/javascript" src="js/managementActivity.js"></script>
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
</head>
<body onunload="endSession(${param.idHistory}, 0);">

	<c:if test="${param.urlType eq 'flash'}">
		<object width="700" height="400">
		  <param name="movie" value="${param.url}" />
		  <param name="quality" value="high" />
		  <param name="allowFullScreen" value="true"></param>
		  <param name="allowscriptaccess" value="always"></param>
		  <embed src="${param.url}" width="700" height="400"></embed>
		</object>
	</c:if>
	
	<c:if test="${param.urlType eq 'image'}">
		<img src="${param.url}" alt="Nome do Objeto" align="left" />
	</c:if>
	
</body>
</html>