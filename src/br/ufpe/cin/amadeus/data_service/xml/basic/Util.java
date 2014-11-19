package br.ufpe.cin.amadeus.data_service.xml.basic;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;

public final class Util {
	
	
	public static DSElement buildForums(List<Forum> forums) {
		
		DSElement forumListTag = new DSElement("forumList");
		for (Forum forum : forums) {
			DSElement idForumTag = new DSElement("id");
			idForumTag.setText(forum.getId() + "");
			
			DSElement nameForumTag = new DSElement("name");
			nameForumTag.setText(forum.getName());
			
			DSElement dateForumTag = new DSElement("date");
			dateForumTag.setText(forum.getCreationDate() + "");
			
			DSElement forumTag = new DSElement("forum");
			forumTag.addContent(idForumTag);
			forumTag.addContent(nameForumTag);
			forumTag.addContent(dateForumTag);
			
			forumListTag.addContent(forumTag);
		}
		
		return forumListTag;
	}
	
	public static DSElement buildPolls(List<Poll> polls) {
		
		DSElement pollListTag = new DSElement("pollList");
		for (Poll poll : polls) {
			DSElement idPollTag = new DSElement("id");
			idPollTag.setText(poll.getId() + "");
			
			DSElement namePollTag = new DSElement("name");
			namePollTag.setText(poll.getName());
			
			DSElement questionPollTag = new DSElement("question");
			questionPollTag.setText(poll.getQuestion());
			
			DSElement datePollTag = new DSElement("date");
			datePollTag.setText(poll.getCreationDate() + "");
			
			DSElement pollTag = new DSElement("poll");
			pollTag.addContent(idPollTag);
			pollTag.addContent(namePollTag);
			pollTag.addContent(questionPollTag);
			pollTag.addContent(datePollTag);
			
			pollListTag.addContent(pollTag);
		}
		
		return pollListTag;
	}
	
	public static DSElement buildGames(List<Game> games) {
		
		DSElement gameListTag = new DSElement("gameList");
		for (Game game : games) {
			DSElement idGameTag = new DSElement("id");
			idGameTag.setText(game.getId() + "");
			
			DSElement nameGameTag = new DSElement("name");
			nameGameTag.setText(game.getName());
			
			DSElement dateGameTag = new DSElement("date");
			dateGameTag.setText(game.getCreationDate() + "");
			
			DSElement gameTag = new DSElement("game");
			gameTag.addContent(idGameTag);
			gameTag.addContent(nameGameTag);
			gameTag.addContent(dateGameTag);
			
			gameListTag.addContent(gameTag);
		}
		
		return gameListTag;
	}
	
	public static DSElement buildLearningObjects(List<LearningObject> objs) {
		
		DSElement lobjListTag = new DSElement("lobjList");
		for (LearningObject lobj : objs) {
			DSElement idObjTag = new DSElement("id");
			idObjTag.setText(lobj.getId() + "");
			
			DSElement nameObjTag = new DSElement("name");
			nameObjTag.setText(lobj.getName());
			
			DSElement dateObjTag = new DSElement("date");
			dateObjTag.setText(lobj.getCreationDate() + "");
			
			DSElement lobjTag = new DSElement("lobj");
			lobjTag.addContent(idObjTag);
			lobjTag.addContent(nameObjTag);
			lobjTag.addContent(dateObjTag);
			
			lobjListTag.addContent(lobjTag);
		}
		
		return lobjListTag;
	}
	
	public static DSElement buildEvaluations(List<Evaluation> evaluations) {
		
		DSElement evaluationListTag = new DSElement("evalList");
		for (Evaluation eval : evaluations) {
			DSElement idEvalTag = new DSElement("id");
			idEvalTag.setText(eval.getId() + "");
			
			DSElement descEvalTag = new DSElement("desc");
			descEvalTag.setText(eval.getDescription());
			
			DSElement dateEvalTag = new DSElement("date");
			dateEvalTag.setText(eval.getStart() + "");
			
			DSElement evalTag = new DSElement("eval");
			evalTag.addContent(idEvalTag);
			evalTag.addContent(descEvalTag);
			evalTag.addContent(dateEvalTag);

			evaluationListTag.addContent(evalTag);
		}
		return evaluationListTag;
	}
	
	public static DSElement buildVideos(List<VideoIriz> videos) {
		
		DSElement videoListTag = new DSElement("videoList");
		for (VideoIriz video : videos) {
			DSElement idVideoTag = new DSElement("id");
			idVideoTag.setText(video.getId() + "");
			
			DSElement nameVideoTag = new DSElement("name");
			nameVideoTag.setText(video.getName());
			
			DSElement dateVideoTag = new DSElement("date");
			dateVideoTag.setText(video.getCreationDate() + "");
			
			DSElement videoTag = new DSElement("video");
			videoTag.addContent(idVideoTag);
			videoTag.addContent(nameVideoTag);
			videoTag.addContent(dateVideoTag);
			
			videoListTag.addContent(videoTag);
		}
		return videoListTag;
	}
	
	public static DSElement buildMaterialRequest(List<MaterialRequest> materialRequests) {
		
		DSElement matReqListTag = new DSElement("matReqList");
		for (MaterialRequest matReq : materialRequests) {
			DSElement idMatReqTag = new DSElement("id");
			idMatReqTag.setText(matReq.getId() + "");
			
			DSElement nameMatReqTag = new DSElement("name");
			nameMatReqTag.setText(matReq.getName());
			
			DSElement dateMatReqTag = new DSElement("date");
			dateMatReqTag.setText(matReq.getCreationDate() + "");
			
			DSElement matReqTag = new DSElement("matReq");
			matReqTag.addContent(idMatReqTag);
			matReqTag.addContent(nameMatReqTag);
			matReqTag.addContent(dateMatReqTag);
		
			matReqListTag.addContent(matReqTag);
		}
		
		return matReqListTag;
	}
	

}
