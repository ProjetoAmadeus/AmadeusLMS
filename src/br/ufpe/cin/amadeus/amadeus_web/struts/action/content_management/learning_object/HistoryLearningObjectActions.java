/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.learning_object;

import java.sql.Time;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.HistoryLearningObject;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class HistoryLearningObjectActions extends DispatchAction{
	
	
	public ActionForward endSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Facade facade = Facade.getInstance();
		
		int idHistory = Integer.parseInt( (String) request.getParameter("idHistory") );
		String score = (String) request.getParameter("score");
		
		HistoryLearningObject historyBD = facade.getHistoryLearningObjectById(idHistory);
		historyBD.setDateEndAccess(new Date());
		historyBD.setScore(score);
		Time timeSql = new Time(historyBD.getDateEndAccess().getTime() - historyBD.getDateAccess().getTime());
		historyBD.setTimeAccess(timeSql);
		
		return null;
	}
	
}
