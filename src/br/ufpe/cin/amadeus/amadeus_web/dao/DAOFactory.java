/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ArchiveDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.CourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.DeliveryDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ForumDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GameDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HistoryLearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HomeworkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.KeywordDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.LearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ModuleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PersonRoleCourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PollDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.RoleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.VideoIrizDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.AccessInfoDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.OpenIDDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.PersonDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.ResumeDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.UserRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationRealizedDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.externallink.ExternalLinkDAO;

public abstract class DAOFactory {

    /**
     * Creates a standalone DAOFactory that returns unmanaged DAO
     * beans for use in any environment Hibernate has been configured
     * for. Uses HibernateUtil/SessionFactory and Hibernate context
     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
     * and transaction scoped.
     */
	@SuppressWarnings("unchecked")
    public static final Class HIBERNATE = br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.HibernateDAOFactory.class;

    /**
     * Factory method for instantiation of concrete factories.
     */
    @SuppressWarnings("unchecked")
    public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }

    public abstract PersonDAO getPersonDAO();
    public abstract AccessInfoDAO getAccessInfoDAO();
    public abstract OpenIDDAO getOpenIDDAO();
    public abstract KeywordDAO getKeywordDAO();
    public abstract CourseDAO getCourseDAO();
    public abstract UserRequestDAO getUserRequestDAO();
    public abstract HomeworkDAO getHomeworkDAO();
	public abstract RoleDAO getRoleDAO();
	public abstract DeliveryDAO getDeliveryDAO();
	public abstract PersonRoleCourseDAO getPersonRoleCourseDAO();
	public abstract ModuleDAO getModuleDAO();
	public abstract PollDAO getPollDAO();
	public abstract MaterialDAO getMaterialDAO();
	public abstract MaterialRequestDAO getMaterialRequestDAO();
	public abstract ResumeDAO getResumeDAO();
	public abstract GameDAO getGameDAO();
	public abstract ForumDAO getForumDAO();
	public abstract MessageDAO getMessageDAO();
	public abstract ArchiveDAO getArchiveDAO();
	public abstract VideoIrizDAO getVideoIrizDAO();
	public abstract LearningObjectDAO getLearningObjectDAO();
	public abstract HistoryLearningObjectDAO getHistoryLearningObjectDAO();
	public abstract EvaluationDAO getEvaluationDAO();
	public abstract EvaluationRealizedDAO getEvaluationRealizedDAO();
	public abstract ExternalLinkDAO getExternalLinkDAO();
}

