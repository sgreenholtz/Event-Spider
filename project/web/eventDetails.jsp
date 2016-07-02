<%--
  Event Details View

  User: Kolya
  Date: 2016-06-13
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Event Details"); %>
<c:import url="header.jsp"/>
<h1>${event.title}</h1>
<p>${event.description}</p>
<h4>When</h4>
<p>${event.startTime} to ${event.stopTime}</p>
<h4>Where</h4>
<p>${event.venueAddress}, ${event.state} ${event.city} ${event.postalCode}</p>
<c:import url="footer.jsp"/>