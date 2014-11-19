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

<html>
<head>
<link href="<html:rewrite page="/themes/default/css/css.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript">
function formSubmit(){
	document.materialActivity.submit();
}

function editMaterialChange(){
document.getElementById('showFile').style.display = "NONE";
document.getElementById('editFile').style.display = "BLOCK";
document.getElementById('function').innerHTML = "<a onclick='document.materialActivity.submit();' href='javascript:void(0)'><bean:message key='activities.material.sendFile' /></a>";
document.getElementById('nameTextField').disabled = false;
}
</script>
</head>

<body class="bodyfileUpload">
<html:form action="editMaterial" method="post" enctype="multipart/form-data">
<html:hidden property="method" value="editMaterialActivity"/>
<html:hidden property="idModule" value="${module.id}"/>
<html:hidden property="idMaterial" value="${material.id}"/>

<table class="tableinterna3">
<html:errors/>
	<tr>
		<td><bean:message key="activities.material.name" />:<br />
		<html:text property="nameMaterial" value="${material.archiveName}" styleId="nameTextField" disabled="true"/></td>
	</tr>
	<tr>
	<td>
		<div id="editFile" style="display:none">
		<bean:message key="activities.material.archive" />:<br />
		<html:file property="archive" styleClass="avatar"/>
		</div>
		<div id="showFile">
		<table width="100%">
		<tr>
		<td>
		<a href="<%=request.getContextPath()%>/showMaterial.do?method=showMaterial&idActivity=${material.id}&idModule=${module.id}">${material.extension}</a>
		</td>
		<td align="right">
		<a href="javascript:void(0);" onclick="editMaterialChange();"><bean:message key="general.edit"/></a>
		</td>
		</tr>
		</table>
		</div>	
		</td>
	</tr>
	<tr>
		<td><bean:message key="activities.material.fileSize" /></td> 		
	</tr>
	<tr align="right">
		<td>
		<span id="function"><a onclick="top.cancelShowListMaterial(${module.position});"	
		href="javascript:void(0)"><bean:message key="activities.material.sendFile" /></a></span> / 
		<a onclick="top.cancelShowListMaterial(${module.position});" href="javascript:void(0)">
		<bean:message key="general.cancel" /></a>
		</td> 
	</tr>
</table>	
</html:form>
</body>
</html>