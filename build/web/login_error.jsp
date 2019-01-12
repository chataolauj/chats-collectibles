<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <h3>Login</h3>
                <p>Incorrect username and/or password. Try again.</p>
                <form class="login-form" method="POST" action="">
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control">
                    </div>
                    <input type="hidden" name="action" value="login" />
                    <input type="submit" value="Log In" class="btn btn-primary" />
                </form>
                <a href="login?action=register">Register</a>
                <br>
                <a href="">Forgot Password?</a>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>