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
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/jquery.maskedinput-1.2.2.min.js"></script>
	<script type="text/javascript">
		$(function(){$('#birthDate').datepicker({changeMonth: true,changeYear: true, showAnim: 'scale'});});

		jQuery(function($){
		   $("#cpf").mask("999.999.999-99");
		   $("#phoneNumber").mask("(99)9999-9999");
		   $("#birthDate").mask("99/99/9999");
		});

		userStatus(${userProfile.id});
	</script>
</head>
<body>
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />      
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2><bean:message key="managerUsers.title"/></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu"><bean:message key="menu.name"/></html:link></li>
				<li><html:link action="user.do?method=showViewAllUsersInManagerUsers"><bean:message key="managerUsers.title"/></html:link></li>
				<li>Editar Perfil</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li><html:link action="user.do?method=showViewAllUsersInManagerUsers"><img src="themes/default/imgs/menu/users-16x16.png" border="0" title="<bean:message key="managerUsers.menu.usersSystem"/>" />&nbsp;<bean:message key="managerUsers.menu.usersSystem"/></html:link></li>
					<li><html:link action="user.do?method=showViewUserNewInManagerUsers"><img src="themes/default/imgs/menu/user-16x16.png" title="Nova Conta" border="0" />&nbsp;Nova Conta</html:link></li>
					<li><b><img src="themes/default/imgs/menu/user-edit-16x16.png" title="Editar Perfil" />&nbsp;Editar Perfil</b></li>
					<li><html:link action="user.do?method=showViewSendMailInManagerUsers"><img src="themes/default/imgs/menu/email-16x16.png" border="0" title="<bean:message key="managerUsers.menu.sendEmail"/>" />&nbsp;<bean:message key="managerUsers.menu.sendEmail"/></html:link></li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<html:errors />
			<html:form action="/editUserFormInManagerUsers" focus="name" enctype="multipart/form-data">
			<html:hidden property="method" value="userNewInManagerUsers"/>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr align="right">
					<td colspan="2">
					<html:link action="user.do?method=showViewUserProfileInManagerUsers&userId=${userProfile.id}">
						<img border="0" src="themes/default/imgs/menu/user-info-16x16.png" title="Perfil do Usuário" />
					</html:link>
					&nbsp;
					<html:link action="/user.do?method=showViewSendMailInManagerUsers&to=${userProfile.email}">
						<img border="0" src="themes/default/imgs/menu/email-16x16.png" title="Enviar E-mail" />
					</html:link>
					</td>
				</tr>
				<tr>
					<td width="125px;">
						<div class="normalPicture">
							<img class="normalPhoto" src="user.do?method=showPhoto&id=${userProfile.id}" />
						</div>
					</td>
					<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%;">
						<tr>
							<td style="width: 35px;" class="formAttribute">Status:</td>
							<td style="width: 100%;"><label id="userStatus${userProfile.id}" class="offlineUser" title="">&nbsp;</label></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td class="formAttribute"><bean:message key="general.login"/>:</td>
							<td><bean:write name="userProfile" property="login" filter="false"/></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td class="formAttribute">Tipo:</td>
							<td><bean:write name="userProfile" property="userType" filter="false"/></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr><td colspan="2">O Tipo e o Login não podem ser editados.</td></tr>
					</table>		
					</td>
				</tr>
				<tr><td colspan="2"><html:file property="image" /></td></tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute">*<bean:message key="editUserForm.fullName"/>:</td>
					<td><html:text name="userProfile" property="name" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.fullNameDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute">*<bean:message key="general.password"/>:</td>
					<td><html:password property="password" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="userRegistrationForm.passwordDescription"/></td>
				</tr>
				
				<tr>
					<td class="formAttribute">*<bean:message key="editUserForm.email" />:</td>
					<td><html:text name="userProfile" property="email" style="width: 88%;" />&nbsp;<img src="themes/default/imgs/menu/refresh-16x16.png" style="cursor: pointer;" title="Verificar se o e-mail está disponível." onclick="verifyEmail(${personId}, document.editUserFormInManagerUsers.email.value);" />
						<span id="emailResponse" class="emailResponseOK"></span>
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.emailDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.cpf" />:</td>
					<td><html:text name="userProfile" property="cpf" styleId="cpf" style="width: 100px;" onblur = "ValidarCPF(editUserFormInManagerUsers.cpf);" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.cpfDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute" align="right"><bean:message key="editUserForm.phoneNumber" />:</td>
					<td><html:text name="userProfile" property="phoneNumber" styleId="phoneNumber" style="width: 95px;" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.phoneNumberDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.gender" />:</td>
					<td>
						<html:radio name="userProfile" property="gender" value="M"></html:radio>
						<bean:message key="editUserForm.gender.male" />
						<html:radio name="userProfile" property="gender" value="F"></html:radio>
						<bean:message key="editUserForm.gender.female" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription">&nbsp;</td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.birthDate" />:</td>
					<td><html:text name="userProfile" styleId="birthDate" property="birthDate" styleClass="birthDate" style="width: 80px;" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.birthDateDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.stateCity" />:</td>
					<td>
					<html:select name="userProfile" property="state">
						<html:option value=""></html:option>
						<html:option value="AC">AC</html:option>
						<html:option value="AL">AL</html:option>
						<html:option value="AM">AM</html:option>
						<html:option value="AP">AP</html:option>
						<html:option value="BA">BA</html:option>
						<html:option value="CE">CE</html:option>
						<html:option value="DF">DF</html:option>
						<html:option value="ES">ES</html:option>
						<html:option value="GO">GO</html:option>
						<html:option value="MA">MA</html:option>
						<html:option value="MG">MG</html:option>
						<html:option value="MS">MS</html:option>
						<html:option value="MT">MT</html:option>
						<html:option value="PA">PA</html:option>
						<html:option value="PB">PB</html:option>
						<html:option value="PE">PE</html:option>
						<html:option value="PI">PI</html:option>
						<html:option value="PR">PR</html:option>
						<html:option value="RJ">RJ</html:option>
						<html:option value="RN">RN</html:option>
						<html:option value="RO">RO</html:option>
						<html:option value="RR">RR</html:option>
						<html:option value="RS">RS</html:option>
						<html:option value="SC">SC</html:option>
						<html:option value="SE">SE</html:option>
						<html:option value="SP">SP</html:option>
						<html:option value="TO">TO</html:option>
					</html:select> - <html:text name="userProfile" property="city" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.stateCityDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.degree" />:</td>
					<td>
					<html:select name="userProfile" property="degree" >
						<html:option value="">
							<bean:message key="editUserForm.degree.select" />
						</html:option>
						<html:option value="degree1">
							<bean:message key="editUserForm.degree.degree1" />
						</html:option>
						<html:option value="degree2">
							<bean:message key="editUserForm.degree.degree2" />
						</html:option>
						<html:option value="degree3">
							<bean:message key="editUserForm.degree.degree3" />
						</html:option>
						<html:option value="specialization">
							<bean:message key="editUserForm.degree.specialization" />
						</html:option>
						<html:option value="master">
							<bean:message key="editUserForm.degree.master" />
						</html:option>
						<html:option value="phd">
							<bean:message key="editUserForm.degree.phd" />
						</html:option>
						<html:option value="other">
							<bean:message key="editUserForm.degree.other" />
						</html:option>
					</html:select>
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.degreeDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.year" />:</td>
					<td>
					<html:select name="userProfile" property="year">
						<html:option value="" />
						<html:options collection="years" property="value" name="value"/>
					</html:select>
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.yearDescription" /></td>
				</tr>
				
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.instituition" />:</td>
					<td><html:text name="userProfile" property="instituition" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.instituitionDescription" /></td>
				</tr>
				
				<tr>
					<td  class="formAttribute"><bean:message key="editUserForm.description" />:</td>
					<td><html:textarea name="userProfile" property="description" style="width: 100%;"></html:textarea></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.descriptionDescription" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><html:submit property="insertUser" styleClass="button"><bean:message key="general.register"/></html:submit></td>
				</tr>
			</table>					
			</html:form>
		</div>
		<div id="pRightMenu" class="pRightMenu">
		
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>