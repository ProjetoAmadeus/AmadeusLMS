package br.ufpe.cin.amadeus.amadeus_web.permissions.register;

import javax.servlet.http.HttpServletRequest;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;

public class UserPermissions {

	public static boolean userCanShowViewAllCoursesInManagerUsers(AccessInfo user) {
		boolean userCanShowViewAllCoursesInManagerUsers = false;
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewAllCoursesInManagerUsers = true;
		}
		
		return userCanShowViewAllCoursesInManagerUsers;
	}

	public static boolean userCanShowViewAllUsersInManagerUsers(AccessInfo user) {
		boolean userCanShowViewAllUsersInManagerUsers = false;
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewAllUsersInManagerUsers = true;
		}
		
		return userCanShowViewAllUsersInManagerUsers;
	}
	
	public static boolean userCanShowViewUserNewInManagerUsers(HttpServletRequest request) {
		boolean userCanShowViewUserNewInManagerUsers = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewUserNewInManagerUsers = true;
		}
		
		return userCanShowViewUserNewInManagerUsers;
	}
	
	public static boolean userCanUserNewInManagerUsers(HttpServletRequest request) {
		boolean userCanUserNewInManagerUsers = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanUserNewInManagerUsers = true;
		}
		
		return userCanUserNewInManagerUsers;
	}
	
	public static boolean userCanShowViewUserProfileInManagerUsers(HttpServletRequest request) {
		boolean userCanShowViewUserProfileInManagerUsers = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanShowViewUserProfileInManagerUsers = true;
		}
		
		return userCanShowViewUserProfileInManagerUsers;
	}
	
	public static boolean userCanShowViewSendMailInManagerUsers(HttpServletRequest request) {
		boolean userCanViewSendMailInManagerUsers = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanViewSendMailInManagerUsers = true;
		}
		
		return userCanViewSendMailInManagerUsers;
	}
	
	public static boolean userCanSendMailInManagerUsers(HttpServletRequest request) {
		boolean userCanSendMailInManagerUsers = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanSendMailInManagerUsers = true;
		}
		
		return userCanSendMailInManagerUsers;
	}
	
}
