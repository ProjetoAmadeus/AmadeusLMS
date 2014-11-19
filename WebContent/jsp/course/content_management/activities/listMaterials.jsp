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

<script type="text/javascript">
	function lfShowViewNewMaterialActivity(idModule, positionModule){
		showViewNewMaterialActivity(idModule, positionModule, '<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.loading" />');
	}
	function lfShowViewNewExternalLinkActivity(idModule, positionModule){
		showViewNewExternalLinkActivity(idModule, positionModule, '<img border=0 src=<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.loading" />');
	}
</script>

<div class="modHeader">
	<h1><bean:message key="activities.newMaterial" /></h1>
</div>
<div class="modLine">&nbsp;</div>

<div class="modLine">
	<a href="javascript:void(0)" onclick="lfShowViewNewMaterialActivity(${param.idModule}, ${param.positionModule})">
		<img alt="Novo Material" src="themes/default/imgs/icons/material_50x50.png">
		<bean:message key="activities.newMaterial"/>
	</a>
</div>

<div class="modLine">
	<a href="javascript:void(0)" onclick="lfShowViewNewExternalLinkActivity(${param.idModule}, ${param.positionModule})">
		<img alt="Novo Link Externo" src="themes/default/imgs/icons/weblink_50x50.png">
		<bean:message key="activities.newExternalLink"/>
	</a>
</div>

<div class="cmFooter">
	<div id="actions">
		<a onclick="cancelShowListActivity(${param.positionModule});" href="javascript:void(0)"><bean:message key="general.cancel" /></a>
	</div>
</div>