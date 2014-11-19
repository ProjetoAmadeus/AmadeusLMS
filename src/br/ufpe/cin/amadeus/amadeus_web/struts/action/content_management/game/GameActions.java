/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco

Este arquivo ï¿½ parte do programa Amadeus Sistema de Gestï¿½o de Aprendizagem, ou simplesmente Amadeus LMS

O Amadeus LMS ï¿½ um software livre; vocï¿½ pode redistribui-lo e/ou modifica-lo dentro dos termos da Licenï¿½a Pï¿½blica Geral GNU como
publicada pela Fundaï¿½ï¿½o do Software Livre (FSF); na versï¿½o 2 da Licenï¿½a.

Este programa ï¿½ distribuï¿½do na esperanï¿½a que possa ser ï¿½til, mas SEM NENHUMA GARANTIA; sem uma garantia implï¿½cita de ADEQUAï¿½ï¿½O a qualquer MERCADO ou APLICAï¿½ï¿½O EM PARTICULAR. Veja a Licenï¿½a Pï¿½blica Geral GNU para maiores detalhes.

Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU, sob o tï¿½tulo "LICENCA.txt", junto com este programa, se nï¿½o, escreva para a Fundaï¿½ï¿½o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 **/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.tools.javac.util.Context;

import antlr.Parser;
import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Image;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Thumbnail;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.settings.WebSettings;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.AmadeusSessionListener;



public class GameActions extends org.apache.struts.actions.DispatchAction {

	private Facade facade = null;

	private final String FORWARD_SHOW_VIEW_NEW_GAME_ACTIVITY = "fShowViewNewGameActivity";
	private final String FORWARD_SHOW_VIEW_GAME = "fShowGame";
	private final String FORWARD_SHOW_PLAY_GAME = "fShowPlayGame";
	private final String FORWARD_SHOW_GRAPHIC_GAME = "fShowGraphicGame";
	
	public ActionForward showGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		facade = Facade.getInstance(); 

		int gameID = Integer.valueOf(request.getParameter("idGame"));

		Game game = facade.getGameById(gameID);

		request.getSession().setAttribute("game", game);
		
		//TODO - LOG - Abertura do Jogo - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_ABRIR_JOGO);
		log.setIdObjeto(game.getId());
		this.facade.saveLog(log);

		return mapping.findForward(FORWARD_SHOW_VIEW_GAME);
	}
	
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("activities.game.changeOrderGame", "changeOrderGame");
		
		return map;
	}

	public ActionForward listGames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			int courseID = Integer.valueOf(request.getParameter("courseID"));
			facade = Facade.getInstance();
			List<Game> games = facade.getGamesByCourse(courseID);

			ObjectOutputStream out = new ObjectOutputStream(response
					.getOutputStream());
			out.writeObject(games);
			out.flush();

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward getPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Person person = null;
			int personID = Integer.valueOf((String) request
					.getParameter("personID"));
			String sessionID = (String) request.getParameter("sessionID");

			HttpSession correctSession = AmadeusSessionListener.sessionMap
					.get(sessionID);


			if (correctSession != null) {
				AccessInfo info = (AccessInfo) correctSession
						.getAttribute("user");
				if (info != null && info.getPerson().getId() == personID){
					person = info.getPerson();
					person.getImage();
				
				}
					
			}

			ObjectOutputStream out = new ObjectOutputStream(response
					.getOutputStream());
			out.writeObject(person);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward openAmadeusGames(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		Game game = (Game) request.getSession().getAttribute("game");
		AccessInfo accessInfo = (AccessInfo) session.getAttribute("user");
		int courseID = game.getModule().getCourse().getId();

		String url = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/amadeusgames"
				+ "/index.jsp?sessionID=" + session.getId() + "&courseID="
				+ courseID + "&personID=" + accessInfo.getPerson().getId();

		try {
			response.sendRedirect(url);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward showViewNewGameActivity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		facade = Facade.getInstance(); 
		int moduleId = Integer.parseInt(request.getParameter("moduleId"));

		Module module = facade.getModuleById(moduleId);

		request.setAttribute("module", module);

		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_GAME_ACTIVITY);
	}

	public ActionForward newGame(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		Game game = new Game();
		game.setName(myForm.getString("nameGame"));
		game.setDescription(myForm.getString("descriptionGame"));
		game.setCreationDate(new Date());
		
		
		FormFile myFile = (FormFile) myForm.get("image");
		
		//Salvando a imagem
		try {
			byte[] byteImagem = myFile.getFileData();
			if(byteImagem.length>0)
			{
				game.setImage(byteImagem);				
			}
			else
			{				
				//Caso não seja informado imagem carrega uma imagem padrão
				String classPath = request.getRealPath("/");
				
				File f = new File(classPath+"themes/default/imgs/005.jpg");
			    FileInputStream fin = null;
			    FileChannel ch = null;
			    try {
			    	fin = new FileInputStream(f);
			        byte fileContent[] = new byte[(int)f.length()];
			        fin.read(fileContent);
			        game.setImage(fileContent);
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    } finally {
			        try {
			            if (fin != null) {
			                fin.close();
			            }
			            if (ch != null) {
			                ch.close();
			            }
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }
			    }
				
			}
		} catch (FileNotFoundException e1) {
			messages.add("editError", new ActionMessage(e1.getMessage()));
		} catch (IOException e1) {
			messages.add("editError", new ActionMessage(e1.getMessage()));
		}

		int moduleId = Integer.parseInt(request.getParameter("moduleId"));

		Module module = facade.getModuleById(moduleId);

		
		//Salvando o jogo
		myFile = (FormFile) myForm.get("urlGame");
	    FileOutputStream fout = null;
	    try {
	    	
	    	byte[] bytes = myFile.getFileData();
	    	
	    	if(bytes.length>0)
	    	{
	    		String path= request.getRealPath("/");
	    		String path2 = "/games/";
	    		String path3 = game.getName()+".swf";
	    		
	    		//pasta do curso
	    		path2 += "course" + module.getCourse().getId();
	    		
	    		File f = new File(path+path2);
	    		
	    		if(!f.exists())
	    		{
	    			f.mkdir();
	    		}
	    		
	    		//pasta do modulo
	    		path2 += "/module" + moduleId;
	    		
	    		f = new File(path+path2);	    		
	    		
	    		if(!f.exists())
	    		{
	    			f.mkdir();
	    		}	
	    		
	    		path2 += "/";
	    		
	    		//Arquivo do jogo 
	    		f = new File(path+path2+path3);
	    		int i = 0;
	    		
	    		//caso exista arquivo com mesmo nome
	    		while(f.exists())
	    		{
	    			path3 = game.getName()+i+".swf";
	    			f = new File(path+path2+path3);
	    			i++;
	    		}
	    		
	    		fout = new FileOutputStream(f);
	    		fout.write(bytes);
	    		
	    		//ajustar link com host correto
	    		game.setUrl("http://"+SystemActions.webSettings.getSystemGeneralDomain()+path2+path3);
	    	}
	    	else
	    	{
	    		//caso seja link externo
	    		game.setUrl(myForm.getString("externalUrlGame"));
	    		game.setLinkExterno(true);
	    	}  

	    } catch (IOException e) {
	    	e.printStackTrace();
	    	System.out.println("Erro de leitura do arquivo");
	    	messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
	    } finally {
	        try {
	            if(fout != null) {
	            	fout.close();
	            }
	        } catch (IOException e) {
	        	System.out.println("Erro de fechamento do arquivo");
		    	messages.add("confirmation", new ActionMessage(
						"activities.game.serverOut"));
	        }
	    }
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			module.getGames().add(game);

			facade.flush();

			UtilActivities.eraseAndWriteNameActivity(module.getId());
			
			return mapping.findForward("fShowViewNewGameActivity");
		}
	}
	
	public ActionForward editGame(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		
		int idGame = Integer.parseInt(request.getParameter("idGame"));

		facade = Facade.getInstance();
		//alerando tipos simples
		Game game = facade.getGameById(idGame);
		game.setName(myForm.getString("nameGame"));
		game.setDescription(myForm.getString("descriptionGame"));
		
		//alterar imagem
		FormFile myFile = (FormFile) myForm.get("image");
		
		try {
			byte[] byteImagem = myFile.getFileData();
			if(byteImagem.length>0)
			{
				game.setImage(byteImagem);				
			}			
		} catch (FileNotFoundException e1) {
			messages.add("editError", new ActionMessage(e1.getMessage()));
		} catch (IOException e1) {
			messages.add("editError", new ActionMessage(e1.getMessage()));
		}

		Module module = game.getModule();

		//aletrando url
		myFile = (FormFile) myForm.get("urlGame");
	    FileOutputStream fout = null;
	    try {
	    		    	
	    	byte[] bytes = myFile.getFileData();
	    	
	    	if(bytes.length>0)
	    	{
	    		// deletando swf antigo
		    	if(!game.getLinkExterno())
				{
					String path= request.getRealPath("/");
		    		String path3 = game.getUrl().substring(33);
		    		
		    		File file = new File(path+path3);
		    		
		    		file.delete();
				}
		    	//salvando o novo
	    		String path= request.getRealPath("/");
	    		String path2 = "//games//";
	    		String path3 = game.getName()+".swf";
	    		
	    		
	    		//pasta do curso
	    		path2 += "course" + module.getCourse().getId();
	    		
	    		File f = new File(path+path2);
	    		
	    		if(!f.exists())
	    		{
	    			f.mkdir();
	    		}
	    		
	    		//pasta do modulo
	    		path2 += "/module" + module.getId();
	    		
	    		f = new File(path+path2);	    		
	    		
	    		if(!f.exists())
	    		{
	    			f.mkdir();
	    		}	
	    		
	    		path2 += "/";
	    		
	    		f = new File(path+path2+path3);
	    		int i = 0;
	    		
	    		while(f.exists())
	    		{
	    			path3 = game.getName()+i+".swf";
	    			f = new File(path+path2+path3);
	    			i++;
	    		}
	    		
	    		fout = new FileOutputStream(f);
	    		fout.write(bytes);
	    		
	    		//ajustar link com host correto
	    		game.setUrl("http://"+SystemActions.webSettings.getSystemGeneralDomain()+path2+path3);
	    		game.setLinkExterno(false);
	    	}
	    	else
	    	{
	    		String externalLink = myForm.getString("externalUrlGame");
	    		if(!externalLink.equals(""))
	    		{
	    			// deletando swf antigo
			    	if(!game.getLinkExterno())
					{
						String path= request.getRealPath("/");
			    		String path3 = game.getUrl().substring(33);
			    		
			    		File file = new File(path+path3);
			    		
			    		file.delete();
					}
			    	//atualinado link externo
	    			game.setUrl(externalLink);
	    			game.setLinkExterno(true);	    			
	    		}
	    	}  

	    } catch (IOException e) {
	    	e.printStackTrace();
	    	System.out.println("Erro de leitura do arquivo");
	    	messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
	    } finally {
	        try {
	            if(fout != null) {
	            	fout.close();
	            }
	        } catch (IOException e) {
	        	System.out.println("Erro de fechamento do arquivo");
		    	messages.add("confirmation", new ActionMessage(
						"activities.game.serverOut"));
	        }
	    }
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		} else {

			module.getGames().add(game);

			facade.flush();

			UtilActivities.eraseAndWriteNameActivity(module.getId());
		}
		return null;
	}

	public ActionForward saveGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionMessages messages = new ActionMessages();
		DynaActionForm myForm = (DynaActionForm) form;
		Facade facade = Facade.getInstance();
		int idGame = Integer.parseInt(myForm.getString("idGame"));
		Course course = (Course) request.getSession().getAttribute("course");
		int idCourse = course.getId();

		Game game = facade.getGameById(idGame);

		game.setName(myForm.getString("nameGame"));
		game.setUrl(myForm.getString("urlGame"));
		game.setDescription(myForm.getString("descriptionGame"));

		facade.updateGame(game);

		Course courseResult = facade.getCoursesById(idCourse);

		request.getSession().removeAttribute("course");
		request.getSession().setAttribute("course", courseResult);

		StringBuffer caminho = new StringBuffer();

		try {
			// caminho.append(
			// "http://" + request.getServerName() + ":" +
			// request.getServerPort() + "/AmadeusGames"
			// + "/updategame.do?"
			// + URLEncoder.encode("id", "ISO-8859-1")
			// + "="
			// + URLEncoder.encode(Integer.toString(game.getIdMMJogos()),
			// "ISO-8859-1")
			// + "&"
			// + URLEncoder.encode("nmJogo", "ISO-8859-1")
			// + "="
			// + URLEncoder.encode(game.getName(), "ISO-8859-1")
			// + "&"
			// + URLEncoder.encode("caminho", "ISO-8859-1")
			// + "="
			// + URLEncoder.encode(game.getUrl(), "ISO-8859-1")
			// + "&"
			// + URLEncoder.encode("comentario", "ISO-8859-1")
			// + "="
			// + URLEncoder.encode(game.getDescription(), "ISO-8859-1")
			// );

			URL url = new URL(caminho.toString());
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("POST");
			urlcon.setRequestProperty("Content-type", "text/xml;charset=UTF8");
			urlcon.setDoInput(true);
			urlcon.setDoOutput(false);

			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon
					.getInputStream()));

			in.close();

		} catch (MalformedURLException e) {
			System.out.println("URL mal feita!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (IOException e) {
			System.out.println("URL nï¿½o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		}

		int idModule = Integer.parseInt(request.getParameter("idModule")) - 1;

		UtilActivities.eraseAndWriteNameActivity(idModule);

		return null;
	}

	public ActionForward deleteGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		

		Facade facade = Facade.getInstance();
//		ActionMessages messages = new ActionMessages();
		int idGame = Integer.parseInt(request.getParameter("idGame"));
		
		Game game = facade.getGameById(idGame);
		
		if(!game.getLinkExterno())
		{
			String path= request.getRealPath("/");//System.getProperty("catalina.base");
    		String path2 = "";
    		String path3 = game.getUrl().substring(33);
    		
    		File file = new File(path+path2+path3);
    		
    		file.delete();
		}
		Module module = game.getModule();
		int idModule = module.getId();
		
		module.getGames().remove(game);
		
		facade.flush();


		UtilActivities.eraseAndWriteNameActivity(idModule);
		return null;
	}

	public ActionForward changeOrderGame(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String type = (String) request.getParameter("type");
		int idMMJogos = Integer.parseInt(request.getParameter("idMMJogos"));

		
		String data = "";
		if (type.equals("grupo")) {
			data = facade.getJSONArrayGameGrid(idMMJogos);
		}
		else if (type.equals("individual")) {
			int idUser = Integer.parseInt(request.getParameter("idUser"));
			data = facade.getJSONArrayGameGridByUser(idMMJogos, idUser);
		}
		else if (type.equals("visualizacao")) {
			data = facade.getJSONArrayGameScoreVisualizacao(idMMJogos);
		}
		
		request.setAttribute("data", data);
		request.setAttribute("type", type);
		request.setAttribute("domain", SystemActions.webSettings.getSystemGeneralDomain());

		return mapping.findForward("fShowViewScoreGames");
		
//		StringBuffer caminho = new StringBuffer();
//		Document docXML = null;
//		caminho.append("http://" + request.getServerName() + ":"
//				+ request.getServerPort() + "/AmadeusGames"
//				+ "/score.jsp?type=" + typeId + "&idjogo=" + idMMJogos);
//
//		List<String> score = new ArrayList<String>();
//	
//		try {
//			URL url = new URL(caminho.toString());
//			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
//			urlcon.setRequestMethod("GET");
//			urlcon.setRequestProperty("Content-type", "text/xml;charset=UTF8");
//			urlcon.setDoInput(true);
//			urlcon.setDoOutput(false);
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon
//					.getInputStream()));
//
//			SAXBuilder sb = new SAXBuilder();
//
//			docXML = sb.build(new InputStreamReader(urlcon.getInputStream()));
//
//			in.close();
//			urlcon.disconnect();
//
//		} catch (MalformedURLException e) {
//			System.out.println("URL mal feita!" + e.getMessage());
//			messages.add("confirmation", new ActionMessage(
//					"activities.game.serverOut"));
//		} catch (IOException e) {
//			System.out.println("URL nï¿½o existe!" + e.getMessage());
//			messages.add("confirmation", new ActionMessage(
//					"activities.game.serverOut"));
//		} catch (JDOMException e) {
//			System.out.println("Erro na conversï¿½o para XML" + e.getMessage());
//			messages.add("confirmation", new ActionMessage(
//					"activities.game.serverOut"));
//		}
//
//		if (!messages.isEmpty()) {
//			saveErrors(request, messages);
//			return mapping.getInputForward();
//		} else {
//
//			// TODO arrumar data na exibiï¿½ï¿½o (MM JOGOS)
//			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//
//			Element m = docXML.getRootElement();
//			List elements = m.getChildren();
//			Iterator i = elements.iterator();
//			if (i.hasNext()) {
//				Element element = (Element) i.next();// Pega o tipo do
//				// score
//				// System.out.println(element.getAttributeValue("type")+"\n");
//
//				if (type.equals("top5")) {
//					elements = element.getChildren();
//					i = elements.iterator();
//					while (i.hasNext()) {
//						element = (Element) i.next();
//						score.add(element.getText());
//						score.add(element.getAttributeValue("dt_entrada"));
//						score.add(element.getAttributeValue("tempo"));
//						score.add(element.getAttributeValue("score"));
//					}
//				} else {
//					if (type.equals("user") || type.equals("date")) {
//						elements = element.getChildren();
//						i = elements.iterator();
//						while (i.hasNext()) {
//							element = (Element) i.next();
//
//							List user = element.getChildren();
//							Iterator ii = user.iterator();
//							while (ii.hasNext()) {
//								Element userElement = (Element) ii.next();
//								if (type.equals("user")) {
//									score
//											.add(element
//													.getAttributeValue("user"));
//									score.add(userElement
//											.getAttributeValue("dt_entrada"));
//									score.add(userElement
//											.getAttributeValue("tempo"));
//									score.add(userElement.getText());// Score
//								} else {
//									score
//											.add(element
//													.getAttributeValue("date"));
//									score.add(userElement.getText());// Jogador
//									score.add(userElement
//											.getAttributeValue("tempo"));
//									score.add(userElement
//											.getAttributeValue("score"));
//								}
//							}
//						}
//
//					}
//				}
//			}
//		}
//		
//		score.add("50");
//		
//		request.removeAttribute("score");
//		request.setAttribute("score", score);
//
//		request.removeAttribute("typeScore");
//		request.setAttribute("typeScore", type);
//
//		request.removeAttribute("idMMJogos");
//		request.setAttribute("idMMJogos", Integer.toString(idMMJogos));
//
		
	}

	public ActionForward showPlayerGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int idMMJogos = Integer.parseInt(request.getParameter("idMMJogos"));
		ActionMessages messages = new ActionMessages();

		StringBuffer caminho = new StringBuffer();

		caminho.append("http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/AmadeusGames"
				+ "/howPlayNow.jsp?idjogo=" + idMMJogos);

		List<String> score = new ArrayList<String>();
		Document docXML = null;

		try {
			URL url = new URL(caminho.toString());
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("GET");
			urlcon.setRequestProperty("Content-type", "text/xml;charset=UTF8");
			urlcon.setDoInput(true);
			urlcon.setDoOutput(false);

			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon
					.getInputStream()));

			SAXBuilder sb = new SAXBuilder();

			docXML = sb.build(new InputStreamReader(urlcon.getInputStream()));

			in.close();
			urlcon.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("URL mal feita!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (IOException e) {
			System.out.println("URL nï¿½o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (JDOMException e) {
			System.out.println("Erro na conversï¿½o para XML" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			Element m = docXML.getRootElement();
			List elements = m.getChildren();
			Iterator i = elements.iterator();
			while (i.hasNext()) {
				Element element = (Element) i.next();

				elements = element.getChildren();
				Iterator ii = elements.iterator();
				StringBuffer playerNames = new StringBuffer();
				while (ii.hasNext()) {
					element = (Element) ii.next();
					playerNames.append(element.getText().trim());
					if (ii.hasNext())
						playerNames.append(", ");
				}
				score.add(playerNames.toString());
			}
		}

		request.removeAttribute("score");
		request.setAttribute("score", score);

		request.removeAttribute("idMMJogos");
		request.setAttribute("idMMJogos", Integer.toString(idMMJogos));

		return mapping.findForward("fshowPlayerGame");
	}
	
	public ActionForward playGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Game game = facade.getGameById(gameId);
		
		request.setAttribute("game", game);
		
		//TODO - LOG - Jogo - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_ABRIR_JOGO);
		log.setIdObjeto(game.getId());
		this.facade.saveLog(log);
		
		return mapping.findForward(FORWARD_SHOW_PLAY_GAME);		
	}
	
	public ActionForward showGraphicGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Game game = facade.getGameById(gameId);		
		String graphic = request.getParameter("graphic");
		
		
		request.setAttribute("game", game);
		request.setAttribute("graphic", graphic);
		
		
		String data = calcGameGraphicData(graphic, gameId);
		request.setAttribute("xml", data);
		request.setAttribute("domain", SystemActions.webSettings.getSystemGeneralDomain());
		
		return mapping.findForward(FORWARD_SHOW_GRAPHIC_GAME);		
	}
	
	
	private String calcGameGraphicData(String graphic, int idGame)
	{
		String data = "";
		
		if(graphic.equals("g1"))
		{
			data =
				"<country name=\"China\">"+
				"<medal category=\"Gold\" count=\"51\"/>"+ 
				"<medal category=\"Silver\" count=\"28\"/>"+
				"<medal category=\"Bronze\" count=\"21\"/>"+
				"</country>"+
				"<country name=\"USA\">"+
				"<medal category=\"Gold\" count=\"36\"/>"+ 
				"<medal category=\"Silver\" count=\"36\"/>"+
				"<medal category=\"Bronze\" count=\"38\"/>"+
				"</country>"+
				"<country name=\"Russia\">"+ 
				"<medal category=\"Gold\" count=\"23\"/>"+ 
				"<medal category=\"Silver\" count=\"28\"/>"+
				"<medal category=\"Bronze\" count=\"21\"/>"+
				"</country>"+
				"<country name=\"Great Britian\">"+
				"<medal category=\"Gold\" count=\"19\"/>"+ 
				"<medal category=\"Silver\" count=\"15\"/>"+
				"<medal category=\"Bronze\" count=\"13\"/>"+
				"</country>"+
				"<country name=\"Germany\">"+
				"<medal category=\"Gold\" count=\"16\"/>"+ 
				"<medal category=\"Silver\" count=\"15\"/>"+
				"<medal category=\"Bronze\" count=\"10\"/>"+
				"<medal category=\"Latão\" count=\"10\"/>"+
				"</country>";
		}
		else if(graphic.equals("g2"))
		{
			data = "[{\"Profit\":2000,\"Expenses\":1500,\"Amount\":450,\"Month\":\"Jan\"},{\"Profit\":1000,\"Expenses\":200,\"Amount\":600,\"Month\":\"Feb\"},{\"Profit\":1500,\"Expenses\":500,\"Amount\":300,\"Month\":\"Mar\"},{\"Profit\":1800,\"Expenses\":1200,\"Amount\":900,\"Month\":\"Apr\"},{\"Profit\":2400,\"Expenses\":575,\"Amount\":500,\"Month\":\"May\"}]";
		}
		else if(graphic.equals("g3"))
		{
			data = facade.getJSONArrayGameScore(idGame);
		}
		else if(graphic.equals("g4"))
		{
			data = facade.getJSONArrayGameScore(idGame);	
		}
		else if(graphic.equals("g5"))
		{
			data = facade.getXmlGameScore(idGame);
		}
		else if(graphic.equals("g6"))
		{
			data = facade.getJSONArrayGameLevel(idGame);
		}
		else if(graphic.equals("g7"))
		{
			data = facade.getJSONArrayGameLevel(idGame);
		}
		else if(graphic.equals("g8"))
		{
			data = facade.getXmlGameLevel(idGame);
		}
		else if(graphic.equals("g9"))
		{
			data = facade.getJSONArrayGameMeta(idGame);
		}
		else if(graphic.equals("g10"))
		{
			data = facade.getJSONObjectTempoLevelPontuacao(idGame);
		}
		else if(graphic.equals("g11"))
		{
			data = facade.getJSONObjectTempoQuantidadePartidas(idGame);
		}
		else if(graphic.equals("g12"))
		{
			data = facade.getJSONObjectLevelPontuacao(idGame);
		}
		return data;
	}
	
	
	public void viewImage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Game game = facade.getGameById(gameId);		
		
		response.setContentType("image/jpeg");
		response.getOutputStream().write(game.getImage());
		response.getOutputStream().flush();
		response.getOutputStream().close();	
	}
		
}
