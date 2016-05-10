package be.brusselsbook.servs;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	private static final String SEARCH_SQL = 
			"SELECT e.* FROM Establishment e, Address a WHERE (a.EID = e.EID) " +
			"and (e.EName LIKE ? or a.Locality LIKE ? " +
			"or a.PostalCode LIKE ? or a.Street LIKE ?)";
	
	private EstablishmentAccess<Establishment> eAccess;
	
	public Search(){
		this.eAccess = AccessFactory.getInstance().getEstablishmentAccess();
	}
	
	private List<Establishment> searchFor(String question){
		List<Establishment> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeLikeQuery(AccessFactory.getInstance(), SEARCH_SQL, question, question, question, question);
		while(AccessUtils.next(set)){
			results.add(eAccess.safeMap(set));
		}
		return results;
	}
	
	private boolean goodQuestion(String question){
		return question != null && !question.equals(ServerUtils.SEARCHTITLE);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String question = req.getParameter("q");
		question = goodQuestion(question) ? question : "";
		question = question.trim(); // removing space in begin & end
		List<Establishment> results = searchFor(question);
		Map<Long, Address> addresses = AccessUtils.getAddressFor(results);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(results);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(results);
		req.setAttribute("results", results);
		req.setAttribute("addresses", addresses);
		req.setAttribute("numberOfComments", numberOfComments);
		req.setAttribute("averageScores", averageScores);
		if(results.isEmpty()){
			req.setAttribute("warning", "No results found !");
		}else{
			req.setAttribute("message", "Found " + results.size() + " results.");
		}
		getServletContext().getRequestDispatcher(ServerUtils.SEARCHJSPFILE).forward(req, resp);
	}

}
