import beans.User;
import database.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Register")
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        UserDB udb = new UserDB();
        ArrayList<User> users = udb.getUsers();
        String url="/home";
        if(request.getServletPath().equals("/register")){
            if(request.getParameter("name") == null){
                url="/register.jsp";

            } else{

              User user = new User(request.getParameter("name"), request.getParameter("email"),request.getParameter("pass"));
                udb.addUser(user);
                session.setAttribute("user", user);
                redirect = true;
                url = "/home";
            }
        }
        if (redirect) response.sendRedirect(request.getContextPath() + url);
        else getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
