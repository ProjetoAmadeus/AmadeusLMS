/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidVideoException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR;

public class NewVideoIrizFromURLAction extends org.apache.struts.actions.DispatchAction{

	public ActionForward newVideoIrizFromURL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Facade facade = Facade.getInstance();
		ActionMessages messages = new ActionMessages();
		
		String name = request.getParameter("videoIrizName");
		String description = request.getParameter("videoDescription");
		String url = request.getParameter("url");
		String youtubeId = "" ;
		int idModule = Integer.valueOf(request.getParameter("idModule"));
		
		try {
			facade.validateVideoURL(url);
			youtubeId = UtilActivities.getVideoIdFromURL(url);
			
		} catch (InvalidVideoException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		Module module;
		
		if(messages.isEmpty()){
			
			VideoIriz video = new VideoIriz();
			video.setName(name);
			video.setDescription(description);
			video.setUploaded(false);
			video.setYoutubeId(youtubeId);
			video.setCreationDate(new Date());
			
			module = facade.getModuleById(idModule);
			video.setModule(module);
			module.getVideos().add(video);
			
			facade.flush();
			
			if(SystemActions.mobileSettings.getSmsVideo().equals("on")){
				Receiver receiver = new Receiver();
				receiver.addMaterial(module.getCourse().getId(), module.getId(), video.getId());
			}
			
			UtilActivities.eraseAndWriteNameActivity(module.getId());
	
		} else {
			saveErrors(request, messages);
			request.setAttribute("videoIrizName", name);
			request.setAttribute("videoDescription", description);
			request.setAttribute("url", url);
			return mapping.getInputForward();
		}
		
		UtilDWR.getUtil().addFunctionCall("cancelShowListActivity", module.getPosition());
		return null;
	}
	
}
