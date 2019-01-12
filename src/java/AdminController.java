import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String url = "/index.jsp";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action != null) {
            if(action.equals("viewCatalog")) {
                ArrayList<Product> products = ProductDB.getProducts();

                session.setAttribute("products", products);

                url = "/catalog-listing.jsp";
            }
            else if(action.equals("addProduct")) {
                url = "/new_product.jsp";
            }
            else if(action.equals("saveNewProduct")) {
                String name = request.getParameter("name");
                String category = request.getParameter("category");
                double price = Double.parseDouble(request.getParameter("price"));
                String productCode = request.getParameter("productCode");
                String description = request.getParameter("description");
                String imageURL = request.getParameter("imageURL");
                
                Product product = new Product(productCode, name, category, description, price, imageURL);
                ProductDB.addProduct(product);
                
                session.setAttribute("product", product);
                
                url = "/item.jsp";
            }
            else if(action.equals("viewSpecificProduct")) {
                String productCode = request.getParameter("productCode");
                
                Product product = ProductDB.getProduct(productCode);
                
                session.setAttribute("product", product);
                
                url = "/item.jsp";
            }
            else if(action.equals("editProduct")) {
                String productCode = request.getParameter("productCode");
                
                Product product = ProductDB.getProduct(productCode);
                
                session.setAttribute("product", product);
                
                url = "/edit_product.jsp";
            }
            else if(action.equals("saveProductEdit")) {
                String name = request.getParameter("name");
                String category = request.getParameter("category");
                double price = Double.parseDouble(request.getParameter("price"));
                String productCode = request.getParameter("productCode");
                String description = request.getParameter("description");
                String imageURL = request.getParameter("imageURL");
                
                Product currentProduct = (Product) session.getAttribute("product");
                
                Product product = new Product(productCode, name, category, description, price, imageURL);
                ProductDB.updateProduct(product, currentProduct.getCode());
                
                session.setAttribute("product", product);
                
                url = "/item.jsp";
            }
            else if(action.equals("deleteProduct")) {
                String productCode = request.getParameter("productCode");
                ProductDB.deleteProduct(productCode);
                
                url = "/catalog-listing.jsp";
            }
            else if(action.equals("viewOrders")) {
                ArrayList<Order> orders = OrderDB.getAllOrders();

                session.setAttribute("theOrders", orders);

                url = "/order-listing.jsp";
            }
            else if(action.equals("viewSpecificOrder")) {
                int userID = Integer.parseInt(request.getParameter("userID"));
                int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
                
                Order order = OrderDB.getOrder(userID, orderNumber);
                ArrayList<OrderItem> items = OrderDB.getOrderItems(order.getOrderNumber());
                
                order.setItems(items);
                
                session.setAttribute("currentOrder", order);
                
                url = "/order.jsp";
            }
            else if(action.equals("editOrder")) {
                url = "/edit_order.jsp";
            }
            else if(action.equals("updateQuantity")) {
                String productCode = request.getParameter("productCode");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                
                Order order = (Order) session.getAttribute("currentOrder");
                ArrayList<OrderItem> items = order.getItems();
                
                for(OrderItem item : items) {
                    if(productCode.equals(item.getProduct().getCode())) {
                        item.setQuantity(quantity);
                        order.addItem(item);
                        
                        if(item.getQuantity() == 0) {
                            order.removeItem(item);
                        }
                    }
                }
                
                session.setAttribute("currentOrder", order);
                
                url = "/edit_order.jsp";
            }
            else if(action.equals("saveOrderEdit")) {
                Order order = (Order) session.getAttribute("currentOrder");
                ArrayList<OrderItem> items = order.getItems();
                
                for(OrderItem item : items) {
                    OrderDB.updateOrder(order, item);
                }
                
                order = OrderDB.getOrder(order.getUser().getUserID(), order.getOrderNumber());

                session.setAttribute("currentOrder", order);

                url = "/order.jsp";
            }
            else if(action.equals("cancelOrderEdit")) {
                Order order = (Order) session.getAttribute("currentOrder");
                
                session.setAttribute("currentOrder", order);
                
                url = "/order.jsp";
            }
            else if(action.equals("viewUsers")) {
                ArrayList<User> users = UserDB.getUsers();

                session.setAttribute("theUsers", users);

                url = "/user-listing.jsp";
            }
            else if(action.equals("viewSpecificUser")) {
                String username = request.getParameter("username");
                
                User userRole = UserDB.getUserRole(username);
                User user = UserDB.getUser(username);
                user.setRolename(userRole.getRolename());
                
                session.setAttribute("viewedUser", user);
                
                url = "/profile.jsp";
            }
            else if(action.equals("editUser")) {
                url = "/edit_profile.jsp";
            }
            else if(action.equals("saveUserEdit")) {
                String rolename = request.getParameter("rolename");
                
                User viewedUser = (User) session.getAttribute("viewedUser");
                UserDB.updateUserRole(viewedUser.getUsername(), rolename);
                
                url = "/profile.jsp";
            }
            else if(action.equals("saveUserEdit")) {
                url = "/profile.jsp";
            }
            else if(action.equals("cancelUserEdit")) {
                url = "/profile.jsp";
            }
            else if(action.equals("deleteUser")) {
                int userID = Integer.parseInt(request.getParameter("userID"));
                
                UserDB.deleteUser(userID);
                
                url = "user-listing.jsp";
            }
        }
        else {
            url = "/administration.jsp";
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