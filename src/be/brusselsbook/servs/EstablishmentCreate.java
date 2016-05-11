package be.brusselsbook.servs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class EstablishmentCreate
 */
@WebServlet("/escreate")
public class EstablishmentCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccessUtils.setAttribute(request.getSession(), "ESNAMETITLE", "Establishment name");
		AccessUtils.setAttribute(request.getSession(), "PHONENUMBERTITLE", "Phone number");
		AccessUtils.setAttribute(request.getSession(), "WEBSITETITLE", "Website url");
		AccessUtils.setAttribute(request.getSession(), "STREETTITLE", "Street");
		AccessUtils.setAttribute(request.getSession(), "STREETNUMBERTITLE", "Street number");
		AccessUtils.setAttribute(request.getSession(), "LOCALITYTITLE", "Locality");
		AccessUtils.setAttribute(request.getSession(), "ZIPTITLE", "Zip code");
		getServletContext().getRequestDispatcher(ServerUtils.ESCREATEJSPFILE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
