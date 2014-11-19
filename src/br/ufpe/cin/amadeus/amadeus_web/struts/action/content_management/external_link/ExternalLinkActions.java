package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.external_link;

import java.util.Date;
import java.util.HashMap;
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
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ExternalLink;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;

public class ExternalLinkActions extends SystemActions{
	
	private final String FORWARD_SHOW_VIEW_EXTERNAL_LINK_ACTIVITY = "fExternalLinkStatus";
	private final String FORWARD_SHOW_VIEW_NEW_EXTERNAL_LINK_ACTIVITY = "fShowViewNewExternalLink";
	private final String FORWARD_SHOW_VIEW_EDIT_EXTERNAL_LINK_ACTIVITY = "fShowViewEditExternalLink";

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("externalLinkMaterial.showViewExternalLink", "showViewExternalLink");
		map.put("externalLinkMaterial.showViewNewExternalLink", "showViewNewExternalLink");
		map.put("externalLinkMaterial.newExternalLink", "newExternalLink");
		map.put("externalLinkMaterial.showViewEditExternalLink", "showViewEditExternalLink");
		map.put("externalLinkMaterial.editExternalLink", "editExternalLink");
		map.put("externalLinkMaterial.deleteExternalLink", "deleteExternalLink");
		
		return map;
	}
	
	public ActionForward showViewExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idExternalLink = Integer.parseInt(request.getParameter("idExternalLink"));
		
		ExternalLink extLink = facade.getExternalLinkById(idExternalLink);
		Module module = extLink.getModule();
		
		request.setAttribute("module", module);
		request.setAttribute("externalLink", extLink);

		return mapping.findForward(FORWARD_SHOW_VIEW_EXTERNAL_LINK_ACTIVITY);	
	}
	
	public ActionForward showViewNewExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_EXTERNAL_LINK_ACTIVITY);
	}

	public ActionForward newExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		Course course = module.getCourse();
		
		ExternalLink extLink = new ExternalLink();
		extLink.setName(myForm.getString("nameExternalLink"));
		extLink.setUrl(myForm.getString("urlExternalLink"));
		extLink.setDescription(myForm.getString("descriptionExternalLink"));
		extLink.setCreationDate(new Date());
		
		if((new Date()).compareTo(course.getFinalCourseDate()) > 0){
			messages.add("erros", new ActionMessage("errors.finalCourseDate"));
		}
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {
			extLink.setModule(module);
			module.getExternalLinks().add(extLink);
			
			facade.flush();
			
			// AJAX REVERSO
			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;
		}
	}
	
	public ActionForward showViewEditExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idExternalLink = Integer.parseInt(request.getParameter("idExternalLink"));
		
		ExternalLink extLink = facade.getExternalLinkById(idExternalLink);
		Module module = extLink.getModule();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("idExternalLink", extLink.getId());
		data.put("nameExternalLink", extLink.getName());
		data.put("urlExternalLink", extLink.getUrl());
		data.put("descriptionExternalLink", extLink.getDescription());
		
		request.setAttribute("module", module);
		request.setAttribute("externalLink", data);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_EXTERNAL_LINK_ACTIVITY);
	}
	
	public ActionForward editExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DynaActionForm myForm = (DynaActionForm) form;
		int idExternalLink = Integer.parseInt(request.getParameter("idExternalLink"));
		
		ExternalLink extLink = facade.getExternalLinkById(idExternalLink);
		Module module = extLink.getModule();
		
		extLink.setName(myForm.getString("nameExternalLink"));
		extLink.setUrl(myForm.getString("urlExternalLink"));
		extLink.setDescription(myForm.getString("descriptionExternalLink"));
		
		facade.flush();
		
		// AJAX REVERSO
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;
	}
	
	public ActionForward deleteExternalLink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idExternalLink = Integer.parseInt(request.getParameter("idExternalLink"));
		
		ExternalLink extLink = facade.getExternalLinkById(idExternalLink);
		Module module = extLink.getModule();
		
		module.getExternalLinks().remove(extLink);
		
		facade.flush();
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;
	}
	
}

