package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/home")
public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessUtils.setAttribute(req.getSession(), "SEARCHTITLE", ServerUtils.SEARCHTITLE);
		getServletContext().getRequestDispatcher(ServerUtils.HOMEJSPFILE).forward(req, resp);
	}
	
	
	
}

