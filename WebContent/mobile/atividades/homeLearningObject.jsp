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
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile"%>


<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type"
		content="application/xhtml+xml; charset=iso-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>amadeus.mobile</title>

</head>
<body>
<%
	String idLearning = request.getParameter("idLearning");
	String idCourse = request.getParameter("idCourse");
	String modulo = request.getParameter("idModule");
	String login = request.getParameter("login");
	String tipo = request.getParameter("tipo");
	String pagina = request.getParameter("pagina");

	Facade facade = Facade.getInstance();
	FacadeMobile facMobile = FacadeMobile.getInstance();
	CourseMobile c = facMobile.getCourse(Integer.parseInt(idCourse), login);
	LearningObjectMobile learningMobile = facMobile.getLearningObject(Integer.parseInt(idLearning));
	String retorno = "";
	String idNoticia = request.getParameter("idNotice");
	String nome = c.getName();
	
	if (tipo.equalsIgnoreCase("curso")) {
		retorno = "atividades/homeAtividades.jsp?login=" + login
				+ "&idCourse=" + idCourse + "&pagina=" + pagina;
		
	} else if (tipo.equalsIgnoreCase("todos")) {
		retorno = "atividades/homeAtividadesTodosCursos.jsp?login="
				+ login + "&pagina=" + pagina;
		
	} else if (tipo.equalsIgnoreCase("modulo")) {
		retorno = "atividades/homeAtividadesModulo.jsp?login=" + login
				+ "&pagina=" + pagina + "&idCourse=" + idCourse
				+ "&idModule=" + modulo;
		
	} else if (tipo.equalsIgnoreCase("noticiaCurso")) {
		retorno = "noticias/visualizacaoNoticia.jsp?login=" + login
				+ "&pagina=" + pagina + "&idCourse=" + idCourse
				+ "&idNotice=" + idNoticia;
		
	} else if (tipo.equalsIgnoreCase("noticiaModulo")) {
		retorno = "noticias/visualizacaoNoticiaModulo.jsp?login="
				+ login + "&pagina=" + pagina + "&idCourse=" + idCourse
				+ "&idNotice=" + idNoticia + "&idModule=" + modulo;
		
	} else if (tipo.equalsIgnoreCase("noticiaTodos")) {
		retorno = "noticias/visualizacaoNoticiaTodosCursos.jsp?login="
				+ login + "&pagina=" + pagina + "&idCourse=" + idCourse
				+ "&idNotice=" + idNoticia;
	}

	if (modulo != null && !modulo.trim().equalsIgnoreCase("")) {
		ModuleMobile m = facMobile.getModule(Integer.parseInt(modulo));
		nome = m.getNome();
	}
%>

<script language="javascript">
	function openLearningObject(url){
		window.open(url,'','directories=0, location=0, menubar=0, resizable=0, scrollbars=0, status=0');
	}
</script>

<form action="<%=request.getContextPath()%>/#">
	<table style="width: 100%;">
		<input type="hidden" id="login" name="login" value="<%=login%>" />
		<input type="hidden" id="idCourse" name="idCourse" value="<%=idCourse%>" />
		<input type="hidden" id="idLearningObject" name="idLearningObject" value="<%=idLearning%>" />
	
		<tr>
			<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=nome%></strong></td>
		</tr>
		<tr>
			<td style="border-bottom: 1px solid #cccccc;"><strong>>>Atividades</strong></td>
		</tr>
		<tr>
			<%DateFormat dateFormat = DateFormat.getInstance(); %>
			<td><strong><%=learningMobile.getName()%> - <%=  dateFormat.format(learningMobile.getDatePublication()) %></strong>
			</td>
		</tr>
		<tr>
			<td>-<%=learningMobile.getDescription()%></td>
		</tr>
		<tr>
			<td>
				<center>
					<a onclick="openLearningObject('<%=learningMobile.getUrl()%>');" href="javascript:void(0)"> >>Acessar</a> <br />
					<label>( <%= facade.getTotalAccessLearningObject(Integer.parseInt(idLearning)) %> ) acesso(s)</label> 
				</center>
			</td>
		</tr>
		
		<tr>
			<td style="border-top: 1px solid #cccccc;" align="left">
				<a href="<%=request.getContextPath()%>/mobile/<%=retorno%>"> Voltar</a>
				<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>