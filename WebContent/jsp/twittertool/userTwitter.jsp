<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<!-- 
	Arquivo criado por Nailson Cunha para exibição do twitter do usuário no Amadeus.
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="user-twitter">
	<c:if test="${ empty user.person.twitterLogin }">
		<p class="no-user-twitter">
			Adicione seu Twitter ao seu perfil <a href="/amadeuslms/user.do?method=showViewIntegrationSocialNetworks"><b>aqui</b></a>.
		</p>
	</c:if>
	<c:if test="${ ! empty user.person.twitterLogin }">

		<div id="twitter">
			<h2>Seus Ultimos Tweets</h2>
			<ul id="twitter_update_list"></ul>
		</div>
		 

		<script type="text/javascript"
			src="http://twitter.com/javascripts/blogger.js"></script>
		<script type="text/javascript"
			src="https://api.twitter.com/1/statuses/user_timeline.json?screen_name=${user.person.twitterLogin}&callback=twitterCallback2&count=4"></script>
	</c:if>
</div>