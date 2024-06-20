<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>
intx=10;
<%
String[];
%>
<p>Hello!</p>
<p>こんにちは！</p>


<select>
<% for(int i=0; i<values.length; i++) { %>
<option value="<%= values[i] %>"><%=strs[i] %></option>
<% } %>
</select>

<%@include file="../footer.html"%>