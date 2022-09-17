/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thuongng.cart.CartObjectV2;
import thuongng.cart.CartObjectV2List;
import thuongng.orderdetail.OrderDetailDAO;
import thuongng.orders.OrdersDAO;
import thuongng.utils.MyApplicationConstants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMap.getProperty(MyApplicationConstants.CartFeatures.SHOW_CART_PAGE);

        try {
            HttpSession session = request.getSession(false);
            String userId = (String) session.getAttribute("USERNAME");
            if (session != null) {
                    CartObjectV2List cart = (CartObjectV2List) session.getAttribute("CARTLIST");
                if (cart != null) {
                    ArrayList<CartObjectV2> items = cart.getList();
                    if (items != null) {
                        OrdersDAO orderDAO = new OrdersDAO();
                        int orderID = orderDAO.addOrder(cart,userId);
                        OrderDetailDAO detailDAO = new OrderDetailDAO();
                        detailDAO.addOrderDetail(orderID, cart);
                        cart.DeleteAllBookFromCart();
                        session.removeAttribute("CARTLIST");
                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckOutServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
           log("CheckOutServlet _ Naming" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
