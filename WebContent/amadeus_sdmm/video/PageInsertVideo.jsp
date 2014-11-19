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
        <title>Inserção de Video</title>
    </head>
    <body>

    <center><h1>Inserir Video</h1></center>
        
    <center>
    <form action="InsertVideo.jsp" method="post" enctype="multipart/form-data">
        <p>Nome: <input type="text" name="name" size="50"></p>
        <p>Autor: <input type="text" name="author" size="50"></p>
        <p>Descricao: <br/><textarea name="description" cols="50" rows="5">descricao do video</textarea></p>
        <p>Tags: <br/><textarea name="tags" cols="50" rows="5">tags da imagem separados por virgula</textarea></p>
        <p> Direitos autorais:
            <select name="license" size="1">
                <option value="1">Publico</option>
                <option value="0">Privado</option>
            </select>
        </p>
        <p>Caminho: <input id="video" type="file" name="video" size="50"></p>
        <input type="submit" name="buttonsubmit" value="Importar">
    </form>
    </center>                
    </body>
</html>
