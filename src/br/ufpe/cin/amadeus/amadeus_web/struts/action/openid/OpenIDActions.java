/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.openid;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.OpenID;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.settings.OpenIDSettings;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;

public class OpenIDActions extends org.apache.struts.actions.DispatchAction {
	
	private Facade facade = Facade.getInstance();
	private OpenIDSettings openIDSettings = OpenIDSettings.getInstance();
	
	private final String FORWARD_URL_LOGGED_USER = "fURLLoggedUser";
	
	private final String FORWARD_SHOW_VIEW_MANAGER_OPENIDS = "fShowViewManagerOpenIDs";
	private final String FORWARD_MANAGER_OPENIDS = "fManagerOpenIDs";
	
	private String messageInformation = null;
	
	public ActionForward googleAccountsRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String urlGoogle = openIDSettings.getUrlOpenID(request);
		
		return new ActionForward(urlGoogle, true);
	}
	
	public ActionForward googleAccountsResponse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String openIDIdentity = request.getParameter(this.openIDSettings.getParameterIdentity());
		String email = request.getParameter(this.openIDSettings.getParameterEXT1ValueEmail());
		String name = request.getParameter(this.openIDSettings.getParameterEXT1ValueFirstName()) +" "+ request.getParameter(this.openIDSettings.getParameterEXT1ValueLastName());
		
		OpenID openID = facade.getOpenIDByIdentity(openIDIdentity.trim());
		
		if(!SystemActions.isLoggedUser(request)) {
			if( openID != null ) {
				AccessInfo accessInfo = openID.getAccessInfo();
				forward = this.logon(mapping, form, request, response, accessInfo);
			} else if (SystemActions.webSettings.isSecurityAutoSigning()) {
				Person person = facade.getPersonFromEmail(email);
				
				if(person == null) {
					AccessInfo accessInfo = this.newUser(openIDIdentity, name, email);
					forward = this.logon(mapping, form, request, response, accessInfo);	
				} else {
					forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
				}
				 
				//forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.addNewGoogleOpenId(mapping, form, request, response);
		}
		
		return forward;
	}
	
	private ActionForward addNewGoogleOpenId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String openIDIdentity = request.getParameter(this.openIDSettings.getParameterIdentity());
		String email = request.getParameter(this.openIDSettings.getParameterEXT1ValueEmail());
		String name = request.getParameter(this.openIDSettings.getParameterEXT1ValueFirstName()) +" "+ request.getParameter(this.openIDSettings.getParameterEXT1ValueLastName());
		
		AccessInfo accessInfo = (AccessInfo) request.getSession().getAttribute("user");
		accessInfo = facade.searchUserById(accessInfo.getId());
		
		OpenID openID = facade.getOpenIDByIdentity(openIDIdentity);
		
		if(openID == null) {
			openID = new OpenID();
			openID.setIdentity(openIDIdentity);
			openID.setEmail(email);
			openID.setName(name);
			openID.setProvideBy("GOOGLE");
			openID.setAccessInfo(accessInfo);
			
			accessInfo.getOpenIDs().add(openID);
		} else {
			if(openID.getAccessInfo().equals(accessInfo)) {
				this.setMessageInformation("Este OpenID já está associado a sua conta do Amadeus.");
			} else {
				this.setMessageInformation("Um outro usuário já possui esse OpenID associado a sua conta do Amadeus. " +
						"Para mais informções contate o adminstrador do sistema.");
			}
		}
		
		return new ActionForward(mapping.findForward(FORWARD_MANAGER_OPENIDS).getPath(), true);
	}
	
	public ActionForward deleteGoogleOpenId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int openIDId = Integer.parseInt(request.getParameter("openIDId")); 
			
			AccessInfo accessInfoUserLogged = (AccessInfo) request.getSession().getAttribute("user");
			accessInfoUserLogged = facade.searchUserById(accessInfoUserLogged.getId());
			
			OpenID openID = facade.getOpenIDById(openIDId);
			AccessInfo accessInfo = openID.getAccessInfo();
			
			//Security
			if(accessInfo.equals(accessInfoUserLogged)) {
				for(int x = 0; x < accessInfo.getOpenIDs().size(); x++) {
					if(accessInfo.getOpenIDs().get(x).getId() == openID.getId()) {
						accessInfo.getOpenIDs().remove(x);
						break;
					}
				}
				facade.flush();
			}
			
			forward = new ActionForward(mapping.findForward(FORWARD_MANAGER_OPENIDS).getPath(), true);
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewManagerOpenIDs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			AccessInfo accessInfo = (AccessInfo) request.getSession().getAttribute("user");
			accessInfo = facade.searchUserById(accessInfo.getId());
			
			List<OpenID> openIDs = accessInfo.getOpenIDs();
			
			request.setAttribute("openIDs", openIDs);
			
			if(this.getMessageInformation() != null) {
				request.setAttribute("messageInformation", this.getMessageInformation());
				this.setMessageInformation(null);
			}
			
			ProfileType loggedProfile = accessInfo.getTypeProfile();
			
			boolean canRequestTeaching = false;
			
			if (!(loggedProfile == ProfileType.ADMIN || loggedProfile == ProfileType.PROFESSOR) 
	   				&& facade.canRequestTeaching(accessInfo.getPerson())) {
	   			canRequestTeaching = true;
	   		}
			
			request.setAttribute("canRequestTeaching", canRequestTeaching);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_MANAGER_OPENIDS);
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	private ActionForward logon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, AccessInfo accessInfo) {
		
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user", accessInfo);
		
		return new ActionForward(mapping.findForward(FORWARD_URL_LOGGED_USER).getPath(), true);
	}
	
	private AccessInfo newUser(String openIDIdentity, String name, String email) throws InvalidUserException {
		Person p = new Person();
		p.setName(name);
		p.setEmail(email);

		AccessInfo accInfo = new AccessInfo();
		accInfo.setLogin(email);
		accInfo.setPassword("he9x2a");

		accInfo.setTypeProfile(ProfileType.STUDENT);
		accInfo.setPerson(p);
		p.setAccessInfo(accInfo);

		facade.insertPerson(p);
		facade.confirmRegistry(p.getAccessInfo());
		
		OpenID openID = new OpenID();
		openID.setAccessInfo(accInfo);
		openID.setIdentity(openIDIdentity);
		openID.setEmail(email);
		openID.setName(name);
		openID.setProvideBy("GOOGLE");
		
		accInfo.getOpenIDs().add(openID);
		
		return accInfo;
	}
	
	private String getMessageInformation(){
		return this.messageInformation;
	}
	
	private void setMessageInformation(String message) {
		this.messageInformation = message;
	}
}
