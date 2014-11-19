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
<%
	FacadeMobile facMobile = FacadeMobile.getInstance();
	String curso = request.getParameter("idCourse");
	String modulo = request.getParameter("idModule");
	String login = request.getParameter("login");
	int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
	CourseMobile c = facMobile
			.getCourse(Integer.parseInt(curso), login);
	ModuleMobile m = null;
	List<Object> homeworks = facMobile.getModuleActivities(Integer
			.parseInt(modulo));
	HomeworkMobile homework = null;
	PollMobile p = null;
	m = facMobile.getModule(Integer.parseInt(modulo));
	String idRetorno = "";
	String tipo = "";
%>
<body>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=m.getNome()%>

		</strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong>>>Atividades</strong></td>
	</tr>
	<%
		//homeworks = m.getHomeworks();
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
			homework = null;
			homework = (HomeworkMobile) homeworks.get(j);
			if (homework.getTypeActivity().equalsIgnoreCase(HomeworkMobile.POLL)) {
				tipo = "e";
			}
			if (homework.getTypeActivity().equalsIgnoreCase(HomeworkMobile.LEARNING_OBJECT)){
				tipo = "learning";
			}
	%>
	<tr style="cursor: pointer;">
		<td valign="top" align="left"
			style="border-bottom: 1px solid #cccccc;">
		<%
			if (tipo.equalsIgnoreCase("e")) {
				idRetorno = "homeEnquete.jsp?idPoll=" + homework.getId()
				+ "&tipo=Modulo";
				
			} else if(tipo.equalsIgnoreCase("learning")){
				idRetorno = "homeLearningObject.jsp?idLearning=" + homework.getId()
				+ "&tipo=Modulo";
				
			} else {
				idRetorno = "visualizarAtividadeModulo.jsp?idAtividade="
					+ homework.getId() + "&type="
					+ homework.getTypeActivity();
			}
		%> <a
			href="<%=request.getContextPath()%>/mobile/atividades/<%=idRetorno%>&idCourse=<%=curso%>&login=<%=login%>&idModule=<%=modulo%>&pagina=<%=paginaAtual%>"
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
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesModulo.jsp?idCourse=<%=curso%>&idModule=<%=modulo%>&login=<%=login%>&pagina=<%=paginaAtual - 1%>">
		<< </a> &nbsp;&nbsp; <%
 	}

 		for (int l = 0; l < tamanhoPaginacao; l++) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesModulo.jsp?idCourse=<%=curso%>&idModule=<%=modulo%>&login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>

		&nbsp;&nbsp; <%
 	}

 		if (paginaAtual < tamanhoPaginacao) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/atividades/homeAtividadesModulo.jsp?idCourse=<%=curso%>&idModule=<%=modulo%>&login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
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
			<a href="<%=request.getContextPath()%>/mobile/curso/homeModulo.jsp?login=<%=login%>&idCourse=<%=curso%>&idModule=<%=modulo%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</table>
</body>
</html>