/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco

Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS

O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.

Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.

Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 **/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import br.ufpe.cin.amadeus.amadeus_mobile.sms.Receiver;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Image;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Thumbnail;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SettingsActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;
import br.ufpe.cin.amadeus.amadeus_web.util.AmadeusSessionListener;



public class GameActions extends org.apache.struts.actions.DispatchAction {

	private Facade facade = null;

	private final String FORWARD_SHOW_VIEW_NEW_GAME_ACTIVITY = "fShowViewNewGameActivity";
	private final String FORWARD_SHOW_VIEW_GAME = "fShowGame";

	public ActionForward showGame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		facade = Facade.getInstance(); 

		int gameID = Integer.valueOf(request.getParameter("idGame"));

		Game game = facade.getGameById(gameID);

		request.getSession().setAttribute("game", game);

		return mapping.findForward(FORWARD_SHOW_VIEW_GAME);
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
		game.setUrl(myForm.getString("urlGame"));
		game.setDescription(myForm.getString("descriptionGame"));
		game.setCreationDate(new Date());
		
		//number validation
		try{
			Integer maxUsers = Integer.parseInt(myForm.getString("maxUsers"));
			Integer minUsers = Integer.parseInt(myForm.getString("minUsers"));
			game.setMaxUsers(maxUsers);
			game.setMinUsers(minUsers);
		} catch (Exception e){
			messages.add("editError",new ActionMessage(e.getMessage()));
		}
		
		FormFile myFile = (FormFile) myForm.get("image");
		
		//photo validation
		try {
			if(!myFile.getFileName().equals("")){
				if (myFile.getFileSize() < 500000){ //if the photo has less than 500KB
			    	Image image = new Image();
			    	Thumbnail thumb = new Thumbnail();
			    	byte[] photo = thumb.resize(myFile.getFileData());
			    	game.setImage(photo);
				}else messages.add("requestError", new ActionMessage("errors.photoSize"));
			}
		} catch (Exception e) {
			messages.add("editError", new ActionMessage(e.getMessage()));
		}

		
		
		
		int moduleId = Integer.parseInt(request.getParameter("moduleId"));

		Module module = facade.getModuleById(moduleId);

		StringBuffer caminho = new StringBuffer();

		try {
			caminho.append("http://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ "/AmadeusGames"
					+ "/newgame.do?"
					+ URLEncoder.encode("nmJogo", "ISO-8859-1")
					+ "="
					+ URLEncoder.encode(game.getName(), "ISO-8859-1")
					+ "&"
					+ URLEncoder.encode("caminho", "ISO-8859-1")
					+ "="
					+ URLEncoder.encode(game.getUrl(), "ISO-8859-1")
					+ "&"
					+ URLEncoder.encode("comentario", "ISO-8859-1")
					+ "="
					+ URLEncoder.encode(game.getDescription(), "ISO-8859-1")
					+ "&"
					+ URLEncoder.encode("curso", "ISO-8859-1")
					+ "="
					+ URLEncoder.encode(Integer.toString(module.getCourse()
							.getId()), "ISO-8859-1"));

			URL url = new URL(caminho.toString());
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("POST");
			urlcon.setRequestProperty("Content-type", "text/xml;charset=UTF8");
			urlcon.setDoInput(true);
			urlcon.setDoOutput(false);

			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon
					.getInputStream()));

			SAXBuilder sb = new SAXBuilder();

			Document docXML = sb.build(new InputStreamReader(urlcon
					.getInputStream()));

			in.close();
			urlcon.disconnect();

			Element root = docXML.getRootElement();
			Attribute result = root.getAttribute("result");
			if (result.getValue().equals("true")) {
				List<?> elements = root.getChildren();
				Iterator<?> i = elements.iterator();
				Element element = (Element) i.next();
				// game.setIdMMJogos(Integer.parseInt(element.getText()));

			} else {
				messages.add("confirmation", new ActionMessage(
						"activities.game.serverOut"));
			}

		} catch (MalformedURLException e) {
			System.out.println("URL mal feita!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (IOException e) {
			System.out.println("URL n�o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (JDOMException e) {
			System.out.println("Erro na convers�o para XML" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			game.setModule(module);
			module.getGames().add(game);

			facade.flush();

			if(SettingsActions.mobileSettings.getSmsGame().equals("on")){
				Receiver receiver = new Receiver();
				receiver.addMaterial(module.getCourse().getId(), module.getId(),
					game.getId());
			}

			UtilActivities.eraseAndWriteNameActivity(module.getId());
			return null;
		}
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
			System.out.println("URL n�o existe!" + e.getMessage());
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
		ActionMessages messages = new ActionMessages();
		int idModule = Integer.parseInt(request.getParameter("idModule")) - 1;
		int idGame = Integer.parseInt(request.getParameter("idGame"));

		Course courseSession = (Course) request.getSession().getAttribute(
				"course");
		int idCourse = courseSession.getId();
		int idModuleBD = courseSession.getModules().get(idModule).getId();

		Module module = facade.getModuleById(idModuleBD);

		Game game = facade.getGameById(idGame);
		module.getGames().remove(game);
		facade.updateModule(module);
		Course courseResult = facade.getCoursesById(idCourse);

		StringBuffer caminho = new StringBuffer();

		try {
			// caminho.append(
			// "http://" + request.getServerName() + ":" +
			// request.getServerPort() + "/AmadeusGames"
			// + "/removegame.do?"
			// + URLEncoder.encode("id", "ISO-8859-1")
			// + "="
			// + URLEncoder.encode(Integer.toString(game.getIdMMJogos()),
			// "ISO-8859-1")
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
			urlcon.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("URL mal feita!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (IOException e) {
			System.out.println("URL n�o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		}

		request.getSession().removeAttribute("course");
		request.getSession().setAttribute("course", courseResult);

		/*Receiver receiver = new Receiver();
		receiver
				.addMaterial(courseResult.getId(), module.getId(), game.getId());*/

		UtilActivities.eraseAndWriteNameActivity(idModule);
		return null;
	}

	public ActionForward changeOrderGame(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String type = (String) request.getParameter("type");
		int idMMJogos = Integer.parseInt(request.getParameter("idMMJogos"));
		ActionMessages messages = new ActionMessages();

		int typeId = 0;
		if (type.equals("top5")) {
			typeId = 1;
		}
		if (type.equals("date")) {
			typeId = 2;
		}
		if (type.equals("user")) {
			typeId = 3;
		}

		StringBuffer caminho = new StringBuffer();
		Document docXML = null;
		caminho.append("http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/AmadeusGames"
				+ "/score.jsp?type=" + typeId + "&idjogo=" + idMMJogos);

		List<String> score = new ArrayList<String>();

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
			System.out.println("URL n�o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (JDOMException e) {
			System.out.println("Erro na convers�o para XML" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		} else {

			// TODO arrumar data na exibi��o (MM JOGOS)
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

			Element m = docXML.getRootElement();
			List elements = m.getChildren();
			Iterator i = elements.iterator();
			if (i.hasNext()) {
				Element element = (Element) i.next();// Pega o tipo do
				// score
				// System.out.println(element.getAttributeValue("type")+"\n");

				if (type.equals("top5")) {
					elements = element.getChildren();
					i = elements.iterator();
					while (i.hasNext()) {
						element = (Element) i.next();
						score.add(element.getText());
						score.add(element.getAttributeValue("dt_entrada"));
						score.add(element.getAttributeValue("tempo"));
						score.add(element.getAttributeValue("score"));
					}
				} else {
					if (type.equals("user") || type.equals("date")) {
						elements = element.getChildren();
						i = elements.iterator();
						while (i.hasNext()) {
							element = (Element) i.next();

							List user = element.getChildren();
							Iterator ii = user.iterator();
							while (ii.hasNext()) {
								Element userElement = (Element) ii.next();
								if (type.equals("user")) {
									score
											.add(element
													.getAttributeValue("user"));
									score.add(userElement
											.getAttributeValue("dt_entrada"));
									score.add(userElement
											.getAttributeValue("tempo"));
									score.add(userElement.getText());// Score
								} else {
									score
											.add(element
													.getAttributeValue("date"));
									score.add(userElement.getText());// Jogador
									score.add(userElement
											.getAttributeValue("tempo"));
									score.add(userElement
											.getAttributeValue("score"));
								}
							}
						}

					}
				}
			}
		}

		request.removeAttribute("score");
		request.setAttribute("score", score);

		request.removeAttribute("typeScore");
		request.setAttribute("typeScore", type);

		request.removeAttribute("idMMJogos");
		request.setAttribute("idMMJogos", Integer.toString(idMMJogos));

		return mapping.findForward("fShowViewScoreGames");
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
			System.out.println("URL n�o existe!" + e.getMessage());
			messages.add("confirmation", new ActionMessage(
					"activities.game.serverOut"));
		} catch (JDOMException e) {
			System.out.println("Erro na convers�o para XML" + e.getMessage());
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

}
