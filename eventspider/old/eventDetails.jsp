<%--
  Event Details View

  User: Kolya
  Date: 2016-06-13
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Event Details"); %>
<c:import url="../src/main/resources/WEB-INF/jsp/header.jsp"/>
<h3 class="text-danger">${addedMessage}</h3>
<h1>${event.title}</h1>
<c:choose>
    <c:when test="${empty event.description}">
        <p>No description available.</p>
    </c:when>
    <c:otherwise>
        <p>${event.description}</p>
    </c:otherwise>
</c:choose>
<h4>When</h4>
<c:choose>
    <c:when test="${empty event.stopTime}">
        <p>${event.startTime}</p>
    </c:when>
    <c:otherwise>
        <p>${event.startTime} to ${event.stopTime}</p>
    </c:otherwise>
</c:choose>
<h4>Where</h4>
<p>${event.venueAddress}, ${event.state} ${event.city} ${event.postalCode}</p>
<p><a href="${event.url}" target="_blank" class="btn btn-warning">Learn More</a></p>
<c:choose>
    <c:when test="${empty userID}">
        <c:set var="returnPageEvent" value="/eventDetails?id=${event.eventId}" scope="session" />
        <a href="login" class="btn btn-danger btn-lg">Log In to Add</a>
    </c:when>
    <c:when test="${returnPage == 'my-events'}">
        <a href="#" class="btn btn-danger btn-lg">Delete from My Events</a>
    </c:when>
    <c:otherwise>
        <a href="addEventToUser?id=${event.eventId}" class="btn btn-danger btn-lg">Add to My Events</a>
    </c:otherwise>
</c:choose>
<a href="${returnPage}" class="btn btn-success btn-lg">Return to Previous Page</a>
<c:remove var="addedMessage" />
<c:import url="../src/main/resources/WEB-INF/jsp/footer.jsp"/>