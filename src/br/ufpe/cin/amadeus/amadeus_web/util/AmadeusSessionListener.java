package br.ufpe.cin.amadeus.amadeus_web.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.OnlineUser;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public final class AmadeusSessionListener implements HttpSessionAttributeListener, HttpSessionListener {

	public static HashMap<String,HttpSession> sessionMap = new HashMap<String, HttpSession>();
	
    public AmadeusSessionListener() {
    
    }

    private void addOnlineUser(HttpSession session) {
    	if(session != null) {
			AccessInfo user = null;
			
			if(session.getAttribute("user") != null){
				user = (AccessInfo) session.getAttribute("user");
				user = Facade.getInstance().searchUserById(user.getId());
				OnlineUser.addUser(session.getId(), user);
			}
		}
    }
    
    private void removeOnlineUser(HttpSession session) {
    	if(session != null) {
			OnlineUser.removeUser(session.getId());
		}
    }
    
	public void attributeAdded(HttpSessionBindingEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		this.addOnlineUser(session);
	}

	public void attributeRemoved(HttpSessionBindingEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		this.removeOnlineUser(session);
	}

	public void attributeReplaced(HttpSessionBindingEvent sessionEvent) {
		//HttpSession session = sessionEvent.getSession();

        //System.out.println("[MySessionListener] Session attributeReplaced: "+session.getAttribute("user"));
	}

	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		this.addOnlineUser(session);
		sessionMap.put(session.getId(),session);
		
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		this.removeOnlineUser(session);
		sessionMap.remove(session.getId());
	}
}