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

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile"%><html
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
	String idCourse = request.getParameter("idCourse");
	String id = request.getParameter("idModule");
	String login = request.getParameter("login");
	List<NoticeMobile> notices = null;
	int noticias = 0;
	int atividades = 0;
	int materiais = 0;
	int qtdade = 0;
	CourseMobile c = facMobile.getCourse(Integer.parseInt(idCourse),
			login);
	ModuleMobile m = facMobile.getModule(Integer.parseInt(id));
	if (m.getNoticies() != null) {
		notices = m.getNoticies();
		noticias = m.getNoticies().size();
		for (NoticeMobile n : notices) {
			if (!n.isRead()) {
				qtdade++;
			}
		}
	} else {
		notices = facMobile.getNoticesModule(Integer.parseInt(id),
				login);
		if (notices != null) {
			noticias = notices.size();
			for (NoticeMobile n : notices) {
				if (!n.isRead()) {
					qtdade++;
				}
			}
		}
	}
	if (m.getHomeworks() != null) {
		atividades = m.getHomeworks().size();
	}
	if (m.getMaterials() != null) {
		materiais = m.getMaterials().size();
	}
%>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=m.getNome()%>
		</strong></td>
	</tr>
	<tr>
		<td align="center">
		<%
			if (noticias > 0) {
		%> <a
			href="<%=request.getContextPath()%>/mobile/noticias/noticiasModulo.jsp?idModule=<%=id%>&login=<%=login%>&idCourse=<%=idCourse%>&pagina=1">
		Notícias (<%=qtdade%>) <%
 	} else {
 %> Notícias (Nenhuma notícia) <%
 	}
 %> <%
 	if (noticias > 0) {
 %>
		</a> <%
 	}
 %>
		</td>
	</tr>
	<tr>
		<td align="center">
		<%
			if (atividades > 0) {
		%> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesModulo.jsp?idModule=<%=id%>&login=<%=login%>&idCourse=<%=idCourse%>&pagina=1">
		Atividades (<%=atividades%>) <%
 	} else {
 %> Atividades (Nenhuma atividade)
		<%
 	}
 %> <%
 	if (atividades > 0) {
 %> </a> <%
 	}
 %>
		</td>
	</tr>

	<tr>
		<td align="center">
		<%
			if (materiais > 0) {
		%> <a
			href="<%=request.getContextPath()%>/mobile/materiais/homeMateriaisModulo.jsp?idModule=<%=id%>&login=<%=login%>&idCourse=<%=idCourse%>&pagina=1">
		Materiais (<%=materiais%>) <%
 	} else {
 %> Materiais (Nenhuma material) <%
 	}
 %>
		<%
			if (materiais > 0) {
		%> </a> <%
 	}
 %>
		</td>
	</tr>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeModulos.jsp?login=<%=login%>&idCourse=<%=idCourse%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
			
	</tr>
</table>
</body>
</html>