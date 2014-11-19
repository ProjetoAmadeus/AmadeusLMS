/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo ï¿½ parte do programa Amadeus Sistema de Gestï¿½o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS ï¿½ um software livre; vocï¿½ pode redistribui-lo e/ou modifica-lo dentro dos termos da Licenï¿½a Pï¿½blica Geral GNU como
publicada pela Fundaï¿½ï¿½o do Software Livre (FSF); na versï¿½o 2 da Licenï¿½a.
 
Este programa ï¿½ distribuï¿½do na esperanï¿½a que possa ser ï¿½til, mas SEM NENHUMA GARANTIA; sem uma garantia implï¿½cita de ADEQUAï¿½ï¿½O a qualquer MERCADO ou APLICAï¿½ï¿½O EM PARTICULAR. Veja a Licenï¿½a Pï¿½blica Geral GNU para maiores detalhes.
 
Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU, sob o tï¿½tulo "LICENCA.txt", junto com este programa, se nï¿½o, escreva para a Fundaï¿½ï¿½o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.ufpe.cin.amadeus.amadeus_web.dao.DAOFactory;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.AmadeusDroidHistoricDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ArchiveDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.CourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.DeliveryDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ForumDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GameDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GroupsDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HistoryLearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HomeworkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.KeywordDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.LearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.LogDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ModuleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PersonRoleCourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PollDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.RoleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.VideoIrizDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationRealizedDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.externallink.ExternalLinkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.AmadeusDroidHistoricHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.ArchiveHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.CourseHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.DeliveryHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.ForumHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.GameHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.GroupsHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.HistoryLearningObjectHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.HomeworkHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.KeywordHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.LearningObjectHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.LogHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.MaterialHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.MaterialRequestHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.MessageHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.ModuleHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.PersonRoleCourseHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.PollHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.RoleHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.VideoIrizHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.evaluation.EvaluationHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.evaluation.EvaluationRealizedHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.externallink.ExternalLinkHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.AccessInfoHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.MessengerMessageHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.OpenIDHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.PersonHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.ResumeHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.TweetHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.UserRequestHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.AccessInfoDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.MessengerMessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.OpenIDDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.PersonDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.ResumeDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.TweetDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.UserRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;

public class HibernateDAOFactory extends DAOFactory {

    public PersonDAO getPersonDAO() {
        return (PersonDAO)instantiateDAO(PersonHibernateDAO.class);
    }
    
    public UserRequestDAO getUserRequestDAO(){
    	return (UserRequestDAO) instantiateDAO(UserRequestHibernateDAO.class); 
    }
    
    public AccessInfoDAO getAccessInfoDAO(){
    	return (AccessInfoDAO) instantiateDAO(AccessInfoHibernateDAO.class);
    }
    
    public DeliveryDAO getDeliveryDAO(){
    	return (DeliveryDAO) instantiateDAO(DeliveryHibernateDAO.class);
    }
    
	public KeywordDAO getKeywordDAO() {
		return (KeywordDAO)instantiateDAO(KeywordHibernateDAO.class);
	}
	
	public CourseDAO getCourseDAO(){
		return (CourseDAO) instantiateDAO(CourseHibernateDAO.class);
	}
	
	public ModuleDAO getModuleDAO(){
		return (ModuleDAO) instantiateDAO(ModuleHibernateDAO.class);
	}
	
	public HomeworkDAO getHomeworkDAO(){
		return (HomeworkDAO) instantiateDAO(HomeworkHibernateDAO.class);
	}
	
	public RoleDAO getRoleDAO() {
		return (RoleDAO) instantiateDAO(RoleHibernateDAO.class);
	}
	
	public PersonRoleCourseDAO getPersonRoleCourseDAO() {
		return (PersonRoleCourseDAO) instantiateDAO(PersonRoleCourseHibernateDAO.class);
	}

	@SuppressWarnings("unchecked")
    private GenericHibernateDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass + ", " + ex.getMessage(), ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public PollDAO getPollDAO() {
		return (PollDAO) instantiateDAO(PollHibernateDAO.class);
	}

    public MaterialDAO getMaterialDAO() {
		return (MaterialDAO) instantiateDAO(MaterialHibernateDAO.class);
	}
    
    public MaterialRequestDAO getMaterialRequestDAO() {
		return (MaterialRequestDAO) instantiateDAO(MaterialRequestHibernateDAO.class);
	}
	
	public ResumeDAO getResumeDAO(){
    	return (ResumeDAO) instantiateDAO(ResumeHibernateDAO.class);
    }

	public GameDAO getGameDAO(){
    	return (GameDAO) instantiateDAO(GameHibernateDAO.class);
    }
	
	public ForumDAO getForumDAO(){
    	return (ForumDAO) instantiateDAO(ForumHibernateDAO.class);
    }
	
	public MessageDAO getMessageDAO(){
    	return (MessageDAO) instantiateDAO(MessageHibernateDAO.class);
    }

	public ArchiveDAO getArchiveDAO() {
		return (ArchiveDAO) instantiateDAO(ArchiveHibernateDAO.class);
	}
	
	public VideoIrizDAO getVideoIrizDAO() {
		return (VideoIrizDAO) instantiateDAO(VideoIrizHibernateDAO.class);
	}
	
	public LearningObjectDAO getLearningObjectDAO(){
		return (LearningObjectDAO) instantiateDAO(LearningObjectHibernateDAO.class);
	}

	public HistoryLearningObjectDAO getHistoryLearningObjectDAO() {
		return (HistoryLearningObjectDAO) instantiateDAO(HistoryLearningObjectHibernateDAO.class);
	}
	
	public EvaluationDAO getEvaluationDAO(){
    	return (EvaluationDAO) instantiateDAO(EvaluationHibernateDAO.class);
    }
	
	public EvaluationRealizedDAO getEvaluationRealizedDAO(){
    	return (EvaluationRealizedDAO) instantiateDAO(EvaluationRealizedHibernateDAO.class);
    }

	public OpenIDDAO getOpenIDDAO() {
		return (OpenIDDAO) instantiateDAO(OpenIDHibernateDAO.class);
	}
	
	public ExternalLinkDAO getExternalLinkDAO(){
		return (ExternalLinkDAO) instantiateDAO(ExternalLinkHibernateDAO.class);
	}

	
	public AmadeusDroidHistoricDAO getAmadeusDroidHistoricDAO() {
		return (AmadeusDroidHistoricDAO) instantiateDAO(AmadeusDroidHistoricHibernateDAO.class);
	}
	
	@Override
	public LogDAO getLogDAO() {
		return (LogDAO) instantiateDAO(LogHibernateDAO.class);
	}
<<<<<<< HEAD

	/**
	 * Método que retorna o MessengerMessageDAO
	 * @author Nailson Cunha
	 */
	@Override
	public MessengerMessageDAO getMessengerMessageDAO() {
		return (MessengerMessageDAO)instantiateDAO(MessengerMessageHibernateDAO.class);
	}

	@Override
	public TweetDAO getTweetDAO() {
		return (TweetDAO) instantiateDAO(TweetHibernateDAO.class);
	}
	
=======
	
	@Override
	public GroupsDAO getGroupsDAO() {
		return (GroupsDAO) instantiateDAO(GroupsHibernateDAO.class);
	}
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
}



