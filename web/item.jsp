<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <nav class="breadcrumb">
      <a class="breadcrumb-item" href="index.jsp">Home</a>
        <a class="breadcrumb-item" href="catalog">Catalog</a>
      <span class="breadcrumb-item active">${product.name}</span>
    </nav>
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-10">
            <aside>
                <c:if test="${currentUser.rolename == 'administrator'}">
                    <div class="form-row">
                        <form class="form-inline" action="admin" method="post">
                            <input type="hidden" name="action" value="viewCatalog" />
                            <input type="submit" value="Back" class="btn" />
                        </form>
                        <form class="form-inline" action="admin" method="post">
                            <input type="hidden" name="productCode" value="<c:out value='${product.code}'/>" />
                            <input type="hidden" name="action" value="editProduct" />
                            <input type="submit" value="Edit" class="btn btn-primary" />
                        </form>
                        <form class="form-inline" action="admin" method="post">
                            <input type="hidden" name="productCode" value="<c:out value='${product.code}'/>" />
                            <input type="hidden" name="action" value="deleteProduct" />
                            <input type="submit" value="Delete" class="btn btn-danger" />
                        </form>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-sm-4 item-img">
                        <img src="images/${product.getImageURL()}" alt="${product.name}"/>
                        <p>$${product.price}</p> <br>
                        <c:if test="${currentUser.rolename != 'administrator'}">
                            <form class="form-group" action="cart" method="post">
                                <input type="hidden" name="productList" value="<c:out value='${product.code}'/>">
                                <input type="hidden" name="action" value="updateCart">
                                <input type="submit" value="Add To Cart" class="btn btn-block btn-success">
                            </form>
                        </c:if>
                    </div>
                    <div class="col-sm-8">
                        <div class="row">
                            <div>
                                <h3>${product.name}</h3>
                            </div>
                        </div>
                        <div class="row item-description">
                            <div class="col-sm-12 item-description">
                                <c:if test="${product.category == 'cards'}">
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <p><strong>Rarity:</strong></p>
                                        </div>
                                        <div class="col-sm-10">
                                            <p>Mythic</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <p><strong>Card Type:</strong></p>
                                        </div>
                                        <div class="col-sm-10">
                                            <p>Planeswalker</p>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <p><strong>Description:</strong></p>
                                    </div>
                                    <div class="col-sm-10">
                                        <p>${product.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>