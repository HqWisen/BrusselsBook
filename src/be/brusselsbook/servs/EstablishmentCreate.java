package be.brusselsbook.servs;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

/**
 * Servlet implementation class EstablishmentCreate
 */
@WebServlet("/escreate")
public class EstablishmentCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantAccess rAccess;

	public EstablishmentCreate() {
		AccessFactory accessFactory = AccessFactory.getInstance();
		rAccess = accessFactory.getRestaurantAccess();
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
		System.out.println("NAME => " + name);
		System.out.println("Website => " + website);
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
				if (pR.equals("") || pR == null) {
					error = "No price range given.";
				} else if (bP.equals("") || bP == null) {
					error = "No banquet places given.";
				} else {
					Integer priceRange = Integer.parseInt(pR);
					Integer banquetPlaces = Integer.parseInt(bP);
					Boolean takeaway = request.getParameter("takeaway") == null ? false : true;
					Boolean delivery = request.getParameter("delivery") == null ? false : true;
					Establishment e = rAccess.createRestaurantFromAdmin(aid, name, phoneNumber, website, street, streetNumber, locality,
							zip, priceRange, banquetPlaces, takeaway, delivery);
					forward = ServerUtils.ADMINJSPFILE;
					message = "Restaurant created with eid " + e.getEid();
				}
			} else if (type.equals("hotel")) {
			} else if (type.equals("cafe")) {

			}

		}
		AccessUtils.setAttribute(request.getSession(), "error", error);
		AccessUtils.setAttribute(request.getSession(), "message", message);
		getServletContext().getRequestDispatcher(forward).forward(request, response);
	}

}
