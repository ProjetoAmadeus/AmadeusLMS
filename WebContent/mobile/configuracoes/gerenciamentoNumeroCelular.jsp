<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>amadeus.mobile</title>
</head>
<body>
	<%
		FacadeMobile facMobile = FacadeMobile.getInstance();
		String login = request.getParameter("login");
		PersonMobile p = facMobile.getLogin(login);
		String numero = p.getPhoneNumber();
		if (numero == null) {
			numero = "";
		}
	%>
	<form action="<%=request.getContextPath()%>/mobile/configuracoes/processamentoNumero.jsp">
		<input type="hidden" id="login" name="login" value="<%=login%>" />
		<table style="width: 100%;">
			<tr>
				<td colspan="2" align="center" style="background-color: #cccccc;">
					<strong>Configurações</strong>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<strong>Cadastro de celular</strong>
				</td>
			</tr>
			<tr>
				<td>Número:</td>
				<td><input id="numero" name="numero" value="<%=numero%>" />
			</tr>
			<tr>
				<td colspan="2"><input type="submit" id="logar" value="Cadastrar" />
			</tr>
			<tr>
				<td align="left" colspan="2">
					<a href="<%=request.getContextPath()%>/mobile/configuracoes/homeGerenciamento.jsp?login=<%=login%>">Voltar</a>
					<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>