/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.poll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.DateValidator;

public class PollActions extends org.apache.struts.actions.DispatchAction{

	private Facade facade = Facade.getInstance();
	private final String FORWARD_SHOW_VIEW_NEW_POLL = "fShowViewNewPoll";
	
	public ActionForward newPoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		Module module = facade.getModuleById(idModule);
		Course course = module.getCourse();

		List<Choice> listChoices = new ArrayList<Choice>();

		Poll poll = new Poll();

		poll.setName(myForm.getString("namePoll"));
		poll.setQuestion(myForm.getString("questionPoll"));

		String finalDateDay = (String) myForm.getString("dayPoll");
		String finalDateMonth = (String) myForm.getString("monthPoll");
		String finalDateYear = (String) myForm.getString("yearPoll");	

		DateValidator fDate = new DateValidator(messages, finalDateDay,	finalDateMonth, finalDateYear, true);

		Date finalDate = fDate.getDate();

		boolean isfinalDataValida = facade.isValidationDataToInsertPoll(course,	finalDate);

		if (isfinalDataValida == false) {
			messages.add("erros", new ActionMessage("errors.activities.poll.todayRegistrationDate"));
		}
		
		Date today = new Date();

		if (finalDate.before(today)) {
			messages.add("erros", new ActionMessage("errors.poll.finalDatePoll"));
		}
		
		if (today.compareTo(course.getInitialCourseDate()) >= 0) {

			if (today.compareTo(course.getFinalCourseDate()) > 0) {
				messages.add("erros", new ActionMessage(
						"errors.finalCourseDate"));
			} else {
				poll.setCreationDate(new Date());
			}

		} else {
			poll.setCreationDate(course.getInitialCourseDate());
		}

		poll.setFinishDate(finalDate);

		String[] options = request.getParameterValues("optionsPoll");

		for (int i = 0; i < options.length; i++) {
			if (options[i].equals("")) {
				messages
						.add("erros", new ActionMessage("activities.poll.erro"));
			} else {
				Choice choice = new Choice();
				choice.setAlternative(options[i].trim());
				listChoices.add(choice);
			}
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {
			poll.setChoices(listChoices);
			poll.setModule(module);
			module.getPolls().add(poll);

			facade.flush();
			if(SystemActions.mobileSettings.getSmsPoll().equals("on")){
				Receiver receiver = new Receiver();
				receiver.addMaterial(course.getId(), module.getId(), poll.getId());
			}
			// AJAX REVERSO
			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;
		}

	}

	public ActionForward savePoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		int idPoll = Integer.parseInt(request.getParameter("idPoll"));
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		Course course = module.getCourse();
			
		Poll poll = facade.getPollByID(idPoll);

		List<Choice> listChoices = poll.getChoices();
		List<Answer> listAnswers = poll.getAnswers();

		String[] options = request.getParameterValues("optionsPoll");
		boolean sameChoices = true;

		if (options.length == listChoices.size()) {
			// permaneceu o mesmo numero de alternativas
			
			for(int t = 0; t < listChoices.size(); t = t + 1){
				if(!options[t].equalsIgnoreCase(listChoices.get(t).getAlternative())){
					sameChoices = false;
				}
			}
			
			if(!sameChoices){
				for (int i = 0; i < options.length; i++) {
					if (options[i].equals("")) {
						messages.add("erros", new ActionMessage(
								"activities.poll.erro"));
					} else {
						poll.getChoices().get(i).setAlternative(options[i].trim());
						poll.getChoices().get(i).setPercentage(0.0);
						poll.getChoices().get(i).setVotes(0);
						poll.getAnswers().removeAll(listAnswers);
					}
				}
			}

		} else if (options.length > listChoices.size()) {
			// Adicionou mais alternativas

			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("")) {
					messages.add("erros", new ActionMessage(
							"activities.poll.erro"));
				} else {
					if (i < listChoices.size()) {
						poll.getChoices().get(i).setAlternative(
								options[i].trim());
						poll.getChoices().get(i).setPercentage(0.0);
						poll.getChoices().get(i).setVotes(0);
						poll.getAnswers().removeAll(listAnswers);
					} else {
						Choice choiceAdd = new Choice();
						choiceAdd.setAlternative(options[i].trim());
						poll.getChoices().add(choiceAdd);
					}
				}
			}

		} else {
			// Reirou algumas alternativas

			// id da alternativa a ser excluida
			int id = options.length;

			for (int i = 0; i < listChoices.size(); i++) {

				if (i < options.length) {

					if (options[i].equals(""))
						messages.add("erros", new ActionMessage(
								"activities.poll.erro"));

					poll.getChoices().get(i).setAlternative(options[i].trim());
					poll.getChoices().get(i).setPercentage(0.0);
					poll.getChoices().get(i).setVotes(0);
					poll.getAnswers().removeAll(listAnswers);

				} else {
					Choice choiceRemove = listChoices.get(id);
					poll.getChoices().remove(choiceRemove);
				}
			}

			//Choice choiceRemove = listChoices.get(id);
			//poll.getChoices().remove(choiceRemove);

		}

		poll.setName(myForm.getString("namePoll"));
		poll.setQuestion(myForm.getString("questionPoll"));

		String finalDateDay = (String) myForm.getString("dayPoll");
		String finalDateMonth = (String) myForm.getString("monthPoll");
		String finalDateYear = (String) myForm.getString("yearPoll");

		DateValidator fDate = new DateValidator(messages, finalDateDay,
				finalDateMonth, finalDateYear, true);

		Date finalDate = fDate.getDate();

		boolean isfinalDataValida = facade.isValidationDataToInsertPoll(course,
				finalDate);

		if (isfinalDataValida == false)
			messages.add("erros", new ActionMessage(
					"errors.activities.poll.todayRegistrationDate"));

		Date today = new Date();

		if (finalDate.before(today))
			messages.add("erros",
					new ActionMessage("errors.poll.finalDatePoll"));

		if (today.compareTo(course.getInitialCourseDate()) >= 0) {

			if (today.compareTo(course.getFinalCourseDate()) > 0) {
				messages.add("erros", new ActionMessage(
						"errors.finalCourseDate"));
			} else {
				// poll.setInitDate(new Date());
				poll.setCreationDate(poll.getCreationDate());
			}

		} else {
			poll.setCreationDate(course.getInitialCourseDate());
		}

		poll.setFinishDate(finalDate);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			// AJAX REVERSO
			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;

		}
	}

	public ActionForward deletePoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int idPoll = Integer.parseInt(request.getParameter("idPoll"));
		
		Poll poll = facade.getPollByID(idPoll);
		Module module = poll.getModule();
		
		module.getPolls().remove(poll);
		
		Course course = module.getCourse();
		
		Receiver receiver = new Receiver();
		receiver.removeMaterial(course.getId(), module.getId(), poll.getId());
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;
	}

	public ActionForward answerPoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionMessages messages = new ActionMessages();

		if (request.getParameter("idAlternative").equals("undefined"))
			messages.add("erros", new ActionMessage(
					"errors.activities.poll.alternative"));

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		String alternativa = request.getParameter("idAlternative");
		int idAlternative = Integer.parseInt(alternativa);

		int idPoll = Integer.parseInt(request.getParameter("idPoll"));
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());

		answerPollBD(idAlternative, idPoll, loggedUser);

		return null;
	}

	public synchronized void answerPollBD(int idAlternative, int idPoll, AccessInfo loggedUser) {

		AccessInfo user = facade.searchUserById(loggedUser.getId());
		Person author = facade.getPersonFromEmail(user.getPerson().getEmail());

		Poll poll = facade.getPollByID(idPoll);

		Answer newAnswer = new Answer();

		newAnswer.setAnswerDate(new Date());
		newAnswer.setPerson(author);

		int votesAll = 0;

		for (int i = 0; i < poll.getChoices().size(); i++)
			votesAll = votesAll + poll.getChoices().get(i).getVotes();

		votesAll = votesAll + 1;

		for (int i = 0; i < poll.getChoices().size(); i++) {

			int votesAlternative = poll.getChoices().get(i).getVotes();

			// alternativa escolhida 
			if ((i + 1) == idAlternative) {
				int newVotesAlternative = votesAlternative + 1;
				poll.getChoices().get(i).setVotes(newVotesAlternative);
				poll.getChoices().get(i).setPercentage
				((double) ((double) newVotesAlternative / (double) votesAll)
				* (double) 100);
				// alternativa nao escolhida
			} else {
				poll.getChoices().get(i).setVotes(votesAlternative);
				poll.getChoices().get(i).setPercentage
				((double)((double) votesAlternative / (double) votesAll)
				* (double) 100);
			}
		}

		poll.getAnswers().add(newAnswer);

		return;
	}
	
	public ActionForward showViewNewPoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_POLL);
	}
	
	public ActionForward showViewEditPoll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
