import beans.User;
import database.UserDB;
import filters.BCrypt;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(
        name = "Register",
        description = "registration controller",
        urlPatterns = {"/Register"}
)
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException,
            NoSuchAlgorithmException {
        // AUTHENTICATION HANDLED IN FILTER
        HttpSession session = request.getSession(true);
        String name  = request.getParameter("name");
        String email = request.getParameter("email");
        String pass  = request.getParameter("pass");
        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());

        User user = new User(name, email, hashed);
        UserDB.addUser(user);
        session.setAttribute("user", user);
        String encode = response.encodeURL(request.getContextPath());
        response.sendRedirect(encode + "/Home?action=home");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
