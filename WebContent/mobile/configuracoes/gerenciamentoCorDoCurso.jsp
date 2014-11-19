<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String id = request.getParameter("idCourse");
	String cor = request.getParameter("cor");
	String login = request.getParameter("login");
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	if (cor != null) {
		cor = "#" + cor;
		c.setColor(cor);
		facMobile.updateCourse(c, login);
	}
%>
<table style="width: 100%;">
	<tr>
		<td colspan="2" align="center" style="background-color: #cccccc;"><strong>Configurações</strong></td>
	</tr>
	<tr style="cursor: pointer;">
		<td colspan="2" align="left"
			style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%></strong></td>
	</tr>
	<tr>
		<td>Clique na cor desejada para alterar a cor padrão deste curso:
		</td>
	</tr>
	<tr>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FFDAB9;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=FFDAB9"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #778899;">
			<a ref="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=778899"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #87cefa;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=87cefa"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #00FF00;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=00FF00"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FFFF00;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=FFFF00"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #D2691E;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?&idCourse=<%=id%>&login=<%=login%>&cor=D2691E"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FF0000;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=FF0000"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
		<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #8B8989;">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCorDoCurso.jsp?idCourse=<%=id%>&login=<%=login%>&cor=8B8989"
				style="display: block; width: 100%; height: 100%;"></a>
		</td>
	</tr>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/configuracoes/gerenciamentoCurso.jsp?login=<%=login%>&idCourse=<%=id%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</body>
</html>