/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.material;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.ModuleUtils;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Archive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidMaterialException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.DateValidator;

public class MaterialActions extends org.apache.struts.actions.DispatchAction {

	private Facade facade = Facade.getInstance();
	
	public static final String FORWARD_EDIT_MATERIAL;
	public static final String FORWARD_ADD_MATERIAL_NAME;
	public static final String FORWARD_EDIT_MATERIAL_REQUEST;
	public static final String FORWARD_SHOW_VIEW_MATERIAL_REQUEST_ACTIVITY;
	public static final String FORWARD_VIEW_MATERIAL_REQUEST_FILE_UPLOAD;
	public static final String FORWARD_SHOW_MATERIAL_REQUEST_TEACHER;
	
	public final String FORWARD_SHOW_VIEW_NEW_MATERIAL_ACTIVITY = "fShowViewNewMaterialActivity";
	public final String FORWARD_SHOW_VIEW_EDIT_MATERIAL_ACTIVITY = "fShowViewEditMaterialActivity";
	public final String FORWARD_SHOW_VIEW_UPLOAD_MATERIAL_ACTIVITY = "fShowViewUploadMaterialActivity";
	public final String FORWARD_SHOW_VIEW_EDIT_UPLOAD_MATERIAL_ACTIVITY = "fShowViewEditUploadMaterialActivity";
	
	public final String FORWARD_SHOW_VIEW_NEW_MATERIAL_REQUEST_ACTIVITY = "fShowViewNewMaterialRequestActivity";
	
	static {
		FORWARD_ADD_MATERIAL_NAME = "fAddMaterialName";
		FORWARD_EDIT_MATERIAL = "fEditMaterial";
		FORWARD_EDIT_MATERIAL_REQUEST = "fEditMaterialRequest";
		FORWARD_SHOW_VIEW_MATERIAL_REQUEST_ACTIVITY = "fShowMaterialRequest";
		FORWARD_VIEW_MATERIAL_REQUEST_FILE_UPLOAD = "fViewMaterialRequestFileUpload";
		FORWARD_SHOW_MATERIAL_REQUEST_TEACHER = "fShowMaterialRequestTeacher";
	}
	
	//********************** MATERIAL DELIVERY *******************

	public ActionForward showViewNewMaterialActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		int idModule = Integer.parseInt(request.getParameter("idModule"));
					
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_MATERIAL_ACTIVITY);
	}
	
	public ActionForward newMaterialActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		Person author = info.getPerson();
		int idModule = Integer.valueOf((String)request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		String name = myForm.getString("nameMaterial");
		FormFile archive = (FormFile) myForm.get("archive");
		
		Material material = new Material();

		try {
			facade.validateMaterial(archive, name);
			Archive a = new Archive();
			a.setArchive(archive.getFileData());
			material.setArchive(a);
			material.setExtension(archive.getFileName());
		} catch (InvalidMaterialException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		} catch (Exception e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		material.setCreationDate((Calendar.getInstance()).getTime());
		material.setArchiveName(name);
		material.setAuthor(author);
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			
			request.setAttribute("idModule", module.getId());
			
			return this.showViewNewMaterialActivity(mapping, myForm, request, response);
		}
		
		material.setModule(module);
		module.getMaterials().add(material);
		
		facade.flush();
		
		if(SystemActions.mobileSettings.getSmsMaterial().equals("on")){
			Receiver receiver = new Receiver();
			receiver.addMaterial(module.getCourse().getId(), module.getId(), material.getId());
		}
		request.setAttribute("action", "0");
		request.setAttribute("material", material);
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_ADD_MATERIAL_NAME);
	}
	
	public ActionForward newMaterialDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		String callFromPaddingTask = request.getParameter("callFromPaddingTask");
		
		Person author = info.getPerson();
		int idModule = Integer.valueOf((String)request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		int idActivity = Integer.valueOf(request.getParameter("idActivity"));
		
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idActivity);
		
		String name = myForm.getString("nameMaterial");
		FormFile archive = (FormFile) myForm.get("archive");
		
		Material material = new Material();

		try {
			facade.validateMaterial(archive, name);
			Archive a = new Archive();
			a.setArchive(archive.getFileData());
			material.setArchive(a);
			material.setExtension(archive.getFileName());
		} catch (InvalidMaterialException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		} catch (Exception e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		if(idActivity != 0){
			material.setRequestedMaterial(materialRequest);
		}
		
		material.setCreationDate((Calendar.getInstance()).getTime());
		material.setArchiveName(name);
		material.setAuthor(author);
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return null;//this.showViewMaterialRequestActivity(mapping, myForm, request, response);
		}
		
		material.setModule(module);
		module.getMaterials().add(material);
		
		facade.flush();
		
		if(SystemActions.mobileSettings.getSmsMaterial().equals("on")){
			Receiver receiver = new Receiver();
			receiver.addMaterial(module.getCourse().getId(), module.getId(), material.getId());
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		StringBuilder script = new StringBuilder();
		script.append("<script type=\"text/javascript\">");
		
		if(!callFromPaddingTask.equals("true")){
			script.append("top.showViewMaterialRequestActivity("+module.getPosition()+","+idActivity+","+callFromPaddingTask+");");
		} else {
			script.append("top.showViewMaterialRequestActivity("+idActivity+","+idActivity+","+callFromPaddingTask+");");
		}
		
		script.append("</script>");
		
		//TODO - LOG - Entrega de atividade - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_ENTREGAR_MATERIAL);
		log.setIdObjeto(material.getId());
		this.facade.saveLog(log);
		
		out.print(script.toString());
		
		return null;
	}
	
	public ActionForward showViewEditMaterialActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idMaterial = Integer.parseInt(request.getParameter("idMaterial"));
		
		request.setAttribute("idMaterial", idMaterial);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_MATERIAL_ACTIVITY);
	}
	
	public ActionForward editMaterialActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idMaterial = Integer.parseInt(request.getParameter("idMaterial"));
		
		Material material = facade.getMaterialByID(idMaterial);
		Module module = material.getModule();
		Course course = module.getCourse();
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		Person author = course.getProfessor();
		
		String name = myForm.getString("nameMaterial");
		FormFile archive = (FormFile) myForm.get("archive");
		
		
		try {
			facade.validateMaterial(archive, name);
			material.setArchive(new Archive(archive.getFileData()));
			material.setExtension(archive.getFileName());
		} catch (InvalidMaterialException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		} catch (Exception e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);	
			return this.showViewEditUploadMaterialActivity(mapping, myForm, request, response);
		}
		
		material.setArchiveName(name);
		material.setAuthor(author);
		
		List<Material> materials = module.getMaterials();
		
		facade.flush();
		
		request.setAttribute("action", "1");
		request.setAttribute("material", material);
		request.setAttribute("materials", materials);
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_ADD_MATERIAL_NAME);	
	}
	
	public ActionForward showViewEditUploadMaterialActivity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		int idMaterial = Integer.parseInt(request.getParameter("idMaterial"));
		
		Material material = facade.getMaterialByID(idMaterial);
		
		Module module = material.getModule();
		
		request.setAttribute("material", material);
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_UPLOAD_MATERIAL_ACTIVITY);
	}	
	
	public ActionForward deleteMaterialActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idMaterial = Integer.valueOf((String)request.getParameter("idActivity"));
		
		Material material = facade.getMaterialByID(idMaterial);
		Module module = material.getModule();
		
		module.getMaterials().remove(material);
				
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		
		/*Receiver receiver = new Receiver();
		receiver.removeMaterial(module.getCourse().getId(), module.getId(), material.getId());*/
	
		return null;	
	}
	
	//******************** MATERIAL REQUEST **********************
	
	public ActionForward showViewNewMaterialRequestActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_MATERIAL_REQUEST_ACTIVITY);
	}
	
	public ActionForward newMaterialRequestActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		String allowLateDeliveries = (String) request.getParameter("allowMaterial");
		String description = myForm.getString("descriptionMaterial");
		String name = myForm.getString("nameMaterial");
		String day = myForm.getString("dayMaterial");
		String month = myForm.getString("monthMaterial");
		String year = myForm.getString("yearMaterial");
		
		DateValidator dateValidator = new DateValidator(messages,day, month, year, true);
				
		MaterialRequest materialRequest = new MaterialRequest();
		
		if(allowLateDeliveries.equalsIgnoreCase("on")){
			materialRequest.setAllowLateDeliveries(true);
		}
		
		materialRequest.setName(name);
		materialRequest.setDescription(description);
		materialRequest.setDeliveryDate(dateValidator.getDate());
		materialRequest.setModule(module);
		materialRequest.setCreationDate(new Date());
				
		module.getMaterialRequests().add(materialRequest);
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
		
		facade.flush();
		if(SystemActions.mobileSettings.getSmsMaterial().equals("on")){
			Receiver receiver = new Receiver();
			receiver.addMaterial(module.getCourse().getId(), module.getId(), materialRequest.getId());
		}
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		
		return null;
	}
	
	public ActionForward showViewEditMaterialRequestActivity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		int idMaterial = Integer.valueOf((String) request.getParameter("idActivity"));
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idMaterial);

		Module module = materialRequest.getModule();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(materialRequest.getDeliveryDate());

		String day = "" + cal.get(Calendar.DATE);
		int monthInteger = cal.get(Calendar.MONTH) + 1;
		String month = "" + monthInteger;
		String year = "" + cal.get(Calendar.YEAR);

		cal.setTime(materialRequest.getDeliveryDate());

		//Cria um formul�rio dinamicamente
		ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(request, getServlet().getServletContext());
		FormBeanConfig formConfig = moduleConfig.findFormBeanConfig("materialRequestActivity");
		DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formConfig);

		DynaActionForm myForm = null;
		try {
			myForm = (DynaActionForm) dynaClass.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	

		myForm.set("nameMaterial", materialRequest.getName());
		myForm.set("descriptionMaterial", materialRequest.getDescription());
		myForm.set("dayMaterial", day);
		myForm.set("monthMaterial", month);
		myForm.set("yearMaterial", year);
		myForm.set("allowMaterial", materialRequest.isAllowLateDeliveries());
		
		request.setAttribute("materialRequestActivity", myForm);

		request.setAttribute("idMaterial", idMaterial);
		request.setAttribute("module", module);

		return mapping.findForward(FORWARD_EDIT_MATERIAL_REQUEST);
	}

	public ActionForward editMaterialRequestActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		int idMaterialRequest = Integer.valueOf((String) request.getParameter("idActivity"));
		
		boolean allowLateDeliveries = (Boolean) myForm.get("allowMaterial");
		String description = myForm.getString("descriptionMaterial");
		String name = myForm.getString("nameMaterial");
		String day = myForm.getString("dayMaterial");
		String month = myForm.getString("monthMaterial");
		String year = myForm.getString("yearMaterial");
		
		DateValidator dateValidator = new DateValidator(messages,day, month, year, true);
				
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idMaterialRequest);
		
		materialRequest.setAllowLateDeliveries(allowLateDeliveries);
		materialRequest.setName(name);
		materialRequest.setDescription(description);
		materialRequest.setDeliveryDate(dateValidator.getDate());

		UtilActivities.eraseAndWriteNameActivity(materialRequest.getModule().getId());
		
		return null;
	}
	
	public ActionForward deleteMaterialRequestActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idMaterial = Integer.valueOf((String)request.getParameter("idActivity"));
		
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idMaterial);
		Module module = materialRequest.getModule();
		
		List<Material> materialsFromRequest = materialRequest.getMaterials();
		module.getMaterials().removeAll(materialsFromRequest);
		
		module.getMaterialRequests().remove(materialRequest);
			
		/*Receiver receiver = new Receiver();
		receiver.removeMaterial(module.getCourse().getId(), module.getId(), materialRequest.getId());*/
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
				
		return null;	
	}
	
	public ActionForward showViewMaterialRequestActivity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		int idMaterial = Integer.valueOf((String) request.getParameter("idActivity"));
		String callFromPaddingTask = request.getParameter("callFromPaddingTask");
		
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idMaterial);
		Module module = materialRequest.getModule();
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		Person author = info.getPerson();
		
		Material material = facade.getMaterial(author, materialRequest);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(materialRequest.getDeliveryDate());

		//Cria um formul�rio dinamicamente
		ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(request, getServlet().getServletContext());
		FormBeanConfig formConfig = moduleConfig.findFormBeanConfig("materialRequestActivity");
		DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formConfig);

		DynaActionForm myForm = null;
		try {
			myForm = (DynaActionForm) dynaClass.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	

		myForm.set("nameMaterial", materialRequest.getName());
		myForm.set("descriptionMaterial", materialRequest.getDescription());
		myForm.set("deliveryDate", materialRequest.getDeliveryDate());
		
		
		Calendar today = Calendar.getInstance();
		Calendar deliveryDate = Calendar.getInstance();
		deliveryDate.setTime(materialRequest.getDeliveryDate());
		int expiredDelivery = UtilActivities.compareDates(deliveryDate,today);
		
		request.setAttribute("materialRequest", materialRequest);
		request.setAttribute("expiredDelivery", expiredDelivery);
		request.setAttribute("materialRequestActivity", myForm);
		request.setAttribute("idActivity", idMaterial);
		request.setAttribute("module", module);
		request.setAttribute("material", material);
		request.setAttribute("callFromPaddingTask", callFromPaddingTask);

		return mapping.findForward(FORWARD_SHOW_VIEW_MATERIAL_REQUEST_ACTIVITY);
	}

	public ActionForward showMaterialRequestTeacher(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		int idMaterial = Integer.valueOf((String) request.getParameter("idActivity"));
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idMaterial);
		Module module = materialRequest.getModule();
		
		//Cria um formul�rio dinamicamente
		ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(request, getServlet().getServletContext());
		FormBeanConfig formConfig = moduleConfig.findFormBeanConfig("materialRequestActivity");
		DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formConfig);

		DynaActionForm myForm = null;
		try {
			myForm = (DynaActionForm) dynaClass.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		
		myForm.set("nameMaterial", materialRequest.getName());
		myForm.set("descriptionMaterial", materialRequest.getDescription());
		myForm.set("deliveryDate", materialRequest.getDeliveryDate());
		
		request.setAttribute("materialRequestActivity", myForm);
		
		request.setAttribute("idActivity", idMaterial);
		request.setAttribute("module", module);
		request.removeAttribute("materials");
		request.setAttribute("materials", materialRequest.getMaterials());

		return mapping.findForward(FORWARD_SHOW_MATERIAL_REQUEST_TEACHER);
	}
	
	public ActionForward viewMaterialRequestActivitiesFileUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		int idActivity = Integer.valueOf((String) request.getParameter("idActivity"));
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		Person author = info.getPerson();
		
		MaterialRequest materialRequest = facade.getMaterialRequestByID(idActivity);
		
		Material material = facade.getMaterial(author, materialRequest);
		
		Calendar today = Calendar.getInstance();
		Calendar deliveryDate = Calendar.getInstance();
		deliveryDate.setTime(materialRequest.getDeliveryDate());
		int expiredDelivery = UtilActivities.compareDates(deliveryDate,today);
		
		request.setAttribute("materialRequest", materialRequest);
		request.setAttribute("expiredDelivery", expiredDelivery);
		request.setAttribute("idActivity", idActivity);
		request.setAttribute("module", materialRequest.getModule());
		request.setAttribute("material", material);
		
		return mapping.findForward(FORWARD_VIEW_MATERIAL_REQUEST_FILE_UPLOAD);
	}
	
	public ActionForward showMaterial(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) {
		
		int idActivity = Integer.valueOf((String) request.getParameter("idActivity"));

		Facade facade = Facade.getInstance();
		
		Material material = facade.getMaterialByID(idActivity);
		
		response.addHeader("Content-Disposition",
		"attachment; filename=" + material.getExtension().replace(' ', '_')); 
		response.setContentType(UtilActivities.defineContentType(material.getExtension()));
		
		OutputStream out;
		try {
			out = response.getOutputStream();
			out.write(material.getArchive().getArchive());

			out.flush();

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO - LOG - Visualizacao de Material - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_VISUALIZACAO_MATERIAL);
		log.setIdObjeto(material.getId());
		this.facade.saveLog(log);
		
		return null;
	}
	
	public ActionForward showSWF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ActionForward forward = null;
		
		int idActivity = Integer.valueOf((String) request.getParameter("idActivity"));

		Facade facade = Facade.getInstance();
		
		Material material = facade.getMaterialByID(idActivity);
		
		response.setContentType(UtilActivities.defineContentType(material.getExtension()));
		
		byte[] swf;
		
		try {
			OutputStream os;
			swf = material.getArchive().getArchive();
			os = response.getOutputStream();
			os.flush();
			os.write(swf);
			os.close();
			
			//TODO - LOG - Visualizacao de Material - OK
			Log log = SystemActions.getLogUser(request);
			log.setCodigo(Log.LOG_CODIGO_VISUALIZACAO_MATERIAL);
			log.setIdObjeto(material.getId());
			this.facade.saveLog(log);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return forward;
	}
	
	public ActionForward saveMaterialGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		Float materialGrade = Float.parseFloat(request.getParameter("materialGrade"));
		
		Material material = facade.getMaterialByID(materialId);
		
		material.setGrade(materialGrade);
		material.setCorrectedDate(new Date());
		
		facade.flush();
		
		return null;
	}
	
	public ActionForward getUserMaterialGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		if(SystemActions.isLoggedUser(request)) {
			int personId = Integer.parseInt(request.getParameter("personId")); 
			int materialRequestId = Integer.parseInt(request.getParameter("materialRequestId"));
			Float grade = null;
			
			MaterialRequest materialRequest = facade.getMaterialRequestByID(materialRequestId);
			List<Material> materials = materialRequest.getMaterials();
			
			for (Material m : materials) {
				if(m.getAuthor().getId() == personId) {
					grade = m.getGrade();
					break;
				}
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			if(grade != null){
				out.print(grade);
			} else {
				out.print("&nbsp;");
			}
		}
		
		return null;
	}
}