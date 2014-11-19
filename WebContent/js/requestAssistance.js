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

var msgCancel;
var msgApprove;

var lastRequest = null;

function ajaxLoadingConfig(div, innerHTML) {
	document.getElementById(div).innerHTML = innerHTML;
  	document.getElementById(div).style.textAlign = "center";
  	document.getElementById(div).style.color = "#2f7445"; 
  	document.getElementById(div).style.fontWeight = "bolder";
}

function init(approve, cancel) {
	msgCancel = cancel;
	msgApprove = approve;
	pinballEffect();
}

function showDetails(requestId) {
	if (lastRequest != null)
		hideDetails(lastRequest);
	lastRequest = requestId;
	
	document.getElementById('request'+requestId).className = "requestExpanded";
	document.getElementById('reqInfo'+requestId).style.display = "BLOCK";
	document.getElementById('reqBtns'+requestId).style.display = "BLOCK";
}
function hideDetails(requestId) {
	document.getElementById('request'+requestId).className = "requestColapsed";
	document.getElementById('reqInfo'+requestId).style.display = "NONE";
	document.getElementById('reqJust'+requestId).style.display = "NONE";
	document.getElementById('reqBtns'+requestId).style.display = "NONE";

	document.getElementById('reqJustification'+requestId).value = '';
	document.getElementById('reqBtnLeft'+requestId).blur();
	document.getElementById('reqBtnLeft'+requestId).value = msgApprove;
	document.getElementById('reqBtnLeft'+requestId).onclick = function() {approveRequest(requestId)};	
	document.getElementById('reqBtnRight'+requestId).onclick = function() {showJustBox(requestId)};
}

function showJustBox(requestId) {
	document.getElementById('reqInfo'+requestId).style.display = "NONE";
	document.getElementById('reqJust'+requestId).style.display = "BLOCK";

	document.getElementById('reqJustification'+requestId).focus();
	document.getElementById('reqBtnLeft'+requestId).value = msgCancel;
	document.getElementById('reqBtnLeft'+requestId).onclick = function() {showDetails(requestId)};	
	document.getElementById('reqBtnRight'+requestId).onclick = function() {disaproveRequest(requestId)};
}

function approveRequest(requestId, innerHTML) {
	var cb = {callback:function(data) {requestServerResponse(requestId, data);}};

	UtilDWR.getInclude('/approveAssistanceRequest.do?parameter=Aprovar&userRequestId='+requestId,
 		function(data) {
 		dwr.util.setValue('request'+requestId, null, { escapeHtml:false });
		document.getElementById('request'+requestId).className = "NONE";
		lastRequest = null;
  		}
  	);

}
function disaproveRequest(requestId) {
	var just = document.getElementById('reqJustification'+requestId).value;
	var cb = {callback:function(data) {requestServerResponse(requestId, data);}};
	if (just == "") {alert("Voc� deve escrever uma justificativa");
	}else{
	
	UtilDWR.getInclude('/disapproveAssistanceRequest.do?parameter=Reprovar&userRequestId='+requestId+'&justification='+just,
 		function(data) {
 		dwr.util.setValue('request'+requestId, null, { escapeHtml:false });
		document.getElementById('request'+requestId).className = "NONE";
		lastRequest = null;
  		}
  	);	

	}
}
function requestServerResponse(requestId, data) {
	if (data != null) {
		alert(data);
	} else {
		$('request'+requestId).style.display = 'none';
	}
}

// ## PinBall Effect ## //

var lastElement = null;
var W3CDOM = (document.createElement && document.getElementsByTagName);

function pinballEffect() {
	if (!W3CDOM) return;
	var allElements = document.getElementsByTagName('div');
	var originalBackgrounds=new Array();
	for (var i=0; i<allElements.length; i++) {
		if (allElements[i].className.indexOf('pinball-scoop') !=-1) {
			pinballAddEvents(allElements[i]);
		}
	}
}

function mouseGoesOver() {
	originalClassNameString = this.className;
	this.className += " pinball-on";
	this.style.cursor = "pointer";
	//this.style.cursor = "hand";
}

function mouseGoesOut() {
	this.className = originalClassNameString;
}

function mouseGoesClick() {
	var allThisAreasElements = this.getElementsByTagName('*');
	for (var j=0; j<allThisAreasElements.length; j++) {
		if (allThisAreasElements[j].className.indexOf('pinball-sinkhole') != -1) {
			if (lastElement != null)
				pinballAddEvents(lastElement);

			lastElement = this;
			allThisAreasElements[j].onclick();
			originalClassNameString = this.className;
			pinballRemoveEvents(this);
		}
	}
}

function pinballAddEvents(element) {
	element.onmouseover = mouseGoesOver;
	element.onmouseout = mouseGoesOut;
	element.onclick = mouseGoesClick;
}
function pinballRemoveEvents(element) {
	element.onmouseover = function() {};
	element.onmouseout = function() {};
	element.onclick = function() {};
	element.style.cursor = 'default';
}