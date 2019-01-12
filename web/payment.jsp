<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <h3>Payment</h3>
                <div class="container col-lg-8">
                    <form action="" method="post">
                        <div class="form-group">
                            <label>Name on Card</label>
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Card Type</label>
                            <select class="form-control">
                                <option>Select Card Type</option>
                                <option>Visa</option>
                                <option>Mastercard</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Card Number</label>
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label>Expiration Date</label>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <select class="form-control">
                                            <option>MM</option>
                                            <option>01</option>
                                            <option>02</option>
                                            <option>03</option>
                                            <option>04</option>
                                            <option>05</option>
                                            <option>06</option>
                                            <option>07</option>
                                            <option>08</option>
                                            <option>09</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <select class="form-control">
                                            <option>YYYY</option>
                                            <option>2017</option>
                                            <option>2018</option>
                                            <option>2019</option>
                                            <option>2020</option>
                                            <option>2021</option>
                                            <option>2022</option>
                                            <option>2023</option>
                                            <option>2024</option>
                                            <option>2025</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-4">
                                    <label>CVC</label>
                                    <input type="text" class="form-control">
                                </div>
                        </div>
                        <input type="hidden" name="action" value="confirmOrder" />
                        <input type="submit" value="Confirm Payment" class="btn btn-block btn-success" />
                    </form>
                </div>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>