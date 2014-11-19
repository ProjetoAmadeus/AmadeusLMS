/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_sdmm.general;

/**
 * @author Armando Soares Sousa
 *
 */
import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Enumeration;

/**Class that init all set configurations to Sdmm application
 *
 * @author armando
 * @version
 */
public final class Sdmm extends HttpServlet {
    public static String tempDirectory;
    public static String tempDirectoryImages;
    public static String directoryImages;
    public static String publicDirectoryImages;
    public static String tempDirectoryAudios;
    public static String directoryAudios;
    public static String publicDirectoryAudios;
    public static String tempDirectoryVideos;
    public static String directoryVideos;
    public static String publicDirectoryVideos;
    public static String tempDirectoryVORs;
    public static String directoryVORs;
    public static String publicDirectoryVORs;
    public static String ipAddress;
    public static String soType;
    public static String userDataBase;
    public static String passwordDataBase;
    public static String nameDataBase;
    public static String portDataBase;
    public static String ipAddressDataBase;
    
    /*Starts all parameters to Sdmm application 
     *
     *@param Config - all parameters from web.xml web configuration file
     */
    public void init(ServletConfig config) throws ServletException{
        Enumeration parameters = config.getInitParameterNames();
        while(parameters.hasMoreElements()){
            String parameter = (String) parameters.nextElement();
            if (parameter.equals("tempDirectory")){
                this.tempDirectory = config.getInitParameter("tempDirectory");
            }
            /*stars configuration parameters about image*/
            if (parameter.equals("tempDirectoryImages")){
                this.tempDirectoryImages = config.getInitParameter("tempDirectoryImages");
            }
            if (parameter.equals("directoryImages")){
                this.directoryImages = config.getInitParameter("directoryImages");              
            }
            if (parameter.equals("publicDirectoryImages")){
                this.publicDirectoryImages = config.getInitParameter("publicDirectoryImages");
            }
            /*end of configuration parameters about image*/
            
            /*stars configuration parameters about audio*/
            if (parameter.equals("tempDirectoryAudios")){
                this.tempDirectoryAudios = config.getInitParameter("tempDirectoryAudios");
            }
            if (parameter.equals("directoryAudios")){
                this.directoryAudios = config.getInitParameter("directoryAudios");              
            }
            if (parameter.equals("publicDirectoryAudios")){
                this.publicDirectoryAudios = config.getInitParameter("publicDirectoryAudios");
            }
            /*end of configuration parameters about audio*/

            /*stars configuration parameters about video*/
            if (parameter.equals("tempDirectoryVideos")){
                this.tempDirectoryVideos = config.getInitParameter("tempDirectoryVideos");
            }
            if (parameter.equals("directoryVideos")){
                this.directoryVideos = config.getInitParameter("directoryVideos");              
            }
            if (parameter.equals("publicDirectoryVideos")){
                this.publicDirectoryVideos = config.getInitParameter("publicDirectoryVideos");
            }
            /*end of configuration parameters about video*/

            /*stars configuration parameters about VOR - Virtual Object Reality*/
            if (parameter.equals("tempDirectoryVORs")){
                this.tempDirectoryVORs = config.getInitParameter("tempDirectoryVORs");
            }
            if (parameter.equals("directoryVORs")){
                this.directoryVORs = config.getInitParameter("directoryVORs");              
            }
            if (parameter.equals("publicDirectoryVORs")){
                this.publicDirectoryVORs = config.getInitParameter("publicDirectoryVORs");
            }
            /*end of configuration parameters about VOR*/
            
            if (parameter.equals("ipAddress")){
                this.ipAddress = config.getInitParameter("ipAddress");
            }
            if (parameter.equals("soType")){
                this.soType = config.getInitParameter("soType");
            }
            if (parameter.equals("userDataBase")){
                this.userDataBase = config.getInitParameter("userDataBase");
            }
            if (parameter.equals("passwordDataBase")){
                this.passwordDataBase = config.getInitParameter("passwordDataBase");
            }
            if (parameter.equals("nameDataBase")){
                this.nameDataBase = config.getInitParameter("nameDataBase");
            }
            if (parameter.equals("portDataBase")){
                this.portDataBase = config.getInitParameter("portDataBase");
            }
            if (parameter.equals("ipAddressDataBase")){
                this.ipAddressDataBase = config.getInitParameter("ipAddressDataBase");
            }            
        }
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Sdmm</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Servlet Sdmm at " + request.getContextPath () + "</h3>");
        out.println("<p>Parametros de configuracao da aplicacao carregados!</p>");
        out.println("<a href='Prototipo.jsp'>Prototipo SDMM</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Servlets that starts all configuration parameters to Sdmm application.";
    }
    // </editor-fold>
}