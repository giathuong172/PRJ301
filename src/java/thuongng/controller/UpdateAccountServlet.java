/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thuongng.ErrorCLasses.AccountUpdateError;
import thuongng.registration.RegistrationDAO;
import thuongng.utils.MyApplicationConstants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    /*  ServletContext context = this.getServletContext();
    Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
    private final String SEARCH_CONTROLLER = siteMap.getProperty(MyApplicationConstants.SearchFeatures.SEARCH_PAGE);
     */
    

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
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
        String ERROR_PAGE =  siteMap.getProperty(MyApplicationConstants.ErrorFeatures.ERROR_PAGE);
        String url = siteMap.getProperty(MyApplicationConstants.SearchFeatures.SEARCH_LASTNAME_CONTROLLER);
        response.setContentType("text/html;charset=UTF-8");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        Boolean isAdmin = false;
        String username = request.getParameter("txtUsername");
        String searchValue = request.getParameter("lastSearchValue");
        String fullName = request.getParameter("txtLastName");
        String urlRewriting = ERROR_PAGE;
        AccountUpdateError errors = new AccountUpdateError();
        boolean foundErr = false;
        try {
            if (password.trim().length() == 0) {
                foundErr = true;
                errors.setPasswordIsEmptyError("Password required input");
            }
            if (fullName.trim().length() == 0) {
                foundErr = true;
                errors.setFullnameIsEmptyError("Fullname required input ");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR_UPDATE",errors);
                request.setAttribute("ERROR_PK",username);
                 urlRewriting = url
                            + "?btAction=Search"
                            + "&txtSearchValue=" + searchValue;
                 System.out.println(errors.getFullnameIsEmptyError());
            }
            if (!foundErr) {
                request.setAttribute("PK",username);
                RegistrationDAO dao = new RegistrationDAO();
                if (admin != null) {
                    isAdmin = true;
                }
                boolean result = dao.updateAccount(password, isAdmin, username, fullName);
                if (result) {
                    urlRewriting = url
                            + "?btAction=Search"
                            + "&txtSearchValue=" + searchValue;
                }
            } 
        } catch (SQLException ex) {
            log("UpdateAccountServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
            rd.forward(request, response);
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
