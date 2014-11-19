package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management;

import java.io.PrintWriter;
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

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;

public class ModuleActions extends SystemActions {

	private final String FORWARD_SHOW_VIEW_EDIT_MODULE = "fShowViewEditModule";
	private final String FORWARD_SHOW_VIEW_LIST_MODULES = "fShowViewListModules";
	private final String FORWARD_SHOW_VIEW_MODULE = "fShowViewModule";
	private final String FORWARD_SHOW_VIEW_CLICK_NEW_MODULE = "fShowViewClickNewModule";
	
	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("module.showViewEditModule", "showViewEditModule");
		map.put("module.newModule", "newModule");
		map.put("module.saveModule", "saveModule");
		map.put("module.changeOrderModule", "changeOrderModule");
		map.put("module.deleteModule", "deleteModule");
		map.put("module.showViewListModules", "showViewListModules");
		map.put("module.showViewModule", "showViewModule");
		map.put("module.cancelModule", "cancelModule");
		map.put("module.getDefineCSSClass", "getDefineCSSClass");
		map.put("module.cancelModule", "cancelModule");
		map.put("module.showViewClickNewModule", "showViewClickNewModule");
		map.put("module.getIdCourseByIdModule", "getIdCourseByIdModule");
		map.put("module.eraseAndWriteNameActivity", "eraseAndWriteNameActivity");
		
		return map;
	}
	
	public ActionForward showViewEditModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int idModule = Integer.valueOf(request.getParameter("idModule")); 
			Module module = facade.getModuleById(idModule);
			
			Course course = module.getCourse();
			
			List<Module> listModules = course.getModules();
			
			request.setAttribute("listModules", listModules);
			request.setAttribute("module", module);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_EDIT_MODULE);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward newModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			
			int idCourse = Integer.parseInt(request.getParameter("idCourse"));
			Course course = facade.getCoursesById(idCourse);
			
			int positionModule = facade.getNextPositionModule(course);
			
			Module module = new Module();
			module.setName("Nome do M�dulo - "+(positionModule));
			module.setDescription("Descri��o");
			module.setVisible(true);
			module.setPosition(positionModule);		
			module.setCourse(course);
			
			course.getModules().add(module);
			
			facade.flush();
			
			request.getSession().removeAttribute("course");
			request.getSession().setAttribute("course", course);
	
			request.getParameterMap().put("idModule", module.getId());
		
			forward = this.showViewEditModule(mapping, form, request, response);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}

	@SuppressWarnings("unchecked")
	public ActionForward saveModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			ActionMessages messages = new ActionMessages();
			DynaActionForm myForm = (DynaActionForm) form;
			
			int idModule = Integer.parseInt((String) request.getParameter("idModule"));
			String nameModule = (String) request.getParameter("nameModule");
			String descriptionModule = (String) request.getParameter("descriptionModule");
			boolean visibleModule = Boolean.parseBoolean(request.getParameter("visibleModule"));
			
			boolean nameExists = false;
				
			Module module = facade.getModuleById(idModule);
			String oldName = module.getName();
			
			for(Module m : module.getCourse().getModules()){
				if(m.getName().equalsIgnoreCase(nameModule) && !nameModule.equalsIgnoreCase(oldName)){
					nameExists = true;
				}
			}
			
			if("".equals(nameModule.trim())) {
				messages.add("invalidDate", new ActionMessage("errors.required","O nome do m�dulo"));
			} else if(nameModule.length() > 255) {
				String[] s  = {"O nome","255"};
				messages.add("invalidDate", new ActionMessage("errors.maxlength",s));
			}
			if("".equals(descriptionModule.trim())) {
				messages.add("invalidDate", new ActionMessage("errors.required","A descri��o do m�dulo"));
			} else if(descriptionModule.length() > 1500){
				String[] s  = {"descri��o","1500"};
				messages.add("invalidDate", new ActionMessage("errors.maxlength",s));
			}
			
			if(nameExists){
				module.setName(facade.getModuleById(idModule).getName());
				messages.add("invalidDate", new ActionMessage("errors.moduleNameAlreadyExists"));
			}
			
			
			if (!messages.isEmpty()) {
				saveErrors(request, messages);
				request.getParameterMap().put("idModule", module.getId());
				return this.showViewEditModule(mapping, myForm, request, response);
			} else {
				module.setName(nameModule);
				module.setDescription(descriptionModule);
				module.setVisible(visibleModule);
			}
			
			forward = this.showViewModule(mapping, myForm, request, response);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
			
		return forward;
	}

	@SuppressWarnings("unchecked")
	public ActionForward changeOrderModule(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int oldPosition = Integer.parseInt(request.getParameter("oldPosition")) - 1;
		int newPosition = Integer.parseInt(request.getParameter("newPosition")) - 1;
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		Course course = module.getCourse();
		
		List<Module> moduleList = course.getModules();

		for (int index = 0; index <= (moduleList.size() - 1); index++) {
			Module m = null;
			if (index != oldPosition && index != newPosition) {
				m = moduleList.get(index);
			} else if (index == oldPosition) {
				m = moduleList.get(newPosition);
			} else if (index == newPosition) {
				m = moduleList.get(oldPosition);
			}
			m.setPosition(index+1);
		}
		
		request.getParameterMap().put("idCourse", course.getId());
		
		return this.showViewListModules(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward deleteModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int idModule = Integer.parseInt(request.getParameter("idModule"));
			
			Module module = facade.getModuleById(idModule);
			Course course = module.getCourse();
			
			//Impede estouro de mem�ria.
			for (int i = (module.getMaterials().size() -1); i >= 0; i--) {
				module.getMaterials().remove(i);
				facade.flush();
			}
			
			course.getModules().remove(module);
			
			//Reorganiza as position
			for (int x = 0; x < course.getModules().size(); x++) {
				Module m = course.getModules().get(x);
				m.setPosition(x+1);
			}
			
			request.getParameterMap().put("idCourse", course.getId());
			
			forward = this.showViewListModules(mapping, form, request, response);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewListModules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) { 
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());
			
			int idCourse = Integer.parseInt(request.getParameter("idCourse"));
			
			Course course = facade.getCoursesById(idCourse);
			
			Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			
			List<Module> modules = course.getModules();
			
			request.setAttribute("positionModule", 0);
			request.setAttribute("modules", modules);
			request.setAttribute("course", course);
			request.setAttribute("userRoleInCourse", userRoleInCourse);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_LIST_MODULES);
		} else {
			forward = new SystemActions().showViewMenu(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());
			
			int idModule = Integer.parseInt((String) request.getParameter("idModule"));
			Module module = facade.getModuleById(idModule);
			
			Course course = module.getCourse();
			
			Role userRoleInCourse = facade.getRoleByPersonInCourse(user.getPerson(), course);
			
			request.setAttribute("module", module);
			request.setAttribute("userRoleInCourse", userRoleInCourse);
			request.getSession().setAttribute("course", course);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_MODULE);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward cancelModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			forward = this.showViewModule(mapping, form, request, response);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward getDefineCSSClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String extension = request.getParameter("extension");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print(UtilActivities.defineCssClass(extension));
		
		return null;
	}
	
	public ActionForward showViewClickNewModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int lastPositionModule = Integer.parseInt(request.getParameter("lastPositionModule"));
		int idCourse = Integer.parseInt(request.getParameter("idCourse"));
		
		request.setAttribute("lastPositionModule", lastPositionModule);
		request.setAttribute("idCourse", idCourse);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_CLICK_NEW_MODULE);
	}
	
	public ActionForward getIdCourseByIdModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Course course = module.getCourse();
		
		out.print(course.getId());
		
		return null;
	}

	public ActionForward eraseAndWriteNameActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int moduleId = Integer.parseInt(request.getParameter("moduleId"));
		
		UtilActivities.eraseAndWriteNameActivity(moduleId);
		
		return null;
	}
}