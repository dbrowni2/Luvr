import beans.User;
import database.UserDB;
import filters.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Register",
        description = "registration controller",
        urlPatterns = {"/Register"}
)
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // AUTHENTICATION HANDLED IN FILTER
        String encode = null;
        HttpSession session = request.getSession(true);
        UserDB udb = new UserDB();
        ArrayList<User> users = udb.getUsers();
        String hashed = BCrypt.hashpw(request.getParameter("pass"), BCrypt.gensalt());

        User user = new User(request.getParameter("name"), request.getParameter("email"), hashed);
        udb.addUser(user);
        session.setAttribute("user", user);
        encode = response.encodeURL(request.getContextPath());
        response.sendRedirect(encode + "/Home?action=home");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
