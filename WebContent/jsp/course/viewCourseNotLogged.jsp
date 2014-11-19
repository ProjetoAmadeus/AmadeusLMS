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

<html>
<jsp:include page="/jsp/conf/header.jsp" />
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<div id="login">
			<dl id="formlogin">
				<html:form action="/validateLogin" focus="login" >				
		  		<dt><html:text property="login"  styleClass="inputlogin" size="15" maxlength="15" /> </dt>
      			<dt><html:password property="password" styleClass="inputlogin" size="15" maxlength="15" />&nbsp;
					<html:submit property="logonForm" styleClass="button"><bean:message key="general.submit"/></html:submit></dt>
	       		</html:form>
			</dl>
		  </div>
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="viewCourse.heading"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><bean:write name="course" property="name"/></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link forward="fProject"><bean:message key="institutionalLinks.project"/></html:link></li>
			    </ul>
			</div>
		</div>						
		<div id="pContent" class="pContent">
			<html:errors/>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.courseName"/>:
				</p>
				<p class="prvf">
					<bean:write property="name" name="course" filter="false"/>
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
				    <bean:message key="viewCourse.teachers"/>:
				</p>
				<p class="prvf">
					<logic:iterate id="teacher" name="teachers">
						<bean:define id="accessInfo" name="teacher" property="accessInfo"></bean:define>
						<a href="user.do?method=showViewPublicData&userId=<bean:write name="accessInfo" property="id"/>">
						<bean:write name="teacher" property="name" filter="false"/></a>
					</logic:iterate>
				</p>
			</div>
			<logic:notEmpty name="assistants">
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.assistant"/>
				</p>	
				<p class="prvf">
					<logic:iterate id="assistant" name="assistants">
						<bean:define id="accessInfo" name="assistant" property="accessInfo"></bean:define>
						<a href="user.do?method=showViewPublicData&userId=<bean:write name="accessInfo" property="id"/>">
				      	<bean:write name="assistant" property="name" filter="false"/></a><br />
					</logic:iterate>
				</p>
			</div>
			</logic:notEmpty>
			<div class="dfvf">
				<p class="plvf">
					<bean:message key="viewCourse.objectives"/>:
				</p>
				<p class="prvf">
					<bean:write property="objectives" name="course" filter="false"/>
				</p>
			</div>
			<div class="dsvf">
				<p class="plvf">
					<bean:message key="viewCourse.content"/>:
				</p>
				<p class="prvf">
					<bean:write property="content" name="course" filter="false"/>
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
				<c:forEach var="keyword" items="${keywords}" varStatus="status">
					<a href="fSearchCourse.do?keyword_course=<bean:write property="name" name="keyword"/>">
					<bean:write property="name" name="keyword" filter="false"/></a>
					<c:if test="${!status.last}">
						<c:out value=","/>
					</c:if>
				</c:forEach>
				</p>
			</div>				
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>	
</html>