package filters;

import database.UserDB;
import beans.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "LoginFilter",
        description = "login filter",
        urlPatterns = {"/login"})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest)req;
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String error;
        User filterUser = null;
        HttpSession session = request.getSession(true);

        // find user
        User user = UserDB.getUserByEmail(email);
        assert user != null;
        if(BCrypt.checkpw(pass, user.getuPass()))
            filterUser = user;

        // check if user exists
        if(filterUser == null) {
            response.sendRedirect(request.getContextPath() + "/Home?action=login");
            error = "Invalid username or password. Please try again.";
            session.setAttribute("error", error);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        // TODO: implement LoginFilter.init()
    }

    public void destroy() {
        // TODO: implement LoginFilter.destroy()
    }
}
