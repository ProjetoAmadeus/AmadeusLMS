/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufpe.cin.amadeus.amadeus_mobile.settings.MobileSettings;

public class RepositoryMobile {
	
	public static final MobileSettings mobileSettings = MobileSettings.getInstance();
	
	private Connection connectionDotReply;

	public void createConnectionDotReply() {
		try {
			Class.forName(mobileSettings.getDatabaseDriverClass());
			this.connectionDotReply = (Connection) DriverManager.getConnection(
					mobileSettings.getDatabaseUrl(), mobileSettings.getDatabaseUsername(), mobileSettings.getDatabasePassword());
			
		} catch (ClassNotFoundException e) {
			System.out.println("O driver especificado n�o foi encontrado.");
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel conectar-se ao Banco de Dados");
		}
	}

	public Connection getConnectionDotReply() {
		createConnectionDotReply();
		return connectionDotReply;
	}

	public void setConnectionDotReply(Connection connectionDotReply) {
		this.connectionDotReply = connectionDotReply;
	}

}
