<%--
  Event Details View

  User: Kolya
  Date: 2016-06-13
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<% session.setAttribute("title", "Event Details"); %>
<c:import url="header.jsp"/>
<c:if test="${success ne null}">
    <c:choose>
        <c:when test="${success}">
            <div class="alert alert-success">
                <strong>Success!</strong> Event saved to My Events.
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger">
                <strong>Error:</strong> Something went wrong while trying to save event.
            </div>
        </c:otherwise>
    </c:choose>
</c:if>
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
    <c:when test="${empty event.endTime}">
        <p><joda:format value="${event.startDate}" style="F-"/> at ${event.startTime}</p>
    </c:when>
    <c:otherwise>
        <p><joda:format value="${event.startDate}" style="F-"/> from ${event.startTime} to ${event.endTime}</p>
    </c:otherwise>
</c:choose>
<h4>Where</h4>
<p>${event.venueAddress}, ${event.state} ${event.city} ${event.postalCode}</p>
<p><a href="${event.url}" target="_blank" class="btn btn-warning">Learn More</a></p>
<c:choose>
    <c:when test="${empty activeuser}">
        <a href="login" class="btn btn-danger btn-lg">Log In to Save</a>
    </c:when>
    <c:otherwise>
        <a href="addEventToUser?id=${event.eventId}" class="btn btn-danger btn-lg">Save to My Events</a>
    </c:otherwise>
</c:choose>
<a href="searchResult" class="btn btn-success btn-lg">Return to Results</a>
<c:import url="footer.jsp"/>