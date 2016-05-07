package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BookUserAccess<BookUser> bookUserAccess;

	public Login() {
		bookUserAccess = AccessFactory.getInstance().getBookUserAccess();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(ServerUtils.LOGINJSPFILE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String identifier = req.getParameter("identifier");
		String password = req.getParameter("password");
		String error = null;
		BookUser bookUser = bookUserAccess.withIdentifier(identifier);
		if(bookUser == null){
			error = "this identifier doesn't exist.";
		}else if(!password.equals(bookUser.getPassword())){
			error = "password doesn't match the identifier.";	
		}else{
			ServerUtils.setConnectedSession(bookUser, req.getSession());
		}
		req.setAttribute("error", error);
		getServletContext().getRequestDispatcher(ServerUtils.LOGINJSPFILE).forward(req, resp);
	}

}
