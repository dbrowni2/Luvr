import beans.DateBean;
import beans.User;
import com.sendgrid.*;
import database.DatesDB;
import database.UserDB;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//.import  com.mysql.cj.xdevapi.JsonParser;


@WebServlet(name = "Dates",
        description ="dates controller",
        urlPatterns = {"/dates","/invite"}
)
public class Dates extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        boolean redirect = false;
        String encode = null;
        JSONObject root = null;
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
        if(request.getServletPath().equals("/invite")){

            URL uri = new URL("https://api.yelp.com/v3/businesses/" + request.getParameter("invDate"));
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");
            //Get the results and read them
            InputStream results = conn.getInputStream();
            JSONTokener tokener = new JSONTokener(results);
            //Create JSON object
            root = new JSONObject(tokener);
            String dName = root.getString("name");
            String dPhone = root.getString("phone");
            String dAddr = root.getJSONObject("location").getString("address1") + ", " + root.getJSONObject("location").getString("city") + ", " + root.getJSONObject("location").getString("state") + ", " + root.getJSONObject("location").getString("zip_code");
            Float rating = root.getFloat("rating");
            String confirmation, error;
            DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate when =  LocalDate.parse(request.getParameter("when"),f1);
            DateTimeFormatter f2 = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
            String whenS = when.format(f2);
            String email = request.getParameter("to");
           String message = "Hey there, "+ request.getParameter("who") + "! Someone wants to go out with you! Here are the details: \nWho: " + request.getParameter("from") + "\nPlace: " + dName + "\nPhone: " + dPhone + "\nAddress: " + dAddr + "\nWhen: " +whenS +
                   "\nHere is a custom message they wrote you:\n" + request.getParameter("cus") ;

            session = request.getSession(true);

                Email from = new Email("luvr49er@protonmail.com");
                String subject = "Luvr Invite!";
                Email to = new Email(email);
                Content content = new Content("text/plain", message);
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request req = new Request();
                try {
                    req.setMethod(Method.POST);
                    req.setEndpoint("mail/send");
                    req.setBody(mail.build());
                    Response resp = sg.api(req);
                    System.out.println(resp.getStatusCode());
                    System.out.println(resp.getBody());
                    System.out.println(resp.getHeaders());
                    confirmation = "Sent! Check with your person to see if they got it!.";
                    session.setAttribute("confirmation", confirmation);
                    response.sendRedirect(request.getContextPath() + "/Home?action=home");
                } catch (IOException ex) {
                    throw ex;
                }

            }



        /*if (request.getServletPath().equals("/userdates") || request.getServletPath().equals("/user_dates")) {
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
        }*/

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }
}
