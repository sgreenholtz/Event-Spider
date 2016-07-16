<%--
  Search JSP

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Search Event"); %>
<c:if test="${empty userID}">
    <c:redirect url="/login" />
</c:if>
<c:import url="header.jsp"/>

<form class="form-horizontal" action="searchServlet" method="get">
    <fieldset>
        <legend>Search</legend>
        <%--<div class="form-group">--%>
            <%--<label for="location" class="col-lg-2 control-label">Location:</label>--%>
            <%--<div class="col-lg-10">--%>
                <%--<input type="text" class="form-control" id="location" placeholder="City, State, or Zip" name="location">--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label for="keyword" class="col-lg-2 control-label">Find:</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="keyword" placeholder="Title or keywords" name="keyword">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>

<c:import url="eventfulSearch.jsp" />

<c:import url="footer.jsp" />