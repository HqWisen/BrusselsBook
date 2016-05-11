package be.brusselsbook.utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.BookUser;

public final class ServerUtils {

	public static final String SEARCHJSPFILE = ServerUtils.getJspPath("search.jsp");
	public static final String LOGINJSPFILE = ServerUtils.getJspPath("login.jsp");
	public static final String USERCREATEJSPFILE = ServerUtils.getJspPath("usercreate.jsp");
	public static final String HOMEJSPFILE = ServerUtils.getJspPath("home.jsp");
	public static final String ESTABLISHMENTJSPFILE = ServerUtils.getJspPath("establishment.jsp");
	public static final String ADMINJSPFILE = ServerUtils.getJspPath("admin.jsp");
	public static final String ESCREATEJSPFILE = ServerUtils.getJspPath("escreate.jsp");
	public static final String WEBINF = "/WEB-INF/";
	public static final String JSPDIR = "jsp/";
	public static final String SEARCHTITLE = "Establishment, locality, zip, street ...";
	public static final int MINIMUMUSERNAMESIZE = 4;
	public static final int MINIMUMPASSWORDSIZE = 4;
	
	
	public static String getJspPath(String jspname) {
		return WEBINF+JSPDIR+jspname;
	}

	public static void setConnectedSession(BookUser bookUser, HttpSession session) {
		AdministratorAccess adminAccess = AccessFactory.getInstance().getAdminstratorAccess();
		Administrator admin = adminAccess.withUid(bookUser.getUid());
		session.setAttribute("user", admin != null ? admin : bookUser);
		session.setAttribute("connected", true);
		session.setAttribute("isadmin", admin != null);
	}

	public static void setDisconnectedSession(HttpSession session) {
		session.setAttribute("user", null);
		session.setAttribute("connected", null);
		session.setAttribute("isadmin", null);
	}

	public static void redirectTo(HttpServletResponse response, String site) {
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
	
	public static boolean validEmailAddress(String email){
		return email.split("@").length == 2 && email.split("@")[1].split("\\.").length > 1;
	}

	public static boolean validUsername(String username) {
		return username.length() >= MINIMUMUSERNAMESIZE;
	}
	
}
