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
    	<c:if test="${empty param.name}">
		  <c:set var="nametext" value="${sessionScope.ESNAMETITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.name}">
		  <c:set var="nametext" value="${param.name}" scope="page"/>
		</c:if>
		
	     <form class="logform" method="post" action="escreate">
	       <div>Enter your credentials</div>
	       <input class="log-input" id="name" type="text" name="name" maxlength="50" value="<c:out value="${pageScope.nametext}"/>" />
	       <br />
	       <input class="log-submit" type="submit" value="Create" onclick="return checkform();"/>
	       <br />
	     </form>
	     <script>
	 		buildInputDefault("#name", "<c:out value="${sessionScope.ESNAMETITLE}"/>");
			function checkform(){
				checkinput("#name", "<c:out value="${sessionScope.ESNAMETITLE}"/>");
			}
		</script>	
	     
	     </div>
		</div>     
    </body>

    </html>