import java.io.*;
import java.util.*;
import java.text.*;

public class Order implements Serializable {
    ArrayList<OrderItem> items;
    User user;
    int orderNumber;
    Date date;
    double taxRate;
    double totalCost;
    boolean paid;
    
    public Order() {
        items = new ArrayList<OrderItem>();
        date = new Date();
        taxRate = 0.0;
        totalCost = 0.0;
        paid = false;
    }
    
    public void addItem(OrderItem item) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getCode().equals(item.getProduct().getCode())) {
                orderItem.setQuantity(item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        for (int i = 0; i < items.size(); i++) {
            OrderItem orderItem = items.get(i);
            if (orderItem.getProduct().getCode().equals(item.getProduct().getCode())) {
                items.remove(i);
                return;
            }
        }
    }
    
    void setItems(ArrayList<OrderItem> items) {
        /*for(OrderItem item : items) {
            this.items.add(item);
        }*/
        this.items = items;
    }
    
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    
    void setUser(User u) {
        user = u;
    }
    
    public User getUser() {
        return user;
    }
    
    void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public String dateFormatter() {
        SimpleDateFormat dft = new SimpleDateFormat ("MM/DD/YYYY");
        
        return dft.format(date);
    }
    
    void setDate(Date date) {
        SimpleDateFormat dft = new SimpleDateFormat ("YYYY-MM-DD");
        dft.format(date);
    }
    
    public java.sql.Date getDate() {
        java.sql.Date now = new java.sql.Date(date.getTime());
        return now;
    }
    
    void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    
    public String getTaxRate() {
        NumberFormat nf = new DecimalFormat("0.00");
        
        return nf.format(taxRate);
    }
    
    void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public String getSubTotal() {
        double total = 0.0;
        NumberFormat nf = new DecimalFormat("0.00");
        for(OrderItem item :  items) {
            total += Double.parseDouble(item.getTotal());
        }
        
        return nf.format(total);
    }
    
    public String getTotalCost() {
        NumberFormat nf = new DecimalFormat("0.00");
        double total = 0.0;
        
        for(OrderItem item : items) {
            total += Double.parseDouble(item.getTotal());
        }
        
        totalCost = total + (total * (taxRate / 100));
        
        return nf.format(totalCost);
    }
    
    void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public boolean getPaid() {
        return paid;
    }
}