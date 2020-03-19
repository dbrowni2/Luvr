import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Welcome")
public class Welcome extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 String url = "/index.jsp";
      HttpSession session = request.getSession();
      if (request.getServletPath().equals("/test")) {
          url = "/test.jsp";
      }
      getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
