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
		<c:param name="search" value="true"/>
	</c:import>
	<div id="container">
	<c:if test="${not empty requestScope.results}">
		<div class="container-title">Establishments</div>
	</c:if>
	<c:forEach items="${requestScope.results}" var="establishment">
		
		<div class="establishment" onclick="location.href='establishment?eid=${establishment.eid}'">
		     <img src="image/resto.png" />
		     <div class="esta-infos">
		       <div class="esta-name"><c:out value="${establishment.name} ${establishment.type}"/></div>
		       <div class="esta-address"><c:out value="${addresses[establishment.eid]}"/></div>
		       <div class="esta-footer">
		         <div class="esta-contact">
		           <div class="esta-number"><c:out value="${establishment.phoneNumber}"/></div>
		           <a href="<c:out value="${establishment.url}"/>" class="esta-site">
		           <c:out value="${establishment.formattedUrl}"/></a>
		         </div>
		         <div class="esta-score">
		           <div><c:out value="${numberOfComments[establishment.eid]}"/> comments</div>
		           <div>
		             <c:forEach var="i" begin="1" end="${averageScores[establishment.eid]}" step="1">
					 	<img src="image/star.png" />
					 </c:forEach>
		             <c:forEach var="i" begin="1" end="${5 - averageScores[establishment.eid]}" step="1">
						 <img src="image/star-off.png" />
		          	 </c:forEach>
		           </div>
		
		         </div>
		       </div>
		     </div>
		   </div>
			</c:forEach>
	<c:if test="${not empty requestScope.userResults}">
		<div class="container-title">Users</div>
	</c:if>
	
	<c:forEach items="${requestScope.userResults}" var="user">
		
		<div class="establishment" onclick="location.href='user?uid=${user.uid}'">
		      <img src="image/resto.png" />
		     <div class="esta-infos">
		       <div class="esta-name"><c:out value="${user.username}"/></div>
		       <div class="esta-address"><c:out value="${user.emailAddress}"/></div>
		       <div class="esta-footer">
		         <div class="esta-contact">
		           <a href="mailto:<c:out value="${user.emailAddress}"/>" class="esta-site">
		           <c:out value="${user.emailAddress}"/></a>
		         </div>
		         <div class="esta-score">
		           <div>wrote <c:out value="${userNumberOfComments[user.uid]}"/> comments</div>
		         </div>
		       </div>
		     </div>
		   </div>
			</c:forEach>
		
	</div>
	
	
</body>

</html>