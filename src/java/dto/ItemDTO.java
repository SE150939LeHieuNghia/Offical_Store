/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ItemDTO extends CustomerDTO implements Serializable{
    private int itemID;
    private String itemName;
    private String itemPic;
    private String customerID;
    private String storeID;
    private Date itemSendingDate;
    private Date itemGettingDate;
    private boolean statusID;

    public ItemDTO() {
    }

    public ItemDTO(int itemID, String itemName, String itemPic, String customerID, String storeID, Date itemSendingDate, Date itemGettingDate, boolean statusID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPic = itemPic;
        this.customerID = customerID;
        this.storeID = storeID;
        this.itemSendingDate = itemSendingDate;
        this.itemGettingDate = itemGettingDate;
        this.statusID = statusID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public Date getItemSendingDate() {
        return itemSendingDate;
    }

    public void setItemSendingDate(Date itemSendingDate) {
        this.itemSendingDate = itemSendingDate;
    }

    public Date getItemGettingDate() {
        return itemGettingDate;
    }

    public void setItemGettingDate(Date itemGettingDate) {
        this.itemGettingDate = itemGettingDate;
    }

    public boolean isStatusID() {
        return statusID;
    }

    public void setStatusID(boolean statusID) {
        this.statusID = statusID;
    }

    
    
    
}
