<%--
  Admin Page

  User: Kolya
  Date: 2016-06-06
  Time: 7:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Admin"); %>
<c:import url="header.jsp"/>
<h2>Options: </h2>
<div class="list-group">
    <a href="clearDatabase" class="list-group-item">Clear Old Database Items</a>
    <a href="#" class="list-group-item">Modify User Permissions</a>
    <a href="#" class="list-group-item">Delete Database Items</a>
    <a href="#" class="list-group-item list-group-item-dange">Clear Entire Database</a>
</div>
<c:import url="footer.jsp"/>