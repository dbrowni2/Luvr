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
import java.util.ArrayList;

@WebServlet(name = "Home",
        description = "home controller",
        urlPatterns = {"/Home"}
)
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        // forward to controller url instead of jsp via action
        switch(action) {
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "home":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "dates":
                request.getRequestDispatcher("dates.jsp").forward(request, response);
                break;
            case "userdates":
                User user = (User) session.getAttribute("user");
                String uEmail = user.getuEmail();
                // actual date getting handled in DatesDB
                ArrayList<UserDates> dates = UserDatesDB.getUserDates(uEmail);
                session.setAttribute("user_dates", dates);
                session = request.getSession(true);
                String zip = request.getParameter("zip");
                //zip = "28075";
                ArrayList<String> tags = RecommendationsDB.getUserTags(user);
                session.setAttribute("tags", tags);
                if (zip != null && tags.size() > 0) {
                    String rad = "16093";
                    //zip = "28223";

                    System.out.println("getting recDates");

                    session.setAttribute("recDates", RecommendationsDB.getDates(zip, rad, tags));
                }
                    request.getRequestDispatcher("user_dates.jsp").forward(request, response);

                break;
                    case "add_date":
                String id = request.getParameter("date");
                String name = UserDatesDB.getDetails(id);

                request.setAttribute("name",name);
                request.setAttribute("id",id);

                request.getRequestDispatcher("date_add.jsp").forward(request, response);

                break;
            case "logout":
                session.invalidate();
               String encode = response.encodeURL(request.getContextPath());
                response.sendRedirect(encode + "/Home?action=home");
                break;
            case "forgotpw":
                request.getRequestDispatcher("forgotpw.jsp").forward(request, response);
                break;
            case "invite":
                id = request.getParameter("date");
                name = UserDatesDB.getDetails(id);

                request.setAttribute("name", name);
                request.setAttribute("id", id);
                request.getRequestDispatcher("invite.jsp").forward(request, response);
                break;

            default:
                break;
        }
    }
}
