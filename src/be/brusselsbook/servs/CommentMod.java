package be.brusselsbook.servs;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/commentmod")
public class CommentMod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<BookComment> selectSignaledComments(){
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		List<BookComment> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), ServerUtils.SELECT_SIGNALED_COMMENT);
		while(AccessUtils.next(set)){
			results.add(bookCommentAccess.safeMap(set));
		}
		return results;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookComment> comments = selectSignaledComments();
		request.setAttribute("comments", comments);
		if(comments.isEmpty()){
			request.setAttribute("warning", "No comments have been reported.");
		}
		getServletContext().getRequestDispatcher(ServerUtils.COMMENTMODJSPFILE).forward(request, response);
	}
}
