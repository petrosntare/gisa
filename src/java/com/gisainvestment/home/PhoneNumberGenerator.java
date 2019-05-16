/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.home;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 *
 * @author LTD
 */
public class PhoneNumberGenerator {

    private static DecimalFormat formatter = new DecimalFormat("0000000");
    public static String getPhoneNumber() {
        String[] rwInits = new String[]{"072", "078", "073"};
        int startIndex = (int) (Math.random() * 3);
        int endNum = (int) (Math.random() * 10000000);
        return rwInits[startIndex] + formatter.format(endNum);
    }
    public static String getMtnPhoneNumber() {
        String[] rwInits = new String[]{"078"};
        int startIndex = (int) (Math.random());
        int endNum = (int) (Math.random() * 10000000);
        return rwInits[startIndex] + formatter.format(endNum);
    }
    public static String getIdCard(){
        return "";
    }
}
