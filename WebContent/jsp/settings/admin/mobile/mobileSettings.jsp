<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store" />
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/settings.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#tabs").tabs({
				
			});
		});
	</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Configurações do Sistema</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li>Configurações do Sistema Mobile</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">			
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li>
						<img border="0" src="themes/default/imgs/menu/mobile.png" />&nbsp;<b>Mobile</b>
					</li>
					<li>
						<html:link href="settingsActions.do?method=showViewSettings"><img src="themes/default/imgs/menu/web.png"/>&nbsp;Web</html:link>
					</li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent2">
<fieldset>
   <legend><b>Configurações do Human Mobile</b></legend>
   <div class="lineBlank"></div>
   <form id="formMobileSetting">
   <table>
   		<tr>
   			<td align="right" style="font-size: 12px;" ><label class="labelAttribute"><bean:message key="settings.mobile.sms-data.login"/>:</label></td>
   			<td><input type="text" name="login" value="${mobileSettings.login}" style="width: 250px;" /></td>
   		</tr>
   		<tr>
   			<td align="right" style="font-size: 12px;" ><label class="labelAttribute"><bean:message key="settings.mobile.sms-data.email"/>:</label></td>
   			<td><input type="text" name="email" style="width: 250px;" value="${mobileSettings.email}" />&nbsp;&nbsp;</td>
   		</tr>
   		<tr>
   			<td align="right" style="font-size: 12px;" ><label class="labelAttribute"><bean:message key="settings.mobile.sms-data.password"/>:</label></td>
   			<td><input type="password" name="password" style="width: 250px;" value="" /></td>
   		</tr>
   		</table>
   		<table>
   		<tr>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Material SMS:</label></td>
   			<td> <input type="checkbox" name="smsMaterial" value="smsMaterial" <c:if test="${mobileSettings.smsMaterial eq 'on'}">checked</c:if>/> </td>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Video SMS:</label></td>
   			<td> <input type="checkbox" name="smsVideo" value="smsVideo" <c:if test="${mobileSettings.smsVideo eq 'on'}">checked</c:if>/> </td>
   		</tr>
   		<tr>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Enquete SMS:</label></td>
   			<td> <input type="checkbox" name="smsPoll" value="smsPoll" <c:if test="${mobileSettings.smsPoll eq 'on'}">checked</c:if>/> </td>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Forum SMS:</label></td>
   			<td> <input type="checkbox" name="smsForum" value="smsForum" <c:if test="${mobileSettings.smsForum eq 'on'}">checked</c:if>/> </td>
   		</tr>
   		<tr>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Game SMS:</label></td>
   			<td> <input type="checkbox" name="smsGame" value="smsGame" <c:if test="${mobileSettings.smsGame eq 'on'}">checked</c:if>/> </td>
   			<td align="left" style="font-size: 12px;" ><label class="labelAttribute">Midia SMS:</label></td>
   			<td> <input type="checkbox" name="smsLearningObject" value="smsLearningObject" <c:if test="${mobileSettings.smsLearningObject eq 'on'}">checked</c:if>/> </td>
   		</tr>
   </table>
   </form>
   <img id="imgMobile" border=0 src="<%=request.getContextPath()%>/themes/default/imgs/ajax-loader-activity.gif" style="display: none; margin-left: 200px;" />
   <div class="lineBlank"></div>
   <div class="lineSettings">
	<input type="button" value="Salvar Modificações" onclick="saveMobileSettings();" />
   </div>
</fieldset>
	</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>