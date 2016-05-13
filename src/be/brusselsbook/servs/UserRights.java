package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class UserRights
 */
@WebServlet("/rights")
public class UserRights extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessFactory accessFactory = AccessFactory.getInstance();
		AdministratorAccess aAccess = accessFactory.getAdminstratorAccess();
		HttpSession session = request.getSession();
		BookUser user = (BookUser)session.getAttribute("user");
		Long uid = Long.parseLong(request.getParameter("uid"));
		String error = null;
		String message = null;
		if(user != null && aAccess.withUid(user.getUid()) != null){
			AccessUtils.executeInsert(accessFactory, aAccess.INSERT(), uid);
			message = "User #"+uid+" is now administrator.";
		}else{
			error = "Access denied";
		}
		if(error != null){
			AccessUtils.setAttribute(session, "error", error);
			ServerUtils.redirectTo(response, "home");
		}else{
			AccessUtils.setAttribute(session, "message", message);
			ServerUtils.redirectTo(response, "usermod");
		}
		
	}

}
