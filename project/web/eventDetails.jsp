<%--
  User: Kolya
  Date: 2016-06-13
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.EventBean" %>
<%@ page import="java.util.HashMap" %>
<% session.setAttribute("title", "Event Details"); %>

<c:import url="header.jsp"/>
<h1>${event.title}</h1>

<c:import url="footer.jsp"/>