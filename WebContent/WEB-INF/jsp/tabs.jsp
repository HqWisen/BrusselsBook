<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="toolbar-section">
	<div id="toolbar">
		<ul id="lefttabs">
			<li class="tab" id="rtab0"><a id="rtab0link"
				class="<c:if test="${param.home}">active</c:if>" href="home">HOME</a></li>
			<li class="tab" id="rtab1"><a id="rtab1link"
				class="<c:if test="${param.search}">active</c:if>" href="search">SEARCH</a></li>
		</ul>
		<ul id="righttabs">
			<c:choose>
				<c:when test="${sessionScope.connected}">
					<li class="tab" id="ltab0"><a id="ltab0link" href="#">Disconnect</a></li>
				</c:when>
				<c:otherwise>
					<li class="tab" id="ltab0"><a id="ltab0link" href="login">Login</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</section>
