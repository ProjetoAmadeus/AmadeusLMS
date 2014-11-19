<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<div id="institutional_menu">
	<c:if test="${user != null && user.typeProfile eq 'ADMIN'}">
	<span>
		<html:link action="settingsActions.do?method=showViewSettings">
		<img src="themes/default/imgs/menu/settings.png" border="0" style="vertical-align: middle;">
		&nbsp;Configurações
		</html:link>
	</span>
	<span>&nbsp;|&nbsp;</span>
	</c:if>
	<span><html:link href="http://www.softwarepublico.gov.br/ver-comunidade?community_id=9677539" target="_blank"><bean:message key="institutionalLinks.community" /><span style="padding-left:5px;" ><a target="blank_" href="http://www.softwarepublico.gov.br"><img src="themes/default/imgs/pspb-logo.png" /></a></span></html:link></span>
</div>