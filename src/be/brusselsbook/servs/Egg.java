package be.brusselsbook.servs;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/egg")
public class Egg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String R1 = "SELECT u.* FROM BookComment c, BookUser u "
			+ "WHERE (c.UID = u.UID) AND  (c.Score > 3) "
			+ "AND EXISTS(SELECT c2.* FROM BookComment c2 WHERE (c2.UID = 7) "
			+ "AND (c.EID = c2.EID) AND (c2.Score > 3)) GROUP BY c.UID HAVING COUNT(DISTINCT c.EID) > 2";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer r = Integer.parseInt(request.getParameter("r"));
		switch (r) {
		case 1:
			doR1(request, response);
			request.setAttribute("message", R1);
			break;
		}
		getServletContext().getRequestDispatcher(ServerUtils.EGGJSPFILE).forward(request, response);

	}

	private List<BookUser> selectUsers(String query) {
		BookUserAccess<BookUser> bAccess = AccessFactory.getInstance().getBookUserAccess();
		List<BookUser> results = new ArrayList<>();
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while (AccessUtils.next(set)) {
			results.add(bAccess.safeMap(set));
		}
		return results;
	}

	private void doR1(HttpServletRequest request, HttpServletResponse response) {
		List<BookUser> users = selectUsers(R1);
		Map<Long, Integer> userNumberOfComments = AccessUtils.getNumberOfCommentsForUsers(users);
		request.setAttribute("users", users);
		request.setAttribute("userNumberOfComments", userNumberOfComments);
		if (users.isEmpty()) {
			request.setAttribute("warning", "No results found !");
		}
	}
}
