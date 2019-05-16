/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.utility;

/**
 *
 * @author mcpaul
 */
public class Locator {

    //PAYMENT API
    public static final String WS_PAYMENT_URL = "http://41.74.172.132:8080/RequestsDispatcher/RequestsHandler";
//    public static final String WS_PAYMENT_URL = "http://41.74.172.132:8080/RequestsDispatcher/RequestsHandlerHost";
//    public static final String WS_PAYMENT_URL = "http://localhost:8080/gisa/endpoints/xml/momogwtest";
    public static final String WS_MTN_PAYMENTSPID = "2484";
    public static final String WS_TIGO_PAYMENTSPID = "3382";
    public static final String WS_AIRTEL_PAYMENTSPID = "5728";
    public static final String WS_OLTRANZ_API_CONTRACTID = "145201";
    //SMS API OLTRANZ
    public static final String WS_SMS_URL = "http://41.74.172.132:8080/SMSServiceProvider/sendSMS";
    public static final String WS_SMS_API_SRC = "GISA";
    public static final String WS_SMS_CONTRACTID = "14522341";
    //SMS API ROUTSMS
    public static final String ROUTESMS_USERNAME = "cyu-gisa";
    public static final String ROUTESMS_PASSWORD = "itdc@SYS";
    public static final String ROUTESMS_HOST = "121.241.242.114";
    public static final int ROUTESMS_PORT = 8080;
    public static final String ROUTESMS_TYPE = "1";
    public static final String ROUTESMS_DELIVERY = "0";
    public static final String ROUTESMS_SENDER = "GISA";
    //INTEREST RATE FOR GISA
    public static final double GISA_INTEREST_MTN = 0.038;
    public static final double GISA_INTEREST_TIGO = 0.038;
    public static final double GISA_INTEREST_AIRTEL = 0.04;
    
    public static final double GISA_SYSTEM_IN = 0.941; //removed cost from payment gateway(OLTRANZ)
    public static final double GISA_SYSTEM_CILCULATION_STORE = 0.784;
    public static final double GISA_SYSTEM_INVESTMENT_WORLD = 0.216;
    public static final double GISA_SYSTEM_OUT = 1.12;
    public static final double GISA_SYSTEM_OUT_RATE = 1.05392;
    public static final double GISA_SYSTEM_TAX_RATE_MTN_TIGO = 0.00670;
    public static final double GISA_SYSTEM_TAX_RATE_AIRTEL = 0.00705;

}
