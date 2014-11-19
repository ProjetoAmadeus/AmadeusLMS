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
	
	Course c = amadeusFacade.getCoursesById(Integer.parseInt(id));
	ArrayList<Forum> forumList = new ArrayList<Forum>();
	System.out.println("LALA1");
	if(c!=null){
		System.out.println("LALA2");
		for(int i=0; i<c.getModules().size(); i++){
			if(c.getModules().get(i).isVisible()){
				System.out.println("LALA3");
				for(int j=0; j<c.getModules().get(i).getForums().size();j++){
					System.out.println("LALA4");
					forumList.add(c.getModules().get(i).getForums().get(j));
				}
			}
		}	
	}
	
%>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color: #6db664">Foruns</td>
	</tr>
	<% if(forumList.size()==0){ %>
	<tr>
		<td align="center" style="font-size: 10px; font-style: italic;" >
			Não há forum neste curso.
		</td>
	</tr>
	<% } %>
	<%for(int i=0; i<forumList.size(); i++ ){%>
	<tr>
		<td align="center" >
			<a href="<%=request.getContextPath()%>/mobile/curso/viewForum.jsp?idCourse=<%=id%>&login=<%=login%>&forum=<%=forumList.get(i).getId()%>">
				<%=forumList.get(i).getDescription() %>	
			</a>
		</td>
	</tr>	
	<%} %>
</table>

<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?idCourse=<%=id%>&login=<%=login%>" >Voltar</a>
</body>
</html>