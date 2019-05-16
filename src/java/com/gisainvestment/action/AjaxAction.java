/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class AjaxAction extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(AjaxAction.class);
    private String valueToDiv;

    public void reloadContent() {
        valueToDiv = "These are data from AjaxAction";
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        AjaxAction.LOGGER = LOGGER;
    }

    public String getValueToDiv() {
        return valueToDiv;
    }

    public void setValueToDiv(String valueToDiv) {
        this.valueToDiv = valueToDiv;
    }

}
