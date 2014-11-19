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
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<html>
<head>
	<jsp:include page="/jsp/conf/header.jsp" />
	<script type="text/javascript" src="js/jquery.maskedinput-1.2.2.min.js"></script>
	<script type="text/javascript">
		$(function(){$('#birthDate').datepicker({changeMonth: true,changeYear: true, showAnim: 'scale'});});

		jQuery(function($){
		   $("#cpf").mask("999.999.999-99");
		   $("#phoneNumber").mask("(99)9999-9999");
		   $("#birthDate").mask("99/99/9999");
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
			<h2><bean:message key="editUserForm.title" /></h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu">
					<bean:message key="menu.name" />
				</html:link></li>
				<li><bean:message key="editUserForm.title" /></li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<div id="side_menu_1">
				<ul id="menu_sessoes">
					<li>
						<html:link action="user.do?method=showViewMyProfile">
							<img src="themes/default/imgs/menu/user-info-16x16.png" border="0" title="Meu Perfil" />&nbsp;<bean:message key="userPrivateData.title2"/>
						</html:link>
					</li>
					<li>
						<img src="themes/default/imgs/menu/user-edit-16x16.png" border="0" title="Editar Perfil" />&nbsp;<b><bean:message key="editUser.heading"/></b>
					</li>
					<li>
						<html:link action="user.do?method=showViewEditPassword">
							<img src="themes/default/imgs/menu/user-password-16x16.png" border="0" title="Trocar Senha" />&nbsp;<bean:message key="editPassword.heading"/>
						</html:link>
					</li>
					<c:if test="${!(user.typeProfile eq 'ADMIN' || user.typeProfile eq 'PROFESSOR') && canRequestTeaching}">
				   	<li>
				   		<html:link action="user.do?method=showViewTeachingRequest">
				   			<img src="themes/default/imgs/menu/user-card-16x16.png" border="0" title="Solicitar Docência" />&nbsp;<bean:message key="teachingRequest.heading"/>
				   		</html:link>
				   	</li>
					</c:if>
					<li>
						<html:link action="user.do?method=showViewClassMates">
							<img src="themes/default/imgs/menu/user-group-16x16.png" border="0" title="Colegas de Sala" />&nbsp;<bean:message key="sideMenu.classmates"/>
						</html:link>
					</li>
	         		<li>
	         			<html:link action="openIDActions.do?method=showViewManagerOpenIDs">
	         				<img src="themes/default/imgs/menu/openid.png" border="0" title="Google Account"/>&nbsp;Google Account
		        		</html:link>
		        	</li>
			    </ul>
			</div>
		</div>
		<div id="pContent" class="pContent">
			<html:errors />
			<html:form action="/editUser" focus="name" enctype="multipart/form-data">
			<html:hidden name="userProfile" property="id"/>
			<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td width="125px;">
						<div class="normalPicture">
							<img class="normalPhoto" src="user.do?method=showPhoto" />
						</div>
					</td>
					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%;">
							<tr>
								<td></td>
								<td></td>
							</tr>
						</table>		
					</td>
				</tr>
				<tr><td colspan="2"><html:file property="image" /></td></tr>
			
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td class="formAttribute">*<bean:message key="editUserForm.fullName" />:</td>
					<td>
						<html:text name="userProfile" property="name" style="width: 100%;" />
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.fullNameDescription" /></td>
				</tr>	
						
				<tr>
					<td class="formAttribute">*<bean:message key="editUserForm.cpf" />:</td>
					<td><html:text name="userProfile" property="cpf" styleId="cpf" style="width: 100px;" onblur="ValidarCPF(editUserFormInManagerUsers.cpf);" /></td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.cpfDescription" /></td>
				</tr>
					
				<tr>
					<td class="formAttribute"><bean:message key="editUserForm.phoneNumber" />:</td>
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
					<td class="formAttribute">*<bean:message key="editUserForm.email" />:</td>
					<td>
						<html:text name="userProfile" property="email" style="width: 88%;" />&nbsp;<img src="themes/default/imgs/menu/refresh-16x16.png" style="cursor: pointer;" title="Verificar se o e-mail está disponível." onclick="verifyEmail(${userProfile.id}, document.editUserForm.email.value);" />
						<span id="emailResponse" class="emailResponseOK"></span>
					</td>
				</tr>
				<tr>
					<td></td><td class="formDescription"><bean:message key="editUserForm.emailDescription" /></td>
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
					<td colspan="2" align="right">
						<html:submit property="editUser" styleClass="button"><bean:message key="editUserForm.edit" /></html:submit>
						<html:submit property="editUser" styleClass="button"><bean:message key="general.cancel" /></html:submit>
					</td>
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