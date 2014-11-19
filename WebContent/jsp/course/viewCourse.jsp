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
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<logic:notPresent name="user"> 
	<logic:redirect action="course.do?method=showViewCourseNotLogged&courseId=${course.id}"/>			
</logic:notPresent>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/managementCourse.js"></script>
	<link href="themes/default/css/table.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="viewCourse.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li>${course.name}</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="1" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>						
		<div id="pContent" class="pContent">
			<c:if test="${user.typeProfile eq 'ADMIN' || userRoleInCourse eq 'TEACHER' || userRoleInCourse eq 'ASSISTANT'}">
			<table border="0" cellpadding="0" cellspacing="0" width="100%;" style="margin-bottom: 10px;">
				<tr align="right">
					<td>
						<html:link action="/course.do?method=showViewSendMail&courseId=${course.id}">
						<img border="0" src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />
						</html:link>
					</td>
				</tr>
			</table>
			</c:if>
			<html:form action="/viewCourse">
			<html:errors/>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.courseName"/>:
				</p>
				<p class="prvf">
					<bean:write property="name" name="course" filter="false" />
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
				    <bean:message key="viewCourse.teachers"/>:
				</p>
				<logic:iterate id="teacher" name="teachers">
					<bean:define id="accessInfo" name="teacher" property="accessInfo"></bean:define>
					<p class="prvf">
					<c:if test="${user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR'}">
						<label id="userStatus${accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;
						<a href="user.do?method=showViewMyProfile&userId=<bean:write name="accessInfo" property="id"/>">
						<bean:write property="name" name="teacher" filter="false" /></a>
						<script type="text/javascript">userStatus(${accessInfo.id});</script>
					</c:if>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR')}">
						<label id="userStatus${accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;
						<a href="user.do?method=showViewPublicData&userId=<bean:write name="accessInfo" property="id"/>">
						<bean:write property="name" name="teacher" filter="false" /></a>
						<script type="text/javascript">userStatus(${accessInfo.id});</script>
					</c:if>
					</p>
				</logic:iterate>
			</div>
			<logic:notEmpty name="assistants">
	 		<div class="dsvf">	
 				<p class="plvf">
					<bean:message key="viewCourse.assistant"/>:
				</p>
					<p class="prvf">
				<logic:iterate id="assistant" name="assistants">
					<bean:define id="accessInfo" name="assistant" property="accessInfo"></bean:define>
		  			<c:if test="${user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR'}">
		  				<label id="userStatus${accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;
  						<a href="user.do?method=showViewMyProfile&userId=<bean:write name="accessInfo" property="id"/>">
  						<bean:write property="name" name="assistant" filter="false" /></a>
  						<script type="text/javascript">userStatus(${accessInfo.id});</script>
  						<br />
					</c:if>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR')}">
						<label id="userStatus${accessInfo.id}" class="offlineUser" title="">&nbsp;</label>&nbsp;
						<a href="user.do?method=showViewPublicData&userId=<bean:write name="accessInfo" property="id"/>">
						<bean:write property="name" name="assistant" filter="false" /></a>
						<script type="text/javascript">userStatus(${accessInfo.id});</script>
						<br />
					</c:if>
				</logic:iterate>
					</p>
			</div>
			</logic:notEmpty>					
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.objectives"/>:
				</p>
				<p class="prvf">
					<bean:write property="objectives" name="course" filter="false" />
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.content"/>:
				</p>
				<p class="prvf">
					<bean:write property="content" name="course" filter="false" />
				</p>
			</div>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.maxAmountStudents"/>
				</p>
				<p class="prvf">
					<bean:write property="maxAmountStudents" name="course" />
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.numberOfStudentsInCourse"/>
				</p>
				<p class="prvf">
					<bean:write property="numberOfStudentsInCourse" name="course" />
				</p>
			</div>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.viewInitialRegistrationDate"/>
				</p>
				<p class="prvf">
					<bean:write property="initialRegistrationDate" name="course" format="EEEE, d MMMM yyyy"/>
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.viewFinalRegistrationDate"/>
				</p>
				<p class="prvf">
					<bean:write property="finalRegistrationDate" name="course" format="EEEE, d MMMM yyyy"/>
				</p>
			</div>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.viewInitialCourseDate"/>
				</p>
				<p class="prvf">
					<bean:write property="initialCourseDate" name="course" format="EEEE, d MMMM yyyy"/>
				</p>
			</div>	
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.viewFinalCourseDate"/>
				</p>
				<p class="prvf">
					<bean:write property="finalCourseDate" name="course" format="EEEE, d MMMM yyyy"/>
				</p>
			</div>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.keywords"/>
				</p>
				<p class="prvf">
				<c:forEach var="keyword" items="${keywords}" begin="0" varStatus="status">
					<a href="fSearchCourse.do?keyword_course=<bean:write property="name" name="keyword"/>">
					<bean:write property="name" name="keyword" filter="false" /></a>
					<c:if test="${!status.last}">
						<c:out value=","/>
					</c:if>
				</c:forEach> 
				</p>
			</div>	
			<c:if test="${canRegisterUser}">
				<html:hidden property="id" name="course"/>
				<dt class="field"><html:submit property="viewCourse" styleClass="button"><bean:message key="viewCourse.registration"/></html:submit></dt>
			</c:if>
			</html:form>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
</html>