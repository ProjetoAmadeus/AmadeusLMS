package br.ufpe.cin.amadeus.amadeus_web.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.Course;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.Service;

@Path("/user")
public class UserResource{
	
	@GET
	@Path("/validate/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> validateUser(@PathParam("login") String login, @PathParam("password") String password){
		
		Service service = new Service();
		AccessInfo userInfo = new AccessInfo();
		userInfo.setLogin(login);
		userInfo.setPassword(password);
		
		boolean test = Facade.getInstance().validateUser(userInfo);
		if (test) {
			service.setValidAccessInfo(true);
		}else{
			service.setValidAccessInfo(false);
		}
		
		List<Service> services = new ArrayList<Service>();
		services.add(service);
		return services;
	}
	
	@GET
	@Path("/countcourses/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> countCourses(@PathParam("login") String login, @PathParam("password") String password){
		Service service = new Service();
		AccessInfo userInfo = new AccessInfo();
		userInfo.setLogin(login);
		userInfo.setPassword(password);
		
		List<Course> listCourses = Facade.getInstance().getCoursesByUserSyncronize(userInfo);
		service.setCountCourses(listCourses.size());
		
		List<Service> services = new ArrayList<Service>();
		services.add(service);
		return services;
	}
			
	@GET
	@Path("/service/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> getCoursesByUser(@PathParam("login") String login, @PathParam("password") String password){
		
		Service service = new Service();
		AccessInfo userInfo = new AccessInfo();
		userInfo.setLogin(login);
		userInfo.setPassword(password);
		
		List<Course> listCourses = Facade.getInstance().getCoursesByUserSyncronize(userInfo);
		List<PersonRoleCourse> prcList = Facade.getInstance().getStudentByUser(userInfo);
				
		List<Service> services = new ArrayList<Service>();
		service.setListCourses(listCourses);
		service.setListPersons(prcList);
		services.add(service);
			
		
		return services;
	}
		
	@GET
	@Path("/material/{id}")
	@Produces("application/pdf")
	public Response getMaterial(@PathParam("id") String material_id) {

		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));
		
		byte[] pdfContent = material.getArchive().getArchive();
		
		return Response.ok().entity(pdfContent).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
 
	}
	
	@GET
	@Path("/material/sound/mp3/{id}")
	@Produces("audio/mpeg")
	public Response getMaterialSoundMp3(@PathParam("id") String material_id) {

		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));
		
		byte[] mp3Content = material.getArchive().getArchive();
		
		return Response.ok().entity(mp3Content).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
 
	}
	
	@GET
	@Path("/material/sound/wav/{id}")
	@Produces("audio/vnd.wave")
	public Response getMaterialSoundWav(@PathParam("id") String material_id) {

		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));
		
		byte[] wavContent = material.getArchive().getArchive();
		
		return Response.ok().entity(wavContent).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
 
	}
	
	@GET
	@Path("/material/ppt/{id}")
	@Produces("application/vnd.ms-powerpoint")
	public Response getMaterialPPT(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialPPT = material.getArchive().getArchive();

		return Response.ok().entity(materialPPT).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/pptx/{id}")
	@Produces("application/vnd.openxmlformats-officedocument.presentationml.presentation")
	public Response getMaterialPPTX(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialPPTX = material.getArchive().getArchive();

		return Response.ok().entity(materialPPTX).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/ppsx/{id}")
	@Produces("application/vnd.openxmlformats-officedocument.presentationml.slideshow")
	public Response getMaterialPPSX(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialPPSX = material.getArchive().getArchive();

		return Response.ok().entity(materialPPSX).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/doc/{id}")
	@Produces("application/msword")
	public Response getMaterialDOC(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialDOC = material.getArchive().getArchive();

		return Response.ok().entity(materialDOC).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/docx/{id}")
	@Produces("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	public Response getMaterialDOCX(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialDOCX = material.getArchive().getArchive();

		return Response.ok().entity(materialDOCX).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/jpg/{id}")
	@Produces("image/jpeg")
	public Response getMaterialJPG(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialJPG = material.getArchive().getArchive();

		return Response.ok().entity(materialJPG).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/material/png/{id}")
	@Produces("image/png")
	public Response getMaterialPNG(@PathParam("id") String material_id){
		
		Material material = Facade.getInstance().findMaterialByID(Integer.parseInt(material_id));

		byte[] materialPNG = material.getArchive().getArchive();

		return Response.ok().entity(materialPNG).header("Content-Disposition","attachment; filename="+ material.getExtension()).build();
	}
	
	@GET
	@Path("/photo/{login}")
	@Produces("image/png")
	public Response getPhoto(@PathParam("login") String login) {

		Person person = Facade.getInstance().getPersonByLogin(login);
		
		byte[] photoContent = null;
		
		try {
			photoContent = person.getImage().getPhoto();
		} catch (Exception e) {
			
		}
		
		return Response.ok().entity(photoContent).header("Content-Disposition","attachment; filename=image_user.png").build();
 
	}
	
	@POST
	@Path("/socialHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> insertSocialHistory(@FormParam("course_name") String course_name, 
			@FormParam("user_name") String user_name, @FormParam("action_value") String actionValue, 
			@FormParam("resource_name") String resource_name, @FormParam("creation_date") String creationDate){
		
						
		AmadeusDroidHistoric historic = new AmadeusDroidHistoric();
		historic.setId(0);
		historic.setCourseName(course_name);
		historic.setUserName(user_name);
		historic.setResourceName(resource_name);
		historic.setActionValue(actionValue);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = dateFormat.parse(creationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		historic.setCreationDate(data);
		
		Service service = new Service();
		List<Service> services = new ArrayList<Service>();
		
		List<AmadeusDroidHistoric> listHistoric = new ArrayList<AmadeusDroidHistoric>();
		AmadeusDroidHistoric result = Facade.getInstance().insertSocialHistory(historic);
		listHistoric.add(result);
		service.setListHistoric(listHistoric);
		
		return services;
	}
	
	@POST
	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> saveForumMessage(@FormParam("body") String body, 
			                     @FormParam("person") String person_id, 
			                     @FormParam("forum") String forum_id){
		
		Service service = new Service();
		List<Service> services = new ArrayList<Service>();
		
		Message message = new Message();
		message.setBody(body);
		message.setDate(new Date());
		Person author = new Person();
		author.setId(Integer.parseInt(person_id));
		message.setAuthor(author);
		
		Forum forum = Facade.getInstance().getForumById(Integer.parseInt(forum_id));
		message.setForum(forum);
		
		forum.getMessages().add(message);
		Facade.getInstance().updateForum(forum);
		br.ufpe.cin.amadeus.amadeus_web.syncronize.Message msg = Facade.getInstance().getLastMessage();
		service.setMessage(msg);
		services.add(service);
		
		return services;
	}
	
	@GET
	@Path("/history")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> getListSocialHistory(){
		List<AmadeusDroidHistoric> retrn = new ArrayList<AmadeusDroidHistoric>();
		Service service = new Service();
		List<Service> services = new ArrayList<Service>();
		
		List<AmadeusDroidHistoric> result = Facade.getInstance().getSocialHistory();
		for (AmadeusDroidHistoric obj : result) {
			Person p = Facade.getInstance().getPersonByUserName(obj.getUserName());
			obj.setUserLogin(p.getAccessInfo().getLogin());
			retrn.add(obj);
		}
		service.setListHistoric(retrn);
		services.add(service);
		
		return services;
	}
	
	@GET
	@Path("/forumMessages")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> getForumMessagesList(){
		Service service = new Service();
		List<Service> services = new ArrayList<Service>();
		
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum> listForum = Facade.getInstance().getListForumSyncronize();
		service.setListForum(listForum);
		services.add(service);
		
		return services;
	}
					
}




