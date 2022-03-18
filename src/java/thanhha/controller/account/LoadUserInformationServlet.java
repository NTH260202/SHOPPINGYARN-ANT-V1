/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thanhha.controller.account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thanhha.account.AccountDTO;
import thanhha.cart.CartDAO;
import thanhha.cart.CartObject;
import static thanhha.constant.ResourceUrl.PathName.PRODUCT_PAGE;
import thanhha.product.ProductDAO;
import thanhha.product.ProductDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoadUserInformationServlet", urlPatterns = {"/LoadUserInformationServlet"})
public class LoadUserInformationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoadUserInformationServlet.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            AccountDTO validUser = (AccountDTO) session.getAttribute("USER");
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }

            CartDAO cartDao = new CartDAO();
            Map<String, Integer> items = cartDao.getItemsInCart(validUser.getUsername());
            
            cart.setItems(items);
            
            
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> productList = productDAO.getAllProducts(true);

            session.setAttribute("CART", cart);
            session.setAttribute("PRODUCT_LIST", productList);
        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect(PRODUCT_PAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
