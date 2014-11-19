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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		
<script type="text/javascript">
	function minusPlus(id){
		var valueLink = $("#link"+id).attr("class");
		$("#realizedQuestions"+id).toggle("clip");
		if (valueLink.indexOf('imgPlus') != -1) {
			$("#link"+id).removeClass("imgPlus");
			$("#link"+id).addClass("imgMinus");
		} else { 
			$("#link"+id).removeClass("imgMinus");
			$("#link"+id).addClass("imgPlus"); 
		};
	}

	function lfSaveMaterialGrade(msgPosition, materialId, msgSuccess) {
		saveMaterialGrade(msgPosition, materialId, msgSuccess);
	}
</script>

<div id="viewMaterialRequestTeacherActivities" class="cmBody">		 
	<div class="cmTitle">
		<bean:message key="activities.materialRequest"/>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.name" />:</label>
		<label class="labelValue"><bean:write property="nameMaterial" name="materialRequestActivity" filter="false" /></label>
	</div>
	<div class="cmLine"> 
		<label class="labelAttribute"><bean:message key="activities.materialRequest.description" />:</label>
		<label class="labelValue"><bean:write property="descriptionMaterial" name="materialRequestActivity" filter="false" /></label>
	</div>
	<div class="cmLine">
		<label class="labelAttribute"><bean:message key="activities.materialRequest.dateToDelivery" />:</label>
		<label class="labelValue"><bean:write property="deliveryDate" name="materialRequestActivity" format="EEEE, d MMMM yyyy"/></label>
	</div>
	<div class="containerContent">
<c:forEach var="material" items="${materials}" varStatus="status">
	<div id="material${status.count}" class="expansibleBoxBody">
		<div id="msgMatInfo${status.count}" style="text-align: center; color: #35784c; font-weight: bold; display: none;"></div>
		<div class="expansibleBoxTitle">
			<div class="expansibleBoxTitleCollLeft">
				${material.author.name}
			</div>
			<div id="link${status.count}" onclick="minusPlus(${status.count});" class="expansibleBoxTitleCollRight imgPlus"></div>
		</div>
		<div id="realizedQuestions${status.count}" class="expansibleBoxContent">
			<div id="matInfo${status.count}" class="expansibleBoxBody">
				<table width="100%">
					<tr>
						<td style="width: 50%; vertical-align: middle;">
							<ul class="listActivitiesMaterials">
								<li class='<c:import url="/module.do?method=getDefineCSSClass&extension=${material.extension}" />'>
								<html:link action="showMaterial.do?method=showMaterial&idActivity=${material.id}&idModule=${param.idModule}">
								<bean:write name="material" property="archiveName"/></html:link>
								</li>
							</ul>
						</td>
						<td style="width: 50%; vertical-align: middle; text-align: right;">
							<html:form action="/newMaterial">
								<bean:message key="activities.material.grade"/>: 
								<html:text name="material" property="grade" styleId="materialGrade${status.count}" styleClass="grade"/> 
								<img src="themes/default/imgs/icons/agt_15.png" title="Salvar" style="cursor: pointer;" onclick="lfSaveMaterialGrade(${status.count},${material.id},'Nota salva com sucesso!')" />
							</html:form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
	</div>
	<div class="cmFooter">
		<a href="javascript:void(0)" onclick="backEditName(${module.position});"><bean:message key="general.close" /></a>
	</div>
</div>