<!--
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="module" items="${modules}" varStatus="status">
	<c:set var="positionModule" value="${positionModule + 1}"/>
	<c:if test="${ user.typeProfile eq 'ADMIN' || !(user.typeProfile eq 'STUDENT' && !module.visible) }">
		<div id="module${positionModule}" class="modBody">
		<c:set var="module" scope="request" value="${module}" />
		<jsp:include page="/jsp/course/content_management/module.jsp" />
		</div>
	</c:if>
</c:forEach>
<c:if test="${!(userRoleInCourse eq 'STUDENT')}">
	<c:set var="lastPositionModule" value="${ fn:length(modules) + 1}" scope="request"/>
	<c:set var="idCourse" value="${course.id}" scope="request"/>
	<jsp:include page="/jsp/course/content_management/clickNewModule.jsp" />
</c:if>