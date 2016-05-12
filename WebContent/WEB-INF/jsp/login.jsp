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
    	<c:if test="${empty param.identifier}">
		  <c:set var="identifiertext" value="${sessionScope.IDENTIFIERTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.identifier}">
		  <c:set var="identifiertext" value="${param.identifier}" scope="page"/>
		</c:if>
		
	     <form class="logform" method="post" action="login">
	       <div>Enter your credentials</div>
	       <input class="log-input" id="identifier" type="text" name="identifier" maxlength="50" value="<c:out value="${pageScope.identifiertext}"/>" />
	       <br />
	       <input class="log-input" id="password" type="password" name="password" maxlength="16" />
	       <br />
	       <input class="log-submit" type="submit" value="Log in" onclick="return checkform();"/>
	       <br />
	     </form>
	     <a href="usercreate" class="notconnected">Don't have an account ? Create one !</a>
	     <script>
	 		buildInputDefault("#identifier", "<c:out value="${sessionScope.IDENTIFIERTITLE}"/>");
			//buildInputDefault("#password", "<c:out value="${requestScope.PASSWORDTITLE}"/>");
			function checkform(){
				if($("#identifier").val() == "<c:out value="${sessionScope.IDENTIFIERTITLE}"/>"){
		    		$("#identifier").val("");
		    	}
		    	
			}
		</script>	
	     
	     </div>
	     <c:if test="${sessionScope.connected}">
	       <c:redirect url="home">
	       </c:redirect>
	     </c:if>
		</div>     
    </body>

    </html>