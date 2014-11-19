<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
<script type="text/javascript">


var xml = '<%=(String)request.getAttribute("xml")%>';


function getXML()
{
	document["Grafico"].sendXML(xml);
};


	
</script>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<div id="pTitle" class="pTitle">
			<h2>Visualização</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li>Visualização</li>
			</ul>
		</div>
		<div id="pContent" class="pContent2">
			<div id="divComboBox">	
				<c:if test="${graphic == 'g3'}"><li><b>Pontuação via Gráfico de Colunas</b></li></c:if>
				<c:if test="${graphic != 'g3'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g3">Pontuação via Gráfico de Colunas</html:link></li></c:if>		
				<c:if test="${graphic == 'g4'}"><li><b>Pontuação via Gráfico Pizza</b></li></c:if>
				<c:if test="${graphic != 'g4'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g4">Pontuação via Gráfico Pizza</html:link></li></c:if>
				<c:if test="${graphic == 'g5'}"><li><b>Pontuação via Gráfico TreeMap</b></li></c:if>
				<c:if test="${graphic != 'g5'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g5">Pontuação via Gráfico TreeMap</html:link></li></c:if>
				<c:if test="${graphic == 'g6'}"><li><b>Level via Gráfico de Colunas</b></li></c:if>
				<c:if test="${graphic != 'g6'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g6">Level via Gráfico de Colunas</html:link></li></c:if>
				<c:if test="${graphic == 'g7'}"><li><b>Level via Gráfico Pizza</b></li></c:if>
				<c:if test="${graphic != 'g7'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g7">Level via Gráfico Pizza</html:link></li></c:if>
				<c:if test="${graphic == 'g8'}"><li><b>Level via Gráfico TreeMap</b></li></c:if>
				<c:if test="${graphic != 'g8'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g8">Level via Gráfico TreeMap</html:link></li></c:if>
<!--				<c:if test="${graphic == 'g9'}"><li><b>Meta Alternativa</b></li></c:if>-->
<!--				<c:if test="${graphic != 'g9'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g9">Meta Alternativa</html:link></li></c:if>-->
				<c:if test="${graphic == 'g10'}"><li><b>Tempo x Level X Pontuação</b></li></c:if>
				<c:if test="${graphic != 'g10'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g10">Tempo x Level X Pontuação</html:link></li></c:if>
				<c:if test="${graphic == 'g11'}"><li><b>Tempo Total Jogado x Quantidade de Partidas</b></li></c:if>
				<c:if test="${graphic != 'g11'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g11">Tempo Total Jogado x Quantidade de Partidas</html:link></li></c:if>
				<c:if test="${graphic == 'g12'}"><li><b>Level x Pontuação</b></li></c:if>
				<c:if test="${graphic != 'g12'}"><li><html:link action="graphicGameActivity.do?method=showGraphicGame&gameId=${game.id}&graphic=g12">Level x Pontuação</html:link></li></c:if>

			</div>
			<div style="overflow-x: scroll; min-height: 400px;">
				<c:choose>
					<c:when test="${graphic == 'sel'}"></c:when>
					<c:when test="${graphic == 'g1'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/Grafico1.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/Grafico1.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g2'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoLine.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoLine.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g3' || graphic == 'g6' || graphic == 'g9'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoColuna.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoColuna.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g4' || graphic == 'g7'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoPizza.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoPizza.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g5' || graphic == 'g8'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoTreeMap.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoTreeMap.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g10'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoBolha.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoBolha.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>			
					</c:when>
					<c:when test="${graphic == 'g11'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoPlot.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoPlot.swf" quality="high" bgcolor="#869ca7"
			    	   			width="500" height="375" name="Grafico" align="middle"
				   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
		       					type="application/x-shockwave-flash"
		   						pluginspage="http://www.macromedia.com/go/getflashplayer">
		   					</embed>
						</object>	
					</c:when>
					<c:when test="${graphic == 'g12'}">
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		   	   				id="ExternalInterfaceExample" width="500" height="375"
		       		   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			   				<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoLine2.swf" />
		   					<param name="quality" value="high" />
		   					<param name="bgcolor" value="#869ca7" />
		   					<param name="allowScriptAccess" value="sameDomain" />
		      				<embed src="http://<%= request.getAttribute("domain") %>/GraficoLine2.swf" quality="high" bgcolor="#869ca7"
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