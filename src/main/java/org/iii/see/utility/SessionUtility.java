package org.iii.see.utility;

import javax.servlet.http.HttpSession;

import org.iii.see.session.User;

public class SessionUtility {

	public static boolean isLogon(HttpSession session) {
		return session.getAttribute("user") != null;
	}

	public static String getUserAccount(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return null;
		} 
		return user.getAccount();
	}

	public static String getUserNickname(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return null;
		} 
		return user.getNickname();
	}
	
	public static User getUser(HttpSession session) {
		return (User)session.getAttribute("user");
	}

	public static void clearSession(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("projectList");
		session.removeAttribute("activeProjectUuid");
		session.removeAttribute("activeProjectName");
	}

    public static String getUserUuid(HttpSession session) {

		return null;
	}
}
