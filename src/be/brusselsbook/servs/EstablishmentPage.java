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
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.access.EstablishmentDeletionAccess;
import be.brusselsbook.sql.access.EstablishmentModificationAccess;
import be.brusselsbook.sql.access.TagAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentDeletion;
import be.brusselsbook.sql.data.EstablishmentModification;
import be.brusselsbook.sql.data.Tag;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/establishment")
public class EstablishmentPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
		EstablishmentModificationAccess emAccess = AccessFactory.getInstance().getEstablishmentModifiacationAccess();
		EstablishmentDeletionAccess edAccess = AccessFactory.getInstance().getEstablishmentDeletionAccess();
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		TagAccess tAccess = AccessFactory.getInstance().getTagAccess();
		String eidParam = request.getParameter("eid");
		if (eidParam == null) {
			ServerUtils.redirectTo(response, "home");
		} else {
			Long eid = Long.parseLong(eidParam);
			Establishment establishment = eAccess.withEid(eid);
			EstablishmentModification oldModif = emAccess.withOldEid(eid);
			EstablishmentModification newModif = emAccess.withNewEid(eid);
			EstablishmentDeletion deletion = edAccess.withEid(eid);
			Establishment fullEstablishment = AccessUtils.findFull(establishment);
			Address address = AccessUtils.getAddresFor(establishment);
			List<BookComment> comments = bookCommentAccess.withEid(eid);
			Map<Long, String> commentAuthors = AccessUtils.getAuthorsFor(comments);
			List<Tag> tags = tAccess.getObjects();
			Map<String, Integer> tagCounters = AccessUtils.getCountersFor(tags, eid);
			BookUser user = (BookUser) request.getSession().getAttribute("user");
			Long uid = user != null ? user.getUid() : null;
			Map<String, Boolean> tagApposed = user == null ? null : AccessUtils.getApposedFor(tags, eid, uid);
			request.setAttribute("establishment", fullEstablishment);
			request.setAttribute("establishmentAddress", address);
			request.setAttribute("comments", comments);
			request.setAttribute("commentAuthors", commentAuthors);
			request.setAttribute("tags", tags);
			request.setAttribute("tagCounters", tagCounters);
			request.setAttribute("tagApposed", tagApposed);
			if(deletion != null){
				String error = "This establishment has been deleted and is not visible on the site.";
				request.setAttribute("error", error);
			}
			if(oldModif != null){
				String warning = "Edited establishment (not visible on site)"
						+ " see  <a href='establishment?eid="+oldModif.getNewEID()+"'>#"+oldModif.getNewEID()+"</a>.";
				request.setAttribute("warning", warning);
			}
			if(newModif != null){
				String message = "This establishment was previously here <a href='establishment?eid="+newModif.getOldEID()+"'>#"+newModif.getOldEID()+"</a>.";
				request.setAttribute("message", message);
			}
			getServletContext().getRequestDispatcher(ServerUtils.ESTABLISHMENTJSPFILE).forward(request, response);
		}
	}

}
