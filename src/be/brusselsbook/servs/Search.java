package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.utils.ServerUtils;

@WebServlet("/search")
public class Search extends HttpServlet {

	private static final String SEARCHJSPFILE = ServerUtils.getJspPath("search.jsp");
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(SEARCHJSPFILE).forward(req, resp);
	}

}
