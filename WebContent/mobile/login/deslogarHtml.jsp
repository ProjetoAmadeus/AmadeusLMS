<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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