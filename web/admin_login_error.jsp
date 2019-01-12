<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <h3>Admin Login - Error</h3>
                <p>You did not log in successfully.</p>
                <p>Please check your username and password and try again.</p>
                <form action="j_security_check" method="POST">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Username</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="j_username">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="password" name="j_password">
                        </div>
                    </div>
                    <input class="btn btn-primary" type="submit" value="Login">    
                </form>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>