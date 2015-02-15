<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<logic:notPresent name="user">
	<logic:redirect action="system.do?method=showViewWelcome" />
</logic:notPresent>

<%@page import="br.ufpe.cin.amadeus.amadeus_web.syncronize.*"%>
<%@page import="antlr.collections.List"%><html
	xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store" />
<jsp:include page="/jsp/conf/header.jsp" />
	<link href="themes/default/css/displaytag.css" rel="stylesheet" type="text/css"></link>
	<link href="themes/default/css/groups.css" rel="stylesheet" type="text/css"></link>
	<script>
		function habilitarDesabilitarCriarGrupo()
		{
			var flag_habilitar_grupo = document.getElementById("inputCourseFlag_habilitar_grupo").value;
			var habilitarDesabilitar = false;
			
			if(flag_habilitar_grupo == "true")
			{
				var desabilitarWindow=confirm("Desabilitar Criação de Grupos?");
				
				if (desabilitarWindow==true)
				{
					habilitarDesabilitar = true;
				}
			}
			else
			{
				var habilitarWindow=confirm("Habilitar Criação de Grupos?");
				if (habilitarWindow==true)
				{
					habilitarDesabilitar = true;
				}
			}

			if(habilitarDesabilitar)
			{
				var courseId = document.getElementById("inputCourseID").value;
				window.location = "http://"+'localhost:8080/amadeuslms' + "/course.do?method=habilitarDesabilitarCriarGrupos&courseId=" + courseId;
			}
		}

		function convidarPerson()
		{
			var countAlunos = document.getElementById("inputCountAlunosCourse").value;

			var i;
			for (i=0; i<countAlunos; i++)
			{
				checkTemp = document.getElementById("inputAlunoCourse"+i);
				if(checkTemp.checked && !checkTemp.disabled)
				{
					var table = document.getElementById("tabelaParticipantesGrupos");
					var rowCount = table.rows.length;
		            var row = table.insertRow(rowCount);

					var aluno = checkTemp.value;

		            var nomeAluno = document.getElementById("inputhiddennamealuno"+i).value;
		            var alunoRow = row.insertCell(0);
		            alunoRow.innerHTML = "<input id='inputButtonExcluirAlunoGrupo"+i+"' class='button_add' type='button' name='"+i+"'/><input id='inputAlunoGrupo"+i+"' type='hidden' value='"+aluno+"'/>"+nomeAluno;
		            alunoRow.className  = "criarGrupos";

		            checkTemp.disabled = "disabled";
		            document.getElementById("tdAlunoCourse"+i).style.color='grey';
		            
		            var btnExcluir = document.getElementById("inputButtonExcluirAlunoGrupo"+i);
		            					
		            btnExcluir.onclick = function(e)
		            {
		            	var targ;
		            	if (!e)
		            	{
			            	var e = window.event;
			            } 
		            	if (e.target)
		            	{
			            	targ = e.target;
		            	}      	

			            var tableIn = document.getElementById("tabelaParticipantesGrupos");
						var rowCountIn = tableIn.rows.length;
						
						for (var j=2; j<rowCountIn; j++)
						{
							var rowIn = tableIn.rows[j];
							var input = rowIn.cells[0].childNodes[0];
							if(input != null && input.id == targ.id)
							{
								tableIn.deleteRow(j);

								var checkCurso = document.getElementById("inputAlunoCourse"+targ.name);
								checkCurso.disabled = "";
								checkCurso.checked = "";
								document.getElementById("tdAlunoCourse"+targ.name).style.color='black';
								return false;
							}
						}
		            	
			        };
				}
			}
		}
		
		function montarLista()
		{			
			var nameGroup = document.getElementById("inputNameGroup").value;
			if(nameGroup == null || nameGroup == "" )
			{
				alert('Preencha o nome do grupo.');
				return false;
			}
			
			var confirmacaoCriarGrupo=confirm("Deseja concluir a criação do novo grupo?");			
			if (confirmacaoCriarGrupo!=true)
			{
				return false;
			}
			else
			{
				var countAlunos = document.getElementById("inputCountAlunosCourse").value;	
				
				var courseId = document.getElementById("inputCourseID").value;
				var stringAction = "course.do?method=showViewCreateGroup&courseId="+courseId+"&nomeCourse="+nameGroup;
				
				var indice = 0;
				var entrou = false;
				for (var i=-1; i<countAlunos; i++)
				{
					var aluno = document.getElementById("inputAlunoGrupo"+i);
					if(aluno != null)
					{
						stringAction += "&alunoId"+indice+"="+aluno.value;
						indice++;
						entrou = true;
					}
				}

				stringAction += "&qtdAlunos="+indice;


				if(!entrou)
				{
					alert('Convide algum aluno do curso para o grupo.')
					return false;
				}
				
				document.getElementById("formCreateGroup").action = stringAction;
			}
		}
		
		var ctx = "${pageContext.request.contextPath}";

		function openOneGroup(groupID)
		{
			var courseId = document.getElementById("inputCourseID").value;
			var external = window.open(ctx + "/course.do?method=showViewOneGroup&courseId="+courseId+"&groupID="+groupID,'Grupo',
				'height=400,width=480,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}

		function openTimeline(groupID)
		{
			var external = window.open(ctx + "/course.do?method=showViewGroupTimeline&groupID="+groupID,'Grupo',
			'height=480,width=600,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}

		function openRelatorio()
		{
			var courseId = document.getElementById("inputCourseID").value;
			var external = window.open(ctx + "/course.do?method=showViewRelatorioAtividade&courseId="+courseId,'Grupo',
			'height=400,width=480,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no ,modal=yes');
		}		
	</script>
</head>
<body>
	<input id="inputCourseFlag_habilitar_grupo" type="hidden"
		value="${course.flag_habilitar_grupo}" />
	<input id="inputCourseID" type="hidden" value="${course.id}" />
	<div id="pBody" class="pBody">
		<div id="pHeader" class="pHeader">
			<jsp:include page="/jsp/conf/login.jsp" />
		</div>
		<jsp:include page="/jsp/conf/logo.jsp" />
		<div id="pTitle" class="pTitle">
			<h2>Grupos</h2>
		</div>
		<div id="pBreadCrumbs" class="pBreadCrumbs">
			<ul id="breadcrumb">
				<li><html:link action="system.do?method=showViewMenu">
						<bean:message key="menu.name" />
					</html:link></li>
				<li><html:link
						action="course.do?method=showViewCourse&courseId=${course.id}">
						<bean:write name="course" property="name" />
					</html:link></li>
				<li>Grupos</li>
			</ul>
		</div>
		<div id="pLeftMenu" class="pLeftMenu">
			<c:set var="selectedPosition" value="6" scope="request" />
			<jsp:include page="/jsp/conf/courseMenu.jsp" />
		</div>
		<div id="pContent" class="pContent2">
			<div id="comboBoxGroupsContent" class="horizontal-menu">
				<ul>
					<c:if test="${canViewEnableGroups}">
						<c:if test="${course.flag_habilitar_grupo}">
							<li><a href="#" onclick="habilitarDesabilitarCriarGrupo();">Desabilitar
									Criar Grupos</a></li>
						</c:if>
						<c:if test="${!course.flag_habilitar_grupo}">
							<li><a href="#" onclick="habilitarDesabilitarCriarGrupo();">Habilitar
									Criar Grupos</a></li>
						</c:if>
					</c:if>
					<c:if
						test="${canCreateGroups && (canViewEnableGroups || course.flag_habilitar_grupo)}">
						<li><html:link
								action="course.do?method=showViewGroups&courseId=${course.id}&viewCreateGroup=1">Criar Grupo</html:link></li>
					</c:if>
					<li><html:link
							action="course.do?method=showViewGroups&courseId=${course.id}&viewCreateGroup=2">Visualizar Grupos</html:link></li>
					<c:if test="${canViewEnableGroups}">
						<li><a href="#" onclick="openRelatorio();">Relatório das
								Atividades</a></li>
					</c:if>
				</ul>
			</div>
			<p />
			<p />
			<c:choose>
				<c:when test="${viewCreateGroup == '2'}">
					<div>
						<table border="0" cellpadding="5" cellspacing="0"
							class="visualizarGrupo">
							<tr>
								<th>Nome do Grupo</th>
								<th>Integrantes</th>
								<th>Timeline</th>
								<th>Atividades</th>
							</tr>
							<c:forEach var="group" items="${groups}" varStatus="status">
								<tr>
									<td><a href="#" onclick="openOneGroup(${group.id})">${group.name}</a></td>
									<td>${group.qtdMembros}</td>
									<td><a href="#" onclick="openTimeline(${group.id})">Visualizar</a></td>
									<td align="center"><c:if test="${group.status}">
											<img src="themes/default/imgs/icons/green.png" />
										</c:if> <c:if test="${!group.status}">
											<img src="themes/default/imgs/icons/red.png" />
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:when test="${canCreateGroups && viewCreateGroup == '1'}">
					<div id="criarGrupos">
						<form id="formCreateGroup" method="post" action=""
							onsubmit="return montarLista();">
							<table border="0" class="criarGrupos">
								<tr class="criarGrupos">
									<td class="criarGrupos">
										<div>
											<b>Nome do Grupo:</b>
										</div>
										<div>
											<input id="inputNameGroup" class="groupsName" type="text"
												name="nomeGrupo" />
										</div>
									</td>
								</tr>
								<tr class="criarGrupos">
									<td class="criarGrupos">
										<table id="tabelaParticipantesGrupos" border=1
											class="criarGrupos">
											<tr class="criarGrupos">
												<th class="criarGrupos" colspan="2">Participantes do
													Grupo:</th>
											</tr>
											<tr>
												<c:if test="${!canViewEnableGroups}">
													<td><input id='inputAlunoGrupo-1' class='groups'
														type='hidden' value='${user.person.id}' />${user.person.name}
													</td>
												</c:if>
											</tr>
										</table>
									</td>
									<td class="criarGrupos"><input class='convidarRetirar'
										type="button" value="&lt; Adicionar" onclick="convidarPerson();" />
									</td>
									<td class="criarGrupos">
										<div>
											<table border=1 class="criarGrupos">
												<tr class="criarGrupos">
													<th class="criarGrupos">Participantes do Curso: <input
														id="inputCountAlunosCourse" type="hidden"
														value="${fn:length(students)}" />
													</th>
												</tr>
												<c:forEach var="student" items="${students}"
													varStatus="status">
													<tr>
														<input id="inputhiddennamealuno${student.position}"
															type="hidden" value="${student.name}"> <c:if
																test="${student.haveGroup}">
																<td id="tdAlunoCourse${student.position}"
																	class="disable"><input
																	id="inputAlunoCourse${student.position}" class="groups"
																	type="checkbox" disabled="disabled"
																	value="${student.id}" />${student.name}*</td>
															</c:if> <c:if test="${!student.haveGroup}">
																<td class="criarGrupos"
																	id="tdAlunoCourse${student.position}"><input
																	id="inputAlunoCourse${student.position}" class="groups"
																	type="checkbox" value="${student.id}" />${student.name}</td>
															</c:if>
													</tr>
												</c:forEach>

											</table>
										</div>
									</td>
								</tr>
								<tr class="criarGrupos">
									<td class="criarGrupos"><input type="submit"
										value="Concluir" /></td>
								</tr>
							</table>
						</form>
					</div>
				</c:when>
			</c:choose>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</div>
</body>
</html>