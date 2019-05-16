package com.gisainvestment.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "COMMAND")
public class CommandPaymentGW {

    //1. Send request 
    private String CONTRACTID;
    private String PAYINGACCOUNTIDATSP;
    private String PAYMENTSPID;
    private String DESCR;
    private String TRANSID;
    private double AMOUNT;

    //1. Receive response
    private String RESPONDERSTATUS;
    private String REQUESTSTATUS;
    private String REQUESTSTATUSDESC;

    //2. Receive request 
//  private String TRANSID;
//  private String CONTRACTID;
    private String STATUSCODE;
    private String SPTRANSID;
    private String STATUSDESC;

    //2. Send response
//    private String TRANSID;
//    private String STATUSCODE;
//    private String DESCR;
    public CommandPaymentGW() {
    }

    public CommandPaymentGW(String PAYINGACCOUNTIDATSP) {
        this.PAYINGACCOUNTIDATSP = PAYINGACCOUNTIDATSP;
    }

    public CommandPaymentGW(String CONTRACTID, String PAYINGACCOUNTIDATSP, String PAYMENTSPID, String DESCR, String TRANSID, double AMOUNT, String RESPONDERSTATUS, String REQUESTSTATUS, String REQUESTSTATUSDESC, String STATUSCODE, String SPTRANSID, String STATUSDESC) {
        this.CONTRACTID = CONTRACTID;
        this.PAYINGACCOUNTIDATSP = PAYINGACCOUNTIDATSP;
        this.PAYMENTSPID = PAYMENTSPID;
        this.DESCR = DESCR;
        this.TRANSID = TRANSID;
        this.AMOUNT = AMOUNT;
        this.RESPONDERSTATUS = RESPONDERSTATUS;
        this.REQUESTSTATUS = REQUESTSTATUS;
        this.REQUESTSTATUSDESC = REQUESTSTATUSDESC;
        this.STATUSCODE = STATUSCODE;
        this.SPTRANSID = SPTRANSID;
        this.STATUSDESC = STATUSDESC;
    }

    //===============================================
    public String getCONTRACTID() {
        return CONTRACTID;
    }

    /**
     *
     *
     * @param CONTRACTID set CONTRACT ID NUMBER from API
     */
    public void setCONTRACTID(String CONTRACTID) {
        this.CONTRACTID = CONTRACTID;
    }

    public String getPAYINGACCOUNTIDATSP() {
        return PAYINGACCOUNTIDATSP;
    }

    /**
     *
     *
     * @param PAYINGACCOUNTIDATSP set ACCOUNT TO TRANSFER MONEY FROM
     */
    public void setPAYINGACCOUNTIDATSP(String PAYINGACCOUNTIDATSP) {
        this.PAYINGACCOUNTIDATSP = PAYINGACCOUNTIDATSP;
    }

    public String getPAYMENTSPID() {
        return PAYMENTSPID;
    }

    /**
     *
     *
     * @param PAYMENTSPID set ID OF TELCOM
     */
    public void setPAYMENTSPID(String PAYMENTSPID) {
        this.PAYMENTSPID = PAYMENTSPID;
    }

    public String getDESCR() {
        return DESCR;
    }

    /**
     *
     *
     * @param DESCR set DESCRIPTION OF THE PAYMENT(MOTIF)
     */
    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public String getTRANSID() {
        return TRANSID;
    }

    /**
     *
     *
     * @param TRANSID set TRANSACTION ID
     */
    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    /**
     *
     *
     * @param AMOUNT set AMOUNT
     */
    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getRESPONDERSTATUS() {
        return RESPONDERSTATUS;
    }

    public void setRESPONDERSTATUS(String RESPONDERSTATUS) {
        this.RESPONDERSTATUS = RESPONDERSTATUS;
    }

    public String getREQUESTSTATUS() {
        return REQUESTSTATUS;
    }

    public void setREQUESTSTATUS(String REQUESTSTATUS) {
        this.REQUESTSTATUS = REQUESTSTATUS;
    }

    public String getREQUESTSTATUSDESC() {
        return REQUESTSTATUSDESC;
    }

    public void setREQUESTSTATUSDESC(String REQUESTSTATUSDESC) {
        this.REQUESTSTATUSDESC = REQUESTSTATUSDESC;
    }

    public String getSTATUSCODE() {
        return STATUSCODE;
    }

    public void setSTATUSCODE(String STATUSCODE) {
        this.STATUSCODE = STATUSCODE;
    }

    public String getSPTRANSID() {
        return SPTRANSID;
    }

    public void setSPTRANSID(String SPTRANSID) {
        this.SPTRANSID = SPTRANSID;
    }

    public String getSTATUSDESC() {
        return STATUSDESC;
    }

    public void setSTATUSDESC(String STATUSDESC) {
        this.STATUSDESC = STATUSDESC;
    }

    @Override
    public String toString() {
        return "CommandPaymentGW{" + "CONTRACTID=" + CONTRACTID + ", PAYINGACCOUNTIDATSP=" + PAYINGACCOUNTIDATSP + ", PAYMENTSPID=" + PAYMENTSPID + ", DESCR=" + DESCR + ", TRANSID=" + TRANSID + ", AMOUNT=" + AMOUNT + ", RESPONDERSTATUS=" + RESPONDERSTATUS + ", REQUESTSTATUS=" + REQUESTSTATUS + ", REQUESTSTATUSDESC=" + REQUESTSTATUSDESC + ", STATUSCODE=" + STATUSCODE + ", SPTRANSID=" + SPTRANSID + ", STATUSDESC=" + STATUSDESC + '}';
    }

}
