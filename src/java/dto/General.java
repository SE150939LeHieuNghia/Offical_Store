/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class General implements Serializable{
    private String fullName;
    private String itemName;
    private Date billBeginDate;
    private int numberDays;

    public General() {
    }

    public General(String fullName, String itemName, Date billBeginDate, int numberDays) {
        this.fullName = fullName;
        this.itemName = itemName;
        this.billBeginDate = billBeginDate;
        this.numberDays = numberDays;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getBillBeginDate() {
        return billBeginDate;
    }

    public void setBillBeginDate(Date billBeginDate) {
        this.billBeginDate = billBeginDate;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }
    
    
}
