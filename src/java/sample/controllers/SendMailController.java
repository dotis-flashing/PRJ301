/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

import sample.product.Cart;
import sample.user.UserDTO;

/**
 *
 * @author huynh
 */
public class SendMailController extends HttpServlet {

    private static final String CART = "viewCart.jsp";
    private static final String SEND_MAIL = "sendMail.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        String url = CART;
        try {
            HttpSession session = request.getSession();
            UserDTO account = (UserDTO)session.getAttribute("LOGIN_USER");
            Cart cart = (Cart) session.getAttribute("CART");// edit later
            
            String emailTo = request.getParameter("email");

            final String senderMail = "xuanduy650@gmail.com";
            final String senderPassword = "03634237422002duy";
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            //prop.put("mail.mime.allowutf8", true);
            Session sessionMail = Session.getInstance(prop, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderMail, senderPassword);                   
                }
            });
            
            try {
                               
                MimeMessage message = new MimeMessage(sessionMail);
                
                message.setFrom(new InternetAddress(senderMail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));               
                message.setSubject("Thông tin đơn hàng", "UTF-8");
                message.setContent("<h3 style=\"color: blue\">Bạn đã thanh toán thành công.</h3>\n" +
                                "        <div>Tên người dùng: " + account.getFullName()+ "</div>\n" +
                                "        <h3 style=\"color: #c01508\">Cảm ơn bạn đã ủng hộ!</h3>"
                                , "text/html; charset=UTF-8");
                Transport.send(message);
                request.setAttribute("MESSAGE", "Gửi thành công, vui lòng truy cập mail để xem chi tiết");
                url = CART;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
            } catch (Exception e) {
            log("Error at SendMailController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
