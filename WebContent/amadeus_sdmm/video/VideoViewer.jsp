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
<%@ page import="br.ufpe.cin.amadeus.amadeus_sdmm.general.*"%>
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
        <title>Visualizador de Vídeos</title>
    </head>
    <body>

    <center><h1>Visualizador de Vídeos</h1></center>
    
    <% 
        int id = Integer.parseInt(request.getParameter("id"));
        VideoDAO videoDAO = new VideoDAO();
        Video video = videoDAO.get(id);
        videoDAO.loadVideo(video,video.getExtension());
        out.println("<center>");
        out.println("<div><b>Nome: </b>"+video.getName()+"</div>");
        out.println("<div><b>Autor: </b>"+video.getAuthor()+"</div>");
        out.println("<div><b>Descrição: </b>"+video.getDescription()+"</div>");
        out.println("<div><b>Tags: </b>"+video.getTags()+"</div>");
        if(video.getLicense() == '1'){
            out.println("<div><b>Direitos autorais: </b> Público</div>");
        }else{
            out.println("<div><b>Direitos autorais: </b> Privado</div>");
        }
        
        out.println("<div><b>Resolução: </b>"+video.getWidth()+" X "+video.getHeight()+" pixels</div>");
        out.println("<div><b>Tamanho(KB): </b>"+(video.getLength()/1024)+" KB</div>");
        out.println("<div><b>Data de Inserção: </b>"+video.getDateinsertion()+"</div>");
        out.println("<br/><br/>");
        String publicDirectoryVideos = Sdmm.publicDirectoryVideos;
        //out.println("<img src=\"http://localhost/images/" +"image"+id+".jpg\">");
        //out.println("<embed src=\"http://localhost:8081/prototipo4/videos/video"+video.getId()+"."+video.getExtension()+"\" width=\"200\" height=\"200\" autoplay=\"true\" loop=\"true\" controls=\"true\"></embed>");
        out.println("<embed src='"+publicDirectoryVideos+"video"+video.getId()+"."+video.getExtension()+"' width='200' height='200' autoplay='true' loop='true' controls='true'></embed>");
        out.println("</center>");            
    %>    
    
    </body>
</html>