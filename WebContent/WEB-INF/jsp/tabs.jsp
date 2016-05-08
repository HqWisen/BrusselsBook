<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="toolbar-section">
	<div id="toolbar">
		<ul id="lefttabs">
			<li class="tab"><a
				class="<c:if test="${param.home}">active</c:if>" href="home">HOME</a></li>
			<li class="tab"><a
				class="<c:if test="${param.search}">active</c:if>" href="search">SEARCH</a></li>
		</ul>
		<ul id="righttabs">
			<c:choose>
				<c:when test="${sessionScope.connected}">
					<li class="tab"><a href="logout">Log Out</a></li>
				</c:when>
				<c:otherwise>
					<li class="tab"><a href="login">Log In</a></li>
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
