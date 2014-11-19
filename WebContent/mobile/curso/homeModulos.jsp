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
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<table style="width: 100%;">
	<tr>
		<%
			FacadeMobile facMobile = FacadeMobile.getInstance();
			String idCourse = request.getParameter("idCourse");
			String login = request.getParameter("login");
			CourseMobile c = facMobile.getCourse(Integer.parseInt(idCourse),
					login);
			List<ModuleMobile> modules = c.getModules();
			List<NoticeMobile> notices = null;
			ModuleMobile m = null;
			ModuleMobile m1 = null;
			int novidades = 0;
			int qtdade = 0;
		%>
		<td align="center" style="background-color:<%=c.getColor()%>;"><strong><%=c.getName()%></strong></td>
	</tr>
	<%
		for (int i = 0; i < modules.size(); i++) {
			qtdade = 0;
			novidades = 0;
			m1 = modules.get(i);
			m = facMobile.getModule(m1.getId());
			if (m.getNoticies() != null) {
				notices = m.getNoticies();
				for (NoticeMobile n : notices) {
					if (!n.isRead()) {
						qtdade++;
					}
				}
				novidades = novidades + qtdade;
			}
			if (m.getHomeworks() != null) {
				novidades = novidades + m.getHomeworks().size();
			}
			if (m.getMaterials() != null) {
				novidades = novidades + m.getMaterials().size();
			}
	%>

	<tr>
		<td align="left"><a
			href="<%=request.getContextPath()%>/mobile/curso/homeModulo.jsp?login=<%=login%>&idCourse=<%=idCourse%>&idModule=<%=m.getId()%>"><%=(i + 1)%>
		- <%=m.getNome()%> (<%=novidades%>)</a></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td align="left">
			<a href="<%=request.getContextPath()%>/mobile/curso/homeCurso.jsp?login=<%=login%>&idCourse=<%=idCourse%>">Voltar</a>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Home</a>
		</td>
	</tr>
</table>
</body>
</html>