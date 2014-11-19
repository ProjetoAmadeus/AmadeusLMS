package br.ufpe.cin.amadeus.data_service.xml.basic;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;

public class ActivityXMLBuilder {
	
	private final String course = "course";
	private final String module = "mod";
	
	private final String forum = "forum";
	private final String poll = "poll";
	private final String choice = "choice";
	private final String game = "game";
	private final String learningObject = "lobj";
	private final String evaluation = "eval";
	private final String video = "video";
	private final String materialRequest = "matReq";
	
	private final String id = "id";
	private final String name = "name";
	private final String description = "desc";
	private final String date = "date";
	private final String votes = "votes";
	private final String url = "url";
	private final String qMessages = "qMsg";
	private final String question = "question";
	private final String creationDate = "creationDate";
	private final String beginDate = "i_date";
	private final String finishDate = "e_date";
	private final String deliveryDate = "deliveryDate";
	
	
	public DSElement buildForum(Course course, Module module, Forum forum){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getForum");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idForumTag = new DSElement(this.id);
		idForumTag.setText(forum.getId() + "");
		DSElement nameForumTag = new DSElement(this.name);
		nameForumTag.setText(forum.getName());
		DSElement dateForumTag = new DSElement(this.date);
		dateForumTag.setText(forum.getCreationDate() + "");
		DSElement qtdMsgForumTag = new DSElement(this.qMessages);
		qtdMsgForumTag.setText(forum.getMessages().size() + "");
		
		DSElement forumTag = new DSElement(this.forum);
		forumTag.addContent(idForumTag);
		forumTag.addContent(nameForumTag);
		forumTag.addContent(dateForumTag);
		forumTag.addContent(qtdMsgForumTag);
		
		root.addContent(forumTag);

		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}
	
	public DSElement buildPoll(Course course, Module module, Poll poll){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getPoll");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idPollTag = new DSElement(this.id);
		idPollTag.setText(poll.getId() + "");
		DSElement namePollTag = new DSElement(this.name);
		namePollTag.setText(poll.getName());
		DSElement questionPollTag = new DSElement(this.question);
		questionPollTag.setText(poll.getQuestion());
		DSElement creationDatePollTag = new DSElement(this.creationDate);
		creationDatePollTag.setText(poll.getCreationDate() + "");
		DSElement finishDatePollTag = new DSElement(this.finishDate); 
		finishDatePollTag.setText(poll.getFinishDate() + "");
		
		DSElement pollTag = new DSElement(this.poll);
		pollTag.addContent(idPollTag);
		pollTag.addContent(namePollTag);
		pollTag.addContent(questionPollTag);
		for (Choice choice : poll.getChoices()) {
			DSElement choicePollTag = new DSElement(this.choice);
			choicePollTag.setText(choice.getAlternative());
			pollTag.addContent(choicePollTag);
		}
		pollTag.addContent(creationDatePollTag);
		pollTag.addContent(finishDatePollTag);
		
		root.addContent(pollTag);
		

		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		return root;
	}
	
	public DSElement buildPollResults(Course course, Module module, Poll poll){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getPollResults");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idPollTag = new DSElement(this.id);
		idPollTag.setText(poll.getId() + "");
		DSElement namePollTag = new DSElement(this.name);
		namePollTag.setText(poll.getName());
		DSElement questionPollTag = new DSElement(this.question);
		questionPollTag.setText(poll.getQuestion());
		
		DSElement pollTag = new DSElement(this.poll);
		pollTag.addContent(idPollTag);
		pollTag.addContent(namePollTag);
		pollTag.addContent(questionPollTag);
		for (Choice choice : poll.getChoices()) {
			DSElement choicePollTag = new DSElement(this.choice);
			choicePollTag.setText(choice.getAlternative());
			DSElement votesPollTag = new DSElement(this.votes);
			votesPollTag.setText(choice.getPercentage() + "");
			
			pollTag.addContent(choicePollTag);
			pollTag.addContent(votesPollTag);
		}
		
		root.addContent(pollTag);
		
		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		

		return root;
	}
	
	public DSElement buildGame(Course course, Module module, Game game){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getGame");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idGameTag = new DSElement(this.id);
		idGameTag.setText(game.getId() + "");
		DSElement nameGameTag = new DSElement(this.name);
		nameGameTag.setText(game.getName());
		DSElement descGameTag = new DSElement(this.description);
		descGameTag.setText(game.getDescription());
		DSElement dateGameTag = new DSElement(this.date);
		dateGameTag.setText(game.getCreationDate() + "");
		
		DSElement gameTag = new DSElement(this.game);
		gameTag.addContent(idGameTag);
		gameTag.addContent(nameGameTag);
		gameTag.addContent(descGameTag);
		gameTag.addContent(dateGameTag);
		
		root.addContent(gameTag);

		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}
	
	public DSElement buildLearningObject(Course course, Module module, LearningObject learning){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getLearningObject");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idLearningTag = new DSElement(this.id);
		idLearningTag.setText(learning.getId() + "");
		DSElement nameLearningTag = new DSElement(this.name);
		nameLearningTag.setText(learning.getName());
		DSElement descLearningTag = new DSElement(this.description);
		descLearningTag.setText(learning.getDescription());
		DSElement dateLearningTag = new DSElement(this.date);
		dateLearningTag.setText(learning.getCreationDate() + "");
		DSElement urlLearningTag = new DSElement(this.url);
		urlLearningTag.setText(learning.getCreationDate() + "");
		
		DSElement learningTag = new DSElement(this.learningObject);
		learningTag.addContent(idLearningTag);
		learningTag.addContent(nameLearningTag);
		learningTag.addContent(descLearningTag);
		learningTag.addContent(dateLearningTag);
		learningTag.addContent(urlLearningTag);
		
		root.addContent(learningTag);
		

		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}
	
	public DSElement buildEvaluation(Course course, Module module, Evaluation eval){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getEvaluation");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idEvalTag = new DSElement(this.id);
		idEvalTag.setText(eval.getId() + "");
		DSElement descEvalTag = new DSElement(this.description);
		descEvalTag.setText(eval.getDescription());
		DSElement dateInitEvalTag = new DSElement(this.beginDate);
		dateInitEvalTag.setText(eval.getStart() + "");
		DSElement dateFinishEvalTag = new DSElement(this.finishDate);
		dateFinishEvalTag.setText(eval.getFinish() + "");
		
		DSElement evalTag = new DSElement(this.evaluation);
		evalTag.addContent(idEvalTag);
		evalTag.addContent(descEvalTag);
		evalTag.addContent(dateInitEvalTag);
		evalTag.addContent(dateFinishEvalTag);
		
		root.addContent(evalTag);
		

		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}
	
	public DSElement buildVideo(Course course, Module module, VideoIriz video){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getVideo");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idVideoTag = new DSElement(this.id);
		idVideoTag.setText(video.getId() + "");
		DSElement nameVideoTag = new DSElement(this.name);
		nameVideoTag.setText(video.getName());
		DSElement descVideoTag = new DSElement(this.description);
		descVideoTag.setText(video.getDescription());
		DSElement dateVideoTag = new DSElement(this.date);
		dateVideoTag.setText(video.getCreationDate() + "");
		
		DSElement videoTag = new DSElement(this.video);
		videoTag.addContent(idVideoTag);
		videoTag.addContent(nameVideoTag);
		videoTag.addContent(descVideoTag);
		videoTag.addContent(dateVideoTag);
		
		root.addContent(videoTag);

		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());

		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}
	
	public DSElement buildMaterialRequest(Course course, Module module, MaterialRequest matReq){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getMaterialRequest");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idMatReqTag = new DSElement(this.id);
		idMatReqTag.setText(matReq.getId() + "");
		DSElement nameMatReqTag = new DSElement(this.name);
		nameMatReqTag.setText(matReq.getName());
		DSElement descMatReqTag = new DSElement(this.description);
		descMatReqTag.setText(matReq.getDescription());
		DSElement creationDateMatReqTag = new DSElement(this.creationDate);
		creationDateMatReqTag.setText(matReq.getCreationDate() + "");
		DSElement deliveryDateMatReqTag = new DSElement(this.deliveryDate);
		deliveryDateMatReqTag.setText(matReq.getDeliveryDate() + "");
		
		DSElement matReqTag = new DSElement(this.materialRequest);
		matReqTag.addContent(idMatReqTag);
		matReqTag.addContent(nameMatReqTag);
		matReqTag.addContent(descMatReqTag);
		matReqTag.addContent(creationDateMatReqTag);
		matReqTag.addContent(deliveryDateMatReqTag);
		
		root.addContent(matReqTag);
		

		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag  = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());

		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);
		
		root.addContent(courseTag);
		
		
		return root;
	}

}

