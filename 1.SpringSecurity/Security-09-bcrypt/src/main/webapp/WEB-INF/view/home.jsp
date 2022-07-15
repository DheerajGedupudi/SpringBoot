<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Dheeraj Home Page</title>
</head>

<body>
	<h2>Dheeraj Home Page</h2>
	<hr>
	
	Welcome to the Dheeraj's company home page!

	<hr>

	<!-- Displaying user names and roles -->

	<p>
	    User : <security:authentication property="principal.username" />
	    <br><br>
	    Roles : <security:authentication property="principal.authorities" />
	</p>

	<security:authorize access="hasRole('MANAGER')">

	<hr>

        <!-- Adding link to point to /leaders -->
        <p>
            <a href="${pageContext.request.contextPath}/leaders">Leader Meetings</a>
            (Only for Managers)
        </p>

    </security:authorize>


	<security:authorize access="hasRole('ADMIN')">

	<hr>

        <!-- Adding link to point to /systems -->
        <p>
            <a href="${pageContext.request.contextPath}/systems">Admin Meetings</a>
            (Only for Administrator)
        </p>

    </security:authorize>

	<hr>

	<!-- Adding logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="Logout" />
	</form:form>

</body>

</html>