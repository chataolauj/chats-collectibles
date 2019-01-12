import java.io.*;
import java.text.*;

public class OrderItem implements Serializable {
    Product product;
    int quantity;
    int orderNumber;
    
    public OrderItem() {
        quantity = 0;
    }
    
    void setProduct(Product p) {
        product = p;
    }
    
    public Product getProduct() {
        return product;
    }
    
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public String getTotal() {
        NumberFormat nf = new DecimalFormat("0.00");
        double total = Double.parseDouble(product.getPrice()) * quantity;
        
        return nf.format(total);
    }
}