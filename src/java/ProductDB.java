import java.io.*;
import java.util.*;
import java.sql.*;

public class ProductDB{
    
    public static int addProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Product (ProductCode, Name, Category, Description, Price, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getPrice());
            ps.setString(6, product.getImageURL());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
    
    public static Product getProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product WHERE ProductCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            Product product = null;
            if(rs.next()) {
                product = new Product();
                product.setCode(rs.getString("ProductCode"));
                product.setName(rs.getString("Name"));
                product.setCategory(rs.getString("Category"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setImageURL(rs.getString("ImageURL"));
            }
            
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Product> getProducts() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Product product;
            ArrayList<Product> products = new ArrayList<Product>();
            while (rs.next()) {
                product = new Product();
                product.setCode(rs.getString("ProductCode"));
                product.setName(rs.getString("Name"));
                product.setCategory(rs.getString("Category"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setImageURL(rs.getString("ImageURL"));
                
                products.add(product);
            }
            
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateProduct(Product product, String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Product "
                + "SET ProductCode = ?, Name = ?, Category = ?, Description = ?, Price = ?, ImageURL = ? "
                + "WHERE ProductCode = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, Double.parseDouble(product.getPrice()));
            ps.setString(6, product.getImageURL());
            ps.setString(7, productCode);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int deleteProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Product WHERE ProductCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}