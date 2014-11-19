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


<div id="seemessages">
	<c:if test="${ empty pageSeeAll }">	
		<h2>Novas Mensagens</h2>
	</c:if>
		<div id="messages-all">
			<div id="no-message-unread">
				<logic:empty name="messagesUnread">
					<p>Nenhuma mensagem não lida!</p>
				</logic:empty>
			</div>
			<div id="all-messages-unread">
				<logic:notEmpty name="messagesUnread">
					<logic:iterate id="message" name="messagesUnread">
						<div class="the-message">
							<span class="from-user"><small><b>${message.sender.name}</b></small></span><br />
							<span class="message-date"><small><fmt:formatDate value="${message.date}" pattern="dd-MM-yyyy HH:mm" /></small>
							</span>
							<h3 class="the-message-title"><a href="#" id="${message.id}" class="link-message-title lida-${message.read}">${message.title}</a></h3>
							<p class="the-message-content" id="message-content-${message.id}">${message.content}<br /><br />
								<a class="message-reply-link" title="Re: ${message.title}" href="messenger.do?method=replyMessage&messageId=${message.id}&senderId=${message.sender.id}&courseId=${course.id}">Responder</a>
								<a class="message-delete-link" href="messenger.do?method=deleteMessage&messageId=${message.id}">Excluir</a><br />
								<c:if test="${message.toAll}">
									<a class="reply-to-all-link" title="Re: ${message.title}" 
										href="messenger.do?method=replyMessageToAll&messageId=${message.id}&senderId=${message.sender.id}&courseId=${course.id}">
										Responder a todos
									</a>
								</c:if>
							</p>
						<hr />
						</div>
					</logic:iterate>
				</logic:notEmpty>
			</div>
		</div>
		<c:if test="${ empty pageSeeAll }">
			<div id="div-link-see-all-messages"><a href="messenger.do?method=showViewAllMessengerMessages&courseId=${course.id}" class="link-see-all-messages">See all messages</a></div>
		</c:if>
	</div>