<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="antlr.collections.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
	<link href="themes/default/css/css.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/amadeus.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/groups.css" rel="stylesheet" type="text/css"></link>
	
	<style type="text/css">
		table
		{
			width: 400px;
		}
		input
		{
			border: 1px solid grey;
			border-radius: 0;
			cursor: pointer;
		}
	</style>
	<script> 
		function resize()
		{
			window.resizeTo(450,500);
		}
	</script>
	<title>Atividades de ${nomePessoa} do Grupo ${nomeGrupo} - ${data}</title>
</head>
<body onload="resize();">
		<h2>Atividades de ${nomePessoa} do Grupo ${nomeGrupo} - ${data}</h2>
		<div>
			<input type="button" value="<< Voltar" onClick="history.back(-1);"/>
			<p/>
			<table border="0" cellpadding="5" cellspacing="0" class="viewOnePerson">
				<tr>
					<th>Hora</th>
					<th>Atividade</th>
				</tr>
				<c:forEach var="log" items="${logs}" varStatus="status">
					<tr>
						<td><fmt:formatDate value="${log.date}" pattern="HH:mm:ss"/></td>
						<td>
							<c:if test="${log.codigo == 1}">Login</c:if>
							<c:if test="${log.codigo == 2}">Logout</c:if>
							<c:if test="${log.codigo == 3}">Nova Postagem no Fórum</c:if>
							<c:if test="${log.codigo == 4}">Visualização do Fórum</c:if>
							<c:if test="${log.codigo == 5}">Visualização de Material - ${log.materialName}</c:if>
							<c:if test="${log.codigo == 6}">Respondeu Enquete</c:if>
							<c:if test="${log.codigo == 7}">Abriu Jogo</c:if>
							<c:if test="${log.codigo == 8}">Jogou</c:if>
							<c:if test="${log.codigo == 9}">Entregou Material</c:if>
							<c:if test="${log.codigo == 10}">Visualização de Video</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p/>
			<input type="button" value="<< Voltar" onClick="history.back(-1);"/> 
		</div>
</body>
</html>