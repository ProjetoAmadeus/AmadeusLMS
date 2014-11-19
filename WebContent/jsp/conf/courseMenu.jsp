<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="side_menu_1">
	<ul id="menu_sessoes">
		<c:if test="${selectedPosition == 1}"><li><b><bean:message key="viewCourse.heading"/></b></li></c:if>
		<c:if test="${selectedPosition != 1}"><li><html:link action="course.do?method=showViewCourse&courseId=${course.id}"><bean:message key="viewCourse.heading"/></html:link></li></c:if>
		
		<c:if test="${canViewCourseContent}">
			<c:if test="${selectedPosition == 2}"><li><b><bean:message key="courseForm.courseContent"/></b></li></c:if>
			<c:if test="${selectedPosition != 2}"><li><html:link action="course.do?method=showViewShowModules&idCourse=${course.id}"><bean:message key="courseForm.courseContent"/></html:link></li></c:if>
		</c:if>
		
		<c:if test="${selectedPosition == 3}"><li><b><bean:message key="sideMenu.listParticipants"/></b></li></c:if>
		<c:if test="${selectedPosition != 3}"><li><html:link action="course.do?method=showViewCourseParticipants&courseId=${course.id}"><bean:message key="sideMenu.listParticipants"/></html:link></li></c:if>
		
		<c:if test="${canViewCourseEvaluations}">
			<c:if test="${selectedPosition == 4}"><li><b>Avaliações do Curso</b></li></c:if>
			<c:if test="${selectedPosition != 4}"><li><html:link action="course.do?method=showViewCourseEvaluations&courseId=${course.id}">Avaliações do Curso</html:link></li></c:if>
		</c:if>
		
		<c:if test="${canInsertCourse}">
			<li><a href="#" onclick="changeTeacher(${course.id});"><bean:message key="sideMenu.changeTeacher"/></a></li>		
		</c:if>
		
		<c:if test="${canInsertCourse}">
			<li><a href="#" onclick="replicateCourse(${course.id});"><bean:message key="sideMenu.replicateCourse"/></a></li>		
		</c:if>
		
		<c:if test="${canInsertCourse}">
			<li><html:link forward="fInsertCourseStepOne" property="${course.id}"><bean:message key="sideMenu.createNewCourse"/></html:link></li>		
		</c:if>
		
		<c:if test="${canEditCourse}">
			<li><html:link action="course.do?method=showViewEditCourse&courseId=${course.id}"><bean:message key="sideMenu.editCourse"/></html:link></li>	
		</c:if>
		
		<c:if test="${canDeleteCourse}">
			<li><a href="#" onclick="deleteCourse(${course.id});"><bean:message key="sideMenu.deleteCourse"/></a></li>
		</c:if>
		
		<c:if test="${canAssistanceRequest}">
			<li><html:link action="user.do?method=showViewAssistanceRequest&courseId=${course.id}"><bean:message key="assistanceRequest.heading"/></html:link></li>
		</c:if>		
	</ul>
</div>