<%--
  My Events

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "My Events"); %>
<c:if test="${empty userID}">
    <c:redirect url="/login" />
</c:if>
<c:import url="header.jsp"/>



<c:import url="footer.jsp" />