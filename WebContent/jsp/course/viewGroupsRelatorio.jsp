<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="themes/default/css/css.css" rel="stylesheet" type="text/css" />
	<link href="themes/default/css/groups.css" rel="stylesheet" type="text/css"></link>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Relatório de Atividades</title>
</head>
<body>
	<h2>Relatório de Atividades</h2>
	<table border="0" cellpadding="5" cellspacing="0" class="visualizarGrupo">
		<tr>
			<th>Nome do Grupo</th>
			<c:forEach var="modulo" items="${modulos}" varStatus="status">
				<th>${modulo}</th>
			</c:forEach>
		</tr>
		<c:forEach var="relatorio" items="${relatorios}" varStatus="status">
			<tr>
				<td>${relatorio.nome}</td>
				<c:forEach var="s" items="${relatorio.status}" varStatus="status">
					<td align="center">
						<c:if test="${s}">
							<img src = "themes/default/imgs/icons/green.png" />
						</c:if>
						<c:if test="${!s}">
							<img src = "themes/default/imgs/icons/red.png" />
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>			
	</table>
	<div class="horizontal-menu">
		<ul>
			<li><a href="#" onclick="window.print();">Imprimir</a></li>
			<li><a href="#" onclick="window.close();">Fechar</a></li>
		</ul>
	</div>
</body>
</html>