<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/script.js"></script>


<section id="toolbar-section">
	<div id="toolbar">
		<ul id="lefttabs">
			<li class="tab"><a
				class="<c:if test="${param.home}">active</c:if>" href="home">HOME</a></li>
			<%--li class="tab"><a
				class="<c:if test="${param.search}">active</c:if>" href="search">ADVANCED SEARCH</a></li--%>
		</ul>
		<c:if test="${empty param.q}">
		  <c:set var="searchtext" value="${sessionScope.SEARCHTITLE}" scope="page"/>
		</c:if>
		<c:if test="${not empty param.q}">
		  <c:set var="searchtext" value="${param.q}" scope="page"/>
		</c:if>
		<form class="toolbar-search" action="search">
			<input class="toolbar-searchtext" id="searchinput" name="q" value="<c:out value="${pageScope.searchtext}"/>" type="text"/>
			<input class="toolbar-searchsubmit" type="submit" value="SEARCH" onclick ="return checkform();"/>
		</form>
		 <script>
			buildInputDefault("#searchinput", "<c:out value="${sessionScope.SEARCHTITLE}"/>");
			function checkform(){
				if($("#searchinput").val() == "<c:out value="${sessionScope.SEARCHTITLE}"/>"){
		    		$("#searchinput").val("");
		    	}				
			}
	    	
		</script>	
		<ul id="righttabs">
			<c:choose>
				<c:when test="${sessionScope.connected}">
					<div class="tab-hello">Hello,</div>
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
   	<c:if test="${not empty requestScope.notif}">
  		<script>notif("${requestScope.notif}");</script>
  	</c:if>
	<c:if test="${not empty requestScope.error}">
		<div class="log-error">${requestScope.error}</div>
	 	<br />
	</c:if>		
	<c:if test="${not empty requestScope.warning}">
		<div class="log-warning">${requestScope.warning}</div>
	 	<br />
	</c:if>		
	<c:if test="${not empty requestScope.message}">
		<div class="log-message">${requestScope.message}</div>
	 	<br />
	</c:if>		
		
</section>
