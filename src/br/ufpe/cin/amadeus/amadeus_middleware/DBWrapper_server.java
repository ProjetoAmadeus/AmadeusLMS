/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import java.net.InetAddress;
import java.util.Calendar;

import br.ufpe.cin.middleware.services.naming.Naming;
import br.ufpe.cin.middleware.services.naming.RemoteProcess;
import br.ufpe.cin.middleware.services.naming.exceptions.AlreadyBoundException;
import br.ufpe.cin.middleware.util.Properties;

/**
 * Inicia conex�o com o servidor de nomes -
 * br.ufpe.cin.middleware.services.naming.NameServer
 * - enviando a interface da fachada para o mesmo,
 * para que seus m�todos sejam acessados pelos clientes
 * 
 * Ficar� do lado "servidor" do servi�o de banco de dados,
 * e ser� respons�vel por instanciar o skeleton, bem como
 * a classe que cont�m a implementa��o
 * 
 * @author Bruno Barros (blbs at cin ufpe br)
 *
 */
public class DBWrapper_server {
	
	public static void main(String[] args) {
		RemoteProcess remoteProcess = null;
		try {
			remoteProcess = new RemoteProcess(InetAddress.getLocalHost().getHostAddress(), DBWrapper.class);
			Naming.connect(Properties.services_naming_host, Properties.services_naming_port);
			Naming.bind("Facade", remoteProcess);
			System.out.println("===== Autentication Server is Running Since " + Calendar.getInstance().getTime() + " ====");
		} catch (AlreadyBoundException e) {
			try {
				Naming.connect(Properties.services_naming_host, Properties.services_naming_port);
				Naming.rebind("Facade", remoteProcess);
				System.out.println("===== Autentication Server is Running Since " + Calendar.getInstance().getTime() + " ====");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
