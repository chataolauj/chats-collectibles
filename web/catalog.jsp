<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <nav class="breadcrumb">
      <a class="breadcrumb-item" href="index.jsp">Home</a>
      <span class="breadcrumb-item active">Catalog</span>
    </nav>
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-10">
            <aside>
                <h3>Cards</h3>
                <div class="card-deck">
                    <c:forEach items="${productList}" var="product">
                        <c:if test="${product.category == 'cards'}">
                            <div class="card">
                                <div class="card-header">
                                    <strong>${product.name}</strong>
                                </div>
                                <div class="card-body">
                                    <a href="catalog?productCode=${product.code}"><img class="card-img-top img-fluid" src="images/${product.getImageURL()}"></a>
                                </div>
                                <div class="card-footer">
                                    <strong>$${product.price} (${product.code})</strong>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                
                <br>

                <h3>Supplies</h3>
                <div class="card-deck">
                    <c:forEach items="${productList}" var="product">
                        <c:if test="${product.category == 'supplies'}">
                            <div class="card">
                                <div class="card-header">
                                    <strong>${product.name}</strong>
                                </div>
                                <div class="card-body">
                                    <a href="catalog?productCode=${product.code}"><img class="card-img-top img-fluid" src="images/${product.getImageURL()}"></a>
                                </div>
                                <div class="card-footer">
                                    <strong>$${product.price}</strong>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>