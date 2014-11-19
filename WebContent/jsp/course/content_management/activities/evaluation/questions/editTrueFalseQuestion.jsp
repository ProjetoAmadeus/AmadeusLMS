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

<div id="editTrueFalseQuestion">
<form id="form2" name="form2" method="post" action="">
<input type="hidden" name="method" value="editQuestionTrueFalse" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
<input type="hidden" name="questionId" value="${questionTrueFalse.id}" />
	<div class="cmTitle"><bean:message key="activities.evaluations.questions.question"/> - <bean:message key="activities.evaluations.questions.trueOrFalse"/>.</div>
	<html:errors />
	<div class="cmLine"><label class="labelAttribute"><bean:message key="activities.evaluations.questions.description" /></label></div>
	<div class="cmLine"><textarea name="questionDescription" id="Desc" class="cmTextArea"><bean:write name="questionTrueFalse" property="description" filter="false" /></textarea></div>
	<div class="cmLine">
		<bean:message key="activities.evaluations.questions.alternatives" />:
		<bean:message key="activities.evaluations.questions.trueFalse" />
	</div>
	<div id="alternativesTF_div">
	<c:forEach var="alternative" items="${questionTrueFalse.alternatives}" varStatus="status">
		<div id="rowTF${status.count}">
			${status.count})&nbsp;<input type="text" size="28" name="alternativesDescription" value="<bean:write name="alternative" property="description" filter="false"/>" /> 
			<input type="radio" class="radio" name="alternativeCorrect${status.count-1}" value="true" <c:if test="${alternative.correct}">checked="checked"</c:if> />
			&nbsp;<input type="radio" class="radio" name="alternativeCorrect${status.count-1}" value="false" <c:if test="${!alternative.correct}">checked="checked"</c:if> />
			<br /><br />
		</div>
		<c:if test="${status.last}"><input type="hidden" id="idTF" value="${status.count+1}" /></c:if> 
	</c:forEach>
	</div>
	<div class="cmLine">			
		<a href="javascript:void(0)" onClick="addAlternativeTF();"><bean:message key="activities.evaluations.questions.alternatives.add" /></a>
	</div>
	<div class="cmLine">	
		<a href="javascript:void(0)" onClick="removeAlternativeTF();"><bean:message key="activities.evaluations.questions.alternatives.remove" /></a>
	</div>
	<div class="cmFooter">
		<a href="javascript:void(0)" onClick="editQuestionTrueFalse(${evaluation.module.position},${questionPosition})"><bean:message key="general.save" /></a> / 
		<a href="javascript:void(0)" onClick="showViewEditEvaluation(${modulePosition},${evaluationId})"><bean:message key="general.cancel" /></a>
	</div>
</form>
</div>