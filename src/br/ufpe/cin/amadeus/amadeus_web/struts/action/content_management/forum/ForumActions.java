/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.forum;

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
import org.apache.struts.action.DynaActionForm;

import sun.security.provider.SystemSigner;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SettingsActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;

public class ForumActions extends SystemActions {

	private final String FORWARD_SHOW_VIEW_FORUM_ACTIVITY = "fShowViewForumActivity";
	private final String FORWARD_SHOW_VIEW_NEW_FORUM_ACTIVITY = "fShowViewNewForumActivity";
	private final String FORWARD_SHOW_VIEW_EDIT_FORUM_ACTIVITY = "fShowViewEditForumActivity";
	private final String FORWARD_SHOW_VIEW_NEW_ANSWER_FORUM_ACTIVITY = "fShowViewNewAnswerForumActivity";
	private final String FORWARD_SHOW_VIEW_LIST_MESSAGES_FORUM_ACTIVITY = "fShowViewListMessagesForumActivity";

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("forumActivity.showViewForumActivity", "showViewForumActivity");
		map.put("forumActivity.showViewNewForumActivity", "showViewNewForumActivity");
		map.put("forumActivity.newForumActivity", "newForumActivity");
		map.put("forumActivity.showViewEditForumActivity", "showViewEditForumActivity");
		map.put("forumActivity.editForumActivity", "editForumActivity");
		map.put("forumActivity.deleteForumActivity", "deleteForumActivity");
		map.put("forumActivity.showViewListMessagesForumActivity", "showViewListMessagesForumActivity");
		map.put("forumActivity.showViewNewAnswerForumActivity", "showViewNewAnswerForumActivity");
		map.put("forumActivity.answerForumActivity", "answerForumActivity");
		
		return map;
	}
	
	public ActionForward showViewForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		int idForum = Integer.parseInt(request.getParameter("idForum"));
		
		Forum forum = facade.getForumById(idForum);
		Module module = forum.getModule();
		//Course course = module.getCourse();
		
		//boolean canAnswerForum = facade.isValidationDataToAnswerForum(forum, course);
		
		request.setAttribute("forum", forum);
		request.setAttribute("module", module);
		//request.setAttribute("canAnswerForum", canAnswerForum);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_FORUM_ACTIVITY);
	}
	
	public ActionForward showViewNewForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_FORUM_ACTIVITY);
	}
	
	public ActionForward newForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		Course course = module.getCourse();

		Forum f = new Forum();
		f.setName(myForm.getString("topicForum"));
		f.setDescription(myForm.getString("descriptionForum"));

		Date today = new Date();

		f.setCreationDate(today);

		if (today.compareTo(course.getFinalCourseDate()) > 0)
			messages.add("erros", new ActionMessage("errors.finalCourseDate"));

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			f.setModule(module);
			module.getForums().add(f);

			facade.flush();
			if(SettingsActions.mobileSettings.getSmsForum().equals("on")){
				Receiver receiver = new Receiver();
				receiver.addMaterial(course.getId(), module.getId(), f.getId());
			}
			// AJAX REVERSO
			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;
		}

	}

	public ActionForward showViewEditForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idForum = Integer.parseInt(request.getParameter("idForum"));

		Forum forum = facade.getForumById(idForum);
		Module module = forum.getModule();

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("idForum", forum.getId());
		data.put("nameForum",forum.getName());
		data.put("descriptionForum", forum.getDescription());

		request.setAttribute("forum", data);
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_FORUM_ACTIVITY);
	}
	
	public ActionForward editForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		int idForum = Integer.parseInt(request.getParameter("idForum"));
		
		Forum forum = facade.getForumById(idForum);
		
		forum.setName(myForm.getString("topicForum"));
		forum.setDescription(myForm.getString("descriptionForum"));

		facade.flush();
		
		// AJAX REVERSO
		UtilActivities.eraseAndWriteNameActivity(forum.getModule().getId());
		return null;
	}

	public ActionForward deleteForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int idForum = Integer.parseInt(request.getParameter("idForum"));
		
		Forum forum = facade.getForumById(idForum);
		Module module = forum.getModule();
		Course course = module.getCourse();
		
		module.getForums().remove(forum);
		
		facade.flush();
		
		//ENVIO SMS COMENTADO
		/*Receiver receiver = new Receiver();
		receiver.removeMaterial(course.getId(), module.getId(), idForum);*/
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;
	}

	public ActionForward showViewListMessagesForumActivity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		int idForum = Integer.parseInt(request.getParameter("idForum"));
		Forum forum = facade.getForumById(idForum);		
		
		int maxResultado = 5;
		int qtdIdPorPag = 10;
		int pivo;
		List<Message> messages = null;
		int pagina;
		int anterior;
		int posterior;
		int menos = 0;
		int mais = 0;
		int numeroDePagina = 0;
	
		pivo = (int)Math.ceil((double)qtdIdPorPag/(double)2);
		
		int totalResultado = facade.getSizeSearchMessageByForum(forum);

		if (request.getParameter("pagina") == null) {
		   pagina = 1;
		} else {
			pagina = Integer.parseInt((request.getParameter("pagina")).toString());
			
			if (pagina < 1){
				pagina = (int)Math.ceil((double)totalResultado/(double)maxResultado);;
			}
		}

		int inicio = pagina;
		
		if (totalResultado == 0){
		
		} else {
			messages = (List<Message>)facade.searchMessageByPaging(maxResultado, inicio, forum);
		}
		
		if (totalResultado > 1) {
			// Calculando pagina anterior
			menos = pagina - 1;
			// Calculando pagina posterior
			mais = pagina + 1;
			numeroDePagina = (int)Math.ceil((double)totalResultado/(double)maxResultado);
		}
		
		if ((pagina-pivo) < 1 ) {
	          anterior = 1;
		} else {
	          anterior = pagina-pivo;
		}
	    
		if ((pagina+pivo) > numeroDePagina ) {
	          posterior = numeroDePagina;
		} else {
	          posterior = pagina + pivo;
		}
		
		request.setAttribute("maxResultado",maxResultado);
		request.setAttribute("qtdIdPorPag",qtdIdPorPag);
		request.setAttribute("pivo",pivo);
		request.setAttribute("pagina",pagina);
		request.setAttribute("anterior",anterior);
		request.setAttribute("posterior",posterior);
		request.setAttribute("menos",menos);
		request.setAttribute("mais",mais);
		request.setAttribute("numeroDePagina",numeroDePagina);
		request.setAttribute("messages",messages);
		request.setAttribute("totalResultado", totalResultado);
		request.setAttribute("forum", forum);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_LIST_MESSAGES_FORUM_ACTIVITY);
	}
	
	public ActionForward showViewNewAnswerForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idForum = Integer.parseInt(request.getParameter("idForum"));
		
		Forum forum = facade.getForumById(idForum);
		Module module = forum.getModule();
		
		AccessInfo user = (AccessInfo)request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());

		Person person = user.getPerson();

		request.setAttribute("module", module);
		request.setAttribute("forum", forum);
		request.setAttribute("person", person);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_ANSWER_FORUM_ACTIVITY);
	}
	
	public ActionForward answerForumActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			DynaActionForm myForm = (DynaActionForm) form;

			int idForum = Integer.parseInt(request.getParameter("idForum"));
			Forum forum = facade.getForumById(idForum);
			
			AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
			AccessInfo user = facade.searchUserById(loggedUser.getId());

			Message msg = new Message();
			msg.setBody(myForm.getString("answerBody"));

			Date date = new Date();

			msg.setDate(date);
			msg.setAuthor(user.getPerson());

			forum.getMessages().add(msg);

			// se for a primeira resposta set a data como a atual.
			if (forum.getMessages().size() < 2) {
				forum.setCreationDate(date);
			}

			facade.flush();
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
}
