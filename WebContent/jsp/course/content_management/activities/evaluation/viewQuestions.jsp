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


<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade" 
    import="java.util.HashMap"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question"
	import="java.util.List"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message"
	import="java.util.ArrayList"
	import="java.text.SimpleDateFormat"
	import="java.util.Date"
	import="java.util.Calendar"
	import="java.util.GregorianCalendar"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course"
	import="br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR"
	language="java" %>

<%
int idEvaluation; 
int idModule;
if(request.getParameter("idEvaluation") != null && !request.getParameter("idEvaluation").equals("")){
	idEvaluation = Integer.parseInt(request.getParameter("idEvaluation"));
	idModule = Integer.parseInt(request.getParameter("idModule"));
}else{
	idEvaluation =(Integer) request.getAttribute("idEvaluation");
	idModule =(Integer) request.getAttribute("idModule");
}

if(request.getAttribute("questionType") != null){
	String showQuestion = (String)request.getAttribute("questionType");
	UtilDWR.getUtil().addFunctionCall("startQuestion", showQuestion);
}	

Course course = (Course)request.getSession().getAttribute("course");

Facade facade = Facade.getInstance();
Evaluation evaluation = facade.getEvaluationById(idEvaluation);

HashMap<String, Object> data = new HashMap<String, Object>();
SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

data.put("descriptionEvaluation",evaluation.getDescription());
data.put("startEvaluation", evaluation.getStart());
data.put("endEvaluation", evaluation.getFinish());
data.put("allowPeriodEvaluation", evaluation.isAfterdeadlineachieved());

request.setAttribute("evaluations", data);

request.setAttribute("evaluation", evaluation);
request.setAttribute("questions", evaluation.getQuestions());
%>

<div class="cmTitle">
	<bean:message key="activities.evaluations.questions.question"/> <%=(evaluation.getQuestions().size() + 1)%>
</div>
<div class="cmLine">
	<select id="questionType" name="questionType" onChange="selectQuestion(this.options[this.selectedIndex].value, ${evaluation.id});">
		<option value=""><bean:message key="activities.evaluations.questions.choose"/></option>
		<option value="M"><bean:message key="activities.evaluations.questions.multipleChoice"/></option>
		<option value="V"><bean:message key="activities.evaluations.questions.trueOrFalse"/></option>
		<!-- <option value="A"><bean:message key="activities.evaluations.questions.collumnAssociation"/></option> -->
		<option value="P"><bean:message key="activities.evaluations.questions.gapFilling"/></option>
		<option value="D"><bean:message key="activities.evaluations.questions.discursive"/></option>
	</select>
</div>