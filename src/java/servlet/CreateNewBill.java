/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dto.BillDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CreateNewBill extends HttpServlet {

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
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String number = request.getParameter("numberDay");
        String money = request.getParameter("money");
        int money1 = Integer.parseInt(money);
        int numberDay = Integer.parseInt(number);

//        String remain = request.getParameter("txtRemain");
//        int remain_int = Integer.parseInt(remain);
        int idd = Integer.parseInt(id);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat.parse(date);
//            System.out.println(remain_int);
            System.out.println(idd);
            BillDAO dao = new BillDAO();
            ArrayList<BillDTO> bdto = dao.getBillByBillID(idd);
            if (bdto != null) {
                request.setAttribute("newbill", bdto);
                request.setAttribute("money", money1);
//                request.setAttribute("remain", remain_int);
            }

            Calendar c = Calendar.getInstance();

            c.setTime(date1);
            c.add(Calendar.DATE, numberDay);
            date1 = c.getTime();
            String date2 = dateFormat.format(date1);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date2);
            request.setAttribute("date", sqlDate);
            System.out.println(sqlDate);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("updatebill.jsp").forward(request, response);
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
