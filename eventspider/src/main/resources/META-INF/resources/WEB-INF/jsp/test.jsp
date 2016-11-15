<%--
  Test page for any purpose
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Test"); %>
<c:import url="header.jsp"/>

<h3>Welcome ${user.firstName}!</h3>

<c:import url="footer.jsp" />