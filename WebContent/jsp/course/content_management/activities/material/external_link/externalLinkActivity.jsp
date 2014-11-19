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

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewMenu"/>			
</logic:notPresent>

<div id="externalLinkActivities" class="cmBody">
<html:form action="newExternalLink" >
	<div class="cmTitle">
		<bean:message key="activities.learningObject.title"/>
	</div>
	<html:errors/>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="material.externalLink.name"/>:</label>
	</div>
	<div class="cmLine">
		<bean:write name="externalLink" property="name"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="material.externalLink.url"/>:</label>
	</div>
	<div class="cmLine">	
		<bean:write name="externalLink" property="url"/>
	</div>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="material.externalLink.description"/>:</label>
	</div>
	<div class="cmLine">
		<bean:write name="externalLink" property="description"/>
	</div>
	<div class="cmFooter">
		<div id="actions">
			<a href="${externalLink.url}" target="_blank"><bean:message key="material.externalLink.access" /></a> / 
			<a onclick="backEditName(${module.position});" href="javascript:void(0)"><bean:message key="general.close" /></a>
		</div> 
	</div>
</html:form>
</div>