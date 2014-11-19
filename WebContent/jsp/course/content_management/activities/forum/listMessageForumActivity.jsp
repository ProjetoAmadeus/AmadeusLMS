<!--
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>

<logic:notPresent name="user"> 
	<logic:redirect action="system.do?method=showViewWelcome"/> 
</logic:notPresent>

<c:if test="${totalResultado eq 0}">
	<div id="msgMatInfo" class="msgBoxInformation">
		Nenhuma mensagem a ser exibida!
	</div>
</c:if>
<c:if test="${totalResultado != 0}">
	<div id="rolagem" class="cmLine">
		<c:forEach items="${messages}" var="message">
		<div class="fMessageBody">
			<PRE class="preMod"><bean:write name="message" property="body" filter="false"/></PRE>
			<div class="fMessageAuthor">
				<strong><bean:write name="message" property="author.name" filter="false" />,</strong>
				<div class="cmLineRight">
				<fmt:formatDate value="${message.date}" type="both" pattern="EEEE, d MMMM yyyy" />&nbsp;&nbsp;às&nbsp;&nbsp;<fmt:formatDate value="${message.date}" type="time" pattern="HH:mm" />
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	
	<c:if test="${numeroDePagina > 1}">
	<div id="indices" class="fMessagePagination">	
		<c:if test="${menos > 0}">
			<span class="pagAnt"><a href="javascript:void(0)" onclick="showViewListMessagesForumActivity(${forum.module.position},1,${forum.id});">&nbsp;&laquo;&nbsp;</a></span>
     		   	<span class="pagAnt"><a href="javascript:void(0)" onclick="showViewListMessagesForumActivity(${forum.module.position},${menos},${forum.id});">&nbsp;&lt;&nbsp;</a></span>
		</c:if>
		<c:if test="${(mais <= numeroDePagina) && !(menos > 0)}">
			<span class="pagAnt">&nbsp;&laquo;&nbsp;</span>
	    	<span class="pagAnt">&nbsp;&lt;&nbsp;</span>
		</c:if>
		
		<%
		int anterior = (Integer) request.getAttribute("anterior");
		int posterior = (Integer) request.getAttribute("posterior");
		int pagina = (Integer) request.getAttribute("pagina");
		
		for(int i=anterior;i <= posterior;i++){    
          if(i != pagina){
%>
        	  <span class="linkAtivo"><a href="javascript:void(0)" onclick="showViewListMessagesForumActivity(${forum.module.position},<%=i%>,${forum.id});">&nbsp;<%=i%></a>&nbsp;</span>
<%	          }else{ %>
              <span class="pagCorrente">&nbsp;<strong><%=i%></strong>&nbsp;</span>
<%			  }
      	}
		 %>
		
	 	<c:if test="${mais <= numeroDePagina}">
	 	  <span class="pagProx"><a href="javascript:void(0)" onclick="showViewListMessagesForumActivity(${forum.module.position},${mais},${forum.id});">&nbsp;&gt;&nbsp;</a></span>
   		  <span class="pagProx"><a href="javascript:void(0)" onclick="showViewListMessagesForumActivity(${forum.module.position},${numeroDePagina},${forum.id});">&nbsp;&raquo;&nbsp;</a></span>
	 	</c:if>
		<c:if test="${(menos > 0) && !(mais <= numeroDePagina)}">
	 		<span class="pagProx">&nbsp;&gt;&nbsp;</span>
		  	<span class="pagProx">&nbsp;&raquo;&nbsp;</span>
	 	</c:if>
	</div>
	</c:if>
</c:if>