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
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:errors/>

<script type="text/javascript">
<c:if test="${success}">
	success("As configurações foram alteradas com sucesso!");
</c:if>
</script>

<form id="formWebSettingMailSender"> 
<div class="lineSettings"><strong>Servidor de envio (SMTP)</strong></div>
<div class="hrDiv"></div>
<fieldset>
   <legend><b>Configurações</b></legend>
   <table>
   		<tr>
   			<td align="right"><label class="labelAttribute"><bean:message key="settings.web.mailSender.description"/>:</label></td>
   			<td><input type="text" name="description" value="${webSettings.mailDescription}${description}" style="width: 250px;" /></td>
   		</tr>
   		<tr>
   			<td align="right"><label class="labelAttribute"><bean:message key="settings.web.mailSender.server"/>:</label></td>
   			<td><input type="text" name="server" style="width: 250px;" value="${webSettings.mailSmtpHost}${server}" /></td>
   		</tr>
   		<tr>
   			<td align="right"><label class="labelAttribute"><bean:message key="settings.web.mailSender.port"/>:</label></td>
   			<td><input type="text" name="port" style="width: 30px;" value="${webSettings.mailPort}${port}" />&nbsp;&nbsp;<label class="labelAttribute">Padão:</label>25</td>
   		</tr>
   </table>
</fieldset>
<div class="lineSettings">&nbsp;</div>
<fieldset>
   <legend><b>Segurança e autenticação</b></legend>
   <table>
   		<tr>
   			<td style="text-align: right;"><label class="labelAttribute"><bean:message key="settings.web.mailSender.userName"/>:</label></td>
   			<td><input type="text" name="userName" value="${webSettings.mailFrom}${userName}" style="width: 250px;"/></td>
   		</tr>
   		<tr>
   			<td style="padding-left: 25px; text-align: right;"><label class="labelAttribute"><bean:message key="settings.web.mailSender.password"/>:</label></td>
   			<td><input type="password" name="password" value="" /></td>
   		</tr>
   		<tr>
   			<td colspan="2"><b>Usar conexão segura:</b></td>
   		</tr>
   		<tr>
   			<td colspan="2" style="padding-left: 25px;">
   			<c:if test="${securityConnection eq null}">
   				<input type="radio" class="radio" name="securityConnection" value="withoutSecurityConnection" <c:if test="${webSettings.mailWithoutSecurityConnection}">checked="checked"</c:if> />Não&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="startTLSEnable" <c:if test="${webSettings.mailStartTLSEnable}">checked="checked"</c:if> />TLS, se deisponível&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="startTLSRequired" <c:if test="${webSettings.mailStartTLSRequired}">checked="checked"</c:if> />TLS&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="SSLEnable" <c:if test="${webSettings.mailSSLEnable}">checked="checked"</c:if> />SSL
   			</c:if>
   			<c:if test="${securityConnection != null}">
   				<input type="radio" class="radio" name="securityConnection" value="withoutSecurityConnection" <c:if test="${securityConnection eq 'withoutSecurityConnection'}">checked="checked"</c:if> />Não&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="startTLSEnable" <c:if test="${securityConnection eq 'startTLSEnable'}">checked="checked"</c:if> />TLS, se deisponível&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="startTLSRequired" <c:if test="${securityConnection eq 'startTLSRequired'}">checked="checked"</c:if> />TLS&nbsp;&nbsp;
   				<input type="radio" class="radio" name="securityConnection" value="SSLEnable" <c:if test="${securityConnection eq 'SSLEnable'}">checked="checked"</c:if> />SSL
   			</c:if>
   			</td>
   		</tr>
   </table>
</fieldset>
<div class="lineBlank"></div>
<div class="lineSettings">
	<input type="button" value="Salvar Modificações" onclick="saveWebMailSenderSettings();" />
</div>
</form>