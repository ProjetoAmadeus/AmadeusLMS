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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form id="form1" name="form1" method="post" action="">
<html:errors />
<input type="hidden" name="method" value="editQuestionMultiple" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
<input type="hidden" name="questionId" value="${questionMultiple.id}" />
<table class="tableinterna3">
	<tr>
		<td><b>Questão - Múltipla Escolha.</b></td>
	</tr>
	<tr>
		<td><bean:message key="activities.evaluations.questions.description" /></td>
	</tr>
	<tr>
		<td><label><textarea name="questionDescription"	cols="30" rows="4" id="Desc">${questionMultiple.description}</textarea></label></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="activities.evaluations.questions.alternatives" />:</td>
				<td>
				<div align="right"><bean:message key="activities.evaluations.questions.rightChoice" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="alternatives_div">
			<c:forEach var="alternative" items="${questionMultiple.alternatives}" varStatus="status">
				<c:if test="${status.last}"><input type="hidden" id="id" value="${status.count+1}" /></c:if>
				<div id="row${status.count}">
				${status.count}) <input type="text" size="31" name="alternativesDescription" value="${alternative.description}" /> 
				<input type="radio" class="radio" name="alternativeCorrect" value="${status.count-1}" <c:if test="${alternative.correct}">checked="checked"</c:if> />
				<br />
				<br />
				</div>
			</c:forEach>
			</div>
			<a href="javascript:void(0)" onClick="addAlternative();"><bean:message key="activities.evaluations.questions.alternatives.add" /></a><br />
			<a href="javascript:void(0)" onClick="removeAlternative();"><bean:message key="activities.evaluations.questions.alternatives.remove" /></a>
		</td>
	</tr>
	<tr>
		<td>
			<div align="right"><a href="javascript:void(0)" onClick="editQuestionMultiple(${evaluation.module.position},${questionPosition})"><bean:message key="general.save" /></a>
			/ <a href="javascript:void(0)" onClick="showViewEditEvaluation(${modulePosition},${evaluationId})"><bean:message key="general.cancel" /></a></div>
		</td>
	</tr>
</table>
</form>