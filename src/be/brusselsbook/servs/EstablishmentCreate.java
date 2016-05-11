package be.brusselsbook.servs;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.parser.CafeInfos;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.CafeAccess;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.access.HotelAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.Cafe;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.Hotel;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class EstablishmentCreate
 */
@WebServlet("/escreate")
public class EstablishmentCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantAccess rAccess;
	private HotelAccess hAccess;
	private CafeAccess cAccess;

	public EstablishmentCreate() {
		AccessFactory accessFactory = AccessFactory.getInstance();
		rAccess = accessFactory.getRestaurantAccess();
		hAccess = accessFactory.getHotelAccess();
		cAccess = accessFactory.getCafeAccess();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("t") == null) {
			ServerUtils.redirectTo(response, "admin");
		} else {
			AccessUtils.setAttribute(request.getSession(), "ESNAMETITLE", "Establishment name");
			AccessUtils.setAttribute(request.getSession(), "PHONENUMBERTITLE", "Phone number");
			AccessUtils.setAttribute(request.getSession(), "WEBSITETITLE", "Website url");
			AccessUtils.setAttribute(request.getSession(), "STREETTITLE", "Street");
			AccessUtils.setAttribute(request.getSession(), "STREETNUMBERTITLE", "Street number");
			AccessUtils.setAttribute(request.getSession(), "LOCALITYTITLE", "Locality");
			AccessUtils.setAttribute(request.getSession(), "ZIPTITLE", "Zip code");
			getServletContext().getRequestDispatcher(ServerUtils.ESCREATEJSPFILE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("t");
		String error = null;
		String message = null;
		boolean success = true;
		String forward = ServerUtils.ESCREATEJSPFILE;
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phonenumber");
		String website = request.getParameter("website");
		String street = request.getParameter("street");
		String streetNumber = request.getParameter("streetnumber");
		String locality = request.getParameter("locality");
		String zip = request.getParameter("zip");
		Administrator user = (Administrator) request.getSession().getAttribute("user");
		Long aid = user.getAid();
		for (String key : Arrays.asList("name", "phonenumber", "street", "streetnumber", "locality", "zip")) {
			String param = request.getParameter(key);
			if (param == null || param.isEmpty()) {
				success = false;
				error = "The " + key + " field is empty.";
				break;
			}
		}
		if (success) {
			if (type.equals("restaurant")) {
				String pR = request.getParameter("pricerange");
				String bP = request.getParameter("banquetplaces");
				if (pR == null || pR.equals("")) {
					error = "No price range given.";
				} else if (bP == null || bP.equals("")) {
					error = "No banquet places given.";
				} else {
					Integer priceRange = Integer.parseInt(pR);
					Integer banquetPlaces = Integer.parseInt(bP);
					Boolean takeaway = request.getParameter("takeaway") == null ? false : true;
					Boolean delivery = request.getParameter("delivery") == null ? false : true;
					Establishment e = rAccess.createRestaurantFromAdmin(aid, name, phoneNumber, website, street, streetNumber, locality,
							zip, 0.0f, 0.0f, priceRange, banquetPlaces, takeaway, delivery);
					forward = ServerUtils.ADMINJSPFILE;
					message = "Restaurant created with eid " + e.getEid();
				}
			} else if (type.equals("hotel")) {
				String nStars = request.getParameter("stars");
				String nRooms = request.getParameter("rooms");
				String pTwo = request.getParameter("pricefortwo");
				if (nStars == null || nStars.equals("")) {
					error = "No stars given.";
				} else if (nRooms == null || nRooms.equals("")) {
					error = "No number of rooms given.";
				}else if (pTwo == null || pTwo.equals("")) {
					error = "No price for two given.";
				}else{
					Integer stars = Integer.parseInt(nStars);
					Integer rooms = Integer.parseInt(nRooms);
					Float pricefortwo = Float.parseFloat(pTwo);
					Address address = new Address(street, streetNumber, locality, zip, 0.0f, 0.0f);
					Hotel h = hAccess.createHotelForAdmin(aid, name, phoneNumber, website, address, stars, rooms, pricefortwo);
					forward = ServerUtils.ADMINJSPFILE;
					message = "Hotel created with eid " + h.getEid();					
				}
			} else if (type.equals("cafe")) {
				Boolean smoking = request.getParameter("smoking") == null ? false : true;
				Boolean snack = request.getParameter("restoration") == null ? false : true;
				CafeInfos infos = EstablishmentAccess.createCafeInfos(name, phoneNumber, website, street, streetNumber, locality, zip, 0.0f, 0.0f, smoking, snack);
				Cafe c = cAccess.createCafeFromAdmin(aid, infos);
				forward = ServerUtils.ADMINJSPFILE;
				message = "Café created with eid " + c.getEid();
			}

		}
		AccessUtils.setAttribute(request.getSession(), "error", error);
		AccessUtils.setAttribute(request.getSession(), "message", message);
		getServletContext().getRequestDispatcher(forward).forward(request, response);
	}

}
