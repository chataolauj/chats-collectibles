<%-- 
    Document   : header
    Created on : Sep 25, 2017, 9:05:59 PM
    Author     : Toubee Lo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat's Collectibles</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link rel="stylesheet" href="My%20Shop.css">
    </head>
    <body>
        <header>
            <h1>Chat's Collectibles</h1>
            <c:if test="${empty currentUser}">
                <p>Not logged in</p>
            </c:if>
        </header>
