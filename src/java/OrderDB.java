import java.io.*;
import java.util.*;
import java.sql.*;

public class OrderDB{
    
    public static int addOrder(Order order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Orders (Date, UserID, TaxRate, TotalCost, Paid) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, order.getDate());
            ps.setInt(2, order.getUser().getUserID());
            ps.setDouble(3, Double.parseDouble(order.getTaxRate()));
            ps.setDouble(4, Double.parseDouble(order.getTotalCost()));
            ps.setBoolean(5, order.getPaid());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int addOrderItem(OrderItem orderItem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO OrderItem (OrderNumber, ProductCode, Quantity) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderItem.getOrderNumber());
            ps.setString(2, orderItem.getProduct().getCode());
            ps.setInt(3, orderItem.getQuantity());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int getOrderNumber() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Orders ORDER BY OrderNumber DESC LIMIT 1";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            int orderNumber = 0;
            if(rs.next()) {
                orderNumber = rs.getInt("OrderNumber");
            }
            
            return orderNumber;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Order getOrder(int userID, int orderNumber) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Orders, User WHERE Orders.UserID = User.UserID AND User.UserID = ? AND Orders.OrderNumber = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, orderNumber);
            rs = ps.executeQuery();
            Order order = null;
            if(rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getInt("OrderNumber"));
                order.setUser(UserDB.getUser(rs.getString("Username")));
                order.setDate(rs.getDate("Date"));
                order.setTaxRate(rs.getDouble("TaxRate"));
                order.setTotalCost(rs.getDouble("TotalCost"));
                order.setPaid(rs.getBoolean("Paid"));
            }
            
            return order;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Order> getUserOrders(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Orders WHERE UserID = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getUserID());
            rs = ps.executeQuery();
            Order order;
            ArrayList<Order> orders = new ArrayList<Order>();
            while (rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getInt("OrderNumber"));
                order.setUser(user);
                order.setDate(rs.getDate("Date"));
                order.setTaxRate(rs.getDouble("TaxRate"));
                order.setTotalCost(rs.getDouble("TotalCost"));
                order.setPaid(rs.getBoolean("Paid"));
                
                orders.add(order);
            }
            
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Order> getAllOrders() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Orders, User WHERE Orders.UserID = User.UserID";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Order order;
            ArrayList<Order> orders = new ArrayList<Order>();
            while (rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getInt("OrderNumber"));
                order.setUser(UserDB.getUser(rs.getString("Username")));
                order.setDate(rs.getDate("Date"));
                order.setTaxRate(rs.getDouble("TaxRate"));
                order.setTotalCost(rs.getDouble("TotalCost"));
                order.setPaid(rs.getBoolean("Paid"));
                
                orders.add(order);
            }
            
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<OrderItem> getOrderItems(int orderNumber) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM OrderItem WHERE OrderNumber = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            rs = ps.executeQuery();
            OrderItem item;
            ArrayList<OrderItem> items = new ArrayList<>();
            while(rs.next()) {
                item = new OrderItem();
                item.setOrderNumber(rs.getInt("OrderNumber"));
                item.setProduct(ProductDB.getProduct(rs.getString("ProductCode")));
                item.setQuantity(rs.getInt("Quantity"));
                
                items.add(item);
            }
            
            return items;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateOrder(Order order, OrderItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Orders INNER JOIN OrderItem "
                + "SET TotalCost = ?, Quantity = ? "
                + "WHERE Orders.OrderNumber = ? AND OrderItem.OrderNumber = ? AND ProductCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, Double.parseDouble(order.getTotalCost()));
            ps.setInt(2, item.getQuantity());
            ps.setInt(3, order.getOrderNumber());
            ps.setInt(4, item.getOrderNumber());
            ps.setString(5, item.getProduct().getCode());

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