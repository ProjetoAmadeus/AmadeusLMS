<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${myEvaluationRealized eq null}">
<c:if test="${callFromPaddingTask}">
	<form id="realizedEvaluation${evaluation.id}">
	</c:if>
	<c:if test="${!callFromPaddingTask}">
	<form id="realizedEvaluation">
</c:if>
<h1>Avaliação : ${evaluation.description}</h1>
<html:errors />
<c:forEach var="question" items="${evaluation.questions}" varStatus="questionsStatus">
	<p>
	<div id="question" style="border-style: solid; border-color: #999999; border-width: 1px; padding: 4px; margin-bottom: 4px; width: 305px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
		<tr>
			<td><b>${questionsStatus.count}° Questão:</b><br /><br /></td>
		</tr>

		<!-- Multiple Question -->
		<c:if test="${question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple'}">
			<tr>
				<td>${question.description}</td>
			</tr>
			<c:forEach var="alternative" items="${question.alternatives}" varStatus="status">
				<tr>
					<td><input type="radio" class="radio" name="alternative${questionsStatus.count -1}" value="${status.count-1}" />${status.count}) ${alternative.description}</td>
				</tr>
			</c:forEach>
		</c:if>
		<!-- End Multiple Question -->

		<!-- True or False Question -->
		<c:if test="${question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse'}">
			<tr>
				<td>${question.description}</td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td width="5%">
						<div align="center">V</div>
						</td>
						<td width="5%">
						<div align="center">F</div>
						</td>
						<td width="90%">&nbsp;</td>
					</tr>
					<c:forEach var="alternative" items="${question.alternatives}" varStatus="status">
					<tr>
						<td>
							<div align="center"><input type="radio" class="radio" name="alternative${questionsStatus.count - 1}_${status.count - 1}" value="V" /></div>
						</td>
						<td>
							<div align="center"><input type="radio" class="radio" name="alternative${questionsStatus.count - 1}_${status.count - 1}" value="F" /></div>
						</td>
						<td>${status.count}) ${alternative.description}</td>
					</tr>
					</c:forEach>
				</table>
				</td>
			</tr>
		</c:if>
		<!-- End True or False Question -->
		
		<!-- Association Question -->

		<!-- End Association Question -->
		
		<!-- Gap Question  -->
		<c:if test="${question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap'}">
			<tr>
               	<td>
               		<c:set var="questionGap" value="${question}" scope="request" />
               		<c:set var="questionsStatusTemp" value="${questionsStatus.count}" scope="request" />
               		<%
               		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap questionGap = 
               			(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap) request.getAttribute("questionGap");
               		int questionsStatusTemp = (Integer) request.getAttribute("questionsStatusTemp");
               		%>
               		<%=questionGap.getDescription().replaceAll("\\[[a-zA-ZãÃõÕêÊâÂôÔóÓúÚáÁéÉíÍàÀçÇ \\s]+\\]", "<input type='text' name='alternative"+(questionsStatusTemp-1)+"' style='height: 18px; width: 50px; font-size: 11px;' />") %>     
                 </td>
            </tr>
		</c:if>               
		<!-- End Gap Question -->
		
		<!-- Discursive Question -->
		<c:if test="${question.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive'}">
			<tr>
				<td>${question.description}</td>
			</tr>
			<tr>
				<td><textarea name="alternative${questionsStatus.count - 1}" style="width: 98%;"></textarea></td>
			</tr>
		</c:if>
		<!-- End Discursive Question -->
		
	</table>
	</div>
</c:forEach> 
	<input type="hidden" name="evaluationId" value="${evaluation.id}" /> 
	<input type="hidden" name="method" value="saveRealizedEvaluation" />
	<input type="hidden" name="callFromPaddingTask" value="${callFromPaddingTask}" />
	<br />
	<c:if test="${callFromPaddingTask}">
	<input type="button" onclick="saveRealizedEvaluation(${evaluation.id},1);" value="Terminar de realizar a atividade" style="cursor: pointer;">
	</c:if>
	<c:if test="${!callFromPaddingTask}">
	<input type="button" onclick="saveRealizedEvaluation(${evaluation.module.position},0);" value="Terminar de realizar a atividade" style="cursor: pointer;">
	</c:if>
</form>
</c:if>
<c:if test="${myEvaluationRealized != null}">
	<c:if test="${myEvaluationRealized.correctedDate eq null}">
		<div id="msgMatInfo" class="msgBoxInformation">
		<ul class="listActivitiesMaterials">
			<li>
				Aguarde a correção do professor.
			</li>
		</ul>
		</div>
	</c:if>
	<c:if test="${myEvaluationRealized.correctedDate != null}">	 
	<label class="labelAttribute">AVALIAÇÃO CORRIGIDA.</label>
	<div class="cmHrDiv"></div>
		<div class="questionDataTimeleft">
		<label class="labelAttribute">Realização:</label>
		<label class="labelValue"><bean:write name="myEvaluationRealized" property="realizedDate" format="dd / MM / yyyy"/></label>
	</div>
	<div class="questionDataTimeRight">
		<label class="labelAttribute">Correção:</label>
		<label class="labelValue"><bean:write name="myEvaluationRealized" property="correctedDate" format="dd / MM / yyyy"/></label>	
	</div>
	<div class="line">
 		<label class="labelAttribute"><bean:message key="activities.evaluations.questions.grade"/></label><label class="labelValue">${myEvaluationRealized.grade}</label>
 	</div>
 	
 	<c:forEach var="questionRealized" items="${myEvaluationRealized.questionsRealized}" varStatus="questionsStatus">
		<div id="question" class="questionBody">

			<!-- Discursive Question -->
			<c:if test="${questionRealized.class eq 'class br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionDiscursiveRealized'}">
				<div class="line">
					<label class="labelQuestion">${questionsStatus.count}°)&nbsp;<bean:write name="questionRealized" property="question.description" filter="false"/></label>
				</div>
				<div class="line"><label class="labelAnswerQuestion"><bean:write name="questionRealized" property="answer"/></label></div>
				<div class="line">
					<label class="labelQuestion"><bean:message key="activities.evaluations.questions.comment"/></label>
					${questionRealized.comment}
				</div>
				<div class="cmLineRight">
					<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label>${questionRealized.grade}
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
					<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label>${questionRealized.grade}
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
					<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label>${questionRealized.grade}
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
					<label class="labelQuestion"><bean:message key="activities.evaluations.questions.grade"/></label>${questionRealized.grade}
				</div>
			</c:if>
			<!-- End True or False Question -->
		 
		</div>
	</c:forEach>
	</c:if>	
</c:if>