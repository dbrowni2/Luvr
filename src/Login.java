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
@WebServlet(name = "Login",
        description ="login controller",
        urlPatterns = {"/login", "/logout"}
)
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String encode = null;

        // Get the session
        HttpSession session = request.getSession(true);

        // Set default url and initialize redirect (as opposed to forward) to false
        String url = "/login.jsp";
        boolean redirect = false;
        String pass = request.getParameter("pass");
        // The login function is mapped to "/login"
        if (request.getServletPath().equals("/login")) {
            session = request.getSession(true);
            if (session.getAttribute("user") != null) {
                // Redirect so the url does not show "/login"
                redirect = true;
            } else {
                for (User user : UserDB.getUsers()) {
                    if (user.getuEmail().equals(request.getParameter("email")) && BCrypt.checkpw(request.getParameter("pass"),user.getuPass())){
                        session.setAttribute("user", user);
                        encode = response.encodeURL(request.getContextPath());
                        response.sendRedirect(encode + "/Home?action=userdates");
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}


