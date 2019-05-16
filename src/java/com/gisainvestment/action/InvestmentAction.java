/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action;

import com.gisainvestment.utility.FileHandler;
import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.domain.Investment;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class InvestmentAction extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(InvestmentAction.class);
    private String ticket;
    private String reference;
    List<Investment> investments = new ArrayList<>();
    List<Investment> singleinvestment = new ArrayList<>();
    List<Investment> singlerefenceinvestment = new ArrayList<>();
    List<Investment> investmentToPay = new ArrayList<>();
    private InputStream excelStream;
    private String excelFileName;

    public String singleTicket() {
        singleinvestment = InvestmentDAOimp.getInvestmentByTicket(ticket);
        return "singleticket";
    }

    public String singleReference() {
        singlerefenceinvestment = InvestmentDAOimp.getInvestmentByReference(reference);
        return "singlereference";
    }

    public String view() {
        investments = InvestmentDAOimp.getAllInvestment();
        return "view";
    }

    public String readyToBePayed() {        
        investmentToPay = InvestmentDAOimp.calculateInvestment();
        return "readyToBePayed";
    }

    public String downloadExcel() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
            excelStream = FileHandler.generateExcel();
            excelFileName = "Report_Full_" + fmt.format(new Date()) + ".xls";
            return "download";
        } catch (Exception e) {
            LOGGER.error(e.toString());
            addActionError("Fail to download this full excel file");
            return "faildownload";
        }
    }
    public String downloadExcelMinified() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
            excelStream = FileHandler.generateExcelMinified();
            excelFileName = "Report_Mini_" + fmt.format(new Date()) + ".xls";
            return "download";
        } catch (Exception e) {
            LOGGER.error(e.toString());
            addActionError("Fail to download this mini excel file");
            return "faildownload";
        }
    }

//======================= GETTERS AND SETTERS =============================
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        InvestmentAction.LOGGER = LOGGER;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<Investment> getSingleinvestment() {
        return singleinvestment;
    }

    public void setSingleinvestment(List<Investment> singleinvestment) {
        this.singleinvestment = singleinvestment;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Investment> getSinglerefenceinvestment() {
        return singlerefenceinvestment;
    }

    public void setSinglerefenceinvestment(List<Investment> singlerefenceinvestment) {
        this.singlerefenceinvestment = singlerefenceinvestment;
    }

    public List<Investment> getInvestmentToPay() {
        return investmentToPay;
    }

    public void setInvestmentToPay(List<Investment> investmentToPay) {
        this.investmentToPay = investmentToPay;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

}
