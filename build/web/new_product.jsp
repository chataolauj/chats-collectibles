<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="user-navigation.jsp" %>
<div class="content container">
    <div class="row">
        <%@include file="site-navigation.jsp" %>
        <div class="col-sm-8">
            <aside>
                <h3>Add Product</h3>
                <form action="" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="name">Product Name</label>
                            <input type="text" name="name" class="form-control" id="name" placeholder="Product Name" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="category">Category</label>
                            <select name="category" id="category" class="form-control" required>
                                <option></option>
                                <option>cards</option>
                                <option>supplies</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="price">Price</label>
                            <input type="number" name="price" class="form-control" id="price" placeholder="Product Price" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="productCode">Product Code</label>
                            <input type="text" name="productCode" class="form-control" id="productPrice" placeholder="Product Code" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea type="textarea" name="description" rows="5" class="form-control" id="description" placeholder="Product Description" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="imageURL"></label>
                        <input type="text" name="imageURL" class="form-control" id="imageURL" placeholder="Image URL for product">
                    </div>
                    <br>
                    <input type="hidden" name="action" value="saveNewProduct" />
                    <input type="submit" value="Save Changes" class="btn btn-block btn-primary" />
                </form>
                <form action="" method="post">
                    <label></label>
                    <input type="hidden" name="action" value="updateCatalog" />
                    <input type="submit" value="Cancel" class="btn btn-block btn-danger" />
                </form>
            </aside>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>