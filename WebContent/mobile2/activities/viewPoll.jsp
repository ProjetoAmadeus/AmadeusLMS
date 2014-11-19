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

<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=iso-8859-1" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<title>amadeus.mobile</title>
	</head>
	
	<body>
		<form action="">
			<table style="width: 100%;">
				<tr>
					<td align="center" style="background-color:${course.color};">
						<strong>${course.name}</strong>
					</td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid #cccccc;">
						<strong>>>Atividades</strong>
					</td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid #cccccc;">
						<strong>${poll.name}</strong>
					</td>
				</tr>
				<tr>
					<td>${poll.question}</td>
				</tr>
				<logic:iterate id="choice" name="choices" indexId="count">
					<tr>
						<td>
							<input type="radio" name="group" id="group" value="${choice.id}" />
							<label for="${choice.id}">${choice.alternative}</label>
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td>
						<html:link action="mobile.do?method=showViewPollResults&idPoll=${poll.id}">Ver resultados</html:link>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Responder" />
					</td>
				</tr>
				<tr>
					<td style="border-top: 1px solid #cccccc;" align="left">
						<html:link action="mobile.do?method=showViewCourseActivities&idCourse=${course.id}">Voltar</html:link>
						<html:link action="mobile.do?method=showLoggedView">Home</html:link>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html:html>