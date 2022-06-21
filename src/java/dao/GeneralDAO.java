/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.General;
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
public class GeneralDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public ArrayList<General> getProperties(){
        ArrayList<General> gen = new ArrayList<>();
        try{
            con = DBConnect.makeConnection();
            
            if(con != null){
               String sql =  " select c.fullname, i.itemName, b.billBeginDate, b.numberDays "
                           + " from tblCustomer c inner join tblItem i on c.customerID = i.customerID inner join tblBill b on i.itemID = b.itemID "
                           + " where c.customerID = i.customerID and i.itemID = b.itemID";
               stm = con.prepareStatement(sql);
               
               rs = stm.executeQuery();
               
               while(rs.next()){
                   String fullName = rs.getString("fullname");
                   String itemName = rs.getString("itemName");
                   Date billBeginDate = rs.getDate("billBeginDate");
                   int numberDays = rs.getInt("numberDays");
                   
                   General g = new General(fullName, itemName, billBeginDate, numberDays);
                   gen.add(g);
               }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return gen;
        
    }
}
