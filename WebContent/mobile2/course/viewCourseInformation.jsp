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
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>

<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile"%>
<%@page import="br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile"%>

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
				<td colspan="2" align="center"
					style="background-color:${course.color};"><strong>${course.name}</strong></td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Descrição: </b></td>
				<td>${course.name}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Professor(es): </b></td>
				<td>
					<logic:iterate id="professor" name="teachers" indexId="count" type="java.lang.String">
						${professor} &nbsp;
					</logic:iterate>
				</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Monitor(es): </b></td>
				<td>
					<c:if test="${helpers != null}">
						<logic:iterate id="monitor" name="helpers" indexId="count" type="java.lang.String">
							${monitor} <br>
						</logic:iterate>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Objetivos: </b></td>
				<td>${course.objectives}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Programa: </b></td>
				<td>${course.content}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Máximo de Alunos: </b></td>
				<td>${course.maxAmountStudents}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Início do Curso: </b></td>
				<td>${course.initialCourseDate}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Fim do Curso: </b></td>
				<td>${course.finalCourseDate}</td>
			</tr>
			<tr>
				<td style="font-size: large;"><b>Palavras-chave: </b></td>
				<td>
					<c:if test="${keywords != null}">
						<logic:iterate id="keyword" name="keywords" indexId="count" type="br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile">
							${keyword.name} <br> 
						</logic:iterate>
					</c:if>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td align="left">
					<html:link action="mobile.do?method=showViewCourse&idCourse=${course.id}"> Voltar </html:link>
					<html:link action="mobile.do?method=showLoggedView"> Home </html:link>
				</td>
			</tr>
		</table>
	</body>
</html:html>