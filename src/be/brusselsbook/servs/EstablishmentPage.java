package be.brusselsbook.servs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/establishment")
public class EstablishmentPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		
		Long eid = Long.parseLong(request.getParameter("eid"));
		Establishment establishment = eAccess.withEid(eid);
		Address address = EstablishmentAccess.getAddresFor(establishment);
		List<BookComment> comments = bookCommentAccess.withEid(eid); 
		request.setAttribute("establishment", establishment);
		request.setAttribute("establishmentAddress", address);
		request.setAttribute("comments", comments);
		getServletContext().getRequestDispatcher(ServerUtils.ESTABLISHMENTJSPFILE).forward(request, response);
	}

}
