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
                        <c:if test="${empty theShoppingCart.items}">
                            <h3>Your Cart is Empty...</h3>
                            <form action="catalog">
                                <input class="btn btn-success btn-lg" type="submit" value="Continue Shopping">
                            </form>
                        </c:if>
                        <c:if test="${not empty theShoppingCart.items}">
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
                                    <c:forEach items="${theShoppingCart.items}" var="item">
                                        <tr>
                                            <th scope="row">${item.product.name}</th>
                                            <td>$${item.product.price}</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <form action="" method="post">
                                                            <input type="hidden" name="productList" value="<c:out value='${item.product.code}'/>">
                                                            <input style="width: 50px" type="number" name="${item.product.code}" value="<c:out value='${item.quantity}'/>">
                                                            <input type="hidden" name="action" value="updateCart">
                                                            <input class="btn btn-primary btn-sm" type="submit" value="Update">
                                                        </form>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <form action="" method="post">
                                                            <input type="hidden" name="productList" value="<c:out value='${item.product.code}'/>">
                                                            <input type="hidden" name="${item.product.code}" value="0">
                                                            <input type="hidden" name="action" value="updateCart">
                                                            <input class="btn btn-danger btn-sm" type="submit" value="Remove">
                                                        </form>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>$${item.getTotal()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="cart-form">
                                <c:if test="${not empty theShoppingCart.items}">
                                    <p>Subtotal: $${theShoppingCart.getTotal()}</p>
                                    <form action="catalog" method="post">
                                        <input class="btn btn-primary" type="submit" value="Back to Catalog">
                                    </form>
                                    <form action="" method="post">
                                        <input type="hidden" name="action" value="checkout" />
                                        <input class="btn btn-success" type="submit" value="Checkout">
                                    </form>
                                </c:if>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>