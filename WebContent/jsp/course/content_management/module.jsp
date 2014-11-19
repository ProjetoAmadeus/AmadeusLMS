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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:errors/>
<table class="modAllTitle">
	<tr>
		<td class="modTitleName">
			<html:hidden name="module" property="id" styleId="idModule${module.position}"/>
			<bean:write name="module" property="name" filter="false" />
		</td>
		<td class="modTitleVisible">
			<logic:equal name="module" property="visible" value="true">
				<bean:message key="module.visible" />
			</logic:equal>
			<logic:equal name="module" property="visible" value="false">
				<span class="alert"> <bean:message key="module.invisible" /> </span>
			</logic:equal>
		</td>
		<td class="modTitlePosition">
			${module.position}
		</td>
	</tr>
</table>
<div class="modLine">
	<b><bean:message key="module.description" />:</b>
</div>
<div class="modDescription">
	<PRE class="preMod"><bean:write name="module" property="description" filter="false" /></PRE>
</div>
<div class="modLine">
	<table width="100%">
	<tr>
		<td class="headTab2" width="50%"><bean:message key="module.material" /></td>
		<td class="headTab2"><bean:message key="module.activities" /></td>
	</tr>
	<tr valign="top">
		<td width="50%">
		 	<ul class="listActivitiesMaterials"
		 	<c:if test="${ (user.typeProfile eq 'ADMIN') || (userRoleInCourse eq 'TEACHER') || (userRoleInCourse eq 'ASSISTANT') }">
		 		id="materialsList${module.position}"
		 	</c:if> >
				<c:forEach var="material" items="${module.materials}">
					<c:if test="${material.requestedMaterial == null}">
					<li class="<c:import url='/module.do?method=getDefineCSSClass&extension=${material.extension}' />">
						<html:link action="/showMaterial.do?method=showSWF&idActivity=${material.id}&idModule=${module.id}" target="_blank"><bean:write name="material" property="archiveName" filter="false" /></html:link>
					</li>
					</c:if>
				</c:forEach>
				<c:forEach var="externalLink" items="${module.externalLinks}">
					<li class="externalLink">						 
						<a href="javascript:void(0)" onclick="showActivity(${module.position}, 'externalLink', ${externalLink.id})">
							<bean:write name="externalLink" property="name" filter="false"/>
						</a>
					</li>
				</c:forEach>
			</ul>
		</td>
		<td width="50%">
		<ul class="listActivitiesMaterials" 
		<c:if test="${ (user.typeProfile eq 'ADMIN') || (userRoleInCourse eq 'TEACHER') || (userRoleInCourse eq 'ASSISTANT') }">
		 	id="activitiesList${module.position}"
		</c:if> >
			<c:forEach var="forum" items="${module.forums}">
				<li class="forum">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'forum',${forum.id})"><bean:write name="forum" property="name" filter="false" /></a>
				</li>
			</c:forEach>
			<c:forEach var="poll" items="${module.polls}">
				<li class="pollItem">
				<c:set var="answered" value="false" />
				<c:forEach var="answer" items="${poll.answers}">
					<c:if test="${answer.person.id eq user.person.id}">
						<c:set var="answered" value="true" />	
					</c:if>
				</c:forEach>
				<c:if test="${answered}">
					<bean:write name="poll" property="name" filter="false" />
				</c:if>
				<c:if test="${!answered}">
					<span id="pollNamePlace${poll.id}">
						<a href="javascript:void(0)" onclick="showActivity(${module.position},'poll',${poll.id})">
							<span id="pollName${poll.id}"><bean:write name="poll" property="name" filter="false" /></span>
						</a>
					</span>
				</c:if>
				<br />
				<a href="javascript:void(0)" class="pollResults" onclick="viewResultsPoll(${poll.id},${module.position})">
					<bean:message key="activities.poll.viewResults" />
				</a>
				</li>
			</c:forEach>
			<c:forEach var="video" items="${module.videos}">
				<li class="videoItem">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'videoIriz',${video.id})">
						<bean:write name="video" property="name" filter="false" />
					</a>
				</li>
			</c:forEach>
			<c:forEach var="materialRequest" items="${module.materialRequests}">
				<c:if test="${!( (user.typeProfile eq 'ADMIN') || (userRoleInCourse eq 'TEACHER') || (userRoleInCourse eq 'ASSISTANT') ) }">  
				<li class="delivery">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'materialRequest',${materialRequest.id})">
						<bean:write name="materialRequest" property="name" filter="false" />
					</a>
				</li>
				</c:if>
				<c:if test="${ (user.typeProfile eq 'ADMIN') || (userRoleInCourse eq 'TEACHER') || (userRoleInCourse eq 'ASSISTANT') }"> 
				<li class="delivery">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'materialRequestTeacher',${materialRequest.id})">
						<bean:write name="materialRequest" property="name" filter="false" />
					</a>
				</li>
				</c:if>
			</c:forEach>
			<c:forEach var="game" items="${module.games}">
				<li class="gameItem">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'game',${game.id})">
						<bean:write name="game" property="name" filter="false" />
					</a>
				</li>
			</c:forEach>
			<c:forEach var="learningObject" items="${module.learningObjects}">
				<li class="learningObjectItem">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'learningObject',${learningObject.id})">
						<bean:write name="learningObject" property="name" filter="false" />
					</a>
				</li>
			</c:forEach>
			<c:forEach var="evaluation" items="${module.evaluations}">
				<li class="evaluationItem">
					<a href="javascript:void(0)" onclick="showActivity(${module.position},'evaluation',${evaluation.id})">
						<bean:write name="evaluation" property="description" filter="false" />
					</a>
				</li>
			</c:forEach>
		</ul>
		</td>
	</tr>
	</table>
</div>
<div class="modLine">
	<!-- Este link deve ser obrigatoriamente igual da div abaixo para a ultilização do ajax na função showEditName do javascript managementModule.js -->
	<div id="editLink${module.position}" style="display: none;">
		<c:if test="${!(userRoleInCourse eq 'STUDENT')}">
		<div class="lineRight">
			<a href="javascript:void(0)" onclick="editModule(${module.id},${module.position});"><bean:message key="general.edit" /></a>
		</div>
		</c:if>
	</div>
	<div id="editOption${module.position}">
	<c:if test="${!(userRoleInCourse eq 'STUDENT')}">
		<div class="lineRight">
			<a href="javascript:void(0)" onclick="editModule(${module.id},${module.position});"><bean:message key="general.edit" /></a>
		</div>
	</c:if>
	</div>
</div>