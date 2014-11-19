/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Alternative;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionAssociation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class QuestionActions extends org.apache.struts.actions.DispatchAction{

	private Facade facade = Facade.getInstance();
	
	private final String FORWARD_SHOW_VIEW_NEW_MULTIPLE_CHOICE_QUESTION = "fShowViewNewMultipleChoiceQuestion";
	private final String FORWARD_SHOW_VIEW_NEW_TRUE_FALSE_QUESTION = "fShowViewNewTrueFalseQuestion";
	private final String FORWARD_SHOW_VIEW_NEW_GAP_FILLING_QUESTION = "fShowViewNewGapFillingQuestion";
	private final String FORWARD_SHOW_VIEW_NEW_DISCURSIVE_QUESTION = "fShowViewNewDiscursiveQuestion";
	private final String FORWARD_SHOW_VIEW_NEW_COLLUMN_ASSOCIATION_QUESTION = "fShowViewNewCollumAssociationQuestion";
	
	private final String FORWARD_SHOW_VIEW_EDIT_MULTIPLE_CHOICE_QUESTION = "fShowViewEditMultipleChoiceQuestion";
	private final String FORWARD_SHOW_VIEW_EDIT_TRUE_FALSE_QUESTION = "fShowViewEditTrueFalseQuestion";
	private final String FORWARD_SHOW_VIEW_EDIT_GAP_FILLING_QUESTION = "fShowViewEditGapFillingQuestion";
	private final String FORWARD_SHOW_VIEW_EDIT_DISCURSIVE_QUESTION = "fShowViewEditDiscursiveQuestion";
	private final String FORWARD_SHOW_VIEW_EDIT_COLLUMN_ASSOCIATION_QUESTION = "fShowViewEditCollumAssociationQuestion";
	
	private final String FORWARD_SHOW_VIEW_DELETE_CONFIRMATION_QUESTION = "fShowViewDeleteConfirmationQuestion";
	
	public ActionForward showViewNewQuestionMultiple(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		request.setAttribute("evaluation", evaluation);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_MULTIPLE_CHOICE_QUESTION);
	}
	
	public ActionForward newQuestionMultiple(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ActionMessages messages;
		
		QuestionMultiple questionMultipleForValidation = new QuestionMultiple();
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		questionMultipleForValidation.setDescription(request.getParameter("questionDescription"));
		
		String[] alternativesDescription = request.getParameterValues("alternativesDescription");
		int alternativesCorrect = Integer.parseInt(request.getParameter("alternativeCorrect"));
		List<Alternative> alternatives = new ArrayList<Alternative>();
		for (int i = 0; i < alternativesDescription.length; i++) {
			Alternative a = new Alternative();
			a.setDescription(alternativesDescription[i]);
			a.setCorrect(alternativesCorrect == (i+1));
			alternatives.add(a);
		}
		
		questionMultipleForValidation.setAlternatives(alternatives);
		
		messages = facade.validateQuestionMultiple(questionMultipleForValidation);	
		
		if(messages.isEmpty()){
			
			Course course = evaluation.getModule().getCourse();
			
			QuestionMultiple question = new QuestionMultiple();
			question.setCourse(course);
			question.setDescription(questionMultipleForValidation.getDescription());
			question.setAlternatives(questionMultipleForValidation.getAlternatives());
			question.getEvaluations().add(evaluation);
			
			evaluation.getQuestions().add(question);
			
			course.getQuestions().add(question);
			
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response); 
		}else{
			saveErrors(request, messages);
			forward = this.showViewNewQuestionMultiple(mapping, form, request, response); 
		}
		
		return forward;
	}
	
	private ActionForward showViewEditQuestionMultiple(Evaluation evaluation, Question question, ActionMapping mapping) {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_MULTIPLE_CHOICE_QUESTION);
	}
	
	public ActionForward editQuestionMultiple(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ActionMessages messages;
		QuestionMultiple questionMultipleForValidation = new QuestionMultiple();
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		int questionMultipleId = Integer.valueOf(request.getParameter("questionId"));
		
		questionMultipleForValidation.setDescription(request.getParameter("questionDescription"));
		
		String[] alternativesDescription = request.getParameterValues("alternativesDescription");
		
		int alternativesCorrect = 0;
		boolean hasOneAlternativesCorrect = false;
		
		if(request.getParameter("alternativeCorrect") != null) {
			alternativesCorrect = Integer.parseInt(request.getParameter("alternativeCorrect"));	
			hasOneAlternativesCorrect = true;
		}
		
		List<Alternative> alternatives = new ArrayList<Alternative>();
		for (int i = 0; i < alternativesDescription.length; i++) {
			Alternative a = new Alternative();
			a.setDescription(alternativesDescription[i]);
			if(hasOneAlternativesCorrect) {
				a.setCorrect(alternativesCorrect == i);
			}
			alternatives.add(a);
		}
		
		questionMultipleForValidation.setAlternatives(alternatives);
		
		messages = facade.validateQuestionMultiple(questionMultipleForValidation);
		
		if(messages.isEmpty()){
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			QuestionMultiple questionMultiple = (QuestionMultiple) facade.getQuestionById(evaluation, questionMultipleId);
			
			questionMultiple.setDescription(questionMultipleForValidation.getDescription());
			
			for (int i = (questionMultiple.getAlternatives().size()-1); i >= 0; i--) {
				questionMultiple.getAlternatives().remove(i);
			}
			
			questionMultiple.getAlternatives().addAll(questionMultipleForValidation.getAlternatives());
			
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response); 
		}else{
			saveErrors(request, messages);
			forward = this.showViewEditQuestion(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewNewQuestionTrueFalse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		request.setAttribute("evaluation", evaluation);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_TRUE_FALSE_QUESTION);
	}
	
	public ActionForward newQuestionTrueFalse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ActionMessages messages;
		
		QuestionTrueFalse questionTrueFalseForValidation = new QuestionTrueFalse();
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		questionTrueFalseForValidation.setDescription(request.getParameter("questionDescription"));
		
		String[] alternativesDescription = request.getParameterValues("alternativesDescription");

		List<Alternative> alternatives = new ArrayList<Alternative>();
		for (int i = 0; i < alternativesDescription.length; i++) {
			Alternative a = new Alternative();
			a.setDescription(alternativesDescription[i]);
			a.setCorrect(request.getParameter("alternativeCorrect" + i).equals("true"));
			alternatives.add(a);
		}
		
		questionTrueFalseForValidation.setAlternatives(alternatives);
		
		messages = facade.validateQuestionTrueFalse(questionTrueFalseForValidation);	
		
		if(messages.isEmpty()) {
			
			Course course = evaluation.getModule().getCourse();
			
			QuestionTrueFalse questionTrueFalse = new QuestionTrueFalse();
			questionTrueFalse.setCourse(course);
			questionTrueFalse.setDescription(questionTrueFalseForValidation.getDescription());
			questionTrueFalse.setAlternatives(questionTrueFalseForValidation.getAlternatives());
			questionTrueFalse.getEvaluations().add(evaluation);
			
			evaluation.getQuestions().add(questionTrueFalse);
			
			course.getQuestions().add(questionTrueFalse);

			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewNewQuestionTrueFalse(mapping, form, request, response);
		}
				
		return forward;
	}

	private ActionForward showViewEditQuestionTrueFalse(Evaluation evaluation, Question question, ActionMapping mapping) {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_TRUE_FALSE_QUESTION);
	}
	
	public ActionForward editQuestionTrueFalse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ActionMessages messages;
		QuestionTrueFalse questionTrueFalseForValidation = new QuestionTrueFalse();
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		int questionTrueFalseId = Integer.valueOf(request.getParameter("questionId"));
		
		questionTrueFalseForValidation.setDescription(request.getParameter("questionDescription"));
		
		String[] alternativesDescription = request.getParameterValues("alternativesDescription");
		
		List<Alternative> alternatives = new ArrayList<Alternative>();
		for (int i = 0; i < alternativesDescription.length; i++) {
			Alternative a = new Alternative();
			a.setDescription(alternativesDescription[i]);
			a.setCorrect(request.getParameter("alternativeCorrect" + i).equals("true"));
			alternatives.add(a);
		}
		
		questionTrueFalseForValidation.setAlternatives(alternatives);
		messages = facade.validateQuestionTrueFalse(questionTrueFalseForValidation);
		
		if(messages.isEmpty()) {
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			QuestionTrueFalse questionTrueFalse = (QuestionTrueFalse) facade.getQuestionById(evaluation, questionTrueFalseId);
			
			questionTrueFalse.setDescription(questionTrueFalseForValidation.getDescription());
			
			for (int i = (questionTrueFalse.getAlternatives().size()-1); i >= 0; i--) {
				questionTrueFalse.getAlternatives().remove(i);
			}
			
			questionTrueFalse.getAlternatives().addAll(questionTrueFalseForValidation.getAlternatives());
			
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewEditQuestion(mapping, form, request, response);
		}
		
		return forward;		
	}
	
	public ActionForward showViewNewQuestionGap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		request.setAttribute("evaluation", evaluation);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_GAP_FILLING_QUESTION);
	}
	
	public ActionForward newQuestionGap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward;
		
		QuestionGap questionGapForValidation = new QuestionGap();
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		questionGapForValidation.setDescription(request.getParameter("questionDescription"));
		
		ActionMessages messages = facade.validateQuestionGap(questionGapForValidation);
		
		if(messages.isEmpty()) {
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			
			Course course = evaluation.getModule().getCourse();
			
			QuestionGap question = new QuestionGap();
			question.setCourse(course);
			question.setDescription(questionGapForValidation.getDescription());
			question.getEvaluations().add(evaluation);
			
			evaluation.getQuestions().add(question);
			
			course.getQuestions().add(question);
	
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewNewQuestionGap(mapping, form, request, response);
		}
		
		return forward;
	}
	
	private ActionForward showViewEditQuestionGap(Evaluation evaluation, Question question, ActionMapping mapping) {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_GAP_FILLING_QUESTION);
	}
	
	public ActionForward editQuestionGap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward;
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		int questionGapId = Integer.valueOf(request.getParameter("questionId"));
		String questionGapDescription = request.getParameter("questionGapDescription");
		
		QuestionGap questionGapForValidation = new QuestionGap();
		questionGapForValidation.setDescription(questionGapDescription);
		
		ActionMessages messages = facade.validateQuestionGap(questionGapForValidation);
		
		if(messages.isEmpty()) {
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			QuestionGap questionGap = (QuestionGap) facade.getQuestionById(evaluation, questionGapId);
			
			questionGap.setDescription(questionGapForValidation.getDescription());
			
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewEditQuestion(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewNewQuestionDiscursive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		request.setAttribute("evaluation", evaluation);

		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_DISCURSIVE_QUESTION);
	}
	
	public ActionForward newQuestionDiscursive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward;
		
		QuestionDiscursive questionDiscursiveForValidation = new QuestionDiscursive();
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		questionDiscursiveForValidation.setDescription(request.getParameter("questionDescription"));
		
		ActionMessages messages = facade.validateQuestionDiscursive(questionDiscursiveForValidation);
		
		if(messages.isEmpty()) {
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			
			Course course = evaluation.getModule().getCourse();
			
			QuestionDiscursive question = new QuestionDiscursive();
			question.setCourse(course);
			question.setDescription(questionDiscursiveForValidation.getDescription());
			question.getEvaluations().add(evaluation);
			
			evaluation.getQuestions().add(question);
			
			course.getQuestions().add(question);

			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewNewQuestionDiscursive(mapping, form, request, response);
		}
		
		return forward;
	}
	
	private ActionForward showViewEditQuestionDiscursive(Evaluation evaluation, Question question, ActionMapping mapping) {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_DISCURSIVE_QUESTION);
	}
	
	public ActionForward editQuestionDiscursive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward;
		
		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		int questionDiscursiveId = Integer.valueOf(request.getParameter("questionId"));
		String questionDiscursiveDescription = request.getParameter("questionDiscursiveDescription");
		
		QuestionDiscursive questionDiscursiveForValidation = new QuestionDiscursive();
		questionDiscursiveForValidation.setDescription(questionDiscursiveDescription);
		
		ActionMessages messages = facade.validateQuestionDiscursive(questionDiscursiveForValidation);
		
		if(messages.isEmpty()) {	
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			QuestionDiscursive questionDiscursive = (QuestionDiscursive) facade.getQuestionById(evaluation, questionDiscursiveId);
			
			//BeanUtils.copyProperties( questionDiscursive, questionDiscursiveForValidation);
			
			questionDiscursive.setDescription(questionDiscursiveForValidation.getDescription());
			
			EvaluationActions evaluationActions = new EvaluationActions();
			
			forward = evaluationActions.showViewEditEvaluation(mapping, form, request, response);
		} else {
			saveErrors(request, messages);
			forward = this.showViewEditQuestion(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewNewQuestionAssociation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int evaluationId = Integer.valueOf(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		request.setAttribute("evaluation", evaluation);

		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_COLLUMN_ASSOCIATION_QUESTION);
	}
	
	public ActionForward newQuestionAssociation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		Evaluation evaluation = facade.getEvaluationById(evaluationId);

		String[] alternativesDescription = request.getParameterValues("alternativesDescription");
		String[] alternativesNumber = request.getParameterValues("alternativesNumber");
		String[] alternativesDescription2 = request.getParameterValues("alternativesDescription2");

		Course course = evaluation.getModule().getCourse();
		
		QuestionAssociation question = new QuestionAssociation();
		
		Map<String, String> associations = question.getAssociations();
		for (int i = 0; i < alternativesDescription.length; i++) {
			String chave = alternativesDescription[i];
			String valor = "";
			
			//encontrando o valor associado
			for (int j = 0; j < alternativesNumber.length; j++) {
				if (Integer.parseInt(alternativesNumber[j]) - 1 == i) {
					valor = alternativesDescription2[j];
				}
			}
			associations.put(chave, valor);
		}
		
		question.setCourse(course);
		question.setDescription(request.getParameter("questionDescription"));
		question.getEvaluations().add(evaluation);
		
		evaluation.getQuestions().add(question);
		
		course.getQuestions().add(question);

		EvaluationActions evaluationActions = new EvaluationActions();
		
		return evaluationActions.showViewEditEvaluation(mapping, form, request, response);
	}

	private ActionForward showViewEditQuestionAssociation(Evaluation evaluation, Question question, ActionMapping mapping) {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_COLLUMN_ASSOCIATION_QUESTION);
	}
	
	public ActionForward editQuestionAssociation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {


		return null;
	}
	
	public ActionForward showViewEditQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		int questionPosition = Integer.parseInt(request.getParameter("questionPosition"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		Question question = facade.getQuestionById(evaluation, questionId);
		
		if(question instanceof QuestionMultiple) {
		
			QuestionMultiple questionMultiple = (QuestionMultiple) question;
			request.setAttribute("questionMultiple", questionMultiple);
			
			forward = this.showViewEditQuestionMultiple(evaluation, question, mapping);
		
		} else if(question instanceof QuestionTrueFalse) {
		
			QuestionTrueFalse questionTrueFalse = (QuestionTrueFalse) question;
			request.setAttribute("questionTrueFalse", questionTrueFalse);
			forward = this.showViewEditQuestionTrueFalse(evaluation, question, mapping);
		
		} else if(question instanceof QuestionAssociation) {
		
			QuestionAssociation questionAssociation = (QuestionAssociation) question;
			
			List<String> chaves = new ArrayList<String>(questionAssociation.getAssociations().keySet());
			List<String> valores = new ArrayList<String>(questionAssociation.getAssociations().values());
			
			Collections.shuffle(valores);
			
			request.setAttribute("questionAssociation", questionAssociation);
			request.setAttribute("chaves", chaves);
			request.setAttribute("valores", valores);
			forward = this.showViewEditQuestionAssociation(evaluation, question, mapping);
		
		} else if(question instanceof QuestionGap) {
			
			QuestionGap questionGap = (QuestionGap) question;
			request.setAttribute("questionGap", questionGap);
			forward = this.showViewEditQuestionGap(evaluation, question, mapping);
		
		} else if(question instanceof QuestionDiscursive) {
		
			QuestionDiscursive questionDiscursive = (QuestionDiscursive) question;
			request.setAttribute("questionDiscursive", questionDiscursive);
			forward = this.showViewEditQuestionDiscursive(evaluation, question, mapping);
		
		}
		
		request.setAttribute("questionPosition", questionPosition);
		request.setAttribute("evaluation", evaluation);
		return forward;
	}
	
	public ActionForward showViewDeleteConfirmationQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int modulePosition = Integer.parseInt(request.getParameter("modulePosition"));
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		
		request.setAttribute("modulePosition", modulePosition);
		request.setAttribute("evaluationId", evaluationId);
		request.setAttribute("questionId", questionId);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_DELETE_CONFIRMATION_QUESTION);
	}
	
	public ActionForward deleteQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		Question question = facade.getQuestionById(evaluation, questionId);
	
		evaluation.getQuestions().remove(question);
		
		facade.flush();
		
		EvaluationActions evaluationActions = new EvaluationActions();
		
		return evaluationActions.showViewEditEvaluation(mapping, form, request, response);
	}
}
