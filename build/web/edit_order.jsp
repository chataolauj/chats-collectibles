<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
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
                                <td>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form action="" method="post">
                                                <input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>">
                                                <input style="width: 50px" type="number" name="quantity" value="<c:out value='${item.quantity}'/>">
                                                <input type="hidden" name="action" value="updateQuantity">
                                                <input class="btn btn-primary btn-sm" type="submit" value="Update">
                                            </form>
                                        </div>
                                        <div class="col-sm-12">
                                            <form action="" method="post">
                                                <input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>">
                                                <input type="hidden" name="quantity" value="0">
                                                <input type="hidden" name="action" value="updateQuantity">
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
                    <p>Subtotal: $${currentOrder.getSubTotal()}</p>
                    <p>Tax: ${currentOrder.getTaxRate()}</p>
                    <p>Total: $${currentOrder.getTotalCost()}</p>
                    <form action="" method="post">
                        <input type="hidden" name="action" value="saveOrderEdit" />
                        <input class="btn btn-success" type="submit" value="Save Changes">
                    </form>
                    <form action="" method="post">
                        <input type="hidden" name="action" value="cancelOrderEdit" />
                        <input class="btn btn-danger" type="submit" value="Cancel">
                    </form>
                </div>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>