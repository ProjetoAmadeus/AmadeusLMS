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

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page
	import="br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkComparator"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile"%>
<%@page
	import="br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile"%><html
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
		<td align="center" style="background-color: #cccccc;"><strong>Meus
		Cursos </strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong>>>Atividades</strong></td>
	</tr>
	<%
		FacadeMobile facMobile = FacadeMobile.getInstance();
		String login = request.getParameter("login");
		int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
		CourseMobile c = null;
		ModuleMobile m = null;
		List<CourseMobile> courses = facMobile.listCourses(login);
		List<Object> homeworks = facMobile.getAllCoursesActivities(login);
		HomeworkMobile homework = null;
		PollMobile p = null;
		int tamanho = courses.size();
		Integer[] ids = new Integer[tamanho];
		String[] cores = new String[tamanho];
		String cor = "";
		Integer idDoCurso = -1;
		int idTempCourse = -1;
		String idRetorno = "";
		String tipo = "";
		for (int k = 0; k < courses.size(); k++) {
			c = facMobile.getCourse(courses.get(k).getId(), login);
			ids[k] = new Integer(c.getId());
			cores[k] = c.getColor();
			//for(int i = 0; i < c.getModules().size(); i++)  {
			//m = c.getModules().get(i);
			//homeworks.addAll(m.getHomeworks());
			//}
		}
		Collections.sort(homeworks, new HomeworkComparator());
		int tamanhoPaginacao = facMobile.pagesQuantity(homeworks);
		int inicioFor = (paginaAtual - 1) * 5;
		int fimFor = -1;
		if (paginaAtual == tamanhoPaginacao) {
			fimFor = homeworks.size();
		} else {
			fimFor = (paginaAtual) * 5;
		}
		for (int j = inicioFor; j < fimFor; j++) {
			tipo = "";
			tipo = "";
			homework = null;
			homework = (HomeworkMobile) homeworks.get(j);
			if (homework.getTypeActivity().equalsIgnoreCase(HomeworkMobile.POLL)) {
				tipo = "e";
			}
			if (homework.getTypeActivity().equalsIgnoreCase(HomeworkMobile.LEARNING_OBJECT)){
				tipo = "learning";
			}
			idDoCurso = new Integer(homework.getIdCourse());
			for (int x = 0; x < ids.length; x++) {
				if (idDoCurso.intValue() == ids[x].intValue()) {
					cor = cores[x];
					idTempCourse = ids[x].intValue();
					break;
				}
			}
	%>
	<tr style="cursor: pointer;">
		<td
			style="background-color: <%=cor%>; border-bottom:1px solid #cccccc;"
			" valign="top" align="left">
		<%
			if (tipo.equalsIgnoreCase("e")) {
				idRetorno = "homeEnquete.jsp?idPoll=" + homework.getId()
				+ "&tipo=Todos";
			} else if(tipo.equalsIgnoreCase("learning")){
				idRetorno = "homeLearningObject.jsp?idLearning=" + homework.getId()
				+ "&tipo=Todos";
			} else {
				idRetorno = "visualizarAtividadeTodosCursos.jsp?idAtividade="
					+ homework.getId()
					+ "&type="
					+ homework.getTypeActivity();
			}
		%> <a
			href="<%=request.getContextPath()%>/mobile/atividades/<%=idRetorno%>&login=<%=login%>&pagina=<%=paginaAtual%>&idCourse=<%=idTempCourse%>"
			style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
		<%
			if (homework != null) {
		%> <%=homework.getDeadline()%> - <%=homework.getDescription()%>
		<%
			} else {
		%> <%=p.getFinishDate()%> - <%=p.getName()%> <%
 	}
 %> </a></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<%
			}
			if (tamanhoPaginacao > 1) {
				if (paginaAtual > 1) {
		%> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesTodosCursos.jsp?login=<%=login%>&pagina=<%=paginaAtual - 1%>">
		<< </a> &nbsp;&nbsp; <%
 	}

 		for (int l = 0; l < tamanhoPaginacao; l++) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesTodosCursos.jsp?login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>

		&nbsp;&nbsp; <%
 	}

 		if (paginaAtual < tamanhoPaginacao) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesTodosCursos.jsp?login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
		>> </a> <%
 	}
 	}
 %>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeTodosCursos.jsp?login=<%=login%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>

</table>
</body>
</html>