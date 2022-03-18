package thanhha.controller.cart;

import thanhha.cart.CartObject;
import thanhha.product.ProductDAO;
import thanhha.product.ProductDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import thanhha.account.AccountDTO;
import thanhha.cart.CartDAO;
import thanhha.cart.CartDTO;

import static thanhha.constant.ResourceUrl.PathName.PRODUCT_PAGE;

@WebServlet(name = "AddItemToCartServlet", value = "/AddItemToCartServlet")
public class AddItemToCartServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddItemToCartServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }

            String id = request.getParameter("selectedItem");
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product = productDAO.getProductById(id);
            List<ProductDTO> productList = productDAO.getAllProducts(true);

            if (cart.getQuantityByItemId(id) < product.getInStock()) {
                cart.addItemToCart(product.getId());
            }
            
            AccountDTO validUser = (AccountDTO) session.getAttribute("USER");
            if (validUser != null) {
                CartDAO cartDAO = new CartDAO();
                if (cart.getQuantityByItemId(id) == 0) {
                    boolean result = cartDAO.insertItemToCart(id, validUser.getUsername());
                } else {
                    boolean result = cartDAO.updateItemInCart(id, validUser.getUsername());
                }
            }
            session.setAttribute("CART", cart);
            session.setAttribute("PRODUCT_LIST", productList);
        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect(PRODUCT_PAGE);
        }
    }

}
