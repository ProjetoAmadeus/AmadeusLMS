package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ExternalLink;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidVideoException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR;

public class UtilActivities {

	public static void eraseAndWriteNameActivity(int idModule) {
		// AJAX REVERSO

		Facade facade = Facade.getInstance();

		Module module = facade.getModuleById(idModule);

		UtilDWR.getUtil().addFunctionCall("removeAllNameActivity", module.getPosition());
		UtilDWR.getUtil().addFunctionCall("removeAllNameMaterial", module.getPosition());

		List<Forum> foruns = module.getForums();
		Iterator<Forum> iforuns = foruns.iterator();
		while (iforuns.hasNext()) {
			Forum f = (Forum) iforuns.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "forum", module.getPosition(), f.getId(), f.getName());
		}

		List<Game> games = module.getGames();
		Iterator<Game> i = games.iterator();
		while (i.hasNext()) {
			Game g = (Game) i.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "game",	module.getPosition(), g.getId(), g.getName());
		}

		List<Poll> polls = module.getPolls();
		Iterator<Poll> iPolls = polls.iterator();
		while (iPolls.hasNext()) {
			Poll p = (Poll) iPolls.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "poll", module.getPosition(), p.getId(), p.getName());
		}
		
		List<Material> materials = module.getMaterials();
		Iterator<Material> iMaterial = materials.iterator();
		while (iMaterial.hasNext()) {
			Material m = (Material) iMaterial.next();
			if(m.getRequestedMaterial() == null){
				UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "material", module.getPosition(), m.getId(), m.getArchiveName());
			}
			
		}
		
		List<MaterialRequest> materialRequests = module.getMaterialRequests();
		Iterator<MaterialRequest> iMaterialRequest = materialRequests.iterator();
		while (iMaterialRequest.hasNext()) {
			MaterialRequest m = (MaterialRequest) iMaterialRequest.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "materialRequest", module.getPosition(), m.getId(), m.getName()); 
		}

		List<VideoIriz> videosIriz = module.getVideos();
		Iterator<VideoIriz> iVideoIriz = videosIriz.iterator();
		while (iVideoIriz.hasNext()) {
			VideoIriz v = (VideoIriz) iVideoIriz.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "videoIriz", module.getPosition(), v.getId(), v.getName());
		}
		
		
		List<LearningObject> learningObjects = module.getLearningObjects();
		Iterator<LearningObject> iLearningObject = learningObjects.iterator();
		while (iLearningObject.hasNext()) {
			LearningObject learning = (LearningObject) iLearningObject.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "learningObject", module.getPosition(), learning.getId(), learning.getName());
		}
		
		List<Evaluation> evaluations = module.getEvaluations();
		Iterator<Evaluation> iEvaluation = evaluations.iterator();
		while (iEvaluation.hasNext()) {
			Evaluation m = (Evaluation) iEvaluation.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "evaluation", module.getPosition(), m.getId(), m.getDescription());
		}
		
		List<ExternalLink> links = module.getExternalLinks();
		Iterator<ExternalLink> iLink = links.iterator();
		while(iLink.hasNext()){
			ExternalLink extLink = (ExternalLink) iLink.next();
			UtilDWR.getUtil().addFunctionCall("addNewNameActivity", "externalLink", module.getPosition(), extLink.getId(), extLink.getName());
		}
		
	}

	/**
	 * Method that compares 2 dates, works similarly to the compareTo method from Calendar, but 
	 * ignores the time, just uses the year, month and day
	 * @param date1
	 * @param date2
	 * @return 0 if the dates represent the same day, -1 if date1 is before date2, 1 if date1 is 
	 * after date2 
	 */
	public static int compareDates(Calendar date1, Calendar date2) {
		int result = 0;
		if(date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
			if(date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)){
				
				if(date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH)){
					result = 0;
				}else if(date1.get(Calendar.DAY_OF_MONTH) < date2.get(Calendar.DAY_OF_MONTH)){
					result = -1;
				}else if(date1.get(Calendar.DAY_OF_MONTH) > date2.get(Calendar.DAY_OF_MONTH)){
					result = 1;
				}
			}
			else if(date1.get(Calendar.MONTH) < date2.get(Calendar.MONTH)){
				result = -1;
			}else if(date1.get(Calendar.MONTH) > date2.get(Calendar.MONTH)){
				result = 1;
			}
			
		}else if(date1.get(Calendar.YEAR) < date2.get(Calendar.YEAR)){
			result = -1;
		}else if(date1.get(Calendar.YEAR) > date2.get(Calendar.YEAR)){
			result = 1;
		}
		
		return result;
	}

	public static String defineContentType(String extension) {
		String type = UtilActivities.getFileType(extension);
		String contentType;
		if(type.equalsIgnoreCase("doc") || type.equalsIgnoreCase("dot")){
			contentType = "application/msword";
		}else if(type.equalsIgnoreCase("xls") || 
				 type.equalsIgnoreCase("xla") || 
				 type.equalsIgnoreCase("xlt") ||
				 type.equalsIgnoreCase("xlc") ||
				 type.equalsIgnoreCase("xlm") ||
				 type.equalsIgnoreCase("xlw")){
			contentType = "application/vnd.ms-excel";
		}else if(type.equalsIgnoreCase("pps") || 
				 type.equalsIgnoreCase("ppt") || 
				 type.equalsIgnoreCase("pot")){
			contentType = "application/vnd.ms-powerpoint";
		}else if(type.equalsIgnoreCase("docx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.wordprocessingml.document";
		}else if(type.equalsIgnoreCase("dotx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.wordprocessingml.template";
		}else if(type.equalsIgnoreCase("pptx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.presentationml.presentation";
		}else if(type.equalsIgnoreCase("ppsx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.presentationml.slideshow";
		}else if(type.equalsIgnoreCase("potx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.presentationml.template";
		}else if(type.equalsIgnoreCase("xlsx")){
			contentType = "application/vnd.openxmlformats-" +
					"officedocument.spreadsheetml.sheet";
		}else if(type.equalsIgnoreCase("xltx")){
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
		}else if(type.equalsIgnoreCase("pdf")){
			contentType = "application/pdf";
		}else if(type.equalsIgnoreCase("jpg") ||
				 type.equalsIgnoreCase("jpeg")|| 
				 type.equalsIgnoreCase("jpe")){
			contentType = "image/jpeg";
		}else if(type.equalsIgnoreCase("zip")){
			contentType = "multipart/x-zip";
		}else if(type.equalsIgnoreCase("mp2") || 
				 type.equalsIgnoreCase("mpg") ||
				 type.equalsIgnoreCase("mpeg") ||
				 type.equalsIgnoreCase("mpe") ||
				 type.equalsIgnoreCase("mpa")){
			contentType = "video/mpeg";
		}else if(type.equalsIgnoreCase("xml")){
			contentType = "application/xml";
		}else if(type.equalsIgnoreCase("mov") ||
				 type.equalsIgnoreCase("qt") ){
			contentType = "video/quicktime";
		}else if(type.equalsIgnoreCase("movie")){
			contentType = "video/x-sgi-movie";
		}else if(type.equalsIgnoreCase("avi")){
			contentType = "video/x-msvideo";
		}else if(type.equalsIgnoreCase("mp3")){
			contentType = "audio/mpeg";
		}else if(type.equalsIgnoreCase("bmp")){
			contentType = "image/bmp";
		}else if(type.equalsIgnoreCase("gif")){
			contentType = "image/gif";
		}else if(type.equalsIgnoreCase("wav")){
			contentType = "audio/x-wav";
		}else if(type.equalsIgnoreCase("mid")){
			contentType = "audio/mid";
		}else if(type.equalsIgnoreCase("swf")){
			contentType = "application/x-shockwave-flash";
		}else {
			contentType = "application/octet-stream";
		}
		
		return contentType;
	}
	
	public static String defineCssClass(String extension){
		String type = UtilActivities.getFileType(extension);
		String cssClass;
		
		if(type.equalsIgnoreCase("doc") || 
				type.equalsIgnoreCase("dot") ||
				type.equalsIgnoreCase("docx")||
				type.equalsIgnoreCase("dotx")){
			cssClass = "word";
		}else if(type.equalsIgnoreCase("xls") || 
				 type.equalsIgnoreCase("xla") || 
				 type.equalsIgnoreCase("xlt") ||
				 type.equalsIgnoreCase("xlc") ||
				 type.equalsIgnoreCase("xlm") ||
				 type.equalsIgnoreCase("xlw") ||
				 type.equalsIgnoreCase("xlsx")||
				 type.equalsIgnoreCase("xltx")){
			cssClass = "excel";
		}else if(type.equalsIgnoreCase("pps") || 
				 type.equalsIgnoreCase("ppt") || 
				 type.equalsIgnoreCase("pot") ||
				 type.equalsIgnoreCase("pptx")||
				 type.equalsIgnoreCase("potx")||
				 type.equalsIgnoreCase("ppsx")){
			
			cssClass = "powerPoint";
		}else if(type.equalsIgnoreCase("bmp") || 
				 type.equalsIgnoreCase("gif") || 
				 type.equalsIgnoreCase("jpg") ||
				 type.equalsIgnoreCase("jpeg")||
				 type.equalsIgnoreCase("png")){
			
			cssClass = "imageFile";
		}else if(type.equalsIgnoreCase("avi") || 
				 type.equalsIgnoreCase("mov") || 
				 type.equalsIgnoreCase("mpg") ||
				 type.equalsIgnoreCase("mpeg")){
			
			cssClass = "videoFile";
		}else if(type.equalsIgnoreCase("pdf")){
			
			cssClass = "pdf";
		}else if(type.equalsIgnoreCase("mp3") || 
				 type.equalsIgnoreCase("wav") || 
				 type.equalsIgnoreCase("mid") ||
				 type.equalsIgnoreCase("wma")){
			
			cssClass = "audioFile";
		}else {
			cssClass = "material";
		}
		
		return cssClass;
	}

	private static String getFileType(String extension) {
		int length = extension.length();
		int dotChar = length - 6;
		String subString = extension.substring(dotChar);
		
		while(subString.charAt(0) != '.'){
			dotChar = dotChar + 1;
			subString = extension.substring(dotChar);
		}

		return extension.substring(dotChar + 1);
	}	
	
	public static String getVideoId(String longId){
		String[] pieces = longId.split("video:");
		return pieces[1];
	}
	
	public static String getVideoIdFromURL(String url) throws InvalidVideoException{
		String[] urlPieces = url.split("v=");
		String result;
		
		if(urlPieces.length == 2){
			String[] finalString = urlPieces[1].split("&");
			result = finalString[0];
			
		}else{
			throw new InvalidVideoException("errors.video.url.invalid");
		}
		
		return result;
	}
}
