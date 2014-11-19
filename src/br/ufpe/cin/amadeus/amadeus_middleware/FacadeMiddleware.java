/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.middleware.exceptions.AmadeusMiddlewareException;
import br.ufpe.cin.middleware.exceptions.SerializationException;
import br.ufpe.cin.middleware.services.naming.Naming;
import br.ufpe.cin.middleware.services.naming.exceptions.NamingServiceException;

public class FacadeMiddleware {

	private static DBWrapper instance;
	
	public static DBWrapper getInstance() {
		if (instance == null) {
			try {
				//Basta saber onde o servidor de nomes está rodando...
				Naming.connect("localhost", 1900);
				//... e pedir a ele uma instância da fachada, que foi 
				// criada e enviada pelo servidor
				instance = (DBWrapper) Naming.resolve("Facade");
			} catch (AmadeusMiddlewareException e) {
				//exceção levantada caso haja algum erro dentro do middleware
				System.err.println("O servidor de nomes não foi encontrado!");
				System.exit(-1);
			} catch (NamingServiceException e) {
				//exceção levantada caso haja algum erro específico do servidor de nomes
				System.err.println("Problema no servidor de nomes: " + e.getMessage());
				System.exit(-1);
			} catch (SerializationException e) {
				//exceção levantada caso o objeto enviado pelo servidor não possa ser
				//instanciado do lado cliente. 
				System.err.println("O objeto não pôde ser instanciado: " + e.getMessage());
				System.exit(-1);
			}	
		}
		return instance;
	}

	//main para testes
	public static void main(String[] args) {
		DBWrapper f = FacadeMiddleware.getInstance();
		try {
			//a partir daqui, o restante é feito como antes
			AccessInfo user = f.searchUserByLogin("vcp");
			user.getLogin();
			user.getPassword();
			Person person = user.getPerson();
			System.out.println(person.getName() + ":" + person.getGender());
		} catch (AmadeusMiddlewareException e) {
			//agora todos os métodos lançam esta exceção, ela deve ser tratada
			e.printStackTrace();
		}
	}

}
