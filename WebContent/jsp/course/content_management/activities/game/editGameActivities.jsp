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

<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade,
				 java.util.HashMap,br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game" %>
<%int idGame=-1; %>
<%

idGame = Integer.parseInt(request.getParameter("idGame"));

Facade facade = Facade.getInstance();
Game game = facade.getGameById(idGame);

HashMap<String, Object> data = new HashMap<String, Object>();

data.put("nameGame",game.getName());
data.put("urlGame", game.getUrl());
data.put("descriptionGame", game.getDescription());

request.setAttribute("game", data);
%>

<html:form action="newGame" >
<td colspan="3" class="cont-tableinterna">
<table class="tableinterna">
	<tbody>
		<tr>
			<td class="headTab"><bean:message key="activities.game.editTitle"/></td>
		</tr>
			<html:errors/>
		<tr>
			<td><bean:message key="activities.game.name"/>:<br />
			<html:text name="game" property="nameGame" /></td>
		</tr>
		<tr>
			<td><bean:message key="activities.game.url"/>:<br />
			<html:text name="game" property="urlGame" /></td>
		</tr>
		<tr>
			<td><bean:message key="activities.game.description"/>:<br />
				<html:textarea name="game" property="descriptionGame" styleClass="ativDescriptTextarea"/>
			</td>
		</tr>
		
		<tr align="right">
			<td align="right" colspan="3">
				<a onclick=<%="saveGame('"+(idGame)+"');"%>	href="javascript:void(0)"><bean:message key="general.save" /></a> / 
				<a onclick="cancelShowListActivity();" href="javascript:void(0)"><bean:message key="general.cancel" /></a> 
			</td>
		</tr>
	</tbody>
</table>
</td>
</html:form>