<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input id="gridData" type="hidden" value="${data}"/>
<c:if test="${type == 'grupo' || type == 'individual'}">
	<div id="GridGame">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="ExternalInterfaceExample" width="500" height="375"
			codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			<param name="movie" value="http://<%= request.getAttribute("domain") %>/GameGrid.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7"/>
			<param name="allowScriptAccess" value="sameDomain" />
			<param name="name" value="GameGrid"/> 
			<embed src="http://<%= request.getAttribute("domain") %>/GameGrid.swf" quality="high" bgcolor="#869ca7"
				width="340" height="200" name="GameGrid" align="middle"
				play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
			   	type="application/x-shockwave-flash"
				pluginspage="http://www.macromedia.com/go/getflashplayer">
			</embed>
		</object>		
	</div>
</c:if>
<c:if test="${type == 'visualizacao'}">
	<div>
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="ExternalInterfaceExample" width="340" height="200"
	   		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			<param name="movie" value="http://<%= request.getAttribute("domain") %>/GraficoColuna2.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="http://<%= request.getAttribute("domain") %>/GraficoColuna2.swf" quality="high" bgcolor="#869ca7"
   	   			width="340" height="200" name="GameGrid" align="middle"
   	   			play="true" loop="false" quality="high" allowScriptAccess="sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.macromedia.com/go/getflashplayer">
			</embed>
		</object>			
	</div>
</c:if>
