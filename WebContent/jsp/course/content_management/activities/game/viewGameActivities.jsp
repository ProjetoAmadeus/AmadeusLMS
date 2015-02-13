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

<div class="cmTitle">
	<bean:message key="activities.game"/>
</div>
<div>
	<div class="cmGamePicture">
		<img src="viewImage.do?method=viewImage&gameId=${game.id}" width="150" height="150"/> 
	</div>
	<div class="cmGameInfo">
		<h1>${game.name}</h1>
		<p>${game.description}</p>
	</div>
</div>	
<div class="cmGameFooter">
	<div class="cmFloatRight">
		<!--<html:link action="/gameActions?method=openAmadeusGames"><bean:message key="general.submit"/></html:link>-->
		<c:if test="${game.linkExterno == 'false'}">
			<a onclick="modalGameWin(${game.id});" href="javascript:void(0)"><bean:message key="general.submit" /></a> /
		</c:if>
		<c:if test="${game.linkExterno == 'true'}">
			<a onclick="openGameExternal('${game.url}');" href="javascript:void(0)"><bean:message key="general.submit" /></a> /
		</c:if>
		<a onclick="window.location.reload();" href="javascript:void(0)"><bean:message key="general.back" /></a>
	</div>
</div>
<div>
	<c:if test="${game.linkExterno == 'false'}">
		<div class="cmTitle">
			Dados do jogo
		</div>
		<div class="cmFloatLeft">
			<a onclick="showScoreGameGrupo('grupo',${game.id});" href="javascript:void(0)">
				<img src="themes/default/imgs/icons/146.ico" width="38" height="38"> Grupo 
			</a> &nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="showScoreGameIndividual('individual',${game.id},${user.id});" href="javascript:void(0)">
				<img src="themes/default/imgs/icons/user.ico" width="38" height="38"/> Individual 
			</a> &nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="showScoreGameIndividual('visualizacao',${game.id});" href="javascript:void(0)">
				<img src="themes/default/imgs/icons/icon_grafico.gif" width="38" height="38"/> Visualização 
			</a>
<!--			<a onclick="modalVisualizacaoWin(${game.id});" href="javascript:void(0)"> -->
<!--				<img src="themes/default/imgs/icons/icon_grafico.gif" width="38" height="38"/> Visualização -->
<!--			</a>-->
		</div>
	</c:if>
	<div  id="percepcao${game.id}">
	</div>
</div>	
<div style="clear:both"></div>