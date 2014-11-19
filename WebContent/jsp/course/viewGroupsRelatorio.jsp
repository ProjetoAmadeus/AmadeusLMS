<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
	 	input.button_add {
		    background-image: url(<%=request.getContextPath()%>/themes/default/imgs/icons/button_cancel-15.png); /* 16px x 16px */
		    background-color: transparent;
		    background-repeat: no-repeat;
		    border: none;
		    cursor: pointer;        /* make the cursor like hovering over an <a> element */
		    height: 16px;
		    padding-left: 16px;     /* make text start to the right of the image */
		    vertical-align: middle; /* align the text vertically centered */
		}	 	
		table.visualizarGrupo
		{
			width: 450px;
		}
		table.criarGrupos
		{
			border-collapse:collapse;
			border:1px solid grey;
		}
		th.criarGrupos
		{
			padding-bottom: 5px;
		}
		tr.criarGrupos, td.criarGrupos
		{
			border:none;
			padding-left: 10px;
			padding-right: 10px;
		}
		td.disable
		{
			border:none;
			padding-left: 10px;
			padding-right: 10px;
			color:grey;	
		}
		input.groups
		{
			border: none;
		}
		input.groupsName
		{
			width: 129px;
		}
		input.convidarRetirar
		{
			width: 70px;
			text-align: center;
		}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Relatório de Atividades</title>
</head>
<body>
	<h2>Relatório de Atividades</h2>
	<table border="1" cellpadding="5" cellspacing="0" class="visualizarGrupo">
		<tr bgcolor="#AAAAAA">
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
							<img src = "/amadeuslms/themes/default/imgs/icons/green.png" />
						</c:if>
						<c:if test="${!s}">
							<img src = "/amadeuslms/themes/default/imgs/icons/red.png" />
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>			
	</table>
	<a href="#" onclick="window.print();">Imprimir</a>
	<a href="#" onclick="window.close();">Fechar</a>
</body>
</html>