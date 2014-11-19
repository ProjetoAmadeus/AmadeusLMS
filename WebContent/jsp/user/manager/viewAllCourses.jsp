<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"/>
</head>

<body>
	<script type="text/javascript">
	$(function(){$('.inputCalendar').datepicker({changeMonth: true,changeYear: true, showAnim: 'scale'});});
	</script>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Gerenciar Cursos</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="user.do?method=showViewAllCoursesInManagerUsers"><bean:message key="general.managerCourse"/></html:link></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewAllCoursesInManagerUsers">Todos os cursos</html:link></li>
					<li><html:link action="/fInsertCourseStepOne.do"> Criar curso</html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<div class="expansibleBoxTitle">
				<div class="expansibleBoxTitleCollLeft">
					<label class="labelAttribute">Pesquisa Avançada...</label>
				</div>
				<div id="advancedSearch" onclick="minusPlus('#advancedSearch','#divAdvancedSearch');" class="expansibleBoxTitleCollRight imgPlus"></div>
			</div>
			<div style="padding: 5px; border-color: #eeeeee; border-style: solid; border-width: 0px 1px 1px 1px; display: none;" id="divAdvancedSearch">
				<html:form action="advancedCourse.do" method="get">
				<input type="hidden" name="method" value="advancedSearchCourse"/>
				<table border="0" width="100%">
					<tr>
						<td align="right">
							<label class="labelAttribute">Nome:</label>
						</td>
						<td>
							<html:text property="name" style="width: 220px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="labelAttribute">Professor:</label>
						</td>
						<td>
							<html:text property="professorName" style="width: 220px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="labelAttribute">Início:</label>
						</td>
						<td>
							<html:text property="initialDate" styleClass="inputCalendar" style="width: 80px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="labelAttribute">Fim:</label>
						</td>
						<td>
							<html:text property="finalDate" styleClass="inputCalendar" style="width: 80px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="Pesquisar"/>
						</td>
					</tr>
				</table>
				</html:form>
			</div>
			<display:table name="courses" id="course" pagesize="20" class="displaytag" requestURI="user.do?method=showViewAllCoursesInManagerUsers">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				<display:column title="Cursos" headerClass="sortable" sortable="true">
					<div class="coursesListItem">
						<div class="itemHead">
							<h3>
								<html:link href="course.do?method=showViewCourse&courseId=${course.id}"><bean:write name="course" property="name" /></html:link>
							</h3>
							<div class="iconBar">
								<html:link action="course.do?method=showViewEditCourse&courseId=${course.id}">
									<img border="0" src="themes/default/imgs/menu/edit-16x16.png" title="Editar Curso" />
								</html:link>
								<html:link action="course.do?method=viewReplicateCourse&courseId=${course.id}">
									<img border="0" src="themes/default/imgs/menu/replicate-16x16.png" title="Replicar Curso" />
								</html:link>
								<html:link action="course.do?method=viewDeleteConfirmation&courseId=${course.id}">
									<img border="0" src="themes/default/imgs/menu/remove-16x16.png" title="Remover Curso" />
								</html:link>
							</div>
						</div>
						<div class="itemBody">
							<div class="dates">
								<span class="labelInfo">Início:</span>
								<span><bean:write name="course" property="initialCourseDate" format="dd/MM/yyyy" /></span>
								<br />
								<span class="labelInfo">Fim:</span>
								<span><bean:write name="course" property="finalCourseDate" format="dd/MM/yyyy" /></span>
							</div>
							<div>
								<span><bean:write name="course" property="numberOfStudentsInCourse" /></span>
								<span>alunos</span>
								<br />
								<span class="professor">
									<html:link action="user.do?method=showViewUserProfileInManagerUsers&userId=${course.professor.accessInfo.id}">
										<bean:write name="course" property="professor.name" />
									</html:link>
								</span>
							</div>
						</div>
					</div>
				</display:column>
			</display:table>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>