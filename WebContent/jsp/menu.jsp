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
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html:html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
</head>
<body onload="countOnlineUser();">
<div id="pBody" class="pBody">
	<div id="pHeader" class="pHeader">
		<jsp:include page="/jsp/conf/login.jsp" />
	</div>
	<jsp:include page="/jsp/conf/logo.jsp" />
	<div class="pw">
		<div class="plw">
			<div id="youHave" class="youHave">
				<div id="youHaveTitle" class="youHaveTitle"><bean:message key="menu.youHave"/>:</div>
				<c:if test="${pendingTasks > 0}">
					<div id="youHavePendingTasks" class="youHavePendingTasks">
						<html:link action="user.do?method=showViewPendingTasks">(${pendingTasks})<bean:message key="sideMenu.tasks"/>.</html:link>
					</div>
				</c:if>
				<c:if test="${pendingTasks eq 0}">
					<div id="youHavePendingTasks" class="youHavePendingTasks">(0)&nbsp;<bean:message key="sideMenu.tasks"/>.</div>
	       	  	</c:if>
       	  		<div id="youHaveOnlineUser" class="youHaveOnlineUser">
       	  			<html:link action="user.do?method=showViewOnlineUsers">
  	      				(<span id="countOnlineUser">0</span>)&nbsp;<bean:message key="sideMenu.onlineUser"/>.
  	      			</html:link>
  	      		</div>
  	      	</div>	
       		<c:if test="${youCan}">
       		<div id="youCan" class="youCan">
				<div id="youCanTitle" class="youCanTitle"><bean:message key="menu.youCan"/>:</div>
				<div id="youCanInsertCourse" class="youCanInsertCourse">
					<html:link forward="fInsertCourseStepOne"><bean:message key="sideMenu.createNewCourse"/></html:link>
				</div>
				<c:if test="${user.typeProfile eq 'ADMIN'}">
				<div id="youCanManagerUser" class="youCanManagerUser">
					<html:link action="user.do?method=showViewAllUsersInManagerUsers">
						<bean:message key="general.managerUser"/>
					</html:link>
				</div>
				<div id="youCanManagerCourse" class="youCanManagerCourse">
					<html:link action="user.do?method=showViewAllCoursesInManagerUsers">
						<bean:message key="general.managerCourse"/>
					</html:link>
				</div>
				</c:if>
			</div>
       		</c:if>
			<div id="yourCourses" class="yourCourses">
				<div id="yourCoursesTitle" class="yourCoursesTitle"><bean:message key="courses.yourCourses"/>:</div>
				<logic:iterate id="course" name="userCourses">
		  			<div id="yourCourse" class="yourCourse">	
		  				<html:link action="course.do?method=showViewCourse&courseId=${course.id}"><bean:write name="course" property="name" filter="false"/></html:link>
					</div>
				</logic:iterate>
			</div>
			<div class="line"></div>
		</div>
		<div class="prw">
			<html:errors property="courseName" />
			<html:form action="/searchCourse" focus="courseName">
			<div class="pTitleSearchCourse">
				<bean:message key="courses.search"/>:
			</div>
			<div class="pSearchCourse">
				<html:text property="courseName" styleClass="formfield2" ></html:text>
				<html:submit property="searchCourse" styleClass="button"><bean:message key="courses.searchButton"/></html:submit>
			</div>
			</html:form>	
			<div class="pTitleKeyword">			
				<bean:message key="keywords.title"/>:
			</div>
			<div class="pTagcloud">
				<bean:write name="content" filter="false"/>
			</div>	
		</div>
	</div>
	<jsp:include page="/jsp/conf/footer.jsp" />       
</div>	
</body>
</html:html>