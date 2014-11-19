/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo ï¿½ parte do programa Amadeus Sistema de Gestï¿½o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS ï¿½ um software livre; vocï¿½ pode redistribui-lo e/ou modifica-lo dentro dos termos da Licenï¿½a Pï¿½blica Geral GNU como
publicada pela Fundaï¿½ï¿½o do Software Livre (FSF); na versï¿½o 2 da Licenï¿½a.
 
Este programa ï¿½ distribuï¿½do na esperanï¿½a que possa ser ï¿½til, mas SEM NENHUMA GARANTIA; sem uma garantia implï¿½cita de ADEQUAï¿½ï¿½O a qualquer MERCADO ou APLICAï¿½ï¿½O EM PARTICULAR. Veja a Licenï¿½a Pï¿½blica Geral GNU para maiores detalhes.
 
Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU, sob o tï¿½tulo "LICENCA.txt", junto com este programa, se nï¿½o, escreva para a Fundaï¿½ï¿½o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
			System.out.println("O driver especificado não foi encontrado.");
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar-se ao Banco de Dados");
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
