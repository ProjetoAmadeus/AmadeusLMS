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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html:html>

	<head>
		<meta http-equiv="Content-Type"
			content="application/xhtml+xml; charset=iso-8859-1" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<title>amadeus.mobile</title>
	</head>
	
	<body>
		<table style="width: 100%;">
			<tr>
				<td align="center" style="background-color:${course.color};"><strong>${course.name}</strong></td>
			</tr>
			
			<tr>
				<td align="center">
					<c:if test="${modules > 0}">
						<html:link action="mobile.do?method=showViewCourseModules&idCourse=${course.id}"> Modulos ( ${modules} )</html:link>
					</c:if>
					<c:if test="${modules < 1}">
						Modulos
					</c:if>
				</td>
			</tr>
		
			<tr>
				<td align="center">
					<c:if test="${notices > 0}">
						<html:link action="mobile.do?method=showViewCourseNotices&idCourse=${course.id}"> Not�cias ( ${notices} )</html:link>
					</c:if>
					<c:if test="${notices < 1}">
						Not�cias
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					<c:if test="${homeworks > 0}">
						<html:link action="mobile.do?method=showViewCourseActivities&idCourse=${course.id}"> Atividades ( ${homeworks} )</html:link>
					</c:if>
					<c:if test="${homeworks < 1}">
						Atividades
					</c:if>
				</td>
			</tr>
		
			<tr>
				<td align="center">
					<c:if test="${materials > 0}">
						<html:link action="mobile.do?method=showViewCourseMaterials&idCourse=${course.id}"> Materiais ( ${materials} )</html:link>
					</c:if>
					<c:if test="${materials < 1}">
						Materiais
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					<html:link action="mobile.do?method=showViewCourseInformation&idCourse=${course.id}"> Informa��es </html:link>
				</td>
			</tr>
		
			<tr>
				<td align="left">
					<html:link action="mobile.do?method=showLoggedView"> Voltar </html:link>
				</td>
			</tr>
			
		</table>
	</body>
</html:html>