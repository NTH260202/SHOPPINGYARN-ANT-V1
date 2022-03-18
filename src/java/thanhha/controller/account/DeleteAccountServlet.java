package thanhha.controller.account;

import thanhha.account.AccountDAO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import static thanhha.constant.ResourceUrl.PathName.ERROR_PAGE;

@WebServlet(name = "DeleteAccountServlet", value = "/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteAccountServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR_PAGE;

        String lastSearchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("deleteValue");
        try {
            AccountDAO dao = new AccountDAO();
            boolean result = dao.deleteAccountByUsername(username);
            if (result) {
                url = "searchAccount?txtSearchValue=" + lastSearchValue;
            }

        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }
}
