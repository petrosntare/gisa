/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.utility;

import java.text.DecimalFormat;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class Validation {

    private static Logger LOGGER = Logger.getLogger(Validation.class);

    /**
     *
     * @param o
     * @return true if it object is null
     */
    public static boolean isObjectNull(Object o) {
        boolean valid = false;
        if (o == null) {
            valid = true;
        }
        return valid;
    }

    /**
     *
     * @param list
     * @return true if list is null or empty
     */
    public static boolean isListNullOrEmpty(List<Object> list) {
        boolean valid = false;
        if (list == null || list.isEmpty()) {
            valid = true;
        }
        return valid;
    }

    public static double formatDouble(double value) {
        double valid = 0;
        try {
            String doubleString = new DecimalFormat("###.##").format(value);
            valid = Double.parseDouble(doubleString);
            return valid;
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException" + e.toString());
            return valid;
        }
    }

    public static String formatDoubleReturnString(double value) {
        return new DecimalFormat("#,###.##").format(value);
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        Validation.LOGGER = LOGGER;
    }
}
