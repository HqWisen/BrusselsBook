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
	public static final String ESEDITJSPFILE = ServerUtils.getJspPath("esedit.jsp");
	public static final String USERJSPFILE = ServerUtils.getJspPath("user.jsp");
	public static final String ESMODJSPFILE = ServerUtils.getJspPath("esmod.jsp");
	public static final String USERMODJSPFILE = ServerUtils.getJspPath("usermod.jsp");
	public static final String COMMENTMODJSPFILE = ServerUtils.getJspPath("commentmod.jsp");
	public static final String WEBINF = "/WEB-INF/";
	public static final String JSPDIR = "jsp/";
	public static final String SEARCHTITLE = "Establishment, locality, zip, street, user, email";
	public static final int MINIMUMUSERNAMESIZE = 4;
	public static final int MINIMUMPASSWORDSIZE = 4;
	
	public static final String SEARCH_SQL = 
			"SELECT e.* FROM Establishment e, Address a "
			+ "WHERE e.EID NOT IN (SELECT OldEID from EstablishmentModification) "
			+ "AND e.EID NOT IN (SELECT EID from EstablishmentDeletion) "
			+ "AND (a.EID = e.EID) " +
			"AND (e.EName LIKE ? OR a.Locality LIKE ? " +
			"OR a.PostalCode LIKE ? OR a.Street LIKE ?)";
	
	public static final String SEARCH_USER_SQL = "SELECT * FROM BookUser "
			+ " WHERE UID NOT IN (SELECT UID FROM UserDeletion) "
			+ " AND (Username LIKE ? OR EmailAddress LIKE ?)";

	public static final String SELECT_ES_DEL = "SELECT e.* from EstablishmentDeletion d "
			+ ", Establishment e WHERE e.EID = d.EID";
	
	public static final String SELECT_ES_MODIF = "SELECT e.* from EstablishmentModification m "
			+ ", Establishment e WHERE e.EID = m.OldEID";
	
	public static final String SELECT_ES = "SELECT * FROM Establishment e "
			+ "WHERE e.EID NOT IN (SELECT OldEID from EstablishmentModification) "
			+ "AND e.EID NOT IN (SELECT EID from EstablishmentDeletion)";
	
	public static final String SELECT_USERS = "SELECT * FROM BookUser u "
			+ "WHERE u.UID NOT IN (SELECT UID FROM UserDeletion) "
			+ "AND u.UID NOT IN(SELECT UID FROM Administrator)";

	public static final String SELECT_ADMINS = "SELECT u.* FROM BookUser u, "
			+ "Administrator a WHERE u.UID = a.UID " 
			+ "AND a.UID NOT IN (SELECT UID FROM UserDeletion)";

	public static final String SELECT_USERS_DEL = "SELECT u.* from UserDeletion d "
			+ ", BookUser u WHERE u.UID = d.UID";
	
	public static final String SELECT_SIGNALED_COMMENT = "SELECT b.* FROM UserSignal s, "
			+ "BookComment b WHERE "
			+ "b.DID = s.DID";

	
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
