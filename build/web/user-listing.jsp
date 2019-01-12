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
                        <h3>Users</h3>
                        <input type="submit" value="Back" class="btn" />
                    </form>
                </div>
                <table class="table">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${theUsers}" var="user">
                            <tr>
                                <td scope="row"><a href="?action=viewSpecificUser&amp;username=${user.username}">${user.username}</a></td>
                                <td>${user.email}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>