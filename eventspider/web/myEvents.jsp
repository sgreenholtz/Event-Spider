<%--
  My Events

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "My Events"); %>
<c:import url="header.jsp"/>
<c:if test="${empty userID}">
    <c:redirect url="/login" />
</c:if>
<h1>My Events</h1>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <td></td>
        <td>Title</td>
        <td>Date</td>
        <td>Location</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event" items="${userEventsMap}">
        <tr>
            <td><a href="eventDetails?id=${event.key}" class="btn btn-success btn-sm">View</a></td>
            <td><div class="tableOverflow">${event.value.title}</div></td>
            <td>${event.value.startTime}</td>
            <td>${event.value.venueAddress}, ${event.value.city} ${event.value.state}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:import url="footer.jsp" />