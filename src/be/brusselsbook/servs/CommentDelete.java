package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.UserSignalAccess;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/commentdelete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookCommentAccess commentAccess = AccessFactory.getInstance().getBookCommentAccess();
		UserSignalAccess signalAccess = AccessFactory.getInstance().getUserSignalAccess();
		String undoStr = request.getParameter("undo");
		Long did = Long.parseLong(request.getParameter("did"));
		Boolean undo = undoStr == null ? false : Boolean.parseBoolean(undoStr);
		HttpSession session = request.getSession();
		Boolean isadmin = (Boolean) session.getAttribute("isadmin");
		String warning = null;
		if (!isadmin) {
			AccessUtils.setAttribute(session, "error", "Access denied.");
			ServerUtils.redirectTo(response, "home");
		} else {
			if (undo) {
				signalAccess.hardDeleteWithDid(did);
				warning = "Comment #" + did + " is no longer reported.";
			} else {
				commentAccess.hardDeleteWithDid(did);
				warning = "Comment #" + did + " has been deleted from the database.";
			}

			AccessUtils.setAttribute(session, "warning", warning);
			ServerUtils.redirectTo(response, "commentmod");
		}
	}

}
