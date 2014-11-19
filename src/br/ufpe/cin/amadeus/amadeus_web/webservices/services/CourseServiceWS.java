package br.ufpe.cin.amadeus.amadeus_web.webservices.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;

import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.webservices.entities.CourseRemote;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
	
@WebService
public class CourseServiceWS {
	
	private Facade facadeAmadeus;
	
	private void getFacadeAmadeus(){
		this.facadeAmadeus = Facade.getInstance();
	}
	@WebMethod
	public List<CourseRemote> getAllCourses(){
		
		getFacadeAmadeus();
		List<Course> courseList = new ArrayList<Course>();
		List<CourseRemote> courseRemoteList = new ArrayList<CourseRemote>();
		
		courseList = facadeAmadeus.getAllCourses();
		
		for (int i = 0; i < courseList.size(); i++) {
			Course course = courseList.get(i);
			CourseRemote courseRemote = new CourseRemote();
			courseRemote.setName(course.getName());
			courseRemote.setId(course.getId());
			courseRemote.setContent(course.getContent());
			courseRemote.setObjectives(course.getObjectives());
			courseRemote.setMaxAmountStudentes(course.getMaxAmountStudents());
			courseRemote.setNumberOfStudentsInCourse(course.getNumberOfStudentsInCourse());
			System.out.println("SERVIDOR: "+courseRemote.getName());
			courseRemoteList.add(courseRemote);
		}
		return courseRemoteList;
	} 

}
