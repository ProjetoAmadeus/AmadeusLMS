	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="antlr.collections.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
	<link href="themes/default/css/css.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/amadeus.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/groups.css" rel="stylesheet" type="text/css"></link>
	<script> 
		function resize()
		{
			window.resizeTo(440,480);
		}
		function openPersonTimeline(personID)
		{
			var groupID = document.getElementById("inputHiddenIdGroup").value;
			var external = window.open("http://"+'localhost:8080/amadeuslms' + "/course.do?method=showViewPersonTimeline&groupID="+groupID+"&personID="+personID,'Grupo',
			'height=480,width=600,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}
	</script>
	<title>Grupo ${nomeGrupo}</title>
</head>
<body onload="resize();">
		<input id="inputHiddenIdGroup" type="hidden" value="${group.id}"/>
		<div>
			<table border="0" cellpadding="5" cellspacing="0" class="viewOneGroup">
				<tr>
					<th>Nome do Integrante</th>
					<th>Função</th>
					<th>Atividades</th>
				</tr>
				<c:forEach var="aluno" items="${alunos}" varStatus="status">
					<tr>
						<td><a href="#" onclick="openPersonTimeline(${aluno.id});">${aluno.name}</a></td>
						<td>
							<c:if test="${aluno.papel == 0}">Fundador/Moderador</c:if>
							<c:if test="${aluno.papel == 1}">Moderador</c:if>
							<c:if test="${aluno.papel == 2}">Membro</c:if>
						</td>
						<td align="center">
							<c:if test="${aluno.status}">
								<img src = "themes/default/imgs/icons/green.png" />
							</c:if>
							<c:if test="${!aluno.status}">
								<img src = "themes/default/imgs/icons/red.png" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>