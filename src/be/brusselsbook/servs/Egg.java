package be.brusselsbook.servs;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import be.brusselsbook.sql.data.TagDescribe;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/egg")
public class Egg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String R1 = "SELECT u.* FROM BookComment c, BookUser u "
			+ "WHERE (c.UID = u.UID) AND  (c.Score > 3) "
			+ "AND EXISTS(SELECT c2.* FROM BookComment c2 WHERE (c2.UID = 7) "
			+ "AND (c.EID = c2.EID) AND (c2.Score > 3)) GROUP BY c.UID HAVING COUNT(DISTINCT c.EID) > 2";

	private static final String R3 = "SELECT * FROM Establishment e WHERE "
			+ "(SELECT COUNT(*) FROM BookComment c WHERE c.EID = e.EID) <= 1";
	
	private static final String R4 = "SELECT u.* FROM Administrator a, EstablishmentCreation ec, BookUser u "
			+ "WHERE ec.AID = a.AID AND a.UID = u.UID "
			+ "AND NOT EXISTS(SELECT * FROM BookComment c WHERE c.UID = a.UID AND c.EID = ec.EID) "
			+ "GROUP BY(ec.AID)";
	
	private static final String R5 = "SELECT e.*, AVG(c.Score) "
			+ "AS AvgScore FROM BookComment c, Establishment e WHERE "
			+ "e.EID = c.EID AND c.EID IN "
			+ "(SELECT e.EID FROM Establishment e WHERE "
			+ "(SELECT COUNT(*) FROM BookComment c WHERE e.EID = c.EID ) >= 3 ) "
			+ "GROUP BY (c.EID) ORDER BY AvgScore";


	private static final String R6 = "SELECT td.TagName, (SELECT AVG(c.Score) "
			+ "FROM BookComment c, TagDescribe td2 "
			+ "WHERE (td.TagName = td2.TagName) AND (c.EID = td2.EID)) as AvgScore "
			+ "FROM BookComment c, TagDescribe td "
			+ "GROUP BY td.TagName HAVING COUNT(DISTINCT td.EID) > 4 ORDER BY AvgScore";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer r = Integer.parseInt(request.getParameter("r"));
		switch (r) {
		case 1:
			doR1(request, response);
			request.setAttribute("message", R1);
			break;
		case 3:
			doR3(request, response);
			request.setAttribute("message", R3);
			break;
		case 4:
			doR4(request, response);
			request.setAttribute("message", R4);
			break;
		case 5:
			doR5(request, response);
			request.setAttribute("message", R5);
			break;
		case 6:
			doR6(request, response);
			request.setAttribute("message", R6);
			break;
		}
		getServletContext().getRequestDispatcher(ServerUtils.EGGJSPFILE).forward(request, response);

	}

	private List<BookUser> selectUsers(String query) {
		BookUserAccess<BookUser> bAccess = AccessFactory.getInstance().getBookUserAccess();
		List<BookUser> results = new ArrayList<>();
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while (AccessUtils.next(set)) {
			results.add(bAccess.safeMap(set));
		}
		return results;
	}
	
	private List<Establishment> selectEstablishment(String query) {
		EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
		List<Establishment> results = new ArrayList<>();
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while (AccessUtils.next(set)) {
			results.add(eAccess.safeMap(set));
		}
		return results;
	}

	private List<TagDescribe> selectTagDescrive(String query) {
		List<TagDescribe> results = new ArrayList<>();
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while (AccessUtils.next(set)) {
			TagDescribe td = new TagDescribe();
			try {
				td.setTagName(set.getString("TagName"));
				// NOTE score is in uid (la flemme)
				td.setNumber(Float.parseFloat(set.getString("AvgScore")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			results.add(td);
		}
		return results;
	}

	
	private void doR1(HttpServletRequest request, HttpServletResponse response) {
		List<BookUser> users = selectUsers(R1);
		Map<Long, Integer> userNumberOfComments = AccessUtils.getNumberOfCommentsForUsers(users);
		request.setAttribute("users", users);
		request.setAttribute("userNumberOfComments", userNumberOfComments);
		if (users.isEmpty()) {
			request.setAttribute("warning", "No results found !");
		}
	}

	private void doR3(HttpServletRequest request, HttpServletResponse response) {
		List<Establishment> establishments = selectEstablishment(R3);
		
		Map<Long, Address> addresses = AccessUtils.getAddressFor(establishments);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(establishments);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(establishments);
		
		request.setAttribute("establishments", establishments);
		request.setAttribute("addresses", addresses);
		request.setAttribute("numberOfComments", numberOfComments);
		request.setAttribute("averageScores", averageScores);
		if(establishments.isEmpty()){
			request.setAttribute("warning", "No results found !");			
		}
	}

	private void doR5(HttpServletRequest request, HttpServletResponse response) {
		List<Establishment> establishments = selectEstablishment(R5);
		
		Map<Long, Address> addresses = AccessUtils.getAddressFor(establishments);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(establishments);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(establishments);
		
		request.setAttribute("establishments", establishments);
		request.setAttribute("addresses", addresses);
		request.setAttribute("numberOfComments", numberOfComments);
		request.setAttribute("averageScores", averageScores);
		if(establishments.isEmpty()){
			request.setAttribute("warning", "No results found !");			
		}
	}

	private void doR4(HttpServletRequest request, HttpServletResponse response) {
		List<BookUser> users = selectUsers(R4);
		Map<Long, Integer> userNumberOfComments = AccessUtils.getNumberOfCommentsForUsers(users);
		request.setAttribute("users", users);
		request.setAttribute("userNumberOfComments", userNumberOfComments);
		if (users.isEmpty()) {
			request.setAttribute("warning", "No results found !");
		}
	}

	private void doR6(HttpServletRequest request, HttpServletResponse response) {
		List<TagDescribe> describers = selectTagDescrive(R6);
		request.setAttribute("tags", describers);
		if(describers.isEmpty()){
			request.setAttribute("warning", "No results found !");			
		}
	}


}
