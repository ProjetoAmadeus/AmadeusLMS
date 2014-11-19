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

<div class="modHeader">
	<h1><bean:message key="activities.newActivity" /></h1>
</div>
<div class="modLine">&nbsp;</div>
<div class="modLine">				
	<select id="activities${param.positionModule}" onchange="selectActivity(${param.idModule}, ${param.positionModule});">
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/information_frame.png) no-repeat;/>')" value="select"><bean:message key="activities.select" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/tags__pencil.png) no-repeat;/>')" value="poll"><bean:message key="activities.poll" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/documents_exclamation.png) no-repeat;/>')" value="materialRequest"><bean:message key="activities.materialRequest" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/balloons.png) no-repeat;/>')" value="forum"><bean:message key="activities.forum" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/puzzle.png) no-repeat;/>')" value="game"><bean:message key="activities.game" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/films_plus.png) no-repeat;/>')" value="videoIriz"><bean:message key="activities.videoIriz" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/mobile_phone_plus.png) no-repeat;/>')" value="learningObject"><bean:message key="activities.learningObject" /></option>
		<option style="padding-left: 20px; padding-bottom: 3px; background:#fff url(themes/default/imgs/icons/document_text.png) no-repeat;/>')" value="evaluation"><bean:message key="activities.evaluation" /></option>
	</select>
</div>
<div style="text-align: right;">
	<a onclick="cancelShowListActivity(${param.positionModule});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
</div>