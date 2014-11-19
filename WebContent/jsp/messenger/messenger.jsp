<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<!-- 
	Arquivo criado por Nailson Cunha para implementação de um sistemas de mensagens inbox no Amadeus.
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="messenger">
	<div id="participants">
		<h2>Participantes</h2>
		<ul class="all-participants">
			<c:if test="${canSendToClassmates}">
			<li id="all-participants" class="alluser" >Classmates</li>
			<li>
				<div class="send-message-0">
					<form action="messenger.do" id="form-send-to-all">
						<input type="hidden" name="courseId" value="${course.id}" />
						<input type="text" name="assunto-to-all" id="assunto-to-all" class="ipt-assunto" />
						<textarea name="content-to-all" id="content-to-all"></textarea>
						<input type="submit" value="Enviar!" /> 
						<input type="button"
							class="btn-cancelar" value="Cancelar" />
					</form>
				</div>
			</li>
			</c:if>
			<logic:notEmpty name="teachers">
				<li id="li-professores">Professores</li>
				<logic:iterate id="teacher" name="teachers">
					<li id="${teacher.accessInfo.id}" class="participant-name offlineUser">${teacher.name}</li>
					<li>
						<div id="sendto-${teacher.accessInfo.id}" class="send-message">
							<form class="send-msg" id="${teacher.accessInfo.id}"  action="">
								<input type="hidden" name="para" value="${teacher.id}" />
								<input type="text" name="assunto" class="ipt-assunto" />
								<textarea name="txtarea-mensagem"  class="txtarea-mensagem"></textarea>
								<input type="submit" value="Enviar!" /> <input type="button"
									class="btn-cancelar" value="Cancelar" />
							</form>
						</div>
					</li>
				</logic:iterate>
			</logic:notEmpty>
			<logic:notEmpty name="participants">
			<li id="li-alunos">Alunos</li>
				<logic:iterate id="participant" name="participants">
					<li id="${participant.accessInfo.id}" class="participant-name offlineUser">${participant.name}</li>
					<li>
						<div id="sendto-${participant.accessInfo.id}" class="send-message">
							<form class="send-msg" action="">
								<input type="hidden" name="para" value="${participant.id}" />
								<input type="text" name="assunto" class="ipt-assunto" />
								<textarea name="txtarea-mensagem" class="txtarea-mensagem"></textarea>
								<input type="submit" value="Enviar!" /> 
								<input type="button" class= "btn-cancelar" value="Cancelar" />
							</form>
						</div>
					</li>
				</logic:iterate>
			</logic:notEmpty>
		</ul>
	</div>
	<!-- 
	
	 -->
	 <c:if test="${ empty pageSeeAll }">
	 	<jsp:include page="/jsp/messenger/seemessages.jsp" />
	 </c:if>
</div>
<input type="hidden" id="hidden-course-id" name="hidden-course-id" class="hidden-course-id"  value="${course.id}" />
<div id="form-reply-to" title="Responder Mensagem"></div>