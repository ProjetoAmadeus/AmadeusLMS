/* * Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco * * Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS *  * O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como * publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a. *  * Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes. *   * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA. * *///Vari�veis globaisvar _loadTimer	= setInterval(__loadAnima,18);
var _loadPos	= 0;
var _loadDir	= 2;
var _loadLen	= 0;//Anima a barra de progresso
function __loadAnima(){
	var elem = document.getElementById("barra_progresso");
	if(elem != null){
		if (_loadPos==0) _loadLen += _loadDir;
		if (_loadLen>32 || _loadPos>79) _loadPos += _loadDir;
		if (_loadPos>79) _loadLen -= _loadDir;
		if (_loadPos>79 && _loadLen==0) _loadPos=0;
		elem.style.left		= _loadPos;
		elem.style.width	= _loadLen;
	}
}//Esconde o carregador
function __loadEsconde(){
	this.clearInterval(_loadTimer);
	var objLoader				= document.getElementById("carregador_pai");
	objLoader.style.display		="none";
	objLoader.style.visibility	="hidden";
}