<%--
  Edit Profile

  User: Kolya
  Date: 2016-07-24
  Time: 8:25 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Error"); %>
<c:import url="header.jsp"/>

<div class="well">
<form:form class="form-horizontal" action="submit-edit-profile" method="post" modelAttribute="profile">
    <fieldset>
        <legend>Edit Your Profile</legend>
        <div class="form-group">
            <form:label path="firstName" for="firstName" class="col-lg-2 control-label">First Name</form:label>
            <div class="col-lg-10">
                <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="lastName" for="lastName" class="col-lg-2 control-label">Last Name</form:label>
            <div class="col-lg-10">
                <form:input path="lastName" type="text" class="form-control" id="lastName"
                            placeholder="Last Name" name="lastName"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="image" for="image" class="col-lg-2 control-label">Path to Profile Image</form:label>
            <div class="col-lg-10">
                <form:input path="image" type="text" class="form-control" id="image"
                            placeholder="Path to Image " name="image"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="reset" class="btn btn-default">Cancel</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form:form>

    <form:form class="form-horizontal" action="submit-edit-user" method="post" modelAttribute="user">
        <fieldset>
            <legend>Edit Login Info</legend>
            <div class="form-group">
                <form:label path="email" for="email" class="col-lg-2 control-label">Email</form:label>
                <div class="col-lg-10">
                    <form:input path="email" type="text" class="form-control" id="email" placeholder="Email" name="email"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="password" for="password" class="col-lg-2 control-label">Password</form:label>
                <div class="col-lg-10">
                    <form:input path="password" type="password" class="form-control" id="password"
                                placeholder="Password" name="password" value=""/>
                    <button type="button" class="btn btn-default"
                            data-toggle="tooltip"
                            data-placement="bottom"
                            data-original-title="Toggle password visibility">
                        <span id="showPassword" class="glyphicon glyphicon-eye-close"></span></button>
                    <span class="text-info">Click to toggle password visibility</span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form:form>

</div>

<c:import url="footer.jsp"/>