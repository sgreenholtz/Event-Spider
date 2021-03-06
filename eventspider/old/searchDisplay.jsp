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
<c:import url="../src/main/resources/WEB-INF/jsp/header.jsp"/>
<c:set var="returnPage" value="search-result-list" scope="session" />
<h3>Found ${fn:length(eventsMap)} events matching "${searchTerm}"</h3>
<%--<table class="display" id="table">--%>
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
    <c:forEach var="event" items="${eventsMap}">
        <tr>
            <td><a href="eventDetails?id=${event.key}" class="btn btn-success btn-xs">View</a></td>
            <td><div class="tableOverflow>">${event.value.title}</div></td>
            <td><div class="tableOverflow">${event.value.startTime}</div></td>
            <td>${event.value.venueAddress}, ${event.value.city} ${event.value.state}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="../src/main/resources/WEB-INF/jsp/footer.jsp"/>