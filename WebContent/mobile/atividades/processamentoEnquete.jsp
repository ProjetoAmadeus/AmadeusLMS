<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Date"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile"%>
<%@page import="java.io.PrintWriter"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String idEnquete = request.getParameter("idEnquete");
	String idChoice = request.getParameter("grupo");
	String idCourse = request.getParameter("idCourse");
	String login = request.getParameter("login");
	String tipo = request.getParameter("tipo");
	String id = request.getParameter("idCourse");
	String modulo = request.getParameter("idModule");
	String pagina = request.getParameter("pagina");
	String idNotice = request.getParameter("idNotice");
	
	String retorno = "";
	if (tipo.equalsIgnoreCase("curso")) {
		retorno = "&pagina=" + pagina + "&tipo=" + tipo;
	} else if (tipo.equalsIgnoreCase("todos")) {
		retorno = "&pagina=" + pagina + "&tipo=" + tipo;
	} else if (tipo.equalsIgnoreCase("modulo")) {
		retorno = "&pagina=" + pagina + "&idModule=" + modulo
				+ "&tipo=" + tipo;
	} else if (tipo.equalsIgnoreCase("noticiaCurso")) {
		retorno = "&pagina=" + pagina + "&idNotice=" + idNotice
				+ "&tipo=" + tipo;
	} else if (tipo.equalsIgnoreCase("noticiaModulo")) {
		retorno = "&pagina=" + pagina + "&idNotice=" + idNotice
				+ "&tipo=" + tipo + "&idModule=" + modulo;
	} else if (tipo.equalsIgnoreCase("noticiaTodos")) {
		retorno = "&pagina=" + pagina + "&idNotice=" + idNotice
				+ "&tipo=" + tipo;
	}

	FacadeMobile facMobile = FacadeMobile.getInstance();
	PollMobile pollMM = facMobile.getPoll(Integer.parseInt(idEnquete));
	ChoiceMobile oneChoice = null;
	//ChoiceMobile c1 = null;
	List<ChoiceMobile> choicesMM = pollMM.getChoices();
	
	int idUserChoice = Integer.parseInt(idChoice);
	int totalVotes = 0;
	double percentage = -1;

	for (int i = 0; i < choicesMM.size(); i++) {
		oneChoice = choicesMM.get(i);
		if (oneChoice.getId() == idUserChoice) {
			oneChoice.setVotes(oneChoice.getVotes() + 1);
		}
		totalVotes += oneChoice.getVotes();
	}

	for (int i = 0; i < choicesMM.size(); i++) {
		percentage = 0;
		oneChoice = choicesMM.get(i);
		if (oneChoice.getVotes() > 0) {
			percentage = ((double) oneChoice.getVotes() / totalVotes) * 100;
		}
		oneChoice.setPercentage(percentage);
	}
	
	facMobile.updatePoll(login, Integer.parseInt(idEnquete), pollMM.getChoices());

	response.sendRedirect(request.getContextPath()
			+ "/mobile/atividades/resultadoEnquete.jsp?idPoll="
			+ idEnquete + "&idCourse=" + idCourse + "&login=" + login
			+ retorno);
%>
</body>
</html>