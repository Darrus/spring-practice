<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Student Registration Form</title>
</head>

<body>
	<form:form action="processForm" modelAttribute="student">
		First name: <form:input path="firstName"/>
		<br><br>
		Last name: <form:input path="lastName"/>
		<br><br>
		<form:select path="country">
			<form:options items="${student.countryOptions}"/>
		</form:select>
		<br><br>
		<input type="submit" value="Submit"/>
		<br><br>
		Favorite Language:
		Java <form:radiobutton path="favoriteLanguage" value="java" />
		C++ <form:radiobutton path="favoriteLanguage" value="c++" />
		C# <form:radiobutton path="favoriteLanguage" value="c#" />
		<br><br>
		Operation Systems:
		Linux <form:checkbox path="operatingSystems" value="Linux" />
		Windows <form:checkbox path="operatingSystems" value="Windows" />
		Mac <form:checkbox path="operatingSystems" value="Mac" />
	</form:form>
</body>

</html>