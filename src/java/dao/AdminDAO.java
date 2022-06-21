/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AdminDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class AdminDAO {
    Connection con=null;
    PreparedStatement stm=null;
    ResultSet rs=null;
    private final String LOGIN="SELECT fullName FROM tblAdmin WHERE adminID = ? AND password = ? ;";
    public AdminDTO getAdmin(String id, String password) throws SQLException{
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                stm=con.prepareStatement(LOGIN);
                stm.setString(1, id);
                stm.setString(2, password);
                rs=stm.executeQuery();
                if(rs.next()){
                    String fullName=rs.getString("fullName");
                    AdminDTO admin =new AdminDTO(id, fullName, password);
                    return admin;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return null;
    }
    public boolean checkLogin(String id, String password) throws SQLException{
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                stm=con.prepareStatement(LOGIN);
                stm.setString(1, id);
                stm.setString(2, password);
                rs=stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return false;
    }
}
