<%--
  Search Results List

  User: Kolya
  Date: 2016-06-06
  Time: 7:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<% session.setAttribute("title", "Search Results"); %>
<c:import url="header.jsp"/>
<c:choose>
    <c:when test="${search.keyword eq ''}">
        <h3>Found ${fn:length(eventsList)} events in ${search.location}</h3>
    </c:when>
    <c:otherwise>
        <h3>Found ${fn:length(eventsList)} events matching "${search.keyword}"</h3>
    </c:otherwise>
</c:choose>

<table class="table table-striped table-hover ">
    <thead>
    <tr>
        <th></th>
        <th>Title</th>
        <th>Date</th>
        <th>Location</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event" items="${eventsList}">
        <tr>
            <td><a href="eventDetails?id=${event.eventId}" class="btn btn-success btn-xs">View</a></td>
            <td><div class="tableOverflow>">${fn:substring(event.title, 0, 50)}</div></td>
            <td><div class="tableOverflow"><joda:format value="${event.startDate}" style="M-"/></div></td>
            <td>${fn:substring(event.venueAddress, 0, 30)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="footer.jsp"/>