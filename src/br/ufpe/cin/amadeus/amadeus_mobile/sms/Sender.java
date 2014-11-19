/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.hibernate.HibernateException;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.AmadeusFacade;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.HibernateUtil;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SettingsActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Sender {

	private final static Sender instance = new Sender();
	private AmadeusFacade amadeusFacade;
	private FacadeMobile facade;

	public Sender() {
		this.amadeusFacade = AmadeusFacade.getInstance();
		this.facade =  FacadeMobile.getInstance();
	}
	
	public synchronized void createSMS(NoticeMobile sms, String courseName) {
		int id = this.facade.getLastId(sms.getIdCourse());
		sms.setId(id + 1);
		String stringId = "amadeus.mobile " + sms.getId();
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			ArrayList<String> users = amadeusFacade.findLoginsByCourse(sms.getIdCourse());
			for(int i = 0; i < users.size(); i++) {
				PersonMobile user = this.facade.getLogin(users.get(i));
				if(user != null) {
				//String ddd = user.getPhoneNumber().substring(1, 3);
				//String number = "55"+ddd.toString() + user.getPhoneNumber().substring(4, 12);
					String number = "55" + user.getPhoneNumber();
					System.out.println("#: "+number);
					//SENDING MESSAGE
					System.out.println("Amadeus:" + sms.getContent());
					//Para enviar sms, descomente o c�digo abaixo.
					this.send(number, sms.getContent(), SettingsActions.mobileSettings.getLogin(), stringId);
				}
			}
			
			this.facade.addNotice(sms);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} catch (HibernateException e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
	}
	
	/**
	 * Method that returns the instance of the class
	 * @return - Sender instance
	 */
	public static Sender getInstance() {
		return instance;
	}

	/**
	 * Method that sends the message to the SMS Gateway (Human)
	 * Prints on the screen the sent message response status
	 */
	public void send(String to, String body, String from, String id) {
		
		PostMethod http = new PostMethod("http://system.human.com.br/GatewayIntegration/msgSms.do");
		
		if (body.length() + from.length() > 142) {
		    body = body.substring(0,142 - from.length());
		}
		
		
		
		http.addParameter("dispatch", "send");
		http.addParameter("account", SystemActions.mobileSettings.getLogin()); // parametro passado pela Human
		http.addParameter("code", SystemActions.mobileSettings.getPassword()); // parametro passado pela Human
		http.addParameter("from", from);
		http.addParameter("msg", body);
		http.addParameter("to", to);
		http.addParameter("id", id);
		
		System.out.println("LALA: "+from);
		System.out.println("LALA: "+body);
		System.out.println("LALA: "+to);
		System.out.println("LALA: "+id);
		

		HttpClient httpclient = new HttpClient();
		//httpclient.setTimeout(60000); // Timeout de 60s
		try {
		    httpclient.executeMethod(http);
		    String response = http.getResponseBodyAsString();
		    String responseCode = "";
		    // Separa os 3 primeiros d�gitos do retorno em responseCode
		    if (response != null && response.length() > 3) {
		        responseCode = response.substring(0, 3);
		    }
		    
		    /** 
		     *  000 - Message Sent
		     *  010 - Empty message content
		     *  011 - Message body invalid
		     *  012 - Message content overflow
		     *  012 - Empty 'to' mobile number
		     *  013 - Incorrect or incomplete 'to' mobile number
		     *  080 - Message with same ID already sent
		     *  900 - Authentication error
		     *  990 - Account Limit Reached 
		     *  999 - Unknown Error
		     */
		    if ("000".equals(responseCode)) {
		        System.out.println("Enviou Mensagem!");
		    } else if ("010".equals(responseCode)) {
		        System.out.println("Erro enviando (010 - Mensagem vazia)");
		    } else if ("011".equals(responseCode)) {
		        System.out.println("Erro enviando (011 - Mensagem invalida)");
		    } else if ("012".equals(responseCode)) {
		        System.out.println("Erro enviando (012 - Destinatario vazio)");
		    } else if ("013".equals(responseCode)) {
		        System.out.println("Erro enviando (013 - Destinatario invalido)");
		    } else if ("080".equals(responseCode)) {
		        System.out.println("Envio repetido (080 - Id ja usado)");
		    } else if ("900".equals(responseCode)) {
		        System.out.println("Erro de autenticacao na conta.");
		    } else if ("990".equals(responseCode)) {
		        System.out.println("Erro - Creditos terminaram na conta.");
		    } else if ("999".equals(responseCode)) {
		        System.out.println("Erro desconhecido (999 - desconhecido)");
		    } else {
		        System.out.println("Erro ao conectar com o HumanGateway "+responseCode);
		    }
		} catch (ConnectException e) {
		    System.out.println("");
		} catch (SocketTimeoutException e) {
		    System.out.println("");
		} catch (IOException e) {
		    System.out.println("");
		} catch (Exception e) {
		    System.out.println("");
		}
	}
}