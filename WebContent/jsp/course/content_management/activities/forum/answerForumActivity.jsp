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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewMenu"/>			
</logic:notPresent>
<div id="answerForumActivity" class="cmBody">
<html:form action="answerForumActivity">
	<html:errors/>
	<div class="cmLine">
		<c:if test="${not empty message}">
			<h3><bean:message key="activities.forum.inreplyto" /> ${message.author.name}:</h3>
			<div class="fMessageBody">	
				<PRE class="preMod"><bean:write name="message" property="body" filter="false"/></PRE><br />
			</div>
			<textarea name="answerBody" id="areatexto" class="cmTextArea" autofocus="autofocus" ><bean:write name="messageForum" property="answerBody" filter="false"/></textarea>
		</c:if>
		<c:if test="${empty message}">
			<textarea name="answerBody" id="areatexto" class="cmTextArea" autofocus="autofocus" ><bean:write name="messageForum" property="answerBody" filter="false"/></textarea>
		</c:if>
		
	</div>
	<div class="cmFooter">
	<c:if test="${empty message}">
		<a onclick="answerForumActivity(${module.position}, ${forum.id}, ${person.id});" href="javascript:void(0)"><bean:message key="general.send" /></a> / 
	</c:if>
	<c:if test="${not empty message}">
		<a onclick="answerForumActivityWithMessage(${module.position}, ${forum.id}, ${person.id}, ${message.id});" href="javascript:void(0)"><bean:message key="general.send" /></a> / 
	</c:if>
		<a onclick="cancelShowAnswerForumActivity(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
	</div>
</html:form>
</div>