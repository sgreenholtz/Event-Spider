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
<h3>Found ${fn:length(eventsList)} events matching "${searchTerm}"</h3>
<table class="table table-striped table-hover ">
    <thead>
        <tr>
            <td></td>
            <td>Title</td>
            <td>URL</td>
        </tr>
    </thead>
<c:forEach var="event" items="${eventsList}">
        <tr>
            <td><a href="event-details?id=${event.eventId}" class="btn btn-default">View</a></td>
            <td>${event.title}</td>
            <td>${event.url}</td>
        </tr>
</c:forEach>
</table>
<c:import url="footer.jsp"/>