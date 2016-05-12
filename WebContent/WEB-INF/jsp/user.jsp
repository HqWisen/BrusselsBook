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
    <div class="estapage">
      <div class="estapage-name"><c:out value="${requestScope.pageUser.username}"/></div>
      <div class="estapage-site"><a href="mailto:${requestScope.pageUser.emailAddress}"><c:out value="${requestScope.pageUser.emailAddress}"/></a></div>
      <div class="estapage-comments">
         <c:forEach items="${requestScope.comments}" var="comment">
           <div class="estapage-comment">
             <div class="estapage-commenttext"><c:out value="${comment.text}"/></div>
             <div class="estapage-commentauthor">${requestScope.pageUser.username} on <a class="simplelink" href="establishment?eid=${comment.eid}">#${comment.eid}</a></div>
             <div class="estapage-commentdate"><c:out value="${comment.creationDate}"/></div>
             <div class="estapage-commentscore">
		       <c:forEach var="i" begin="1" end="${comment.score}" step="1">
			     <img src="image/star.png" />
			   </c:forEach>
		       <c:forEach var="i" begin="1" end="${5 - comment.score}" step="1">
			     <img src="image/star-off.png" />
		       </c:forEach>
              </div>
           </div>
      	 </c:forEach>
       </div>
        
    </div>
  </div>
  

</body>

</html>