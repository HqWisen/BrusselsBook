<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  	<form method="post" action="login">
    	<fieldset>
    		<legend>Login</legend>
    		<label>Identifier</label>
    		<input type="text" name="identifier" value="<c:out value="${param.identifier}"/>"/>
			<br/>
    		<label>Password</label>
			<input type="password" name="password" maxlength="16"/>
    		<br/>
    		<c:if test="${not empty requestScope.error}">
    		<span class="error">${requestScope.error}</span><br/>
    		</c:if>
    		<input type="submit" value="Log in"/> <br/>
    	</fieldset>
    </form>
  	<c:out value="${sessionScope.user}"/>
  	<c:out value="${sessionScope.isadmin}"/>
  </body>

  </html>