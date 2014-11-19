/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;


public class Conversor {

	/**
	 * Method that converts AMADeUs Course object into Mobile Course object
	 * @param curso - AMADeUs Course to be converted
	 * @return - Converted Mobile Course object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile converterCurso(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course curso){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile();
		
		retorno.setId(curso.getId());
		retorno.setName(curso.getName());
		retorno.setContent(curso.getContent());
		retorno.setObjectives(curso.getObjectives());
		retorno.setModules(converterModulos(curso.getModules()));
		retorno.setKeywords(converterKeywords(curso.getKeywords()));
		ArrayList<String> nomes = new ArrayList<String>();
		nomes.add(curso.getProfessor().getName());
		retorno.setTeachers(nomes);
		retorno.setCount(0);
		retorno.setMaxAmountStudents(curso.getMaxAmountStudents());
		retorno.setFinalCourseDate(curso.getFinalCourseDate());		
		retorno.setInitialCourseDate(curso.getInitialCourseDate());
					
		return retorno;
	}
	/**
	 * Method that converts a AMADeUs Course object list into Mobile Course object list
	 * @param cursos - AMADeUs Course object list to be converted
	 * @return - Converted Mobile Course object list 
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile> converterCursos(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course> cursos){
	       
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c : cursos){
		   retorno.add(Conversor.converterCurso(c));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Module object into Mobile Module object 
	 * @param modulo - AMADeUs Module object to be converted
	 * @return - Converted Mobile Module object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile converterModulo(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module modulo){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile mod = new br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile(modulo.getId(), modulo.getName());


		List<HomeworkMobile> listHomeworks = new ArrayList<HomeworkMobile>();
		
		for (Poll poll : modulo.getPolls()) {
			listHomeworks.add( Conversor.converterPollToHomework(poll) );
		}
		for (Forum forum : modulo.getForums()) {
			listHomeworks.add( Conversor.converterForumToHomework(forum) );
		}
		for(Game game : modulo.getGames()){
			listHomeworks.add( Conversor.converterGameToHomework(game) );
		}
		for(LearningObject learning : modulo.getLearningObjects()){
			listHomeworks.add( Conversor.converterLearningObjectToHomework(learning) );
		}
		
		mod.setHomeworks(listHomeworks);
		mod.setMaterials(converterMaterials(modulo.getMaterials()));
		
		return mod;		
	}
	
	/**
	 * Mothod that converts a AMADeUs Module object list into Mobile Module object list
	 * @param modulos - AMADeUs Module object list to be converted
	 * @return - Converted Mobile Module object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile> converterModulos(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module> modulos){
       
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module m : modulos){
		   retorno.add(Conversor.converterModulo(m));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Homework object into Mobile Homework object
	 * @param home - AMADeUs Homework object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterHomework(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework home){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(home.getId());
		retorno.setName(home.getName());
		retorno.setDescription(home.getDescription());
		retorno.setInitDate(home.getInitDate());
		retorno.setDeadline(home.getDeadline());
		retorno.setAlowPostponing(home.getAllowPostponing());
		retorno.setInfoExtra("");
		retorno.setTypeActivity(HomeworkMobile.HOMEWORK);
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Game object into Mobile Homework object
	 * @param game - AMADeUs Game object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterGameToHomework(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game game){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(game.getId());
		retorno.setName(game.getName());
		retorno.setDescription(game.getDescription());
		retorno.setInfoExtra(game.getUrl());
		retorno.setTypeActivity(HomeworkMobile.GAME);
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Forum object into Mobile Homework object
	 * @param forum - AMADeUs Forum object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterForumToHomework(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum forum){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(forum.getId());
		retorno.setName(forum.getName());
		retorno.setDescription(forum.getDescription());
		retorno.setInitDate(forum.getCreationDate());
		retorno.setInfoExtra("");
		retorno.setTypeActivity(HomeworkMobile.FORUM);
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Poll object into Mobile Homework object
	 * @param poll - AMADeUs Poll object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterPollToHomework(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll poll){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(poll.getId());
		retorno.setName(poll.getName());
		retorno.setDescription(poll.getQuestion());
		retorno.setInitDate(poll.getCreationDate());
		retorno.setDeadline(poll.getFinishDate());
		retorno.setInfoExtra("");
		retorno.setTypeActivity(HomeworkMobile.POLL);
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Multimedia object into Mobile Homework object
	 * @param media - AMADeUs Multimedia object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterMultimediaToHomework(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Multimedia media){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(media.getId());
		retorno.setName(media.getName());
		retorno.setDescription(media.getDescription());
		retorno.setInfoExtra(media.getUrl());
		retorno.setTypeActivity(HomeworkMobile.MULTIMEDIA);
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Video object into Mobile Homework object
	 * @param video - AMADeUs Video object to be converted
	 * @return - Converted Mobile Homework object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterVideoToHomework(br.ufpe.cin.amadeus.amadeus_sdmm.dao.Video video){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(video.getId());
		retorno.setName(video.getName());
		retorno.setDescription(video.getDescription());
		retorno.setInitDate(video.getDateinsertion());
		retorno.setInfoExtra(video.getTags());
		retorno.setTypeActivity(HomeworkMobile.VIDEO);
		
		return retorno;	
	}
	
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile converterLearningObjectToHomework(
			br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject learning) {

		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile();
		
		retorno.setId(learning.getId());
		retorno.setName(learning.getName());
		retorno.setUrl(learning.getUrl());
		retorno.setDescription(learning.getDescription());
		retorno.setDeadline(learning.getCreationDate());
		retorno.setTypeActivity(HomeworkMobile.LEARNING_OBJECT);
		
		return retorno;
	}
	
	/**
	 * Method that converts AMADeUs Homework object list into Mobile Homework object list
	 * @param homes - AMADeUs Homework object list to be converted
	 * @return - Converted Mobile Homework object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> converterHomeworks(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework> homes){
	       
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework h : homes){
		   retorno.add(Conversor.converterHomework(h));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Material object into Mobile Material object
	 * @param mat - AMADeUs Material object to be converted
	 * @return - Mobile Material object converted
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile converterMaterial(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material mat){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile();
		
		retorno.setId(mat.getId());
		retorno.setName(mat.getArchiveName());
		retorno.setAuthor(converterPerson(mat.getAuthor()));
		retorno.setPostDate(mat.getCreationDate());
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Mobile Material object list into Mobile Material object list 
	 * @param mats - AMADeUs Material object list
	 * @return - Mobile Material object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile> converterMaterials(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material> mats){
	       
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material mat : mats){
		   retorno.add(Conversor.converterMaterial(mat));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Keyword object into Mobile Keyword object
	 * @param key - AMADeUs Keyword object to be converted
	 * @return - Converted Keywork object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile converterKeyword(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword key){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile();
		
		retorno.setId(key.getId());
		retorno.setName(key.getName());
		retorno.setPopularity(key.getPopularity());
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Keyword object list into a Mobile Keyword HashSet object
	 * @param keys - AMADeUs Keyword object list to be converted
	 * @return - Mobile Keywork HashSet object list
	 */
	public static HashSet<br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile> converterKeywords(Set<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword> keys){
	    
		HashSet<br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile> retorno = new HashSet<br.ufpe.cin.amadeus.amadeus_mobile.basics.KeywordMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword k : keys){
		   retorno.add(Conversor.converterKeyword(k));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Choice object into Mobile Choice object
	 * @param ch - AMADeUs Choice object to be converted
	 * @return - Converted Mobile Choice object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile converterChoice(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice ch){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile();
		
		retorno.setId(ch.getId());
		retorno.setAlternative(ch.getAlternative());
		retorno.setVotes(ch.getVotes());
		retorno.setPercentage(ch.getPercentage());
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Choice object list into Mobile Choice object list
	 * @param chs - AMADeUs Choice object list to be converted
	 * @return - Converted Mobile Choice object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile> converterChoices(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice> chs){
    
	List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile>();
	
	for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice c : chs){
	   retorno.add(Conversor.converterChoice(c));			
	}
	
	return retorno;
}
	
	/**
	 * Method that converts AMADeUs Poll object into Mobile Poll Object
	 * @param p - AMADeUs Poll object to be converted
	 * @return - Converted Mobile Poll object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile converterPool(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll p){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile();
		
		retorno.setId(p.getId());
		retorno.setName(p.getName());
		retorno.setQuestion(p.getQuestion());
		retorno.setInitDate(p.getCreationDate());
		retorno.setFinishDate(p.getFinishDate());
		retorno.setAnswered(false);
		retorno.setChoices(converterChoices(p.getChoices()));
		retorno.setAnsewered(converterAnswers(p.getAnswers()));
		
		return retorno;	
	}
	
	
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile converterLearningObject (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject learning){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile();
		
		retorno.setId(learning.getId());
		retorno.setName(learning.getName());
		retorno.setDescription(learning.getDescription());
		retorno.setDatePublication(learning.getCreationDate());
		retorno.setUrl(learning.getUrl());
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Poll object list into Mobile Poll object list
	 * @param pls - AMADeUs Poll object list
	 * @return - Converted Mobile object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile> converterPools(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll> pls){
	       
		List<br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll p : pls){
		   retorno.add(Conversor.converterPool(p));			
		}
		
		return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Answer object into Mobile Answer object
	 * @param ans - AMADeUs Answer object to be converted
	 * @return - Converted Mobile Answer object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile converterAnswer(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer ans){
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile retorno = new br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile();
		
		retorno.setId(ans.getId());
		retorno.setAnswerDate(ans.getAnswerDate());
		retorno.setPerson(converterPerson(ans.getPerson()));
		
		return retorno;	
	}
	
	/**
	 * Method that converts AMADeUs Answer object list into Mobile Answer object list
	 * @param anss - AMADeUs Answer object list
	 * @return - Converted Mobile object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile> converterAnswers(List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer> anss){
    
		List<br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile>();
	
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer an : anss){
		   retorno.add(Conversor.converterAnswer(an));			
		}
	
	    return retorno;		
	}
	
	/**
	 * Method that converts AMADeUs Person object into Mobile Person object
	 * @param p - AMADeUs Person object to be converted
	 * @return - Converted Mobile Person object
	 */
	public static br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile converterPerson(br.ufpe.cin.amadeus.amadeus_web.domain.register.Person p){
		
		return new br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile(p.getId(), p.getAccessInfo().getLogin(), p.getPhoneNumber());
						 
	}
	
	/**
	 * Method that converts AMADeUs Person object list into Mobile Person object list
	 * @param persons - AMADeUs Person object list to be converted
	 * @return - Converted Mobile Person object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile> converterPersons(List<br.ufpe.cin.amadeus.amadeus_web.domain.register.Person> persons){
	    
		List<br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile>();
	
		for (br.ufpe.cin.amadeus.amadeus_web.domain.register.Person p : persons){
		   retorno.add(Conversor.converterPerson(p));			
		}
	
	    return retorno;		
	}
	
	/**
	 * Method that converts Mobile Poll object into AMADeUs Poll Object
	 * @param p - Mobile Poll object to be converted
	 * @return - Converted AMADeUs Poll object
	 */
	public static br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll converterPool(br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile p){
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll retorno = new br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll();
		
		retorno.setId(p.getId());
		retorno.setName(p.getName());
		retorno.setQuestion(p.getQuestion());
		retorno.setCreationDate(p.getInitDate());
		retorno.setFinishDate(p.getFinishDate());
		retorno.setChoices(converterChoices2(p.getChoices()));
		retorno.setAnswers(converterAnswers2(p.getAnsewered()));
		
		return retorno;	
	}
	
	/**
	 * Method that converts Mobile Choice object into  AMADeUs Choice object
	 * @param ch - Mobile Choice object to be converted
	 * @return - Converted  AMADeUs Choice object
	 */
	public static br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice converterChoice(br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile ch){
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice retorno = new br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice();
		retorno.setId(ch.getId());
		retorno.setAlternative(ch.getAlternative());
		retorno.setVotes(ch.getVotes());
		retorno.setPercentage(ch.getPercentage());
		
		return retorno;	
	}
	
	/**
	 * Method that converts Mobile Choiceobject list into AMADeUs Choice object list
	 * @param chs - Mobile Choice object list to be converted
	 * @return - Converted AMADeUs Choice object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice> converterChoices2(List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile> chs){
    
	List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice>();
	
	for (br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile c : chs){
	   retorno.add(Conversor.converterChoice(c));			
	}
	
	return retorno;		
}
	
	/**
	 * Method that converts Mobile Answer object into AMADeUs Answer object
	 * @param ans - Mobile Answer object to be converted
	 * @return - Converted AMADeUs Answer object
	 */
	public static br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer converterAnswer(br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile ans){
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer retorno = new br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer();
		
		retorno.setId(ans.getId());
		retorno.setAnswerDate(ans.getAnswerDate());
		retorno.setPerson(converterPerson(ans.getPerson()));
		
		return retorno;	
	}
	
	/**
	 * Method that converts Mobile Answer object list into AMADeUs Answer object list
	 * @param anss - Mobile Answer object list
	 * @return - Converted AMADeUs Answer object list
	 */
	public static List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer> converterAnswers2(List<br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile> anss){
    
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer>();
	
		for (br.ufpe.cin.amadeus.amadeus_mobile.basics.AnswerMobile an : anss){
		   retorno.add(Conversor.converterAnswer(an));			
		}
	
	    return retorno;		
	}
	
	/**
	 * Method that converts Mobile Person object into AMADeUs Person object
	 * @param p - Mobile Person object to be converted
	 * @return - Converted AMADeUs Person object
	 */
	public static br.ufpe.cin.amadeus.amadeus_web.domain.register.Person converterPerson(br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile p){
		
		br.ufpe.cin.amadeus.amadeus_web.domain.register.Person p1 = new br.ufpe.cin.amadeus.amadeus_web.domain.register.Person();
		p1.setId(p.getId());
		p1.setName(p.getName());
		p1.setPhoneNumber(p.getPhoneNumber());
		
		return p1;
	}
	
}