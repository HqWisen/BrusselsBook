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
import be.brusselsbook.sql.data.Restaurant;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/esedit")
public class EstablishmentEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RestaurantAccess rAccess;
	private HotelAccess hAccess;
	private CafeAccess cAccess;
	private EstablishmentAccess<Establishment> eAccess;

	public EstablishmentEdit() {
		AccessFactory accessFactory = AccessFactory.getInstance();
		rAccess = accessFactory.getRestaurantAccess();
		hAccess = accessFactory.getHotelAccess();
		cAccess = accessFactory.getCafeAccess();
		eAccess = accessFactory.getEstablishmentAccess();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("t") == null && request.getParameter("eid") == null) {
			ServerUtils.redirectTo(response, "admin");
		} else {
			Long eid = Long.parseLong(request.getParameter("eid"));
			EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
			Establishment establishment = eAccess.withEid(eid);
			Address address = AccessUtils.getAddressFor(eid);
			request.setAttribute("t", request.getParameter("t").toLowerCase());
			request.setAttribute("establishment", AccessUtils.findFull(establishment));
			request.setAttribute("address", address);
			AccessUtils.setAttribute(request.getSession(), "ESNAMETITLE", "Establishment name");
			AccessUtils.setAttribute(request.getSession(), "PHONENUMBERTITLE", "Phone number");
			AccessUtils.setAttribute(request.getSession(), "WEBSITETITLE", "Website url");
			AccessUtils.setAttribute(request.getSession(), "STREETTITLE", "Street");
			AccessUtils.setAttribute(request.getSession(), "STREETNUMBERTITLE", "Street number");
			AccessUtils.setAttribute(request.getSession(), "LOCALITYTITLE", "Locality");
			AccessUtils.setAttribute(request.getSession(), "ZIPTITLE", "Zip code");
			AccessUtils.setAttribute(request.getSession(), "warning",
					"All comments & tags associated will no longer be available after editing !");
			getServletContext().getRequestDispatcher(ServerUtils.ESEDITJSPFILE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("t");
		String error = null;
		String message = null;
		boolean success = true;
		String forward = ServerUtils.ESEDITJSPFILE;
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phonenumber");
		String website = request.getParameter("website");
		String street = request.getParameter("street");
		String streetNumber = request.getParameter("streetnumber");
		String locality = request.getParameter("locality");
		String zip = request.getParameter("zip");
		Administrator user = (Administrator) request.getSession().getAttribute("user");
		Long eid = Long.parseLong(request.getParameter("eid"));
		Establishment establishment = eAccess.withEid(eid);
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
			Address address = new Address(street, streetNumber, locality, zip, 0.0f, 0.0f);
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
					// FIXME halfclosedday + lat lng
					Restaurant r = rAccess.editRestaurant(aid, eid, name, phoneNumber, website, address,
							establishment.getTypeId(), priceRange, banquetPlaces, takeaway, delivery, "");
					forward = ServerUtils.ESMODJSPFILE;
					message = "Restaurant #" + eid + " edited at #" + r.getEid();
				}
			} else if (type.equals("hotel")) {
				String nStars = request.getParameter("stars");
				String nRooms = request.getParameter("rooms");
				String pTwo = request.getParameter("pricefortwo");
				if (nStars == null || nStars.equals("")) {
					error = "No stars given.";
				} else if (nRooms == null || nRooms.equals("")) {
					error = "No number of rooms given.";
				} else if (pTwo == null || pTwo.equals("")) {
					error = "No price for two given.";
				} else {
					Integer stars = Integer.parseInt(nStars);
					Integer rooms = Integer.parseInt(nRooms);
					Float pricefortwo = Float.parseFloat(pTwo);
					Hotel h = hAccess.editHotel(aid, eid, name, phoneNumber, website, address,
							establishment.getTypeId(), stars, rooms, pricefortwo);
					forward = ServerUtils.ESMODJSPFILE;
					message = "Hotel #" + eid + " edited at #" + h.getEid();
				}
			} else if (type.equals("cafe")) {
				Boolean smoking = request.getParameter("smoking") == null ? false : true;
				Boolean snack = request.getParameter("restoration") == null ? false : true;
				Cafe c = cAccess.editCafe(aid, eid, name, phoneNumber, website, address, establishment.getTypeId(),
						smoking, snack);
				forward = ServerUtils.ESMODJSPFILE;
				message = "Cafe #" + eid + " edited at #" + c.getEid();
			}

		}
		AccessUtils.setAttribute(request.getSession(), "error", error);
		AccessUtils.setAttribute(request.getSession(), "message", message);
		if(forward.equals(ServerUtils.ESMODJSPFILE)){
			ServerUtils.redirectTo(response, "esmod");
		}else{
			doGet(request, response);
			//getServletContext().getRequestDispatcher(forward).forward(request, response);			
		}
	}

}
