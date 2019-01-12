<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <c:choose>
                    <c:when test="${currentUser.rolename == 'administrator'}">
                        <div class="form-row">
                            <form class="form-inline" action="admin" method="post">
                                <h3>${viewedUser.username}'s Profile</h3>
                                <input type="hidden" name="action" value="viewUsers" />
                                <input type="submit" value="Back" class="btn" />
                            </form>
                            <form class="form-inline" action="admin" method="post">
                                <input type="hidden" name="userID" value="<c:out value='${viewedUser.userID}'/>" />
                                <input type="hidden" name="action" value="editUser" />
                                <input type="submit" value="Edit" class="btn btn-primary" />
                            </form>
                            <form class="form-inline" action="admin" method="post">
                                <input type="hidden" name="userID" value="<c:out value='${viewedUser.userID}'/>" />
                                <input type="hidden" name="action" value="deleteUser" />
                                <input type="submit" value="Delete" class="btn btn-danger" />
                            </form>
                        </div>
                        <p><strong>Username</strong>: ${viewedUser.username}</p>
                        <p><strong>Email</strong>: ${viewedUser.email}</p>
                        <p><strong>Name</strong>: ${viewedUser.firstName} ${viewedUser.lastName}</p>
                        <p><strong>Address</strong>: </p>
                        <c:if test="${!empty viewedUser.addressOne}">
                            <p>${viewedUser.addressOne}</p>
                            <p>${viewedUser.city}, ${viewedUser.state} ${viewedUser.postCode}</p>
                            <p>${viewedUser.country}</p>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <div class="form-row">
                            <form class="form-inline" action="" method="post">
                                <h3>Profile</h3>
                                <input type="hidden" name="action" value="edit" />
                                <input type="submit" value="Edit Profile" class="btn btn-primary" />
                            </form>
                        </div>
                        <p><strong>Username</strong>: ${currentUser.username}</p>
                        <p><strong>Email</strong>: ${currentUser.email}</p>
                        <p><strong>Name</strong>: ${currentUser.firstName} ${currentUser.lastName}</p>
                        <p><strong>Address</strong>: </p>
                        <c:if test="${!empty currentUser.addressOne}">
                            <p>${currentUser.addressOne}</p>
                            <p>${currentUser.city}, ${currentUser.state} ${currentUser.postCode}</p>
                            <p>${currentUser.country}</p>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>