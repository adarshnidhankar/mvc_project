<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Data</title>
</head>
<body>
<% String user = request.getParameter("username"); %>
<h3><%=user %> Employee is Registered Successfully</h3>
</body>
</html>