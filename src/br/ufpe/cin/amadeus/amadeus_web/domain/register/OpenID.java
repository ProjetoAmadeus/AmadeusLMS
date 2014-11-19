/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.register;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class OpenID implements Serializable {
	
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "_identity",unique = true)
	private String identity;
	
	@ManyToOne
	@JoinColumn(name = "ACCESSINFO_ID", nullable = false,
			updatable = false, insertable = false)
	private AccessInfo accessInfo;
	
	private String provideBy;
	
	private String email;
	
	private String name;
	
	public OpenID() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public AccessInfo getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(AccessInfo accessInfo) {
		this.accessInfo = accessInfo;
	}

	public String getProvideBy() {
		return provideBy;
	}
	
	public void setProvideBy(String provideBy) {
		this.provideBy = provideBy;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if(obj instanceof Course && obj != null
				&& ((Course)obj).getId() == this.id ){
			retorno = true;
		}
		
		return retorno;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
