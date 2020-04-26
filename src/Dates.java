import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
//.import  com.mysql.cj.xdevapi.JsonParser;
import java.net.*;
import java.util.ArrayList;

import database.DatesDB;
import database.RecommendationsDB;
import org.json.*;



@WebServlet(name = "Dates",
        description ="dates controller",
        urlPatterns = {"/dates"}
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

        if (request.getServletPath().equals("/user_dates")) {
            url = "/user_dates.jsp";
            session = request.getSession(true);


            //Set the url and open the connection
            String zip = request.getParameter("zip");
            String tag = "";


            if (zip != null) {
                String rad = "10000";

                // actual date getting handled in DatesDB
                session.setAttribute("dates", RecommendationsDB.getDates(zip, rad, tag));
                encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=dates");

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
