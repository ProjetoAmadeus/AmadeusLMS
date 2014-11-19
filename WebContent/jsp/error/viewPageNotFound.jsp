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
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:html>
<head>
<link href="<html:rewrite page="/themes/default/css/error.css" />" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/jsp/conf/header.jsp" />
		<div id="all">
			<div id="header">
			</div>
			<jsp:include page="/jsp/conf/logo.jsp" />
			<div id="pageError">
				<div id="titlePageError"><bean:message key="pageError.Title"/></div>
				<div id="contentPageError">
					<p>
					<font color="#5a9b63" size="4"><bean:message key="pageError.pageNotFoundContent"/></font>
					<br/>
					<br/>
					<img src="<%=request.getContextPath()%>/themes/default/imgs/favicon2.jpg"/>&nbsp;
					<font color="#5a9b63" size="2"><bean:message key="pageError.pageNotFoundLikelyCause"/></font>
					<p/>
				</div>
				<html:link action="system.do?method=showViewMenu"><bean:message key="general.back"/></html:link>
				<br/><br/>
			</div>					
			<jsp:include page="/jsp/conf/footer.jsp" />
		</div>
	</body>
</html:html>