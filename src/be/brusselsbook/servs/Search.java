package be.brusselsbook.servs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EstablishmentAccess<Establishment> establishmentAccess = AccessFactory.getInstance().getEstablishmentAccess();
		List<Establishment> results = establishmentAccess.getObjects();
		Map<Long, Address> addresses = AccessUtils.getAddressFor(results);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(results);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(results);
		req.setAttribute("results", results);
		req.setAttribute("addresses", addresses);
		req.setAttribute("numberOfComments", numberOfComments);
		req.setAttribute("averageScores", averageScores);
		getServletContext().getRequestDispatcher(ServerUtils.SEARCHJSPFILE).forward(req, resp);
	}

}
