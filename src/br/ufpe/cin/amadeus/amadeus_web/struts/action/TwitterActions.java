package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MultiMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;
import br.ufpe.cin.amadeus.amadeus_web.dao.JdbcDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.util.SocialInteractions;
import br.ufpe.cin.amadeus.amadeus_web.util.TweetInteractionType;

/**
 * Classe responsável por centralizar as ações referentes ao monitoramento da
 * ferramenta do twitter
 * 
 * @author Nailson Cunha
 * 
 */
public class TwitterActions extends SystemActions {

	private final String FORWARD_SHOW_VIEW_SOCIAL_NETWORK_MONITORING = "fshowViewSocialNetworkMonitoring";
	private final String FORWARD_SHOW_VIEW_SOCIAL_TEST_TOOLS = "fshowViewTestTools";

	private final String CONSUMER_KEY = "lZJGjiuAdYjiRvuNqBp5kg";
	private final String CONSUMER_SECRET = "ygeVV2WVXjYMyL8HkH065WmVC985RtSufYUpSA";
	private final String ACESS_TOKEN = "248676552-gaRX2FU7IGcqXxLoGZfFROQ9jTymfYWySoWtHhBG";
	private final String ACESS_TOKEN_SECRET = "7MxIfOc2lfn9NP2TaIQaIDl9A6kEqtc3J5myLgJfRY";
	private final String ACESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
	private final String ACESS_TOKEN_AUTHORIZATION = "https://api.twitter.com/oauth/authorize";

	private static TwitterStream twitterStream;

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("social.showViewSocialNetworkMonitoring","showViewSocialNetworkMonitoring");
		map.put("social.startSocialNetworkMonitoring","startSocialNetworkMonitoring");
		map.put("social.stopSocialNetworkMonitoring","stopSocialNetworkMonitoring");
		map.put("social.testMessageTool","testMessageTool");
		map.put("social.testTwitterTool","testTwitterTool");
		map.put("social.showViewTestTools","showViewTestTools");

		return map;
	}

	/**
	 * Action que retorna a visualização da página de Monitoramento de
	 * Interações Sociais do Twitter.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showViewSocialNetworkMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		if (SystemActions.isLoggedUser(request)) {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute(
					"user");
			user = facade.searchUserById(user.getId());

			int courseId;

			if (request.getParameter("courseId") == null) {
				courseId = (Integer) request.getAttribute("courseId");
			} else {
				courseId = Integer.parseInt(request.getParameter("courseId"));
			}

			Course course = facade.getCoursesById(courseId);
			List<Person> teachers = facade.getTeachersByCourse(course);
			List<Person> assistants = facade.listAssistantsByCourse(course);
			List<Person> participants = facade.listStudentsByCourse(course);
			List<MessengerMessage> messagesUnread = facade
					.getAllUnreadByPerson(facade.getPersonByLogin(user
							.getLogin()));

			int studentsNumber = facade.getNumberOfStudentsInCourse(course);
			course.setNumberOfStudentsInCourse(studentsNumber);

			Set<Keyword> keywords = course.getKeywords();

			boolean canRegisterUser = false;

			if (facade.canRegisterUser(user, course)) {
				canRegisterUser = true;
			}

			Role userRoleInCourse = Facade.getInstance()
					.getRoleByPersonInCourse(user.getPerson(), course);

			SystemActions.setMenuPermissionsForUserInRequest(request, course);
			request.setAttribute("userRoleInCourse",
					(userRoleInCourse != null) ? userRoleInCourse.getRoleType()
							: null);
			request.setAttribute("canRegisterUser", canRegisterUser);
			request.setAttribute("course", course);
			request.setAttribute("teachers", teachers);
			request.setAttribute("assistants", assistants);
			request.setAttribute("participants", participants);
			request.setAttribute("messagesUnread", messagesUnread);
			request.setAttribute("keywords", keywords);

			forward = mapping
					.findForward(FORWARD_SHOW_VIEW_SOCIAL_NETWORK_MONITORING);
		}

		return forward;
	}

	/**
	 * Método que inicia o monitoramento do twitter
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward startSocialNetworkMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!SystemActions.isLoggedUser(request))
			return null;

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		cb.setOAuthAccessToken(ACESS_TOKEN);
		cb.setOAuthAccessTokenSecret(ACESS_TOKEN_SECRET);
		cb.setOAuthAccessTokenURL(ACESS_TOKEN_URL);
		cb.setOAuthAuthorizationURL(ACESS_TOKEN_AUTHORIZATION);

		twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {
			@Override
			public void onException(Exception arg0) {
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
			}

			@Override
			public void onStatus(Status status) {
				System.out.println(status.getUser().getScreenName() + " - " + status.getText());
				if (new JdbcDAO().userTwitterExists(status.getUser()
						.getScreenName())) {
					if (status.isRetweet()) {
						saveAsRetweet(status);
					} else if (status.getInReplyToScreenName() != null) {
						saveAsReply(status);
					} else if (status.getUserMentionEntities() != null) {
						saveAsMention(status);
					}
				}
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}
		};

		String[] hashtag = new String[1];
		hashtag[0] = "#" + request.getParameter("hashtag");

		FilterQuery fq = new FilterQuery();
		fq.track(hashtag);
		twitterStream.addListener(listener);
		twitterStream.filter(fq);

		return null;
	}

	/**
	 * Método que para as threads que estão monitorando alguma hashtag no twitter.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stopSocialNetworkMonitoring(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TwitterStream ts = getTwitterStream();

		if (ts != null)
			ts.shutdown();
		
		List<Tweet> tweets = facade.getAllTweets();
		for(Tweet t : tweets){
			System.out.println(t);
		}

		return null;
	}

	private TwitterStream getTwitterStream() {
		if (twitterStream != null)
			return twitterStream;
		return null;
	}

	/**
	 * Método que salva os tweets como retweet
	 * @param status
	 */
	private void saveAsRetweet(Status status) {
		JdbcDAO jd = new JdbcDAO();

		// Se o dono do tweet não estiver cadastrado
		if (!jd.userTwitterExists(status.getUser().getScreenName()))
			return;

		// Se o usuario retweetado não estiver cadastrado
		int idUserRetweeted = jd.getUserIdByScreenName(status
				.getRetweetedStatus().getUser().getScreenName());
		if (idUserRetweeted == 0)
			return;

		Date dateOfTweet = status.getCreatedAt();
		TweetInteractionType tit = TweetInteractionType.RETWEET;
		String tweetText = status.getText();
		int userSenderId = jd.getUserIdByScreenName(status.getUser()
				.getScreenName());
		int userTargetId = jd.getUserIdByScreenName(status.getRetweetedStatus()
				.getUser().getScreenName());

		jd.saveTweet(dateOfTweet, tit, tweetText, userSenderId, userTargetId);
	}

	/**
	 * Método que salva os tweets como reply
	 * @param status
	 */
	private void saveAsReply(Status status) {
		JdbcDAO jd = new JdbcDAO();

		// Se o usuario principal nao existe
		if (!jd.userTwitterExists(status.getUser().getScreenName()))
			return;

		// Se o usuario replied nao existir
		if (!jd.userTwitterExists(status.getInReplyToScreenName()))
			return;

		Date dateOfTweet = status.getCreatedAt();
		TweetInteractionType tit = TweetInteractionType.REPLY;
		String tweetText = status.getText();
		int userSenderId = jd.getUserIdByScreenName(status.getUser()
				.getScreenName());
		int userTargetId = jd.getUserIdByScreenName(status
				.getInReplyToScreenName());

		jd.saveTweet(dateOfTweet, tit, tweetText, userSenderId, userTargetId);

		UserMentionEntity[] umes = status.getUserMentionEntities();
		if (umes != null && umes.length > 1) {
			for(int i = 1; i <= status.getUserMentionEntities().length; i++){
				if(jd.userTwitterExists(umes[i].getScreenName())){
					userTargetId = jd.getUserIdByScreenName(umes[i].getScreenName());
					tit = TweetInteractionType.MENTION;
					jd.saveTweet(dateOfTweet, tit, tweetText, userSenderId, userTargetId);
				}
			}
		}

	}

	/**
	 * Método que salva os tweets como mention
	 * @param status
	 */
	private void saveAsMention(Status status) {
		JdbcDAO jd = new JdbcDAO();

		// Se o usuario principal nao existe
		if (!jd.userTwitterExists(status.getUser().getScreenName()))
			return;

		int userTargetId;

		Date dateOfTweet = status.getCreatedAt();
		TweetInteractionType tit = TweetInteractionType.MENTION;
		String tweetText = status.getText();
		int userSenderId = jd.getUserIdByScreenName(status.getUser()
				.getScreenName());

		UserMentionEntity[] umes = status.getUserMentionEntities();
		if (umes != null) {
			for(UserMentionEntity ume : umes){
				if(jd.userTwitterExists(ume.getScreenName())){
					userTargetId = jd.getUserIdByScreenName(ume.getScreenName());
					jd.saveTweet(dateOfTweet, tit, tweetText, userSenderId, userTargetId);
				}
			}
		}
	}
	
	
	//Métodos de testes das ferramentas de Interações Sociais
	/**
	 * Redireciona para a página de teste das ferramentas.
	 */
	public ActionForward showViewTestTools(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			
		if (!SystemActions.isLoggedUser(request))
			return null;
		
		ActionForward forward = mapping
		.findForward(FORWARD_SHOW_VIEW_SOCIAL_TEST_TOOLS);
		
		return forward;
		
	}
	
	/**
	 * Método criado pra teste do método getSocialInteractions da ferramenta de mensagens
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward testMessageTool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		System.out.println("Testando MessageTool");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = "2012-12-10";
		String fim = "2013-02-05";
		
		Date dataIni = sdf.parse(inicio);
		Date dataFim = sdf.parse(fim);
		Course curso = facade.getCoursesById(18);
		MultiMap map = SocialInteractions.getSocialInteractionsFromMessenger(curso, dataIni, dataFim);
		
		for(Object o : map.values())
			System.out.println(o);
		
		return null;
		
	}
	
	
	/**
	 * Método criado pra teste do método getSocialInteractions da ferramenta do twitter
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward testTwitterTool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			
		System.out.println("Testando TwitterTool");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = "2013-03-03";
		String fim = "2013-03-05";
		Date dataIni = sdf.parse(inicio);
		Date dataFim = sdf.parse(fim);
		MultiMap map = SocialInteractions.getSocialInteractionsFromTwitterTool(dataIni,dataFim);
		for(Object o : map.values())
			System.out.println(o);
		return null;
		
	}

}
