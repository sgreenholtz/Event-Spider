<%--
  Register new user

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Register"); %>
<c:import url="header.jsp"/>

<form:form id="register" class="form-horizontal" action="register" method="post" modelAttribute="user">
    <fieldset>
        <legend>Register</legend>
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
            <form:label path="firstName" for="fisrtName" class="col-lg-2 control-label">First Name</form:label>
            <div class="col-lg-10">
                <form:input path="firstName" class="form-control" id="first" placeholder="First Name" name="firstName"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="lastName" for="lastName" class="col-lg-2 control-label">Last Name</form:label>
            <div class="col-lg-10">
                <form:input path="lastName" class="form-control" id="last" placeholder="Last Name" name="lastName"/>
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