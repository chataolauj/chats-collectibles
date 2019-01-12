import java.io.*;
import java.util.*;
import java.text.*;

public class Cart implements Serializable{
    ArrayList<OrderItem> items;
    
    public Cart() {
        items = new ArrayList<OrderItem>();
    }
    
    public ArrayList getItems() { 
        return items;
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
    
    public void emptyCart() {
        for(OrderItem item : items) {
            removeItem(item);
        }
    }
    
    public String getTotal() {
        double total = 0.0;
        NumberFormat nf = new DecimalFormat("0.00");
        for(OrderItem item :  items) {
            total += Double.parseDouble(item.getTotal());
        }
        
        return nf.format(total);
    }
}