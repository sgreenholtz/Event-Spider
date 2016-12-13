<%--
  User Profile

  User: Kolya
  Date: 2016-11-15
  Time: 9:12 PM
  todo: finish this view
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<% session.setAttribute("title", "Profile"); %>
<c:import url="header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="text-left text-muted">
                        Your Profile
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img alt="Bootstrap Image Preview" src="http://lorempixel.com/140/140/" />
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        ${profile.firstName} ${profile.lastName}
                    </h3>
                </div>
                <div class="panel-body">
                    Tags will go here
                </div>
                <div class="panel-footer">
                    Other info here
                </div>
            </div>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-6">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        View
                    </th>
                    <th>
                        Title
                    </th>
                    <th>
                        Date
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${profile.events}" var="event">
                <tr>
                    <td>
                        <a href="eventDetails?id=${event.eventId}" target="_blank" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-new-window"></span></a>
                    </td>
                    <td>
                        ${event.title}
                    </td>
                    <td>
                        <p><joda:format value="${event.startDate}" style="M-"/> at ${event.startTime}</p>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="edit-profile"><span class="glyphicon glyphicon-pencil"></span> Edit Profile</a>
                </li>
            </ul>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</div>

<c:import url="footer.jsp" />