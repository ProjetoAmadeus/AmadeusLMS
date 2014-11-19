<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="br.ufpe.cin.amadeus.amadeus_sdmm.dao.*" %>
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufpe.cin.amadeus.amadeus_sdmm.general.*" %>


<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Vídeos</title>
        <!--<script src="javascript/mootools.v1.11.js">-->
        <script language="javascript">
                       
            function submitForm(searchType){
                document.getElementById("searchType").value = searchType;
                document.getElementById("form").submit();
            }
                                   
            function showAdvanced(){
                if(document.getElementById("teste").style.display == "none"){
                    document.getElementById("teste").style.display = "block";
                }else{
                    document.getElementById("teste").style.display = "none";
                }
            }
            
            function getPage(number,searchType){
                document.getElementById("page").value = number;
                submitForm(searchType); 
            }
            
            function selectAll(){
                var checkArray = document.getElementsByTagName("input");
                for(var i=0 ; i < checkArray.length;i++){
                    if(checkArray[i].type == "checkbox"){
                        checkArray[i].checked = true;
                    }
                }
            }
            
            function deleteVideo(id,searchType){
                if(confirm("Você deseja excluir este vídeo?")){
                    document.getElementById("form").action = "./deleteVideo.jsp";
                    document.getElementById("deleteList").value = id;
                    submitForm("");
                }
            }
            
            function deleteAll(searchType){
                var idList = "";
                if(confirm("Você deseja excluir os vídeos selecionados?")){
                    document.getElementById("form").action = "./deleteVideo.jsp";
                    var checkArray = document.getElementsByTagName("input");
                    for(var i=0 ; i < checkArray.length;i++){
                        if(checkArray[i].type == "checkbox" && checkArray[i].checked == true){
                            if(i == checkArray.length-1 || checkArray.length == 1){
                                var temp = checkArray[i].id.replace("delete",""); 
                                idList = idList + temp;
                            }else{
                                var temp = checkArray[i].id.replace("delete",""); 
                                idList = idList + temp + "-";
                            }                        
                        }
                    }
                    document.getElementById("deleteList").value = idList;
                    submitForm("");
                }
            }
       </script>              
    </head>
    <body>
   <div> 
    <center>
        <h1>Consulta de Vídeos</h1>
        <%
            if(request.getParameter("edit")!= null && request.getParameter("edit").equals("ok")){
                out.println("<center><p><font color=\"blue\"><b>Informações salvas com Sucesso!</b></font></p></center>");
            }
        %>
        <form action="./PageSearchVideo.jsp" method="post" id="form" name="form">
            <input type="hidden" name="searchType" id="searchType" value="">            
            <input type="hidden" name="page" id="page" value="1">
            <input type="hidden" name="deleteList" id="deleteList" value="">
                        
            <table border="0"> 
                <tr>
                    <td>
                        <p>
                        <%
                            if(request.getParameter("kind") != null){
                                String base = "author;Autor#name;Nome#description;Descrição#tags;Tags";
                                String[] list = base.split("#");
                                for(int i=0; i<list.length; i++){
                                    String[] temp = list[i].split(";");
                                    if(request.getParameter("kind").equals(temp[0])){
                                        out.println("<input type=\"radio\" id=\"kind\" name=\"kind\" value=\""+temp[0]+"\" checked>"+temp[1]);
                                    }else{
                                        out.println("<input type=\"radio\" id=\"kind\" name=\"kind\" value=\""+temp[0]+"\">"+temp[1]);
                                    }
                                }
                             }else{
                                out.println("<input value=\"author\" name=\"kind\" checked=\"checked\" type=\"radio\">Autor" +
                                "<input value=\"name\" name=\"kind\" type=\"radio\">Nome <input value=\"description\" name=\"kind\" type=\"radio\">Descrição" +
                                "<input value=\"tags\" name=\"kind\" type=\"radio\">Tags");
                             }
                           %>                            
                        </p>
                    </td>
                </tr>
            </table>
            <br></br>            
            <%
                if(request.getParameter("search") != null){
                    out.println("<input type=\"text\" id=\"search\" name=\"search\" value=\""
                            +request.getParameter("search")+"\" maxlength=\"100\" size=\"40\">");
                }else{
                    out.println("<input type=\"text\" id=\"search\" name=\"search\" value=\"\" maxlength=\"100\" size=\"40\">");
                }
            %>
            <input class="form" type="button" value="Consultar" name="consultar" onclick="submitForm('generic');">
            <div><a href="javascript:showAdvanced();"><h3><b>Busca Avançada</b></h3></a></div>
        </center>
    </div>
    <center><div id="teste" style="display: none">
            <p>
                <b>Formato: </b>
                <select id="format" name="format">
                    <%
                        if(request.getParameter("format") != null){
                            String base = "avi;AVI#mp1;MPEG-1#mp2;MPEG-2#mp4;MPEG-4#ogg;OGG#rm;Real Video#wmv;WMV";
                            String[] list = base.split("#");
                            for(int i=0; i<list.length; i++){
                                String[] temp = list[i].split(";");
                                if(request.getParameter("format").equals(temp[0])){
                                    out.println("<option value=\""+temp[0]+"\" selected>"+temp[1]+"</option>");
                                }else{
                                    out.println("<option value=\""+temp[0]+"\">"+temp[1]+"</option>");
                                }
                                
                            }
                            
                        }else{
                            out.println("<option value=\"avi\" selected>AVI</option><option value=\"mp1\">MPEG-1</option>" +
                            "<option value=\"mp2\">MPEG-2</option><option value=\"mp4\">MPEG-4</option>" +
                            "<option value=\"ogg\">OGG</option><option value=\"rm\">Real Video</option><option value=\"wmv\">WMV</option>");
                        }
                    %>                    
                </select>&nbsp;&nbsp;<input type="button" name="format" value="Consultar" onclick="javascript:submitForm('format');">
            </p>
            <p>
                <b>Licença: </b>
                <select id="license" name="license">
                    <%
                        if(request.getParameter("license") != null){
                            if(request.getParameter("license").equals("1")){
                                out.println("<option value=\"1\" selected>Público</option><option value=\"0\">Privado</option>");
                            }else{
                                out.println("<option value=\"1\">Público</option><option value=\"0\" selected>Privado</option>");
                            }
                        }else{
                            out.println("<option value=\"1\" selected>Público</option><option value=\"0\">Privado</option>");
                        }
                    %>                                   
                </select>&nbsp;&nbsp;<input type="button" name="format" value="Consultar" onclick="javascript:submitForm('license');">
            </p>
            <p>
                <b>Resolução: </b>
                Largura: 
                <%
                    if(request.getParameter("width") != null){
                        out.println("<input type=\"text\" size=\"4\" name=\"width\" id=\"width\" value=\""+request.getParameter("width")
                            +"\">");
                    }else{
                        out.println("<input type=\"text\" size=\"4\" name=\"width\" id=\"width\" value=\"\">");
                    }
                %>       
                 X 
                Altura: 
                <%
                    if(request.getParameter("height") != null){
                        out.println("<input type=\"text\" size=\"4\" name=\"height\" id=\"height\" value=\""+request.getParameter("height")
                            +"\">");
                    }else{
                        out.println("<input type=\"text\" size=\"4\" name=\"height\" id=\"height\" value=\"\">");
                    }
                %>    
                 pixels 
                <input type="button" name="resolution" value="Consultar" onclick="javascript:submitForm('resolution');">
            </p>
                <p><b>Período de Inserção: </b></p>
                <%
                    if(request.getParameter("dateinit") != null && request.getParameter("dateend") != null){
                       out.println("<p>Data Início: <input type=\"text\" size=\"10\" name=\"dateinit\" id=\"dateinit\" value=\""+
                                request.getParameter("dateinit")+"\">" + " (aaaa/mm/dd)</p><p>Data Final: <input type=\"text\" " +
                                "size=\"10\" name=\"dateend\" id=\"dateend\" value=\""+request.getParameter("dateend")+"\"> (aaaa/mm/dd)</p");
                    }else{
                        out.println("<p>Data Início: <input type=\"text\" size=\"10\" name=\"dateinit\" id=\"dateinit\" value=\"\"> " +
                        "(aaaa/mm/dd)</p><p>Data Final: <input type=\"text\" size=\"10\" name=\"dateend\" id=\"dateend\" value=\"\"> " +
                        "(aaaa/mm/dd)</p");
                    }
                %>               
                <input type="button" name="period" value="Consultar" onclick="javascript:submitForm('period');">            
    </div></center>
    <!-- </form>-->
    
    <%
    
    if(request.getParameter("searchType") != null){        
        
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        String searchType = request.getParameter("searchType");
       
        int begin = pageNumber*10 - 9;
        int end = pageNumber*10;        
                
        VideoDAO videodao = new VideoDAO();
        Vector result = null;
        
        double temp = 0.0;
        
        if(searchType.equals("generic")){
            String kind = request.getParameter("kind");
            String value = request.getParameter("search");
            result = videodao.search(kind,value,begin);
            temp = videodao.getAmount(kind,value)/10.0 + 0.9;
        }else if(searchType.equals("format")){
            String value = request.getParameter("format");
            String kind = "extension";
            result = videodao.search(kind,value,begin);
            temp = videodao.getAmount(kind,value)/10.0 + 0.9;
        }else if(searchType.equals("license")){
            String value = request.getParameter("license");
            String kind = "license";
            result = videodao.search(kind,value,begin);
            temp = videodao.getAmount(kind,value)/10.0 + 0.9;
        }else if(searchType.equals("period")){
            String value1 = request.getParameter("dateinit").replace("/","-");
            String value2 = request.getParameter("dateend").replace("/","-");
            result = videodao.searchAdvanced(searchType,value1,value2,begin);
            temp = videodao.getAmountAdvanced(searchType,value1,value2)/10.0 + 0.9;
        }else if(searchType.equals("resolution")){
            String value1 = request.getParameter("width");
            String value2 = request.getParameter("height");
            result = videodao.searchAdvanced(searchType,value1,value2,begin);
            temp = videodao.getAmountAdvanced(searchType,value1,value2)/10.0 + 0.9;
        }
        
        if(result.size() > 0){
            out.println("<center>");
            out.println("<p><b>Navegação:</b> <select>");
            int amount = (int) temp;
            for(int i=1;i<=amount;i++){
                if(i == pageNumber){
                    out.println("<option value=\"page"+i+"\" onclick=\"javascript:getPage("+i+",'"+searchType+"');\" selected> Página "+i+"</option>");        
                }else{
                    out.println("<option value=\"page"+i+"\" onclick=\"javascript:getPage("+i+",'"+searchType+"');\"> Página "+i+"</option>");        
                }

            }
            out.println("</select></p>");

            out.println("<p><a href=\"javascript:selectAll();\">Selecionar todas</a> | " +
                "<a href=\"javascript:deleteAll();\">Excluir Selecionadas</p>");

            out.println("<div><table>");
            for(int i=0; i<result.size();i++){
                String color = "#FFFFFF";
                if(i % 2 == 0){
                    color = "#FFFFCC";
                }
                Video video = (Video) result.get(i);
                //im.loadThumbnail(video.getId());
                String publicDirectoryVideos = Sdmm.publicDirectoryVideos;
                out.println("<tr bgcolor="+color+">");
                out.println("<td><input type=\"checkbox\" id=\"delete"+video.getId()+
                        "\" name=\"delete"+video.getId()+"\"></td>");
                out.println("<td><a href=\"VideoViewer.jsp?id="+video.getId()+"\"><img border=\"0\" src='"+publicDirectoryVideos +
                        "thumbnail.jpg'></td>");
                out.println("<td>");
                out.println("<p><b>Nome: </b>"+video.getName()+"</p>");
                out.println("<p><b>Descrição: </b>"+video.getDescription()+"</p>");
                out.println("<p><b>Autor: </b>"+video.getAuthor()+"</p>");
                out.println("<p><b>Tags: </b>"+video.getTags()+"</p>");
                out.println("<p><b>Data de Inserção </b>"+video.getDateinsertion()+"</p>");
                out.println("</td>");
                out.println("<td><a href=\"EditVideo.jsp?id="+video.getId()+"\"><img border=\"0\" src=\"edit2.png\"></a>&nbsp;&nbsp;" +
                            "<a href=\"#\" onclick=\"deleteVideo("+video.getId()+",'"+searchType+"');\"><img border=\"0\" src=\"trash2.png\"></a></td>");
                out.println("</tr>");
            }
            out.println("</table></div>");
            out.println("<div><a href=\"javascript:selectAll();\">Selecionar todas</a> | " +
                "<a href=\"javascript:deleteAll('"+searchType+"');\">Excluir Selecionadas</div>");
            out.println("</center>");
        }else{
            out.println("<center><p><font color=\"red\"><b>Não foi encontrado nenhum vídeo!</b></font></p></center>");
        }
    }    
    %>
    </body>
</html>