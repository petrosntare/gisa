/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.SmsMessage;
import com.gisainvestment.utility.Locator;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mcpaul
 */
public class SmsService {
    static Logger LOG = Logger.getLogger(SmsService.class.getName());

    static {
        new Log4jConf().loadLog4j();
    }

    public static void main(String[] args) {
        SmsMessage response = new SmsMessage();
        response.setSrc("GISA");
//        response.setDest("250736305895");
        response.setDest("250783453253");
        response.setMessage("FIRST MESSAGE TEST 9");
        response.setWait("0");
        response.setContractId(Locator.WS_SMS_CONTRACTID);
        response.setId(countSmsMessage());
        response.setStatus("SUCCESS");
//        LOG.error("save message :\n" + create(response));

        LOG.error("\n" + new SmsService().sendsms(response));
    }

    public static boolean create(SmsMessage sms) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(sms);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            return true;
        } catch (HibernateException e) {
            LOG.error(e.toString());
            return false;
        }
    }

    public static Long countSmsMessage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String queryString = "select count(*) from sms_message";
        Query query = session.createQuery(queryString);
        Object queryResult = query.uniqueResult();
        Long user = (Long) queryResult;
        session.getTransaction().commit();
        session.close();
        return user + 1;
    }

    public static List<SmsMessage> getAllSmsMessage() {
        Session session = HibernateUtil.getSession();
        List<SmsMessage> smsMessage = session.createCriteria(SmsMessage.class).list();
        session.close();
        return smsMessage;
    }

    public static boolean updateSmsmessage(SmsMessage sms) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(sms);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return true;
        }

    }

    public boolean sendsms(SmsMessage sms) {
        try {
            sms.setId(countSmsMessage());
            sms.setStatus("SUCCESS");
            sms.setContractId(Locator.WS_SMS_CONTRACTID);
            sms.setWait("0");
            sms.setSrc("GISA");

            HttpResponse<String> response = Unirest.post(Locator.WS_SMS_URL)
                    .header("content-type", "application/json")
                    .body("{\n\""
                            + "src\":\"" + sms.getSrc() + "\",\n\""
                            + "dest\":\"" + sms.getDest() + "\",\n\""
                            + "message\":\"" + sms.getMessage() + "\",\n\""
                            + "wait\":" + sms.getWait() + ",\n\""
                            + "contractId\":" + sms.getContractId() + "\n} ")
                    .asString();
            Gson json = new Gson();
            SmsMessage resp = json.fromJson(response.getBody(), SmsMessage.class);
            LOG.error("SMS REQUEST SENT: \n---------------" + sms);
            LOG.error("\nSMS: RECEIVED RESPONSE: \n---------------" + resp);
            sms.setCode(resp.getCode());
            sms.setDescr(resp.getDescr());
            if (create(sms)) {
                if (resp.getCode().equals("100")) {
                    return true;
                } else {
                    sms.setStatus("FAIL");
                    updateSmsmessage(sms);
                    return false;
                }
            } else {
                return false;
            }
        } catch (UnirestException e) {
            LOG.error(e.toString());
            return false;
        }

    }

    public boolean sendToRouteSms(SmsMessage sms) {
        try {
            sms.setId(countSmsMessage());
            sms.setStatus("SUCCESS");
            sms.setContractId(Locator.WS_SMS_CONTRACTID);
            sms.setWait("0");
            sms.setSrc("GISA");

            HttpResponse<String> response = Unirest.post(Locator.WS_SMS_URL)
                    .header("content-type", "application/json")
                    .body("{\n\""
                            + "src\":\"" + sms.getSrc() + "\",\n\""
                            + "dest\":\"" + sms.getDest() + "\",\n\""
                            + "message\":\"" + sms.getMessage() + "\",\n\""
                            + "wait\":" + sms.getWait() + ",\n\""
                            + "contractId\":" + sms.getContractId() + "\n} ")
                    .asString();
            Gson json = new Gson();
            SmsMessage resp = json.fromJson(response.getBody(), SmsMessage.class);
            LOG.error("SMS REQUEST SENT: \n---------------" + sms);
            LOG.error("\nSMS: RECEIVED RESPONSE: \n---------------" + resp);
            sms.setCode(resp.getCode());
            sms.setDescr(resp.getDescr());
            if (create(sms)) {
                if (resp.getCode().equals("100")) {
                    return true;
                } else {
                    sms.setStatus("FAIL");
                    updateSmsmessage(sms);
                    return false;
                }
            } else {
                return false;
            }
        } catch (UnirestException e) {
            LOG.error(e.toString());
            return false;
        }

    }
}
