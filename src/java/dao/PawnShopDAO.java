/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PawnShopDTO;
import dto.StaffDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class PawnShopDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private final String VIEW = "SELECT * FROM tblPawnShop WHERE storeID = ?";
    public final String CREATE = "INSERT INTO tblPawnShop (storeID,storeName,storeAddress,phoneNumber,managerID,confirmKey) values (?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE tblPawnShop set storeName = ?, storeAddress = ?, phoneNumber = ?, managerID = ?  WHERE storeID = ?;";
    private final String DELETE = "DELETE FROM tblPawnShop WHERE storeID = ? ";

    public boolean createPawnShop(String storeID, String storeName, String storeAddress, int phoneNumber, String managerID, String confirmKey) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(CREATE);
                stm.setString(1, storeID);
                stm.setString(2, storeName);
                stm.setString(3, storeAddress);
                stm.setInt(4, phoneNumber);
                stm.setString(5, managerID);
                stm.setString(6, confirmKey);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public PawnShopDTO viewPawnShop(String id) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String storeName=rs.getString("storeName");
                    String storeAddress=rs.getString("storeAddress");
                    int phoneNumber=rs.getInt("storeAddress");
                    String managerID=rs.getString("managerID");
                    String confirmKey=rs.getString("confirmKey");
                    PawnShopDTO shop = new PawnShopDTO(id, storeName, storeAddress, phoneNumber, managerID, confirmKey);
                    return shop;
                }
            }
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
        return null;
    }

    public boolean updatePawnShop(String storeID, String storeName, String storeAddress, int phoneNumber, String managerID) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE);
                stm.setString(1, storeName);
                stm.setString(2, storeAddress);
                stm.setInt(3, phoneNumber);
                stm.setString(4, managerID);
                stm.setString(5, storeID);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteStaff(String id) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(DELETE);
                stm.setString(1, id);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    
}
