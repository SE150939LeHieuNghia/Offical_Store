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
import java.util.ArrayList;
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
    public final String CREATE = "INSERT INTO tblPawnShop (storeName,storeAddress,phoneNumber,managerID,confirmKey) values (?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE tblPawnShop set storeName = ?, storeAddress = ?, phoneNumber = ?, managerID = ?  WHERE storeID = ?;";
    private final String DELETE = "DELETE FROM tblPawnShop WHERE storeID = ? ";
    private final String GET_LIST_SHOP = "SELECT * FROM tblPawnShop";
    private final String GET_ID = "SELECT storeID from tblPawnShop WHERE confirmKey = ?";
    public boolean createPawnShop(String storeName, String storeAddress, int phoneNumber, String managerID, String confirmKey) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(CREATE);
                stm.setString(1, storeName);
                stm.setString(2, storeAddress);
                stm.setInt(3, phoneNumber);
                stm.setString(4, managerID);
                stm.setString(5, confirmKey);
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

    public PawnShopDTO viewPawnShop(int id) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String storeName=rs.getString("storeName");
                    String storeAddress=rs.getString("storeAddress");
                    int phoneNumber=rs.getInt("phoneNumber");
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

    public boolean updatePawnShop(int storeID, String storeName, String storeAddress, int phoneNumber, String managerID) throws SQLException, ClassNotFoundException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE);
                stm.setString(1, storeName);
                stm.setString(2, storeAddress);
                stm.setInt(3, phoneNumber);
                stm.setString(4, managerID);
                stm.setInt(5, storeID);
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

    public boolean deleteShop(String id) throws SQLException, ClassNotFoundException {
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
    
    public int getPawnShopIDByKey(String key) throws SQLException{
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                stm=con.prepareStatement(GET_ID);
                stm.setString(1, key);
                rs=stm.executeQuery();
                if(rs.next()){
                    return rs.getInt("storeID");
                }
            }
        }finally{
         if(rs!=null) rs.close();
         if(stm!=null) stm.close();
         if(con!=null) con.close();
        }
        return -1;
    }
    
    public ArrayList<PawnShopDTO> getListShop() throws SQLException{
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                ArrayList<PawnShopDTO> list=new ArrayList<>();
                stm=con.prepareStatement(GET_LIST_SHOP);
                rs=stm.executeQuery();
                while(rs.next()){
                    int storeID =rs.getInt("storeID");
                    String storeName=rs.getString("storeName");
                    String storeAddress=rs.getString("storeAddress");
                    int phoneNumber=rs.getInt("storeAddress");
                    String managerID=rs.getString("managerID");
                    String confirmKey=rs.getString("confirmKey");
                    PawnShopDTO shop = new PawnShopDTO(storeID, storeName, storeAddress, phoneNumber, managerID, confirmKey);
                    list.add(shop);
                }
                if(!list.isEmpty()) return list;
            }
        }finally{
         if(rs!=null) rs.close();
         if(stm!=null) stm.close();
         if(con!=null) con.close();
        }
        return null;
    }
}
