<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>

<%@ page import="java.util.List"%>

<%
String idMMJogos = (String) request.getAttribute("idMMJogos");
@SuppressWarnings("unchecked")
List<String> score = (List<String>) request.getAttribute("score");
%>
<table>
	<tr>
		<td>
		<dl class="orderScoreGame">
			<%if (score.size()>0){
					String aux = score.get(0);
					for (int i = 0; i < (score.size()); i ++) { %>
						<dt><bean:message key="activities.game.match" />: <%=score.get(i)%></dt>
						<%}
			}else{%><dt> <bean:message key="activities.game.noScore"/> </dt><%}%>
		</dl>
		</td>
	</tr>
</table>