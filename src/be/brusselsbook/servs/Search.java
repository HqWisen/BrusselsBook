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
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SEARCH_SQL = 
			"SELECT e.* FROM Establishment e, Address a "
			+ "WHERE e.EID NOT IN (SELECT OldEID from EstablishmentModification) "
			+ "AND e.EID NOT IN (SELECT EID from EstablishmentDeletion) "
			+ "AND (a.EID = e.EID) " +
			"AND (e.EName LIKE ? OR a.Locality LIKE ? " +
			"OR a.PostalCode LIKE ? OR a.Street LIKE ?)";
	
	private static final String SEARCH_USER_SQL = "SELECT * FROM BookUser "
			+ " WHERE UID NOT IN (SELECT UID FROM UserDeletion) "
			+ " AND (Username LIKE ? OR EmailAddress LIKE ?)";
	
	private EstablishmentAccess<Establishment> eAccess;
	private BookUserAccess<BookUser> bAccess;
	
	public Search(){
		this.eAccess = AccessFactory.getInstance().getEstablishmentAccess();
		this.bAccess = AccessFactory.getInstance().getBookUserAccess();
	}
	
	private List<Establishment> searchFor(String question){
		List<Establishment> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeLikeQuery(AccessFactory.getInstance(), SEARCH_SQL, question, question, question, question);
		while(AccessUtils.next(set)){
			results.add(eAccess.safeMap(set));
		}
		return results;
	}
	
	private List<BookUser> searchUserFor(String question){
		List<BookUser> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeLikeQuery(AccessFactory.getInstance(), SEARCH_USER_SQL, question, question);
		while(AccessUtils.next(set)){
			results.add(bAccess.safeMap(set));
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
		List<BookUser> userResults = searchUserFor(question);
		System.out.println(userResults);
		Map<Long, Address> addresses = AccessUtils.getAddressFor(results);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(results);
		Map<Long, Integer> userNumberOfComments = AccessUtils.getNumberOfCommentsForUsers(userResults);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(results);
		req.setAttribute("results", results);
		req.setAttribute("userResults", userResults);
		req.setAttribute("addresses", addresses);
		req.setAttribute("numberOfComments", numberOfComments);
		req.setAttribute("userNumberOfComments", userNumberOfComments);
		req.setAttribute("averageScores", averageScores);
		String message = null;
		String warning = null;
		if(results.isEmpty() && userResults.isEmpty()){
			warning = "No results found !";
		}else if(!userResults.isEmpty() && results.isEmpty()){
			message = "Found " + userResults.size() + " users.";
		}else if(!results.isEmpty() && userResults.isEmpty()){
			message = "Found " + results.size() + " establishment.";	
		}else{
			message = "Found " + results.size() + " establishment";
			message += " and " + userResults.size() + " users.";
		}
		req.setAttribute("message", message);
		req.setAttribute("warning", warning);
		getServletContext().getRequestDispatcher(ServerUtils.SEARCHJSPFILE).forward(req, resp);
	}

}
