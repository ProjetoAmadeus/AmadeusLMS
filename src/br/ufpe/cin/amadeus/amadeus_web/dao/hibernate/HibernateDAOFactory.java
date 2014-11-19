/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate;

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
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.OpenIDHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.PersonHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.ResumeHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register.UserRequestHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.AccessInfoDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.OpenIDDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.PersonDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.ResumeDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.UserRequestDAO;

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
	
	@Override
	public GroupsDAO getGroupsDAO() {
		return (GroupsDAO) instantiateDAO(GroupsHibernateDAO.class);
	}
}



