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

<%@page import="java.util.List"%>
<%@page import="java.lang.*"%>


<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<%
	String idPool = request.getParameter("idPoll");
	String id = request.getParameter("idCourse");
	String login = request.getParameter("login");
	String retorno = "";
	String tipo = request.getParameter("tipo");
	String modulo = request.getParameter("idModule");
	String pagina = request.getParameter("pagina");
	String idNoticia = request.getParameter("idNotice");
	
	if (tipo.equalsIgnoreCase("curso")) {
		retorno = "atividades/homeAtividades.jsp?login=" + login
				+ "&idCourse=" + id + "&pagina=" + pagina;
	} else if (tipo.equalsIgnoreCase("todos")) {
		retorno = "atividades/homeAtividadesTodosCursos.jsp?login="
				+ login + "&pagina=" + pagina;
	} else if (tipo.equalsIgnoreCase("modulo")) {
		retorno = "atividades/homeAtividadesModulo.jsp?login=" + login
				+ "&pagina=" + pagina + "&idCourse=" + id
				+ "&idModule=" + modulo;
	} else if (tipo.equalsIgnoreCase("noticiaCurso")) {
		retorno = "noticias/visualizacaoNoticia.jsp?login=" + login
				+ "&pagina=" + pagina + "&idCourse=" + id
				+ "&idNotice=" + idNoticia;
	} else if (tipo.equalsIgnoreCase("noticiaModulo")) {
		retorno = "noticias/visualizacaoNoticiaModulo.jsp?login="
				+ login + "&pagina=" + pagina + "&idCourse=" + id
				+ "&idNotice=" + idNoticia + "&idModule=" + modulo;
	} else if (tipo.equalsIgnoreCase("noticiaTodos")) {
		retorno = "noticias/visualizacaoNoticiaTodosCursos.jsp?login="
				+ login + "&pagina=" + pagina + "&idCourse=" + id
				+ "&idNotice=" + idNoticia;
	}

	FacadeMobile facMobile = FacadeMobile.getInstance();
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	PollMobile p = facMobile.getPoll(Integer.parseInt(idPool));
	ChoiceMobile choice = null;
	String percentage = "";
	double percante = -1;
	String subString = "";
	int posicao = -1;
%>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%></strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong>>>Atividades</strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong><%=p.getName()%></strong>
		</td>
	</tr>
	<tr>
		<td><%=p.getQuestion()%></td>
	</tr>
	<%
		for (int i = 0; i < p.getChoices().size(); i++) {
			choice = p.getChoices().get(i);
			percante = choice.getPercentage();
			percentage = "" + percante;
			posicao = percentage.indexOf(".");
			subString = percentage.substring(0, posicao + 2);
	%>
	<tr>
		<td><%=subString%>% - <%=choice.getAlternative()%></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td>
	<tr>
		<td style="border-top: 1px solid #cccccc;">
			<a href="<%=request.getContextPath()%>/mobile/<%=retorno%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</body>
</html>