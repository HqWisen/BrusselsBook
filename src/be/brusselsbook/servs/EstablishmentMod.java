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



@WebServlet("/esmod")
public class EstablishmentMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EstablishmentAccess<Establishment> eAccess;
	
	public EstablishmentMod(){
		eAccess = AccessFactory.getInstance().getEstablishmentAccess();
	}
	private List<Establishment> select(String query){
		List<Establishment> results = new ArrayList<>(); 
		ResultSet set = AccessUtils.executeQuery(AccessFactory.getInstance(), query);
		while(AccessUtils.next(set)){
			results.add(eAccess.safeMap(set));
		}
		return results;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Establishment> establishments = select(ServerUtils.SELECT_ES);
		List<Establishment> delEstablishments = select(ServerUtils.SELECT_ES_DEL);
		List<Establishment> modifEstablishments = select(ServerUtils.SELECT_ES_MODIF);
		
		List<Establishment> all = new ArrayList<>();
		all.addAll(establishments);
		all.addAll(delEstablishments);
		all.addAll(modifEstablishments);
		
		Map<Long, Address> addresses = AccessUtils.getAddressFor(all);
		Map<Long, Integer> numberOfComments = AccessUtils.getNumberOfCommentsFor(all);
		Map<Long, Integer> averageScores = AccessUtils.getAverageScoresFor(all);
		
		request.setAttribute("establishments", establishments);
		request.setAttribute("delEstablishments", delEstablishments);
		request.setAttribute("modifEstablishments", modifEstablishments);
		request.setAttribute("addresses", addresses);
		request.setAttribute("numberOfComments", numberOfComments);
		request.setAttribute("averageScores", averageScores);
		
		getServletContext().getRequestDispatcher(ServerUtils.ESMODJSPFILE).forward(request, response);		
	}

}
