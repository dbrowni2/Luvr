import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import beans.User;
import database.UserDB;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;
@WebServlet(name = "Login",
        description ="login controller",
        urlPatterns = {"/Login"}
)
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        // Get the session
        HttpSession session = request.getSession();


        // Set default url and initialize redirect (as opposed to forward) to false
        String url = "/login.jsp";
        boolean redirect = false;

        // The login function is mapped to "/login"
        if (request.getServletPath().equals("/login")) {
            session = request.getSession(true);
            if(session.getAttribute("user") != null){
                //  If the user is signing in from a specific item page, redirect there


// If the user is signing in from the swaps page, redirect there


                // Redirect so the url does not show "/login"
                redirect = true;
            } else{
                if(request.getParameter("email") == null || request.getParameter("pass") == null){
                    url= "/login.jsp";
                }
                else{
                    for(User user: UserDB.getUsers()){
                        if(user.getuEmail().equals(request.getParameter("email")) && user.getuPass().equals(request.getParameter("pass"))){
                            session.setAttribute("user", user);
                            url ="/home";
                            redirect = true;

                        }
                    }

                }

            }


        }

        // The logout function is mapped to "/logout"
       if (request.getServletPath().equals("/logout") && session != null) {
            url = "/index.jsp";
            session.invalidate();
        }

        // The index page is mapped to "/home"

        if (request.getServletPath().equals("/home") || request.getServletPath().equals("") ) {
            url = "/index.jsp";

        }


        // Forward the request
        if (redirect) {
            response.sendRedirect(request.getContextPath() + url);
        }
        else{
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            System.out.println(e);
        }

   /**     String url = "/login.jsp";
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        if (request.getServletPath().equals("/signup")) {
            url = "/register.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    **/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            processRequest(request, response);
        } catch (SQLException e){
            System.out.println(e);
        }
        /**     String url = "/login.jsp";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        if (request.getServletPath().equals("/login.do")) {
            if(request.getParameter("username") == null || request.getParameter("password") == null){
                url= "/index.jsp";
            } else{
                for(User user: UserDB.getUsers()){
                    if(user.getuEmail().equals(request.getParameter("Lemail")) && user.getuPass().equals(request.getParameter("Lpass"))){
                        session.setAttribute("user", user);
                        url= "/index.jsp";
                    }
                }

            }

        }



        if (request.getServletPath().equals("/signup")) {
            url = "/register.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    **/
    }

}

