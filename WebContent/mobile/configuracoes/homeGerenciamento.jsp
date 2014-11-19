<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="java.util.List"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=iso-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>amadeus.mobile</title>
</head>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String login = request.getParameter("login");
	List<CourseMobile> courses = facMobile.listCourses(login);
	CourseMobile c = null;
	CourseMobile temp = null;
%>
<body>
	<table style="width: 100%;">
		<tr>
			<td align="center" style="background-color: #cccccc;">
				<strong>Configura��es</strong>
			</td>
		</tr>
		<tr>
			<td>
				<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoNumeroCelular.jsp?login=<%=login%>">Cadastro celular</a>
			</td>
		</tr>
		<tr>
			<td>Clique no curso para editar suas configura��es:</td>
		</tr>
		<%
			for (int i = 0; i < courses.size(); i++) {
				temp = courses.get(i);
				c = facMobile.getCourse(temp.getId(), login);
		%>
		<tr style="cursor: pointer;">
			<td align="left" style="background-color:<%=c.getColor()%>;">
				<a style="color: #000; text-decoration: none; display: block; width: 100%; height: 100%;" 
					href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCurso.jsp?idCourse=<%=c.getId()%>&login=<%=login%>">
					<strong>
						<%if (c.getSms()) {%>
					 		SMS 
						<%}%>
					 	<%=c.getName()%>
					</strong>
				</a>
			</td>
		</tr>
		<%}%>
		<tr>
			<td align="left">
				<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Voltar</a>
			</td>
		</tr>
	</table>
</body>
</html>