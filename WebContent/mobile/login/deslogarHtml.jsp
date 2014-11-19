<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="javax.servlet.http.Cookie"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8"/> 
 <meta http-equiv="Cache-Control" content="no-cache"/>
<title>amadeus.mobile</title>
</head>

<body>
<%
String deslogar= request.getParameter("deslogar");

if (deslogar != null && deslogar.equals("t")){
  response.addCookie(new Cookie("login", ""));
  response.addCookie(new Cookie("senha", ""));
  response.sendRedirect(request.getContextPath()+"/mobile/login/loginHtml.jsp?erro=f");
} else {
  response.sendRedirect(request.getContextPath()+"/mobile/login/loginHtml.jsp?erro=t");	
}
%>
</body>
</html>