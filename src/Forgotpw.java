import beans.User;
import database.UserDB;

import com.sendgrid.*;
import filters.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "Forgotpw",
    description = "forgot password",
    urlPatterns = {"/forgotpw"}
    )
public class Forgotpw extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation, error;
        String email = null;
        String pass = null;
        int ID = 0;
        boolean found = false;
        HttpSession session = request.getSession(true);
        for (User user : UserDB.getUsers()) {
            if (user.getuEmail().equals(request.getParameter("email"))) {
                email = new String(user.getuEmail());
                pass = new String(user.getuPass());
                ID = user.getID();
                found = true;
                break;
            }
        }
        if(found) {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            String hashed = BCrypt.hashpw(generatedString, BCrypt.gensalt());
            UserDB.updateUPass(hashed,ID);
            Email from = new Email("luvr49er@protonmail.com");
            String subject = "Luvr Password Reset";
            Email to = new Email(email);
            Content content = new Content("text/plain", "Here is your password new: " + generatedString);
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
