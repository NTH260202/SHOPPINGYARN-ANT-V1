package thanhha.controller.product;

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

import static thanhha.constant.ResourceUrl.PathValue.VIEW_PRODUCT_CATALOG;

@WebServlet(name = "ViewProductServlet", value = "/ViewProductServlet")
public class ViewProductServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewProductServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = VIEW_PRODUCT_CATALOG;

            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> productList = productDAO.getAllProducts(true);
            request.setAttribute("VIEW_RESULT", productList);

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
