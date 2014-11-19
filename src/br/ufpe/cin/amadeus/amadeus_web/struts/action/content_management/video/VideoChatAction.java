/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.video;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.messages.Messages;
import br.ufpe.cin.amadeus.amadeus_web.util.UtilDWR;
import server.ChatServerAmadeusLMS;

public class VideoChatAction extends org.apache.struts.actions.DispatchAction {
	
	public static final String FORWARD_fShowIriz = "fShowIriz";
	private Facade facade = Facade.getInstance();
	private DataInputStream i;
	private DataOutputStream o;
	private ChatServerAmadeusLMS chatServer = new ChatServerAmadeusLMS();
	private Thread threadChatServer;
	
	public ActionForward connectChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = null;
		
		String room = (String) request.getSession().getAttribute("idVideo");
		
		if(room == null) {
			AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
			loggedUser = facade.searchUserById(loggedUser.getId());
			
			String userLogin = loggedUser.getLogin();
			room = request.getParameter("idVideo");
			
			String server = "localhost";
			int port = 8888;
			Socket s = null;
			
			try {
				s = new Socket (server, port);
			} catch (SocketException e) {
				System.out.println("Conectando o Irene");
				try {
					this.threadChatServer = new Thread(this.chatServer);
					
					this.threadChatServer.start();
					
					s = new Socket (server, port);
				} catch (IOException ioE) {
					ioE.printStackTrace();
				}
			}
			
			this.o = new DataOutputStream (new BufferedOutputStream(s.getOutputStream()));
			this.i = new DataInputStream (new BufferedInputStream(s.getInputStream()));
					
			try {
				o.writeUTF((String) "<message><request from=\""+userLogin+"\" type=\"login\">"+room+"</request></message>");
				o.writeUTF((String) "<message><request from=\""+userLogin+"\" type=\"room\">"+room+"</request></message>");
		
				o.flush ();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
				
			request.getSession().setAttribute("socketIriz", s);
			request.getSession().setAttribute("idVideo", room);
			request.getSession().setAttribute("cssText", this.getCSSText());
			
			request.setAttribute("idVideo", room);
			request.setAttribute("userName", userLogin);
			
			forward = mapping.findForward(FORWARD_fShowIriz);
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String msg = "<script type='text/javascript'>alert('"+Messages.getString("videoChat.isConnected")+"'); window.close();</script>";
			out.print(msg);
		}
		
		return forward;
	}
	
	public ActionForward sendMsgChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		String name = loggedUser.getLogin(); 
		
		String msgChat = request.getParameter("msgChat");
		
		Socket s = (Socket) request.getSession().getAttribute("socketIriz");
			
		this.o = new DataOutputStream (new BufferedOutputStream(s.getOutputStream()));
		
		try {
			o.writeUTF((String) "<message><text from=\""+name+"\" destination=\"\">"+msgChat+"</text></message>");
			o.flush ();		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	public ActionForward getMsgChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
			loggedUser = facade.searchUserById(loggedUser.getId());
			
			String name = loggedUser.getLogin(); 
			
			Socket s = (Socket) request.getSession().getAttribute("socketIriz");
			String room = (String) request.getSession().getAttribute("idVideo");
				
			this.i = new DataInputStream (new BufferedInputStream(s.getInputStream()));
			
			String line = null;
			
		
			while(i.available() != 0){
				line = i.readUTF();
				//System.out.println(line);
				String dataHoraConfig = "HH:mm:ss";
			    SimpleDateFormat formatador = new SimpleDateFormat(dataHoraConfig);

			    Date data = new Date(); 
			    String hora = formatador.format(data);

			    SAXBuilder parser = new SAXBuilder();
		        
		        	
	        	ByteArrayInputStream bin = new ByteArrayInputStream(line.getBytes("UTF-8"));
		        	
		        Document doc = parser.build(bin);
 
	            Element message = (Element)doc.getRootElement().getChildren().get(0);
		            
	            if(message.getName().equals("lobby")){
	            	//Obtem a lista de usuários.
	            	List<Element> users = new ArrayList<Element>();
	            	
	            	users =	message.getChild("room").getChild("user_list").getChildren();
	            	
	            	String userList = message.getChild("room").getAttributeValue("id") +",";
	            	
	            	for (Element user : users) {
	            		userList = userList + user.getAttributeValue("login") + user.getAttributeValue("mood") +",";	
					}
	            	
	            	userList = userList.substring(0, userList.length()-1);
	            	response.setContentType("text/html");
	        		PrintWriter out = response.getWriter();
	        		out.print(userList);
	        		
	        		UtilDWR.getUtil().addFunctionCall("ajaxReverseSendUserList",userList);
	            } else if(message.getName().equals("text")){
	            	//Envia mensagem para os usuários.
	            	if(message.getAttributeValue("from").equals(name)){
	            		String cssText = (String) request.getSession().getAttribute("cssText");
	            		UtilDWR.getUtil().addFunctionCall("ajaxReverseSendMsg", room, hora, name, line, cssText);
	            	}
	            } 
			}
		} catch (Exception ex) {
			System.out.println("Erro no método getMsgChat: provavelmente o usuário estava desconectado do servidor de chat.");
			
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				String msg = "close";
				out.print(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ActionForward logoffChat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
			loggedUser = facade.searchUserById(loggedUser.getId());
			
			String name = loggedUser.getLogin(); 
					
			Socket s = (Socket) request.getSession().getAttribute("socketIriz");
			String idVideo = (String) request.getSession().getAttribute("idVideo"); 
			String room = (String) request.getSession().getAttribute("idVideo");
			
			this.o = new DataOutputStream (new BufferedOutputStream(s.getOutputStream()));
				
			try {
				o.writeUTF((String) "<message><request from=\""+name+"\" type=\"logoff\">"+idVideo+"</request></message>");
			
				o.flush ();	
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			UtilDWR.getUtil().addFunctionCall("ajaxReverseLogoffChat", room, name);
			
			request.getSession().setAttribute("socketIriz", null);
			request.getSession().setAttribute("idVideo", null);
			request.getSession().setAttribute("cssText", null);
		} catch (Exception e) {
			System.out.println("Erro no método logoffChat: provavelmente o usuário estava desconectado do servidor de chat.");
		}
		return null;
	}
	
	public static void logoffChatMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = Facade.getInstance().searchUserById(loggedUser.getId());
		
		String name = loggedUser.getLogin(); 
				
		Socket s = (Socket) request.getSession().getAttribute("socketIriz");
		String idVideo = (String) request.getSession().getAttribute("idVideo"); 
		String room = (String) request.getSession().getAttribute("idVideo");
		
		 DataOutputStream o = new DataOutputStream (new BufferedOutputStream(s.getOutputStream()));
			
		try {
			o.writeUTF((String) "<message><request from=\""+name+"\" type=\"logoff\">"+idVideo+"</request></message>");
		
			o.flush ();	
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//UtilDWR.getUtil().addFunctionCall("ajaxReverseLogoffChatMain", room, name);
		
		request.getSession().setAttribute("socketIriz", null);
		request.getSession().setAttribute("idVideo", null);
		request.getSession().setAttribute("cssText", null);
	}
	
	public ActionForward changeMood(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		String name = loggedUser.getLogin(); 
		
		String idMood = request.getParameter("idMood");		
		
		Socket s = (Socket) request.getSession().getAttribute("socketIriz");
		String room = (String) request.getSession().getAttribute("idVideo");
		
		this.o = new DataOutputStream (new BufferedOutputStream(s.getOutputStream()));
			
		try {
			o.writeUTF((String) "<message><settings from=\""+name+"\"><mood value=\""+idMood+"\"/></settings></message>");
		
			o.flush ();	
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		UtilDWR.getUtil().addFunctionCall("ajaxReverseChangeMood", room, name, idMood);
		
		return null;
	}
	
	public String getCSSText() {
		String cssText = "none";
		
		int key = (int) (Math.random() * 4);
		
		switch (key) {
		case 0:
			cssText = "userA";
			break;
		case 1:
			cssText = "userB";
			break;
		case 2:
			cssText = "userC";
			break;
		case 3:
			cssText = "userD";
			break;
		default:
			break;
		}
		
		return cssText; 
	}
}
