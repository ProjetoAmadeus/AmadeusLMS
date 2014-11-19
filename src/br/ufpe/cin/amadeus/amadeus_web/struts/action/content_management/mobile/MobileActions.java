package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.mobile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class MobileActions extends org.apache.struts.actions.DispatchAction{
	
	public Facade facade = Facade.getInstance();
	public FacadeMobile facadeMobile = FacadeMobile.getInstance();
	
	private final String SHOW_VIEW_LOGGED = "fShowViewLoggedMobile";
	private final String SHOW_VIEW_NOT_LOGGED = "fShowViewWelcomeMobile";
	private final String SHOW_MANAGEMENT = "fShowViewManagementMobile";
	private final String SHOW_MANAGEMENT_CELLPHONE = "fShowManagementCellPhone";
	private final String SHOW_MANAGEMENT_COURSE = "fShowManagementCourse";
	private final String SHOW_MANAGEMENT_COURSE_COLOR = "fShowManagementCourseColor";
	
	private final String SHOW_COURSE_INFORMATION = "fShowCourseInformation";
	private final String SHOW_HOME_COURSE = "fShowHomeCourse";
	private final String SHOW_HOME_MODULES = "fShowHomeModules";
	private final String SHOW_HOME_MODULE = "fShowHomeModule";
	private final String SHOW_ALL_COURSES = "fShowAllCourses";
	
	private final String SHOW_COURSE_ACTIVITY = "fShowCourseActivities";
	private final String SHOW_MODULE_ACTIVITY = "fShowModuleActivities";
	private final String SHOW_VIEW_POLL = "fShowViewPoll";
	private final String SHOW_VIEW_LEARNING_OBJECT = "fShowViewLearningObject";
	private final String SHOW_VIEW_HOMEWORK = "fShowViewHomework";
	
	private final String SHOW_COURSE_MATERIALS = "fShowCourseMaterials";
	private final String SHOW_MODULE_MATERIALS = "fShowModuleMaterials";
	
	//************************** DEFAULT **********************************//
	
	public ActionForward logon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("userMobile") != null){
			return mapping.findForward(SHOW_VIEW_LOGGED);
		}
		
		UserMobile userMobile = null;
		PersonMobile personMobile = null;
		String login = request.getParameter("loginMobile");
		String password = request.getParameter("passwordMobile");
		
		if(login == null || password == null){
			return mapping.findForward(SHOW_VIEW_NOT_LOGGED);
		}
		
		try {
			userMobile = facadeMobile.verifyLogin(login, password);
			
			if(userMobile != null){
				personMobile = facadeMobile.getLogin(login);
				if(personMobile == null){
					facadeMobile.insertLogin(login);
				}
			}
			
		} catch (Exception e) {
			return mapping.findForward(SHOW_VIEW_NOT_LOGGED);
			//e.printStackTrace();
		}
		
		request.getSession().setAttribute("userMobile", userMobile);
		this.takeCoursesAboutStudent(mapping, form, request, response);
		return mapping.findForward(SHOW_VIEW_LOGGED);
	}
	
	public ActionForward takeCoursesAboutStudent (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = request.getParameter("loginMobile");
		List<CourseMobile> listCourses = facadeMobile.listCourses(login);
		List<NoticeMobile> listNotices = facadeMobile.getNoticesAmadeus(login);
		int qtdUnreadNotices = 0;
		int noticesSize = listNotices.size();
		
		for (NoticeMobile not : listNotices) {
			if(!not.isRead()){
				qtdUnreadNotices++;
			}
		}
		
		request.getSession().setAttribute("listCourses", listCourses);
		request.getSession().setAttribute("listNotices", listNotices);
		request.getSession().setAttribute("qtdUnreadNotices", qtdUnreadNotices);
		request.getSession().setAttribute("noticesSize", noticesSize);
		
		return null;
	}
	
	public ActionForward showLoggedView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.getSession().removeAttribute("listCustomizedCourses");
		
		return mapping.findForward(SHOW_VIEW_LOGGED);
	}
	
	public ActionForward logoff (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.getSession().removeAttribute("userMobile");
		request.getSession().removeAttribute("listCourses");
		request.getSession().removeAttribute("listNotices");
		request.getSession().removeAttribute("qtdUnreadNotices");
		request.getSession().removeAttribute("noticesSize");
		request.getSession().removeAttribute("listCustomizedCourses");
		
		return mapping.findForward(SHOW_VIEW_NOT_LOGGED);
	}
	
	//************************** MANAGEMENT **********************************//
	
	@SuppressWarnings("unchecked")
	public ActionForward showViewManagement (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UserMobile userMobile = (UserMobile) request.getSession().getAttribute("userMobile");
		List<CourseMobile> listCourses = (List<CourseMobile>)request.getSession().getAttribute("listCourses");
		List<CourseMobile> listCustomizedCourses = new ArrayList<CourseMobile>();
		
		for (CourseMobile course : listCourses) {
			listCustomizedCourses.add( this.facadeMobile.getCourse(course.getId(), userMobile.getLogin()) );
		}
		
		request.getSession().setAttribute("listCustomizedCourses", listCustomizedCourses);
		
		return mapping.findForward(SHOW_MANAGEMENT);
	}

	public ActionForward showViewCellPhoneNumberManagement (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UserMobile user = (UserMobile) request.getSession().getAttribute("userMobile");
		PersonMobile person = this.facadeMobile.getPerson(user.getLogin());
		if(person.getPhoneNumber() != null && person.getPhoneNumber().trim().length() > 0){
			request.setAttribute("phoneNumber", person.getPhoneNumber());
		}
		
		return mapping.findForward(SHOW_MANAGEMENT_CELLPHONE);
	}
	
	public ActionForward insertCellNumber (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String cellNumber = request.getParameter("cellNumber");
		UserMobile user = (UserMobile) request.getSession().getAttribute("userMobile");
		PersonMobile person = this.facadeMobile.getPerson(user.getLogin());
		person.setPhoneNumber(cellNumber);
		this.facadeMobile.updateLogin(person);
		
//		throw new IllegalAccessError();
		
		return this.showViewManagement(mapping, form, request, response);
	}
	
	public ActionForward showViewCourseManagement (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String idCourse = (String) request.getParameter("idCourse");
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		
		String turnSms = request.getParameter("ativarSms");
		if (turnSms != null) {
			if (turnSms.equalsIgnoreCase("true")) {
				course.setSms(true);
				this.facadeMobile.updateCourse(course, login);
			} else {
				course.setSms(false);
				this.facadeMobile.updateCourse(course, login);
			}
		}
		request.setAttribute("course", course);
		
		return mapping.findForward(SHOW_MANAGEMENT_COURSE);
	}
	
	public ActionForward changeCourseColor (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String login = ((UserMobile) request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		
		String color = (String)request.getParameter("courseColor");
		if(color != null){
			course.setColor(color);
			this.facadeMobile.updateCourse(course, login);
		}
		
		return this.showViewManagementCourseColor(mapping, form, request, response);
	}
	
	public ActionForward showViewManagementCourseColor (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile) request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");

		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		request.setAttribute("course", course);
		
		return mapping.findForward(SHOW_MANAGEMENT_COURSE_COLOR);
	}
	
	//************************** COURSE **********************************//
	
	public ActionForward showViewCourse (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = request.getParameter("idCourse");
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		
		request.setAttribute("course", course);
		List<ModuleMobile> modules = course.getModules();
		if(modules != null){
			request.setAttribute("modules", modules.size()+"");
		}else{
			request.setAttribute("modules", "0");
		}
		
		if(course.getNotices() != null){
			request.setAttribute("notices", course.getNotices().size()+"");
		}else{
			request.setAttribute("notices", "0");
		}
		
		int homeworks = 0;
		int materials = 0;
		ModuleMobile tempModule = null;
		for (ModuleMobile module : modules) {
			tempModule = this.facadeMobile.getModule(module.getId());
			homeworks = homeworks + tempModule.getHomeworks().size();
			materials = materials + tempModule.getMaterials().size();
		}
		
		request.setAttribute("homeworks", homeworks+"");
		request.setAttribute("materials", materials+"");
		
		return mapping.findForward(SHOW_HOME_COURSE);
	}
	
	public ActionForward showViewCourseInformation (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = request.getParameter("idCourse");
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		
		request.setAttribute("course", course);
		request.setAttribute("teachers", course.getTeachers());
		request.setAttribute("helpers", course.getHelpers());
		request.setAttribute("keywords", course.getKeywords());
		
		return mapping.findForward(SHOW_COURSE_INFORMATION);
	}

	public ActionForward showViewCourseModules (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		List<ModuleMobile> modules = course.getModules();
		
		request.setAttribute("modules", modules);
		request.setAttribute("course", course);

		return mapping.findForward(SHOW_HOME_MODULES);
	}
	
	public ActionForward showViewModule (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idModule = (String)request.getParameter("idModule");
		
		int atividades = 0;
		int materiais = 0;
		int noticias = 0;
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		ModuleMobile module = this.facadeMobile.getModule(Integer.parseInt(idModule));
		
		if(module.getHomeworks() != null){
			atividades = module.getHomeworks().size();
		}
		if(module.getMaterials() != null){
			materiais = module.getMaterials().size();
		}
		if(module.getNoticies() != null){
			noticias = module.getNoticies().size();
		}
		
		request.setAttribute("course", course);
		request.setAttribute("module", module);
		request.setAttribute("atividades", atividades);
		request.setAttribute("materiais", materiais);
		request.setAttribute("noticias", noticias);
		
		return mapping.findForward(SHOW_HOME_MODULE);
	}
	
	public ActionForward showViewAllCourses (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();

		List<Object> allCoursesActivities = this.facadeMobile.getAllCoursesActivities(login);
		List<MaterialMobile> allCoursesMaterials = this.facadeMobile.getAllCoursesMaterials(login);
		
		request.setAttribute("activities", allCoursesActivities.size());
		request.setAttribute("materials", allCoursesMaterials.size());
		
		return mapping.findForward(SHOW_ALL_COURSES);
	}
	
	//************************** ACTIVITIES **********************************//
	
	public ActionForward showViewCourseActivities (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		List<Object> homeworks = this.facadeMobile.getCourseActivities(Integer.parseInt(idCourse));
		
		List<HomeworkMobile> learningObjects = new ArrayList<HomeworkMobile>();
		List<HomeworkMobile> polls = new ArrayList<HomeworkMobile>();
		List<HomeworkMobile> anotherHomeworks = new ArrayList<HomeworkMobile>();
		
		for (Object obj : homeworks) {
			if( ((HomeworkMobile)obj).getTypeActivity().equalsIgnoreCase(HomeworkMobile.POLL) ){
				polls.add((HomeworkMobile)obj);
			}else if( ((HomeworkMobile)obj).getTypeActivity().equalsIgnoreCase(HomeworkMobile.LEARNING_OBJECT) ){
				learningObjects.add((HomeworkMobile)obj);
			}else{
				anotherHomeworks.add((HomeworkMobile)obj);
			}
		}
		
		request.setAttribute("learningObjects", learningObjects);
		request.setAttribute("polls", polls);
		request.setAttribute("anotherHomeworks", anotherHomeworks);
		request.setAttribute("course", course);
		
		
		return mapping.findForward(SHOW_COURSE_ACTIVITY);
	}
	
	public ActionForward showViewModuleActivities (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idModule = (String)request.getParameter("idModule");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		List<Object> homeworks = this.facadeMobile.getModuleActivities(Integer.parseInt(idModule));
		
		List<HomeworkMobile> learningObjects = new ArrayList<HomeworkMobile>();
		List<HomeworkMobile> polls = new ArrayList<HomeworkMobile>();
		List<HomeworkMobile> anotherHomeworks = new ArrayList<HomeworkMobile>();
		
		for (Object obj : homeworks) {
			if( ((HomeworkMobile)obj).getTypeActivity().equalsIgnoreCase(HomeworkMobile.POLL) ){
				polls.add((HomeworkMobile)obj);
			}else if( ((HomeworkMobile)obj).getTypeActivity().equalsIgnoreCase(HomeworkMobile.LEARNING_OBJECT) ){
				learningObjects.add((HomeworkMobile)obj);
			}else{
				anotherHomeworks.add((HomeworkMobile)obj);
			}
		}
		
		request.setAttribute("learningObjects", learningObjects);
		request.setAttribute("polls", polls);
		request.setAttribute("anotherHomeworks", anotherHomeworks);
		request.setAttribute("course", course);
		request.setAttribute("idModule", idModule);
		
		return mapping.findForward(SHOW_MODULE_ACTIVITY);
	}
	
	@Deprecated
	public ActionForward showViewAllCoursesActivities (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		
		
		
		return null;
	}
	
	public ActionForward showViewPoll (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idPoll = (String)request.getParameter("idPoll");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		PollMobile poll = this.facadeMobile.getPoll(Integer.parseInt(idPoll));
		
		request.setAttribute("course", course);
		request.setAttribute("poll", poll);
		request.setAttribute("choices", poll.getChoices());
		
		return mapping.findForward(SHOW_VIEW_POLL);
	}
	
	public ActionForward showViewLearningObject (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idLearning = (String)request.getParameter("idLearning");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		LearningObjectMobile learningObject = this.facadeMobile.getLearningObject(Integer.parseInt(idLearning));
		
		int access = this.facade.getTotalAccessLearningObject(Integer.parseInt(idLearning));
		
		request.setAttribute("course", course);
		request.setAttribute("learning", learningObject);
		request.setAttribute("access", access);
		
		return mapping.findForward(SHOW_VIEW_LEARNING_OBJECT);
	}
	
	public ActionForward showViewHomework (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idHomework = (String)request.getParameter("idHomework");
	
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		
		List<Object> courseActivities = this.facadeMobile.getCourseActivities(Integer.parseInt(idCourse));
		for (Object obj : courseActivities) {
			int id = ((HomeworkMobile)obj).getId();
			if(Integer.parseInt(idHomework) == id){
				HomeworkMobile homework = (HomeworkMobile) obj;
				request.setAttribute("homework", homework);
			}
		}
		
		request.setAttribute("course", course);
		
		return mapping.findForward(SHOW_VIEW_HOMEWORK);
	}
	
	//************************** MATERIALS **********************************//
	
	public ActionForward showViewCourseMaterials (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		List<ModuleMobile> modules = course.getModules();
		List<MaterialMobile> allMaterials = new ArrayList<MaterialMobile>();
		for (ModuleMobile mod : modules) {
			allMaterials.addAll( mod.getMaterials() );
		}
		
		request.setAttribute("materials", allMaterials);
		request.setAttribute("course", course);
		
		return mapping.findForward(SHOW_COURSE_MATERIALS);
	}
	
	public ActionForward showViewModuleMaterials (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String login = ((UserMobile)request.getSession().getAttribute("userMobile")).getLogin();
		String idCourse = (String)request.getParameter("idCourse");
		String idModule = (String)request.getParameter("idModule");
		
		CourseMobile course = this.facadeMobile.getCourse(Integer.parseInt(idCourse), login);
		ModuleMobile module = this.facadeMobile.getModule(Integer.parseInt(idModule));
		
		request.setAttribute("materials", module.getMaterials());
		request.setAttribute("course", course);
		request.setAttribute("idModule", idModule);
		
		return mapping.findForward(SHOW_MODULE_MATERIALS);
	}
	
	public ActionForward showViewAllCourseMaterials (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return null;
	}
	
}
