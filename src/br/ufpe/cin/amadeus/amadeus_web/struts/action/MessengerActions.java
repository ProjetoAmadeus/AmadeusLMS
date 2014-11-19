package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.util.MessengerMessageMirror;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Classe quem centraliza todas as ações referentes as mensagens vindas do
 * sistema de mensagem assíncrono.
 * 
 * @author Nailson Cunha
 * 
 */
public class MessengerActions extends SystemActions {
	
	private final String FORWARD_SHOW_VIEW_ALL_MESSENGER_MESSAGES = "fshowViewAllMessengerMessages";

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("messenger.saveMessage", "saveMessage");
		map.put("messenger.getUnreadMessages", "getUnreadMessages");
		map.put("messenger.replyMessage", "replyMessage");
		map.put("messenger.setMessageAsRead", "setMessageAsRead");
		map.put("messenger.deleteMessage", "deleteMessage");
		map.put("messenger.sendMessageToAll", "sendMessageToAll");
		map.put("messenger.replyMessageToAll", "replyMessageToAll");
		map.put("messenger.showViewAllMessengerMessages", "showViewAllMessengerMessages");

		return map;
	}

	/**
	 * Action que monta a mensagem e a repassa para a persistência.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null pois a requisição é via ajax
	 * @throws Exception
	 */
	public ActionForward saveMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Person from = this.getUserInRequest(request);
		Person to = facade.getPersonByID(Integer.parseInt(request
				.getParameter("para")));

		MessengerMessage message = prepareMessage(
				request.getParameter("assunto"),
				request.getParameter("txtarea-mensagem"), from, to, 0, false);

		facade.saveMessengerMessage(message, from, to);

		return null;
	}

	/**
	 * Método que retorna o json com a as mensagens não lidas de um determinado
	 * usuário
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUnreadMessages(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Person person = this.getUserInRequest(request);
		List<MessengerMessage> lm = facade.getAllUnreadByPerson(person);

		if (lm == null || lm.isEmpty()) // Se não houver mensagens não lidas,
			return null;				// pare por aqui!
										

		List<MessengerMessageMirror> mmm = new ArrayList<MessengerMessageMirror>();
		String[] str = request.getParameterValues("ignoreIds[]");
		int courseId = Integer.parseInt(request.getParameter("courseId"));

		if (str != null || str.length > 0) {
			List<Integer> lint = new ArrayList<Integer>();
			for (String s : str) {
				lint.add(Integer.parseInt(s));
			}

			for (MessengerMessage m : lm) {
				if (!lint.contains(m.getId()))
					mmm.add(prepareMessageMirror(m, courseId));
			}
		} else
			for (MessengerMessage m : lm)
				mmm.add(prepareMessageMirror(m, courseId));

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(messagesToJson(mmm));

		return null;
	}

	/**
	 * Método que serializa um List de MessengerMessageMirror para uma string
	 * json
	 * 
	 * @param mmm
	 *            A lista a ser serializada
	 * @return Uma string representando a lista no formato json
	 */
	private String messagesToJson(List<MessengerMessageMirror> mmm) {
		Gson gson = new Gson();
		Type tipo = new TypeToken<List<MessengerMessageMirror>>() {
		}.getType();
		return gson.toJson(mmm, tipo);
	}

	/**
	 * Método que espelha um MessengerMessage pra um MessengerMessageMirror, recebe também um int como
	 * id do curso. Usada para o caso de reply-to-all
	 * @param message A mensagem a ser espelhada.
	 * @param courseId o Id do curso corrente
	 * @return A mensagem espelhada
	 */
	private MessengerMessageMirror prepareMessageMirror(MessengerMessage message, int courseId) {
		
		MessengerMessageMirror messageMirror = new MessengerMessageMirror();
		messageMirror.setTitle(message.getTitle());
		messageMirror.setContent(message.getContent());
		messageMirror.setDate(message.getDate());
		messageMirror.setId(message.getId());
		messageMirror.setSender(message.getSender().getName());
		messageMirror.setSenderId(message.getSender().getId());
		messageMirror.setToAll(message.isToAll());
		messageMirror.setCourseId(courseId);
		
		return messageMirror;
	}

	/**
	 * Método que salva uma messagem como resposta a outra. Recebe na requisição
	 * o Id da mensagem a qual está sendo respondida
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward replyMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Person from = this.getUserInRequest(request);
		Person to = facade.getPersonByID(Integer.parseInt(request
				.getParameter("senderId")));

		MessengerMessage message = prepareMessage(
				request.getParameter("messageTitle"),
				request.getParameter("messageContent"), from, to,
				Integer.parseInt(request.getParameter("messageId")),
				false);

		facade.saveMessengerMessage(message, from, to);

		return null;
	}

	/**
	 * Método que marca a mensagem como lida
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setMessageAsRead(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int idMensagemLida = Integer
				.parseInt(request.getParameter("messageId"));
		MessengerMessage message = facade
				.getMessengerMessageById(idMensagemLida);
		message.setRead(true);

		facade.saveOnlyMessage(message);

		return null;
	}

	/**
	 * Método que exlui uma determinada mensagem, baseada em seu ID. Inlcui uma
	 * restrição que só quem apaga a mensagem é quem a recebeu.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (!request.getMethod().equals("POST")) {
			System.out.println("Não pode apagar essa mensagem via GET!");
			return null;
		}

		Person receiver = this.getUserInRequest(request);

		int messageId = Integer.parseInt(request.getParameter("messageId"));
		MessengerMessage message = facade.getMessengerMessageById(messageId);

		if (receiver.getId() == message.getReceiver().getId())
			facade.deleteMessengerMessage(message);
		else
			System.out.println("Não pode apagar essa mensagem!");

		return null;
	}

	/**
	 * Metodo que cria a mensagem e seus relacionamentos e a retorna para
	 * persistência
	 * 
	 * @param title
	 *            O título ou assunto da mensagem
	 * @param content
	 *            O conteudo da mensagem
	 * @param from
	 *            O rementente da mensagem
	 * @param to
	 *            O destinatário da mensagem
	 * @param respondTo
	 *            um inteiro que representa a mensagem respondida. Passar 0 caso
	 *            não esteja respondendo nenhuma
	 * @return A mensagem pronta.
	 */
	private MessengerMessage prepareMessage(String title, String content,
			Person from, Person to, int respondTo, boolean toAll) {
		MessengerMessage message = new MessengerMessage();
		message.setTitle(title);
		message.setContent(content);
		message.setDate(new Date());
		message.setResponseTo(respondTo);
		message.setSender(from);
		message.setReceiver(to);
		message.setToAll(toAll);
		return message;
	}
	
	/**
	 * Método que envia uma MessengerMessage recebida, a todos os participantes de um curso
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendMessageToAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		Course course = facade.getCoursesById(Integer
				.parseInt(request.getParameter("courseId")));
		String assunto = "All: " + request.getParameter("assunto-to-all");
		String content = request.getParameter("content-to-all");
		
		Person from = this.getUserInRequest(request);
		
		List<Person> personInCourse = facade.listStudentsByCourse(course);
		personInCourse.addAll(facade.listTeachersByCourse(course));
		
		this.finalizeSendToAll(personInCourse, from, assunto, content, 0);		
				
		return null;
	}
	
	/**
	 * Método que faz o processamento final de envio de mensagens para todos. Tanto serve para mensagens
	 * iniciais (que não respondem nenhuma) quanto para mensagens de resposta a todos.
	 * 
	 * @param personInCourse A lista de pessoas no curso
	 * @param from A pessoa que está respondendo
	 * @param assunto O título da mensagem
	 * @param content O conteúdo da mensagem
	 * @param respondTo O id da mensagem que está sendo respondida
	 */
	private void finalizeSendToAll(List<Person> personInCourse, Person from,
			String assunto, String content, int respondTo) {
		
		for(Person p : personInCourse){
			if(p.getId() != from.getId()){
				MessengerMessage message = prepareMessage(assunto, content, from, p, respondTo, true);
				facade.saveMessengerMessage(message, from, p);
			}
		}
		
	}

	/**
	 * Método que retorna a Person que está na sessão da requisição HTTP
	 * @param request um HttpServletRequest
	 * @return a pessoa - Person -  encontrada
	 */
	private Person getUserInRequest(HttpServletRequest request){
		AccessInfo accessInfo = null;
		if (SystemActions.isLoggedUser(request)) {
			accessInfo = (AccessInfo) request.getSession().getAttribute("user");
			accessInfo = facade.searchUserById(accessInfo.getId());
		}
		Person person = facade.getPersonByLogin(accessInfo.getLogin());
		return person;
	}
	
	/**
	 * Método que envia a resposta de uma mensagem a todos os usuarios do curso.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward replyMessageToAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (!request.getMethod().equals("POST")) {
			System.out.println("Não pode responder GET!");
			return null;
		}
		
		Person from = this.getUserInRequest(request);
		String assunto = request.getParameter("messageTitle");
		String content = request.getParameter("messageContent");
		int respondTo = Integer.parseInt(request.getParameter("messageId"));
		
		Course course = facade.getCoursesById(Integer
				.parseInt(request.getParameter("courseId")));
		List<Person> personInCourse = facade.listStudentsByCourse(course);
		personInCourse.addAll(facade.listTeachersByCourse(course));
		
		this.finalizeSendToAll(personInCourse, from, assunto, content, respondTo);	
		
		return null;
	}

	/**
	 * Método que prepara e exibe a view Show All Messenger Messages.
	 * Exibe uma página que contém todas as mensagens de um determinado usuário
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showViewAllMessengerMessages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward forward = null;
		
		Person person = this.getUserInRequest(request);
		if(person != null){
			
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());
			
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			Course course = facade.getCoursesById(courseId);
			boolean canRegisterUser = false;
			
			if (facade.canRegisterUser(user, course)) {
				canRegisterUser = true;
			}
			
			Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);			
			SystemActions.setMenuPermissionsForUserInRequest(request, course);
			
			List<Person> teachers = facade.getTeachersByCourse(course);
			List<Person> assistants = facade.listAssistantsByCourse(course);
			List<Person> participants = facade.listStudentsByCourse(course); //added by Nailson
			List<MessengerMessage> messagesUnread = facade.getAllMessengerMessageByPerson(person);
			
			request.setAttribute("userRoleInCourse", ( userRoleInCourse != null) ? userRoleInCourse.getRoleType() : null );
			request.setAttribute("canRegisterUser", canRegisterUser);
			request.setAttribute("course", course);
			request.setAttribute("teachers", teachers);
			request.setAttribute("assistants", assistants);
			request.setAttribute("participants", participants); //added by Nailson
			request.setAttribute("messagesUnread", messagesUnread);
			request.setAttribute("pageSeeAll", true);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_ALL_MESSENGER_MESSAGES);
		}
		return forward;
	}
	

}
