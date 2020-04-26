package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "RegisterFilter",
        description = "registration filter",
        urlPatterns = {"/Register"}
)
public class RegisterFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest)req;
        String email = request.getParameter("email");
        String confirme = request.getParameter("confirme");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");

        if(name == null || !email.equals(confirme) || !pass.equals(cpass)) {
            response.sendRedirect(request.getContextPath() + "/Home?action=register");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) {
        // TODO: implement RegisterFilter.init()
    }

    public void destroy() {
        // TODO: implement RegisterFilter.destroy()
    }
}
