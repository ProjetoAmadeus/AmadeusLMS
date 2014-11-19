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
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>


<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String idCourse = request.getParameter("idCourse");
	String login = request.getParameter("login");
	CourseMobile c = facMobile.getCourse(Integer.parseInt(idCourse),
			login);
	String professores = "";
	String monitores = "";
	String palavrasChaves = "";
	HashSet<KeywordMobile> keywords = c.getKeywords();

	int contador = 0;

	for (int i = 0; i < c.getTeachers().size(); i++) {
		if (i == 0) {
			professores = c.getTeachers().get(i);
		} else {
			professores = professores + " , " + c.getTeachers().get(i);
		}
	}
	if (c.getHelpers() != null) {
		for (int j = 0; j < c.getHelpers().size(); j++) {
			if (j == 0) {
				monitores = c.getHelpers().get(j);
			} else {
				monitores = monitores + " , " + c.getHelpers().get(j);
			}
		}
	}
	if (keywords != null) {
		Iterator<KeywordMobile> ite = keywords.iterator();
		while (ite.hasNext()) {
			if (contador == 0) {
				palavrasChaves = ite.next().getName();
				contador++;
			} else {
				palavrasChaves = palavrasChaves + " , "
						+ ite.next().getName();
			}
		}
	}
%>
<body>
<table style="width: 100%;">
	<tr>
		<td colspan="2" align="center"
			style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%></strong></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Descri��o: </b></td>
		<td><%=c.getName()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Professor(es): </b></td>
		<td><%=professores%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Monitor(es): </b></td>
		<td><%=monitores%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Objetivos: </b></td>
		<td><%=c.getObjectives()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Programa: </b></td>
		<td><%=c.getContent()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>M�ximo de Alunos: </b></td>
		<td><%=c.getMaxAmountStudents()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>In�cio do Curso: </b></td>
		<td><%=c.getDataInicio()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Fim do Curso: </b></td>
		<td><%=c.getDataFim()%></td>
	</tr>
	<tr>
		<td style="font-size: large;"><b>Palavras-chave: </b></td>
		<td><%=palavrasChaves%></td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?login=<%=login%>&idCourse=<%=idCourse%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</table>
</body>
</html>