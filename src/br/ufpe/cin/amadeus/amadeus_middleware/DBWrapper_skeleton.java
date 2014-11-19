/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import br.ufpe.cin.middleware.exceptions.AmadeusMiddlewareException;
import br.ufpe.cin.middleware.proxies.Skeleton_abstract;

/**
 * Respons�vel por receber as requisi��es vindas
 * do stub do lado cliente e deleg�-las � classe
 * que cont�m a implementa��o dos m�todos.
 * 
 * Ficar� do lado "servidor" do servi�o de banco de dados.
 * 
 * @author Bruno Barros (blbs at cin ufpe br)
 *
 */
public class DBWrapper_skeleton extends Skeleton_abstract {

	public DBWrapper_skeleton() throws AmadeusMiddlewareException {
		super(new DBWrapper_impl());
	}

}
