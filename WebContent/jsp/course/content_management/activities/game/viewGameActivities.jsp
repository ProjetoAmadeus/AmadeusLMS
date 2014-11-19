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

<div class="cmTitle">
	<bean:message key="activities.game"/>
</div>
<div>
	<div class="cmGamePicture">
		<img src="themes/default/imgs/005.jpg"/> 
	</div>
	<div class="cmGameInfo">
		<h1>${game.name}</h1>
		<p>${game.description}</p>
	</div>
</div>	
	<div class="cmGameFooter">
		<div class="cmFloatRight">
			<html:link action="/gameActions?method=openAmadeusGames"><bean:message key="general.submit"/></html:link> /
			<a onclick="backEditName();" href="javascript:void(0)"><bean:message key="general.back" /></a>
		</div>
		
		<div class="cmFloatLeft">
			<a onclick="showPlayerGame('IDDOJOGO');" href="javascript:void(0)"><bean:message key="activities.game.playersOnline"/></a> /
			<a onclick="showScoreGame('top5','idDOJOGO');" href="javascript:void(0)"><bean:message key="activities.game.score"/></a>
		</div>
		<div style="clear:both"></div>
	</div>
