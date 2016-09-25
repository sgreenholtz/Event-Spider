<%--
  Log In

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Log In"); %>
<c:import url="header.jsp"/>

<c:if test="${notLoggedIn}">
    <div class="alert alert-danger" role="alert">Oops we didn't find you did you mean to <a href="register">register</a>?</div>
</c:if>
<form:form id="loginForm" class="form-horizontal" action="verify" method="post" modelAttribute="user">
    <fieldset>
        <legend>Log In</legend>
        <div class="form-group">
            <form:label path="email"  for="email" class="col-lg-2 control-label">Email</form:label>
            <div class="col-lg-10">
                <form:input path="email" type="text" class="form-control" id="email" placeholder="Email" name="email"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password" for="password" class="col-lg-2 control-label">Password</form:label>
            <div class="col-lg-10">
                <form:password path="password" class="form-control" id="password" placeholder="Password" name="password"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <input type="submit" class="btn btn-primary" value="Submit" />
            </div>
        </div>
    </fieldset>
</form:form>


<c:import url="footer.jsp" />