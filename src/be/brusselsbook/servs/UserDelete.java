package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.access.UserDeletionAccess;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BookUserAccess<BookUser> bAccess = AccessFactory.getInstance().getBookUserAccess();
		UserDeletionAccess deletionAccess = AccessFactory.getInstance().getUserDeletionAccess();
		String undoStr = request.getParameter("undo");
		Long uid = Long.parseLong(request.getParameter("uid"));
		Boolean hard = Boolean.parseBoolean(request.getParameter("hard"));
		Boolean undo = undoStr == null ? false : Boolean.parseBoolean(undoStr);
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute("user");
		String warning = null;
		String error = null;
		
		
		if (!uid.equals(admin.getUid()) && undo) {
			deletionAccess.hardDeleteWithUid(uid);
			warning = "User #"+uid+" account enable.";
		} else if(!uid.equals(admin.getUid())) {
			if (hard) {
				bAccess.hardDeleteWithUid(uid);
				warning = "User #" + uid + " has been deleted from the database.";
			} else {
				deletionAccess.createUserDeletion(uid, admin.getAid());
				warning = "User #" + uid + " account disabled.";
			}
		}
		if(uid.equals(admin.getUid())){
			error = "You cannot delete yourself !";
		}
		AccessUtils.setAttribute(session, "warning", warning);
		AccessUtils.setAttribute(session, "error", error);
		ServerUtils.redirectTo(response, "usermod");
	}

}
