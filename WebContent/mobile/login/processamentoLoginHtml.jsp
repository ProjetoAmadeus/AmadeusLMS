<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile"%><html xmlns="http://www.w3.org/1999/xhtml"> 

<head> 
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8"/> 
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<title>amadeus.mobile</title>
</head>
<body>
	<%
		FacadeMobile facade = FacadeMobile.getInstance();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String cookie = request.getParameter("cookie");
		
		if (login != null && senha != null && login.length()>0 && senha.length()>0) {
		
		  UserMobile user = null;
		  try {
		     user = facade.verifyLogin(login, senha);
		  } 
		  catch (Exception e){}
		
		  if (user != null){
			PersonMobile person = facade.getLogin(login);
			if (person == null) {
			  facade.insertLogin(login);
			}
			if (cookie != null && cookie.equals("on")){
			  Cookie log = new Cookie("login", login);
			  Cookie pass = new Cookie("senha", senha);
			  int semana = 60 * 60 * 24 * 7;
			  log.setMaxAge(semana);
			  pass.setMaxAge(semana);
			  response.addCookie(log);
		      response.addCookie(pass);
			}
			response.sendRedirect(request.getContextPath()+"/mobile/home/homeHtml.jsp?login="+login);
			
		  } else {
		
			response.sendRedirect(request.getContextPath()+"/mobile/login/loginHtml.jsp?erro=t");  
		  }
		
		} else {
		  
		  response.sendRedirect(request.getContextPath()+"/mobile/login/loginHtml.jsp?erro=t");	
		}
	%>
</body>
</html>