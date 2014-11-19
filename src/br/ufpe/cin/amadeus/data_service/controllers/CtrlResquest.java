package br.ufpe.cin.amadeus.data_service.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.HibernateUtil;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;
import br.ufpe.cin.amadeus.data_service.xml.basic.ActivityXMLBuilder;
import br.ufpe.cin.amadeus.data_service.xml.basic.CourseXMLBuilder;
import br.ufpe.cin.amadeus.data_service.xml.basic.MaterialXMLBuilder;
import br.ufpe.cin.amadeus.data_service.xml.basic.ModuleXMLBuilder;
import br.ufpe.cin.amadeus.data_service.xml.message.ErrorMessage;
import br.ufpe.cin.amadeus.data_service.xml.message.RequestMessage;

public class CtrlResquest {
	
	private static final String getCourse = "getCourse";
	private static final String getCourseList = "getCourseList";
	private static final String getCourseInformation = "getCourseInformation";
	private static final String getModule = "getModule";
	private static final String getModuleList = "getModuleList";
	private static final String getModuleInformation = "getModuleInformation";
	
	private static final String getCourseMaterialList = "getCourseMaterialList";
	private static final String getCourseActivityList = "getCourseActivityList";
	private static final String getModuleMaterialList = "getModuleMaterialList";
	private static final String getModuleActivityList = "getModuleActivityList";
	
	private static final String getForum = "getForum";
	private static final String getPoll = "getPoll";
	private static final String getPollResults = "getPollResults";
	private static final String getMaterialDelivery = "getMaterialDelivery";
	private static final String getGame = "getGame";
	private static final String getLearningObject = "getLearningObject";
	private static final String getEvaluation = "getEvaluation";
	private static final String getVideo = "getVideo";
	private static final String getMaterial = "getMaterial";
	
	//TODO ainda faltando as mensagens de configuração
	
	private static CtrlResquest ctrlRequest;

	
	private CtrlResquest(){
	}
	public static CtrlResquest getInstance(){
		if(CtrlResquest.ctrlRequest == null){
			CtrlResquest.ctrlRequest = new CtrlResquest();
		}
		return CtrlResquest.ctrlRequest;
	}
	

	public String execute(String xml){
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		
		DSElement messageDSElement = null;
		
		try {
			RequestMessage messageReq = new RequestMessage(xml);
			
			//verifing permissions
			Facade facade = Facade.getInstance();
			AccessInfo ai = facade.searchUserByLogin(messageReq.getLogin());
			ai.setLogin(messageReq.getLogin());
			ai.setPassword(messageReq.getPass());
			facade.logon(ai);
			
			//muxing a get requisition
			if(messageReq.getType().equalsIgnoreCase("GET")){
				String op = messageReq.getOp();
				
				if(op.equalsIgnoreCase(CtrlResquest.getCourse)){
					messageDSElement = this.getCourse(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getCourseList)){
					messageDSElement = this.getCourseList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getCourseInformation)){
					messageDSElement = this.getCourseInformation(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getModule)){
					messageDSElement = this.getModule(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getModuleList)){
					messageDSElement = this.getModuleList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getModuleInformation)){
					messageDSElement = this.getModuleInformation(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getCourseMaterialList)){
					messageDSElement = this.getCourseMaterialList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getCourseActivityList)){
					messageDSElement = this.getCourseActivityList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getModuleMaterialList)){
					messageDSElement = this.getModuleMaterialList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getModuleActivityList)){
					messageDSElement = this.getModuleActivityList(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getForum)){
					messageDSElement = this.getForum(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getPoll)){
					messageDSElement = this.getPoll(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getPollResults)){
					messageDSElement = this.getPollResults(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getMaterialDelivery)){
					messageDSElement = this.getMaterialDelivery(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getGame)){
					messageDSElement = this.getGame(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getLearningObject)){
					messageDSElement = this.getLearningObject(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getEvaluation)){
					messageDSElement = this.getEvaluation(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getVideo)){
					messageDSElement = this.getVideo(messageReq);
				}else if(op.equalsIgnoreCase(CtrlResquest.getMaterial)){
					messageDSElement = this.getMaterial(messageReq);
				}
			}
		} catch (JDOMException e) {
			ErrorMessage errorMsg = new ErrorMessage(-1, "get", "ERROR", "A mensagem enviada possui formato inválido!");
			e.printStackTrace();
		} catch (IOException e) {
			ErrorMessage errorMsg = new ErrorMessage(-1, "get", "ERROR", "Ocorreu um erro no servidor! Contate o administrador do sistema!");
			e.printStackTrace();
		} catch (InvalidLogonException e) {
			ErrorMessage errorMsg = new ErrorMessage(-1, "get", "ERROR", "Você não tem permissão para acessar este conteúdo!");
			e.printStackTrace();
		}
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		return messageDSElement.toString();
	}
	
	
	//course
	private DSElement getCourse(RequestMessage msg){
		Facade facade = Facade.getInstance();

		String valueParam = msg.getValueParam();
		int courseId = Integer.parseInt(valueParam);
		Course course = facade.getCoursesById(courseId);
		
		int nMaterials = 0;
		int nActivities = 0;
		List<Module> modules = course.getModules();
		for (Module mod : modules) {
			nMaterials += mod.getMaterials().size();
			
			nActivities += mod.getForums().size();
			nActivities += mod.getPolls().size();
			nActivities += mod.getGames().size();
			nActivities += mod.getLearningObjects().size();
			nActivities += mod.getEvaluations().size();
			nActivities += mod.getVideos().size();
			nActivities += mod.getMaterialRequests().size();
		}
		
		CourseXMLBuilder builder = new CourseXMLBuilder();
		DSElement courseXML = builder.buildCourse(courseId, course.getName(), modules.size(), nMaterials, nActivities);
		
		return courseXML;
	}

	private DSElement getCourseList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String login = msg.getLogin();
		AccessInfo ac = facade.searchUserByLogin(login);
		List<Course> courses = facade.searchCoursesByAccessInfo(ac);

		CourseXMLBuilder builder = new CourseXMLBuilder();
		DSElement courseListXML = builder.buildCourseList(courses);
		
		return courseListXML;
	}

	private DSElement getCourseInformation(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int courseId = Integer.parseInt(valueParam);
		Course course = facade.getCoursesById(courseId);
		
		CourseXMLBuilder builder = new CourseXMLBuilder();
		DSElement courseInfoXML = builder.buildCourseInformation(course,"azul");
		
		return courseInfoXML;
	}

	private DSElement getCourseMaterialList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int courseId = Integer.parseInt(valueParam);
		
		Course course = facade.getCoursesById(courseId);
		
		List<Material> materials = new ArrayList<Material>();
		List<Module> modules = course.getModules();
		for (Module module : modules) {
			List<Material> mats = module.getMaterials();
			materials.addAll(mats);
		}
		
		CourseXMLBuilder builder = new CourseXMLBuilder();
		DSElement courseMatListXML = builder.buildCourseMaterialList(course, materials);
		
		return courseMatListXML;
	}

	private DSElement getCourseActivityList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int courseId = Integer.parseInt(valueParam);
		
		Course course = facade.getCoursesById(courseId);
		
		List<Forum> forums = new ArrayList<Forum>();
		List<Poll> polls = new ArrayList<Poll>();
		List<Game> games = new ArrayList<Game>();
		List<LearningObject> objs = new ArrayList<LearningObject>();
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		List<VideoIriz> videos = new ArrayList<VideoIriz>();
		List<MaterialRequest> materialRequests = new ArrayList<MaterialRequest>();
		
		List<Module> modules = course.getModules();
		for (Module mod : modules) {
			forums.addAll(mod.getForums());
			polls.addAll(mod.getPolls());
			games.addAll(mod.getGames());
			objs.addAll(mod.getLearningObjects());
			evaluations.addAll(mod.getEvaluations());
			videos.addAll(mod.getVideos());
			materialRequests.addAll(mod.getMaterialRequests());
		}
		
		CourseXMLBuilder builder = new CourseXMLBuilder();
		DSElement courseActXML = builder.buildCourseActivityList(course, forums, polls, games, objs, evaluations, videos, materialRequests);
		
		return courseActXML;
	}
	
	
	//module
	private DSElement getModule(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int moduleId = Integer.parseInt(valueParam);
		Module module = facade.getModuleById(moduleId);
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ModuleXMLBuilder builder = new ModuleXMLBuilder();
		DSElement moduleXML = builder.buildModule(course, module);
		
		return moduleXML;
	}
	
	private DSElement getModuleList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int courseId = Integer.parseInt(valueParam);
		
		Course course = facade.getCoursesById(courseId);
		List<Module> modules = course.getModules();
		
		ModuleXMLBuilder builder = new ModuleXMLBuilder();
		DSElement moduleListXML = builder.buildModuleList(course, modules);
		
		return moduleListXML;
	}

	private DSElement getModuleInformation(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int moduleId = Integer.parseInt(valueParam);
		Module module = facade.getModuleById(moduleId);
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ModuleXMLBuilder builder = new ModuleXMLBuilder();
		DSElement moduleInfoXML = builder.buildModuleInformation(course, module);
		
		return moduleInfoXML;
	}

	private DSElement getModuleMaterialList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int moduleId = Integer.parseInt(valueParam);
		Module module = facade.getModuleById(moduleId);
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ModuleXMLBuilder builder = new ModuleXMLBuilder();
		DSElement moduleMatListXML = builder.buildModuleMaterialList(course, module);
		
		return moduleMatListXML;
	}
	
	private DSElement getModuleActivityList(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int moduleId = Integer.parseInt(valueParam);
		Module module = facade.getModuleById(moduleId);
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ModuleXMLBuilder builder = new ModuleXMLBuilder();
		DSElement moduleMatListXML = builder.buildModuleMaterialList(course, module);
		
		return moduleMatListXML;
	}
	
	
	//Entities
	private DSElement getForum(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int forumId = Integer.parseInt(valueParam);
		
		Forum forum = facade.getForumById(forumId);
		Module module = facade.getModuleById(forum.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement forumXML = builder.buildForum(course, module, forum);
		
		return forumXML;
	}

	private DSElement getPoll(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int pollId = Integer.parseInt(valueParam);
		
		Poll poll = facade.getPollByID(pollId);
		Module module = facade.getModuleById(poll.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement pollXML = builder.buildPoll(course, module, poll);
		
		return pollXML;
	}

	private DSElement getPollResults(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int pollId = Integer.parseInt(valueParam);
		
		Poll poll = facade.getPollByID(pollId);
		Module module = facade.getModuleById(poll.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement pollResultsXML = builder.buildPollResults(course, module, poll);
		
		return pollResultsXML;
	}

	private DSElement getMaterialDelivery(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int idMatReq = Integer.parseInt(valueParam);
		
		MaterialRequest matReq = facade.getMaterialRequestByID(idMatReq);
		Module module = facade.getModuleById(matReq.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement matReqXML = builder.buildMaterialRequest(course, module, matReq);
		
		return matReqXML;
	}

	private DSElement getGame(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int gameId = Integer.parseInt(valueParam);
		
		Game game = facade.getGameById(gameId);
		Module module = facade.getModuleById(game.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement gameXML = builder.buildGame(course, module, game);
		
		return gameXML;
	}

	private DSElement getLearningObject(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int learningId = Integer.parseInt(valueParam);
		
		LearningObject learningObj = facade.getLearningObjectById(learningId);
		Module module = facade.getModuleById(learningObj.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement lobjXML = builder.buildLearningObject(course, module, learningObj);
		
		return lobjXML;
	}

	private DSElement getEvaluation(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int evalId = Integer.parseInt(valueParam);
		
		Evaluation eval = facade.getEvaluationById(evalId);
		Module module = facade.getModuleById(eval.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement evalXML = builder.buildEvaluation(course, module, eval);
		
		return evalXML;
	}

	private DSElement getVideo(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int videoId = Integer.parseInt(valueParam);
		
		VideoIriz video = facade.getVideoByID(videoId);
		Module module = facade.getModuleById(video.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		ActivityXMLBuilder builder = new ActivityXMLBuilder();
		DSElement videoXML = builder.buildVideo(course, module, video);
		
		return videoXML;
	}

	private DSElement getMaterial(RequestMessage msg){
		Facade facade = Facade.getInstance();
		
		String valueParam = msg.getValueParam();
		int materialId = Integer.parseInt(valueParam);
		
		Material material = facade.getMaterialByID(materialId);
		Module module = facade.getModuleById(material.getModule().getId());
		Course course = facade.getCoursesById(module.getCourse().getId());
		
		MaterialXMLBuilder builder = new MaterialXMLBuilder();
		DSElement materialXML = builder.buildMaterial(course, module, material);
		
		return materialXML;
	}

}
