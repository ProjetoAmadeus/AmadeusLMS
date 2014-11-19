/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.sms;

import java.util.ArrayList;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.AmadeusFacade;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile;
import sun.awt.windows.ThemeReader;

public class AddHomeworkThread extends Thread {

	private NoticeMobile sms;
	private FacadeMobile fac;
	private String courseName;

	public AddHomeworkThread(NoticeMobile notice, String courseName) {
		super();
		this.sms = notice;
		this.courseName = courseName;;
		this.fac = FacadeMobile.getInstance();
	}
	
	public NoticeMobile getSms() {
		return sms;
	}

	public void setSms(NoticeMobile sms) {
		this.sms = sms;
	}

	public boolean check() {
		boolean check = false;
		for(Thread t : Receiver.threads) {
			if(t instanceof AddHomeworkThread) {
				if(((AddHomeworkThread)t).equals(this)) continue;
				if(((AddHomeworkThread)t).getSms().getIdCourse() == this.getSms().getIdCourse()) {
					check = true;
				}
			}
		}
		return check;
	}

	public void run() {
		long time = 20000;
		long initialTime = System.currentTimeMillis();
		System.out.println("comecou");
		while((System.currentTimeMillis() - initialTime) < time) {}
		if(!this.check()) {
			System.out.println("!check");
			Sender sender = new Sender();
			sender.createSMS(this.sms, this.courseName);
		} else {
			System.err.println("não enviou");
		}
		this.interrupt();
		Receiver.threads.remove(this);
	}
}