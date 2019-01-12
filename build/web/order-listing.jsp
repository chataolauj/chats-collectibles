<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <c:choose>
                    <c:when test="${empty currentUser}">
                        <form action="login" mathod="post">
                            <input type="submit" value="Login to continue..." class="btn btn-lg btn-primary"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="form-row">
                            <form class="form-inline" action="admin" method="post">
                                <h3>Orders</h3>
                                <input type="submit" value="Back" class="btn" />
                            </form>
                        </div>
                        <table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                  <th>Order Number</th>
                                  <th>Customer</th>
                                  <th>Date</th>
                                  <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${theOrders}" var="order">
                                    <tr>
                                        <td scope="row"><a href="?action=viewSpecificOrder&amp;userID=${order.user.userID}&amp;orderNumber=${order.orderNumber}">${order.orderNumber}</a></td>
                                        <td>${order.user.firstName} ${order.user.lastName}</td>
                                        <td>${order.date}</td>
                                        <td>$${order.getTotalCost()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>