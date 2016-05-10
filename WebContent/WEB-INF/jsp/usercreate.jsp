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
    	<c:if test="${empty param.username}">
		  <c:set var="usernametext" value="${sessionScope.USERNAMETITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.username}">
		  <c:set var="usernametext" value="${param.username}" scope="page"/>
		</c:if>
    	<c:if test="${empty param.email}">
		  <c:set var="emailtext" value="${sessionScope.EMAILTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.email}">
		  <c:set var="emailtext" value="${param.email}" scope="page"/>
		</c:if>
		
	     <form class="logform" method="post" action="usercreate">
	       <div>Create your account</div>
	       <input class="log-input" id="username" type="text" name="username" maxlength="15" value="<c:out value="${pageScope.usernametext}"/>" />
	       <br />
	       <input class="log-input" id="email" type="text" name="email" value="<c:out value="${pageScope.emailtext}"/>" />
	       <br />
	       <input class="log-input" id="password" type="password" name="password" maxlength="16" />
	       <br />
	       <input class="log-submit" type="submit" value="Create" onclick= "return checkform();" />
	       <br />
	     </form>
	     <script>
	 		buildInputDefault("#username", "<c:out value="${sessionScope.USERNAMETITLE}"/>");
			buildInputDefault("#email", "<c:out value="${sessionScope.EMAILTITLE}"/>");
			//buildInputDefault("#password", "<c:out value="${sessionScope.PASSWORDTITLE}"/>");
		    function checkform(){
		    	if($("#username").val() == "<c:out value="${sessionScope.USERNAMETITLE}"/>"){
		    		$("#username").val("");
		    	}
		    	if($("#email").val() == "<c:out value="${sessionScope.EMAILTITLE}"/>"){
		    		$("#email").val("");
		    	}
		    	if($("#password").val() == "<c:out value="${sessionScope.PASSWORDTITLE}"/>"){
		    		$("#password").val("");
		    	}
		    }	
		</script>	
	     
	     </div>
		</div>     
    </body>

    </html>