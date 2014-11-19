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
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript" src="js/tiny_mce/tiny_mce.js"></script>
	<script type="text/javascript">
	<c:if test="${success}">
		window.setTimeout(function() {
			success("Email enviado com sucesso!");
	    }, 1000);
	</c:if>

		// O2k7 skin (silver)
		tinyMCE.init({
			// General options
			mode : "exact",
			elements : "message",
			theme : "advanced",
			skin : "o2k7",
			skin_variant : "silver",
			plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
	
			// Theme options
			theme_advanced_buttons1 : "print,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,fontselect,fontsizeselect",
			theme_advanced_buttons2 : "bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,code,|,forecolor,backcolor,|,hr",
	        theme_advanced_buttons3 : "",
	        theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : false,
	
			// Replace values for the template plugin
			template_replace_values : {
				username : "Some User",
				staffid : "991234"
			}
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
			<h2>Gerenciar Usuários</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="course.do?method=showViewCourse&courseId=${course.id}">${course.name}</html:link></li>
				<li>Enviar Email</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><b><img src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />&nbsp;Enviar Email</b></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent2">
			<form id="formSendMail" name="formSendMail" action="course.do" method="post">
			<input type="hidden" name="method" value="sendMailForCourseParticipants"/>
			<input type="hidden" name="courseId" value="${course.id}"/>
			<table width="100%" border="0" cellspacing="0">
				<tr><td>&nbsp;</td>
				<td><div id="infoMessage" class="msgBoxSuccess" style="display: none;"></div></td></tr>
				<tr>
					<td style="padding-bottom: 10px;" class="formAttribute">Para:</td>
					<td style="padding-bottom: 10px;">O curso <bean:write name="course" property="name" filter="false" /></td>
				</tr>
				<tr style="padding-bottom: 10px;">
					<td style="padding-bottom: 10px;" class="formAttribute">Assunto:</td>
					<td style="padding-bottom: 10px;"><input type="text" id="subject" name="subject" style="width: 435px;" value="${subject}"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td style="padding-top: 5px;">
						<textarea id="message" name="message" style="width: 100%; height: 300px;">${message}</textarea>
					</td>
				</tr>
				<tr>
					<td height="40px;">&nbsp;</td>
					<td><input type="submit" value="Enviar"/></td>
				</tr>
			</table>
			<br />
			</form>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>