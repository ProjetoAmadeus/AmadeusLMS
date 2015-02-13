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

	<title><bean:message key="general.title"/></title>
	
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	
	<link href="themes/default/css/css.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/amadeus.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/messenger.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/twittertool.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/content_management.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/jqueryui/smoothness/jquery-ui-1.7.1.custom.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/imgs/favicon.ico" rel="shortcut icon" />
	
	<!-- <link href="themes/default/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="themes/default/css/bootstrap.min.css" rel="stylesheet" type="text/css"/> -->
	<link href="themes/default/css/nyroModal.css" rel="stylesheet" type="text/css"/>
	
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<!-- <script type="text/javascript" src="js/jqueryui/jquery-1.3.2.min.js"></script> -->
	<script type="text/javascript" src="js/jqueryui/jquery-1.7.2.min.js"></script>
	
	<!-- <script type="text/javascript" src="js/bootstrap/bootstrap-modal.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script> -->
	
	<script type="text/javascript" src="js/jqueryNyroModal/jquery.nyroModal.custom.min.js"></script>
	<script type="text/javascript" src="js/jqueryNyroModal/jquery.nyroModal-ie6.min.js"></script>

	<script type="text/javascript" src="js/jqueryui/jquery-ui-1.7.1.custom.min.js"></script>
	<script type="text/javascript" src="js/openid.js"></script>
	<script type="text/javascript" src="js/amadeus.js"></script>
	<script type="text/javascript" src="js/messenger.js"></script>
	<script type="text/javascript" src="js/twittertool.js"></script>
	<script type="text/javascript">			
	function getData()
	{
		var stringData = document.getElementById("gridData").value;
		document["GameGrid"].sendData(stringData);
	};

	function ajaxLoadingConfigEditGame(div, innerHTML) {
		document.getElementById(div).innerHTML = innerHTML;
	  	document.getElementById(div).style.textAlign = "center";
	  	document.getElementById(div).style.color = "#2f7445"; 
	  	document.getElementById(div).style.fontWeight = "bolder";
	}
	
	function formSubmitEditGame(){
		var courseId = document.getElementById("inputCourse").value;
		ajaxLoadingConfigEditGame('actions','<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.save" />');

		//reload forcado pq o ajax reverso não está funcionado com fileform
		setTimeout(function() {window.location.reload();},3000);
	  	document.gameActivity.submit();	  	
	}
	</script>
	<script type="text/javascript" src="js/Log.js"></script>