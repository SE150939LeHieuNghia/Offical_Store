/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.KeyDAO;
import dao.PawnShopDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StaffDAO;
import tool.Tool;
/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

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
        String url = "login.jsp";
        String action = request.getParameter("action");
        try {
            if (action == "Register") {
                boolean error = false;
                StaffDAO daoStaff = new StaffDAO();
                PawnShopDAO daoShop = new PawnShopDAO();
                KeyDAO daoKey = new KeyDAO();
                String managerID = request.getParameter("userID");
                if (!Tool.checkInt(managerID, 12, true).equals("")) {
                    request.setAttribute("ID_MESS", "CMND " + Tool.checkInt(managerID, 12, true));
                    error = true;
                }
                if (daoStaff.viewStaff(managerID) != null) {
                    request.setAttribute("ID_MESS", "CMND đã được sử dụng");
                    error = true;
                }
                String password = request.getParameter("password");
                if (!Tool.checkString(password, 4, false).equals("")) {
                    request.setAttribute("PASS_MESS", "Mật khẩu " + Tool.checkString(password, 4, false));
                    error = true;
                }
                String fullName = request.getParameter("fullName");
                if (!Tool.checkString(fullName, 1, false).equals("")) {
                    request.setAttribute("FULLNAME_MESS", "Họ Tên " + Tool.checkString(fullName, 1, false));
                    error = true;
                }
                String confirmPassword = request.getParameter("confirmPassword");
                if (password.compareTo(confirmPassword) != 0) {
                    request.setAttribute("CONFIRMPASS_MESS", "2 mật khẩu khác nhau");
                    error = true;
                }
                String storeName = request.getParameter("storeName");
                if (!Tool.checkString(storeName, 1, false).equals("")) {
                    request.setAttribute("STORENAME_MESS", "Tên cửa hàng " + Tool.checkString(storeName, 1, false));
                    error = true;
                }
                String address = request.getParameter("address");
                if (!Tool.checkString(address, 10, false).equals("")) {
                    request.setAttribute("ADDRESS_MESS", "Địa chỉ cửa hàng " + Tool.checkString(address, 10, false));
                    error = true;
                }
                String phoneNumber = request.getParameter("phoneNumber");
                int phone=Integer.parseInt(phoneNumber); 
                if (!Tool.checkInt(phoneNumber, 10, true).equals("")) {
                    request.setAttribute("PHONE_MESS", "Số điện thoại " + Tool.checkInt(phoneNumber, 10, true));
                    error = true;
                }
                String confirmKey = request.getParameter("confirmKey");
                if (!Tool.checkString(confirmKey, 8, true).equals("")) {
                    request.setAttribute("KEY_MESS", "Mã mở tài khoản " + Tool.checkString(confirmKey, 8, true));
                    error = true;
                }
                if (!error) {
                    daoStaff.createStaff(managerID, password, fullName, "0");
                   daoShop.createPawnShop(storeName, address, phone, managerID, confirmKey);
//                   daoStaff.viewStaff(managerID).setStoreID(daoShop.);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
