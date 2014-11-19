<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>

<table id="list">
	<tr><th><bean:message key="course.viewDeleteConfirmation.title"/> ${course.name}</th></tr>
	<tr>
		<td>
			<bean:message key="course.viewDeleteConfirmation.msgConfirmation"/> ${course.name}?<br/><br/>
			<bean:message key="course.viewDeleteConfirmation.msgContent"/>
			<br/><br/>
			<bean:message key="course.viewDeleteConfirmation.msgCaution"/>
			<br/><br/>
			<html:link action="system.do?method=showViewMenu"><bean:message key="general.no"/></html:link>&nbsp;/&nbsp;<a href="<%=request.getContextPath()%>/course.do?method=deleteCourse&courseId=${course.id}" style="cursor: pointer;"><bean:message key="general.yes"/></a>
		</td>
	</tr>
</table>