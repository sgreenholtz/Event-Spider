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
<h3>Found ${fn:length(eventsMap)} events matching "${searchTerm}"</h3>
<table class="table table-striped table-hover ">
    <thead>
        <tr>
            <td></td>
            <td>Title</td>
            <td>Date</td>
            <td>Location</td>
        </tr>
    </thead>
<c:forEach var="event" items="${eventsMap}">
        <tr>
            <td><a href="eventDetails?id=${event.key}" class="btn btn-success btn-sm">View</a></td>
            <td><div class="tableOverflow">${event.value.title}</div></td>
            <td>${event.value.startTime}</td>
            <td>${event.value.venueAddress}, ${event.value.city} ${event.value.state}</td>
        </tr>
</c:forEach>
</table>
<c:import url="footer.jsp"/>