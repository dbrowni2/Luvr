import beans.User;
import database.DatesDB;
import database.RecommendationsDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//.import  com.mysql.cj.xdevapi.JsonParser;


@WebServlet(name = "Dates",
        description ="dates controller",
        urlPatterns = {"/dates", "/user_dates"}
)
public class Dates extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        String encode = null;
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
                encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=dates");

            }
        }

        if (request.getServletPath().equals("/userdates") || request.getServletPath().equals("/user_dates")) {
            url = "/user_dates.jsp";
            session = request.getSession(true);
            User user = (User) session.getAttribute("user");

            //Set the url and open the connection
            String zip = request.getParameter("zip");
            ArrayList<String> tags = RecommendationsDB.getUserTags(user);


            if (zip != null && tags.size() > 0) {
                String rad = "10000";
                zip = "28223";

                System.out.println("getting recDates");

                session.setAttribute("recDates", RecommendationsDB.getDates(zip, rad, tags));
                encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=userdates");

            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }
}
