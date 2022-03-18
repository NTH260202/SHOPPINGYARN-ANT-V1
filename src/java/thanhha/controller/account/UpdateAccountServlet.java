package thanhha.controller.account;

import thanhha.account.AccountDAO;

import javax.naming.NamingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import static thanhha.constant.ResourceUrl.PathName.ERROR_PAGE;

@WebServlet(name = "UpdateAccountServlet", value = "/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateAccountServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = ERROR_PAGE;

            String lastSearchValue = request.getParameter("updateParam");
            String updateKey = request.getParameter("updatePK");
            boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

            AccountDAO accountDAO = new AccountDAO();
            boolean result = accountDAO.updateAccountByUsername(updateKey, isAdmin);
            if (result) {
                url = "searchAccount?txtSearchValue=" + lastSearchValue;
            }
            response.sendRedirect(url);
        } catch (SQLException | IOException | NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
