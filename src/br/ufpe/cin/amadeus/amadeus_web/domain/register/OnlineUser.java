package br.ufpe.cin.amadeus.amadeus_web.domain.register;

import java.util.HashMap;

public class OnlineUser {
	
	private static HashMap<String,AccessInfo> users = new HashMap<String,AccessInfo>();
	private static final int OFFLINE = 0;
	private static final int ONLINE = 1;
	
	private OnlineUser() {
		
	}
	
	public static void addUser(String idSession, AccessInfo user) {
		if(!OnlineUser.users.containsKey(idSession)){
			boolean hasAccessInfo = false;
			for (AccessInfo accessInfo : OnlineUser.getUsers().values()) {
				if(accessInfo.getId() == user.getId()) {
					hasAccessInfo = true;
				}
			}
			if (!hasAccessInfo){
				OnlineUser.users.put(idSession, user);
			}
		}
	}

	public static void removeUser(String idSession) {
		OnlineUser.users.remove(idSession);
	}
	
	public static int countUsers() {
		return OnlineUser.users.size();
	}
	
	public static int status(int accessInfoId) {
		int status;
		
		boolean hasAccessInfo = false;
		for (AccessInfo accessInfo : OnlineUser.getUsers().values()) {
			if(accessInfo.getId() == accessInfoId) {
				hasAccessInfo = true;
			}
		}
		
		if(hasAccessInfo) {
			status = OnlineUser.ONLINE;
		} else {
			status = OnlineUser.OFFLINE;
		}
		
		return status;
	}
	
	public static HashMap<String,AccessInfo> getUsers() {
		return OnlineUser.users;
	}

	public static void setUsers(HashMap<String,AccessInfo> users) {
		OnlineUser.users = users;
	}
}
