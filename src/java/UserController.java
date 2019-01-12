import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String url = "/index.jsp";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");
        
        if(action != null) {
            if(action.equals("edit")) {
                url = "/edit_profile.jsp";
            }
            else if(action.equals("saveEdit")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String addressOne = request.getParameter("addressOne");
                String addressTwo = request.getParameter("addressTwo");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String postCode = request.getParameter("postCode");
                String country = request.getParameter("country");
                
                User user = new User(firstName, lastName, email, username, "", "", addressOne, addressTwo, city, state, postCode, country);
                
                UserDB.updateUserProfile(user, currentUser.getUserID());
                user.setUserID(currentUser.getUserID());
                
                session.setAttribute("currentUser", user);
                
                url = "/profile.jsp";
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
            else if(action.equals("viewOrders")) {
                
                
                if(currentUser != null) {
                    ArrayList<Order> orders = OrderDB.getUserOrders(currentUser);

                    session.setAttribute("theOrders", orders);

                    url = "/order-listing.jsp";
                }
                else {
                    url = "/order-listing.jsp";
                }
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
        }
        else {
            url = "/profile.jsp";
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