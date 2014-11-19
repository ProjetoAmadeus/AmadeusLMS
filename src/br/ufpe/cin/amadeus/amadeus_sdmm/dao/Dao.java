/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_sdmm.dao;

/**
 * @author Armando Soares Sousa / Antonio Nascimento
 *
 */
/*
 * DAO.java
 *
 * Created on 13 de Julho de 2007, 16:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import br.ufpe.cin.amadeus.amadeus_sdmm.general.Sdmm;


public class Dao {
    
    private String usuario;
    private String senha;
    private String banco;
    private String local;
    private Connection con;
    private Statement stat;
    
    /** Creates a new instance of DAO */
    public Dao() {
        this.usuario = Sdmm.userDataBase;
        this.senha = Sdmm.passwordDataBase;
        this.banco = Sdmm.nameDataBase;
        //this.local = Sdmm.ipAddressDataBase+":"+Sdmm.portDataBase;
        this.local = Sdmm.ipAddressDataBase;
    }
    
    public Connection getCon() {
        return this.con;
    }

    public void setCon(Connection con) {
	this.con = con;
    }

    public Statement getStat() {
	return stat;
    }

    public void setStat(Statement stat) {
	this.stat = stat;
    }

    public void closeConnection(){
        //Confere se ainda est� conectada.
	try {
            if(con != null && !con.isClosed()){
                con.close();
		//System.out.println("Desconectado");
            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }

    public void getConnection(){
        Connection con = null;
        try {
            try {
                Class.forName("org.postgresql.Driver");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            Properties jdbc = new Properties();
            jdbc.put("user",usuario);
            jdbc.put("password",senha);
            String url = "jdbc:postgresql://"+local+"/"+banco;
            System.out.println("url de conexao com o banco: "+url);
            con = DriverManager.getConnection(url,jdbc);
            this.con = con;
        }
        catch(SQLException e) {
            System.out.println("Exception on DAO getConnection : "+e.getMessage());
        }
    }

    public Statement createStament() throws SQLException{
        Statement retorno = null;
            if (this.con != null) {
                retorno = this.con.createStatement();
            }
	return retorno;
    }

    public void executaComandoSQL(String pcomando){
        try {
            this.setStat(this.createStament());
            this.getStat().executeUpdate(pcomando);
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }

    public ResultSet executaConsultaSQL(String pconsulta){
        ResultSet retorno = null;
        try{
            this.setStat(this.createStament());
            retorno = this.getStat().executeQuery(pconsulta);
	} catch (SQLException e){
            e.printStackTrace();
	}
        return retorno;
    }
    
}   