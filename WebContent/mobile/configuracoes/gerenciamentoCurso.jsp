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
<body>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String ativar = request.getParameter("ativar");
	String id = request.getParameter("idCourse");
	String login = request.getParameter("login");
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	if (ativar != null) {
		if (ativar.equals("t")) {
			c.setSms(true);
		} else {
			c.setSms(false);
		}
		facMobile.updateCourse(c, login);
	}
%>

	<table style="width: 100%;">
		<tr>
			<td colspan="2" align="center" style="background-color: #cccccc;">
				<strong>Configura��es</strong>
			</td>
		</tr>
		<tr style="cursor: pointer;">
			<td colspan="2" align="left" style="background-color:<%=c.getColor()%>;">
				<strong> <%=c.getName()%></strong>
				<a href="#"></a>
			</td>
		</tr>
		<tr>
			<td colspan="2">Not�cias por SMS:&nbsp;&nbsp; 
			 	<%if (c.getSms()) {%>
			 		 Sim 
			 	<%} else {%>
			 		 N�o 
			 	<%}%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<%if (c.getSms()) {%>
			 		<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&ativar=f">Desativar</a>
				<%} else {%>
			 		<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&ativar=t">Ativar</a>
				<%}%>
			</td>
		</tr>
		<tr>
			<td style="width: 95px;" align="left">Cor do curso:</td>
			<td align="left" style="float:left;width:20px;height:20px;margin-left:5px;background-color:<%=c.getColor()%>;"></td>
		</tr>
		<tr>
			<td colspan="2" style="border-bottom: 1px solid #cccccc;">
				<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>">Alterar</a>
			</td>
		</tr>
		<tr>
			<td align="left">
				<a href="<%=request.getContextPath()%>/mobile/configuracoes/homeGerenciamento.jsp?login=<%=login%>">Voltar</a>
				<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>	
			</td>
		</tr>
	</table>
</body>
</html>