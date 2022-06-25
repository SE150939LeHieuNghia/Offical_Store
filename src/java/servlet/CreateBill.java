/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dto.BillDTO;
import dto.StaffDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CreateBill extends HttpServlet {
    private final String CHECK_WRONG = "wrong.html";
    private final String CHECK_CORRECT = "correct.html";
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
        HttpSession session = request.getSession();
        String url = CHECK_WRONG;
        try {
            String item = request.getParameter("itemID");
            int itemID = Integer.parseInt(item);
            
            String pawnMoneys = request.getParameter("txtPawnMoney");
            int pawnMoney = Integer.parseInt(pawnMoneys);
            
            String payMoneyDate = request.getParameter("txtPayDate");
            int numberDays = Integer.parseInt(payMoneyDate);
            
            String interest = request.getParameter("interestDay");
            float interestRate = Float.parseFloat(interest);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            String beginDate = request.getParameter("txtSendingDate");
            java.sql.Date sqlDate = java.sql.Date.valueOf(beginDate);
            
            StaffDTO staffID = (StaffDTO) session.getAttribute("STAFF");
            
            String payMoney = request.getParameter("txtPayMoney");
            int returnMoney = Integer.parseInt(payMoney);
            
            BillDAO bdao = new BillDAO();
            boolean check = bdao.insertBill(itemID , pawnMoney, numberDays, interestRate, sqlDate, staffID , returnMoney);
            System.out.println(check);
            if(check){
                url = CHECK_CORRECT;
            }
      
        } catch (Exception e) {
            e.printStackTrace();
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
