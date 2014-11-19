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

<div id="editDiscursiveQuestion">
<form name="form4" id="form4" method="post">
<input type="hidden" name="method" value="editQuestionDiscursive" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
<input type="hidden" name="questionId" value="${questionDiscursive.id}" />
	<div class="cmTitle"><bean:message key="activities.evaluations.questions.question"/> - <bean:message key="activities.evaluations.questions.discursive"/>.</div>
	<html:errors />
	<div class="cmLine"><label class="labelAttribute"><bean:message key="activities.evaluations.questions.description" /></label></div>
	<div class="cmLine">
		<textarea name="questionDiscursiveDescription" cols="30" rows="4" id="Desc" class="cmTextArea"><bean:write name="questionDiscursive" property="description" filter="false"/></textarea>
	</div>
	<div class="cmFooter">
		<a href="javascript:void(0)" onClick="editQuestionDiscursive(${evaluation.module.position},${questionPosition})"><bean:message key="general.save" /></a> / 
		<a href="javascript:void(0)" onClick="showViewEditEvaluation(${modulePosition},${evaluationId})"><bean:message key="general.cancel" /></a>
	</div>
</form>
</div>