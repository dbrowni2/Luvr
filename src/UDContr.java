import beans.User;
import beans.UserDates;
import database.RecommendationsDB;
import database.UserDatesDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


@WebServlet(name = "UDContr", description = "user dates controller", urlPatterns = {"/userdates", "/add_date", "/user_dates"})

public class UDContr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        String encode = null;
        String url = "/home";
        if(session.getAttribute("user") != null) {
            if (request.getServletPath().equals("/userdates") || request.getServletPath().equals("/user_dates")) {
                url = "/user_dates.jsp";
                session = request.getSession(true);

                User user = (User) session.getAttribute("user");
                session.setAttribute("zip", request.getParameter("zip"));

                String zip = request.getParameter("zip");
                if (zip != null) {
                    session.setAttribute("zip", zip);
                    ArrayList<String> tags = RecommendationsDB.getUserTags(user);
                    session.setAttribute("tags", tags);
                    if (zip != null && tags.size() > 0) {
                        String rad = "16093";
                        //zip = "28223";

                        System.out.println("getting recDates");

                        session.setAttribute("recDates", RecommendationsDB.getDates(zip, rad, tags));
                    }
                }
                if (user != null) {

                    String uEmail = user.getuEmail();
                    // actual date getting handled in DatesDB
                    ArrayList<UserDates> dates = UserDatesDB.getUserDates(uEmail);
                    session.setAttribute("user_dates", dates);
                    encode = response.encodeURL(request.getContextPath());
                    response.sendRedirect(encode + "/Home?action=userdates");

                }
            }
            if (request.getServletPath().equals("/add_date")) {
                String id = request.getParameter("date");
                User user = (User) session.getAttribute("user");
                Date datev = Date.valueOf(request.getParameter("datevis"));
                UserDatesDB.addUserDate(Integer.parseInt(request.getParameter("rate")), user.getuEmail(), id, "", datev, UserDatesDB.getDetails(id));
                encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=userdates");

            }
            if (request.getServletPath().equals("/user_dates")) {
                session = request.getSession(true);
                User user = (User) session.getAttribute("user");
                String zip = request.getParameter("zip");

                ArrayList<String> tags = RecommendationsDB.getUserTags(user);
                session.setAttribute("tags", tags);
                if (zip == null) {
                    System.out.println("Zip Null");
                    zip = "28147";
                }
                if (zip != null && tags.size() > 0) {
                    String rad = "10000";
                    //zip = "28223";
                    System.out.println(zip);

                    System.out.println("getting recDates");

                    session.setAttribute("recDates", RecommendationsDB.getDates(zip, rad, tags));
                    encode = response.encodeURL(request.getContextPath());
                    response.sendRedirect(encode + "/Home?action=userdates");

                }
                /*if (user != null) {
                    //String id = user.getID();
                    ArrayList<String> tags = RecommendationsDB.getUserTags(user);
                    session.setAttribute("tags", tags);
                    encode = response.encodeURL(request.getContextPath());
                    response.sendRedirect(encode + "/Home?action=userdates");

                }*/
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

