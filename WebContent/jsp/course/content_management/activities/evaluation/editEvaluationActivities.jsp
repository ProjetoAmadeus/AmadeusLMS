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

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<script type="text/javascript">
	function lfEditEvaluation(idModule, positionModule) {
		editEvaluation(idModule, positionModule, '<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.save" />');
	}
	$(function(){$('#startEvaluation').datepicker({changeMonth: true,changeYear: true, showAnim: 'scale'});});
	$(function(){$('#finishEvaluation').datepicker({changeMonth: true,changeYear: true, showAnim: 'scale'});});

	function minusPlus(linkId, divId){
		var valueLink = $("#"+linkId).html();
		$("#"+divId).toggle("clip");
		if (valueLink.indexOf('plus.png') != -1) { 
			$("#"+linkId).html('<img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/minus.png"/>');
		} else { 
			$("#"+linkId).html('<img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/>'); 
		};
	}
</script>

<div id="editEvaluationActivities" class="cmBody">
<html:form action="editEvaluation" >
<html:hidden name="evaluationActivity" property="idEvaluation"/>	
	<div class="cmTitle">
		<bean:message key="activities.evaluation" />
	</div>
	<html:errors/>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.evaluation.description" /></label>
	</div>
	<div class="cmLine">	
		<html:text name="evaluationActivity" property="descriptionEvaluation"/>
	</div>
	<div class="cmLine">	
		<strong><bean:message key="activities.evaluation.period" /></strong><br />
	</div>
	<div class="cmDataTime">
		<label class="labelAttribute"><bean:message key="activities.evaluation.start" /></label>
		<html:text name="evaluationActivity" property="startEvaluation" styleId="startEvaluation" style="width: 80px;"/>
	</div>
	<div class="cmDataTime">
		<label class="labelAttribute"><bean:message key="activities.evaluation.end" /></label>
		<html:text name="evaluationActivity" property="finishEvaluation" styleId="finishEvaluation" style="width: 80px;"/>
	</div>
	<div class="cmLine">
		<html:radio name="evaluationActivity" property="afterdeadlineachievedEvaluation" value="true"/>
		<bean:message key="activities.evaluation.allowPeriod" />
	</div>
	<div class="cmLine">
		<html:radio name="evaluationActivity" property="afterdeadlineachievedEvaluation" value="false"/>
		<bean:message key="activities.evaluation.denyPeriod" />
	</div>
</html:form>
	<div class="cmLine">&nbsp;</div>
	<div id="questionBox" class="expansibleBoxBody">
		<div class="expansibleBoxTitle">
			<div class="expansibleBoxTitleCollLeft">
				<bean:message key="activities.evaluations.questions" />
			</div>
			<div class="expansibleBoxTitleCollRight">
				<a id="questionsLink" onClick="minusPlus('questionsLink', 'questions');" class="hand"><img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/></a>
			</div>
		</div>
		<div id="questions" class="expansibleBoxContent">
		<c:if test="${ (fn:length(evaluation.questions)) == 0}">
			<div class="msgBoxInformation"><bean:message key="activities.evaluations.questions.none" /></div>
		</c:if>
		<c:if test="${ (fn:length(evaluation.questions)) != 0}">
			<c:forEach var="question" items="${evaluation.questions}" varStatus="status">
			<div id="question${status.count}" class="expansibleBoxBody">
				<div class="expansibleBoxTitleInside">
					<div class="expansibleBoxTitleCollLeft">
						${status.count})
						<c:if test="${ (fn:length(question.description)) > 40}">
							${fn:substring(question.description, 0,40)}...
						</c:if>
						<c:if test="${ (fn:length(question.description)) <= 40}">
							${question.description}
						</c:if>
					</div>
					<div class="expansibleBoxTitleCollRight">
					<c:if test="${canAddEditDeleteQuestions}">
				 		<a href="javascript:void(0)" onclick="showViewEditQuestion(${status.count},${evaluation.id},${question.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
				 		&nbsp;
				 		<a href="javascript:void(0)" onclick="showViewDeleteConfirmationQuestion(${evaluation.module.position},${evaluation.id},${question.id},${status.count});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
				 		&nbsp;
				 	</c:if>	
				 		<a id="showQuestion_divLink${status.count}" onClick="minusPlus('showQuestion_divLink${status.count}','showQuestion_div${status.count}');" class="hand"><img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/></a>		
					</div>
				</div>
				<div id="showQuestion_div${status.count}" class="expansibleBoxContentInside">
					<br />${question}<br /><br />
				</div>
			</div>
			</c:forEach>
		</c:if>
		<c:if test="${canAddEditDeleteQuestions}">
		<div id="questionOption" class="cmLine">
			<label class="labelAttribute"><bean:message key="activities.evaluations.questions.add" /></label>
			<input type="button" onClick="showQuestions(${evaluation.id})" style="cursor: pointer;" value="<bean:message key="activities.evaluations.questions.new" />"><!-- <button class="button" type="button"><bean:message key="activities.evaluations.questions.database" /></button> -->
		</div>
		</c:if>
		</div>
	</div>
	<div class="cmFooter">
		<div id="actions">
		<a onclick="lfEditEvaluation(${module.id}, ${module.position});" href="javascript:void(0)"><bean:message key="general.save" /></a> / 
		<a onclick="cancelShowListActivity(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
		</div>
	</div>
</div>