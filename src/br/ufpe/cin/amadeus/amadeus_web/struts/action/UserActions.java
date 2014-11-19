/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco

Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS

O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.

Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.

Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 **/

package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.GNOME.Accessibility.AccessUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Image;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.OnlineUser;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Resume;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Thumbnail;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidCurrentPasswordException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidSocialCredencialsException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.amadeus.amadeus_web.permissions.register.UserPermissions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video.VideoChatAction;
import br.ufpe.cin.amadeus.amadeus_web.util.MailSender;

public class UserActions extends SystemActions {

	private final String FORWARD_SUCCESS = "success";
	private final String FORWARD_SHOW_VIEW_INSERT_USER = "fShowViewInsertUser";
	private final String FORWARD_SHOW_VIEW_EDIT_PASSWORD = "fShowViewEditPassword";
	private final String FORWARD_SHOW_VIEW_EDIT_USER = "fViewEditUser";
	private final String FORWARD_SHOW_VIEW_PUBLIC_DATA = "fShowViewPublicData";
	private final String FORWARD_SHOW_VIEW_MY_PROFILE = "fShowViewMyProfile";
	private final String FORWARD_SHOW_VIEW_DEFAULT_PHOTO = "fShowDefaultPhoto";
	private final String FORWARD_SHOW_VIEW_CLASS_MATES = "fShowViewClassMates";
	private final String FORWARD_SHOW_VIEW_TEACHING_REQUEST = "fShowViewTeachingRequest";
	private final String FORWARD_SHOW_VIEW_ASSISTANCE_REQUEST = "fShowViewAssistanceRequest";
	private final String FORWARD_SHOW_VIEW_ADMIN_PENDING_TASKS = "fShowViewAdminPendingTasks";
	private final String FORWARD_SHOW_VIEW_TEACHER_PENDING_TASKS = "fShowViewTeacherPendingTasks";
	private final String FORWARD_SHOW_VIEW_STUDENT_PENDING_TASKS = "fShowViewStudentPendingTasks";
	private final String FORWARD_SHOW_VIEW_ONLINE_USERS = "fShowViewOnlineUsers";
	private final String FORWARD_SHOW_VIEW_INTEGRATION_SOCIAL_NETWORKS = "fShowViewIntegrationSocialNetworks";

	private final String FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS = "fShowViewAllUsersInManagerUsers";
	private final String FORWARD_SHOW_VIEW_USER_NEW_IN_MANAGER_USERS = "fShowViewUserNewInManagerUsers";
	private final String FORWARD_SHOW_VIEW_EDIT_USER_IN_MANAGER_USERS = "fShowViewEditUserInManagerUsers";
	private final String FORWARD_SHOW_VIEW_USER_PROFILE_IN_MANAGER_USERS = "fShowViewUserProfileInManagerUsers";
	private final String FORWARD_SHOW_VIEW_SEND_EMAIL_IN_MANAGER_USERS = "fShowViewSendMailInManagerUsers";
	private final String FORWARD_SHOW_VIEW_ALL_COURSES_IN_MANAGER_USERS = "fShowViewAllCoursesInManagerUsers";

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("general.register", "insertUser");
		map.put("general.submit", "logonForm");
		map.put("remindPassword.remind", "remindPassword");
		map.put("editUserForm.edit", "editUser");
		map.put("general.cancel", "cancelEdition");
		map.put("editPassword.edit", "editPassword");
		map.put("teachingRequest.sendRequest", "teachingRequest");
		map.put("general.leave", "signOut");
		map.put("approveTeachingRequest", "approvedTeachingRequest");
		map.put("disapproveTeachingRequest", "disapprovedTeachingRequest");
		map.put("approveAssistanceRequest", "approveAssistanceRequest");
		map.put("disapproveAssistanceRequest", "disapprovedAssistanceRequest");
		map.put("assistanceRequest.sendRequest", "assistanceRequest");
		map.put("editUser.heading", "viewEditUser");
		map.put("editUser.reportError", "reportError");
		map.put("integrationSocialNetworks.integrate", "integrationSocialNetworks");

		map.put("user.showViewInsertUser", "showViewInsertUser");
		map.put("user.showViewEditPassword", "showViewEditPassword");
		map.put("user.showViewMyProfile", "showViewMyProfile");
		map.put("user.showViewPublicData", "showViewPublicData");
		map.put("user.showPhoto", "showPhoto");
		map.put("user.showViewClassMates", "showViewClassMates");
		map.put("user.showViewTeachingRequest", "showViewTeachingRequest");
		map.put("user.showViewAssistanceRequest", "showViewAssistanceRequest");
		map.put("user.showViewPendingTasks", "showViewPendingTasks");
		map.put("user.countOnlineUser", "countOnlineUser");
		map.put("user.userStatus", "userStatus");
		map.put("user.showViewOnlineUsers", "showViewOnlineUsers");
		map.put("user.showViewIntegrationSocialNetworks", "showViewIntegrationSocialNetworks");

		// MAP MANAGER USERS
		map.put("user.showViewAllUsersInManagerUsers", "showViewAllUsersInManagerUsers");
		map.put("user.showViewAllCoursesInManagerUsers", "showViewAllCoursesInManagerUsers");
		map.put("user.showViewUserNewInManagerUsers", "showViewUserNewInManagerUsers");
		map.put("user.showViewEditUserInManagerUsers", "showViewEditUserInManagerUsers");
		map.put("user.userNewInManagerUsers", "userNewInManagerUsers");
		map.put("user.userEditInManagerUsers", "userEditInManagerUsers");
		map.put("user.showViewUserProfileInManagerUsers", "showViewUserProfileInManagerUsers");
		map.put("user.showViewSendMailInManagerUsers", "showViewSendMailInManagerUsers");
		map.put("user.sendMailInManagerUsers", "sendMailInManagerUsers");
		map.put("user.verifyEmail", "verifyEmail");

		return map;
	}

	public ActionForward showViewInsertUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.webSettings.isSecurityAutoSigning()) {
			forward = mapping.findForward(FORWARD_SHOW_VIEW_INSERT_USER);
		} else {
			forward = SystemActions.showViewAccessDenied(mapping, form,
					request, response);
		}

		return forward;
	}
	
	

	public ActionForward viewEditUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ActionForward actionForward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");
			accessInfo = facade.searchUserById(accessInfo.getId());

			List<LabelValueBean> years = new ArrayList<LabelValueBean>();

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat();
			formatador.applyPattern("yyyy");
			int year = Integer.parseInt(formatador.format(data));
			for (int x = year; x >= year - 100; x--) {
				years.add(new LabelValueBean(String.valueOf(x), String
						.valueOf(x)));
			}

			Person person = accessInfo.getPerson();

			HashMap<String, String> userProfile = new HashMap<String, String>();

			userProfile.put("id", String.valueOf(person.getId()));
			userProfile.put("name", person.getName());
			userProfile.put("city", person.getCity());
			userProfile.put("state", person.getState());
			userProfile.put("cpf", person.getCpf());
			userProfile.put("phoneNumber", person.getPhoneNumber());

			if (person.getBirthDate() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date birthDate = person.getBirthDate();
				userProfile.put("birthDate", formatter.format(birthDate));
			} else {
				person.setBirthDate(null);
			}

			userProfile.put("gender", String.valueOf(person.getGender()));
			userProfile.put("email", person.getEmail());
			if (person.getResume() != null) {
				userProfile.put("degree", person.getResume().getDegree());
				userProfile.put("instituition", person.getResume()
						.getInstituition());
				userProfile.put("description", person.getResume()
						.getDescription());
				userProfile.put("year",
						String.valueOf(person.getResume().getYear()));
			} else {
				userProfile.put("degree", "");
				userProfile.put("instituition", "");
				userProfile.put("description", "");
				userProfile.put("year", "");
			}

			ProfileType loggedProfile = accessInfo.getTypeProfile();

			boolean canRequestTeaching = false;

			if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR)
					&& facade.canRequestTeaching(accessInfo.getPerson())) {
				canRequestTeaching = true;
			}

			request.setAttribute("userProfile", userProfile);
			request.setAttribute("years", years);
			request.setAttribute("canRequestTeaching", canRequestTeaching);

			actionForward = mapping.findForward(FORWARD_SHOW_VIEW_EDIT_USER);

		} else {
			actionForward = this.showViewWelcome(mapping, form, request,
					response);
		}

		return actionForward;
	}

	public ActionForward insertUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(">>>>>>>>>>>>>>> insertUser");

		ActionForward forward = mapping.getInputForward();
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		Person person = new Person();
		person.setName(myForm.getString("name"));
		person.setEmail(myForm.getString("email"));

		AccessInfo accessInfo = new AccessInfo();
		accessInfo.setLogin(myForm.getString("login"));
		accessInfo.setPassword(myForm.getString("password"));
		accessInfo.setTypeProfile(ProfileType.STUDENT);
		accessInfo.setPerson(person);

		person.setAccessInfo(accessInfo);

		try {
			facade.insertPerson(person);
			facade.confirmRegistry(person.getAccessInfo());
			accessInfo = facade.logon(accessInfo);

			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", accessInfo);
		} catch (InvalidUserException e1) {
			messages.add("confirmation", new ActionMessage(e1.getMessage()));
		} catch (InvalidLogonException e2) {
			messages.add("invalidLogin", new ActionMessage(
			"errors.auth.invalid"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			myForm.set("password", "");
			myForm.set("passwordConfirmation", "");
		} else {
			forward = this.showViewMenu(mapping, myForm, request, response);
			//TODO - LOG - Login - OK
			Log log = SystemActions.getLogUser(request);
			log.setCodigo(Log.LOG_CODIGO_LOGIN);
			this.facade.saveLog(log);
		}

		return forward;
	}

	public ActionForward remindPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		DynaActionForm dyna = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		String email = dyna.getString("email");

		if (email.equals("") || email.length() < 2) {
			return mapping.getInputForward();
		}
		if (!facade.existEmail(email)) {
			messages.add("invalidEmail", new ActionMessage(
			"errors.user.notRegistered"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		facade.remindPassword(email);

		return this.showViewWelcome(mapping, form, request, response);
	}

	public ActionForward logonForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ActionForward aForward = null;
		DynaActionForm dyna = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		String login = dyna.getString("login");
		String password = dyna.getString("password");

		boolean fieldsOk = false;

		// fields validation
		if (login != null && password != null) {
			if ((login.length() < 30) && (login.length() > 2)
					&& (password.length() > 2) && (password.length() < 16)) {

				fieldsOk = true;

				for (int i = 0; i < login.length(); i++) {
					if (!Character.isLetterOrDigit(login.charAt(i))
							&& login.charAt(i) != '@' && login.charAt(i) != '.'
								&& login.charAt(i) != '_') {
						fieldsOk = false;
						break;
					}
				}
			}
		}

		if (fieldsOk) {
			try {
				AccessInfo accessInfo = new AccessInfo();
				accessInfo.setLogin(login);
				accessInfo.setPassword(password);

				accessInfo = facade.logon(accessInfo);
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", accessInfo);
				aForward = mapping.findForward("success");
				
				//TODO - LOG - Login - OK
				Log log = SystemActions.getLogUser(request);
				log.setCodigo(Log.LOG_CODIGO_LOGIN);
				this.facade.saveLog(log);
				
			} catch (InvalidLogonException e1) {
				messages.add("invalidLogin", new ActionMessage(
				"errors.auth.invalid"));
				request.getSession().removeAttribute("user");
			}
		} else
			messages.add("invalidLogin", new ActionMessage(
			"errors.auth.invalid"));

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			dyna.set("password", "");
			aForward = mapping.getInputForward();
		}

		return aForward;
	}

	public ActionForward cancelEdition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		return mapping.findForward("cancel");
	}

	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			ActionMessages messages = new ActionMessages();

			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");

			Person person = accessInfo.getPerson();
			
			DynaActionForm dynaForm = (DynaActionForm) form;

			person.setName(dynaForm.getString("name"));
			person.setCity(dynaForm.getString("city"));
			person.setState(dynaForm.getString("state"));
			person.setCpf(dynaForm.getString("cpf"));
			person.setPhoneNumber(dynaForm.getString("phoneNumber"));
			//person.setEmail(dynaForm.getString("email"));
			
			Date dataAnivesario = null;
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			if (!dynaForm.getString("birthDate").trim().equals("")) {
				dataAnivesario = formatter.parse((String) dynaForm.get("birthDate"));
				if(dataAnivesario.compareTo(getNowDateTime()) > 0){
					messages.add("error", new ActionMessage("errors.date.birthDataIsAfterNow"));
				}else{
					person.setBirthDate(dataAnivesario);
				}
			} else {
				person.setBirthDate(null);
			}

			if (!dynaForm.getString("gender").trim().equals("")) {
				person.setGender(((String) dynaForm.getString("gender"))
						.charAt(0));
			}

			Resume resume = person.getResume();

			if (resume == null) {
				resume = new Resume();
				person.setResume(resume);
			}

			if (!dynaForm.getString("degree").equals(""))
				resume.setDegree(dynaForm.getString("degree"));
			if (!dynaForm.getString("instituition").trim().equals(""))
				resume.setInstituition(dynaForm.getString("instituition"));
			if (!dynaForm.getString("description").trim().equals(""))
				resume.setDescription(dynaForm.getString("description"));
			if (!dynaForm.getString("year").trim().equals("")){
				GregorianCalendar anoNascimento = new GregorianCalendar();
				if(dataAnivesario != null){
					anoNascimento.setTime(dataAnivesario);
					if(Integer.parseInt(dynaForm.getString("year")) < anoNascimento.get(GregorianCalendar.YEAR)){
						messages.add("error", new ActionMessage("errors.date.degreeBeforeBirth"));

					}else{
						resume.setYear(Integer.parseInt(dynaForm.getString("year")));
					}
				}else {
					resume.setYear(Integer.parseInt(dynaForm.getString("year")));

				}
				
				
			}

			FormFile myFile = (FormFile) dynaForm.get("image");

			// photo validation
			try {
				if (!myFile.getFileName().equals("")) {
					if (myFile.getFileSize() < 500000) { // if the photo has
						// less than 500KB
						Image image = new Image();
						Thumbnail thumb = new Thumbnail();
						byte[] photo = thumb.resize(myFile.getFileData());
						image.setPhoto(photo);
						person.setImage(image);
					} else
						messages.add("requestError", new ActionMessage(
						"errors.photoSize"));
				}
			} catch (Exception e) {
				messages.add("editError", new ActionMessage(e.getMessage()));
			}
			System.out.println("Novo: "+dynaForm.getString("email")+" Velho: "+person.getEmail());
			if (!dynaForm.getString("email").equals(person.getEmail())) {
				if (facade.existEmail(dynaForm.getString("email"))) {
					messages.add("error", new ActionMessage(
					"errors.email.alreadyExists"));
				} else {
					person.setEmail(dynaForm.getString("email"));
				}
			}

			if (messages.isEmpty()) {
				facade.editUser(person);
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", accessInfo);

				response.sendRedirect("user.do?method=showViewMyProfile");
			} else {
				saveErrors(request, messages);
				forward = this.viewEditUser(mapping, dynaForm, request,
						response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	private Date getNowDateTime() {
		Date date = new Date();
		return date;
	}

	public ActionForward signOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (SystemActions.isLoggedUser(request)) {
			
			//TODO - LOG - Logout - OK
			Log log = SystemActions.getLogUser(request);
			
			try {
				VideoChatAction.logoffChatMain(mapping, form, request, response);
				
				
			} catch (Exception e) {
				log.setCodigo(Log.LOG_CODIGO_LOGOUT);
				System.out.println("signOut Chat");
			}
			

			response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			response.setDateHeader("Expires", 0);
			request.getSession().invalidate();
			
			if(log.getCodigo() != null)
			{
				this.facade.saveLog(log);
				System.out.println("registrou o log de logout.");
			}
		}
		
		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward editPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		AccessInfo userAccessInfo = (AccessInfo) request.getSession()
		.getAttribute("user");

		String currentPassword = myForm.getString("currentPassword");
		String newPassword = myForm.getString("newPassword");
		String newPasswordConfirmation = myForm
		.getString("newPasswordConfirmation");

		try {
			facade.editPassword(userAccessInfo, currentPassword, newPassword,
					newPasswordConfirmation);
		} catch (InvalidCurrentPasswordException e) {
			messages.add("currentPassword", new ActionMessage(
			"errors.invalidCurrentPassword"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		myForm.initialize(mapping);

		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward teachingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionMessages messages = new ActionMessages();
		DynaActionForm dvForm = (DynaActionForm) form;

		AccessInfo accessInfo = facade.searchUserById(((AccessInfo) request
				.getSession().getAttribute("user")).getId());

		Resume resume = new Resume();
		resume.setDegree((String) dvForm.get("degree"));
		resume.setInstituition((String) dvForm.get("instituition"));
		resume.setDescription((String) dvForm.get("description"));
		resume.setYear((Integer) dvForm.get("year"));
		resume.setPerson(accessInfo.getPerson());

		String interest = (String) dvForm.get("interest");

		Date today = new Date();
		Calendar cToday = new GregorianCalendar();
		cToday.setTime(today);

		if (cToday.get(Calendar.YEAR) < resume.getYear()) {
			request.setAttribute("resume", resume);

			messages.add("requestError", new ActionMessage("errors.grad.year"));
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		accessInfo.getPerson().setResume(resume);

		try {
			facade.requestTeacher(accessInfo.getPerson().getResume(), interest);
		} catch (Exception e) {
			throw e;
		}

		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward showViewPendingTasks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {

			AccessInfo user = (AccessInfo) request.getSession().getAttribute(
			"user");
			user = facade.searchUserById(user.getId());

			ProfileType profileType = user.getTypeProfile();

			if (profileType == ProfileType.ADMIN) {
				boolean isProfileAdmin = false;

				if (profileType == ProfileType.ADMIN) {
					isProfileAdmin = true;
				}

				List<UserRequest> requests = facade.getPossibleTeachers();
				List<UserRequest> requestsAssistants = facade
				.getPossibleAssistants(user);

				requests.addAll(requestsAssistants);

				request.setAttribute("isProfileAdmin", isProfileAdmin);
				request.setAttribute("requests", requests);
				request.setAttribute("requestsSize", requests.size());

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_ADMIN_PENDING_TASKS);
			} else if (profileType == ProfileType.PROFESSOR) {
				List<UserRequest> requests = facade.getPossibleAssistants(user);
				request.setAttribute("requests", requests);
				request.setAttribute("requestsSize", requests.size());

				/*
				 * Obtem Tarefas pendentes se o professor for aluno de algum
				 * curso
				 */
				List<Object> tasks = facade.getStudentPendingTasks(user
						.getPerson());

				List<MaterialRequest> tasksMaterialRequest = new ArrayList<MaterialRequest>();
				List<Evaluation> tasksEvaluation = new ArrayList<Evaluation>();

				for (Object task : tasks) {
					if (task instanceof MaterialRequest) {
						tasksMaterialRequest.add((MaterialRequest) task);
					} else if (task instanceof Evaluation) {
						tasksEvaluation.add((Evaluation) task);
					}
				}

				AccessInfo loggedUser = (AccessInfo) request.getSession()
				.getAttribute("user");
				loggedUser = facade.searchUserById(loggedUser.getId());

				ProfileType loggedProfile = loggedUser.getTypeProfile();

				boolean canRequestTeaching = false;

				if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR)
						&& facade.canRequestTeaching(loggedUser.getPerson())) {
					canRequestTeaching = true;
				}

				request.setAttribute("tasksMaterialRequest",
						tasksMaterialRequest);
				request.setAttribute("tasksEvaluation", tasksEvaluation);
				request.setAttribute("canRequestTeaching", canRequestTeaching);

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_TEACHER_PENDING_TASKS);
			} else if (profileType == ProfileType.STUDENT) {

				List<Object> tasks = facade.getStudentPendingTasks(user
						.getPerson());

				List<MaterialRequest> tasksMaterialRequest = new ArrayList<MaterialRequest>();
				List<Evaluation> tasksEvaluation = new ArrayList<Evaluation>();

				for (Object task : tasks) {
					if (task instanceof MaterialRequest) {
						tasksMaterialRequest.add((MaterialRequest) task);
					} else if (task instanceof Evaluation) {
						tasksEvaluation.add((Evaluation) task);
					}
				}

				AccessInfo loggedUser = (AccessInfo) request.getSession()
				.getAttribute("user");
				loggedUser = facade.searchUserById(loggedUser.getId());

				ProfileType loggedProfile = loggedUser.getTypeProfile();

				boolean canRequestTeaching = false;

				if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR)
						&& facade.canRequestTeaching(loggedUser.getPerson())) {
					canRequestTeaching = true;
				}

				request.setAttribute("tasksMaterialRequest",
						tasksMaterialRequest);
				request.setAttribute("tasksEvaluation", tasksEvaluation);
				request.setAttribute("canRequestTeaching", canRequestTeaching);

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_STUDENT_PENDING_TASKS);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward assistanceRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionMessages messages = new ActionMessages();
		DynaActionForm dvForm = (DynaActionForm) form;

		AccessInfo userLogged = (AccessInfo) request.getSession().getAttribute(
		"user");
		AccessInfo user = facade.searchUserById(userLogged.getId());

		String degree = (String) dvForm.get("degree");
		String institution = (String) dvForm.get("instituition");
		String description = (String) dvForm.get("description");
		String interest = (String) dvForm.get("interest");
		Integer year = (Integer) dvForm.get("year");
		Integer courseId = (Integer) dvForm.get("courseId");
		request.setAttribute("courseId", Integer.toString(courseId));

		// valida��o da data
		Date today = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(today);

		if (c.get(Calendar.YEAR) < year) {

			Resume resume = new Resume();
			resume.setDegree(degree);
			resume.setInstituition(institution);
			resume.setDescription(description);
			resume.setYear(year);

			request.removeAttribute("resume");
			request.setAttribute("resume", resume);

			messages.add("requestError", new ActionMessage("errors.grad.year"));
			saveErrors(request, messages);
			return mapping.getInputForward();

		}

		if (user.getPerson().getResume() == null) {
			user.getPerson().setResume(new Resume());
		}

		user.getPerson().getResume().setDegree(degree);
		user.getPerson().getResume().setInstituition(institution);
		user.getPerson().getResume().setDescription(description);
		user.getPerson().getResume().setYear(year);

		Course course = facade.getCoursesById(courseId);
		request.setAttribute("course", course);

		try {
			// persistencia de userRequest
			facade.requestAssistance(course, user.getPerson().getResume(),
					interest);
		} catch (Exception e) {
			throw e;
		}

		return mapping.findForward(FORWARD_SUCCESS);
	}

	public ActionForward approveAssistanceRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int userRequestId = Integer.parseInt(request
				.getParameter("userRequestId"));

		try {

			UserRequest userRequest = facade
			.searchUserRequestById(userRequestId);
			facade.approveAssistanceRequest(userRequest,
					userRequest.getCourse());
			facade.sendEmailApproveAssistanceRequest(userRequest.getPerson()
					.getEmail());

		} catch (Exception e) {
			throw e;
		}

		return null;

	}

	public ActionForward disapprovedAssistanceRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// falta a justificativa de reprovacao
		String justification = (String) request.getParameter("justification");
		if (justification == null || justification.trim().length() == 0) {

			ActionMessages messages = new ActionMessages();

			messages.add("justification", new ActionMessage(
			"errors.missingJustification"));
			saveErrors(request, messages);

			return mapping.getInputForward();

		}

		int userRequestId = Integer.parseInt(request
				.getParameter("userRequestId"));

		try {

			UserRequest userRequest = facade
			.searchUserRequestById(userRequestId);
			facade.disapprovedAssistanceRequest(userRequest);
			facade.sendEmailDisapproveAssistanceRequest(userRequest.getPerson()
					.getEmail(), justification);

		} catch (Exception e) {
			throw e;
		}

		return null;

	}

	public ActionForward approvedTeachingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AccessInfo user = (AccessInfo) request.getSession()
		.getAttribute("user");
		user = facade.searchUserById(user.getId());

		int userRequestId = Integer.parseInt(request
				.getParameter("userRequestId"));

		try {

			UserRequest userRequest = facade
			.searchUserRequestById(userRequestId);
			if (!userRequest.isTeachingRequest()) {
				facade.approveAssistanceRequest(userRequest,
						userRequest.getCourse());
				facade.sendEmailApproveAssistanceRequest(userRequest
						.getPerson().getEmail());
			} else {
				facade.approveTeachingRequest(userRequest);
			}
			List<UserRequest> requests = facade.getPossibleTeachers();
			List<UserRequest> requestsAssistants = facade
			.getPossibleAssistants(user);
			request.removeAttribute("requests");
			if (requests != null) {
				requests.addAll(requestsAssistants);
				request.setAttribute("requests", requests);
			} else {
				request.setAttribute("requests", requestsAssistants);
			}
			facade.sendEmailApproveTeachingRequest(userRequest.getPerson()
					.getEmail());

		} catch (Exception e) {
			throw e;
		}

		return null;

	}

	public ActionForward disapprovedTeachingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// falta a justificativa de reprovacao
		String justification = (String) request.getParameter("justification");
		if (justification == null || justification.trim().length() == 0) {

			ActionMessages messages = new ActionMessages();

			messages.add("justification", new ActionMessage(
			"errors.missingJustification"));
			saveErrors(request, messages);

			return mapping.getInputForward();

		}

		int userRequestId = Integer.parseInt(request
				.getParameter("userRequestId"));

		try {

			UserRequest userRequest = facade
			.searchUserRequestById(userRequestId);
			if (!userRequest.isTeachingRequest()) {
				facade.disapprovedAssistanceRequest(userRequest);
				facade.sendEmailDisapproveAssistanceRequest(userRequest
						.getPerson().getEmail(), justification);
			} else {
				facade.disapprovedTeachingRequest(userRequest);
				facade.sendEmailDisapproveTeachingRequest(userRequest
						.getPerson().getEmail(), justification);
			}

		} catch (Exception e) {
			throw e;
		}

		return null;

	}

	public ActionForward reportError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws MessagingException {

		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String contentError = request.getParameter("contentError");

		Person person = new Person();
		person.setEmail(email);

		MailSender.sendMail(person, subject, contentError);

		return null;
	}

	public ActionForward showViewEditPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");
			accessInfo = facade.searchUserById(accessInfo.getId());

			boolean isTeacher = true;

			if ((accessInfo.getTypeProfile() == ProfileType.STUDENT)) {
				isTeacher = false;
			}

			boolean canRequestTeaching = facade.canRequestTeaching(accessInfo
					.getPerson());

			request.setAttribute("isTeacher", isTeacher);
			request.setAttribute("canRequestTeaching", canRequestTeaching);			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_EDIT_PASSWORD);
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form,
					request, response);
		}

		return forward;
	}

	public ActionForward showViewPublicData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (!SystemActions.isLoggedUser(request)) {
			Integer userId = Integer.parseInt(request.getParameter("userId"));

			AccessInfo userProfile = facade.searchUserById(userId);

			String degree = null;

			if (userProfile.getPerson().getResume() != null) {
				degree = userProfile.getPerson().getResume().getDegree();
			}

			request.setAttribute("webSettings", SystemActions.webSettings);
			request.setAttribute("degree", "editUserForm.degree." + degree);
			request.setAttribute("userProfile", userProfile);

			forward = mapping.findForward(FORWARD_SHOW_VIEW_PUBLIC_DATA);
		} else {
			forward = this.showViewMyProfile(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewMyProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {

			AccessInfo loggedUser = (AccessInfo) request.getSession()
			.getAttribute("user");
			loggedUser = facade.searchUserById(loggedUser.getId());

			AccessInfo requestUser = null;
			AccessInfo userProfile = null;

			Integer requestUserId = null;

			if (request.getParameter("userId") != null) {
				requestUserId = Integer
				.parseInt(request.getParameter("userId"));
				requestUser = facade.searchUserById(requestUserId);
			} else {
				requestUserId = loggedUser.getId();
			}

			ProfileType loggedProfile = loggedUser.getTypeProfile();

			boolean canRequestTeaching = false;

			if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR)
					&& facade.canRequestTeaching(loggedUser.getPerson())) {
				canRequestTeaching = true;
			}

			if (requestUser != null) {
				userProfile = requestUser;
			} else {
				userProfile = loggedUser;
			}

			String degree = null;

			if (userProfile.getPerson().getResume() != null) {
				degree = userProfile.getPerson().getResume().getDegree();
			}

			request.setAttribute("userProfile", userProfile);
			request.setAttribute("requestUserId", requestUserId);
			request.setAttribute("loggedUserId", loggedUser.getId());
			request.setAttribute("degree", "editUserForm.degree." + degree);
			request.setAttribute("canRequestTeaching", canRequestTeaching);

			forward = mapping.findForward(FORWARD_SHOW_VIEW_MY_PROFILE);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ActionForward forward = null;

		AccessInfo info = (AccessInfo) request.getSession()
		.getAttribute("user");

		if (info != null) {
			info = facade.searchUserById(info.getId());
		}

		Person person = new Person();
		if ((info == null && request.getParameter("id") != null)
				|| (!info.getTypeProfile().equals("STUDENT") && request
						.getParameter("id") != null)) {

			String t = request.getParameter("id");
			int userId = Integer.valueOf(t);
			info = facade.searchUserById(userId);
		}
		person = info.getPerson();
		byte[] photo;
		Image image = person.getImage();
		if (image != null) {
			OutputStream os;
			photo = image.getPhoto();
			os = response.getOutputStream();
			os.write(photo);
			os.flush();
			os.close();
		} else {
			forward = mapping.findForward(FORWARD_SHOW_VIEW_DEFAULT_PHOTO);
		}

		return forward;
	}

	public ActionForward showViewClassMates(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");
			accessInfo = facade.searchUserById(accessInfo.getId());

			List<Course> userCourses = facade
			.searchCoursesByAccessInfo(accessInfo);

			for (Course c : userCourses) {
				List<Person> participants = facade.listStudentsByCourse(c);
				participants.remove(accessInfo.getPerson());
				c.setParticipants(participants);
			}

			ProfileType loggedProfile = accessInfo.getTypeProfile();

			boolean canRequestTeaching = false;

			if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR)
					&& facade.canRequestTeaching(accessInfo.getPerson())) {
				canRequestTeaching = true;
			}

			request.setAttribute("courses", userCourses);
			request.setAttribute("canRequestTeaching", canRequestTeaching);

			forward = mapping.findForward(FORWARD_SHOW_VIEW_CLASS_MATES);
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form,
					request, response);
		}

		return forward;
	}

	public ActionForward showViewTeachingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AccessInfo accessInfo = (AccessInfo) request.getSession().getAttribute(
		"user");
		accessInfo = facade.searchUserById(accessInfo.getId());

		Resume resume = accessInfo.getPerson().getResume();

		if (resume == null) {
			resume = new Resume();
		}

		request.setAttribute("resume", resume);

		return mapping.findForward(FORWARD_SHOW_VIEW_TEACHING_REQUEST);
	}

	public ActionForward showViewAssistanceRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Integer courseId;

		if (request.getParameter("courseId") != null) {
			courseId = Integer.parseInt(request.getParameter("courseId"));
		} else {
			courseId = (Integer) request.getAttribute("courseId");
		}

		AccessInfo user = (AccessInfo) request.getSession()
		.getAttribute("user");
		user = facade.searchUserById(user.getId());

		Course course = facade.getCoursesById(courseId);

		request.removeAttribute("resume");
		request.setAttribute("resume", user.getPerson().getResume());
		request.removeAttribute("course");
		request.setAttribute("course", course);
		request.setAttribute("courseId", courseId);

		return mapping.findForward(FORWARD_SHOW_VIEW_ASSISTANCE_REQUEST);
	}

	public ActionForward countOnlineUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print(OnlineUser.countUsers());

		return null;
	}

	public ActionForward userStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		int accessInfo = Integer.parseInt(request.getParameter("accessInfoId"));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print(OnlineUser.status(accessInfo));

		return null;
	}

	public ActionForward showViewOnlineUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");

			accessInfo = facade.searchUserById(accessInfo.getId());

			request.setAttribute("onlineUsers", OnlineUser.getUsers().values());

			forward = mapping.findForward(FORWARD_SHOW_VIEW_ONLINE_USERS);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}
	
	public ActionForward showViewIntegrationSocialNetworks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession()
			.getAttribute("user");

			accessInfo = facade.searchUserById(accessInfo.getId());
			
			HashMap<String, String> socialProfiles = new HashMap<String, String>();

			socialProfiles.put("id", String.valueOf(accessInfo.getId()));
			socialProfiles.put("name", accessInfo.getPerson().getName());
			socialProfiles.put("amadeusLogin",accessInfo.getLogin());
			socialProfiles.put("twitterLogin", accessInfo.getPerson().getTwitterLogin());
			socialProfiles.put("facebookLogin", accessInfo.getPerson().getFacebookLogin());
			

			request.setAttribute("person", accessInfo.getPerson());
			request.setAttribute("socialProfiles", socialProfiles);

			forward = mapping.findForward(FORWARD_SHOW_VIEW_INTEGRATION_SOCIAL_NETWORKS);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewAllCoursesInManagerUsers(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute(
			"user");
			user = facade.searchUserById(user.getId());

			if (UserPermissions.userCanShowViewAllCoursesInManagerUsers(user)) {
				List<Course> courses = facade.getAllCourses();
				request.setAttribute("courses", courses);
				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_ALL_COURSES_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewAllUsersInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute(
			"user");
			user = facade.searchUserById(user.getId());

			if (UserPermissions.userCanShowViewAllUsersInManagerUsers(user)) {
				List<AccessInfo> users = facade.getAllUsers();

				request.setAttribute("users", users);

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewUserNewInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>userNEW");
		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions.userCanShowViewUserNewInManagerUsers(request)) {
				List<LabelValueBean> years = new ArrayList<LabelValueBean>();

				Date data = new Date();
				SimpleDateFormat formatador = new SimpleDateFormat();
				formatador.applyPattern("yyyy");
				int year = Integer.parseInt(formatador.format(data));

				for (int x = year; x >= year - 100; x--) {
					years.add(new LabelValueBean(String.valueOf(x), String
							.valueOf(x)));
				}

				request.setAttribute("years", years);
				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_USER_NEW_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward userNewInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions.userCanUserNewInManagerUsers(request)) {
				ActionMessages messages = new ActionMessages();

				DynaActionForm dynaForm = (DynaActionForm) form;
				Person person = new Person();
				//person.setId(Integer.parseInt(request.getParameter("userId")));
				person.setName(dynaForm.getString("name"));
				person.setCity(dynaForm.getString("city"));
				person.setState(dynaForm.getString("state"));
				person.setCpf(dynaForm.getString("cpf"));

				if (!dynaForm.getString("phoneNumber").trim().equals("")) {
					person.setPhoneNumber(dynaForm.getString("phoneNumber"));
				}
				if (!dynaForm.getString("birthDate").trim().equals("")) {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					person.setBirthDate((Date) formatter
							.parse((String) dynaForm.get("birthDate")));
				}
				if (!dynaForm.getString("gender").trim().equals("")) {
					person.setGender(((String) dynaForm.getString("gender"))
							.charAt(0));
				}

				FormFile myFile = (FormFile) dynaForm.get("image");

				// photo validation
				try {
					if (!myFile.getFileName().equals("")) {
						if (myFile.getFileSize() < 500000) { // if the photo has
							// less than
							// 500KB
							Image image = new Image();
							Thumbnail thumb = new Thumbnail();
							byte[] photo = thumb.resize(myFile.getFileData());
							image.setPhoto(photo);
							person.setImage(image);
						} else
							messages.add("requestError", new ActionMessage(
							"errors.photoSize"));
					}
				} catch (Exception e) {
					messages.add("editError", new ActionMessage(e.getMessage()));
				}

				AccessInfo accessInfo = new AccessInfo();
				accessInfo.setLogin(dynaForm.getString("login"));
				accessInfo.setPassword(dynaForm.getString("password"));
				
				String profileType = dynaForm.getString("userType");
				System.out.println(dynaForm.getString("userType"));

				if (profileType.equals("ADMIN")) {
					accessInfo.setTypeProfile(ProfileType.ADMIN);
				} else if (profileType.equals("STUDENT")) {
					accessInfo.setTypeProfile(ProfileType.STUDENT);
				} else if (profileType.equals("PROFESSOR")) {
					accessInfo.setTypeProfile(ProfileType.PROFESSOR);
				}

				accessInfo.setPerson(person);

				person.setAccessInfo(accessInfo);
				
				person.setEmail(dynaForm.getString("email"));

				Resume resume = new Resume();

				if (!dynaForm.getString("degree").trim().equals("")) {
					resume.setDegree(dynaForm.getString("degree"));
				}
				if (!dynaForm.getString("instituition").trim().equals("")) {
					resume.setInstituition(dynaForm.getString("instituition"));
				}
				if (!dynaForm.getString("description").trim().equals("")) {
					resume.setDescription(dynaForm.getString("description"));
				}
				if (!dynaForm.get("year").equals("")) {
					resume.setYear(Integer.parseInt(dynaForm.getString("year")));
				}

				resume.setPerson(person);

				person.setResume(resume);

				if (facade.existLogin(person.getAccessInfo().getLogin())) {
					messages.add("error", new ActionMessage(
					"errors.login.alreadyExists"));
				}
				/*if (facade.existEmail(person.getEmail())) {
					messages.add("error", new ActionMessage(
					"errors.email.alreadyExists"));
				}*/

				if (messages.isEmpty()) {
					System.out.println("Antes: "+person.getCity());
					System.out.println("Antes: "+person.getCpf());
					System.out.println("Antes: "+person.getEmail());
					person = facade.insertPerson(person);
					System.out.println("Depois: "+person.getCity());
					System.out.println("Depois: "+person.getCpf());
					System.out.println("Depois: "+person.getEmail());
					facade.confirmRegistry(person.getAccessInfo());
					request.setAttribute("userId", person.getAccessInfo()
							.getId());
					forward = this.showViewUserProfileInManagerUsers(mapping,
							dynaForm, request, response);
				} else {
					saveErrors(request, messages);
					forward = this.showViewUserNewInManagerUsers(mapping,
							dynaForm, request, response);
				}
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}
	
	public ActionForward userEditInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions.userCanUserNewInManagerUsers(request)) {
				ActionMessages messages = new ActionMessages();

				DynaActionForm dynaForm = (DynaActionForm) form;

				Person person = new Person();
				
				//person.getAccessInfo().setId(Integer.parseInt(request.getParameter("userId")));
				AccessInfo accessInfoOld = facade.searchUserById(Integer.parseInt(request.getParameter("userId")));
				person.setId(accessInfoOld.getPerson().getId());
				person.setName(dynaForm.getString("name"));
				person.setCity(dynaForm.getString("city"));
				person.setState(dynaForm.getString("state"));
				person.setCpf(dynaForm.getString("cpf"));

				if (!dynaForm.getString("phoneNumber").trim().equals("")) {
					person.setPhoneNumber(dynaForm.getString("phoneNumber"));
				}
				if (!dynaForm.getString("birthDate").trim().equals("")) {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					person.setBirthDate((Date) formatter
							.parse((String) dynaForm.get("birthDate")));
				}
				if (!dynaForm.getString("gender").trim().equals("")) {
					person.setGender(((String) dynaForm.getString("gender"))
							.charAt(0));
				}

				FormFile myFile = (FormFile) dynaForm.get("image");

				// photo validation
				try {
					if (!myFile.getFileName().equals("")) {
						if (myFile.getFileSize() < 500000) { // if the photo has
							// less than
							// 500KB
							Image image = new Image();
							Thumbnail thumb = new Thumbnail();
							byte[] photo = thumb.resize(myFile.getFileData());
							image.setPhoto(photo);
							person.setImage(image);
						} else
							messages.add("requestError", new ActionMessage(
							"errors.photoSize"));
					}
				} catch (Exception e) {
					messages.add("editError", new ActionMessage(e.getMessage()));
				}

				AccessInfo accessInfo = new AccessInfo();
				accessInfo.setLogin(accessInfoOld.getLogin());
				accessInfo.setTypeProfile(accessInfoOld.getTypeProfile());
				accessInfo.setPassword(dynaForm.getString("password"));
				
				String profileType = request.getParameter("userType");

				if (profileType.equals("ADMIN")) {
					accessInfo.setTypeProfile(ProfileType.ADMIN);
				} else if (profileType.equals("STUDENT")) {
					accessInfo.setTypeProfile(ProfileType.STUDENT);
				} else if (profileType.equals("PROFESSOR")) {
					accessInfo.setTypeProfile(ProfileType.PROFESSOR);
				}
				
				accessInfo.setPerson(person);

				person.setAccessInfo(accessInfo);
				
				
				
				person.setEmail(dynaForm.getString("email"));

				Resume resume = new Resume();

				if (!dynaForm.getString("degree").trim().equals("")) {
					resume.setDegree(dynaForm.getString("degree"));
				}
				if (!dynaForm.getString("instituition").trim().equals("")) {
					resume.setInstituition(dynaForm.getString("instituition"));
				}
				if (!dynaForm.getString("description").trim().equals("")) {
					resume.setDescription(dynaForm.getString("description"));
				}
				if (!dynaForm.get("year").equals("")) {
					resume.setYear(Integer.parseInt(dynaForm.getString("year")));
				}

				resume.setPerson(person);

				person.setResume(resume);

				/*if (facade.existLogin(person.getAccessInfo().getLogin())) {
					messages.add("error", new ActionMessage(
					"errors.login.alreadyExists"));
				}*/
				/*if (facade.existEmail(person.getEmail())) {
					messages.add("error", new ActionMessage(
					"errors.email.alreadyExists"));
				}*/

				if (messages.isEmpty()) {
					person = facade.editUser(person);
					facade.confirmRegistry(person.getAccessInfo());
					request.setAttribute("userId", person.getAccessInfo()
							.getId());
					forward = this.showViewUserProfileInManagerUsers(mapping,
							dynaForm, request, response);
				} else {
					saveErrors(request, messages);
					forward = this.showViewUserNewInManagerUsers(mapping,
							dynaForm, request, response);
				}
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}


	public ActionForward showViewEditUserInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(">>>>>>>>>>>>>>> view edit");
		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			int userId = Integer.parseInt(request.getParameter("userId"));

			List<LabelValueBean> years = new ArrayList<LabelValueBean>();

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat();
			formatador.applyPattern("yyyy");
			int year = Integer.parseInt(formatador.format(data));

			for (int x = year; x >= year - 100; x--) {
				years.add(new LabelValueBean(String.valueOf(x), String
						.valueOf(x)));
			}

			AccessInfo accessInfo = facade.searchUserById(userId);

			HashMap<String, String> userProfile = new HashMap<String, String>();

			userProfile.put("id", String.valueOf(accessInfo.getId()));
			userProfile.put("name", accessInfo.getPerson().getName());
			userProfile.put("city", accessInfo.getPerson().getCity());
			userProfile.put("state", accessInfo.getPerson().getState());
			userProfile.put("cpf", accessInfo.getPerson().getCpf());
			userProfile.put("phoneNumber", accessInfo.getPerson()
					.getPhoneNumber());
			userProfile.put("birthDate",
					(accessInfo.getPerson().getBirthDate() != null ? accessInfo
							.getPerson().getBirthDate().toString() : null));
			userProfile.put("gender",
					String.valueOf(accessInfo.getPerson().getGender()));
			userProfile.put("userType", accessInfo.getTypeProfile().toString());
			userProfile.put("login", accessInfo.getLogin());
			userProfile.put("email", accessInfo.getPerson().getEmail());
			userProfile.put("degree",
					accessInfo.getPerson().getResume() != null ? accessInfo
							.getPerson().getResume().getDegree() : null);
			userProfile.put("instituition",
					accessInfo.getPerson().getResume() != null ? accessInfo
							.getPerson().getResume().getInstituition() : null);
			userProfile.put("description",
					accessInfo.getPerson().getResume() != null ? accessInfo
							.getPerson().getResume().getDescription() : null);
			userProfile.put(
					"year",
					accessInfo.getPerson().getResume() != null ? String
							.valueOf(accessInfo.getPerson().getResume()
									.getYear()) : null);
			userProfile.put("twitterLogin", accessInfo.getPerson().getTwitterLogin());

			request.setAttribute("personId", accessInfo.getPerson().getId());
			request.setAttribute("userProfile", userProfile);
			request.setAttribute("years", years);

			request.getSession().setAttribute("editUserInManager", accessInfo);

			forward = mapping
			.findForward(FORWARD_SHOW_VIEW_EDIT_USER_IN_MANAGER_USERS);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewUserProfileInManagerUsers(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions
					.userCanShowViewUserProfileInManagerUsers(request)) {
				int userId = (request.getParameter("userId") != null ? Integer
						.parseInt(request.getParameter("userId"))
						: (Integer) request.getAttribute("userId"));

				AccessInfo userProfile = facade.searchUserById(userId);

				request.setAttribute("userProfile", userProfile);
				if (userProfile.getPerson().getResume() != null) {
					request.setAttribute("degree", "editUserForm.degree."
							+ userProfile.getPerson().getResume().getDegree());
				}

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_USER_PROFILE_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward showViewSendMailInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions.userCanShowViewSendMailInManagerUsers(request)) {
				request.setAttribute("to", request.getParameter("to"));

				List<Course> courses = facade.getAllCourses();

				request.setAttribute("courses", courses);
				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_SEND_EMAIL_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward sendMailInManagerUsers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			if (UserPermissions.userCanSendMailInManagerUsers(request)) {
				List<String> emails = new ArrayList<String>();

				String to = request.getParameter("to");
				int toUserType = Integer.parseInt(request
						.getParameter("toUserType"));
				int toCourse = Integer.parseInt(request
						.getParameter("toCourse"));
				String subject = request.getParameter("subject");
				String message = request.getParameter("message");

				for (String t : to.split(";")) {
					emails.add(t);
				}

				switch (toUserType) {
				case 0:
					emails.addAll(facade.getAllEmail());
					break;
				case 1:
					emails.addAll(facade.getEmailOfStudens());
					break;
				case 2:
					emails.addAll(facade.getEmailOfProfessors());
					break;
				case 3:
					emails.addAll(facade.getEmailOfAdmins());
					break;
				default:
					break;
				}

				if (toUserType != 0) {
					if (toCourse != -1) {
						emails.addAll(facade.getEmailUsersOfCourse(toCourse));
					}
				}

				facade.sendMail(emails, subject, message);

				List<Course> courses = facade.getAllCourses();

				request.setAttribute("courses", courses);
				request.setAttribute("to", to);
				request.setAttribute("subject", subject);
				request.setAttribute("message", message);
				request.setAttribute("success", true);

				forward = mapping
				.findForward(FORWARD_SHOW_VIEW_SEND_EMAIL_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form,
						request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}

		return forward;
	}

	public ActionForward verifyEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		int personId = Integer.parseInt(request.getParameter("personId"));
		String email = request.getParameter("email");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print(facade.verifyEmail(personId, email));

		return null;
	}
	
	public ActionForward integrationSocialNetworks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		AccessInfo userAccessInfo = (AccessInfo) request.getSession()
		.getAttribute("user");

		String twitterLogin = myForm.getString("twitterLogin");
		String facebookLogin = myForm.getString("facebookLogin");

		try {
			facade.integrationSocialNetworks(userAccessInfo, twitterLogin, facebookLogin);
		} catch (InvalidSocialCredencialsException e) {
			messages.add("twitterLogin", new ActionMessage(
			"errors.invalidTwitterLogin"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		myForm.initialize(mapping);

		return mapping.findForward(FORWARD_SUCCESS);
	}

}