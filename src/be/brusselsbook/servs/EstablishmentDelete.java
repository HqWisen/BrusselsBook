package be.brusselsbook.servs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.access.EstablishmentDeletionAccess;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/esdelete")
public class EstablishmentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EstablishmentDeletionAccess deletionAccess = AccessFactory.getInstance().getEstablishmentDeletionAccess();
		EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
		String undoStr = request.getParameter("undo");
		Long eid = Long.parseLong(request.getParameter("eid"));
		Boolean hard = Boolean.parseBoolean(request.getParameter("hard"));
		Boolean undo = undoStr == null ? false : Boolean.parseBoolean(undoStr);
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute("user");
		String warning = null;
		if (undo) {
			deletionAccess.hardDeleteWithEid(eid);
			warning = "Establishment #"+eid+" is available on the site.";
		} else {
			if (hard) {
				eAccess.hardDeleteWithEid(eid);
				warning = "Establishment #" + eid + " has been deleted from the database.";
			} else {
				deletionAccess.createEstablishmentDeletion(eid, admin.getAid());
				warning = "Establishment #" + eid + " has been deleted from the site.";
			}
		}

		AccessUtils.setAttribute(session, "warning", warning);
		ServerUtils.redirectTo(response, "esmod");
	}

}
