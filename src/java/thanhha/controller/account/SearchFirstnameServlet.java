package thanhha.controller.account;

import thanhha.account.AccountDAO;
import thanhha.account.AccountDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

import static thanhha.constant.ResourceUrl.PathValue.SEARCH_PAGE_RESULT;

@WebServlet(name = "SearchFirstnameServlet", value = "/SearchFirstnameServlet")
public class SearchFirstnameServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchFirstnameServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            AccountDTO currentUser = (AccountDTO) session.getAttribute("USER");

            String searchValue = request.getParameter("txtSearchValue");
            String url = SEARCH_PAGE_RESULT;

            AccountDAO accountDAO = new AccountDAO();
            List<AccountDTO> accountList = accountDAO.getAccountByFirstname(searchValue);

            if (accountList != null) {
                for (AccountDTO account : accountList) {
                    if (currentUser.getUsername().equals(account.getUsername())) {
                        accountList.remove(account);
                        break;
                    }
                }
            }

            request.setAttribute("SEARCH_RESULT", accountList);

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request,response);
        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
