/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
	    String page = wctx.getCurrentPage(); 
	    Collection sessions = wctx.getScriptSessionsByPage(page); 
	    Util u = new Util(sessions); 
	    return u; 
	 
	  } 
}