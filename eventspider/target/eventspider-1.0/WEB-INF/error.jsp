<%--
  Error Page

  User: Kolya
  Date: 2016-07-24
  Time: 8:25 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Error"); %>
<c:import url="header.jsp"/>

<div class="jumbotron">
    <h1>Oops! Something went wrong.</h1>
    <p>We're scratching our heads on this one. Wanna go back to the home page?</p>
    <p><a href="/" class="btn btn-primary btn-lg">Take me back!</a></p>
</div>

<c:import url="footer.jsp"/>