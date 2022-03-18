package thanhha.controller.order;

import thanhha.cart.CartObject;
import thanhha.constant.ResourceUrl;
import thanhha.orderDetail.OrderDetailDAO;
import thanhha.product.ProductDAO;
import thanhha.product.ProductDTO;
import thanhha.product.ProductUpdateError;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import thanhha.account.AccountDTO;

import static thanhha.constant.ErrorMessage.PRODUCT.IS_QUANTITY_ERROR;
import static thanhha.constant.ErrorMessage.PRODUCT.QUANTITY_IS_INVALID;
import static thanhha.constant.ResourceUrl.PathName.BILL_PAGE;
import static thanhha.constant.ResourceUrl.PathName.CART_PAGE;

@WebServlet(name = "CreateOrderDetailServlet", value = "/CreateOrderDetailServlet")
public class CreateOrderDetailServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateOrderDetailServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ProductDAO productDAO = new ProductDAO();

        String customerId = null;
        String url = CART_PAGE;
        
        try {
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                AccountDTO validUser = (AccountDTO) session.getAttribute("USER");
                
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        BigDecimal total = BigDecimal.valueOf(0);
                        for (Map.Entry<String, Integer> item : items.entrySet()) {
                            ProductDTO product = productDAO.getProductById(item.getKey());
                            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.getValue())));
                        }
                        
                        if (validUser != null) {
                            customerId = validUser.getUsername();
                        }
                        boolean result = orderDetailDAO.createOrderDetail((HashMap<String, Integer>) items, total, customerId);

                        if (result) {
                            IS_QUANTITY_ERROR = false;

                            session.setAttribute("TOTAL_BILL", total);

                            response.sendRedirect(BILL_PAGE);
                        } else {
                            if (IS_QUANTITY_ERROR) {
                                LOGGER.warn(QUANTITY_IS_INVALID);
                                
                                log("CreateOrderDetailServlet_SQLException(Constraint CHECK CHK_in_stock is conflicted): " + QUANTITY_IS_INVALID);
                                ProductUpdateError error = new ProductUpdateError();

                                error.setQuantityIsInvalid(QUANTITY_IS_INVALID);
                                request.setAttribute("ERROR_MESSAGE", error);

                                RequestDispatcher dispatcher = request.getRequestDispatcher(ResourceUrl.PathValue.CART_PAGE);
                                dispatcher.forward(request, response);
                            } else {
                                response.sendRedirect(url);
                            }
                        }
                    }
                }
            }
        } catch (NamingException | SQLException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
