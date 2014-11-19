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
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type"
		content="application/xhtml+xml; charset=iso-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>amadeus.mobile</title>
</head>
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String curso = request.getParameter("idCourse");
	String login = request.getParameter("login");
	int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
	CourseMobile courseMM = null;
	//ModuleMobile moduleMM = null;

	List<Object> homeworksOfCourseMM = facMobile
			.getCourseActivities(Integer.parseInt(curso));
	HomeworkMobile homeworkMM = null;
	PollMobile pollMM = null;
	ModuleMobile temp = null;
	courseMM = facMobile.getCourse(Integer.parseInt(curso), login);
	String idRetorno = "";
	String tipo = "";
%>
<body>
	<table style="width: 100%;">
		<tr>
			<td align="center" style="background-color:<%=courseMM.getColor()%>;"><strong><%=courseMM.getName()%>
			</strong></td>
		</tr>
		<tr>
			<td style="border-bottom: 1px solid #cccccc;"><strong>>>Atividades</strong></td>
		</tr>
		<%
			Collections.sort(homeworksOfCourseMM, new HomeworkComparator());
			int tamanhoPaginacao = facMobile.pagesQuantity(homeworksOfCourseMM);
			int inicioFor = (paginaAtual - 1) * 5;
			int fimFor = -1;
			if (paginaAtual == tamanhoPaginacao) {
				fimFor = homeworksOfCourseMM.size();
			} else {
				fimFor = (paginaAtual) * 5;
			}
			for (int j = inicioFor; j < fimFor; j++) {
				tipo = "";
				homeworkMM = null;
				homeworkMM = (HomeworkMobile) homeworksOfCourseMM.get(j);
				if (homeworkMM.getTypeActivity().equalsIgnoreCase(HomeworkMobile.POLL)) {
					tipo = "e";
				}
				if (homeworkMM.getTypeActivity().equalsIgnoreCase(HomeworkMobile.LEARNING_OBJECT)){
					tipo = "learning";
				}
				%>
				<tr style="cursor: pointer;">
					<td style="border-bottom: 1px solid #cccccc;" valign="top" align="left">
						<%
							if (tipo.equalsIgnoreCase("e")) {
								idRetorno = "homeEnquete.jsp?idPoll=" + homeworkMM.getId()+ "&tipo=Curso";	
							} 
							else if(tipo.equalsIgnoreCase("learning")){
								idRetorno = "homeLearningObject.jsp?idLearning=" + homeworkMM.getId() + "&tipo=Curso";
							}
							else {
								idRetorno = "visualizarAtividadeCurso.jsp?idAtividade="+ homeworkMM.getId() + "&type="+ homeworkMM.getTypeActivity();
							}
						%> <a href="<%=request.getContextPath()%>/mobile/atividades/<%=idRetorno%>&idCourse=<%=curso%>&login=<%=login%>&pagina=<%=paginaAtual%>"
							style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
						<%
							if (homeworkMM != null) {
						%> 		<%=homeworkMM.getDeadline()%> - <%=homeworkMM.getDescription()%> <%
					 	} else {
					 	%> 		<%=pollMM.getFinishDate()%> - <%=pollMM.getName()%> 
					 	<% }%> 
					 		</a>
			 		</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<%
			}
				if (tamanhoPaginacao > 1) {
					if (paginaAtual > 1) {
			%> <a
				href="<%=request.getContextPath()%>/mobile/atividades/homeAtividades.jsp?idCourse=<%=curso%>&login=<%=login%>&pagina=<%=paginaAtual - 1%>">
			<< </a> &nbsp;&nbsp; <%
	 	}
	
	 		for (int l = 0; l < tamanhoPaginacao; l++) {
	 %> <a
				href="<%=request.getContextPath()%>/mobile/atividades/homeAtividades.jsp?idCourse=<%=curso%>&login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>
			&nbsp;&nbsp; <%
	 	}
	
	 		if (paginaAtual < tamanhoPaginacao) {
	 %> <a ref="<%=request.getContextPath()%>/mobile/atividades/homeAtividades.jsp?idCourse=<%=curso%>&login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
			>> </a> <%
	 	}
	 	}
	 %>
			</td>
		<tr>
			<td align="left">
				<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?idCourse=<%=courseMM.getId()%>&login=<%=login%>">Voltar</a>
				<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
			</td>				
		</tr>
	</table>
</body>
</html>