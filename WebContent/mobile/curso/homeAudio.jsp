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
<%@page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material"%>
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
	ArrayList<Material> materialList = new ArrayList<Material>();
	System.out.println("LALA1");
	String extension;
	if(c!=null){
		System.out.println("LALA2");
		for(int i=0; i<c.getModules().size(); i++){
			System.out.println("LALA3");
			for(int j=0; j<c.getModules().get(i).getMaterials().size();j++){
				if(c.getModules().get(i).isVisible()){
					extension = c.getModules().get(i).getMaterials().get(j).getExtension();
					if(extension.contains(".mp3")){
						materialList.add(c.getModules().get(i).getMaterials().get(j));
						System.out.println("LALA4");
					}					
				}
				
			}
		}	
	}
	
%>
<table style="width: 100%;">
	<tr>
		<td align="center" style="background-color: #6db664">Audios</td>
	</tr>
	<% if(materialList.size()==0){ %>
	<tr>
		<td align="center" style="font-size: 10px; font-style: italic;" >
			N�o h� audio neste curso.
		</td>
	</tr>
	<% } %>
	<%for(int i=0; i<materialList.size(); i++ ){%>
	<tr>
		<td align="center" >	
			<a href="<%=request.getContextPath()%>/mobile/curso/processaDownloadAudio.jsp?idCourse=<%=id%>&login=<%=login%>&audioId=<%=materialList.get(i).getId()%>">
				<%=materialList.get(i).getArchiveName() %>	
			</a>
		</td>
	</tr>	
	<%} %>
</table>

<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?idCourse=<%=id%>&login=<%=login%>" >Voltar</a>
</body>
</html>