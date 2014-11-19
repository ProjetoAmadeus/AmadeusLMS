package br.ufpe.cin.amadeus.amadeus_web.permissions.settings;

import javax.servlet.http.HttpServletRequest;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;

public class WebSettingsPermissions {

	public static boolean userCanShowViewAdminSettings(HttpServletRequest request) {
		boolean userCanShowViewAdminSettings = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewAdminSettings = true;
		}
		
		return userCanShowViewAdminSettings;
	}
	
	public static boolean userCanSaveWebSecuritySettings(HttpServletRequest request) {
		boolean userCanSaveWebSecurity = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanSaveWebSecurity = true;
		}
		
		return userCanSaveWebSecurity;
	}
	
	public static boolean userCanShowViewWebMailSenderSettings(HttpServletRequest request) {
		boolean userCanShowViewWebMailSenderSettings = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewWebMailSenderSettings = true;
		}
		
		return userCanShowViewWebMailSenderSettings;
	}
	
	public static boolean userCanSaveWebMailSenderSettings(HttpServletRequest request) {
		boolean userCanSaveWebMailSenderSettings = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanSaveWebMailSenderSettings = true;
		}
		
		return userCanSaveWebMailSenderSettings;
	}
}
