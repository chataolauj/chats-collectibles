<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <div class="form-row">
                    <form class="form-inline" action="admin" method="post">
                        <h3>Products</h3>
                        <input type="submit" value="Back" class="btn" />
                    </form>
                    <form class="form-inline" action="" method="post">
                        <input type="hidden" name="action" value="addProduct" />
                        <input type="submit" value="Add Product" class="btn btn-success" />
                    </form>
                </div>
                <table class="table">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td scope="row"><a href="?action=viewSpecificProduct&amp;productCode=${product.code}">${product.name}</a></td>
                                <td>${product.category}</td>
                                <td>$${product.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>