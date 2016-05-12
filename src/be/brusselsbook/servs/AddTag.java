package be.brusselsbook.servs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.TagAccess;
import be.brusselsbook.sql.access.TagDescribeAccess;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.ServerUtils;

@WebServlet("/addtag")
public class AddTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagDescribeAccess tdAccess = AccessFactory.getInstance().getTagDescribeAccess();
		TagAccess tagAccess = AccessFactory.getInstance().getTagAccess();
		String tagName = request.getParameter("tagname");
		Long uid = Long.parseLong(request.getParameter("uid"));
		Long eid = Long.parseLong(request.getParameter("eid"));
		String createParam = request.getParameter("create");
		boolean create = createParam != null && createParam.equals("true");
		boolean success = true;
		String error = null;
		String message = null;
		if(create){
			if(tagAccess.withTagName(tagName) != null){
				error = "The tag '" + tagName +"' already exist.";
				success = false;
			}else{
				tagAccess.createTag(uid, tagName);
				message = "You've created the tag '" + tagName + "' !";
			}
		}
		if(success && tdAccess.withEidAndUidAndTagName(eid, uid, tagName) == null){
			tdAccess.createTagDescribe(eid, uid, tagName);			
		}
		AccessUtils.setAttribute(request.getSession(), "error", error);
		AccessUtils.setAttribute(request.getSession(), "message", message);
		ServerUtils.redirectTo(response, "establishment?eid="+eid);
	}
}
