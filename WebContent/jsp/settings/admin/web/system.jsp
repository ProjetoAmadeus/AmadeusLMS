<!--
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="lineSettings"><strong>Geral</strong></div>
<div class="hrDiv"></div>
<div class="lineSettings">
	<label class="labelAttribute">Versão:</label>
	<label class="labelValue">${webSettings.systemGeneralVersion}</label>
</div>
<div class="lineSettings">
	<label class="labelAttribute">Língua:</label>
	<label class="labelValue">${webSettings.systemGeneralLanguage}</label>
</div>
<div class="lineSettings">
	<label class="labelAttribute">Encoding:</label>
	<label class="labelValue">${webSettings.systemGeneralEncoding}</label>
</div>
<div class="lineSettings">
	<label class="labelAttribute">Domínio:</label>
	<input type="text" name="description" value="${webSettings.systemGeneralDomain}" style="width: 250px;" />
</div>
<div class="line"></div>
<div class="lineSettings"><strong>Temas</strong></div>
<div class="hrDiv"></div>