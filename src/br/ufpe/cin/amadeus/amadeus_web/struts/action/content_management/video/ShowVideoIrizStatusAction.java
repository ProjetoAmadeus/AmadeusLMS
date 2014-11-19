/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YtPublicationState;

public class ShowVideoIrizStatusAction extends Action {

	public static final String FORWARD_VIDEO_STATUS = "fVideoStatus";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Facade facade = Facade.getInstance();
		int idActivity = Integer.valueOf(request.getParameter("idActivity"));

		VideoIriz video = facade.getVideoByID(idActivity);
		String status = "activities.video.status.ok";
		String okStatus = "activities.video.status.ok";
		String watchLink = "";

		if (video.isUploaded()) {

			YouTubeService service = new YouTubeService(
					"ytapi-Amadeus-Amadeus-31t3bfbl-0",
					"AI39si4lPYVDXG8xROx-bAoe-vIODvTOcucDwRb-"
							+ "_2Ty6pesAsS3R2ybTp6D44a4FZy3Wi0dqls44Cur0-qiuDLCNdihPYBRmw");

			service.setAuthSubToken("CIyFvq-NDRCrmPDjBQ", null);

			String feedUrl = "http://gdata.youtube.com/feeds/api/users/amadeuslms/uploads";

			VideoFeed videoFeed;
			VideoEntry entry = null;
			try {
				videoFeed = service.getFeed(new URL(feedUrl), VideoFeed.class);

				for (VideoEntry videoInstance : videoFeed.getEntries()) { //search for the video entry
					if (UtilActivities.getVideoId(videoInstance.getId())
							.equals(video.getYoutubeId())) {
						entry = videoInstance;
					}
				}
				
				if(entry == null){
					status = "activities.video.status.invalidURL";
				}
				if (entry.isDraft()) {
					YtPublicationState pubState = entry.getPublicationState();
				if (pubState.getState() == YtPublicationState.State.PROCESSING) {
					status = "activities.video.status.processing";
				} else if (pubState.getState() == YtPublicationState.State.REJECTED) {
					status = "activities.video.status.rejected";
					//System.out.println(pubState.getDescription()); // why it was rejected
					//System.out.print("For help visit: ");
					//System.out.println(pubState.getHelpUrl());
				} else if (pubState.getState() == YtPublicationState.State.FAILED) {
					status = "activities.video.status.failed";
					//System.out.println(pubState.getDescription()); //why it failed
					//System.out.print("For help visit: ");
					//System.out.println(pubState.getHelpUrl());
				}
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(status.equals("activities.video.status.ok")){
			watchLink = "<a onclick='watchVideo" +
			"('"+ request.getContextPath() +"/jsp/course/content_management" +
			"/activities/video/iriz.jsp?idVideo="+ video.getYoutubeId() +"');' href='javascript" +
			":void(0);' ><bean:message key='activities.video.watch' /></a>";
		}else{
			watchLink = "<bean:message key='activities.video.watch' />";
		}

		request.setAttribute("watchLink", watchLink);
		request.setAttribute("video", video);
		request.setAttribute("status", status);
		request.setAttribute("okStatus", okStatus);
		
		return mapping.findForward(FORWARD_VIDEO_STATUS);
	}

}
