/*
 * Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 *
 * Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 * 
 * O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
 * publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 * 
 * Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 *  
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 *
 */

var chatRoom = "";

function trim(str) {
	str = str.replace( /^\s+/g, '' );
	return str.replace( /\s+$/g, '' );
}

function ajaxReverseSendMsg(room, hora, name, msg, cssText) {
	if(chatRoom == room) {
		document.getElementById("chat-txt").innerHTML = document.getElementById("chat-txt").innerHTML +"<dt class=\""+cssText+"\"><span class=\"timestamp\">["+hora+"] </span>"+name+"</dt><dd class=\""+cssText+"\">"+msg+"</dd>";
		document.getElementById('chat-txt').scrollTop = 1000000;
	}
}

function ajaxReverseSendUserList(userList) {
	var users = new Array(); 
	users =	userList.split(',');
	
	if(chatRoom == "") {
		chatRoom = users[0];
	}
	
	if(chatRoom == users[0]) {
		userTextList = "<h1>Usu&aacute;rios</h1><ul>";
		
		for(i = 1; i < users.length; i++){
			var user = users[i].substring(0,users[i].length-1);
			var mood = users[i].substring(users[i].length-1,users[i].length);
			
			userTextList += "<li id='user_"+user+"' class='perception"+mood+"'><a href='#'>"+user+"</a></li>";
		}
		
		userTextList += "</ul>";
			
		document.getElementById("users").innerHTML = userTextList;
	}
}

function ajaxReverseChangeMood(room, user, idMood){
	if(chatRoom == room) {
		document.getElementById("user_"+user).className = "perception"+idMood;
	}
}

function ajaxReverseLogoffChat(room, user){
	if(chatRoom == room) {
		document.getElementById("user_"+user).style.display = "none";
	}
}

function sendMsgChat(){
	var msgChat = trim(dwr.util.getValue("chatInput"));
	dwr.util.setValue("chatInput","");

	if(msgChat != ""){
		UtilDWR.getInclude('/videoChat.do?method=sendMsgChat&msgChat='+msgChat,
			function(data) {
			}
		);
	}
}

function logoffChat(){
	UtilDWR.getInclude('/videoChat.do?method=logoffChat',
		function(data) {
	  	}
	);
}

function getMsgChat(){
	UtilDWR.getInclude('/videoChat.do?method=getMsgChat',
		function(data) {
			if(data == "close") {
				window.close();
			} else if(data != "") {
				ajaxReverseSendUserList(data);
			}
	  	}
	);
}

function changeMoodChat(idMood){
	UtilDWR.getInclude('/videoChat.do?method=changeMood&idMood='+idMood,
		function(data) {
		}
	);
}

function sendMsgForServer(){
	getMsgChat();
	setTimeout('sendMsgForServer();',1000);
}

function enterButton(event){
	if(event.keyCode == 13){
		sendMsgChat();
		return false;
	}
}

function logoff() {
	logoffChat();
}

function loading() {
	document.getElementById("loading").style.display = "none";
	document.getElementById("player").style.display = "block";
}

function time(func,t) {
	setTimeout(func,t);
}