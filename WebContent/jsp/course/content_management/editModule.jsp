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

<script type="text/javascript">
	function lfChangeOrder(idModule, positionModule) {
		changeOrder(idModule, positionModule,'<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.changePosition" />');
	}

	function lfSaveModule(idModule, positionModule) {
		saveModule(idModule,positionModule,'<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.save" />');
	}

	function lfCancelModule(idModule, positionModule) {
		cancelModule(idModule,positionModule,'<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.cancel" />');
	}

	function lfDeleteModule(idModule, positionModule) {
		deleteModule(idModule,positionModule,'<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.delete" />');
	}
</script>
<html:errors/>
<html:form action="/saveModule?method=saveModule" onsubmit="return false;">		
<table class="tableMod">
	<tbody>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td class="headTab">
							<input type="text" name="name" onkeypress="saveModulePressEnter(event,${module.id},${module.position});" value="<bean:write name="module" property="name" filter="false" />" >
						</td>
						<td class="headTab">
							<logic:equal name="module" property="visible" value="true">
								<bean:message key="module.visible"/> <html:checkbox name="module" property="visible" />
							</logic:equal>
							<logic:equal name="module" property="visible" value="false">
								<bean:message key="module.visible"/> <html:checkbox name="module" property="visible" />
							</logic:equal>
						</td>
						<td class="headTab">
							<html:select styleId="moduleNumber${module.position}" name="module" property="position" onchange="lfChangeOrder(${module.id},${module.position});">
								<html:options collection="listModules" property="position"></html:options> 
							</html:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			&nbsp;&nbsp;<b><bean:message key="module.description" />:</b>
			</td>
		</tr>
		<tr>
			<td class="modDescrip">
				<textarea name="description" class="modDescriptTextarea"><bean:write name="module" property="description" filter="false" /></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td class="headTab2" width="50%">
							<bean:message key="module.material" /> <!-- showViewNewMaterialActivity -->
							<a href="javascript:void(0)" onclick="showListMaterial(${module.id}, ${module.position});"><img src="themes/default/imgs/icons/plus_circle.png" class="editIcon"></a>
						</td>
						<td class="headTab2">
							<bean:message key="module.activities" /> 
							<a href="javascript:void(0)" onclick="showListActivity(${module.id}, ${module.position});"><img src="themes/default/imgs/icons/plus_circle.png" class="editIcon"></a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr valign="top">
						<td width="50%">
						<ul class="listStuff" id="materialsList${module.position}">
							<c:forEach var="material" items="${module.materials}">
								<c:if test="${material.requestedMaterial == null}">
								<li> <bean:write name="material" property="archiveName" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'material',${material.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('material',${material.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
								</c:if>
							</c:forEach>
							<c:forEach var="externalLink" items="${module.externalLinks}">
								<li> <bean:write name="externalLink" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'externalLink',${externalLink.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('externalLink',${externalLink.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
						</ul>
						</td>
						<td>
						<ul class="listStuff" id="activitiesList${module.position}">
							<c:forEach var="forum" items="${module.forums}">
								<li title="<bean:message key="activities.forum"/>"> <bean:write name="forum" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'forum',${forum.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('forum',${forum.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="poll" items="${module.polls}">
								<li title="<bean:message key="activities.poll"/>"> <bean:write name="poll" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'poll',${poll.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('poll',${poll.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="video" items="${module.videos}">
								<li title="<bean:message key="activities.videoIriz"/>"> <bean:write name="video" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'videoIriz',${video.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('videoIriz',${video.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="materialRequest" items="${module.materialRequests}">
								<li title="<bean:message key="activities.materialRequest"/>"> <bean:write name="materialRequest" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'materialRequest',${materialRequest.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('materialRequest',${materialRequest.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="game" items="${module.games}">
								<li title="<bean:message key="activities.game"/>"> <bean:write name="game" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'game',${game.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('game',${game.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="learningObject" items="${module.learningObjects}">
								<li title="<bean:message key="activities.learningObject"/>"> <bean:write name="learningObject" property="name" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'learningObject',${learningObject.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('learningObject',${learningObject.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
							<c:forEach var="evaluation" items="${module.evaluations}">
								<li title="<bean:message key="activities.evaluation"/>"> <bean:write name="evaluation" property="description" filter="false" />
									<a href="javascript:void(0)" onclick="editActivity(${module.position},'evaluation',${evaluation.id});"><img src="themes/default/imgs/icons/pencil_small.png" class="editIcon" title="<bean:message key="general.edit" />"></a>
									<a href="javascript:void(0)" onclick="deleteActivity('evaluation',${evaluation.id});"><img src="themes/default/imgs/icons/cross_small.png" class="editIcon" title="<bean:message key="general.delete" />"></a>
								</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</table>
		</td>		
	</tr>
</table>	
</html:form>
<table class="tableButton">
	<tbody>
		<tr>
			<td align="right">
			<div id="dynamic${module.position}">
				<a href="javascript:void(0)" onclick="lfSaveModule(${module.id},${module.position});">
				<bean:message key="general.save" /></a> / 
				<a href="javascript:void(0)" onclick="lfCancelModule(${module.id},${module.position});">
				<bean:message key="general.cancel" /></a> / 
				<a href="javascript:void(0)" onclick="lfDeleteModule(${module.id},${module.position});">
				<bean:message key="general.delete" /></a>
			</div>
			</td>
		</tr>
	</tbody>
</table>

