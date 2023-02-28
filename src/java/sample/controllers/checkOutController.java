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

import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.product.Cart;
import sample.product.ProductDTO;
import sample.user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class checkOutController extends HttpServlet {

    private static final String HOME = "login.jsp";
    private static final String SUCCESS = "sendMail.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = HOME;
            try {
            HttpSession session = request.getSession();
            UserDTO account = (UserDTO)session.getAttribute("LOGIN_USER");
            OrderDAO orderDAO = new OrderDAO();
            Cart cart = (Cart) session.getAttribute("CART");// edit later
            double total = 0;
            for (ProductDTO p : cart.getCart().values()) {
                        total += p.getPrice() * p.getQuantity();
            }
            if(!cart.getCart().isEmpty()) {
                for (ProductDTO c : cart.getCart().values()) {
                
                OrderDTO order = new OrderDTO();
                order.setFoodId(c.getId());
                order.setUserId(account.getUserID());
                order.setQuantity(c.getQuantity());
                order.setPrice(c.getPrice());
                order.setMoney(total);
                orderDAO.addOrder(order);
                }
                cart.getCart().clear();
                url = SUCCESS;
            }
            } catch (Exception e) {
                log("Error at AddController: " + e.toString());
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
