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
String typeScore = (String) request.getAttribute("typeScore");
String idMMJogos = (String) request.getAttribute("idMMJogos");
@SuppressWarnings("unchecked")
List<String> score = (List<String>) request.getAttribute("score");
%>
<table>
	<tr>
		<td valign="top"><bean:message key="activities.game.score.order"/><br />
		<ul class="listGames">
			<li><a onclick="showScoreGame('top5',<%=idMMJogos %>);"
				href="javascript:void(0)"><bean:message key="activities.game.score.top5"/></a></li>
			<li><a onclick="showScoreGame('user',<%=idMMJogos %>);"
				href="javascript:void(0)"><bean:message key="activities.game.score.user"/></a></li>
			<li><a onclick="showScoreGame('date',<%=idMMJogos %>);"
				href="javascript:void(0)"><bean:message key="activities.game.score.date"/></a></li>
		</ul>
		</td>

		<td>
		<dl class="orderScoreGame">
			<%if (score.size()>0){
				if (!typeScore.equals("date")){ 
					String aux = score.get(0);
					for (int i = 0; i < (score.size() - 1); i += 4) {
						if (typeScore.equals("top5")){	%>
						<dt><bean:message key="activities.game.player" />: <%=score.get(i)%></dt>
						<dd><bean:message key="activities.game.date" />: <%=score.get(i + 1).substring(0, 10)%></dd>
						<dd><bean:message key="activities.game.time" />: <%=score.get(i + 2)%></dd>
						<dd><bean:message key="activities.game.score" />: <%=score.get(i + 3)%></dd>
						<%}else{
							if (typeScore.equals("user")){	
								if ((i==0)|| !aux.equals(score.get(i))){%>
								<dt><bean:message key="activities.game.player" />: <%=score.get(i)%></dt>
								<%} aux = score.get(i); %>
								<dd><bean:message key="activities.game.date" />: <%=score.get(i + 1).substring(0, 10)%></dd>
								<dd><bean:message key="activities.game.time" />: <%=score.get(i + 2)%></dd>
								<dd><bean:message key="activities.game.score" />: <%=score.get(i + 3)%></dd>
								<%}
								}
					}
				}else{
					String aux = score.get(0);
					for (int i = 0; i < (score.size() - 1); i += 4) {
						if ((i==0)|| !aux.equals(score.get(i))){%>
							<dt class="showDateOrderGame"><bean:message
								key="activities.game.date" />: <%=score.get(i).substring(0, 10)%></dt>
							<%aux = score.get(i);
						}%>
					<dt><bean:message key="activities.game.player" />: <%=score.get(i+1)%></dt>
					<dd>
					<ul>
						<li><bean:message key="activities.game.time" />: <%=score.get(i + 2)%></li>
						<li><bean:message key="activities.game.score" />: <%=score.get(i + 3)%></li>
					</ul>
					</dd>
				<%} 
				}
			}else{%><dt> <bean:message key="activities.game.noScore"/> </dt><%}%>
		</dl>
		</td>
	</tr>
</table>