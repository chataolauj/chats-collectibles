<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <c:if test="${currentUser.rolename == 'administrator'}">
                    <div class="form-row">
                        <form class="form-inline" action="admin" method="post">
                            <h2>Order #${currentOrder.orderNumber}</h2>
                            <input type="hidden" name="action" value="viewOrders" />
                            <input class="btn" type="submit" value="Back">
                        </form>
                        <form class="form-inline" action="admin" method="post">
                            <input type="hidden" name="action" value="editOrder" />
                            <input class="btn btn-primary" type="submit" value="Edit">
                        </form>
                    </div>
                </c:if>
                <h3>Invoice</h3>
                <div class="cart-billing">
                    <c:if test="${!empty currentOrder.user.addressOne}">
                        <p><strong>Ship/Bill To:</strong></p>
                        <p>${currentOrder.user.firstName} ${currentOrder.user.lastName}</p>
                        <p>${currentOrder.user.addressOne}</p>
                        <p>${currentOrder.user.city}, ${currentOrder.user.state} ${currentOrder.user.postCode}</p>
                        <p>${currentOrder.user.country}</p>
                    </c:if>
                </div>
                <br>
                <table class="table">
                    <thead class="thead-inverse">
                        <tr>
                          <th>Item</th>
                          <th>Price</th>
                          <th>Quantity</th>
                          <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${currentOrder.items}" var="item">
                            <tr>
                                <th scope="row">${item.product.name}</th>
                                <td>$${item.product.price}</td>
                                <td>${item.quantity}</td>
                                <td>$${item.getTotal()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="cart-form">
                    <p>Subtotal: $${currentOrder.getSubTotal()}</p>
                    <p>Tax: ${currentOrder.getTaxRate()}</p>
                    <p>Total: $${currentOrder.getTotalCost()}</p>
                    <c:if test="${currentOrder.paid != true}">
                        <form action="cart">
                            <input class="btn btn-primary" type="submit" value="Back to Cart">
                        </form>
                        <c:choose>
                            <c:when test="${empty theUser.addressOne}">
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="addAddress" />
                                    <input type="submit" value="Purchase" class="btn btn-success"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="purchaseOrder" />
                                    <input class="btn btn-success" type="submit" value="Purchase" />
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>