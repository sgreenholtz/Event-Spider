<%--
  User: Kolya
  Date: 2016-08-05
  Time: 6:00 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />
    <h1>Enter a URL</h1>
    <form class="form-horizontal" action="updateSeedList" method="get">
        <fieldset>
            <div class="form-group">
                <label for="url" class="col-lg-2 control-label">URL</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="url" placeholder="http://www.example.com" name="url">
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