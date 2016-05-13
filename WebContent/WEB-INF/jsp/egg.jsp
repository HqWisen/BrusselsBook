<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Book of Brussels Horecxa !">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="image/logoULB.png" />
<title>BrusselsBook</title>
</head>

<body>
	<%@ include file="adminAccess.jsp"  %>

	<c:import url="tabs.jsp">
		<c:param name="search" value="true"/>
	</c:import>
	<div id="container">
	
	
	
	<c:if test="${not empty requestScope.users}">
		<div class="container-title">Users</div>
	</c:if>
	
	<c:forEach items="${requestScope.users}" var="user">
		
		<div class="establishment" onclick="location.href='user?uid=${user.uid}'">
		      <img src="image/user.png" />
		     <div class="esta-infos">
		       <div class="esta-name"><c:out value="${user.username} #${user.uid}"/></div>
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
		     <div class="esta-button">
		     <a class="esta-delete" href="userdelete?uid=${user.uid}&hard=true">Delete</a>
		     <a class="esta-edit" href="userdelete?uid=${user.uid}&hard=false">Disable</a>
		     <a class="esta-edit" href="rights?uid=${user.uid}">Give rights</a>
		   </div>
		     
		   </div>
			</c:forEach>		
	</div>
	
	
</body>

</html>