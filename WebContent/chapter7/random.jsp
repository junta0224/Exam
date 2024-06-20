<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html"%>


<%
	int x=(int) (Math.random() *5);
	String[] unsei={"大吉","小吉","吉","凶","大凶"};
%>

<h1><%=unsei[x] %></h1>

<%@include file="../footer.html"%>