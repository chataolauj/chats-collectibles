<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <h3>Administrator Menu</h3>
                <form action="" method="post">
                    <input type="hidden" name="action" value="viewCatalog" />
                    <input class="btn btn-primary" type="submit" value="View Catalog">
                </form>
                <br>
                <form action="" method="post">
                    <input type="hidden" name="action" value="viewOrders" />
                    <input class="btn btn-primary" type="submit" value="View Orders">
                </form>
                <br>
                <form action="" method="post">
                    <input type="hidden" name="action" value="viewUsers" />
                    <input class="btn btn-primary" type="submit" value="View Users">
                </form>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>