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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" 
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html:html>

	<body>
		<table style="width: 100%;">
			<tr>
				<td align="center" style="background-color:${course.color};">
					<strong>${course.name}</strong>
				</td>
			</tr>
			<tr>
				<td style="border-bottom: 1px solid #cccccc;">
					<strong>>>Atividades</strong>
				</td>
			</tr>
			
			<tr style="cursor: pointer;">
				<td style="border-bottom: 1px solid #cccccc;" valign="top" align="left">
					<logic:iterate id="poll" name="polls" indexId="count">
						<html:link action="mobile.do?method=showViewPoll&idCourse=${course.id}&idPoll=${poll.id}" 
							style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
								${poll.deadline} - ${poll.name}
						</html:link>
					</logic:iterate>
					<logic:iterate id="learningObject" name="learningObjects" indexId="count" >
						<html:link action="mobile.do?method=showViewLearningObject&idCourse=${course.id}&idLearning=${learningObject.id}" 
							style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
								${learningObject.deadline} - ${learningObject.name}
						</html:link>
					</logic:iterate>
					<logic:iterate id="anotherHomework" name="anotherHomeworks" indexId="count" >
						<html:link action="mobile.do?method=showViewHomework&idCourse=${course.id}&idHomework=${anotherHomework.id}" 
							style="display: block; width: 100%; height: 100%; color: #000; text-decoration: none;">
								${anotherHomework.deadline} - ${anotherHomework.name}
						</html:link>
					</logic:iterate>
		 		</td>
			</tr>
			<tr>
				<td align="left">
					<html:link action="mobile.do?method=showViewCourse&idCourse=${course.id}">Voltar</html:link>
					<html:link action="mobile.do?method=showLoggedView">Home</html:link>
				</td>				
			</tr>
		</table>
	</body>

</html:html>