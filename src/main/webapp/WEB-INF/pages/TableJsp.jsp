<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% ArrayList <User> userss = (ArrayList <User>) request.getAttribute(customers); %>
	<table>
		<tr>
			<th>firstName</th>
			<th>lastName</th>
			<th>usertName</th>
			<th>firstName</th>
			<th>firstName</th>
			<th>firstName</th>
			<th>firstName</th>
			<th>firstName</th>
		</tr>
		<tr>
		<%
			for(User user :userss){
		%>
			<tr>
			
			</tr>
			}
		%>
			
		</tr>
			

</body>
</html>