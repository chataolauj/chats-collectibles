<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <nav class="breadcrumb">
        <a class="breadcrumb-item" href="home.jsp">Home</a>
        <span class="breadcrumb-item active">Contact</span>
    </nav>
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <p>Feel free to contact us through email or by calling us.</p>
                <p>Email: example@gmail.com</p>
                <p>Phone: 000-000-0000</p>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>