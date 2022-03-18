package thanhha.controller.account;

import thanhha.account.AccountDAO;
import thanhha.account.AccountDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import static thanhha.constant.ResourceUrl.PathName.*;
import static thanhha.util.ParsingUtils.hashString;

@WebServlet(name = "AuthenticateServlet", value = "/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AuthenticateServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String url = INVALID_ACCOUNT_PAGE;
        //get request parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {
            String hashedPassword = hashString(password);

            AccountDAO dao = new AccountDAO();
            AccountDTO validUser =
                    dao.getAccountByUsernameAndPassword(
                            username, hashedPassword);
            if (validUser != null) {
                if (validUser.isAdmin()) {
                    url = SEARCH_PAGE;
                } else {
                    url = LOAD_USER_INFOR;
                };
                //create new session
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", validUser);
            }//end if validAccount is not null
        } catch (SQLException | NamingException | NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }
}
