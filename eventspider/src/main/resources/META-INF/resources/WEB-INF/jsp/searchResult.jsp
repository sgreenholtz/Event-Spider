<%--
  Search Results List

  User: Kolya
  Date: 2016-06-06
  Time: 7:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% session.setAttribute("title", "Search Results"); %>
<c:import url="header.jsp"/>
<h3>Found ${fn:length(eventsList)} events matching "${search.keyword}"</h3>
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
            <td><div class="tableOverflow>">${event.title}</div></td>
            <td><div class="tableOverflow">${event.startTime}</div></td>
            <td>${event.venueAddress}, ${event.city} ${event.state}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="footer.jsp"/>