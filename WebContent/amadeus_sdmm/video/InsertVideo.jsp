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
<%@page import="br.ufpe.cin.amadeus.amadeus_sdmm.dao.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.RandomAccessFile" %>
<%@ page import="java.sql.Date" %>
<%@ page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
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
        <title>Inserção de  Video</title>
    </head>
    <body>
    
    <%
        try{
            
            String description="";
            String name="";
            String author="";
            String tags="";
            char license='1';
            String path="";
            String videopath="";
            String thumbnail="";
        
            /*Sdmm.tempDirectory - diretorio temporario que recebe os objetos uploaded*/
            /*pode levantar uma excecao no random*/
            int randomic = 1 + (int)(Math.random()*1000);
            String fileTemp = "arqAux"+ String.valueOf(randomic)+".wmv";
            /*pode levantar uma excecao no file*/
            File fNew = new File(Sdmm.tempDirectory, fileTemp);                
            DiskFileUpload fu = new DiskFileUpload();
            // If file size exceeds, a FileUploadException will be thrown
            fu.setSizeMax(100000000);
        
            /*pode levantar uma excecao no parseRequest*/
            List fileItems = fu.parseRequest(request);
            Iterator itr = fileItems.iterator();
        
            while(itr.hasNext()) {
                FileItem fi = (FileItem)itr.next();
        
                //Check if not form field so as to only handle the file inputs
                //else condition handles the submit button input
                if(!fi.isFormField()) {
                    String tempDirectoryVideos = Sdmm.tempDirectoryVideos; //diretório onde ficam os arquivos uploaded no momento da inserção.
                    /*Pode levantar uma excecao no file*/
                    File fAuxNew = new File(tempDirectoryVideos, fi.getName());
                    fNew = fAuxNew;
                    fi.write(fNew);
                }
                else {
                    if (fi.getFieldName().equals("description")) {
                        description = fi.getString();
                    }
                    if (fi.getFieldName().equals("name")){                
                        name = fi.getString();
                    }
                    if (fi.getFieldName().equals("author")){                
                        author = fi.getString();
                    }
                    if (fi.getFieldName().equals("tags")){                
                        tags = fi.getString();
                    }
                    if (fi.getFieldName().equals("license")){                
                        license = fi.getString().charAt(0);
                    }
                    if (fi.getFieldName().equals("video")){                
                        path = fi.getString();
                    }
                }
            }
            videopath = fNew.getAbsolutePath();      
            if (Sdmm.soType.equals("windows")){
                videopath = videopath.replace("\\","\\\\");
            }else{
                videopath = videopath.replace("/","\\\\");
            }                    
            //Get Next ID
            VideoDAO videoDAO = new VideoDAO();
            //int id = videoDAO.getMaxId()+1;
            int id = videoDAO.getMaxId();

            //Get Video Properties
            long length = fNew.length();
            long datemodification = fNew.lastModified();

            int width = 0;
            int height = 0;
            int bitrate = 0;
            double framerate = 0.0;
            String duration = "";
            String standard = "";

            //------------------------------------

            //Extension - avi,mpeg,ogg...
            String temp = videopath.replace(".","-");
            String[] temp2 = temp.split("-");
            String extension = temp2[temp2.length-1];

            //DATE        
            Date dateinsertion = new Date(new java.util.Date().getTime());

            //TODO: Thumbnail - capturar o primeiro frame do video
            //String thumbnail = "";

            //Creating Video Object
            Video video = new Video(id,description,name,author,tags,videopath,datemodification,license,
                    width,height,thumbnail,length,dateinsertion,extension,bitrate,
                    framerate,duration,standard);

            videoDAO.insertVideo(video);
            out.println(video.getVideo()+ " Cadastrado com Sucesso!!!!");
            response.sendRedirect("Prototipo.jsp?cadastro=ok");           
        }
        catch(Exception e){
            out.println("<center>Excecao no upload/inserção do arquivo: "+e.getMessage()+"</center>");
            e.printStackTrace();            
        }
    %>
    
    </body>
</html>
