<%--
  My Events

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "My Events"); %>
<c:set var="returnPage" value="my-events" scope="session" />
<c:import url="../src/main/resources/WEB-INF/jsp/header.jsp"/>
<c:if test="${empty userID}">
    <c:redirect url="/login" />
</c:if>
<h1>My Events</h1>
<%--<table class="show" id="table">--%>
<table class="table table-hover ">
    <thead>
    <tr>
        <th></th>
        <th>Title</th>
        <th>Date</th>
        <th>Location</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event" items="${userEventsMap}">
        <tr>
            <td><a href="eventDetails?id=${event.key}" class="btn btn-success btn-xs">View</a></td>
            <td><div class="tableOverflow">${event.value.title}</div></td>
            <td>${event.value.startTime}</td>
            <td>${event.value.venueAddress}, ${event.value.city} ${event.value.state}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:import url="../src/main/resources/WEB-INF/jsp/footer.jsp" />