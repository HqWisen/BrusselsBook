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
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class SignalComment
 */
@WebServlet("/signal")
public class SignalComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookCommentAccess bAccess = AccessFactory.getInstance().getBookCommentAccess();
		UserSignalAccess sAccess = AccessFactory.getInstance().getUserSignalAccess();
		HttpSession session = request.getSession();
		Long did = Long.parseLong(request.getParameter("did"));
		BookComment comment = bAccess.withDid(did);
		Long eid = comment.getEid();
		Long uid = ((BookUser)session.getAttribute("user")).getUid();
		if(sAccess.withDidAndUid(did, uid) == null){
			sAccess.createUserSignal(did, uid);	
		}
		AccessUtils.setAttribute(session, "warning", "Thank you for reporting comment #"+did);
		ServerUtils.redirectTo(response, "establishment?eid="+eid);
	}

}
