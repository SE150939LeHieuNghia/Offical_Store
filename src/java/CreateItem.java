/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.ItemDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class CreateItem extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CreateItem() {
        super();
    }
    private final String CREATE_ITEM = "INSERT INTO tblItem (itemName,itemPic,customerID,storeID,itemSendingDate,itemGettingDate,statusID,isKeep) values (?, ?, ?, ?, ?, ?, ?, 1, 0);";
    Connection conn = null;
    PreparedStatement stm = null;
    private static final String SUCCESS = "createbill.jsp";
    private static final String ERROR = "createitem.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
//        processRequest(request, response);
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
//        processRequest(request, response);
        String url = ERROR;
        try (PrintWriter out = response.getWriter()) {
            String itemID = request.getParameter("itemID");
            String itemName = request.getParameter("itemName");
            String fullName = request.getParameter("fullName");

            Part file = request.getPart("itemPic");
            String imageFileName = file.getSubmittedFileName();
            String uploadPath = "C:/Users/Admin/Pictures/" + imageFileName;
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            String storeID = request.getParameter("storeID");
            String stritemSendingDate = request.getParameter("itemSendingDate");
            Date itemSendingDate = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(stritemSendingDate);
            String stritemGettingDate = request.getParameter("itemGettingDate");
            Date itemGettingDate = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(stritemGettingDate);
            int statusID = Integer.parseInt(request.getParameter("statusID"));
            int isKeep = Integer.parseInt(request.getParameter("isKeep"));

            // get selected image file name
            // upload path where we have to upload our actual image
            try {

                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn = DBConnect.makeConnection();
                if (conn != null) {
                    stm = conn.prepareStatement(CREATE_ITEM);
//                    stm.setString(1, itemID);
                    stm.setString(2, itemName);
                    stm.setString(3, imageFileName);
                    stm.setInt(4, customerID);
                    stm.setString(5, storeID);
                    stm.setDate(6, itemSendingDate);
                    stm.setDate(7, itemGettingDate);
                    stm.setInt(8, statusID);
                    stm.setInt(8, isKeep);
                    stm.executeUpdate();
                }
            } finally {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at create item " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
