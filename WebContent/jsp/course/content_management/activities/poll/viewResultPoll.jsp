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
    import="java.text.DecimalFormat"
    import="java.util.HashMap"
    import="java.util.List"
    import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll"
    import="br.ufpe.cin.amadeus.amadeus_web.domain.register.Person"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice"
	import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer"%>

<%

Facade facade = Facade.getInstance();

int idPoll = Integer.parseInt(request.getParameter("idPoll"));
int idModule = Integer.parseInt( (String) request.getParameter("idModule") );

Poll poll = facade.getPollByID(idPoll);

HashMap<String, Object> data = new HashMap<String, Object>();

data.put("namePoll",poll.getName());
data.put("questionPoll", poll.getQuestion());

List<Choice> choices = poll.getChoices();

request.setAttribute("polls", data);

DecimalFormat decimal = new DecimalFormat("0.00");
%>
<div id="viewResultPoll" class="cmBody">
	<div class="cmTitle">
		<bean:message key="activities.poll.viewResultsTitle"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Nome:</label>
		<label class="labelValue"><%=poll.getName()%></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Descrição:</label>
		<label class="labelValue"><%=poll.getQuestion()%></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.poll.options"/></label>
	</div>
	<div class="cmLine">
	  <%for (int i = 0; i < choices.size(); i++){%>
		<ul>
			<li>
				<%=choices.get(i).getAlternative()%>		
				<%=choices.get(i).getVotes()%>
				-
				<%=decimal.format(choices.get(i).getPercentage())%>&#x25;
			</li>
		</ul>
		<%}%>
	</div>
	<%
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
	
		Person loggedUserPerson = loggedUser.getPerson();
		
		boolean answered = false;
		for (Answer answer : poll.getAnswers()) {
			if (answer.getPerson().getId() == loggedUserPerson.getId()) {
				answered = true;
			}
		}
	
		if (answered) { %>
		<div class="msgBoxInformation">	
			<bean:message key="activities.poll.answered" />
		</div>	
			<% 
		} 
	%>
	<div class="cmFooter">
		<a onclick=<%="backViewResultsPoll("+idModule+");"%> href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
</div>