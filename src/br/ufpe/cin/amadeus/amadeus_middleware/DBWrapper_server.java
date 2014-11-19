/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import java.net.InetAddress;
import java.util.Calendar;

import br.ufpe.cin.middleware.services.naming.Naming;
import br.ufpe.cin.middleware.services.naming.RemoteProcess;
import br.ufpe.cin.middleware.services.naming.exceptions.AlreadyBoundException;
import br.ufpe.cin.middleware.util.Properties;

/**
 * Inicia conexão com o servidor de nomes -
 * br.ufpe.cin.middleware.services.naming.NameServer
 * - enviando a interface da fachada para o mesmo,
 * para que seus métodos sejam acessados pelos clientes
 * 
 * Ficará do lado "servidor" do serviço de banco de dados,
 * e será responsável por instanciar o skeleton, bem como
 * a classe que contém a implementação
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
