package be.brusselsbook.servs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/user")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookUserAccess<BookUser> bAccess = AccessFactory.getInstance().getBookUserAccess();
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		String uidParam = request.getParameter("uid");
		if (uidParam == null) {
			ServerUtils.redirectTo(response, "home");
		} else {
			Long uid = Long.parseLong(uidParam);
			BookUser user = bAccess.withUid(uid);
			List<BookComment> comments = bookCommentAccess.withUid(uid);
			request.setAttribute("comments", comments);
			request.setAttribute("pageUser", user);
			getServletContext().getRequestDispatcher(ServerUtils.USERJSPFILE).forward(request, response);
		}
	}

}
