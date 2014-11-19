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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<script type="text/javascript"> 
	function formSubmit(){
		uploadVideoIrizToYoutubeStep1(${module.id});
		ajaxLoadingConfig('actions', '<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif />&nbsp;<bean:message key="ajaxLoading.sendingYoutube" />');
		document.videoUploadYoutube.submit();
	}
</script>

<div id="youtubeUpload" class="cmBody">
	<div class="cmTitle">
		<bean:message key="activities.videoIriz"/>
	</div>	
	<html:errors/>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.video.name" />:</label>
	</div>
	<div class="cmLine">
		<html:text property="nameVideoIriz" name="videoIrizForm" value="${videoIrizName}" disabled="true"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.video.description" />:</label>
	</div>
	<div class="cmLine">
		<html:textarea property="videoDescription" name="videoIrizForm" value="${videoDescription}" disabled="true" styleClass="cmTextArea" />
	</div>
	<form target="iframeUpload" action="${url}?nexturl=http://${address}:${port}${contextPath}/videoActivity.do?method=newVideoIrizUploadActivity" name="videoUploadYoutube" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token" value="${token}"/>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.video.fileUpload" />:</label>
	</div>
	<div class="cmLine">
		<input type="file" name="file"/>
	</div>
	<div class="msgBoxInformation">
		<bean:message key="activities.video.fileFormats" />
	</div>
	<div class="cmFooter">
		<div id="actions">
			<a onclick="formSubmit();" href="javascript:void(0)"><bean:message key="general.send" /></a> / 
			<a onclick="top.cancelShowListMaterial(${module.position});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
		</div>
	</div>
	</form>
	<iframe name="iframeUpload" style="display:none"></iframe>
</div>