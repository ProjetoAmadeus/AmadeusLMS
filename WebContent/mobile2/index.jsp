<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<logic:present name="userMobile"> 
	<logic:redirect action="mobile.do?method=showLoggedView"/> 
</logic:present>

<html:html>
	<head>
		<meta http-equiv="Content-Type"
			content="application/xhtml+xml; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<title>amadeus.mobile</title>
	</head>
	
	
	<body>
		<html:form action="mobile.do?method=logon" >
			<table style="border: none; width: 100%;">
				<tr>
					<td colspan="2" align="center">
						<img src="<%=request.getContextPath()%>/mobile/imgs/amadeus_mobile.jpg"	width="120px" height="150px" alt="logo" /><br />
					</td>
				</tr>
				
				<logic:equal parameter="erro" value="t">
					<tr>
						<td colspan="2" align="center" style="color: red">
							<strong>Login e/ou senha inv�lidos</strong>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td><strong>Login*:</strong></td>
					<td><html:text property="loginMobile" /></td>
				</tr>
				<tr>
					<td><strong>Senha*:</strong></td>
					<td><html:password property="passwordMobile" /></td>
				</tr>
				<tr>
					<td colspan="2"><strong>* Campos obrigat&oacute;rios</strong></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<html:submit value="Entrar"/></td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>