/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
/**
 * Classe que encapsula os dados de um post de um forum
 * 
 * @author yuri
 * 
 */

@Entity
public class Message {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	@Column(length=2000)
	private String body;
	
	private Date date;
	
	@OneToOne
	@JoinColumn (name="MESSAGEREPLY_ID")
	private Message messageReply;

	@OneToOne
	@JoinColumn (name="AUTHOR_ID")
	private Person author;
	
	@ManyToOne
	@JoinColumn(name = "FORUM_ID", nullable = false,
			updatable = false, insertable = false)
	private Forum forum;

	public Message() {

	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Message getMessageReply() {
		return messageReply;
	}

	public void setMessageReply(Message messageReply) {
		this.messageReply = messageReply;
	}
	
	
}
