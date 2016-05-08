<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
    <html>

    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="description" content="Book of Brussels Horeca !">
      <link rel="stylesheet" type="text/css" href="css/style.css" />
      <link rel="icon" href="image/logoULB.png" />
      <title>BrusselsBook</title>
    </head>

    <body>
		<c:import url="tabs.jsp">
		</c:import>
	    <div id="container">
	     <div class="formwrapper">
    
	     <form class="logform" method="post" action="login">
	       <div>Enter your credentials</div>
	       <input class="log-input" type="text" name="identifier" value="<c:out value="${param.identifier}"/>" />
	       <br />
	       <input class="log-input" type="password" name="password" maxlength="16" />
	       <br />
	       <input class="log-submit" type="submit" value="Log in" />
	       <br />
	     </form>
	     </div>
	     <c:if test="${sessionScope.connected}">
	       <c:redirect url="home">
	       </c:redirect>
	     </c:if>
		</div>     
    </body>

    </html>