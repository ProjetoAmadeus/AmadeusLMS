package br.ufpe.cin.amadeus.amadeus_web.permissions.content_management;

import javax.servlet.http.HttpServletRequest;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class CoursePermissions {
	
	public static boolean userCanShowViewCourseEvaluations(HttpServletRequest request, Course course) {
		boolean userCanViewCourseEvaluations = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		ProfileType userProfileType = user.getTypeProfile();
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userRoleInCourse != null) {			
				if(userProfileType == ProfileType.ADMIN || 
						userRoleInCourse.getRoleType() == RoleType.TEACHER ||
						userRoleInCourse.getRoleType() == RoleType.ASSISTANT) {
					userCanViewCourseEvaluations = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanViewCourseEvaluations;
	}

	public static boolean userCanShowViewShowModules(HttpServletRequest request, Course course) {
		boolean userCanShowViewShowModules = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		ProfileType userProfileType = user.getTypeProfile();
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userProfileType == ProfileType.ADMIN) {
				userCanShowViewShowModules = true;
			} else if(userRoleInCourse != null) {			
				if(userRoleInCourse.getRoleType() == RoleType.TEACHER ||
						userRoleInCourse.getRoleType() == RoleType.ASSISTANT ||
						userRoleInCourse.getRoleType() == RoleType.STUDENT) {
					userCanShowViewShowModules = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanShowViewShowModules;
	}
	
	public static boolean userCanShowViewSendMail(HttpServletRequest request, Course course) {
		boolean userCanShowViewSendMail = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		ProfileType userProfileType = user.getTypeProfile();
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userRoleInCourse != null) {			
				if(userProfileType == ProfileType.ADMIN || 
						userRoleInCourse.getRoleType() == RoleType.TEACHER ||
						userRoleInCourse.getRoleType() == RoleType.ASSISTANT) {
					userCanShowViewSendMail = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanShowViewSendMail;
	}
	
	public static boolean userCanSendMailForCourseParticipants(HttpServletRequest request, Course course) {
		boolean userCanSendMailForCourseParticipants = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		ProfileType userProfileType = user.getTypeProfile();
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userRoleInCourse != null) {			
				if(userProfileType == ProfileType.ADMIN || 
						userRoleInCourse.getRoleType() == RoleType.TEACHER ||
						userRoleInCourse.getRoleType() == RoleType.ASSISTANT) {
					userCanSendMailForCourseParticipants = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanSendMailForCourseParticipants; 
	}
	
	public static boolean userCanViewGraphic(HttpServletRequest request, Course course) {
		boolean userCanViewGraphic = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userRoleInCourse != null) {				
				userCanViewGraphic = true;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanViewGraphic; 
	}
	
	public static boolean userCanViewGroups(HttpServletRequest request, Course course) {
		boolean userCanViewGroups = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		Role userRoleInCourse;
		
		try {
			userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			if(userRoleInCourse != null) {				
				userCanViewGroups = true;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCanViewGroups; 
	}
	
	public static boolean userCanCreateGroups(HttpServletRequest request, Course course) {
		boolean userCanCreateGroups = true;		

		try {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			
			Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			
			if(userRoleInCourse != null) {
				if(userRoleInCourse.getRoleType() == RoleType.STUDENT)
				{
					userCanCreateGroups = Facade.getInstance().pesronHaveGroup(course.getId(), user.getPerson().getId());
				}				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return userCanCreateGroups; 
	}

	
}
