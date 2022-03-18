package thanhha.controller.account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static thanhha.constant.ResourceUrl.PathValue.LOGIN_PAGE;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        dispatcher.forward(request, response);
    }
}
