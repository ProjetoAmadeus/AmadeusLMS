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
	import="java.util.List"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message"
	import="java.util.ArrayList"
	import="java.util.GregorianCalendar"
	import="java.util.Calendar"
	import="java.text.SimpleDateFormat"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module"
	%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<%

int idPoll = Integer.parseInt(request.getParameter("idPoll"));

Facade facade = Facade.getInstance();
Poll poll = facade.getPollByID(idPoll);

Module module = poll.getModule();

HashMap<String, Object> data = new HashMap<String, Object>();
SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

List<Choice> choices = poll.getChoices();

GregorianCalendar gc = new GregorianCalendar();
gc.setTime(poll.getFinishDate());

data.put("namePoll",poll.getName());
data.put("questionPoll", poll.getQuestion());
data.put("dayPoll", gc.get(Calendar.DAY_OF_MONTH));
data.put("monthPoll", gc.get(Calendar.MONTH) + 1);
data.put("yearPoll", gc.get(Calendar.YEAR));

request.setAttribute("polls", data);
%>

<div id="editPollActivities" class="cmBody">
<html:form action="newPoll" >
	<div class="cmTitle">
		<bean:message key="activities.poll"/>
	</div>
	<html:errors/>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.name"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="polls" property="namePoll"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.question"/>:</label>
	</div>
	<div class="cmLine">
		<html:textarea name="polls" property="questionPoll" styleClass="ativDescriptTextarea" style="width: 98%;"/>
	</div>
	<%if (poll.getAnswers().size() > 0) {%>
	<div class="msgBoxImportant">
		<bean:message key="activities.poll.lostData"/>
	</div>
	<%}%>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.option"/>:</label>
	</div>
	<div class="cmLine">
		<ul id=<%="poll_alt"%>>
			<%
			
			// se está no prazo de respostas e o usuario ainda não respondeu
		  
			if (choices.size() > 2) {
			
			 	for (int i = 0; i < choices.size(); i++){
					int id = i+1;%>
					<li>
						<input id=<%="alt_" + id%> type="text" value="<%=choices.get(i).getAlternative()%>"/>&nbsp;[<a onclick=<%="delAnswerPoll("+id+");"%> href=javascript:void(0)>X</a>]&nbsp;&nbsp;<%=choices.get(i).getVotes()%>&nbsp;<bean:message key="activities.poll.Votes"/>
					</li>
				<%}%>
			<%} else {
				
				for (int i = 0; i < choices.size(); i++){
					int id = i+1;%>
					<li>
						<input id=<%="alt_" + id%> type="text" value="<%=choices.get(i).getAlternative()%>"/>&nbsp;&nbsp;<%=choices.get(i).getVotes()%>&nbsp;<bean:message key="activities.poll.Votes"/>
					</li>
				<%}%>
			<%}%>
			
		</ul>
		<ul>
			<li>
				<a href="javascript:void(0)" onclick=<%="newAnswerPoll();"%>><bean:message key="activities.poll.newAnswer"/></a>.
			</li>
		</ul>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.deadLine"/>:</label>
	</div>
	<div class="cmLine">	
		<html:text name="polls" property="dayPoll" size="2" maxlength="2"/> / 
		<html:text name="polls" property="monthPoll" size="2" maxlength="2"/> / 
		<html:text name="polls" property="yearPoll" size="4" maxlength="4"/>
	</div>
	<div class="cmFooter">
		<a onclick=<%="savePoll("+module.getId()+","+module.getPosition()+","+idPoll+");"%>	href="javascript:void(0)"><bean:message key="general.save" /></a> / 
		<a onclick="cancelShowListActivity(<%=module.getPosition()%>);" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
	</div>
</html:form>
</div>