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
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<%
	String login = request.getParameter("login");
	int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
	FacadeMobile facMobile = FacadeMobile.getInstance();
	List<NoticeMobile> noticias = facMobile.getNoticesAmadeus(login);
	int tamanhoPaginacao = facMobile.pagesQuantity(noticias);
	int inicioFor = (paginaAtual - 1) * 5;
	int fimFor = -1;
	if (paginaAtual == tamanhoPaginacao) {
		fimFor = noticias.size();
	} else {
		fimFor = (paginaAtual) * 5;
	}
%>
<table style="width: 100%;">
	<tr>
		<td colspan="2" align="center" style="background-color: #cccccc;">
			<strong>AMADeUs</strong>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="border-bottom: 1px solid #cccccc;">
			<strong>>>Notícias</strong>
		</td>
	</tr>
	<%
		NoticeMobile n = null;
		String tipo = "AMADeUs";
		for (int i = inicioFor; i < fimFor; i++) {
			n = noticias.get(i);
	%>

	<tr style="cursor: pointer;">
		<td valign="top" align="center" style="border-bottom: 1px solid #cccccc;">
			<a href="<%=request.getContextPath()%>/mobile/noticias/visualizacaoNoticiaAmadeus.jsp?login=<%=login%>&idNotice=<%=n.getId()%>&pagina=<%=paginaAtual%>"
				style="display: block; width: 100%;"> <%
 	if (n.isRead()) {
 %> 	<img style="border: none;" alt="lida" src="<%=request.getContextPath()%>/mobile/imgs/ico_mensagemlida.gif" />
		<%
			} else {
		%> <img style="border: none;" alt="nlida" src="<%=request.getContextPath()%>/mobile/imgs/ico_mensagemnlida.gif" />
		<%
			}
		%> </a></td>
		<td align="left" style="border-bottom: 1px solid #cccccc;">
			<a href="<%=request.getContextPath()%>/mobile/noticias/visualizacaoNoticiaAmadeus.jsp?login=<%=login%>&idNotice=<%=n.getId()%>&pagina=<%=paginaAtual%>"
				style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
		<%
			if (n.isRead()) {
		%> [<%=tipo%>] <%=n.getTitle()%> <%
 	} else {
 %> <strong>[<%=tipo%>]</strong>
		<strong><%=n.getTitle()%></strong> <%
 	}
 %> </a></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<%
			}
			if (tamanhoPaginacao > 1) {
				if (paginaAtual > 1) {
		%> 			<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasAmadeus.jsp?login=<%=login%>&pagina=<%=paginaAtual - 1%>">
		<< </a> &nbsp;&nbsp; <%
 	}

 		for (int l = 0; l < tamanhoPaginacao; l++) {
 			if ((l + 1) != paginaAtual) {
 %> 			<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasAmadeus.jsp?login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>
		&nbsp;&nbsp; <%
 	} else {
 %> <span><%=l + 1%></span> &nbsp;&nbsp; <%
 	}
 		}
 		if (paginaAtual < tamanhoPaginacao) {
 %> 		<a href="<%=request.getContextPath()%>/mobile/noticias/noticiasAmadeus.jsp?login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
		>> </a> <%
 	}
 	}
 %>
		</td>
	</tr>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/mobile/home/homeHtml.jsp?login=<%=login%>">Voltar</a>
		</td>
	</tr>

</table>
</body>
</html>
