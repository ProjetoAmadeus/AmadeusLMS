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
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<%
	String idNoticia = request.getParameter("idNotice");
	String idPagina = request.getParameter("pagina");
	String id = request.getParameter("idCourse");
	String login = request.getParameter("login");

	FacadeMobile facMobile = FacadeMobile.getInstance();
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	NoticeMobile n = facMobile.getNotice(Integer.parseInt(idNoticia),
			login);
%>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%></strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong>>>Notícias</strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong><%=n.getTitle()%></strong>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><%=n.getContent()%>
		<%
			if (n.getType() == NoticeMobile.TIPO_ENQUETE) {
		%> 		<a href="<%=request.getContextPath()%>/mobile/atividades/homeEnquete.jsp?idPoll=<%=n.getIdElement()%>&login=<%=login%>&idCourse=<%=id%>&pagina=<%=idPagina%>&tipo=noticiaCurso&idNotice=<%=idNoticia%>">Clique
		aqui</a> e responda agora! <%
 	}
 %>
		</td>
	</tr>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasCurso.jsp?login=<%=login%>&idCourse=<%=id%>&pagina=<%=idPagina%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</body>
</html>