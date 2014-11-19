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

<div id="multipleChoiceQuestion">
<form id="form1" name="form1" method="post" action="">
<input type="hidden" name="method" value="newQuestionMultiple" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
	<div class="cmTitle">Questão - Múltipla Escolha.</div>
	<html:errors />
	<div class="cmLine"><bean:message key="activities.evaluations.questions.description" /></div>
	<div class="cmLine"><textarea name="questionDescription" id="Desc" class="cmTextArea"></textarea></div>
	<div class="cmLine">
		<span><bean:message key="activities.evaluations.questions.alternatives" />:</span>
		<span><bean:message key="activities.evaluations.questions.rightChoice" /></span>
	</div>
	<input type="hidden" id="id" value="3" />
	<div id="alternatives_div">
		<div id="row1">
			 1) <input type="text" size="31" name="alternativesDescription" /> 
				<input type="radio" class="radio" name="alternativeCorrect" value="1" checked="checked" />
			<br />
			<br />
		</div>
		<div id="row2">
			 2) <input type="text" size="31" name="alternativesDescription" /> 
				<input type="radio" class="radio" class="radio" name="alternativeCorrect" value="2" />
			<br />
			<br />
		</div>
	</div>
	<div class="cmLine">	
		<a href="javascript:void(0)" onClick="addAlternative();"><bean:message key="activities.evaluations.questions.alternatives.add" /></a>
	</div>
	<div class="cmLine">
		<a href="javascript:void(0)" onClick="removeAlternative();"><bean:message key="activities.evaluations.questions.alternatives.remove" /></a>
	</div>
	<div class="cmFooter">
		<a href="javascript:void(0)" onClick="saveQuestionMultiple(${evaluation.module.position})"><bean:message key="general.save" /></a>
			/ <a href="javascript:void(0)" onClick="cancelQuestions()"><bean:message key="general.cancel" /></a>
	</div>
</form>
</div>