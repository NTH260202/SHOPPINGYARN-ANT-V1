package thanhha.controller.account;

import thanhha.account.AccountDAO;
import thanhha.account.AccountDTO;
import thanhha.account.AccountRegisterError;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import static thanhha.constant.ErrorMessage.ACCOUNT.*;
import static thanhha.constant.ResourceUrl.PathValue.LOGIN_PAGE;
import static thanhha.constant.ResourceUrl.PathValue.REGISTER_ERROR_PAGE;
import static thanhha.util.ParsingUtils.hashString;

@WebServlet(name = "CreateAccountServlet", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateAccountServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean foundError = false;
        String url = REGISTER_ERROR_PAGE;
        AccountRegisterError error = new AccountRegisterError();

        String username = request.getParameter("txtUsername");
        String password  = request.getParameter("txtPassword");
        String confirmedPassword = request.getParameter("txtConfirm");
        String firstname = request.getParameter("txtFirstname");
        String lastname = request.getParameter("txtLastname");

        if (username.trim().length() < 6 ||
                username.trim().length() > 40) {
            foundError = true;
            error.setUsernameLengthErr(USERNAME_LENGTH_ERROR);
            LOGGER.warn(USERNAME_LENGTH_ERROR);
        }
        if (password.trim().length() < 6 ||
                password.trim().length() > 30) {
            foundError = true;
            error.setPasswordLengthErr(PASSWORD_LENGTH_ERROR);
            LOGGER.warn(PASSWORD_LENGTH_ERROR);
        } else if (!confirmedPassword.trim().equals(password.trim())){
            foundError = true;
            error.setConfirmPasswordNotMatched(CONFIRMED_PASSWORD_FAILED);
            LOGGER.warn(CONFIRMED_PASSWORD_FAILED);
        }
        if (firstname.trim().length() < 2 ||
                firstname.trim().length() > 10) {
            foundError = true;
            error.setFirstnameLengthErr(FIRST_NAME_LENGTH_ERROR);
            LOGGER.warn(FIRST_NAME_LENGTH_ERROR);
        }
        if (lastname.trim().length() < 6 ||
                lastname.trim().length() > 40) {
            foundError = true;
            error.setLastnameLengthErr(LAST_NAME_LENGTH_ERROR);
            LOGGER.warn(LAST_NAME_LENGTH_ERROR);
        }

        try {
            if (foundError) {
                request.setAttribute("ERROR_MESSAGE", error);
            } else {
                String hashedPassword = hashString(password);
                AccountDAO accountDAO = new AccountDAO();
                AccountDTO account = new AccountDTO(username, hashedPassword, firstname, lastname);
                boolean result = accountDAO.createNewAccount(account);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException e) {
            log("CreateAccountServlet_SQLException: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                error.setUsernameIsExisted(USERNAME_EXISTED);
                request.setAttribute("ERROR_MESSAGE", error);
                LOGGER.warn(USERNAME_EXISTED);
            }
        } catch (NamingException | NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
}
