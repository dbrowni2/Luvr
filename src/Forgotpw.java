import beans.User;
import database.UserDB;

import com.sendgrid.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Forgotpw",
    description = "forgot password",
    urlPatterns = {"/forgotpw"}
    )
public class Forgotpw extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation, error;
        String email = null;
        String pass = null;
        boolean found = false;
        HttpSession session = request.getSession(true);
        for (User user : UserDB.getUsers()) {
            if (user.getuEmail().equals(request.getParameter("email"))) {
                email = new String(user.getuEmail());
                pass = new String(user.getuPass());
                found = true;
                break;
            }
        }
        if(found) {
            Email from = new Email("mhach@uncc.edu");
            String subject = "Luvr Password Reset";
            Email to = new Email(email);
            Content content = new Content("text/plain", "Here is your password: " + pass);
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
                confirmation = "Sent! Please check your email for your password.";
                session.setAttribute("confirmation", confirmation);
                response.sendRedirect(request.getContextPath() + "/Home?action=forgotpw");
            } catch (IOException ex) {
                throw ex;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/Home?action=forgotpw");
            error = "Email not found. Please try again.";
            session.setAttribute("error", error);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
