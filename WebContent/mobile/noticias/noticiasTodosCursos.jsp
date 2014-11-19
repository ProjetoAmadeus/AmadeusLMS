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

<%@page import="java.lang.Integer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page
	import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeComparator"%>
<%@page import="java.util.ArrayList"%>


<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<table style="width: 100%;" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" align="center" style="background-color: #cccccc;">
		<strong>Meus Cursos</strong></td>

	</tr>
	<tr>
		<td colspan="2" style="border-bottom: 1px solid #cccccc;"><strong>>>Notícias</strong></td>
	</tr>
	<%
		String login = request.getParameter("login");
		int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
		FacadeMobile facMobile = FacadeMobile.getInstance();
		List<NoticeMobile> noticias = new ArrayList<NoticeMobile>();
		List<NoticeMobile> noticeTemp = null;
		List<CourseMobile> courses = facMobile.listCourses(login);
		CourseMobile c = null;
		NoticeMobile n = null;
		String tipo = "";
		Integer idDoCurso = -1;
		int tamanho = courses.size();
		Integer[] ids = new Integer[tamanho];
		String[] cores = new String[tamanho];
		String cor = "";
		for (int j = 0; j < courses.size(); j++) {
			c = courses.get(j);
			ids[j] = new Integer(c.getId());
			cores[j] = c.getColor();
			if (c.getNotices() != null) {
				noticias.addAll(c.getNotices());
			} else {
				noticeTemp = facMobile.getNoticesCourse(c.getId(), login);
				if (noticeTemp != null) {
					noticias.addAll(noticeTemp);
				}
			}
		}

		Collections.sort(noticias, new NoticeComparator());
		int tamanhoPaginacao = facMobile.pagesQuantity(noticias);
		int inicioFor = (paginaAtual - 1) * 5;
		int fimFor = -1;
		if (paginaAtual == tamanhoPaginacao) {
			fimFor = noticias.size();
		} else {
			fimFor = (paginaAtual) * 5;
		}

		for (int i = inicioFor; i < fimFor; i++) {
			n = noticias.get(i);
			idDoCurso = new Integer(n.getIdCourse());
			for (int k = 0; k < ids.length; k++) {
				if (idDoCurso.intValue() == ids[k].intValue()) {
					cor = cores[k];
					break;
				}
			}
			if (n.getType() == NoticeMobile.TIPO_ATIVIDADE) {
				tipo = "Atividade";
			} else if (n.getType() == NoticeMobile.TIPO_CURSO) {
				tipo = "Curso";
			} else if (n.getType() == NoticeMobile.TIPO_MATERIAL) {
				tipo = "Material";
			} else if (n.getType() == NoticeMobile.TIPO_ENQUETE) {
				tipo = "Enquete";
			}
	%>

	<tr style="cursor: pointer;">
		<td valign="top" align="center" style="background-color: <%=cor%>;">
		<a
			href="<%=request.getContextPath()%>/mobile/noticias/visualizacaoNoticiaTodosCursos.jsp?login=<%=login%>&idNotice=<%=n.getId()%>&idCourse=<%=c.getId()%>&pagina=<%=paginaAtual%>"
			style="display: block; width: 100%;"> <%
 	if (n.isRead()) {
 %> 	<img style="border: none;" alt="lida" src="<%=request.getContextPath()%>/mobile/imgs/ico_mensagemlida.gif" />
		<%
			} else {
		%> <img style="border: none;" alt="nlida" src="<%=request.getContextPath()%>/mobile/imgs/ico_mensagemnlida.gif" />
		<%
			}
		%> </a></td>
		<td align="left" style="background-color: <%=cor%>;">
		<a href="<%=request.getContextPath()%>/mobile/noticias/visualizacaoNoticiaTodosCursos.jsp?login=<%=login%>&idNotice=<%=n.getId()%>&idCourse=<%=c.getId()%>&pagina=<%=paginaAtual%>"
			style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
		<%
			if (n.isRead()) {
		%> [<%=tipo%>] <%=n.getTitle()%> <%
 	} else {
 %> <strong>[<%=tipo%>]</strong>
		<strong><%=n.getTitle()%></strong> <%
 	}
 %> </a></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<%
			}
			if (tamanhoPaginacao > 1) {
				if (paginaAtual > 1) {
		%> 			<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasTodosCursos.jsp?login=<%=login%>&pagina=<%=paginaAtual - 1%>">
					<< </a> &nbsp;&nbsp; <%
 				}

 		for (int l = 0; l < tamanhoPaginacao; l++) {
 			if (paginaAtual != (l + 1)) {
 %> 			<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasTodosCursos.jsp?login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>
		&nbsp;&nbsp; <%
 	} else {
 %> <span><%=l + 1%></span> &nbsp;&nbsp; <%
 	}
 		}

 		if (paginaAtual < tamanhoPaginacao) {
 %> 		<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasTodosCursos.jsp?login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
		>> </a> <%
 	}
 	}
 %>
		</td>
	</tr>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/mobile/curso/homeTodosCursos.jsp?login=<%=login%>">Voltar</a>
		</td>
	</tr>

</table>
</body>
</html>
