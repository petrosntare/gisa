/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mcpaul
 */
@XmlRootElement(name = "COMMAND")
public class COMMAND {

    private String MSISDN;
    private String SESSIONID;
    private String FREEFLOW;
    private String MESSAGE;
    private String NEWREQUEST;
    private List<String> MENUS;
    private String AGENTID;
    private String INPUT;
    private String SPID;
    private String FROMMULTIUSSD;
    private boolean RESUME;

    public COMMAND() {
    }

    public COMMAND(String MSISDN, String SESSIONID, String FREEFLOW, String MESSAGE, String NEWREQUEST, List<String> MENUS) {
        this.MSISDN = MSISDN;
        this.SESSIONID = SESSIONID;
        this.FREEFLOW = FREEFLOW;
        this.MESSAGE = MESSAGE;
        this.NEWREQUEST = NEWREQUEST;
        this.MENUS = MENUS;
    }

    public COMMAND(String MSISDN, String SESSIONID, String NEWREQUEST, String AGENTID, String INPUT, String SPID, String FROMMULTIUSSD, boolean resume) {
        this.MSISDN = MSISDN;
        this.SESSIONID = SESSIONID;
        this.NEWREQUEST = NEWREQUEST;
        this.AGENTID = AGENTID;
        this.INPUT = INPUT;
        this.SPID = SPID;
        this.FROMMULTIUSSD = FROMMULTIUSSD;
        this.RESUME = resume;
    }

    public COMMAND(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    
    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getSESSIONID() {
        return SESSIONID;
    }

    public void setSESSIONID(String SESSIONID) {
        this.SESSIONID = SESSIONID;
    }

    public String getFREEFLOW() {
        return FREEFLOW;
    }

    public void setFREEFLOW(String FREEFLOW) {
        this.FREEFLOW = FREEFLOW;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getNEWREQUEST() {
        return NEWREQUEST;
    }

    public void setNEWREQUEST(String NEWREQUEST) {
        this.NEWREQUEST = NEWREQUEST;
    }

    @XmlElementWrapper
    @XmlElement(name = "MENU")
    public List<String> getMENUS() {
        return MENUS;
    }

    public void setMENUS(List<String> MENUS) {
        this.MENUS = MENUS;
    }

    public String getAGENTID() {
        return AGENTID;
    }

    public void setAGENTID(String AGENTID) {
        this.AGENTID = AGENTID;
    }

    public String getINPUT() {
        return INPUT;
    }

    public void setINPUT(String INPUT) {
        this.INPUT = INPUT;
    }

    public String getSPID() {
        return SPID;
    }

    public void setSPID(String SPID) {
        this.SPID = SPID;
    }

    public String getFROMMULTIUSSD() {
        return FROMMULTIUSSD;
    }

    public void setFROMMULTIUSSD(String FROMMULTIUSSD) {
        this.FROMMULTIUSSD = FROMMULTIUSSD;
    }

    public boolean isRESUME() {
        return RESUME;
    }

    public void setRESUME(boolean RESUME) {
        this.RESUME = RESUME;
    }

    @Override
    public String toString() {
        return "COMMAND{" + "MSISDN=" + MSISDN + ", SESSIONID=" + SESSIONID + ", FREEFLOW=" + FREEFLOW + ", MESSAGE=" + MESSAGE + ", NEWREQUEST=" + NEWREQUEST + ", MENUS=" + MENUS + ", AGENTID=" + AGENTID + ", INPUT=" + INPUT + ", SPID=" + SPID + ", FROMMULTIUSSD=" + FROMMULTIUSSD + ", RESUME=" + RESUME + '}';
    }

}
