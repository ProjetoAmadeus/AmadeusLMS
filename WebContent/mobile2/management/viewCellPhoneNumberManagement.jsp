<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
						<strong>Configurações</strong>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<strong>Cadastro de celular</strong>
					</td>
				</tr>
				<tr>
					<td>Número:</td>
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