import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String url = "/index.jsp";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        
        ArrayList<Product> products = ProductDB.getProducts();
        session.setAttribute("productList", products);
        User currentUser = (User) session.getAttribute("currentUser");
        
        
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        if (cart == null) {
            cart = new Cart();
        }
        
        String action = request.getParameter("action");
        
        if(action != null) {
            if(action.equals("updateCart")) {
                String[] productList = request.getParameterValues("productList");
                
                for(int i = 0; i < productList.length; i++) {
                    if(productList[i].equals("MM17-76") || productList[i].equals("MM17-141")
                    || productList[i].equals("MM17-50") || productList[i].equals("db01")
                    || productList[i].equals("sd01") || productList[i].equals("pm01")) {
                        ArrayList<OrderItem> items = cart.getItems();
                        OrderItem orderItem = new OrderItem();
                        
                        String quantityString = (String) request.getParameter(productList[i]);
                        int quantity = orderItem.getQuantity();
                        int quantityToUpdate;
                        
                        if(items.size() != 0) {
                            for(OrderItem item : items) {
                                if(item.getProduct().getCode().equals(productList[i])) {
                                    quantity = item.getQuantity();
                                    orderItem.setProduct(item.getProduct());
                                }
                                else {
                                    Product p = new Product();

                                    for(Product product : products) {
                                        if(product.getCode().equals(productList[i])) {
                                            p.setCode(product.getCode());
                                            p.setName(product.getName());
                                            p.setCategory(product.getCategory());
                                            p.setDescription(product.getDescription());
                                            p.setPrice(Double.parseDouble(product.getPrice()));

                                            orderItem.setProduct(p);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            Product p = new Product();

                            for(Product product : products) {
                                if(product.getCode().equals(productList[i])) {
                                    p.setCode(product.getCode());
                                    p.setName(product.getName());
                                    p.setCategory(product.getCategory());
                                    p.setDescription(product.getDescription());
                                    p.setPrice(Double.parseDouble(product.getPrice()));

                                    orderItem.setProduct(p);
                                }
                            }
                        }
                        
                        try {
                            quantityToUpdate = Integer.parseInt(quantityString);
                            
                            if(quantityToUpdate == 0) {
                                quantity = 0;
                            }
                            else if(quantityToUpdate != quantity) {
                                quantity = quantityToUpdate;
                            }
                            else if(quantityToUpdate == quantity || quantityToUpdate < 0) {
                                url = "/cart.jsp";
                            }
                        }
                        catch (NumberFormatException nfe){
                            quantity++;
                        }
                        
                        
                        orderItem.setQuantity(quantity);

                        if(quantity > 0) {
                            cart.addItem(orderItem);
                        }
                        else if(quantity == 0) {
                            cart.removeItem(orderItem);
                        }
                        
                        session.setAttribute("theShoppingCart", cart);
                        url = "/cart.jsp";
                    }
                }
            }
            else if(action.equals("checkout")) {
                Date now = new Date();
                
                Order order = new Order();
                order.setItems(cart.getItems());
                order.setUser(currentUser);
                order.setDate(now);
                order.setTaxRate(2.00);
                
                session.setAttribute("theUser", currentUser);
                session.setAttribute("currentOrder", order);
                
                url = "/order.jsp";
            }
            else if(action.equals("purchaseOrder")) {
                url = "/payment.jsp";
            }
            else if(action.equals("addAddress")) {
                url = "/new_address.jsp";
            }
            else if(action.equals("saveAddress")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String addressOne = request.getParameter("addressOne");
                String addressTwo = request.getParameter("addressTwo");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String postCode = request.getParameter("postCode");
                String country = request.getParameter("country");
                
                currentUser = (User) session.getAttribute("currentUser");
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setAddressOne(addressOne);
                currentUser.setAddressTwo(addressTwo);
                currentUser.setCity(city);
                currentUser.setState(state);
                currentUser.setPostCode(postCode);
                currentUser.setCountry(country);
                
                UserDB.updateUserProfile(currentUser, currentUser.getUserID());
                
                session.setAttribute("theUser", currentUser);
                session.setAttribute("currentUser", currentUser);
                
                url = "/cart?action=purchaseOrder";
            }
            else if(action.equals("confirmOrder")) {
                User user = (User) session.getAttribute("theUser");
                Order order = (Order) session.getAttribute("currentOrder");
                
                order.setPaid(true);
                order.setUser(user);
                
                OrderDB.addOrder(order);
                order.setOrderNumber(OrderDB.getOrderNumber());
                
                ArrayList<OrderItem> items = order.getItems();
                
                for(OrderItem orderItem : items) {
                    orderItem.setOrderNumber(OrderDB.getOrderNumber());
                    OrderDB.addOrderItem(orderItem);
                }
                
                cart = null;
                session.setAttribute("theShoppingCart", cart);
                session.setAttribute("currentOrder", order);
                
                url = "/order.jsp";
            }
        }
        else {
            url = "/cart.jsp";
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