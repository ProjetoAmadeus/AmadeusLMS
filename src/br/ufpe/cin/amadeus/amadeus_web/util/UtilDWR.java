/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.util;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.proxy.dwr.Util;

@RemoteProxy
@DataTransferObject
public class UtilDWR {
	
	@RemoteMethod
    public String getInclude(String url) throws ServletException, IOException {
        WebContext wctx = WebContextFactory.get();
        return wctx.forwardToString(url);
    }

	@SuppressWarnings("unchecked")
	public static Util getUtil(){ 
	    WebContext wctx = WebContextFactory.get();
	    
	    if(wctx != null) {
	    	String page = wctx.getCurrentPage(); 
	    	Collection sessions = wctx.getScriptSessionsByPage(page); 
	    	Util u = new Util(sessions); 
	    	return u;	    	
	    }
	    else
	    {
	    	Util u = new Util();
	    	return u;
	    }
	    
	 
	  } 
}