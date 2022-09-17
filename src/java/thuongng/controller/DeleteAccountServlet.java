/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thuongng.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thuongng.registration.RegistrationDAO;
import thuongng.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "errorPage";

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
        String id = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String isAdmin = request.getParameter("role");
        HttpSession session = request.getSession(false);
        String currentUserName = (String) session.getAttribute("USERNAME");
        String urlRewriting = ERROR_PAGE;
        try {
            //1. call DAO
            if (isAdmin.equals("false")) {
                if (currentUserName.equals(id)) {
                    urlRewriting = "logout"
                            + "?btAction=Logout";
                } else {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.deleteAccount(id);
                    //2. process result     
                    if (result) {
                        urlRewriting = "searchLastname"
                                + "?btAction=Search"
                                + "&txtSearchValue=" + searchValue;
                    }
                }
            } else{
                String deleteError = "You can not delete a admin account";
                session.setAttribute("deleteError", deleteError);
                urlRewriting = "searchLastname"
                                + "?btAction=Search"
                                + "&txtSearchValue=" + searchValue;
            }
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming" + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
