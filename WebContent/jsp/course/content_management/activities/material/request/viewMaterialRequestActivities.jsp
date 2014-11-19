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
		document.getElementById("materialActivity${idActivity}").submit();
	}
</script>

<div id="viewMaterialRequestActivities" class="cmBody">
	<c:if test="${!callFromPaddingTask}">
	<div class="cmTitle">
		<bean:message key="activities.materialRequest"/>
	</div>
	</c:if>
	<html:errors/>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.name" />:</label>
		<label class="labelValue"><bean:write property="nameMaterial" name="materialRequestActivity" filter="false"/></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.description" />:</label>
		<label class="labelValue"><bean:write property="descriptionMaterial" name="materialRequestActivity" filter="false" /></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute">Postar arquivo até:</label>
		<label class="labelValue"><bean:write property="deliveryDate" name="materialRequestActivity" format="EEEE, d MMMM yyyy"/></label>
	</div>
	<c:if test="${materialRequest.allowLateDeliveries}">
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.acceptedDeliveryAfterDate" /></label>
	</div>
	</c:if>
	<c:if test="${!materialRequest.allowLateDeliveries}">
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.notAcceptedDeliveryAfterDate" /></label>
	</div>
	</c:if>
<c:if test="${material != null}">
	<div id="msgMatInfo" class="msgBoxInformation">
		<ul class="listActivitiesMaterials">
			<li class='<c:import url="/module.do?method=getDefineCSSClass&extension=${material.extension}" />'>
			<html:link action="showMaterial.do?method=showMaterial&idActivity=${material.id}&idModule=${module.id}">
			<bean:write name="material" property="archiveName" filter="false"/></html:link>
			<br/><br/>
			</li>
			<c:if test="${material.correctedDate != null}">
			<li class="agt15">
				Nota: ${material.grade} &nbsp;&nbsp;&nbsp;&nbsp;
				Data da Correção: <bean:write property="correctedDate" name="material" format="dd / MM / yyyy"/>	
			</li>
			</c:if>
			<c:if test="${material.correctedDate == null}">
			<li class="waiting">
				Aguarde a correção do professor.
			</li>
			</c:if>
		</ul>
	</div>
</c:if>
<c:if test="${material == null}">
	<c:if test="${expiredDelivery < 0 && !materialRequest.allowLateDeliveries}">
		<div class="msgBoxImportant">
			<bean:message key="activities.material.deadlineExpired" />
		</div>
	</c:if>
	<c:if test="${(expiredDelivery >= 0) || (expiredDelivery < 0 && materialRequest.allowLateDeliveries)}">
		<html:form action="newMaterial" method="post" enctype="multipart/form-data" target="iframeUpload" styleId="materialActivity${idActivity}">
		<html:hidden property="method" value="newMaterialDelivery"/>
		<html:hidden property="idModule" value="${module.id}"/>
		<html:hidden property="idActivity" value="${idActivity}"/>
		<html:hidden property="callFromPaddingTask" value="${callFromPaddingTask}"/>
		
		<div class="cmHrDiv"></div>
		<html:errors/>
		<div class="cmLine">	
			<label class="labelAttribute"><bean:message key="activities.material.name" />:</label>
		</div>
		<div class="cmLine">
			<html:text property="nameMaterial"/>
		</div>
		<div class="cmLine">	
			<label class="labelAttribute"><bean:message key="activities.material.archive" />:</label>
		</div>
		<div class="cmLine">
			<html:file property="archive" styleClass="avatar"/>
		</div>
		<div class="cmLine">
			<label class="labelAttribute"><bean:message key="activities.material.fileSize" /></label>
		</div>
		<div class="cmFooter">
			<a onclick="formSubmit();" href="javascript:void(0)"><bean:message key="activities.material.sendFile" /></a>
		</div>	
		</html:form>
		<iframe name="iframeUpload" style="display:none"></iframe>
	</c:if>
</c:if>
<c:if test="${!callFromPaddingTask}">
	<div class="cmFooter">
		<a onclick="backEditName(${module.position});" href="javascript:void(0)"><bean:message key="general.close" /></a>
	</div>
</c:if>	
</div>