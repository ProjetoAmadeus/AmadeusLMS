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

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile"%>
<%@page
	import="br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=iso-8859-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>amadeus.mobile</title>
</head>
<body>
<table style="width: 100%;" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" style="background-color: #cccccc;"><strong>Meus
		Cursos </strong></td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #cccccc;"><strong>>>Materiais</strong></td>
	</tr>
	<%
		FacadeMobile facMobile = FacadeMobile.getInstance();
		String login = request.getParameter("login");
		int paginaAtual = Integer.parseInt(request.getParameter("pagina"));
		CourseMobile c = null;
		ModuleMobile m = null;
		List<CourseMobile> courses = facMobile.listCourses(login);
		List<MaterialMobile> materials = new ArrayList<MaterialMobile>();
		MaterialMobile material = null;
		String cor = "";
		int tamanho = courses.size();
		Integer[] tamanhos = new Integer[tamanho];
		String[] cores = new String[tamanho];
		int qntdNoticias = -1;
		int posicao = 0;
		for (int k = 0; k < courses.size(); k++) {
			c = courses.get(k);
			cores[k] = c.getColor();
			qntdNoticias = 0;
			for (int i = 0; i < c.getModules().size(); i++) {
				m = c.getModules().get(i);
				materials.addAll(m.getMaterials());
				qntdNoticias = qntdNoticias + m.getMaterials().size();
			}
			tamanhos[k] = new Integer(qntdNoticias);
		}

		int tamanhoPaginacao = facMobile.pagesQuantity(materials);
		int inicioFor = (paginaAtual - 1) * 5;
		int fimFor = -1;
		if (paginaAtual == tamanhoPaginacao) {
			fimFor = materials.size();
		} else {
			fimFor = (paginaAtual) * 5;
		}

		int tamanhoTemp = tamanhos[posicao].intValue();
		for (int j = inicioFor; j < fimFor; j++) {
			material = materials.get(j);
			while (j >= tamanhoTemp) {
				posicao++;
				tamanhoTemp = tamanhoTemp + tamanhos[posicao].intValue();
			}
			cor = cores[posicao];
			%>
			<tr>
				<td style="background-color: <%=cor%>;" valign="top" align="left"><%=material.getName()%></td>
			</tr>
			<tr>
				<td align="center">
			<%
		}
		if (tamanhoPaginacao > 1) {
			if (paginaAtual > 1) {
		%> <a
			href="<%=request.getContextPath()%>/mobile/materiais/homeMateriaisTodosCursos.jsp?login=<%=login%>&pagina=<%=paginaAtual - 1%>">
		<< </a> &nbsp;&nbsp; <%
 	}

 		for (int l = 0; l < tamanhoPaginacao; l++) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/materiais/homeMateriaisTodosCursos.jsp?login=<%=login%>&pagina=<%=(l + 1)%>"><%=l + 1%></a>

		&nbsp;&nbsp; <%
 	}

 		if (paginaAtual < tamanhoPaginacao) {
 %> <a
			href="<%=request.getContextPath()%>/mobile/materiais/homeMateriaisTodosCursos.jsp?login=<%=login%>&pagina=<%=(paginaAtual + 1)%>">
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
			<a href="<%=request.getContextPath()%>/mobile/curso/homeTodosCursos.jsp?login=<%=login%>">Voltar</a>
		</td>
	</tr>

</table>
</body>
</html>