import beans.DateBean;
import beans.User;
import beans.UserDates;
import database.DatesDB;
import database.UserDatesDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


@WebServlet(name = "UDContr", description = "user dates controller", urlPatterns = {"/userdates", "/add_date"})

public class UDContr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        String encode = null;
        String url = "/home";
        if(session.getAttribute("user") != null) {
            if (request.getServletPath().equals("/userdates")) {
                url = "/user_dates.jsp";
                session = request.getSession(true);

                User user = (User)session.getAttribute("user");


                if (user != null) {

                    int id = user.getID();
                    // actual date getting handled in DatesDB
                    ArrayList<UserDates> dates = UserDatesDB.getUserDates(id);
                    session.setAttribute("user_dates", dates);
                    encode = response.encodeURL(request.getContextPath());
                    response.sendRedirect(encode + "/Home?action=userdates");

                }
            }
            if(request.getServletPath().equals("/add_date")){
                String id = request.getParameter("date");
               User user = (User)session.getAttribute("user");
                Date datev = Date.valueOf(request.getParameter("datevis"));
                UserDatesDB.addUserDate(Integer.parseInt(request.getParameter("rate")),user.getID(),id,"",datev,UserDatesDB.getDetails(id));
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

