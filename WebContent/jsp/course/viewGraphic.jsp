<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript">
var moduleSel = '<%=request.getAttribute("moduleSel")%>'; 
var graphic = '<%=request.getAttribute("graphic")%>';
if(moduleSel != "-1")
{
	var xml = '<%=request.getAttribute("xml")%>';
}

function getXML()
{
	document["Grafico"].sendXML(xml);
}

function selectAluno()
{
	var sele = document.getElementById("comboAluno").value;
	var courseId = document.getElementById("inputCourse").value;

	window.location = "http://"+'<%=request.getAttribute("domain")%>'+"/course.do?method=showViewGraphic&courseId="+courseId+"&graphic="+graphic+"&moduleSel="+moduleSel+"&idAluno="+sele+"&idGame=-1";
}

function selectModule()
{
	var sele = document.getElementById("comboModule").value;
	var courseId = document.getElementById("inputCourse").value;

	window.location = "http://"+'<%=request.getAttribute("domain")%>'+"/course.do?method=showViewGraphic&courseId="+courseId+"&graphic="+graphic+"&moduleSel="+sele+"&idAluno=-1&idGame=-1";
}

function selectGrafico()
{
	var sele = document.getElementById("comboGrafico").value;
	var courseId = document.getElementById("inputCourse").value;

	window.location = "http://"+'<%=request.getAttribute("domain")%>'+"/course.do?method=showViewGraphic&courseId="+courseId+"&graphic="+sele+"&moduleSel=-1&idAluno=-1&idGame=-1";
}

function selectGame()
{
	var sele = document.getElementById("comboGame").value;
	var courseId = document.getElementById("inputCourse").value;

	window.location = "http://"+'<%=request.getAttribute("domain")%>'+"/course.do?method=showViewGraphic&courseId="+courseId+"&graphic="+graphic+"&moduleSel="+moduleSel+"&idAluno=-1&idGame="+sele;
}

	
</script>
</head>
<body>
	<input id="inputCourse" type="hidden" value="${course.id}"/>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Módulo de Visualização</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}"><bean:write name="course" property="name"/></html:link></li>
				<li>Módulo de Visualização</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="5" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
			
			<!-- Added By Nailson Cunha -->
			<div id="twitter-container">
				<jsp:include page="/jsp/twittertool/userTwitter.jsp" />
			</div>
		</div>
		<div id="pContent" class="pContent2">

			<div id="comboBoxGraficoContent">
				Vizualização:
				<select id="comboGrafico" onchange="selectGrafico();">
					<option value="-1" label="Selecione"></option>
					<c:if test="${graphic == 'g1'}">
						<option value="g1" label="Tempo total de jogo" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g1'}">
						<option value="g1" label="Tempo total de jogo"/>
					</c:if>
					<c:if test="${graphic == 'g2'}">
						<option value="g2" label="Tempo de jogo por dia" selected="selected"/>		
					</c:if>
					<c:if test="${graphic != 'g2'}">
						<option value="g2" label="Tempo de jogo por dia"/>		
					</c:if>
					<c:if test="${graphic == 'g3'}">
						<option value="g3" label="Termos Mais Usados" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g3'}">
						<option value="g3" label="Termos Mais Usados"/>
					</c:if>
					<c:if test="${graphic == 'g4'}">
						<option value="g4" label="Quantidade de mensagens" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g4'}">
						<option value="g4" label="Quantidade de mensagens"/>
					</c:if>
					<c:if test="${graphic == 'g5'}">
						<option value="g5" label="Media de tamanho das mensagens" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g5'}">
						<option value="g5" label="Media de tamanho das mensagens"/>
					</c:if>
					<c:if test="${graphic == 'g6'}">
						<option value="g6" label="Tempo de jogo por Aluno" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g7'}">
						<option value="g7" label="Tempo de logado no sistema por Aluno"/>
					</c:if>
					<c:if test="${graphic == 'g7'}">
						<option value="g7" label="Tempo de permanência no sistema" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g8'}">
						<option value="g8" label="Quantidade de visualizações dos fóruns"/>
					</c:if>
					<c:if test="${graphic == 'g8'}">
						<option value="g8" label="Quantidade de visualizações dos fóruns" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g9'}">
						<option value="g9" label="Quantidade de posts em um fórum"/>
					</c:if>
					<c:if test="${graphic == 'g9'}">
						<option value="g9" label="Quantidade de posts em um fórum" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g10'}">
						<option value="g10" label="Quantidade de acessos à materiais"/>
					</c:if>
					<c:if test="${graphic == 'g10'}">
						<option value="g10" label="Quantidade de acessos à materiais" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g11'}">
						<option value="g11" label="Quantidade de enquetes respondidas"/>
					</c:if>
					<c:if test="${graphic == 'g11'}">
						<option value="g11" label="Quantidade de enquetes respondidas" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g12'}">
						<option value="g12" label="Quantidade de acesso aos jogos"/>
					</c:if>
					<c:if test="${graphic == 'g12'}">
						<option value="g12" label="Quantidade de acesso aos jogos" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g13'}">
						<option value="g13" label="Tamanho Medio das Mensagens x Quantidade de Mensagens"/>
					</c:if>
					<c:if test="${graphic == 'g13'}">
						<option value="g13" label="Tamanho Medio das Mensagens x Quantidade de Mensagens" selected="selected"/>
					</c:if>
					<c:if test="${graphic == 'g14'}">
						<option value="g14" label="Pontuação via Gráfico de Colunas" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g14'}">
						<option value="g14" label="Pontuação via Gráfico de Colunas"/>
					</c:if>
					<c:if test="${graphic == 'g15'}">
						<option value="g15" label="Pontuação via Gráfico Pizza" selected="selected"/>		
					</c:if>
					<c:if test="${graphic != 'g15'}">
						<option value="g15" label="Pontuação via Gráfico Pizza"/>		
					</c:if>
					<c:if test="${graphic == 'g16'}">
						<option value="g16" label="Level via Gráfico de Colunas" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g16'}">
						<option value="g16" label="Level via Gráfico de Colunas"/>
					</c:if>
					<c:if test="${graphic == 'g17'}">
						<option value="g17" label="Level via Gráfico Pizza" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g17'}">
						<option value="g17" label="Level via Gráfico Pizza"/>
					</c:if>
					<c:if test="${graphic == 'g18'}">
						<option value="g18" label="Tempo x Level X Pontuação" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g18'}">
						<option value="g18" label="Tempo x Level X Pontuação"/>
					</c:if>
					<c:if test="${graphic == 'g19'}">
						<option value="g19" label="Tempo Total Jogado x Quantidade de Partidas" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g19'}">
						<option value="g19" label="Tempo Total Jogado x Quantidade de Partidas"/>
					</c:if>
					<c:if test="${graphic == 'g20'}">
						<option value="g20" label="Level x Pontuação" selected="selected"/>
					</c:if>
					<c:if test="${graphic != 'g20'}">
						<option value="g20" label="Level x Pontuação"/>
					</c:if>
				</select>
			</div>
			<div id="comboBoxModuleContent">
				<c:if test="${graphic == 'g1' || graphic == 'g2' || graphic == 'g3' || graphic == 'g4' || graphic == 'g5' || graphic == 'g6' || graphic == 'g8' || graphic == 'g9' || graphic == 'g10' || graphic == 'g11' || graphic == 'g12' || graphic == 'g13' || graphic == 'g14' || graphic == 'g15' || graphic == 'g16' || graphic == 'g17' || graphic == 'g18' || graphic == 'g19' || graphic == 'g20' }">
					Modulo:
					<select id="comboModule" onchange="selectModule();">
						<option value="-1" label="Selecione"></option>
						<c:forEach var="module" items="${modules}" varStatus="status">
							<c:if test="${module.id == moduleSel}">
								<option value="${module.id}" label="${module.name}" selected="selected"/>		
							</c:if>
							<c:if test="${module.id != moduleSel}">
								<option value="${module.id}" label="${module.name}"/>		
							</c:if>
						</c:forEach>
					</select>
				</c:if>
			</div>
			<div id="comboBoxAlunoContent">
				<c:if test="${(graphic == 'g6' || graphic == 'g8' || graphic == 'g9' || graphic == 'g10' || graphic == 'g11' || graphic == 'g12')&& moduleSel != -1}">
					Aluno:
					<select id="comboAluno" onchange="selectAluno();">
					<option value="-1" label="Selecione"></option>
						<c:forEach var="aluno" items="${alunos}" varStatus="status">
							<c:if test="${aluno.id == idAluno}">
								<option value="${aluno.id}" label="${aluno.name}" selected="selected"/>		
							</c:if>
							<c:if test="${aluno.id != idAluno}">
								<option value="${aluno.id}" label="${aluno.name}"/>		
							</c:if>
						</c:forEach>
					</select>
					<br/>
				</c:if>
			</div>
			<div id="comboBoxGameContent">
				<c:if test="${(graphic == 'g14' || graphic == 'g15' || graphic == 'g16' || graphic == 'g17' || graphic == 'g18' || graphic == 'g19' || graphic == 'g20')&& moduleSel != -1}">
					Jogo:
					<select id="comboGame" onchange="selectGame();">
					<option value="-1" label="Selecione"></option>
						<c:forEach var="game" items="${games}" varStatus="status">
							<c:if test="${game.id == idGame}">
								<option value="${game.id}" label="${game.name}" selected="selected"/>		
							</c:if>
							<c:if test="${game.id != idGame}">
								<option value="${game.id}" label="${game.name}"/>		
							</c:if>
						</c:forEach>
					</select>
					<br/>
				</c:if>
			</div>	
			<div>
				<c:choose>
					<c:when test="${(graphic == 'g1' && moduleSel != -1)||(graphic == 'g6' && moduleSel != -1 && idAluno != -1) || ((graphic == 'g15'||graphic == 'g17') && moduleSel != -1 && idGame != -1)}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoPizza.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/GraficoPizza.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>			
					</c:when>
					<c:when test="${graphic == 'g2' && moduleSel != -1}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoLine.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/GraficoLine.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>			
					</c:when>
					<c:when test="${graphic == 'g3' && moduleSel != -1}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/CumulusTagCloud.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/CumulusTagCloud.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>			
					</c:when>
					<c:when test="${((graphic == 'g4' || graphic == 'g5') && moduleSel != -1) || ((graphic == 'g14'||graphic == 'g16') && moduleSel != -1 && idGame != -1)}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoColuna.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/GraficoColuna.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>			
					</c:when>
					<c:when test="${(graphic == 'g8' || graphic == 'g9' || graphic == 'g10' || graphic == 'g11' || graphic == 'g12') && moduleSel != -1 && idAluno != -1}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoLine.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/GraficoLine.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>		
					</c:when>
					<c:when test="${(graphic == 'g13' && moduleSel != -1) || (graphic == 'g19' && moduleSel != -1 && idGame != -1)}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        	   				id="ExternalInterfaceExample" width="500" height="375"
	        		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
    		   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoPlot.swf" />
    	   					<param name="quality" value="high" />
	       					<param name="bgcolor" value="#869ca7" />
	       					<param name="allowScriptAccess" value="sameDomain" />
		       				<embed src="http://<%=request.getAttribute("domain")%>/GraficoPlot.swf" quality="high" bgcolor="#869ca7"
    		    	   			width="480" height="375" name="Grafico" align="middle"
    			   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
	           					type="application/x-shockwave-flash"
           						pluginspage="http://www.macromedia.com/go/getflashplayer">
	       					</embed>
	   					</object>			
					</c:when>
					<c:when test="${graphic == 'g18' && moduleSel != -1 && idGame != -1}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoBolha.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%=request.getAttribute("domain")%>/GraficoBolha.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g20' && moduleSel != -1 && idGame != -1}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%=request.getAttribute("domain")%>/GraficoLine2.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%=request.getAttribute("domain")%>/GraficoLine2.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>
					</c:when>
				</c:choose>
			</div>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>