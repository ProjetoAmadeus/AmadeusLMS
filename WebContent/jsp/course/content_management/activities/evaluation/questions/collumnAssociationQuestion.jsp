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
	
<div style="border-style: solid; border-color: #999999; border-width: 1px; padding: 4px; margin-bottom: 4px; width: 305px;">
<form id="form5" name="form5" method="post" action=""><html:errors />
<input type="hidden" name="method" value="newQuestionAssociation" />
<input type="hidden" name="evaluationId" value="${evaluation.id}" />
	<table>
		<tr>
			<td><b>Questão - Associação de Colunas.</b></td>
		</tr>
		<tr>
			<td><bean:message key="activities.evaluations.questions.description" /></td>
		</tr>
		<tr>
			<td><label><textarea name="questionDescription" cols="30" rows="4" id="Desc"></textarea></label></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="212"><bean:message key="activities.evaluations.questions.alternatives" />:</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" id="idASS" value="1" /> 1)
				<div style="display: inline; margin-right: 30px">
					<input	type="text" name="alternativesDescription" size="8" />
				</div>
				<div style="display: inline; margin-right: 10px">
					<input type="text" name="alternativesNumber" size="1" />
				</div>
				<div style="display: inline">
					<input type="text" name="alternativesDescription2" size="13" />
				</div>
				<br />
				<br />
				<div id="alternativesASS_div"></div>
				<a href="javascript:void(0)" onClick="addAlternativeASS();">
					<bean:message key="activities.evaluations.questions.alternatives.add" />
				</a>
				<br />
				<a href="javascript:void(0)" onClick="removeAlternativeASS();">
					<bean:message key="activities.evaluations.questions.alternatives.remove" />
				</a>
			</td>
		</tr>
		<tr>
			<td>
				<div align="right">
					<a href="javascript:void(0)" onClick="saveQuestionAssociation(${evaluation.module.position})"><bean:message key="general.save" /></a> / 
					<a href="javascript:void(0)" onClick="cancelQuestions()"><bean:message key="general.cancel" /></a>
				</div>
			</td>
		</tr>
	</table>
</form>
</div>