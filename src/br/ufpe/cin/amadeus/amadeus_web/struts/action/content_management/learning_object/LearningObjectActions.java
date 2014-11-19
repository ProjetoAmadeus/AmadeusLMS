/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.learning_object;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.HistoryLearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SettingsActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR;

public class LearningObjectActions extends DispatchAction{
	
	private Facade facade = Facade.getInstance();
	
	private final String FORWARD_SHOW_VIEW_EDIT_LEARNING_OBJECT = "fShowViewEditLearningObject";
	private final String FORWARD_SHOW_VIEW_NEW_LEARNING_OBJECT = "fShowViewNewLearningObject";
	private final String FORWARD_SHOW_VIEW_LEARNING_OBJECT_STATUS = "fLearningObjectStatus";
	private final String FORWARD_OPEN_LEARNING_OBJECT = "fOpenLearningObject.do";

	public ActionForward showViewNewLearningObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_LEARNING_OBJECT);
	}
	
	public ActionForward newLearningObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionMessages messages = new ActionMessages();
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		Module module = facade.getModuleById(idModule);
		
		LearningObject learningObject = new LearningObject();
		learningObject.setName(request.getParameter("nameLearningObject"));
		learningObject.setDescription(request.getParameter("descriptionLearningObject"));
		learningObject.setUrl(request.getParameter("urlLearningObject"));
		
		Date today = new Date();
		learningObject.setCreationDate(today);
		
		if (today.compareTo(module.getCourse().getFinalCourseDate()) > 0){
			messages.add("erros", new ActionMessage("errors.finalCourseDate"));
		}
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			learningObject.setModule(module);
			module.getLearningObjects().add(learningObject);
			
			facade.flush();
			
			// AJAX REVERSO
			
			if(SettingsActions.mobileSettings.getSmsMaterial().equals("on")){
				Receiver receiver = new Receiver();
				receiver.addMaterial(module.getCourse().getId(), module.getId(), learningObject.getId());
			}
			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;
		}
	}
	
	public ActionForward showViewEditLearningObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
		int idLearning = Integer.valueOf((String)request.getParameter("idActivity"));
		
		LearningObject learning = facade.getLearningObjectById(idLearning);
		Module module = learning.getModule();
		
		request.setAttribute("learningObject", learning);
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_LEARNING_OBJECT);
	}

	
	public ActionForward editLearningObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idLearning = Integer.valueOf((String)request.getParameter("idActivity"));
		LearningObject learning = facade.getLearningObjectById(idLearning);
		
		String newName = (String) request.getParameter("nameLearningObject");
		String newUrl = (String) request.getParameter("urlLearningObject");
		String newDescription = (String) request.getParameter("descriptionLearningObject");
		
		learning.setName(newName);
		learning.setUrl(newUrl);
		learning.setDescription(newDescription);
		
		facade.flush();
		
		UtilActivities.eraseAndWriteNameActivity(learning.getModule().getId());
		
		return null;
	}
	
	public ActionForward deleteLearningObjectActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idLearning = Integer.parseInt((String)request.getParameter("idLearningObject"));
		
		LearningObject learning = facade.getLearningObjectById(idLearning);
		
		Module module = learning.getModule();
		module.getLearningObjects().remove(learning);
		
		facade.flush();
		
		/*Receiver receiver = new Receiver();
		receiver.addMaterial(module.getCourse().getId(), module.getId(), learning.getId());*/
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		
		return null;
	}
	
	public ActionForward showViewLearningObjectActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idLearning = Integer.valueOf((String)request.getParameter("idActivity"));
		LearningObject learning = facade.getLearningObjectById(idLearning);
		
		String acessos = facade.getTotalAccessLearningObject(learning.getId()) + "";
		DateFormat dateFormat = DateFormat.getInstance();
		
		request.setAttribute("learningObject", learning);
		request.setAttribute("dateCreationLearningObject", dateFormat.format(learning.getCreationDate()));
		request.setAttribute("totalAccess", acessos);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_LEARNING_OBJECT_STATUS);
	}
	
	public ActionForward openLearningObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idLearning = Integer.parseInt( (String)request.getParameter("idLearningObject") );
		LearningObject learning = facade.getLearningObjectById(idLearning);
		
		String url = learning.getUrl();
		request.setAttribute("url", url);
		
		String urlType = "none";
		if(url.toLowerCase().endsWith("bmp") || url.toLowerCase().endsWith("gif") || url.toLowerCase().endsWith("jpg") || url.toLowerCase().endsWith("jpeg") || url.toLowerCase().endsWith("png")){
			urlType = "image";
		}else if(url.toLowerCase().endsWith("swf") || url.toLowerCase().endsWith("avi") || url.toLowerCase().endsWith("mpeg")){
			urlType = "flash";
		} else{
			urlType = "site";
		}
		
		HistoryLearningObject result = null;
		AccessInfo user = (AccessInfo)request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());
		
		String profileType = user.getTypeProfile().name();
		
		System.out.println("Usuario: " + profileType);
		System.out.println("Administrador: " + ProfileType.ADMIN.name());
		System.out.println("Professor: " + ProfileType.PROFESSOR.name());
		System.out.println("Estudante: " + ProfileType.STUDENT.name());
		
		if(profileType.equals( ProfileType.STUDENT.name() )){
			HistoryLearningObject history = new HistoryLearningObject();
			history.setPerson(user.getPerson());
			history.setLearningObject(learning);
			history.setDateAccess(new Date());
			result = facade.startSessionHistoryLearningObject(history);
		}
		
		
		if(result != null){
			String path = request.getContextPath() + "/" + FORWARD_OPEN_LEARNING_OBJECT + "?urlType=" + urlType + "&url=" + url + "&idHistory=" + result.getId();
			UtilDWR.getUtil().addFunctionCall("openLearningObjectNewWindow", path);
		}else{
			String path = request.getContextPath() + "/" + FORWARD_OPEN_LEARNING_OBJECT + "?urlType=" + urlType + "&url=" + url + "&idHistory=";
			UtilDWR.getUtil().addFunctionCall("openLearningObjectNewWindow", path);
		}
		
		
		return null;
	}
	
}
