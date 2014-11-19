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
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message"%>
<%@page import="java.util.List"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course"%>

<%@page import="java.util.ArrayList"%>
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
	Facade amadeusFacade = Facade.getInstance();
	String id = request.getParameter("idCourse");
	String login = request.getParameter("login");
	String idForum = request.getParameter("forum");
	
	Forum forum = amadeusFacade.getForumById(Integer.parseInt(idForum));
	
	List<Message> messages = forum.getMessages();
%>
	
	<div style="margin: auto; text-align: center; padding-right: 50px; background-color: #6db664"> <%="Forum: "+forum.getName()%></div>   
	<br />
	<form method="post" action="<%=request.getContextPath()%>/mobile/curso/gerenciamentoForum.jsp">
	<div style="margin: auto; width: 320px;">
	<table style="width: 100%;">		
		<% for(int i = 0; i< messages.size();i++) {%>
			<tr >		
				<td align="center" style="width: 240px; background-color: #cccccc;">
					<%= messages.get(i).getBody() %>
				</td>
			</tr>
			<tr >		
				<td align="center" style="font-size: 10px; width: 240px" >
					<%= messages.get(i).getAuthor().getName() %>
				</td>
			</tr>
		<%} %>
			<tr><td style="font-size: 9px;">_____________________________________________ </td></tr>
			<tr><td style="font-size: 9px;" >responda: </td></tr>
		
						
			<tr>
				<td> <textarea cols="25" rows="3" name="message"></textarea></td>
				<td><input type="hidden" name="idCourse" value="<%=id %>"></input></td>
				<td><input type="hidden" name="idForum" value="<%=idForum %>"></input></td>
				<td><input type="hidden" name="login" value="<%=login %>" ></input></td>
			</tr>
			<tr>
				<td > <input type="submit" name="message" value="Enviar"/> </td>
			</tr>
	</table>
	</div>
	<a href="<%=request.getContextPath()%>/mobile/curso/homeForum.jsp?idCourse=<%=id%>&login=<%=login%>" >Voltar</a>
	</form>
	
</body>
</html>	

	
	
	