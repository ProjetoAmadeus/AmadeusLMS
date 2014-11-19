package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.permissions.settings.WebSettingsPermissions;

public class SettingsActions extends SystemActions {

	public Facade facade = Facade.getInstance();
	
	private final String FORWARD_SHOW_VIEW_ADMIN_SETTINGS = "fShowViewAdminSettins";
	private final String FORWARD_SHOW_VIEW_WEB_SECURITY_SETTINGS = "fShowViewWebSecuritySettings";
	private final String FORWARD_SHOW_VIEW_WEB_MAIL_SENDER_SETTINGS = "fShowViewWebMailSenderSettings";
	private final String FORWARD_SHOW_VIEW_MOBILE_SETTINGS = "fShowViewMobileSettings";
	
	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("settingsActions.showViewSettings", "showViewSettings");
		map.put("settingsActions.saveWebSecuritySettings", "saveWebSecuritySettings");
		map.put("settingsActions.showViewWebMailSenderSettings", "showViewWebMailSenderSettings");
		map.put("settingsActions.saveWebMailSenderSettings", "saveWebMailSenderSettings");
		map.put("settingsActions.showViewMobileSettings", "showViewMobileSettings");
		map.put("settingsActions.saveMobileSettings", "saveMobileSettings");
		return map;
	}
	
	public ActionForward showViewMobileSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			if(WebSettingsPermissions.userCanShowViewAdminSettings(request)) {
				request.setAttribute("mobileSettings", SystemActions.mobileSettings);
				forward = mapping.findForward(FORWARD_SHOW_VIEW_MOBILE_SETTINGS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}

	public ActionForward showViewSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			if(WebSettingsPermissions.userCanShowViewAdminSettings(request)) {
				request.setAttribute("webSettings", SystemActions.webSettings);
				forward = mapping.findForward(FORWARD_SHOW_VIEW_ADMIN_SETTINGS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	private ActionForward showViewAdminSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("webSettings", SystemActions.webSettings);
		return mapping.findForward(FORWARD_SHOW_VIEW_ADMIN_SETTINGS);
	}
	
	private ActionForward showViewWebSecuritySettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("webSettings", SystemActions.webSettings);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_WEB_SECURITY_SETTINGS);
	}
	
	public ActionForward saveWebSecuritySettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			if(WebSettingsPermissions.userCanSaveWebSecuritySettings(request)) {
				ActionMessages messages = new ActionMessages();
				
				boolean autoSigning = Boolean.parseBoolean(request.getParameter("autoSigning"));
				
				SystemActions.webSettings.setSecurityAutoSigning(autoSigning);
				
				if(messages.isEmpty()){
					request.setAttribute("success", true);
				} else {
					saveErrors(request, messages);
				}
				
				forward = this.showViewWebSecuritySettings(mapping, form, request, response);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewWebMailSenderSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			if(WebSettingsPermissions.userCanShowViewWebMailSenderSettings(request)){
				String description = request.getParameter("description");
				String server = request.getParameter("server");
				String port = request.getParameter("port");
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String securityConnection = request.getParameter("securityConnection");
			
				request.setAttribute("description", description);
				request.setAttribute("server", server);
				request.setAttribute("port", port);
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("securityConnection", securityConnection);
				
				forward = mapping.findForward(FORWARD_SHOW_VIEW_WEB_MAIL_SENDER_SETTINGS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward saveWebMailSenderSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)){
			if(WebSettingsPermissions.userCanSaveWebMailSenderSettings(request)){
				ActionMessages messages = new ActionMessages();
				
				String mailDescription = request.getParameter("description");
				String mailSmtpHost = request.getParameter("server");
				Integer mailPort = Integer.parseInt(request.getParameter("port"));
				String mailFrom = request.getParameter("userName");
				String mailPassword = request.getParameter("password");
				String securityConnection = request.getParameter("securityConnection");
			
				if(securityConnection != null && !securityConnection.equals("withoutSecurityConnection")) {
					
					if(mailFrom == null || mailFrom.trim().equals("")){
						messages.add("error", new ActionMessage("errors.required",
								getResources(request).getMessage("settings.web.mailSender.userName")));
					}
					if(mailPassword == null || mailPassword.trim().equals("")){
						messages.add("error", new ActionMessage("errors.required",
								getResources(request).getMessage("settings.web.mailSender.password")));
					}
				}
				
				if(messages.isEmpty()){
					
					SystemActions.webSettings.setMailDescription(mailDescription);
					SystemActions.webSettings.setMailSmtpHost(mailSmtpHost);
					SystemActions.webSettings.setMailPort(mailPort);
					SystemActions.webSettings.setMailFrom(mailFrom);
					SystemActions.webSettings.setMailPassword(mailPassword);
					
					if(securityConnection.equals("withoutSecurityConnection")) {
						
						SystemActions.webSettings.setMailSmtpAuth(false);
						SystemActions.webSettings.setMailSSLEnable(false);
						SystemActions.webSettings.setMailStartTLSEnable(false);
						SystemActions.webSettings.setMailStartTLSRequired(false);
					
					} else if(securityConnection.equals("startTLSEnable")){
					
						SystemActions.webSettings.setMailSmtpAuth(true);
						SystemActions.webSettings.setMailSSLEnable(false);
						SystemActions.webSettings.setMailStartTLSEnable(true);
						SystemActions.webSettings.setMailStartTLSRequired(false);
					
					} else if(securityConnection.equals("startTLSRequired")) {
					
						SystemActions.webSettings.setMailSmtpAuth(true);
						SystemActions.webSettings.setMailSSLEnable(false);
						SystemActions.webSettings.setMailStartTLSEnable(false);
						SystemActions.webSettings.setMailStartTLSRequired(true);
					
					} else if(securityConnection.equals("SSLEnable")) {
					
						SystemActions.webSettings.setMailSmtpAuth(true);
						SystemActions.webSettings.setMailSSLEnable(true);
						SystemActions.webSettings.setMailStartTLSEnable(false);
						SystemActions.webSettings.setMailStartTLSRequired(false);
					
					}
					
					request.setAttribute("success", true);
				} else {
					saveErrors(request, messages);
				}
				
				forward = this.showViewWebMailSenderSettings(mapping, form, request, response);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return  forward;
	}
	
	public ActionForward saveMobileSettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)){
			if(WebSettingsPermissions.userCanSaveWebMailSenderSettings(request)){
				ActionMessages messages = new ActionMessages();
				
				String loginHumanMobile = request.getParameter("login");
				String emailHumanMobile = request.getParameter("email");
				String passwordHumanMobile = request.getParameter("password");
				String smsMaterial = request.getParameter("smsMaterial");
				String smsPoll = request.getParameter("smsPoll");
				String smsVideo = request.getParameter("smsVideo");
				String smsGame = request.getParameter("smsGame");
				String smsLearningObject = request.getParameter("smsLearningObject");
				String smsForum = request.getParameter("smsForum");
				System.out.println(smsForum);
				System.out.println(smsPoll);
				System.out.println(smsVideo);
				System.out.println(smsGame);
				System.out.println(smsLearningObject);
				System.out.println(smsMaterial);
				
				
				
				if(messages.isEmpty()){
					
					if(loginHumanMobile!= null)
						SystemActions.mobileSettings.setLogin(loginHumanMobile);
					if(emailHumanMobile!=null)
						SystemActions.mobileSettings.setEmail(emailHumanMobile);
					if(passwordHumanMobile!=null)
						SystemActions.mobileSettings.setPassword(passwordHumanMobile);
					
					if(smsForum != null)
						SystemActions.mobileSettings.setSmsForum("on");
					else
						SystemActions.mobileSettings.setSmsForum("off");
					if(smsVideo != null)
						SystemActions.mobileSettings.setSmsVideo("on");
					else
						SystemActions.mobileSettings.setSmsVideo("off");
					if(smsLearningObject != null)
						SystemActions.mobileSettings.setSmsLearningObject("on");
					else
						SystemActions.mobileSettings.setSmsLearningObject("off");
					if(smsGame != null)
						SystemActions.mobileSettings.setSmsGame("on");
					else
						SystemActions.mobileSettings.setSmsGame("off");
					if(smsMaterial != null)
						SystemActions.mobileSettings.setSmsMaterial("on");
					else
						SystemActions.mobileSettings.setSmsMaterial("off");
					if(smsPoll != null)
						SystemActions.mobileSettings.setSmsPoll("on");
					else
						SystemActions.mobileSettings.setSmsPoll("off");
					
					/*if(smsHumanMobile != null){
						if(smsHumanMobile.equals("sms")){
							SystemActions.mobileSettings.setSmsMaterial("on");
							SystemActions.mobileSettings.setSmsForum("on");
							SystemActions.mobileSettings.setSmsPoll("on");
							SystemActions.mobileSettings.setSmsLearningObject("on");
							SystemActions.mobileSettings.setSmsVideo("on");
							SystemActions.mobileSettings.setSmsGame("on");
						}
					}else{
						SystemActions.mobileSettings.setSmsMaterial("off");
						SystemActions.mobileSettings.setSmsForum("off");
						SystemActions.mobileSettings.setSmsPoll("off");
						SystemActions.mobileSettings.setSmsLearningObject("off");
						SystemActions.mobileSettings.setSmsVideo("off");
						SystemActions.mobileSettings.setSmsGame("off");
					}*/
					SystemActions.mobileSettings.setLogin(loginHumanMobile);
					request.setAttribute("success", true);
				} else {
					saveErrors(request, messages);
				}
				forward = this.showViewMobileSettings(mapping, form, request, response);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			System.out.println("LALA3!!! Humam Mobile!!!");
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return  forward;
	}

}
