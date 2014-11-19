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
<%@page import="java.util.*"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%>

<html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String login = request.getParameter("login");
	List cursos = facMobile.listCourses(login);
	CourseMobile curso = null;
	List<NoticeMobile> notices = facMobile.getNoticesAmadeus(login);
	int tamanhoNoticias = 0;
	int quantidade = 0;
	if (notices != null) {
		for (NoticeMobile n : notices) {
			if (!n.isRead()) {
				quantidade++;
			}
		}
		tamanhoNoticias = notices.size();
	}
%>
<body>
	<form id="homeForm" action="" method="post">
		
		<table style="border: none; width: 100%;">
			<tr>
				<td align="center" style="background-color: #cccccc; color: #089b08;">Meus Cursos</td>
			</tr>
			<%
				for (int i = 0; i < cursos.size(); i++) {
					curso = (CourseMobile) cursos.get(i);
			%>
			<tr>
				<td align="center">
					<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?idCourse=<%=curso.getId()%>&login=<%=login%>"><%=curso.getName()%></a>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td align="center">
					<a href="<%=request.getContextPath()%>/mobile/curso/homeTodosCursos.jsp?login=<%=login%>">Todos</a>
				</td>
			</tr>
			<tr>
				<td align="center" style="background-color: #cccccc; color: #089b08;">AMADeUs</td>
			</tr>
			<tr>
				<td align="center">
					<% if (tamanhoNoticias > 0) { %>
					 		<a title="Noticias" href="<%=request.getContextPath()%>/mobile/noticias/noticiasAmadeus.jsp?login=<%=login%>&pagina=1">
							Notícias(<%=quantidade%>) 
					<% } else {	%> 
							Notícias (Nenhuma notícia) 
					<% } if (tamanhoNoticias > 0) { %>
							</a> 
					<%	} %>
				</td>
			</tr>
			<tr>
				<td align="center" style="background-color: #cccccc; color: #089b08;">Configurações</td>
			</tr>
			<tr>
				<td align="center">
					<a title="Gerenciamento" href="<%=request.getContextPath()%>/mobile/configuracoes/homeGerenciamento.jsp?login=<%=login%>">Gerenciamento</a>
				</td>
			</tr>
			<tr>
				<td align="center" style="background-color: #cccccc; color: #089b08;">
				<a title="Deslogar" href="<%=request.getContextPath()%>/mobile/login/deslogarHtml.jsp?deslogar=t">Deslogar</a></td>
			</tr>
		</table>
	</form>
</body>
</html>