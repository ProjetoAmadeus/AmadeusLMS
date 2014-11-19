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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:errors/>

<c:if test="${success}">
<script type="text/javascript">
	success("As configurações foram alteradas com sucesso!");
</script>
</c:if>

<form id="formWebSecurity">
	<div class="lineSettings">
		<input type="checkbox" id="autoSigning" name="autoSigning" 
		<c:if test="${webSettings.securityAutoSigning}">checked="checked"</c:if>
		/>
		&nbsp;<font color="#a30800"><b>Permitir que os usuários se auto-cadastrem.</b></font>
	</div>
	<div class="lineSettings">
		<input type="checkbox" id="" name="" />
		&nbsp;<font color="#a30800"><b>Colocar o sistema em modo de manutenção.</b></font>
	</div>
	<div class="lineBlank"></div>
	<div class="lineSettings">
	<input type="button" value="Salvar Modificações" onclick="saveWebSecuritySettings();" />
	</div>
</form>