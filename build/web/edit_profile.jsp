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
                        <h3>Edit User</h3>
                        <div class="form-row">
                            <form action="" method="post" class="form-inline">
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="rolename">Rolename</label>
                                        <select name="rolename" value="${viewedUser.rolename}" id="rolename" class="form-control">
                                            <option>${viewedUser.rolename}</option>
                                            <option>basic</option>
                                            <option>administrator</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-10">
                                    </div>
                                </div>
                                <input type="hidden" name="" value="saveUserEdit" />
                                <input type="submit" value="Save Changes" class="btn btn-primary"/>
                            </form>
                            <form class="form-inline" action="" method="post">
                                <input type="hidden" name="action" value="cancelUserEdit" />
                                <input type="submit" value="Cancel" class="btn btn-danger"/>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h3>Edit Profile</h3>
                        <form action="" method="post">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="username">Username</label>
                                    <input type="text" name="username" value="${currentUser.username}" class="form-control" id="username" placeholder="Username">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" name="email" value="${currentUser.email}" class="form-control" id="email" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="firstName">First Name</label>
                                    <input type="text" name="firstName" value="${currentUser.firstName}" class="form-control" id="firstName" placeholder="First Name" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" name="lastName" value="${currentUser.lastName}" class="form-control" id="lastName" placeholder="Last Name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="addressOne">Address</label>
                                <input type="text" name="addressOne" value="${currentUser.addressOne}" class="form-control" id="addressOne" placeholder="1234 Main St" required>
                            </div>
                            <div class="form-group">
                                <label for="addressTwo">Address 2</label>
                                <input type="text" name="addressTwo" value="${currentUser.addressTwo}" class="form-control" id="addressTwo" placeholder="Apartment, studio, or floor">
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="city">City</label>
                                    <input type="text" name="city" value="${currentUser.city}" class="form-control" id="city" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="state">State</label>
                                    <select name="state" value="${currentUser.state}" id="state" class="form-control" required>
                                        <option>${currentUser.state}</option>
                                        <option>NC</option>
                                        <option>Other</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="postCode">Zip</label>
                                    <input type="text" name="postCode" value="${currentUser.postCode}" class="form-control" id="postCode" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="country">Country</label>
                                    <select name="country" value="${currentUser.country}" id="country" class="form-control" required>
                                        <option>${currentUser.country}</option>
                                        <option>US</option>
                                        <option>Other</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" name="action" value="saveEdit" />
                            <input type="submit" value="Save Changes" class="btn btn-block btn-primary" />
                        </form>
                        <br>
                        <form action="user" method="post">
                            <input type="submit" value="Cancel" class="btn btn-block btn-danger" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>