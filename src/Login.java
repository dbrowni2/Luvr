import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/login.jsp";
        HttpSession session = request.getSession();
        if (request.getServletPath().equals("/signup")) {
            url = "/signup.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    }

