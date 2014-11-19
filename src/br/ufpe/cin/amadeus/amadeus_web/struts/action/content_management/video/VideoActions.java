/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jdom.JDOMException;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.FormUploadToken;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidVideoException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.CourseActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR;

public class VideoActions extends DispatchAction {
	
	private Facade facade = Facade.getInstance();
	
	public static final String FORWARD_SHOW_VIEW_YOUTUBE_CHOOSE_VIDEO_ORIGIN = "fShowViewYoutubeChooseVideoOrigin";
	public static final String FORWARD_NEW_VIDEO_URL = "fVideoURL";
	public static final String FORWARD_UPLOAD_TO_YOUTUBE = "fVideoUploadToYoutube";
	public final String FORWARD_SHOW_VIEW_EDIT_VIDEO_ACTIVITY = "fShowViewEditVideoActivity";
	
	
	public ActionForward showViewYoutubeChooseVideoOrigin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		return mapping.findForward(FORWARD_SHOW_VIEW_YOUTUBE_CHOOSE_VIDEO_ORIGIN);
	}
	
	public ActionForward createVideoURL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {

		
		try {
			URL url = new URL("http://www.youtube.com/watch?v=XFArQ6Ly5QE");

			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							url.openStream()));

			String inputLine = "";
			String resultado = "";
			List<String> codigo = new ArrayList<String>();

			while ((inputLine = in.readLine()) != null)
				codigo.add(inputLine);

			in.close();
			
			for(int i = 0; i < 300; i++){
				resultado = resultado + codigo.get(i) + "\n";
			}
			System.out.println(resultado);
			UtilDWR.getUtil().addFunctionCall("getVideoURL", resultado);
			
		} catch (MalformedURLException e) {
			System.out.println("%$%$%$%$%$%$%$%$%   URL inv�lida!!   %$%$%$%$%$%$%$%$%$");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}	
	
	public ActionForward chooseFileOrigin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionMessages messages = new ActionMessages();
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		
		Module module = facade.getModuleById(idModule);
		
		String choice = (String)request.getParameter("choice");
		String videoIrizName = (String)request.getParameter("videoIrizName");
		String description = (String)request.getParameter("videoDescription");
		String forward = "";
		
		try{
			facade.validateVideoStep1(videoIrizName, description);
		}catch (InvalidVideoException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		if(messages.isEmpty()){
		
			if(choice.equalsIgnoreCase("url")){
				forward = FORWARD_NEW_VIDEO_URL;
			}else if(choice.equalsIgnoreCase("upload")){ 
				
				forward = FORWARD_UPLOAD_TO_YOUTUBE;
				YouTubeService service = new YouTubeService(
						"ytapi-Amadeus-Amadeus-31t3bfbl-0", "AI39si4lPYVDXG8xROx-bAoe-vIODvTOcucDwRb-" +
								"_2Ty6pesAsS3R2ybTp6D44a4FZy3Wi0dqls44Cur0-qiuDLCNdihPYBRmw");
	
				VideoEntry newEntry = new VideoEntry();
				YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
				mg.setTitle(new MediaTitle());
				mg.getTitle().setPlainTextContent(videoIrizName);
				mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "Education"));
				mg.setKeywords(new MediaKeywords());
				mg.getKeywords().addKeyword("Amadeus");
				mg.getKeywords().addKeyword("EAD");
				mg.setDescription(new MediaDescription());
				mg.getDescription().setPlainTextContent(description);
				mg.setPrivate(false);
	
				newEntry.setGeoCoordinates(new GeoRssWhere(37.0,-122.0));
				
				try {
					service.setAuthSubToken("CIyFvq-NDRCrmPDjBQ", null);
					URL uploadUrl = new URL("http://gdata.youtube.com/action/GetUploadToken");
					FormUploadToken token = service.getFormUploadToken(uploadUrl, newEntry);
					request.setAttribute("url", token.getUrl());
					request.setAttribute("token", token.getToken());
					request.setAttribute("contextPath", request.getContextPath());
					request.setAttribute("address", request.getServerName());
					request.setAttribute("port", request.getServerPort());
					request.setAttribute("module", module);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			saveErrors(request, messages);
			request.setAttribute("videoIrizName", videoIrizName);
			request.setAttribute("videoDescription", description);
			return this.showViewYoutubeChooseVideoOrigin(mapping, form, request, response);
		}
		
		request.setAttribute("module", module);
		request.setAttribute("videoIrizName", videoIrizName);
		request.setAttribute("videoDescription", description);
		
		return mapping.findForward(forward);
	}

	public ActionForward deleteVideoActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int idVideo = Integer.valueOf(request.getParameter("idActivity"));
		
		VideoIriz video = facade.getVideoByID(idVideo);
		Module module = video.getModule();
		
		module.getVideos().remove(video);
		
		/*Receiver receiver = new Receiver();
		receiver.removeMaterial(module.getCourse().getId(), module.getId(), video.getId());*/
		
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		
		return null;
	}
	
	public ActionForward showViewEditVideoActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		
		VideoIriz video = facade.getVideoByID(videoId);
		
		request.setAttribute("video", video);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_VIDEO_ACTIVITY);
	}
	
	public ActionForward editVideoActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages messages = new ActionMessages();
		
		String name = request.getParameter("videoIrizName");
		String description = request.getParameter("videoDescription");
		String url = request.getParameter("url");
		String youtubeId = "";
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		
		try {
			facade.validateVideoURL(url);
			youtubeId = UtilActivities.getVideoIdFromURL(url);
			facade.validateVideoStep1(name, description);
			
		} catch (InvalidVideoException e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}
		
		VideoIriz video = facade.getVideoByID(videoId);
		
		if(messages.isEmpty()){
			video.setName(name);
			video.setDescription(description);
			video.setYoutubeId(youtubeId);
			
			facade.flush();
			
			UtilActivities.eraseAndWriteNameActivity(video.getModule().getId());
		}else {
			saveErrors(request, messages);
			request.setAttribute("video", video);
			return mapping.getInputForward();
		}
		
		return null;
	}
	
	public ActionForward newVideoIrizUploadActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

	 	int lastVideoId = (Integer) request.getSession().getAttribute("lastVideoId");
		String youtubeId = request.getParameter("id");
		
		VideoIriz video = facade.getVideoByID(lastVideoId);
		video.setYoutubeId(youtubeId);
		
		facade.flush();
		
		request.getSession().removeAttribute("lastVideoId");
		
		PrintWriter out = response.getWriter();
		out.print("<script type=\"text/javascript\">top.eraseAndWriteNameActivity("+video.getModule().getId()+","+video.getModule().getPosition()+")</script>");
		
		return null;
	}

}
