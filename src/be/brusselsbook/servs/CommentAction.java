package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class CommentAction
 */
@WebServlet("/comment")
public class CommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookCommentAccess bAccess = AccessFactory.getInstance().getBookCommentAccess();
		Long eid = Long.parseLong(request.getParameter("eid"));
		String text = request.getParameter("text");
		Integer score = Integer.parseInt(request.getParameter("score"));
		BookUser user = (BookUser) request.getSession().getAttribute("user");
		Long uid = user.getUid();
		bAccess.createBookComment(uid, eid, score, text);
		String site = "establishment?eid=" + eid + "#form";
		ServerUtils.redirectTo(response, site);
		
	}

}
