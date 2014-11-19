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
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<div id="editLearningObjectActivities" class="cmBody">
<html:form action="newLearningObject">
<html:hidden name="externalLink" property="idExternalLink"/>
	<div class="cmTitle">
		<bean:message key="activities.learningObject"/>
	</div>
	<html:errors/>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.learningObject.name" />:</label>
	</div>
	<div class="cmLine">	
		<input type="text" id="nameLearningObject" value="<bean:write name="learningObject" property="name" filter="false" />" />
	</div>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.learningObject.url" />:</label>
	</div>
	<div class="cmLine">	
		<input type="text" id="urlLearningObject" value="<bean:write name="learningObject" property="url" filter="false" />" class="cmTextUrl" />
	</div>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.learningObject.description" />:</label>
	</div>
	<div class="cmLine">
		<textarea id="descriptionLearningObject" class="cmTextArea"><bean:write name="learningObject" property="description" filter="false" /></textarea>
	</div>
	<div class="cmFooter">
		<div id="actions">
			<a onclick="editLearningObject(${module.position});" href="javascript:void(0)"><bean:message key="general.save" /></a> / 
			<a onclick="cancelShowListActivity(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
		</div>
	</div>
</html:form>
</div>