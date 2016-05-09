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
	private String from, elem;
	public Login() {
		this.bookUserAccess = AccessFactory.getInstance().getBookUserAccess();
		this.from = null;
		this.elem = null;
		}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		from = request.getParameter("from");
		elem = request.getParameter("elem");
		getServletContext().getRequestDispatcher(ServerUtils.LOGINJSPFILE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifier = request.getParameter("identifier");
		String password = request.getParameter("password");
		String error = null;
		String forward = ServerUtils.LOGINJSPFILE;
		String redirect = null;
		BookUser bookUser = bookUserAccess.withIdentifier(identifier);
		if(bookUser == null){
			error = "this identifier doesn't exist.";
		}else if(!password.equals(bookUser.getPassword())){
			error = "password doesn't match the identifier.";	
		}else{
			ServerUtils.setConnectedSession(bookUser, request.getSession());
			request.setAttribute("notif", "You are connected.");
			redirect = from == null ? null : from + (elem == null ? "" : "#"+elem);
			forward = ServerUtils.HOMEJSPFILE;
		}
		request.setAttribute("error", error);
		if(redirect != null){
			ServerUtils.redirectTo(response, redirect);
		}else{
			getServletContext().getRequestDispatcher(forward).forward(request, response);		
		}
	}

}
