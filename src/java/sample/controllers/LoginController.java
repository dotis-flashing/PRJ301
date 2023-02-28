/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import sample.recaptcha.VerifyRecaptcha;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {
    private static final String ERROR = "login.jsp";
    private static final String SEARCH_PAGE = "search.jsp";
    private static final String USER_PAGE = "ShoppingController";
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            try {
                String userID = request.getParameter("userID");
                String password = request.getParameter("password");
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                boolean valid = false;
                valid = VerifyRecaptcha.verify(gRecaptchaResponse);
                UserDAO dao = new UserDAO();
                UserDTO loginUser = dao.checkLogin(userID, password);
                //Xac thuc o day ne
                if (loginUser != null && valid == true) {
                    //Phan quyen o day ne
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", loginUser);
                    String roleID = loginUser.getRoleID();
                    if (ADMIN.equals(roleID)) {
                        url = SEARCH_PAGE;
                    } else if (USER.equals(roleID)) {
                        url = USER_PAGE;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported!");
                    }

                } else if(loginUser != null && valid == false){
                    request.setAttribute("ERROR", "Please complete the reCAPTCHA");
                } else
                    request.setAttribute("ERROR", "Incorrect userID or password!");         
            } catch (Exception e) {
                log("error at LoginController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
