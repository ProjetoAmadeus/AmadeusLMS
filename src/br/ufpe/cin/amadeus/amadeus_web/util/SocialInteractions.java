package br.ufpe.cin.amadeus.amadeus_web.util;

import java.util.Collection;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import java.util.Set;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.io.GraphMLWriter;


import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

/**
 * Classe responsável por centralizar os métodos que retornarão as interações
 * sociais das ferramentas do amadeus.
 * 
 * @author Nailson Cunha
 * 
 */
public final class SocialInteractions {

	private SocialInteractions() {

	}

	/**
	 * Método que retorna as interações sociais referentes a ferramenta de mensagens assíncronas.
	 * @return Um multimap<Person,Person> que representa as interações sociais.
	 *         Dessa forma Person "key" interage com os Persons "values"
	 * @throws Exception Lancada caso a data de fim venha antes da data de início 
	 */
	@SuppressWarnings("unchecked")
	public static MultiValueMap getSocialInteractionsFromMessenger(Course course, Date inicio, Date fim) throws Exception {
		
		if(fim.before(inicio))
			throw new Exception("Data fim menor que a Data início.");
		
		MultiMap interactions = new MultiValueMap();
		Facade facade = Facade.getInstance();

		List<Person> teachers = facade.getTeachersByCourse(course);
		List<Person> persons = facade.listStudentsByCourse(course);

		for (Person p : persons) {
			for (MessengerMessage m : p.getSent()) {
				if( (isBetweenDates(m, inicio, fim)) && ( persons.contains(m.getReceiver()) || teachers.contains(m.getReceiver()) ) )
					interactions.put(p.getName(), m.getReceiver().getName());
			}
		}
		
		Set keys = interactions.keySet();
		System.out.println("Cheguei");
		for(Object k : keys){
			System.out.println(k + " : " + interactions.get(k));
		}
		
		return (MultiValueMap) interactions;
	}
	
	
	/**
	 * Método que retorna as interações sociais referentes a ferramenta de monitoramento
	 * do twitter.
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public static MultiMap getSocialInteractionsFromTwitterTool(Date inicio, Date fim){
		Facade facade = Facade.getInstance();
		List<Tweet> tweets = facade.getTweetBetweenDates(inicio, fim);
		MultiMap map = new MultiValueMap();
		for(Tweet t : tweets){
			if(t.getDateOfTweet().after(inicio) && t.getDateOfTweet().before(fim))
				map.put(t.getUserSender().getName(), t.getUserTarget().getName());
		}
		Set keys = map.keySet();
		for(Object k : keys){
			System.out.println(k + " : " + map.get(k));
		}
		return map;
	}
	
	
	/**
	 * Método que retorna as interações sociais referentes a ferramenta de forum.
	 * @return Um multimap<Person,Person> que representa as interações sociais.
	 *         Dessa forma Person "key" interage com os Persons "values" paramterizado pelas datas iniciais e finais.
	 */
	@SuppressWarnings("unchecked")
	public static MultiValueMap getSocialInteractionsFromForumsByCourseAndData(Course course, Date dataini, Date datafim) {
		MultiMap mhm = new MultiValueMap();
		Facade facade = Facade.getInstance();

		Course crs = facade.getCoursesById(course.getId());
		List<Module> mdls = crs.getModules();
		for (Module m : mdls) {
			for(Forum f: m.getForums()){
				for(Message msg: f.getMessages()){
					if(msg.getMessageReply()!=null &&msg.getDate().after(dataini)&&msg.getDate().before(datafim)&&!msg.getAuthor().equals(msg.getMessageReply().getAuthor())){
						mhm.put(msg.getAuthor().getName(), msg.getMessageReply().getAuthor().getName());
					}else if(msg.getMessageReply() == null && msg.getDate().after(dataini) && msg.getDate().before(datafim)){
						mhm.put(msg.getAuthor().getName(), "EmptyUserReply");
						
					}
						
					
					
				}
				
			}
		}
		
		Iterator it2 = mhm.keySet().iterator();
		while(it2.hasNext()){
			System.out.println("Retornando chaves:"+it2.next().toString());
		}
		
		Iterator it = mhm.values().iterator();
		while(it.hasNext()){
			System.out.println("Retornando valores:"+it.next().toString());
		}
		
		return (MultiValueMap) mhm;
	}
	

	private static boolean isBetweenDates(MessengerMessage message, Date inicio, Date fim){
		return (message.getDate().after(inicio) && message.getDate().before(fim));
	}
	
	public static void generateReport(String texto, String nomearquivo){
		Document document = new Document(); 
		try { 
			PdfWriter.getInstance(document, new FileOutputStream(nomearquivo)); 
			document.open();
			document.add(new Paragraph(texto)); 
		} 
		catch(DocumentException de) { 
			System.err.println(de.getMessage()); 
		} 
		catch(IOException ioe) { 
			System.err.println(ioe.getMessage()); 
		} document.close(); 
		
	}	
			
	
}
