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

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>

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
				<td colspan="2" align="center" style="background-color: #cccccc;"><strong>Configura��es</strong></td>
			</tr>
			<tr style="cursor: pointer;">
				<td colspan="2" align="left"
					style="background-color:${course.color};"><strong>${course.name}</strong></td>
			</tr>
			<tr>
				<td>Clique na cor desejada para alterar a cor padr�o deste curso:
				</td>
			</tr>
			<tr>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FFDAB9;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=FFDAB9" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #778899;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=778899" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #87cefa;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=87cefa" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #00FF00;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=00FF00" style="display: block; width: 100%; height: 100%;"> </html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FFFF00;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=FFFF00" style="display: block;width: 100%;height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #D2691E;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=D2691E" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #FF0000;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=FF0000" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
				<td align="left" style="float: left; width: 10px; height: 10px; margin-left: 5px; background-color: #8B8989;">
					<html:link action="mobile.do?method=changeCourseColor&idCourse=${course.id}&courseColor=8B8989" style="display: block; width: 100%; height: 100%;"></html:link>
				</td>
			</tr>
			<tr>
				<td align="left">
					<html:link action="mobile.do?method=showViewCourseManagement&idCourse=${course.id}" > Voltar </html:link>
					<html:link action="mobile.do?method=showLoggedView"> Home </html:link>
				</td>
			</tr>
		</table>
	</body>
</html:html>