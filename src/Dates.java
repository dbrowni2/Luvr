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
import java.util.Set;

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
        String url = "/home";
        if (request.getServletPath().equals("/dates")) {
            url = "/dates.jsp";
            session = request.getSession(true);


            //Set the url and open the connection
            URL uri = new URL("https://api.yelp.com/v3/businesses/search?location=Charlotte,NC,USA&radius=5000");
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");
            //Get the results and read them
            InputStream results = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(results);
            String str;
            //Tokenize the JSON object from the input stream reader
            JSONTokener tokener = new JSONTokener(results);
            //Create JSON object
            JSONObject root = new JSONObject(tokener);
            //Extract the JSON array known as "businesses" from the object
            JSONArray stri = root.getJSONArray("businesses");
            //Get the first object from the array of objects
            JSONObject bus = stri.getJSONObject(1);
            //Create a string with the data from the object
            String price = "Name: "+ bus.getString("name") + "\nPrice: " + bus.getString("price");
            //Set the session attribute to be the string so it can be displayed.
            session.setAttribute("loc",price);
        }

        if (redirect) response.sendRedirect(request.getContextPath() + url);
        else getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            processRequest(request, response);

    }
}
