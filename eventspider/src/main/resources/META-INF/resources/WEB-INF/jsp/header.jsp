<%--
  Header

  User: Kolya
  Date: 2016-05-07
  Time: 3:06 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="staticDir" value="../../static" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Search for events in your city from any source">
    <meta name="author" content="Sebastian Greenholtz">

<!-- Icon Stuff -->
<link rel="shortcut icon" src="${staticDir}/icons/calendar.ico">
<link rel="icon" sizes="16x16" src="${staticDir}/icons/calendar.ico">
<link rel="icon" sizes="16x16" src="${staticDir}/icons/calendar.png">

    <title>${title}</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="${staticDir}/bootstrap-custom.css" />
    <link rel="stylesheet" href="${staticDir}/custom.css" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
    <link rel="stylesheet" href="${staticDir}/jquery.timepicker.css" />
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif|Ubuntu" rel="stylesheet">

    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" asynch></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" asynch></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js" asynch></script>
    <script src="${staticDir}/jquery.timepicker.min.js" asynch></script>
    <script src="${staticDir}/eventspider.js" type="text/javascript" asynch></script>
    ${scripts}

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1 id="title">Event Spider</h1>
        </div>
        <div id="spiderDiv" class="col-lg-2 offset-lg-6">
            <img id="spiderImg" src="${staticDir}/images/spider150px.png"/>
        </div>
    </div>

    <div class="row">
    <nav class="navbar navbar-inverse" id="navbar">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse">
                <div id="navbarTextLarge">
                <ul class="nav navbar-nav">
                    <li><a href="/"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li><a href="search-form"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                    <c:choose>
                        <c:when test="${activeUser.userId eq null}">
                            <li class=""><a href="login"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
                            <li class=""><a href="register"><span class="glyphicon glyphicon-edit"></span> Register</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="dropdown" id="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                    <span class="glyphicon glyphicon-user">
                                    </span> ${activeUser.firstName}<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="profile">My Profile</a></li>
                                    <li><a href="add-event-form">Add Custom Event</a></li>
                                    <li><a href="edit-profile">Settings</a></li>
                                    <spring:eval expression="activeUser.role == T(eventspider.beans.Roles).ADMINISTRATOR" var="isAdmin" />
                                    <c:if test="${isAdmin}">
                                        <li><a href="admin-page">Admin Page</a></li>
                                    </c:if>
                                    <li class="divider"></li>
                                    <li><a href="logout">Log Out</a></li>
                                </ul>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                </div>
            </div>
        </div>


    </nav>
    </div>