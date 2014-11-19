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
	String id = request.getParameter("idCourse");
	String login = request.getParameter("login");
	List<NoticeMobile> notices = null;

	int noticias = 0;
	int qtdade = 0;
	int atividades = 0;
	int materiais = 0;
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	notices = facMobile.getNoticesCourse(c.getId(), login);
	if (notices != null) {
		for (NoticeMobile n : notices) {
			if (!n.isRead()) {
				qtdade++;
			}
		}
		noticias = notices.size();
	}

	ModuleMobile m = null;
	ModuleMobile m1 = null;
	if (c.getModules() != null) {
		for (int i = 0; i < c.getModules().size(); i++) {
			m1 = c.getModules().get(i);
			m = facMobile.getModule(m1.getId());
			atividades = atividades + m.getHomeworks().size();
			materiais = materiais + m.getMaterials().size();
		}
	}
%>
<table style="width: 100%;">
	<tr>
		<%
			if (login != null && !login.equals("")) {
		%>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%>
		<%
			} else {
		%>
		
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong>Meus
		Cursos <%
					}
				%> </strong></td>
	</tr>
	<tr>
		<td align="center">
		<%
			if (c.getModules() != null && c.getModules().size() > 0) {
		%> <a href="<%=request.getContextPath()%>/mobile/curso/homeForum.jsp?idCourse=<%=id%>&login=<%=login%>">
		<%
			}
		%> Módulos <%
			if (c.getModules() != null && c.getModules().size() > 0) {
		%> </a> <%
 	}
 %>
		</td>
	</tr>

	<tr>
		<td align="center">
		<%
			if (noticias > 0) {
		%> <a href="<%=request.getContextPath()%>/mobile/noticias/noticiasCurso.jsp?idCourse=<%=id%>&login=<%=login%>&pagina=1">
		Notícias (<%=qtdade%>) <%
 	} else {
 %> Notícias (Nenhuma notícia) <%
 	}
 %> <%
 	if (noticias > 0) {
 %> </a> <%
 	}
 %>
		</td>
	</tr>
	<tr>
		<td align="center">
		<%
			if (atividades > 0) {
		%> <a href="<%=request.getContextPath()%>/mobile/atividades/homeAtividades.jsp?idCourse=<%=id%>&login=<%=login%>&pagina=1">
		Atividades (<%=atividades%>) <%
 	} else {
 %> Atividades (Nenhuma atividade) <%
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
		%> <a href="<%=request.getContextPath()%>/mobile/materiais/homeMateriais.jsp?idCourse=<%=id%>&login=<%=login%>&ehCurso=t&pagina=1">
		Materiais (<%=materiais%>) <%
 	} else {
 %> Materiais (Nenhuma material) <%
 	}
 	if (materiais > 0) {
 %> </a> <%
 	}
 %>
		</td>
		</tr>
	
		<tr>
		<td align="center">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeForum.jsp?idCourse=<%=id%>&login=<%=login%>">Forum</a>
		</td>
		</tr>
		
		<tr>
		<td align="center">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeAudio.jsp?idCourse=<%=id%>&login=<%=login%>">Audio</a>
		</td>
		</tr>
		
	
	<%
		if (id != null) {
	%>
	<tr>
		<td align="center">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeInformacoes.jsp?idCourse=<%=id%>&login=<%=login%>">Informações</a></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Voltar</a>
		</td>
	</tr>
</table>
</body>
</html>