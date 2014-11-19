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

<%@ page import="br.ufpe.cin.amadeus.amadeus_web.facade.Facade"%>
<%@ page isErrorPage="true" %>

<html:html>
	<head>
		<link href="<html:rewrite page="/themes/default/css/error.css" />" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='dwr/interface/UtilDWR.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript">
			function userComment() {
				document.getElementById("linkUserComment").style.display = "none";
				document.getElementById("userComment").style.display = "block";	
			}
			
			function reportError() {
				var startUserComment = "<bean:message key='pageError.pageExceptionStartUserComment'/>";
				var endUserComment = "<bean:message key='pageError.pageExceptionEndUserComment'/>";
				
				var userComment = startUserComment+"\n\n" +
						document.getElementById("textUserComment").value +
						"\n\n"+endUserComment;
				
				var contentError =  userComment +"\n\n\n"+ dwr.util.getValue("contentError");
				var email = "<bean:message key='pageError.emailReportError'/>";
				var subject= "<bean:message key='pageError.emailSubject'/>";
					
				document.getElementById("titlePageError").style.display = "none";
				document.getElementById("contentPageError").style.display = "none";
				
				document.getElementById("titlePageErrorReported").style.display = "block";
				document.getElementById("titlePageErrorReported").innerHTML = "<bean:message key='pageError.pageExceptionTitleSendingError'/>";
				document.getElementById("sendingError").style.display = "block";
				
				UtilDWR.getInclude('/userReportError.do?method=reportError&email='+email+'&subject='+subject+'&contentError='+contentError,
					function(data) {
						document.getElementById("sendingError").style.display = "none";
						document.getElementById("titlePageErrorReported").innerHTML = "<bean:message key='pageError.pageExceptionTilleErrorReported'/>";
						document.getElementById("contentPageErrorReported").style.display = "block";
					}
				);
			}
		</script>
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
					
					<table border="0">
						<tr>
							<td>
								<img src="<%=request.getContextPath()%>/themes/default/imgs/manutencao.jpg"/>
							</td>
							<td>
								<font color="#5a9b63" size="3">
									<bean:message key="pageError.pageExceptionContent"/>
								</font>
							</td>
						</tr>
					</table>
					<br/><br/>
					<div id="linkUserComment">
						<a href="javascript:;" onclick="userComment();" style="text-decoration: none;">
						<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/favicon2.jpg"/>&nbsp;
						<font color="#5a9b63" size="2" style="text-align: right;"><bean:message key="pageError.pageExceptionLinkUserComment"/></font>
						</a>
					</div>
					<div id="userComment">
						<br/>
						<textarea id="textUserComment" rows="20" cols="60"></textarea>
						<br/><br/>
						&nbsp;&nbsp;&nbsp;<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/favicon2.jpg"/>&nbsp;
						<font color="#5a9b63" size="2" style="text-align: right;"><bean:message key="pageError.pageExceptionUserComment"/></font>
					</div>
					<a href="javascript:;" onclick="reportError();" style="text-decoration: none;">
					<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/favicon2.jpg"/>&nbsp;
					<font color="#5a9b63" size="2" style="text-align: right;">
						<bean:message key="pageError.pageExceptionReportError"/>
					</font>
					</a>
					<p/>
				</div>
				<div id="titlePageErrorReported"><bean:message key="pageError.pageExceptionTilleErrorReported"/></div>
				<div id="contentPageErrorReported">
					<p>
					<font color="#5a9b63" size="3"><bean:message key="pageError.pageExceptionContentErrorReported"/></font>
					<br/><br/>
					<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/favicon2.jpg"/>&nbsp;
					<font color="#5a9b63" size="2" style="text-align: right;"><bean:message key="pageError.pageExceptionTimeAmadeusLMS"/></font>
					<p/>
				</div>
				<div id="sendingError">
					<img border="0" src="<%=request.getContextPath()%>/themes/default/imgs/ajax-loader.gif"/>&nbsp;
					<font color="#5a9b63" size="2" style="text-align: right;"><bean:message key="pageError.pageExceptionContentSendingError"/></font>
				</div>
				<html:link action="system.do?method=showViewMenu"><bean:message key="general.back"/></html:link>
				<br/><br/>
				<div id="contentError" style="display: none;">
					<%
					
					if(null == exception){
					    exception = (Throwable)request.getAttribute("org.apache.struts.action.EXCEPTION");
					}
					if(null == exception){
					    out.write("Source of error is unknown.");
					}else{
					    java.io.StringWriter sw = new java.io.StringWriter();
					    java.io.PrintWriter pw = new java.io.PrintWriter(sw);
					
					    exception.printStackTrace(pw);
					    out.write(sw.getBuffer().toString());
					
					}
					%>
				</div>
			</div>
		</div>
		<jsp:include page="/jsp/conf/footer.jsp" />
	</body>
</html:html>