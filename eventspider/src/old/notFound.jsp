<%--

  Not Found (404)

  User: Kolya
  Date: 2016-07-24
  Time: 8:35 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Error"); %>
<c:import url="../main/webapp/WEB-INF/header.jsp"/>

<div class="jumbotron">
    <h1>That page doesn't exist.</h1>
    <p>Whatever you were looking for, it's not there. How about a trip back to the home page?</p>
    <p><a href="/" class="btn btn-primary btn-lg">Take me back!</a></p>
</div>

<c:import url="../main/webapp/WEB-INF/footer.jsp"/>