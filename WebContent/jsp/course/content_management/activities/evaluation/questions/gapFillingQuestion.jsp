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

<div id="gapFillingQuestion">
<form id="form3" name="form3" method="post">
<input type="hidden" name="method" value="newQuestionGap" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
	<div class="cmTitle">Questão - Preenchimento de Lacunas.</div>
	<html:errors />
	<div class="cmLine"><label class="labelAttribute"><bean:message key="activities.evaluations.questions.description" /></label></div>
	<div class="cmLine"><textarea name="questionDescription" id="Desc" class="cmTextArea"></textarea></div>
	<div class="cmLine" style="color:black; font-size: 9px; font-style: italic"><bean:message key="activities.evaluations.questions.exampleGapFilling" /></div>
	<div class="cmFooter">
		<a href="javascript:void(0)" onClick="saveQuestionGap(${evaluation.module.position})"><bean:message key="general.save" /></a> /
		<a href="javascript:void(0)" onClick="cancelQuestions()"><bean:message key="general.cancel" /></a>
	</div>
</form>
</div>