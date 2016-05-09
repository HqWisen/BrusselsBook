<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="toolbar-section">
	<div id="toolbar">
		<ul id="lefttabs">
			<li class="tab"><a
				class="<c:if test="${param.home}">active</c:if>" href="home">HOME</a></li>
			<li class="tab"><a
				class="<c:if test="${param.search}">active</c:if>" href="search">ADVANCED SEARCH</a></li>
		</ul>
		<c:if test="${param.home}">
		<form class="toolbar-search">
			<input class="toolbar-searchtext" type="text"/>
			<input class="toolbar-searchsubmit" type="submit" value="SEARCH"/>
		</form>
		</c:if>
		<ul id="righttabs">
			<c:choose>
				<c:when test="${sessionScope.connected}">
					<div class="tab-hello">Hello</div>
					<div class="tab-username"><c:out value="${sessionScope.user.username}"/></div>
					<li class="righttab"><a href="logout">Log out</a></li>
					<c:if test="${sessionScope.isadmin}">
					  <li class="righttab admintab"><a href="admin">Admin zone</a></li>
					</c:if>
				</c:when>
				<c:otherwise>
					<li class="righttab"><a href="login">Log in</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div id="notif" class="notification"><div id="notifmessage" class="notif-message">This doesn't exist</div></div>
	<script type="text/javascript" src="js/jquery.js"></script>
  	<script type="text/javascript" src="js/script.js"></script>
  	<c:if test="${not empty requestScope.notif}">
  		<script>notif("${requestScope.notif}");</script>
  	</c:if>
	<c:if test="${not empty requestScope.error}">
		<div class="log-error">${requestScope.error}</div>
	 	<br />
	</c:if>		
		
</section>
