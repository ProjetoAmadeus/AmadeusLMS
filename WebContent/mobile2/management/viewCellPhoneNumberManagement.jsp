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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>amadeus.mobile</title>
	</head>
	<body>
		<html:form action="mobile.do?method=insertCellNumber">
			<table style="width: 100%;">
				<tr>
					<td colspan="2" align="center" style="background-color: #cccccc;">
						<strong>Configura��es</strong>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<strong>Cadastro de celular</strong>
					</td>
				</tr>
				<tr>
					<td>N�mero:</td>
					<td>
						<html:text property="cellNumber" name="numero" value="${phoneNumber}" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit value="Cadastrar" />
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">
						<html:link action="mobile.do?method=showViewManagement"> Voltar </html:link>
						<html:link action="mobile.do?method=showLoggedView"> Home </html:link>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>