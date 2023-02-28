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
import java.io.IOException;
import java.io.PrintWriter;

import sample.recaptcha.VerifyRecaptcha;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";
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
            boolean checkValidation = true;
            boolean valid = false;
            try {
                //take parameters out
                String userID = request.getParameter("userID");
                String fullName = request.getParameter("fullName");
                String roleID = request.getParameter("roleID");
                String password = request.getParameter("password");
                String confirm = request.getParameter("confirm");
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                valid = VerifyRecaptcha.verify(gRecaptchaResponse);
                UserDAO dao = new UserDAO();
                
                if(valid == false){
                request.setAttribute("MESSAGE", "Please complete the reCAPTCHA");
                }
//                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (userID.length() < 2 || userID.length() > 5) {
                    request.setAttribute("MESSAGE", "User ID in [2,5]");
                    checkValidation = false;
                }
//                if (checkDuplicate) {
//                    error.setUserID("Duplicate UserID roi kia!");
//                    checkValidation = false;
//                }
                if (fullName.length() < 5 || fullName.length() > 20) {
                    request.setAttribute("MESSAGE", "Full name in [5,20]");
                    checkValidation = false;
                }
                if (!confirm.equals(password)) {
                    request.setAttribute("MESSAGE", "Hai password khong giong nhau kia!");
                    checkValidation = false;
                }
                if (checkValidation == true && valid == true) {
                    UserDTO user = new UserDTO(userID, fullName, roleID, password);
//                    boolean checkCreate = dao.insert(user);
                    boolean checkCreate = dao.insertV2(user);
                    if (checkCreate) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("MESSAGE", "error !");
                    }
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (valid == true && e.toString().contains("duplicate")) {
                    request.setAttribute("MESSAGE", "Trung id roi");
                }
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
