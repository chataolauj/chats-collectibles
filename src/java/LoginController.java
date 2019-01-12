import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter( );
        String url = "/index.jsp";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action != null) {
            if(action.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                if(UserDB.validateUser(username, password) == true) {
                    User userRole = UserDB.getUserRole(username);
                    User user;
                    
                    if(userRole.getRolename().equals("administrator")) {
                        user = userRole;
                    }
                    else {
                        user = UserDB.getUser(username);
                        user.setRolename(userRole.getRolename());
                    }
                    
                    session.setAttribute("currentUser", user);
                    
                    url = "/index.jsp";
                }
                else {
                    url = "/login_error.jsp";
                }
            }
            else if(action.equals("register")) {
                url = "/register.jsp";
            }
            else if(action.equals("createAccount")) {
                session.setAttribute("usernameTaken", false);
                session.setAttribute("emailTaken", false);
                
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String passwordConfirmation = request.getParameter("passwordConfirmation");
                
                if(!UserDB.checkUsername(username)) {
                    if(!UserDB.checkEmail(email)) {
                        if(password.equals(passwordConfirmation)) {
                            User user = new User();

                            user.setUsername(username);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setRolename("basic");

                            UserDB.addUser(user);
                            UserDB.addUserPass(user);
                            UserDB.addUserRole(user);

                            user.setUserID(UserDB.getUserID(user.getUsername()));

                            session.setAttribute("currentUser", user);

                            url = "/index.jsp";
                        }
                        else {
                            session.setAttribute("passwordsMatched", false);

                            url = "/registration_error.jsp";
                        }
                    }
                    else {
                        session.setAttribute("emailTaken", true);

                        url = "/registration_error.jsp";
                    }
                }
                else {
                    session.setAttribute("usernameTaken", true);

                    url = "/registration_error.jsp";
                }
            }
            else if(action.equals("logout")) {
                session.invalidate();
            }
        }
        else {
            url = "/login.jsp";
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