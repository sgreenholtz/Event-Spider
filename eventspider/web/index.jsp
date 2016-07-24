<%--
  User: Kolya
  Date: 2016-05-07
  Time: 3:17 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Home"); %>
<c:import url="header.jsp" />
<h1>Let's go out tonight!</h1>
<div class="jumbotron">
    <h1>Search for Events in Your City</h1>
    <p>Find out what's going on today in your city. Just hop over to the Search page and type in your
    location and keywords - no need to register!</p>
    <p><a href="search" class="btn btn-primary btn-lg">Learn more</a></p>
</div>

<c:import url="footer.jsp" />