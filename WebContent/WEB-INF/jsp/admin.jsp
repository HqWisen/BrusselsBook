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
	
	<%@ include file="adminAccess.jsp"  %>
	
	<c:import url="tabs.jsp">
		<c:param name="admin" value="true"/>
	</c:import>
	<div id="container">	
		<div class="admin-zone">
			<span>Establishment zone</span>
			<a href="escreate?t=restaurant">Create a restaurant</a>
			<a href="escreate?t=cafe">Create a café</a>
			<a href="escreate?t=hotel">Create an hotel</a>
			<a href="search?amode=true">Edition mode</a>
		</div>
		<div class="admin-zone">
			<span>User zone</span>
			<a href="">Edition mode</a>
			<a href="">Comment moderation</a>
		</div>
		<div class="admin-zone">
			<span>Easter egg</span>
			<a href="egg?r=1">R1</a>
			<a href="egg?r=2">R2</a>
			<a href="egg?r=3">R3</a>
			<a href="egg?r=4">R4</a>
			<a href="egg?r=5">R5</a>
			<a href="egg?r=6">R6</a>
		</div>
	</div>
</body>

</html>