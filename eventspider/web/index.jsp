<%--
  User: Kolya
  Date: 2016-05-07
  Time: 3:17 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Home"); %>
<c:import url="header.jsp" />
<h1>${title}</h1>
<h4>Eventful Search</h4>

<c:import url="footer.jsp" />