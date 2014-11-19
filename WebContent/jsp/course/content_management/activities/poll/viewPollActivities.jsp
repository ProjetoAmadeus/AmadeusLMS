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

<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade" 
    import="java.util.HashMap"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo"
	import="java.util.List"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message"
	import="java.util.ArrayList"
	import="java.text.SimpleDateFormat"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module"%>

<%

int idPoll = Integer.parseInt(request.getParameter("idPoll"));
Facade facade = Facade.getInstance();
Poll poll = facade.getPollByID(idPoll);
Module module = poll.getModule();

AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
loggedUser = facade.searchUserById(loggedUser.getId());

HashMap<String, Object> data = new HashMap<String, Object>();
SimpleDateFormat formater = new SimpleDateFormat("EEEE, d MMMM yyyy");

List<Choice> listChoices = poll.getChoices();
List<Answer> listAnswer = poll.getAnswers();

data.put("namePoll",poll.getName());
data.put("questionPoll", poll.getQuestion());

request.setAttribute("polls", data);

boolean isValidationDataToAnswer = facade.isValidationDataToAnswerPoll(poll);
boolean alreadyAnswer = false;

for (int i = 0; i < listAnswer.size(); i++){
  	if (listAnswer.get(i).getPerson().getId() == loggedUser.getPerson().getId())
  		alreadyAnswer = true;
}
String disableChoices = "";
if(!isValidationDataToAnswer){
	disableChoices = "disabled=''";
}

%>

<div id="viewPollActivities" class="cmBody">
<html:form action="answerPoll" >
	<div class="cmTitle"> 
		<bean:message key="activities.poll" />
	</div>
	<html:errors/>
	<div class="cmLine">
		<label class="labelAttribute">Nome:</label>
		<label class="labelValue"><%=poll.getName() %></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.Delivery" /></label>
		<label class="labelValue"><%=formater.format(poll.getFinishDate())%></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Descrição:</label>
		<label class="labelValue"><%=poll.getQuestion() %></label>
	</div>
	<div id="<%="answerPoll"+module.getPosition()%>">
		<div class="cmLine">
			<label class="labelAttribute"><bean:message key="activities.poll.options" /></label>
		</div>
		<div id=<%="poll_alternative"%> align="left">
		 	<%for (int i = 0; i < listChoices.size(); i++){
			  	int id = i + 1;
			%>
					<input id=<%="alternative_"+id%> name="alternativePoll" <%=disableChoices%> class="radio" type="radio" value="<%=listChoices.get(i).getId()%>"/><%=listChoices.get(i).getAlternative()%>
					<br /><br />
			<%}%>
		</div>
	</div>
	<div class="cmFooter">
		 <%
		 	// se está no prazo de respostas e o usuario ainda não respondeu
			
		  if ((alreadyAnswer == false) && (isValidationDataToAnswer == true)) {%>
				<a onclick="answerPoll(<%=module.getPosition()%>,<%=poll.getId()%>);" href="javascript:void(0)"><bean:message key="general.send" /></a> /
		<%}%>
				<a onclick="backEditName(<%=module.getPosition()%>);" href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
</html:form>
</div>