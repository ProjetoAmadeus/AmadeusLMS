/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.util;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;  
  
public final class RequestWrapper extends HttpServletRequestWrapper {  
      
    public RequestWrapper(HttpServletRequest servletRequest) {  
        super(servletRequest);  
    }  
      
    public String[] getParameterValues(String parameter) {  
  
      String[] values = super.getParameterValues(parameter);  
      if (values==null)  {  
                  return null;  
          }  
      int count = values.length;  
      String[] encodedValues = new String[count];  
      for (int i = 0; i < count; i++) {  
                 encodedValues[i] = cleanXSS(values[i]);  
       }    
      return encodedValues;   
    }  
      
    public String getParameter(String parameter) {  
          String value = super.getParameter(parameter);  
          if (value == null) {  
                 return null;   
                  }  
          return cleanXSS(value);  
    }  
      
    public String getHeader(String name) {  
        String value = super.getHeader(name);  
        if (value == null)  
            return null;  
        return cleanXSS(value);  
          
    }  
  
    private String cleanXSS(String value) {

		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");		  
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		//value = value.replaceAll("script", "");
		return value;
	}
    
    public static String cleanXSSReverse(String value) {
    	
    	value = value.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    	value = value.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
    	value = value.replaceAll("&#39;","'");
		
    	return value;
    }
}  
