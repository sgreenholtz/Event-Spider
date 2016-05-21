<%--
  Log In

  User: Kolya
  Date: 2016-05-20
  Time: 7:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("title", "Log In"); %>
<c:import url="header.jsp"/>

<form class="form-horizontal" action="logInServlet">
    <fieldset>
        <legend>Log In</legend>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="email" placeholder="Email" name="email">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-lg-2 control-label">Password</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>

<c:import url="footer.jsp" />