<%--
  Search JSP

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% session.setAttribute("title", "Search Event"); %>
<c:import url="header.jsp"/>

<form:form class="form-horizontal" action="search" method="post" modelAttribute="search">
    <fieldset>
        <legend>Search</legend>
        <div class="form-group">
            <form:label path="location" for="location" class="col-lg-2 control-label">Location:</form:label>
            <div class="col-lg-10">
                <form:input path="location" type="text" class="form-control" id="location"
                            placeholder="City, State, or Zip" name="location"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="keyword" for="keyword" class="col-lg-2 control-label">Find:</form:label>
            <div class="col-lg-10">
                <form:input path="keyword" type="text" class="form-control" id="keyword" placeholder="Title or keywords"
                            name="keyword"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="dateStart" for="dateStart" class="col-lg-2 control-label">Start Date:</form:label>
            <div class="col-lg-10">
                <form:input path="dateStart" type="date" class="date form-control" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="dateEnd" for="dateEnd" class="col-lg-2 control-label">End Date:</form:label>
            <div class="col-lg-10">
                <form:input path="dateEnd" type="date" class="date form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="dateEnd" class="col-lg-2 control-label">Sources:</label>
            <div class="col-lg-10">
                <form:checkbox path="databaseSearch" type="checkbox" class="form-control" /> Event Spider Database
                <form:checkbox path="eventfulSearch" type="checkbox" class="form-control" /> Eventful
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form:form>

<c:import url="footer.jsp" />