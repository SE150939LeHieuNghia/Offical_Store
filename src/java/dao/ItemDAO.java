/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CustomerDTO;
import dto.ItemDTO;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class ItemDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private final String VIEW = "SELECT * FROM tblItem WHERE itemID = ?";

    public ArrayList<ItemDTO> getAllItem() {
        ArrayList<ItemDTO> item = new ArrayList<ItemDTO>();
        try {
            con = DBConnect.makeConnection();

            if (con != null) {
                String sql = "Select itemID, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID "
                        + "From tblItem ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    String storeID = rs.getString("storeID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");

                    ItemDTO i = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID);
                    item.add(i);
                }

            }
        } catch (Exception e) {

        }
        return item;
    }

    public ItemDTO viewItem(int id) throws SQLException {
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int itemID;
                    String itemName = rs.getString("itemName");
                    String itemPic = null;
                    String customerID = rs.getString("customerID");
                    String storeID = null;
                    Date itemSendingDate = null;
                    Date itemGettingDate = rs.getDate("itemGettingDate");
                    boolean statusID = true;
                    ItemDTO item = new ItemDTO(id, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID);
                    return item;
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

    public ArrayList<ItemDTO> viewItems(int id) throws SQLException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int itemID;
                    String itemName = rs.getString("itemName");
                    String itemPic = null;
                    String customerID = rs.getString("customerID");
                    String storeID = null;
                    Date itemSendingDate = null;
                    Date itemGettingDate = rs.getDate("itemGettingDate");
                    boolean statusID = true;
                    ItemDTO item = new ItemDTO(id, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID);
                    list.add(item);
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
        return list;
    }
}
