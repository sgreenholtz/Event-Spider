<%--
  Header

  User: Kolya
  Date: 2016-05-07
  Time: 3:06 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Icon Stuff -->
<link rel="shortcut icon" src="${pageContext.request.contextPath}/images/icons/calendar.ico">
<link rel="icon" sizes="16x16" src="${pageContext.request.contextPath}/images/icons/calendar.ico">
<link rel="icon" sizes="16x16" src="${pageContext.request.contextPath}/images/icons/calendar.png">

<title>Event Spider - ${title}</title>

<!-- Bootstrap Core CSS -->
<link href="https://bootswatch.com/sandstone/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" src="${pageContext.request.contextPath}/css/custom.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- JQuery -->
<script src="jquery-1.12.4.min.js"></script>

</head>

<body>

<div class="container">
    <div class="row">
        <h1>Event Spider</h1>
    </div>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <c:if test="${userID==null}">
                        <li><a href="login">Log In</a></li>
                    </c:if>
                    <li><a href="search">Search</a></li>
                    <li><a href="addEvent">Add Event</a></li>
                </ul>
            </div>

        </div>
    </nav>