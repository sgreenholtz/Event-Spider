<%--
  Add Event Page

  User: Kolya
  Date: 2016-06-04
  Time: 10:14 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Add Events"); %>
<c:import url="header.jsp"/>

<form:form class="form-horizontal" action="addEventManual" method="post">
    <fieldset>
        <legend>Add Event</legend>
        <div class="form-group">
            <form:label path="title" for="title" class="col-lg-2 control-label">Title</form:label>
            <div class="col-lg-10">
                <form:input path="title" type="text" class="form-control" id="title" placeholder="Title" name="title"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="url" for="url" class="col-lg-2 control-label">URL</form:label>
            <div class="col-lg-10">
                <form:input path="url" type="text" class="form-control" id="url"
                            placeholder="Link to Event (Optional)" name="url"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="description" for="description" class="col-lg-2 control-label">Description</form:label>
            <div class="col-lg-10">
                <form:textarea path="description" class="form-control"
                               id="description" name="description"></form:textarea>
            </div>
        </div>
        <div class="form-group">
            <form:label path="startDate" for="startDate" class="col-lg-2 control-label">Start Date</form:label>
            <div class="col-lg-5">
                <form:input path="startDate" type="date" class="form-control" id="startDate" name="startDate"/>
            </div>
            <form:label path="startTime" for="startTime" class="col-lg-2 control-label">Start Time</form:label>
            <div class="col-lg-5">
                <form:input path="startTime" type="time" class="form-control" id="startTime" name="startTime"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="endDate" for="endDate" class="col-lg-2 control-label">End Date</form:label>
            <div class="col-lg-5">
                <form:input path="endDate" type="date" class="form-control" id="endDate" name="endDate"/>
            </div>
            <form:label path="endTime" for="endTime" class="col-lg-2 control-label">End Time</form:label>
            <div class="col-lg-5">
                <form:input path="endTime" type="time" class="form-control" id="endtime" name="endTime"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="venueAddress" for="address" class="col-lg-2 control-label">Venue Address</form:label>
            <div class="col-lg-10">
                <form:input path="venueAddress" type="text" class="form-control" id="address"
                            placeholder="Address" name="address"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="city" for="city" class="col-lg-2 control-label">City</form:label>
            <div class="col-lg-10">
                <form:input  path="city" type="text" class="form-control" id="city" placeholder="City" name="city"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="state" for="state" class="col-lg-2 control-label">State or Province</form:label>
            <div class="col-lg-10">
                <form:input path="state" type="text" class="form-control" id="state"
                            placeholder="Abbreviation works fine" name="state"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="postalCode" for="zipcode" class="col-lg-2 control-label">Zip or Postal Code</form:label>
            <div class="col-lg-10">
                <form:input path="postalCode" type="text" class="form-control" id="zipcode"
                            placeholder="Zip or Postal Code" name="zipcode"/>
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

<c:import url="footer.jsp"/>