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

		<%@ include file="adminAccess.jsp"  %>
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
    	<c:if test="${empty param.phonenumber}">
		  <c:set var="phonenumbertext" value="${sessionScope.PHONENUMBERTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.phonenumber}">
		  <c:set var="phonenumbertext" value="${param.phonenumber}" scope="page"/>
		</c:if>
		<c:if test="${empty param.website}">
		  <c:set var="websitetext" value="${sessionScope.WEBSITETITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.website}">
		  <c:set var="websitetext" value="${param.website}" scope="page"/>
		</c:if>
		
		<c:if test="${empty param.street}">
		  <c:set var="streettext" value="${sessionScope.STREETTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.street}">
		  <c:set var="streettext" value="${param.street}" scope="page"/>
		</c:if>
		
		<c:if test="${empty param.streetnumber}">
		  <c:set var="streetnumbertext" value="${sessionScope.STREETNUMBERTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.streetnumber}">
		  <c:set var="streetnumbertext" value="${param.streetnumber}" scope="page"/>
		</c:if>

		<c:if test="${empty param.locality}">
		  <c:set var="localitytext" value="${sessionScope.LOCALITYTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.locality}">
		  <c:set var="localitytext" value="${param.locality}" scope="page"/>
		</c:if>

		<c:if test="${empty param.zip}">
		  <c:set var="ziptext" value="${sessionScope.ZIPTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.zip}">
		  <c:set var="ziptext" value="${param.zip}" scope="page"/>
		</c:if>

	     <form class="logform" method="post" action="escreate">
	       <div>Create <c:out value="${param.t}"/></div>
	       <input name="t" type="hidden" value="<c:out value="${param.t}"/>"/>
	       <input class="log-input" id="name" type="text" name="name" maxlength="50" value="<c:out value="${pageScope.nametext}"/>" />
	       <br />
	       <input class="log-input" id="phonenumber" type="text" name="phonenumber" maxlength="50" value="<c:out value="${pageScope.phonenumbertext}"/>" />
	       <br />
	       <input class="log-input" id="website" type="text" name="website" maxlength="50" value="<c:out value="${pageScope.websitetext}"/>" />
	       <br />
	       <input class="log-input" id="street" type="text" name="street" maxlength="50" value="<c:out value="${pageScope.streettext}"/>" />
	       <br />
	       <input class="log-input" id="streetnumber" type="text" name="streetnumber" maxlength="50" value="<c:out value="${pageScope.streetnumbertext}"/>" />
	       <br />
	       <input class="log-input" id="locality" type="text" name="locality" maxlength="50" value="<c:out value="${pageScope.localitytext}"/>" />
	       <br />
	       <input class="log-input" id="zip" type="text" name="zip" maxlength="50" value="<c:out value="${pageScope.ziptext}"/>" />
	       <br />
	       <c:if test="${param.t == 'restaurant'}">
	         <label class="formlabel">Average price</label><br/>
	         <input class="log-input" id="pricerange" type="number" name="pricerange" value="<c:out value="${param.pricerange}"/>"/>
	         <br />
	         <label class="formlabel">Number of places for banquet</label><br/>
	         <input class="log-input" id="banquetplaces" type="number" name="banquetplaces" value="<c:out value="${param.banquetplaces}"/>"/>
	         <br />
	         <label class="formlabel">This restaurant make takeaway</label>
	         <input type="radio" name="takeaway" value="takeaway"/>
	         <br />	
	         <label class="formlabel">This restaurant make delivery</label>
	         <input type="radio" name="delivery" value="delivery"/>
	         <br />	
	       </c:if>
	      <c:if test="${param.t == 'cafe'}">
	         <label class="formlabel">Allow smoking</label>
	         <input type="radio" name="smoking" value="smoking"/>
	         <br />	
	         <label class="formlabel">Make restoration</label>
	         <input type="radio" name="restoration" value="restoration"/>
	         <br />	
	       </c:if>
	       <c:if test="${param.t == 'hotel'}">
	         <label class="formlabel">Number of stars</label><br/>
	         <input class="log-input" id="stars" type="number" min="1" max="5" name="stars" value="<c:out value="${param.stars}"/>"/>
	         <br />
	         <label class="formlabel">Number of rooms</label><br/>
	         <input class="log-input" id="rooms" type="number" name="rooms" value="<c:out value="${param.rooms}"/>"/>
	         <br />	
	         <label class="formlabel">Price for two persons</label><br/>
	         <input class="log-input" id="pricefortwo" type="number" name="pricefortwo" step="0.01" value="<c:out value="${param.pricefortwo}"/>"/>
	         <br />	
	       </c:if>

	     	<input class="log-submit" type="submit" value="Create" onclick="return checkform();"/>
	       <br />
	     </form>
	     <script>
	     buildInputDefault("#name", "<c:out value="${sessionScope.ESNAMETITLE}"/>");
	     buildInputDefault("#phonenumber", "<c:out value="${sessionScope.PHONENUMBERTITLE}"/>");
	     buildInputDefault("#website", "<c:out value="${sessionScope.WEBSITETITLE}"/>");
	     buildInputDefault("#street", "<c:out value="${sessionScope.STREETTITLE}"/>");
	     buildInputDefault("#streetnumber", "<c:out value="${sessionScope.STREETNUMBERTITLE}"/>");
	     buildInputDefault("#locality", "<c:out value="${sessionScope.LOCALITYTITLE}"/>");
	     buildInputDefault("#zip", "<c:out value="${sessionScope.ZIPTITLE}"/>");
	     function checkform(){
			 checkinput("#name", "<c:out value="${sessionScope.ESNAMETITLE}"/>");
			 checkinput("#phonenumber", "<c:out value="${sessionScope.PHONENUMBERTITLE}"/>");
			 checkinput("#website", "<c:out value="${sessionScope.WEBSITETITLE}"/>");
			 checkinput("#street", "<c:out value="${sessionScope.STREETTITLE}"/>");
			 checkinput("#streetnumber", "<c:out value="${sessionScope.STREETNUMBERTITLE}"/>");
			 checkinput("#locality", "<c:out value="${sessionScope.LOCALITYTITLE}"/>");
			 checkinput("#zip", "<c:out value="${sessionScope.ZIPTITLE}"/>");
	     }
		</script>	
	     
	     </div>
		</div>     
    </body>

    </html>