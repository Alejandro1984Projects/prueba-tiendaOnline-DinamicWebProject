<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<%
ArrayList<String> lista=(ArrayList<String>)request.getAttribute("respuesta");
for(String linea:lista){
%>
<tr>
<td><%=linea%></td>
</tr>
<%
}
%>
</table>
</body>
</html>