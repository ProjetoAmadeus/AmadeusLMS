<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material" %>
<%@ page import="java.util.List" %>

<script type="text/javascript">

// alert('id do módulo: ${idModule} \nid do Material: ${idMaterial} \nNome do Material: ${materialName}');
if(${action} == '0') { //if it's a material upload
	top.addNewNameMaterial(${module.position}, ${material.id}, '${material.archiveName}');
	top.cancelShowListMaterial(${module.position});

} else if(${action} == '1') { //if it's editing a material
	top.removeAllNameMaterial(${module.position});
	
	<% 
	if(request.getAttribute("materials") != null) {
		List<Material> materials = (List<Material>) request.getAttribute("materials");
		
		for(Material m : materials){
			if(m.getRequestedMaterial() == null) {
				request.setAttribute("idM",m.getId());
				request.setAttribute("nameM",m.getArchiveName());
				%>top.addNewNameMaterial(${module.position}, ${idM}, '${nameM}');<%	
			}
		}
	}
	%>
	
	top.cancelShowListMaterial(${module.position});

}else if(${action} == '2') { //if it's a material delivery
	top.backEditName(${module.position});

}

</script>
