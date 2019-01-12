<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <nav class="breadcrumb">
        <a class="breadcrumb-item" href="home.jsp">Home</a>
        <span class="breadcrumb-item active">About</span>
    </nav>
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <p>We are a Magic: The Gathering shop located in North Carolina, and are dedicated 
                    to providing our customers with unbeatable prices!</p>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>