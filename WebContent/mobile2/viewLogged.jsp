<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html:html>
	<head>
	<meta http-equiv="Content-Type"
		content="application/xhtml+xml; charset=iso-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>amadeus.mobile</title>
	</head>
	<body>
		<form id="homeForm" action="" method="post">
			
			<table style="border: none; width: 100%;">
				<tr>
					<td align="center" style="background-color: #cccccc; color: #089b08;">Meus Cursos</td>
				</tr>
				<logic:iterate id="course" name="listCourses" indexId="count" type="br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile">
					<tr>
						<td align="center">
							<html:link action="mobile.do?method=showViewCourse&idCourse=${course.id}"> ${course.name}</html:link>
						</td>
					</tr>
				</logic:iterate>
				
				<tr>
					<td align="center">
						<html:link action="mobile.do?method=showViewAllCourses"> Todos </html:link>
					</td>
				</tr>
				<tr>
					<td align="center" style="background-color: #cccccc; color: #089b08;">AMADEUS</td>
				</tr>
				<tr>
					<td align="center">
						<c:if test="${noticesSize > 0}">
							<html:link action="mobile.do?method=showViewNotices"> Notícias ( ${qtdUnreadNotices} ) </html:link>
						</c:if>
						<c:if test="${noticesSize < 1}">
							Notícias (Nenhuma notícia)
						</c:if>
					</td>
				</tr>
				<tr>
					<td align="center" style="background-color: #cccccc; color: #089b08;">Configurações</td>
				</tr>
				<tr>
					<td align="center">
						<html:link action="mobile.do?method=showViewManagement"> Gerenciamento </html:link>
					</td>
				</tr>
				<tr>
					<td align="center" style="background-color: #cccccc; color: #089b08;">
						<html:link action="mobile.do?method=logoff"> Deslogar </html:link>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html:html>