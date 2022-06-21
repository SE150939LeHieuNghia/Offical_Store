/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.KeyDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class KeyDAO {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphaUpperCase = alpha.toUpperCase();
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    private static final String ADD = "INSERT INTO tblKey (confirmKey,statusKey,adminID) values (?, ?, ?) ";
    private static final String CHECK = "SELECT * FROM tblKey WHERE confirmKey= ? ";
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public boolean checkKey(String key) throws SQLException {

        try {
            conn = DBConnect.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK);
                stm.setString(1, key);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return false;
                }
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
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    public KeyDTO getKeyRandom(String adminID) throws SQLException {

        try {
            conn = DBConnect.makeConnection();
            if (conn != null) {
                String code = null;
                do {
                    code = randomAlphaNumeric(8);
                } while (!checkKey(code));
                if (code != null) {
                    KeyDTO key = new KeyDTO();
                    key.setComfirmKey(code);
                    stm = conn.prepareStatement(ADD);
                    stm.setString(1, code);
                    stm.setBoolean(2, true);
                    stm.setString(3, adminID);
                    if (stm.executeUpdate() > 0) {
                        return key;
                    }
//            }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
}
