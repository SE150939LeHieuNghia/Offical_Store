/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BillDTO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.StaffDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class BillDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public ArrayList<BillDTO> getAllBill(String staffID) throws SQLException {
        ArrayList<BillDTO> bill = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();

            String sql = "Select billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
                    + "From tblBill WHERE staffID = ?";

            stm = con.prepareStatement(sql);
            stm.setString(1, staffID);
            rs = stm.executeQuery();

            while (rs.next()) {
                int billID = rs.getInt("billID");
                int itemID = rs.getInt("itemID");
                int pawnMoney = rs.getInt("pawnMoney");
                int numberDays = rs.getInt("numberDays");
                float interestRate = rs.getFloat("interestRate");
                Date billBeginDate = rs.getDate("billBeginDate");
                StaffDAO std = new StaffDAO();
                int returnMoney = rs.getInt("returnMoney");
                
                BillDTO b = new BillDTO(billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID,returnMoney);
                bill.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return bill;
    }

//    public ArrayList<BillDTO> getBillByID(int id) {
//        ArrayList<BillDTO> list = new ArrayList<>();
//
//        try {
//            con = DBConnect.makeConnection();
//            String sql = "Select itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
//                    + "From tblBill "
//                    + "Where billID = ? ";
//
//            stm = con.prepareStatement(sql);
//            stm.setInt(1, id);
//
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                int itemID = rs.getInt("itemID");
//                int pawnMoney = rs.getInt("pawnMoney");
//                int numberDays = rs.getInt("numberDays");
//                float interestRate = rs.getFloat("interestRate");
//                Date billBeginDate = rs.getDate("billBeginDate");
//                String staffID = rs.getString("staffID");
//                int returnMoney = rs.getInt("returnMoney");
//
//                StaffDAO std = new StaffDAO();
//                BillDTO b = new BillDTO(id, itemID, pawnMoney, numberDays, interestRate, billBeginDate, std.viewStaff(staffID), returnMoney);
//                list.add(b);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    
    public ArrayList<BillDTO> getBillByIdItem(int id) {
        ArrayList<BillDTO> list = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            String sql = "Select billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
                    + "From tblBill "
                    + "Where itemID = ? ";

            stm = con.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();
            while (rs.next()) {
                int billID = rs.getInt("billID");
                int pawnMoney = rs.getInt("pawnMoney");
                int numberDays = rs.getInt("numberDays");
                float interestRate = rs.getFloat("interestRate");
                Date billBeginDate = rs.getDate("billBeginDate");
                String staffID = rs.getString("staffID");
                int returnMoney = rs.getInt("returnMoney");

                StaffDAO std = new StaffDAO();
                BillDTO b = new BillDTO(billID, id, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public CustomerDTO getCustomerByItemID(BillDTO bill) throws SQLException {
        CustomerDTO cus = new CustomerDTO();
        CustomerDAO daoCus = new CustomerDAO();
        ItemDTO item = new ItemDTO();
        ItemDAO daoItem = new ItemDAO();

        item = daoItem.viewItem(bill.getItemID());
        cus = daoCus.viewCus(item.getCustomerID());
        return cus;
    }

}
