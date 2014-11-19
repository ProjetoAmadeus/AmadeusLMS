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
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>	

<html>
<head>
	<%   
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
		response.setHeader("Pragma","no-cache"); //HTTP 1.0   
		response.setDateHeader ("Expires", 0); //nao salva cache no servidor proxy   
	%> 
	
	<title>Iriz</title>
    <link href="<html:rewrite page="/themes/default/css/iriz.css" />" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
	    function changePerception(img, idMood){
	    	document.getElementById("perceptionSelected").innerHTML = "<img src='<%=request.getContextPath()%>/themes/default/imgs/iriz/"+img+"'/>";
	    	changeMoodChat(idMood);
	    }

	    function changeColorButton(color) {
		    red = "<img src=\"<%=request.getContextPath()%>/themes/default/imgs/iriz/enviar-red.png\" width=\"64\" height=\"68\" border=\"0\" />";
		    white = "<img src=\"<%=request.getContextPath()%>/themes/default/imgs/iriz/enviar-white.png\" width=\"64\" height=\"68\" border=\"0\" />";
			green = "<img src=\"<%=request.getContextPath()%>/themes/default/imgs/iriz/enviar-green.png\" width=\"64\" height=\"68\" border=\"0\" />";
		    
			if(color == "red"){
				document.getElementById("aBtnSend").innerHTML = red;
			}else if(color == "white"){
				document.getElementById("aBtnSend").innerHTML = white;
			}else if(color == "green"){
				if(document.getElementById("chatInput").value != "" && document.getElementById("aBtnSend").innerHTML.toLowerCase() != green.toLowerCase()) { 
					document.getElementById("aBtnSend").innerHTML = green;
				}else if(document.getElementById("chatInput").value == ""){
					changeColorButton("white");
				}
			}
		}
    </script>
    <script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
    <script type="text/javascript" src="js/videoChat.js"></script>
</head>
	<body onload="sendMsgForServer(); time('loading();',3000);" onunload="logoff();">
		<div id="loading">
			<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
			<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/ajax-loader.gif"/>&nbsp;<bean:message key="videoChat.connecting"/>
		</div>
		<div id="player">
			<!-- You Tube -->
			<object width="560" height="345">
        		<param name="movie" value="http://www.youtube.com/v/${param.idVideo}&hl=pt-br&fs=1&rel=0"></param>
        		<param name="allowFullScreen" value="true"></param>
        		<param name="allowscriptaccess" value="always"></param>
        		<embed src="http://www.youtube.com/v/${param.idVideo}&hl=pt-br&fs=1&rel=0" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="560" height="345"></embed>
        	</object>
			<!-- You Tube -->
		</div>
	
		<div id="chat">
			<!-- Chat -->
			<div id="chat-txt">
			</div>
			<div id="chatContainer">
			<div id="chat-input">
			<textarea name="chatInput" id="chatInput" wrap="physical" onkeypress="return enterButton(event);" onkeyup="changeColorButton('green');" ></textarea>
			</div>
			<div id="btnEnviar"><a href="#" onclick="sendMsgChat();" onmousedown="changeColorButton('red');" onmouseup="changeColorButton('white'); sendMsgChat();" onkeypress="changeColorButton('red');" onkeyup="changeColorButton('green');" id="aBtnSend"><img src="<html:rewrite page="/themes/default/imgs/iriz/enviar-white.png" />" width="64" height="68" border="0" /></a></div>
			</div>
			
			<!-- Chat -->
		</div>
	
		<div id="users">
			<!-- lista de usuários -->
			
			<!-- lista de usuários -->
		</div>
		<div id="perception">
			<div id="perceptionSelector">
				<div id="perceptionSelected"><img src="<html:rewrite page="/themes/default/imgs/iriz/emoticon02-96x96.png" />" /></div>
			  <a href="#" onclick="changePerception('emoticon01-96x96.png','1');"><img src="<html:rewrite page="/themes/default/imgs/iriz/emoticon01-24x24.png" />" border="0" /></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="changePerception('emoticon02-96x96.png','2');"><img src="<html:rewrite page="/themes/default/imgs/iriz/emoticon02-24x24.png" />" width="24" height="24" border="0" /></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="changePerception('emoticon03-96x96.png','3');"><img src="<html:rewrite page="/themes/default/imgs/iriz/emoticon03-24x24.png" />" border="0" /></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="changePerception('emoticon04-96x96.png','4');"><img src="<html:rewrite page="/themes/default/imgs/iriz/emoticon04-24x24.png" />" border="0" /></a></div>
		</div>        
	</body>
</html>
