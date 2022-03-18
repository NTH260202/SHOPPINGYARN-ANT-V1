package thanhha.controller.cart;

import thanhha.cart.CartObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import thanhha.account.AccountDTO;
import thanhha.cart.CartDAO;

import static thanhha.constant.ResourceUrl.PathName.CART_PAGE;

@WebServlet(name = "DeleteItemFromCartServlet", value = "/DeleteItemFromCartServlet")
public class DeleteItemFromCartServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteItemFromCartServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        CartDAO cartDAO = new CartDAO();
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                AccountDTO validUser = (AccountDTO) session.getAttribute("USER");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        String[] selectedItem = request.getParameterValues("removeItems");
                        if (selectedItem != null) {
                            for (String item : selectedItem) {
                                cart.removeItemFromCart(item);
                                cartDAO.deleteItemInCart(item, validUser.getUsername());
                            }
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }
            response.sendRedirect(CART_PAGE);
        } catch (IOException | NamingException | SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
