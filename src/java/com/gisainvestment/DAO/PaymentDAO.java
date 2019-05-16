/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Payment;
import com.gisainvestment.utility.Validation;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author mcpaul
 */
public class PaymentDAO {

    private static Logger LOGGER = Logger.getLogger(PaymentDAO.class);
    private static LinkedHashMap<String, Payment> PAYMENTS = new LinkedHashMap<>();
    private double taxes;
    static {
        new Log4jConf().loadLog4j();
        loadPayments();
    }

    
    public static void loadPayments() {
        try {
            Session session = HibernateUtil.getSession();
            List<Payment> paymentl = session.createQuery("FROM com.gisainvestment.domain.Payment payment").list();
            for (Payment paymentInList : paymentl) {
                PAYMENTS.put(paymentInList.getId() + "", paymentInList);
            }
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
        }
    }

    public static List<Payment> getAllPayment() {
        List<Payment> payment = new ArrayList<>();
        try {
            for (Map.Entry<String, Payment> paymentInMap : PAYMENTS.entrySet()) {
                payment.add(paymentInMap.getValue());
            }
            return payment;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static boolean create(Payment payment) {
        boolean valid = false;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(payment);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            valid = true;
            PAYMENTS.put(payment.getId() + "", payment);
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            valid = false;
        }
        return valid;
    }

    public static int countPayments() {
        return PAYMENTS.size();
    }

    /**
     *
     * @return total amount in investment world
     */
    public static double loadAllTaxes() {
        double taxes = 0;
        try {
            for (Payment payment : getAllPayment()) {
                taxes = taxes + payment.getTax();
            }
            return taxes;
//            return 124566.9;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }

    public static String taxPaymentPerMonth() {

        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        try {
            Date initial = getAllPayment().get(0).getDue_date();
            int i = 1;
            while (initial.before(new Date())) {
                Date initialPlusMonth = DateUtils.addMonths(initial, 1);
                map = new HashMap<Object, Object>();
                map.put("x", i);
                map.put("y", countTaxRangeDate(initial, initialPlusMonth));
                LOGGER.error("initial" + initial);
                LOGGER.error("initial" + initialPlusMonth);
                list.add(map);
                initial = initialPlusMonth;
                i++;
            }
        } catch (Exception e) {
            LOGGER.error("EXCEPTION "+e.toString());
        }
        return gsonObj.toJson(list);
    }

    public static double countTaxRangeDate(Date startTime, Date endTime) {
        double taxes = 0;
        for (Map.Entry<String, Payment> paymentMap : PAYMENTS.entrySet()) {
            if (paymentMap.getValue().getDue_date().after(startTime) && paymentMap.getValue().getDue_date().before(endTime)) {
                LOGGER.error(paymentMap.getValue());
                taxes = taxes + paymentMap.getValue().getTax();
            }
        }
        return taxes;
    }
//===================================GETTER AND SETTER===========================

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        PaymentDAO.LOGGER = LOGGER;
    }

    public static LinkedHashMap<String, Payment> getPAYMENTS() {
        return PAYMENTS;
    }

    public static void setPAYMENTS(LinkedHashMap<String, Payment> PAYMENTS) {
        PaymentDAO.PAYMENTS = PAYMENTS;
    }

    public String getTaxes() {
        return Validation.formatDoubleReturnString(loadAllTaxes());
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

}
