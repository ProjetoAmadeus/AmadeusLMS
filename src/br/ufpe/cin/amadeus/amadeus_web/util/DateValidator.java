/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class DateValidator {

	private boolean empty;
	private int day, month, year;
	private final String key = "invalidDate";
	
	public DateValidator(ActionMessages msgs, String stringDay,
			String stringMonth, String stringYear, boolean futureDate) {
		
		int day = -1, month = -1, year = -1;
		ActionMessages messages = new ActionMessages();
		
		if (stringDay.length() == 0 &&
				stringMonth.length() == 0 &&
				stringYear.length() == 0) {
			empty = true;
			return;
		}

		try {
			day = Integer.parseInt(stringDay);
		} catch (NumberFormatException nfe) {
			messages.add(key, new ActionMessage("errors.invalidDate.day"));
		}
		try {
			month = Integer.parseInt(stringMonth);
		} catch (NumberFormatException nfe) {
			messages.add(key, new ActionMessage("errors.invalidDate.month"));
		}
		try {
			year = Integer.parseInt(stringYear);
		} catch (NumberFormatException nfe) {
			messages.add(key, new ActionMessage("errors.invalidDate.year"));
		}
		
		if (messages.isEmpty()) {
			this.setMonth(month, messages);
			if(futureDate) {
				this.setYear(year, messages);
			} else {
				this.setPastYear(year, messages);
			}
			this.setDay(day, messages);
		}
		msgs.add(messages);
	}
	
	private void setDay(int day, ActionMessages messages) {
		this.day = day;
		if (month == 2) {
			if (year % 4 != 0) {
				if (day < 1 || day > 28)
					messages.add(key, new ActionMessage("errors.invalidDate.day"));
			} else {
				if (day < 1 || day > 29)
					messages.add(key, new ActionMessage("errors.invalidDate.day"));
			}
		}
		else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day < 1 || day > 30)
				messages.add(key, new ActionMessage("errors.invalidDate.day"));
		} else {
			if (day < 1 || day > 31)
				messages.add(key, new ActionMessage("errors.invalidDate.day"));
		}
	}
	
	private void setMonth(int month, ActionMessages messages) {
		this.month = month;
		if (month < 1 || month > 12) {
			this.month = 1;
			messages.add(key, new ActionMessage("errors.invalidDate.month"));
		}
	}
	
	private void setYear(int year, ActionMessages messages) {
		if(year < 0){
			messages.add(key, new ActionMessage("errors.invalidDate.year"));
		}
		this.year = year;
	}
	
	private void setPastYear(int year, ActionMessages messages) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(System.currentTimeMillis());
		int y = gc.get(Calendar.YEAR);
		if(year >= y){
			messages.add(key, new ActionMessage("errors.invalidDate.year"));
		}
		this.year = year;
	}
	
	
	public Date getDate() {
		if (empty)
			return null;
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		return gc.getTime();
	}
}
