/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.settings.WebSettings;

public class MailSender extends Thread {

	private static List<MailMessage> mailMessages = new ArrayList<MailMessage>();
	private static WebSettings WEB_SETTINGS = WebSettings.getInstance();
	private static MailSender mailSender = new MailSender();
	
	@Override
	public void run() {
		while (true) {
			if(!mailMessages.isEmpty()) {
				MailMessage mailMessage = mailMessages.get(0);
				
				java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
				String[] recipients = mailMessage.getTo();
				String from = WEB_SETTINGS.getMailFrom();
				
				boolean debug = true;
				
				Properties props = new Properties();
				
				props.put("mail.smtp.host", ""+WEB_SETTINGS.getMailSmtpHost()+"");
				props.put("mail.smtp.auth", ""+WEB_SETTINGS.isMailSmtpAuth()+"");
				props.put("mail.debug", ""+WEB_SETTINGS.isMailDebug()+"");
				props.put("mail.smtp.port", ""+WEB_SETTINGS.getMailPort()+"");
				props.put("mail.smtp.socketFactory.port",  ""+WEB_SETTINGS.getMailPort()+"");
				props.put("mail.smtp.socketFactory.class", ""+WEB_SETTINGS.getMailSocketFactoryClass()+"");
				props.put("mail.smtp.socketFactory.fallback", ""+WEB_SETTINGS.isMailSocketFactoryFallback()+"");
				props.put("mail.smtp.starttls.enable",""+WEB_SETTINGS.isMailStartTLSEnable()+"");
				props.put("mail.smtp.starttls.required",""+WEB_SETTINGS.isMailStartTLSRequired()+"");
				
				Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(WEB_SETTINGS.getMailFrom(), WEB_SETTINGS.getMailPassword());
						}
					});
		
				session.setDebug(debug);
		
				try {
					Message msg = new MimeMessage(session);
					InternetAddress addressFrom = new InternetAddress(from);
					msg.setFrom(addressFrom);
			
					InternetAddress[] addressTo =
					new InternetAddress[recipients.length];
			
					for (int i = 0; i < recipients.length; i++) {
					addressTo[i] = new InternetAddress(recipients[i]);
					}
			
					msg.setRecipients(Message.RecipientType.TO, addressTo);
			
					msg.setSubject(mailMessage.getSubject());
					msg.setContent(mailMessage.getMessage(), "text/html");
					Transport.send(msg);
				} catch (MessagingException e) {
					e.printStackTrace();
				} finally {
					mailMessages.remove(0);
					if(mailMessages.isEmpty()) {
						synchronized (mailSender) {
							try {
								mailSender.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				synchronized (mailSender) {
					try {
						mailSender.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void sendMail(AccessInfo access, String subject, String message)
			throws MessagingException {
		sendMail(access.getPerson().getEmail(), subject, message);
	}

	public static void sendMail(Person person, String subject, String message)
			throws MessagingException {
		sendMail(person.getEmail(), subject, message);
	}
	
	public static synchronized void sendMail(String recipient, String subject, String message)  {
		MailMessage mailMessage = new MailMessage();
		mailMessage.setTo(recipient);
		mailMessage.setSubject(subject);
		mailMessage.setMessage(message);
		
		addMailMessage(mailMessage);
	}
	
	public static synchronized void sendMail(List<String> emails, String subject, String message)  {

		emails = emailNoRepeated(emails);
		
		for (String email : emails) {
			if(!email.trim().equals("")){
				MailMessage mailMessage = new MailMessage();
				
				mailMessage.setTo(email);
				mailMessage.setSubject(subject);
				mailMessage.setMessage(message);
				
				addMailMessage(mailMessage);
			}
		}
	}

	private static List<String> emailNoRepeated(List<String> emails){
		List<String> mails = new ArrayList<String>();
		
		for(String email : emails) {
			if(!mails.contains(email)){
				mails.add(email);
			}
		}
		
		return mails; 
	}
	
	public static synchronized void addMailMessage(MailMessage mailMessage) {
		mailMessages.add(mailMessage);
		if(mailSender.getState() == State.WAITING) {
			synchronized (mailSender) {
				mailSender.notify();
			}
		} else if(mailSender.getState() == State.NEW){
			mailSender.start();
		}
	}
}
