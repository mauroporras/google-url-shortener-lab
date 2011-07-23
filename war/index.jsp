<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gn.lab.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String url = request.getParameter("url");

		url = url == null ? "http://www.glassnova.com" : url;

		Google google = new Google("your_api_key");

		out.print(google.shorten(url));
	%>
	<form action="index.jsp">
		<input type="text" name="url" /> <input type="submit" />
	</form>
</body>
</html>