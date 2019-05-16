/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class Navigation extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(Navigation.class);
    private String report;

    public String passwordRecoveryPage() {
        return "passwordRecovery";
    }

    public String adminHomePage() {
        return "adminHome";
    }
    public String financeHomePage() {
        return "financeHome";
    }
    public String accountingHomePage() {
        return "accountingHome";
    }

    public String investmentPage() {
        return "investment";
    }

    public String investmentSinglePage() {
        return "investmentSingle";
    }

    public String readytobePayedPage() {
        return "investmentReadytobePayed";
    }

    public String PaymentPage() {
        return "PaymentPage";
    }
    public String PaymentUploadPage() {
        return "PaymentUploadPage";
    }

    public String accountAllPage() {
        return "accountAll";
    }

    public String loginPage() {
        LOGGER.error("in loginPage()");
        return "loginPage";
    }
    public String loginRedirect(){
        return "loginRedirect";
    }
//=============================GETTERS AND SETTERS===========================

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        Navigation.LOGGER = LOGGER;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
