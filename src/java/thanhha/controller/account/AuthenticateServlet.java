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
        AccountDTO validUser = null;
        //get request parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {
            AccountDAO dao = new AccountDAO();
            validUser = dao.getAccountByUsernameAndPassword(
                            username, password);
            if (validUser != null) {
                if (validUser.isAdmin()) {
                    url = ADMIN_HOMEPAGE;
                } else {
                    url = USER_HOMEPAGE;
                };   
            }
            
            if (username.isEmpty() && password.isEmpty()) {
                url = LOGIN_PAGE;
                //create new session       
                }//end if validAccount is not null
            HttpSession session = request.getSession(true);
            session.setAttribute("USER", validUser);
//            String hashedPassword = hashString(password);
        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String url = INVALID_ACCOUNT_PAGE;
        AccountDTO validUser = null;
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            validUser = (AccountDTO)session.getAttribute("USER");
        }

        try {
            if (validUser != null) {
                System.out.println(validUser.isAdmin());
                if (validUser.isAdmin()) {
                    System.out.println("is Admin" + validUser.isAdmin());
                    url = ADMIN_HOMEPAGE;
                } else {
                    url = USER_HOMEPAGE;
                }; 
            }
                //create new session       
                //end if validAccount is not null
            session.setAttribute("USER", validUser);
//            String hashedPassword = hashString(password);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }
}
