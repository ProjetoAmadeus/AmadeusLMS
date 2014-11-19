<!-- 
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
-->

<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
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
        <title>Servidor de Dados Multimidia - P4</title>
    </head>
    <body>
    <p>Neste segundo momento o projeto esta em fase de INTEGRAÇÃO com o AMADEUS e 
    existe apenas um repositorio de VIDEO com INSERCAO E CONSULTA dos OBJETOS.
    O protótipo 4 é a união das melhores caracteristicas do protótipo 1 e do prototipo2.
    Prototipo 1 - armazena os objetos binarios como referencia externa - pasta no servidor web
    Prototipo 2 - armazena os objetos binario como blobs internamente no banco - blob no sgbd
    </p>
    <p>Implementação : Antonio Nascimento - Graduando em Ciencia da Computacao</p>
    <p>Implementação/Co-orientenção : Armando Soares Sousa - Mestrando em Ciencia da Computacao</p>
    <p>Orientador: Fernando Fonseca</p>
    <center>
        <h1>Protótipo 4 - SDMM</h1>
        <%
            //Sdmm configServlet = new Sdmm();
            if(request.getParameter("cadastro")!= null && request.getParameter("cadastro").equals("ok")){
                out.println("<center><p><font color=\"blue\"><b>Objeto inserido com Sucesso!</b></font></p></center>");
            }
        %>
        <table border="1">
            <tr>
                <td><a href="PageInsertVideo.jsp">Inserir Video</a></td>
                <td><a href="PageSearchVideo.jsp">Consultar Video</a></td>
            </tr>
        </table>
    </center>    
    </body>
</html>