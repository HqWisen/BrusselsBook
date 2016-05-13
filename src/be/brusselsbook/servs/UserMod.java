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
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;



@WebServlet("/usermod")
public class UserMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookUserAccess<BookUser> bAccess;
	
	public UserMod(){
		bAccess = AccessFactory.getInstance().getBookUserAccess();
	}
	private List<BookUser> select(String query){
		List<BookUser> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while(AccessUtils.next(set)){
			results.add(bAccess.safeMap(set));
		}
		return results;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookUser> users = select(ServerUtils.SELECT_USERS);
		List<BookUser> admins = select(ServerUtils.SELECT_ADMINS);
		List<BookUser> delUsers = select(ServerUtils.SELECT_USERS_DEL);
		
		List<BookUser> all = new ArrayList<>();
		all.addAll(users);
		all.addAll(delUsers);
		all.addAll(admins);
		Map<Long, Integer> userNumberOfComments = AccessUtils.getNumberOfCommentsForUsers(all);
		
		request.setAttribute("users", users);
		request.setAttribute("admins", admins);
		request.setAttribute("delUsers", delUsers);
		request.setAttribute("userNumberOfComments", userNumberOfComments);
		
		getServletContext().getRequestDispatcher(ServerUtils.USERMODJSPFILE).forward(request, response);		
	}

}
