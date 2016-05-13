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
	     <form class="logform" method="post" action="esedit">
	       <div>Edit <c:out value="${requestScope.t}"/> #${establishment.eid}</div>
	       <input name="t" type="hidden" value="<c:out value="${requestScope.t}"/>"/>
	       <input name="eid" type="hidden" value="<c:out value="${establishment.eid}"/>"/>
	       <input class="log-input" id="name" type="text" name="name" maxlength="50" value="<c:out value="${establishment.name}"/>" />
	       <br />
	       <input class="log-input" id="phonenumber" type="text" name="phonenumber" maxlength="50" value="<c:out value="${establishment.phoneNumber}"/>" />
	       <br />
	       <input class="log-input" id="website" type="text" name="website" maxlength="50" value="<c:out value="${establishment.url}"/>" />
	       <br />
	       <input class="log-input" id="street" type="text" name="street" maxlength="50" value="<c:out value="${address.street}"/>" />
	       <br />
	       <input class="log-input" id="streetnumber" type="text" name="streetnumber" maxlength="50" value="<c:out value="${address.number}"/>" />
	       <br />
	       <input class="log-input" id="locality" type="text" name="locality" maxlength="50" value="<c:out value="${address.locality}"/>" />
	       <br />
	       <input class="log-input" id="zip" type="text" name="zip" maxlength="50" value="<c:out value="${address.postalCode}"/>" />
	       <br />
	       <c:if test="${requestScope.t == 'restaurant'}">
	         <label class="formlabel">Average price</label><br/>
	         <input class="log-input" id="pricerange" type="number" name="pricerange" value="<c:out value="${establishment.priceRange}"/>"/>
	         <br />
	         <label class="formlabel">Number of places for banquet</label><br/>
	         <input class="log-input" id="banquetplaces" type="number" name="banquetplaces" value="<c:out value="${establishment.banquetPlaces}"/>"/>
	         <br />
	         <label class="formlabel">This restaurant make takeaway</label>
	         <input type="radio" name="takeaway" value="takeaway" <c:if test="${establishment.takeaway}">checked</c:if>/>
	         <br />	
	         <label class="formlabel">This restaurant make delivery</label>
	         <input type="radio" name="delivery" value="delivery" <c:if test="${establishment.delivery}">checked</c:if>/>
	         <br />	
	       </c:if>
	      <c:if test="${requestScope.t == 'cafe'}">
	         <label class="formlabel">Allow smoking</label>
	         <input type="radio" name="smoking" value="smoking" <c:if test="${establishment.smoke}">checked</c:if>/>
	         <br />	
	         <label class="formlabel">Make restoration</label>
	         <input type="radio" name="restoration" value="restoration" <c:if test="${establishment.restoration}">checked</c:if>/>
	         <br />	
	       </c:if>
	       <c:if test="${requestScope.t == 'hotel'}">
	         <label class="formlabel">Number of stars</label><br/>
	         <input class="log-input" id="stars" type="number" min="1" max="5" name="stars"/>
	         <br />
	         <label class="formlabel">Number of rooms</label><br/>
	         <input class="log-input" id="rooms" type="number" name="rooms" />
	         <br />	
	         <label class="formlabel">Price for two persons</label><br/>
	         <input class="log-input" id="pricefortwo" type="number" name="pricefortwo" step="0.01" />
	         <br />	
	       </c:if>

	     	<input class="log-submit" type="submit" value="Edit" onclick="return checkform();"/>
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