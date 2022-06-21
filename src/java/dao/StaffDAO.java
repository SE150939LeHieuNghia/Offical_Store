/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class StaffDAO {

    private final String VIEW = "SELECT * FROM tblStaff WHERE staffID = ?";
    private final String CREATE = "INSERT INTO tblStaff (staffID,password,fullName,storeID) values (?, ?, ?, ?);";
    private final String UPDATE = "UPDATE tblStaff set password = ?, fullName = ? , storeID = ? WHERE staffID = ? ;";
    private final String DELETE = "DELETE FROM tblStaff WHERE staffID = ? ";

    public boolean createStaff(String id, String password, String fullName, String storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(CREATE);
                stm.setString(1, id);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setString(4, storeID);
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

    public StaffDTO viewStaff(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String storeID = rs.getString("storeID");
                    int store = Integer.parseInt(storeID);
                    StaffDTO staff = new StaffDTO(id, fullName, password, store);
                    return staff;
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

    public boolean updateStaff(String id, String password, String fullName, int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE);
                stm.setString(1, password);
                stm.setString(2, fullName);
                stm.setInt(3, storeID);
                stm.setString(4, id);
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

    public boolean deleteStaff(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
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

    public boolean checkLogin(String id, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT staffID FROM tblStaff WHERE staffID = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
        return false;
    }

    public ArrayList<StaffDTO> getAllStaff(int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ArrayList<StaffDTO> list = new ArrayList<>();
                String sql = "SELECT staffID from tblStaff where storeID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    StaffDTO s = viewStaff(rs.getString("staffID"));
                    list.add(s);
                }
                if (!list.isEmpty()) {
                    return list;
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
}
