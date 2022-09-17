/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thuongng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thuongng.registration.RegistrationDAO;
import thuongng.registration.RegistrationDTO;
import thuongng.utils.MyApplicationConstants;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");

        //get 3 parameter;
        //String button = request.getParameter("btAaction");
        String url = siteMap.getProperty(MyApplicationConstants.LoginFeatures.INVALID_PAGE);
        String action = request.getParameter("btnAction");
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            // call Method;
            //New DAO object , then call method in DAO Object;
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);
            // process result
            if (result != null && action == null) {
                RegistrationDTO dto = new RegistrationDTO(username, password, result.getlastName(), result.isRole());
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                session.setAttribute("FULLNAME", result.getlastName());
                session.setAttribute("PASSWORD", password);
                session.setAttribute("ROLE", result.isRole());
                session.setAttribute("SESSION_USER", dto);
                url = siteMap.getProperty(MyApplicationConstants.SearchFeatures.SEARCH_PAGE);
            } else if (result != null && action.equals("Continue")) {
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                session.setAttribute("FULLNAME", result.getlastName());
                session.setAttribute("PASSWORD", password);
                session.setAttribute("ROLE", result.isRole());
                url = siteMap.getProperty(MyApplicationConstants.CartFeatures.SHOW_CART_PAGE);
            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming" + ex.getMessage());
        } finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        //get 03 parameters
//        String username = request.getParameter("txtUsername");
//        String password = request.getParameter("txtPassword");
//        String button = request.getParameter("btAction");
//        String url = INVALID_PAGE;
//
//        try {
//            if (button.equals("Login")) {
//    //call DAO -> new DAO Object & call method of DAO
//            RegistrationDAO dao = new RegistrationDAO();
//            boolean result = dao.checkLogin(username, password);
//
//            if (result) {
//            url = SEARCH_PAGE;
//            }
//
//            }
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } finally {
//            //response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
//        }
//    }

//            out.print("Username " + username + " - " + password + " - " + button);
//            System.out.println("Username " + username + " - " + password + " - " + button);
//        Connection con = DBHelpers.makeConnection();
//        if (con != null)
//            out.print("Have a nice weekend!!");
//        } else {
//            out.print("Good night!");
//        }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//           out.close();
//        }
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
