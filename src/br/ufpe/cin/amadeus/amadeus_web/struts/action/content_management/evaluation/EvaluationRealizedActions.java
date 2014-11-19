/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.evaluation;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionAssociation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.AlternativeRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.EvaluationRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionAlternativableRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionAssociationRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionDiscursiveRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionGapRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class EvaluationRealizedActions extends org.apache.struts.actions.DispatchAction{

	public ActionForward saveRealizedEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Facade facade = Facade.getInstance(); 
		ActionMessages messages = new ActionMessages();
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		Person person = info.getPerson(); 
		
		Evaluation ev = facade.getEvaluationById(Integer.parseInt(request.getParameter("evaluationId")));

		
		
		
		EvaluationRealized evR = new EvaluationRealized();
		evR.setRealizedDate(new Date());
		evR.setEvaluation(ev);
		evR.setStudent(person);

		for (int i = 0; i < ev.getQuestions().size(); i++) {
			Question q = ev.getQuestions().get(i);
			if (q instanceof QuestionDiscursive) {
				
				QuestionDiscursiveRealized qRTemp0 = new QuestionDiscursiveRealized();
				qRTemp0.setEvaluationRealized(evR);
				qRTemp0.setQuestion(q);
				qRTemp0.setAnswer(request.getParameter("alternative" + i));
				evR.getQuestionsRealized().add(qRTemp0);
				
				messages = facade.validateQuestionRealizedDiscursive(qRTemp0);
				
			} else if (q instanceof QuestionGap) {
				QuestionGap qN = (QuestionGap) q;
				
				List<String> gaps = Arrays.asList(request.getParameterValues("alternative" + i));
				
				QuestionGapRealized qRTemp1 = new QuestionGapRealized();
				qRTemp1.setEvaluationRealized(evR);
				qRTemp1.setQuestion(q);
				qRTemp1.getGapAnswers().addAll(gaps);
				evR.getQuestionsRealized().add(qRTemp1);

			} else if (q instanceof QuestionMultiple) {
				QuestionMultiple qN = (QuestionMultiple) q;
				
				QuestionAlternativableRealized qRTemp2 = new QuestionAlternativableRealized();
				qRTemp2.setEvaluationRealized(evR);
				qRTemp2.setQuestion(q);
				
				if(request.getParameter("alternative" + i) != null){
					int iEscolhida = Integer.parseInt(request.getParameter("alternative" + i));
					for (int j = 0; j < qN.getAlternatives().size(); j++) {
						AlternativeRealized aR = new AlternativeRealized();
						aR.setQuestionAlternativableRealized(qRTemp2);
						aR.setAlternative(qN.getAlternatives().get(j));
						aR.setAnswer(j == iEscolhida);
						qRTemp2.getAlternativesRealized().add(aR);
					}
				} else {
					messages.add("error", new ActionMessage("errors.evaluation.question.answer.multiple.required"));
				}
				evR.getQuestionsRealized().add(qRTemp2);
			} else if (q instanceof QuestionAssociation) {
				QuestionAssociation qN = (QuestionAssociation) q;
				
				QuestionAssociationRealized qRTemp3 = new QuestionAssociationRealized();
				qRTemp3.setEvaluationRealized(evR);
				qRTemp3.setQuestion(qN);
				
				List<String> chaves = Arrays.asList(request.getParameterValues("alternativeK" + i));
				List<String> valores = Arrays.asList(request.getParameterValues("alternativeV" + i));
				List<String> numeros = Arrays.asList(request.getParameterValues("alternativeN" + i));
				
				Map<String, String> associacoes = new HashMap<String, String>();
				
				for (int j = 0; j < valores.size(); j++) {
					associacoes.put(chaves.get(Integer.parseInt(numeros.get(j)) - 1), valores.get(j));
				}
				
				qRTemp3.getAssociationsRealized().putAll(associacoes);
				evR.getQuestionsRealized().add(qRTemp3);
			} else if (q instanceof QuestionTrueFalse) {
				QuestionTrueFalse qN = (QuestionTrueFalse) q;
				
				QuestionAlternativableRealized qRTemp4 = new QuestionAlternativableRealized();
				qRTemp4.setEvaluationRealized(evR);
				qRTemp4.setQuestion(q);
				
				for (int j = 0; j < qN.getAlternatives().size(); j++) {
					AlternativeRealized aR = new AlternativeRealized();
					aR.setQuestionAlternativableRealized(qRTemp4);
					aR.setAlternative(qN.getAlternatives().get(j));
					if(request.getParameter("alternative"+i+"_"+j) != null){
						aR.setAnswer(request.getParameter("alternative"+i+"_"+j).equals("V"));
					}
					qRTemp4.getAlternativesRealized().add(aR);
				}
				evR.getQuestionsRealized().add(qRTemp4);
			}		
			
		}		
		
		if(messages.isEmpty()) {
			ev.getEvaluationsRealized().add(evR);
		}
		
		saveErrors(request, messages);
		
		return new EvaluationActions().showViewEvaluationActivity(mapping, form, request, response);
	}

}
