import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CatalogController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
        ServletContext sc = getServletContext();
       
        ArrayList<Product> products = ProductDB.getProducts();
        request.setAttribute("productList", products);
        
        String productCode = request.getParameter("productCode");
        if(productCode != null) {
            if(productCode.equals("MM17-76") || productCode.equals("MM17-141")
               || productCode.equals("MM17-50") || productCode.equals("db01")
               || productCode.equals("sd01") || productCode.equals("pm01")) {
                Product item = new Product();
                
                for(Product product:products) {
                    if(product.getCode().equals(productCode)) {
                        item.setCode(product.getCode());
                        item.setName(product.getName());
                        item.setCategory(product.getCategory());
                        item.setDescription(product.getDescription());
                        item.setPrice(Double.parseDouble(product.getPrice()));
                    }
                }
                
                request.setAttribute("product", item);

                url = "/item.jsp";
            }
            else {
                url = "/catalog.jsp";
            }
        }
        else {
            url = "/catalog.jsp";
        }
        
        sc.getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
    
    