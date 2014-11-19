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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade,
				 java.util.HashMap,br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game,
				 java.util.HashMap,br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module" %>

<%int idGame=-1; %>
<%

idGame = Integer.parseInt(request.getParameter("idGame"));

Facade facade = Facade.getInstance();
Game game = facade.getGameById(idGame);

HashMap<String, Object> dataGame = new HashMap<String, Object>();
HashMap<String, Object> dataModule = new HashMap<String, Object>();

dataGame.put("nameGame",game.getName());
dataGame.put("urlGame", game.getUrl());
dataGame.put("descriptionGame", game.getDescription());
dataGame.put("isExternal", game.getLinkExterno());
dataGame.put("id",game.getId());

Module module = game.getModule();

dataModule.put("position", module.getPosition());

request.setAttribute("game", dataGame);
request.setAttribute("module", dataModule);



%>

<script type="text/javascript">

</script>

<div id="gameActivity" class="cmBody">
<html:form action="newGameActivity" method="post" enctype="multipart/form-data" target="iframeUpload">
<html:hidden property="method" value="editGame"/>
<html:hidden property="idGame" value="${game.id}"/>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.game.name"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="gameActivity" property="nameGame" value="${game.nameGame}"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Url externo:</label>
	</div>
	<div class="cmLine">
		<c:if test="${!game.isExternal}">
			<html:text property="externalUrlGame" />
		</c:if>
		<c:if test="${game.isExternal}">
			<html:text property="externalUrlGame" value="${game.urlGame}"/>
		</c:if>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Upload do jogo:</label>
	</div>
	<div class="cmLine">
		<html:file property="urlGame" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.description"/>:</label>
	</div>
	<div class="cmLine">
		<html:textarea name="gameActivity" property="descriptionGame" styleClass="ativDescriptTextarea" value="${game.descriptionGame}"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.image"/>:</label>
	</div>
	<div class="cmLine">
		<html:file property="image" />
	</div>
	<div class="cmFooter">
		<div id="actions">
				<a onclick="formSubmitEditGame();"	href="javascript:void(0)"><bean:message key="general.save" /></a> / 
				<a onclick="cancelShowListActivity(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a> 
		</div>
	</div>
</html:form>
<iframe name="iframeUpload" style="display:none"></iframe>
</div>