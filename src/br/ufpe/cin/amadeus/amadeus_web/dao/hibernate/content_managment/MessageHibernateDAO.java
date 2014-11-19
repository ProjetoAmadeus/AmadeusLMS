/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;

public class MessageHibernateDAO extends GenericHibernateDAO<Message, Integer>
		implements MessageDAO {

	// lista de Message por pagina
	@SuppressWarnings("unchecked")
	public List<Message> searchMessageByPaging(int tamanhoBloco, int qtdBloco, Forum forum) {
		Criteria crit = getSession().createCriteria(Message.class);
		crit.add(Restrictions.eq("forum.id", forum.getId()));
		crit.addOrder(Order.desc("date"));
		
		List<Message> messagesTemp = (List<Message>) crit.list();
		List<Message> messages = new ArrayList<Message>();
		for (int i = qtdBloco; i < qtdBloco+5; i++) {
			if(i > messagesTemp.size()-1)
				break;
			else
				messages.add(messagesTemp.get(i));
		}
		return messages;
	}
	
	// retorna a quantidade de mensagens do fórum
	public int getSizeSearchMessageByForum(Forum forum) {
		int size = forum.getMessages().size();
		return size;
	}

	@Override
	public Message getLastMessage() {
		StringBuilder hql = new StringBuilder();
		hql.append("Select m from Message m where m.id = (Select max(id) from Message)");
		
		Message result = (Message) getSession().createQuery(hql.toString()).uniqueResult();
		return result;
	}

}
