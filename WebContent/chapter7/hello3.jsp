<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<p>Hello!</p>
<p>こんにちは！</p>

<p>
<%
out.println(new java.util.Date());
%>
</p>

<%@include file="../footer.html"%>