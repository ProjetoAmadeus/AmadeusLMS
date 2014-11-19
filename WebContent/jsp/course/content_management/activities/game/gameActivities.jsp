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
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<script type="text/javascript">
	function ajaxLoadingConfig(div, innerHTML) {
		document.getElementById(div).innerHTML = innerHTML;
	  	document.getElementById(div).style.textAlign = "center";
	  	document.getElementById(div).style.color = "#2f7445"; 
	  	document.getElementById(div).style.fontWeight = "bolder";
	}
	
	function formSubmit(){
		ajaxLoadingConfig('actions','<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.save" />');
	  	document.gameActivity.submit();
	}
</script>

<div id="gameActivity" class="cmBody">
<html:form action="newGameActivity" method="post" enctype="multipart/form-data" target="iframeUpload">
<html:hidden property="method" value="newGame"/>
<html:hidden property="moduleId" value="${module.id}"/>
	<div class="cmTitle">
		<bean:message key="activities.game.title"/>
	</div>
	<html:errors/>
	<div class="cmLine">	
		<label class="labelAttribute"><bean:message key="activities.game.name"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="gameActivity" property="nameGame" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.maxUsers"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="gameActivity" property="maxUsers" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.minUsers"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="gameActivity" property="minUsers" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.url"/>:</label>
	</div>
	<div class="cmLine">
		<html:text name="gameActivity" property="urlGame" />
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.description"/>:</label>
	</div>
	<div class="cmLine">
		<html:textarea name="gameActivity" property="descriptionGame" styleClass="ativDescriptTextarea"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.game.image"/>:</label>
	</div>
	<div class="cmLine">
		<html:file property="image" />
	</div>
	<div class="cmFooter">
		<div id="actions">
				<a onclick="formSubmit();"	href="javascript:void(0)"><bean:message key="general.save" /></a> / 
				<a onclick="cancelShowListActivity(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a> 
		</div>
	</div>
</html:form>
<iframe name="iframeUpload" style="display:none"></iframe>
</div>