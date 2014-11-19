<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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

<html
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
	String modulo = request.getParameter("idModule");
	String login = request.getParameter("login");
	String tipo = request.getParameter("tipo");
	String pagina = request.getParameter("pagina");

	FacadeMobile facMobile = FacadeMobile.getInstance();
	CourseMobile c = facMobile.getCourse(Integer.parseInt(id), login);
	PollMobile p = facMobile.getPoll(Integer.parseInt(idPool));
	ChoiceMobile choice = null;
	String retorno = "";
	String idNoticia = request.getParameter("idNotice");
	String nome = c.getName();
	
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

	if (modulo != null && !modulo.trim().equalsIgnoreCase("")) {
		ModuleMobile m = facMobile.getModule(Integer.parseInt(modulo));
		nome = m.getNome();
	}
%>

<form action="<%=request.getContextPath()%>/mobile/atividades/processamentoEnquete.jsp">
	<table style="width: 100%;">
		<input type="hidden" id="login" name="login" value="<%=login%>" />
		<input type="hidden" id="idCourse" name="idCourse" value="<%=id%>" />
		<input type="hidden" id="idEnquete" name="idEnquete" value="<%=idPool%>" />
		<input type="hidden" id="idModule" name="idModule" value="<%=modulo%>" />
		<input type="hidden" id="tipo" name="tipo" value="<%=tipo%>" />
		<input type="hidden" id="pagina" name="pagina" value="<%=pagina%>" />
		<input type="hidden" id="idNotice" name="idNotice" value="<%=idNoticia%>" />
	
		<tr>
			<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=nome%></strong></td>
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
		%>
				<tr>
					<td>
						<input type="radio" <%if (i == 0) {%> checked="checked" <%}%> name="grupo" id="grupo" value="<%=choice.getId()%>" />
						<label for="<%=choice.getId()%>"><%=choice.getAlternative()%></label>
					</td>
				</tr>
		<%} %>
		<tr>
			<td>
				<a href="<%=request.getContextPath()%>/mobile/atividades/resultadoEnquete.jsp?idPoll=<%=idPool%>&idCourse=<%=id%>&login=<%=login%>&tipo=<%=tipo%>&modulo=<%=modulo%>&pagina=<%=pagina%>&idNoticia=<%=idNoticia%>" > Ver Resultados</a>
			</td>
		</tr>
				
		<tr>
			<td><input type="submit" value="Responder" />
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