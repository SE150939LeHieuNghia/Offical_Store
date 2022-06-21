/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class Tool {

    public static boolean checkDate(String day, String month, String year) {
        String date = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date = day + "/" + month + "/" + year;
        try {
            df.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String checkString(String s, int i, boolean b) {
           if (s == null || s == "") {
            return " không được để trống";
        }
        if (b == false) {
                if (s.length()< i) {
                    return " phải chứa tối thiếu "+i+" con số";
                }
            }else {
                if (s.length() != i) {
                    return " phải chứa đúng " + i + " con số";
                }
            }
            return "";
        }
    public static String checkInt(String s, int i, boolean b) {
       try {
           if (s == null || s == "") {
            return " không được để trống";
        }
           long test = Long.parseLong(s);
        if (b == false) {
                if (s.length()< i) {
                    return " phải chứa tối thiếu "+i+" con số";
                }
            }else {
                if (s.length() != i) {
                    return " phải chứa đúng " + i + " con số";
                }
            }
            return "";
        } catch (NumberFormatException e) {
            return " phải là một dãy số";
        }
       
    }
}
