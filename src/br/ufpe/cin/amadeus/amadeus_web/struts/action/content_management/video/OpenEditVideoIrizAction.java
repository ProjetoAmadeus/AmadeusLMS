/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class OpenEditVideoIrizAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Facade facade = Facade.getInstance();
		
		int videoId = Integer.valueOf(request.getParameter("idActivity"));
		
		VideoIriz video = facade.getVideoByID(videoId);
		
		request.setAttribute("videoIrizName", video.getName());
		request.setAttribute("videoDescription", video.getDescription());
		request.setAttribute("id", video.getId());
		request.setAttribute("youtubeId", video.getYoutubeId());
		
		return mapping.findForward("fOpenEditVideoIriz");
	}	
}
