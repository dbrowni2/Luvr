import database.DatesDB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet(
        name = "Dates",
        description ="dates controller",
        urlPatterns = {"/dates"}
)
public class Dates extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        String url = "/home";

        if (request.getServletPath().equals("/dates")) {
            url = "/dates.jsp";
            session = request.getSession(true);

            //Set the url and open the connection
            String zip = request.getParameter("zip");
            if (zip != null) {
                String rad = request.getParameter("optradio");

                // actual date getting handled in DatesDB
                session.setAttribute("dates", DatesDB.getDates(zip, rad));
                String encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=dates");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
