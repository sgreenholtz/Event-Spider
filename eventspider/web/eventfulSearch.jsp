<%--
  User: Kolya
  Date: 2016-07-03
  Time: 10:09 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Eventful Search"); %>
<c:import url="header.jsp"/>

<form class="form-horizontal" action="eventfulSearch" method="get">
    <fieldset>
        <legend>Search for Eventful Events</legend>
        <div class="form-group">
            <label for="location" class="col-lg-2 control-label">Location:</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="location" placeholder="City, State, or Zip" name="location">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>

<c:import url="footer.jsp"/>