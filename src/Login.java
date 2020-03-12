import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;
@WebServlet(name = "Login")
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/login.jsp";
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        if (request.getServletPath().equals("/signup")) {
            url = "/signup.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String url = "/login.jsp";
        HttpSession session = request.getSession();
        if (request.getServletPath().equals("/signup")) {
            url = "/signup.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}

