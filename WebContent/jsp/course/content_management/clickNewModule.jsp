<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<div id="module${lastPositionModule}" class="modBody">
<script type="text/javascript">
	function lfShowNewModule(idCourse, lastPositionModule) {
		showNewModule(idCourse, lastPositionModule,'<img border=0 src=themes/default/imgs/ajax-loader-activity.gif /><bean:message key="ajaxLoading.createModule"/>');
	}
</script>
<table class="tableButton">
	<tbody>
		<tr>
			<td id="loading${lastPositionModule}" align="right">
				<a name="createNewModule" href="javascript:;" onclick="lfShowNewModule(${idCourse}, ${lastPositionModule});">
					<bean:message key="module.createNewModule"/>
				</a>
			</td>
		</tr>
	</tbody>
</table>
</div>