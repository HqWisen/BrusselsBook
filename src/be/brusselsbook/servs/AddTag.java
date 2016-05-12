package be.brusselsbook.servs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.TagDescribeAccess;

@WebServlet("/addtag")
public class AddTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagDescribeAccess tAccess = AccessFactory.getInstance().getTagDescribeAccess();
		String tagName = request.getParameter("tagname");
		Long uid = Long.parseLong(request.getParameter("uid"));
		Long eid = Long.parseLong(request.getParameter("eid"));
		if(tAccess.withEidAndUidAndTagName(eid, uid, tagName) == null){
			tAccess.createTagDescribe(eid, uid, tagName);			
		}
	}
}
