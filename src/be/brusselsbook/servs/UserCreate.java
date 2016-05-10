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
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookUserAccess<BookUser> bookUserAccess;

	public UserCreate() {
		this.bookUserAccess = AccessFactory.getInstance().getBookUserAccess();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccessUtils.setAttribute(request.getSession(), "USERNAMETITLE", "Your username");
		AccessUtils.setAttribute(request.getSession(), "EMAILTITLE", "Your email");
		AccessUtils.setAttribute(request.getSession(), "PASSWORDTITLE", "Your password");
		getServletContext().getRequestDispatcher(ServerUtils.USERCREATEJSPFILE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String error = null;
		String forward = ServerUtils.USERCREATEJSPFILE;
		if(username.isEmpty()){
			error = "Please enter a username.";
		}else if(email.isEmpty()){
			error = "Please enter a email.";
		}else if(password.length() < ServerUtils.MINIMUMPASSWORDSIZE){
			error = "The password required " + ServerUtils.MINIMUMPASSWORDSIZE + " character minimum !";	
		}else if (!ServerUtils.validEmailAddress(email)) {
			error = "Invalid format of the email address !";
		} else if (!ServerUtils.validUsername(username)) {
			error = "The username required " + ServerUtils.MINIMUMUSERNAMESIZE + " character minimum !";
		}else if (bookUserAccess.isUsernamUsed(username)) {
			error = "this username is already used.";
		} else if (bookUserAccess.isEmailUsed(email)) {
			error = "this email address is already used.";
		} else {
			BookUser bookUser = bookUserAccess.createUser(email, username, password);
			ServerUtils.setConnectedSession(bookUser, request.getSession());
			request.setAttribute("notif", "You are now a BrusselsBook user !");
			request.setAttribute("message",
					"Your account has been successfully created" + " with email address '" + email + "' !");
			forward = ServerUtils.HOMEJSPFILE;
		}
		request.setAttribute("error", error);
		getServletContext().getRequestDispatcher(forward).forward(request, response);
	}

}
