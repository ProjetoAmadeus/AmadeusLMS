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

	<form>
		<fieldset>
			<label for="reply-assunto">Assunto:</label>
			<input type="text" id="reply-assunto" class="reply-assunto" name="reply-assunto" disabled="disabled" /><br />
			<label id="label-reply-content" for="reply-content">Mensagem:</label>
			<textarea id="reply-content" class="reply-content" name="reply-content"></textarea>
		</fieldset>
	</form>