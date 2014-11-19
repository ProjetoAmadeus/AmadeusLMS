/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
				//Basta saber onde o servidor de nomes est� rodando...
				Naming.connect("localhost", 1900);
				//... e pedir a ele uma inst�ncia da fachada, que foi 
				// criada e enviada pelo servidor
				instance = (DBWrapper) Naming.resolve("Facade");
			} catch (AmadeusMiddlewareException e) {
				//exce��o levantada caso haja algum erro dentro do middleware
				System.err.println("O servidor de nomes n�o foi encontrado!");
				System.exit(-1);
			} catch (NamingServiceException e) {
				//exce��o levantada caso haja algum erro espec�fico do servidor de nomes
				System.err.println("Problema no servidor de nomes: " + e.getMessage());
				System.exit(-1);
			} catch (SerializationException e) {
				//exce��o levantada caso o objeto enviado pelo servidor n�o possa ser
				//instanciado do lado cliente. 
				System.err.println("O objeto n�o p�de ser instanciado: " + e.getMessage());
				System.exit(-1);
			}	
		}
		return instance;
	}

	//main para testes
	public static void main(String[] args) {
		DBWrapper f = FacadeMiddleware.getInstance();
		try {
			//a partir daqui, o restante � feito como antes
			AccessInfo user = f.searchUserByLogin("vcp");
			user.getLogin();
			user.getPassword();
			Person person = user.getPerson();
			System.out.println(person.getName() + ":" + person.getGender());
		} catch (AmadeusMiddlewareException e) {
			//agora todos os m�todos lan�am esta exce��o, ela deve ser tratada
			e.printStackTrace();
		}
	}

}
