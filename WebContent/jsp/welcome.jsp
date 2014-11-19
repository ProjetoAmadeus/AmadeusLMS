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
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript">
		window.history.forward(-1);
	</script>
</head>
<body>
<input type="hidden" id="keyUserNotLogged" value="<ajklsdjfiei78324yhre8yds979873w78jklsdkfjsdlkfjlksdfjklksdflk9usdf98723uoudsfjk348987>" />
<input type="hidden" id="systemVersion" value="${webSettings.systemGeneralVersion}" />
<div id="pBody" class="pBody">
	<div id="pHeader" class="pHeader"></div>
	<jsp:include page="/jsp/conf/logo.jsp" />
	<div class="pw">
		<div class="plw">
			<div class="invalidLogin">
				<html:errors property="invalidLogin" />
				<html:errors property="login" />
				<html:errors property="password" />
				<html:errors property="inactive" />
			</div>
			<div class="dLogin">
				<div class="ldtf"></div><div class="ldtc"></div><div class="ldtr"></div><div class="ldcf"></div>
				<div class="ldcc">
					<html:form action="/validateLogin" focus="login">
						<div class="dLogin">
							<html:text property="login" styleClass="inputlogin" maxlength="150" />
						</div>
		      			<div class="dPassword">
		      				<html:password property="password" styleClass="inputlogin" maxlength="15" />
		      			</div>
		      			<div id="dInputLoginOpenID" class="dInputLoginOpenID">
		      				<input type="text" id="identifier" class="inputLoginOpenID" maxlength="150" />
		      				<a href="#" onclick="requestOpenID('openIDActions.do?method=openIDRequest&identifier=')";>GO</a>
		      			</div>
	        			<div id="openIDLogin" class="openIDLogin">
		      				<div class="enter"><html:submit property="logonForm" styleClass="buttonEnter"><bean:message key="general.submit"/></html:submit></div>
		      				<div class="or"><bean:message key="general.or"/></div> 
			        		<div class="opendIDGoogle" title="<bean:message key="openID.googleAccount"/>" onclick="requestGoogleOpenID('openIDActions.do?method=googleAccountsRequest');"></div>
		        		</div>
		        		<div id="remindPassword" class="remindPassword">
	        				<html:link forward="fRemindPassword" styleClass="insert"><bean:message key="remindPassword.heading"/></html:link> &nbsp;      
	       		    		<c:if test="${webSettings.securityAutoSigning}">
	       		    		<html:link action="user.do?method=showViewInsertUser" styleClass="insert"><bean:message key="userRegistrationForm.heading"/></html:link>
	       		    		</c:if>
        		 		</div>
		        	</html:form>
			    </div>
				<div class="ldcr"></div><div class="ldbf"></div><div class="ldbc"></div><div class="ldbr"></div>
			</div>
		</div>
		<div class="prw">
			<html:errors property="courseName" />
			<html:form action="/searchCourse" focus="courseName" >
			<div class="pTitleSearchCourse">
				<bean:message key="courses.search"/>:
			</div>
			<div class="pSearchCourse">
				<html:text property="courseName" styleClass="formfield2" ></html:text>
				<html:submit property="searchCourse" styleClass="button"><bean:message key="courses.searchButton"/></html:submit>
			</div>
			</html:form>
			<div class="pTitleKeyword">
				<bean:message  key="keywords.title"/>:
			</div>
			<div class="pTagcloud">
				<bean:write name="content" filter="false"/>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/conf/footer.jsp" />
</div>
</body>
</html>