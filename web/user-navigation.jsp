<%-- 
    Document   : user-navigation
    Created on : Sep 25, 2017, 9:20:41 PM
    Author     : Toubee Lo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="header-nav nav justify-content-center">
    <c:if test="${currentUser.rolename != 'administrator'}">
        <c:choose>
            <c:when test="${currentUser == null}">
                <li><a class="nav-link active" href="login">Login</a></li>
            </c:when>
            <c:otherwise>
                <li><a class="nav-link active" href="user">${currentUser.username}</a></li>
            </c:otherwise>
        </c:choose>
        <li><a class="nav-link active" href="cart">Cart</a></li>
        <li><a class="nav-link active" href="user?action=viewOrders">My Orders</a></li>
    </c:if>
    <c:if test="${currentUser.rolename == 'administrator'}">
        <li><a class="nav-link active" href="admin">Admin</a></li>
    </c:if>
    <c:if test="${!empty currentUser}">
        <li><a class="nav-link active" href="login?action=logout">Logout</a></li>
    </c:if>
</ul>


