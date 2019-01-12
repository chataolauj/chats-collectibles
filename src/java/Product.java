import java.io.*;
import java.text.*;

public class Product implements Serializable {
    String code;
    String name;
    String category;
    String description;
    double price;
    String imageURL;
    
    public Product() {
        code = "";
        name = "";
        category = "";
        description = "";
        price = 0.0;
    }
    
    public Product(String code, String name, String category, String description, double price, String imageURL) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getPrice() {
        NumberFormat nf = new DecimalFormat("0.00");
        
        return nf.format(price);
    }
    
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
    
    public String getImageURL() {
        String url = code + ".jpg";
        return url;
    }
}
