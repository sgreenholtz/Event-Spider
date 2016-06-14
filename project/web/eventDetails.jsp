<%--
  User: Kolya
  Date: 2016-06-13
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Event Details"); %>
<c:import url="header.jsp"/>
<h1>${eventsMap[<%= request.getParameter("id")%>].title}</h1>
<h1>${param.id}</h1>
<c:import url="footer.jsp"/>