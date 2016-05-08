package be.brusselsbook.utils;

import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;
import be.brusselsbook.sql.data.BookUser;

public final class ServerUtils {

	public static final String SEARCHJSPFILE = ServerUtils.getJspPath("search.jsp");
	public static final String LOGINJSPFILE = ServerUtils.getJspPath("login.jsp");
	public static final String HOMEJSPFILE = ServerUtils.getJspPath("home.jsp");
	public static final String ESTABLISHMENTJSPFILE = ServerUtils.getJspPath("establishment.jsp");
	public static final String WEBINF = "/WEB-INF/";
	public static final String JSPDIR = "jsp/";
	
	public static String getJspPath(String jspname) {
		return WEBINF+JSPDIR+jspname;
	}

	public static void setConnectedSession(BookUser bookUser, HttpSession session) {
		AdministratorAccess adminAccess = AccessFactory.getInstance().getAdminstratorAccess();
		boolean isadmin = adminAccess.isAdmin(bookUser.getUid());
		session.setAttribute("user", bookUser);
		session.setAttribute("connected", true);
		session.setAttribute("isadmin", isadmin);
	}

	public static void setDisconnectedSession(HttpSession session) {
		session.setAttribute("user", null);
		session.setAttribute("connected", null);
		session.setAttribute("isadmin", null);
	}
	
}
