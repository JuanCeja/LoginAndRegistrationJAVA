<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Book Club</h1>
	<h4>A place for friends to share thoughts on books.</h4>
	<fieldset>
		<legend>
			<h3>Register</h3>
		</legend>
		<form:form action="/register" method="post" modelAttribute="newUser">
			<p>
				<form:label path="userName">Username:</form:label>
				<form:errors path="userName" />
				<form:input path="userName" />
			</p>
			<p>
				<form:label path="email">Email:</form:label>
				<form:errors path="email" />
				<form:input path="email" />
			</p>
			<p>
				<form:label path="password">Password:</form:label>
				<form:errors path="password" />
				<form:input path="password" />
			</p>
			<p>
				<form:label path="confirm">Confirm Password:</form:label>
				<form:errors path="confirm" />
				<form:input path="confirm" />
			</p>
			<input type="submit" value="submit" />
		</form:form>
	</fieldset>
		<fieldset>
		<legend><h3>Login</h3></legend>
		<form:form action="/login" method="post"
			modelAttribute="newLogin">
			<p>
				<form:label path="email">Email</form:label>
				<form:errors path="email" />
				<form:input path="email" />
			</p>
			<p>
				<form:label path="password">Password</form:label>
				<form:errors path="password" />
				<form:input path="password" />
			</p>
			<input type="submit" value="submit" />
		</form:form>
	</fieldset>
</body>
</html>