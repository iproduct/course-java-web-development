<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="elearning.servlet.*,coreservlets.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/main.css" />
<title>User Registration</title>
</head>
<body>
	<h1>User Registration</h1>
	<%--<%String courseSelectUrl = 
		response.encodeURL(request.getContextPath()+"?action=" + PortalServlet.COURSES_SELECT);
	--%>
	<form method="post">
	<table>
		<tr>
			<td>First name*:</td>
			<td><input type="text" id="fname" name="fname" 
			value="<%= (null != request.getParameter("fname"))? request.getParameter("fname"): "" %>"/> </td>
		</tr>
		<tr>
			<td>Last name*:</td>
			<td><input type="text" id="lname" name="lname"  
			value="<%= (null != request.getParameter("lname"))? request.getParameter("lname"): "" %>"/> </td>
		</tr>
		<tr>
			<td>Nickname*:</td>
			<td><input type="text" id="nickname" name="nickname" 
			value="<%= (null != request.getParameter("nickname"))? request.getParameter("nickname"): "" %>" /> </td>
		</tr>
		<tr>
			<td>E-mail*:</td>
			<td><input type="text" id="email" name="email"  
			value="<%= (null != request.getParameter("email"))? request.getParameter("email"): "" %>"
				placeholder="youremail@somedomain.com" onblur="validateEmail();"/> </td>
			<td><div id="emailErrors" class="error"></div></td>
		</tr>
		<tr>
			<td>Password*:</td>
			<td><input type="password" id="password" name="password" /> </td>
		</tr>
		<tr>
			<td>Password (again)*:</td>
			<td><input type="password" id="password2" name="password2" /> </td>
		</tr>
		<tr>
			<td>Your age:</td>
			<td><input type="number" id="age" name="age"  
			value="<%= (null != request.getParameter("age"))? request.getParameter("age"): "" %>"/> </td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" value="Register" /> </td>
			<td><input type="reset" name="reset" value="Reset Form" /> </td>
		</tr>
	</table>
	<p>* The marked fields are required.</p>
	<%String errors = (String) request.getAttribute("errors");
	if(errors != null && errors.length() > 0) { %>
	
	<div id="errors" class="errors"><%= errors %></div>
	
	<%} %>
	</form>
</body>
</html>