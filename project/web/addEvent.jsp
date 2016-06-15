<%--
  Add Event Page

  User: Kolya
  Date: 2016-06-04
  Time: 10:14 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Add Events"); %>
<c:import url="header.jsp"/>

<form class="form-horizontal" action="addEventManual" method="post">
    <fieldset>
        <legend>Add Event</legend>
        <div class="form-group">
            <label for="title" class="col-lg-2 control-label">Title</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="title" placeholder="Title" name="title">
            </div>
        </div>
        <div class="form-group">
            <label for="url" class="col-lg-2 control-label">URL</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="url" placeholder="Link to Event (Optional)" name="url">
            </div>
        </div>
        <div class="form-group">
            <label for="start" class="col-lg-2 control-label">Start Date</label>
            <div class="col-lg-5">
                <input type="date" class="form-control" id="start" name="startDate">
            </div>
            <label for="startTime" class="col-lg-2 control-label">Start Time</label>
            <div class="col-lg-5">
                <input type="time" class="form-control" id="startTime" name="startTime">
            </div>
        </div>
        <div class="form-group">
            <label for="end" class="col-lg-2 control-label">End Date</label>
            <div class="col-lg-5">
                <input type="date" class="form-control" id="end" name="endDate">
            </div>
            <label for="endTime" class="col-lg-2 control-label">End Time</label>
            <div class="col-lg-5">
                <input type="time" class="form-control" id="endtime" name="endTime">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="reset" class="btn btn-default">Cancel</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>

<c:import url="footer.jsp"/>