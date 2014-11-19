/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
			System.err.println("n�o enviou");
		}
		this.interrupt();
		Receiver.threads.remove(this);
	}
}