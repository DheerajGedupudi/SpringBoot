<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title>Dheeraj Home Page</title>
</head>

<body>
	<h2>Dheeraj Home Page</h2>
	<hr>
	
	Welcome to the Dheeraj's company home page!

	<!-- Adding logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="Logout" />
	</form:form>

</body>

</html>