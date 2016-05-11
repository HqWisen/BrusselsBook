	<c:if test="${not sessionScope.isadmin}">
		<c:redirect url="home">
			<c:set var="error" value="Access denied." scope="session"/>
		</c:redirect>
	</c:if>
