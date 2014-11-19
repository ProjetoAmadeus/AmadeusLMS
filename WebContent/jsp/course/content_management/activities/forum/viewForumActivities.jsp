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
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<div id="viewForumActivities" class="cmBody">
<html:form action="newForumActivity" >
	<div class="cmTitle">
		<bean:message key="activities.forum" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.forum.topic" />:</label>
		<label class="labelValue"><bean:write name="forum" property="name" filter="false" /></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.forum.description" />:</label>
		<label class="labelValue"><bean:write name="forum" property="description" filter="false" /></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.forum.dateOpen" /></label>
		<label class="labelValue"><bean:write name="forum" property="creationDate" format="EEEE, d MMMM yyyy"/></label>
	</div>
	<div id="showRolagem${module.position}" class="containerContent">
		<c:import url="/forumActivity.do?method=showViewListMessagesForumActivity&idForum=${forum.id}" />
	</div>
	<div class="cmFooter">	
		<a onclick="showViewNewAnswerForumActivity(${module.position},${forum.id});" href="javascript:void(0)"><bean:message key="activities.forum.answer" /></a>&nbsp;/&nbsp;
		<a onclick="backEditName(${module.position});" href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
</html:form>
<div id="answer${module.position}" class="cmLine"></div>
</div>