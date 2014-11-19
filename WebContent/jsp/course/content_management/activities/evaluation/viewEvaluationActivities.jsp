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

<script type="text/javascript">
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

<div id="viewEvaluationActivity" class="cmBody">
	<c:if test="${!callFromPaddingTask}">
	<div class="cmTitle">
		<bean:message key="activities.evaluation" />
	</div>
	</c:if>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.evaluation.name" /></label>
		<label class="labelValue"><bean:write name="evaluation" property="description" filter="false"/></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Período da Avaliação:</label>
	</div>
	<div class="cmLine">
		<label class="labelValue"><bean:write name="evaluation" property="start" format="EEEE, d MMMM yyyy"/></label>
		&nbsp;&nbsp;até&nbsp;&nbsp;
		<label class="labelValue"><bean:write name="evaluation" property="finish" format="EEEE, d MMMM yyyy"/></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.evaluation.observation" /></label>
		<label class="labelValue">
		<c:if test="${evaluation.afterdeadlineachieved}">
			<bean:message key="activities.evaluation.allowPeriod" />
		</c:if>
		<c:if test="${!evaluation.afterdeadlineachieved}">
			<bean:message key="activities.evaluation.denyPeriod" />
		</c:if>
		</label>
	</div>
	<br />
	
	
<c:if test="${!isStudent}">
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
				 		<a id="showQuestion_divLink${status.count}" onClick="minusPlus('showQuestion_divLink${status.count}','showQuestion_div${status.count}');" class="hand"><img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/></a>		
					</div>
				</div>
				<div id="showQuestion_div${status.count}" class="expansibleBoxContentInside">
					<br />${question}<br /><br />
				</div>
			</div>
			</c:forEach>
		</c:if>
		</div>
	</div>
	<!-- Realized Evaluations -->
	<div id="realizedQuestionBox" class="expansibleBoxBody">
		<div class="expansibleBoxTitle">
			<div class="expansibleBoxTitleCollLeft">
				Corrigir questões <!-- Mudar para texto dinâmico -->
			</div>
			<div class="expansibleBoxTitleCollRight">
				<a id="realizedQuestionsLink" onClick="minusPlus('realizedQuestionsLink', 'realizedQuestions');" class="hand"><img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/></a>
			</div>
		</div>
		<div id="realizedQuestions" class="expansibleBoxContent">
			<c:forEach var="evaluationRealized" items="${evaluationsRealized}" varStatus="status"> 
			<div id="student${status.count}" class="expansibleBoxBody">
				<div class="expansibleBoxTitleInside">
					<div class="expansibleBoxTitleCollLeft">
						<c:if test="${ (fn:length(evaluationRealized.student.name)) > 40}">
							${fn:substring(evaluationRealized.student.name, 0,40)}...
						</c:if>
						<c:if test="${ (fn:length(evaluationRealized.student.name)) < 40}">
							${evaluationRealized.student.name}
						</c:if>
					</div>
					<div class="expansibleBoxTitleCollRight">
						<c:import var="situationEvaluation" url="/evaluation.do?method=getStudentSituationFormEvaluation&personId=${evaluationRealized.student.id}&evaluationId=${evaluation.id}" />
						<c:if test="${situationEvaluation == 0}">
							<font color="red">Pendente</font>
						</c:if> 
						<c:if test="${situationEvaluation == 1}">
							<font color="blue">Corrigida</font>
						</c:if>
				 		&nbsp;
				 		<a id="showStudent_divLink${status.count}" onClick="minusPlus('showStudent_divLink${status.count}','showStudent_div${status.count}')" class="hand"><img src="<%=request.getContextPath()%>/themes/default/imgs/amadeus/plus.png"/></a>		
				 	</div>
				 </div>
				 <div id="showStudent_div${status.count}" class="expansibleBoxContentInside">
				 	
			 		<div class="questionDataTimeleft">
						<label class="labelAttribute">Realização:</label>
						<label class="labelValue"><bean:write name="evaluationRealized" property="realizedDate" format="dd / MM / yyyy"/></label>
					</div>
					<div class="questionDataTimeRight">
						<label class="labelAttribute">Correção:</label>
						<label class="labelValue"><bean:write name="evaluationRealized" property="correctedDate" format="dd / MM / yyyy"/></label>	
					</div>
					<div class="line">
				 		<label class="labelAttribute"><bean:message key="activities.evaluations.questions.grade"/></label><label class="labelValue">${evaluationRealized.grade}</label>
				 	</div>
				 	
				 	<form action="" id="correctEvaluationRealized${status.count}">
				 	<input type="hidden" name="method" value="correctEvaluationRealized">
				 	<input type="hidden" name="evaluationId" value="${evaluation.id}">
				 	<input type="hidden" name="idEvaluationRealized" value="${evaluationRealized.id}">
				 	<c:forEach var="questionRealized" items="${evaluationRealized.questionsRealized}" varStatus="questionsStatus">
						<div id="question" class="questionBody">
	
							<!-- Discursive Question -->
							<c:if test="${questionRealized.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionDiscursiveRealized'}">
								<div class="line">
									<label class="labelQuestion">${questionsStatus.count}°)&nbsp;<bean:write name="questionRealized" property="question.description" filter="false"/></label>
								</div>
								<div class="line"><label class="labelAnswerQuestion"><bean:write name="questionRealized" property="answer"/></label></div>
								<div class="line">
									<label class="labelQuestion"><bean:message key="activities.evaluations.questions.comment"/></label>
									<textarea name="comment${questionsStatus.count - 1}"  style="width: 98%;"><bean:write name="questionRealized" property="comment" /></textarea>
								</div>
								<div class="cmLineRight">
									<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label><input type="text" name="grade${questionsStatus.count - 1}" value="${questionRealized.grade}" style="width: 35px;">
								</div>
							</c:if>
							<!-- End Discursive Question -->
							
							<!-- Gap Question  -->
							<c:if test="${questionRealized.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionGapRealized'}">
								<div class="line">
									<label class="labelQuestion">${questionsStatus.count}°)&nbsp;<bean:write name="questionRealized" property="question.description" filter="false"/></label>
								</div>
								<c:forEach var="gapAnswers" items="${questionRealized.gapAnswers}">
								<div class="line"><label class="labelAnswerQuestion">[<bean:write name="gapAnswers"/>]</label></div>
								</c:forEach>
								<div class="cmLineRight">
									<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label><input type="text" name="grade${questionsStatus.count - 1}" value="${questionRealized.grade}" style="width: 35px;">
								</div>
							</c:if>               
							<!-- End Gap Question -->
						
							<!-- Multiple Question -->
							<c:if test="${(questionRealized.class eq 
							'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionAlternativableRealized') && 
							(questionRealized.question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple')}">
								<div class="line">
									<label class="labelQuestion">${questionsStatus.count}°)&nbsp;<bean:write name="questionRealized" property="question.description" filter="false"/></label>
								</div>
								
								<c:forEach var="alternativeRealized" items="${questionRealized.alternativesRealized}" varStatus="multipleQuestionStatus">
									<div class="questionAnswerLeft">
										<label class="labelAnswerQuestion">
										<input type="radio" class="radio" name="alternative${questionsStatus.count -1}" value="${multipleQuestionStatus.count-1}" 
										<c:if test="${alternativeRealized.answer}">checked="checked"</c:if> disabled="disabled" /> 
										<bean:write name="alternativeRealized" property="alternative.description" filter="false"/>
										</label>
									</div>
									<div class="questionAnswerRigth">
										<c:if test="${alternativeRealized.answer}">
											<c:if test="${alternativeRealized.answer == alternativeRealized.alternative.correct}">
												<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/icons/agt_15.png" />
											</c:if>
											<c:if test="${alternativeRealized.answer != alternativeRealized.alternative.correct}">
												<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/icons/button_cancel-15.png" />
											</c:if>
										</c:if>
									</div>
								</c:forEach>
								
								<div class="cmLineRight">
									<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label><input type="text" name="grade${questionsStatus.count - 1}" value="${questionRealized.grade}" style="width: 35px;">
								</div>
							</c:if>
							<!-- End Multiple Question -->
					
							<!-- True or False Question -->
							<c:if test="${(questionRealized.class eq 
							'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionAlternativableRealized') &&
							(questionRealized.question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse')}">
								<div class="line">
									<label class="labelQuestion">${questionsStatus.count}°)&nbsp;<bean:write name="questionRealized" property="question.description" filter="false"/></label>
								</div>
								<div class="line">V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F</div>	
								
								<c:forEach var="alternativeRealized" items="${questionRealized.alternativesRealized}" varStatus="trueFalseQuestionStatus">
								<div class="questionAnswerLeft">
									<label class="labelAnswerQuestion">
									<input type="radio" class="radio" name="alternative${questionsStatus.count -1}_${trueFalseQuestionStatus.count}" value="V" 
									<c:if test="${alternativeRealized.answer}">checked="checked"</c:if> disabled="disabled" />
									<input type="radio" class="radio" name="alternative${questionsStatus.count -1}_${trueFalseQuestionStatus.count}" value="F" 
									<c:if test="${!alternativeRealized.answer}">checked="checked"</c:if> disabled="disabled" />
									<bean:write name="alternativeRealized" property="alternative.description" filter="false"/>
									</label> 
								</div>
								<div class="questionAnswerRigth">
									<c:if test="${alternativeRealized.answer == alternativeRealized.alternative.correct}">
										<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/icons/agt_15.png" />
									</c:if>
									<c:if test="${alternativeRealized.answer != alternativeRealized.alternative.correct}">
										<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/icons/button_cancel-15.png" />
									</c:if>
								</div>
								</c:forEach>
								
								<div class="cmLineRight">
									<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label><input type="text" name="grade${questionsStatus.count - 1}" value="${questionRealized.grade}" style="width: 35px;">
								</div>
							</c:if>
							<!-- End True or False Question -->
						
						</div>
					</c:forEach>
					<a onclick="correctEvaluationRealized('correctEvaluationRealized${status.count}',${evaluation.module.position});" href="javascript:void(0)">Salvar Correção</a>
					</form>
				 </div>
			</div>
			</c:forEach>
		</div>
	</div>
	<!-- End Realized Evaluations -->
	<div class="cmFooter">
		<a onclick="cancelEditOption(${evaluation.module.position});" href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
</c:if>


<c:if test="${isStudent}">
	<c:if test="${(dateNow < evaluation.start)}">
		&nbsp;&nbsp;&nbsp;Aguarde o inicio da avaliação:&nbsp;<label class="labelValue"><bean:write name="evaluation" property="start" format="EEEE, d MMMM yyyy"/></label>
	</c:if>
	<c:if test="${(dateNow >= evaluation.start)}">
		<c:if test="${(dateNow > evaluation.finish) && !evaluation.afterdeadlineachieved}">
			&nbsp;&nbsp;&nbsp;<font color="red" style="font-weight: bolder;">O prazo para a realização desta availiação já encerrou!</font>
			<c:import url="/evaluation.do?method=showViewRealizeEvaluationActivity&evaluationId=${evaluation.id}"/>
		</c:if>
		<c:if test="${(dateNow <= evaluation.finish) || evaluation.afterdeadlineachieved}">
			<div class="cmLine">
			<c:import url="/evaluation.do?method=showViewRealizeEvaluationActivity&evaluationId=${evaluation.id}"/>
			</div>
		</c:if>
	</c:if>
	<c:if test="${!callFromPaddingTask}">
	<div class="cmFooter">
		<a onclick="cancelEditOption(${evaluation.module.position});" href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
	</c:if>
</c:if>	
</div>