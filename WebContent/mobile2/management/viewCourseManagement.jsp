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

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="java.util.List"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=iso-8859-1" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<title>amadeus.mobile</title>
	</head>
	
	<body>
		<table style="width: 100%;">
			<tr>
				<td colspan="2" align="center" style="background-color: #cccccc;">
					<strong>Configurações</strong>
				</td>
			</tr>
			<tr style="cursor: pointer;">
				<td colspan="2" align="left" style="background-color:${course.color};">
					<strong> ${course.name} </strong>
					<a href="#"></a>
				</td>
			</tr>
			<tr>
				<td colspan="2">Notícias por SMS:&nbsp;&nbsp; 
					<c:if test="${course.sms}">
						Sim
					</c:if>
					<c:if test="${course.sms ne true}">
						Não
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${course.sms}">
						<html:link action="mobile.do?method=showViewCourseManagement&idCourse=${course.id}&ativarSms=false"> Desativar </html:link>
					</c:if>
					<c:if test="${course.sms ne true}">
						<html:link action="mobile.do?method=showViewCourseManagement&idCourse=${course.id}&ativarSms=true"> Ativar</html:link>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width: 95px;" align="left">Cor do curso:</td>
				<td align="left" style="float:left;width:20px;height:20px;margin-left:5px;background-color:${course.color};"></td>
			</tr>
			<tr>
				<td colspan="2" style="border-bottom: 1px solid #cccccc;">
					<html:link action="mobile.do?method=showViewManagementCourseColor&idCourse=${course.id}"> Alterar </html:link>
				</td>
			</tr>
			<tr>
				<td align="left">
					<html:link action="mobile.do?method=showViewManagement"> Voltar </html:link>
					<html:link action="mobile.do?method=showLoggedView"> Home </html:link>
				</td>
			</tr>
		</table>
	</body>
</html:html>