/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.CommandPaymentGW;
import com.gisainvestment.domain.Investment;
import com.gisainvestment.utility.Locator;
import com.gisainvestment.utility.SimpleDateConverter;
import com.gisainvestment.utility.Validation;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author McP
 */
public class InvestmentDAOimp {

    private static LinkedHashMap<String, Investment> INVESTMENTS = new LinkedHashMap<>();
    static Logger LOGGER = Logger.getLogger(InvestmentDAOimp.class.getName());
    private String status;
    private Date toCheck;
    private int difference;
    private double inWorld;
    private double inCirculation;
    private double inSys;

    static {
        new Log4jConf().loadLog4j();
        loadInvestment();
    }

    public static void loadInvestment() {
        try {
            Session session = HibernateUtil.getSession();
            List<Investment> investmentl = session.createQuery("FROM com.gisainvestment.domain.Investment investment order by investment.inv_date").list();
//            List<Investment> investmentl = session.createCriteria(Investment.class).list();
            for (Investment investmentInList : investmentl) {
                INVESTMENTS.put(investmentInList.getId() + "", investmentInList);
            }
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
        }
    }

    public static boolean create(Investment investment) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(investment);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            INVESTMENTS.put(investment.getId() + "", investment);
            return true;
        } catch (HibernateException e) {
            LOGGER.error("HibernateException " + e.toString());
            return false;
        }
    }

    public static Long countInvestment() {
        try {
            return Long.valueOf(INVESTMENTS.size());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }

    }

    public static List<Investment> getInvestmentByReference(String reference) {

        try {
            List<Investment> InvestmentL = new ArrayList<>();
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getReference().equals(reference)
                        && investMap.getValue().getTransactionStatus().equals("success")) {
                    InvestmentL.add(investMap.getValue());
                }
            }
            return InvestmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static List<Investment> getQueuedInvestment() {
        try {
            List<Investment> InvestmentL = new ArrayList<>();
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getStatus().equals("Queued")
                        && investMap.getValue().getTransactionStatus().equals("success")) {
                    InvestmentL.add(investMap.getValue());
                }
            }
            return InvestmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }

    }

    public static List<Investment> getInvestmentByReferenceForToday(String reference, Date today) {
        try {
            List<Investment> InvestmentL = new ArrayList<>();
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getReference().equals(reference)
                        && investMap.getValue().getInv_date().equals(today)
                        && investMap.getValue().getStatus().equals("Queued")
                        && investMap.getValue().getTransactionStatus().equals("success")) {
                    InvestmentL.add(investMap.getValue());
                }
            }
            return InvestmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static List<Investment> getInvestmentByTicket(String ticket) {
        try {
            List<Investment> InvestmentL = new ArrayList<>();
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getTicket().equals(ticket)) {
                    InvestmentL.add(investMap.getValue());
                }
            }
            return InvestmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static Investment checkTicket(String ticket) {
        try {
            Investment invest = null;
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getTicket().equals(ticket)
                        && investMap.getValue().getStatus().equals("Queued")
                        && investMap.getValue().getTransactionStatus().equals("Pending")) {
                    invest = investMap.getValue();
                }
            }
            return invest;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static Investment checkSuccessTicket(String ticket) {
        try {

            Investment invest = null;
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                if (investMap.getValue().getTicket().equals(ticket)
                        && investMap.getValue().getStatus().equals("Queued")
                        && investMap.getValue().getTransactionStatus().equals("success")) {

                    invest = investMap.getValue();
                }
            }
            return invest;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static boolean updateTicket(Investment inv) {
        try {
            LOGGER.error(inv);
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(inv);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            INVESTMENTS.put(inv.getId() + "", inv);
            return true;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return false;
        }

    }

    public static List<Investment> getAllInvestment() {
        try {
            List<Investment> investmentL = new ArrayList<>();
            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {

                investmentL.add(investMap.getValue());
            }
            return investmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public int getDifference() {
        long diff = SimpleDateConverter.toLocalTZReturnDate(new Date()).getTime() - toCheck.getTime();
        long days = diff / 1000 / 60 / 60 / 24;
        difference = (int) days;
        return difference;
    }

    public int dateDifference() {
        long diff = SimpleDateConverter.toLocalTZReturnDate(new Date()).getTime() - toCheck.getTime();
        long days = diff / 1000 / 60 / 60 / 24;
        difference = (int) days;
        return difference;
    }

    public int checkdate() {
        long diff = toCheck.getTime() - SimpleDateConverter.toLocalTZReturnDate(new Date()).getTime();
        long days = diff / 1000 / 60 / 60 / 24;
        difference = (int) days;
        return difference;
    }

    public static int daysPassed(Date toCheck) {
        long diff = SimpleDateConverter.toLocalTZReturnDate(new Date()).getTime() - toCheck.getTime();
        long days = diff / 1000 / 60 / 60 / 24;
        int difference = (int) days;
        return difference;
    }

    /**
     *
     * @param reference
     * @return return list of investment within 31 days of a particular
     * reference number
     */
    public static List<Investment> miniStatement(String reference) {
        try {
            List<Investment> InvestmentL = new ArrayList<>();

            for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
                InvestmentDAOimp inves = new InvestmentDAOimp();
                inves.setToCheck(investMap.getValue().getInv_date());
                if (investMap.getValue().getReference().equals(reference)
                        && investMap.getValue().getStatus().equals("Queued")
                        && investMap.getValue().getTransactionStatus().equals("success")
                        && inves.dateDifference() <= 31) {
                    InvestmentL.add(investMap.getValue());
                }
            }
            return InvestmentL;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    /**
     * This function retrieve list of lenders who are ready to be paid according
     * to the circulation store total amount and days between the date on which
     * the deposit is made and today are greater that 16.
     */
    public static List<Investment> calculateInvestment() {
        List<Investment> list = new ArrayList<>();
        double restInStore = circulationStore();
        InvestmentDAOimp inves = new InvestmentDAOimp();
        try {
            for (Investment inv : getQueuedInvestment()) {
                restInStore = restInStore - (inv.getAmount() * Locator.GISA_SYSTEM_OUT_RATE);
                inves.setToCheck(inv.getInv_date());
                if (restInStore > 0 && inves.dateDifference() > 15) {
//                if (restInStore > 0) {
                    list.add(inv);
                }
            }
            return list;

        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static int countRedTicket() {
        InvestmentDAOimp inves = new InvestmentDAOimp();
        List<Investment> list = new ArrayList<>();
        for (Investment inv : calculateInvestment()) {
            inves.setToCheck(inv.getInv_date());
            if (inves.dateDifference() >= 27 && inves.dateDifference() <= 30) {
                LOGGER.error(inv.getInv_date());
                LOGGER.error(inves.dateDifference());
                list.add(inv);
            }
        }
        return list.size();
    }

    public static int countOrangeTicket() {
        InvestmentDAOimp inves = new InvestmentDAOimp();
        List<Investment> list = new ArrayList<>();
        for (Investment inv : calculateInvestment()) {
            inves.setToCheck(inv.getInv_date());
            if (inves.dateDifference() >= 23 && inves.dateDifference() <= 26) {
                LOGGER.error(inv.getInv_date());
                LOGGER.error(inves.dateDifference());
                list.add(inv);
            }
        }
        return list.size();
    }

    public static int countGreenTicket() {
        InvestmentDAOimp inves = new InvestmentDAOimp();
        List<Investment> list = new ArrayList<>();
        for (Investment inv : calculateInvestment()) {
            inves.setToCheck(inv.getInv_date());
            if (inves.dateDifference() >= 16 && inves.dateDifference() <= 22) {
                LOGGER.error(inv.getInv_date());
                LOGGER.error(inves.dateDifference());
                list.add(inv);
            }
        }
        return list.size();
    }

    /**
     * look-up in all investment ready to be payed -calculated the amount we got
     * in the system after investment(94,1%) -here we removed costs from payment
     * gateway -calculated the amount we have in the circulation store(78,4%).
     */
    public static double circulationStore() {
        double amountSYS = 0;
        double amountCirculation = 0;
        try {
            for (Investment inv : getQueuedInvestment()) {
                amountSYS = amountSYS + (inv.getAmount() * Locator.GISA_SYSTEM_IN);
                amountCirculation = amountSYS * Locator.GISA_SYSTEM_CILCULATION_STORE;
            }
            return amountCirculation;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }

    /**
     *
     * @return total amount in system
     */
    public static double inSystem() {
        double amountSYS = 0;
        try {
            for (Investment inv : getQueuedInvestment()) {
                amountSYS = amountSYS + inv.getIn_sys();
            }
            return amountSYS;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }

    /**
     *
     * @return total amount in circulation store
     */
    public static double inCirculationStore() {
        double amountCirculation = 0;
        try {
            for (Investment inv : getQueuedInvestment()) {
                amountCirculation = amountCirculation + inv.getIn_circulation();
            }
            return amountCirculation;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }

    /**
     *
     * @return total amount in investment world
     */
    public static double inInvestmentWorld() {
        double amountInvestmentWorld = 0;
        try {
            for (Investment inv : getQueuedInvestment()) {
                amountInvestmentWorld = amountInvestmentWorld + inv.getIn_investment_world();
            }
            return Validation.formatDouble(amountInvestmentWorld);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }

    /**
     *
     * @return static data
     */
    public static String chatDataFetcher() {
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

        map = new HashMap<Object, Object>();
        map.put("x", 10);
        map.put("y", 71);
        list.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", 20);
        map.put("y", 55);
        list.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", 30);
        map.put("y", 50);
        list.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", 40);
        map.put("y", 65);
        list.add(map);
        return gsonObj.toJson(list);
    }

    public static String investmentPerWeek() {
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Date initial = getAllInvestment().get(0).getInv_date();
        int i = 1;
        while (initial.before(new Date())) {
            Date initialPlusWeek = SimpleDateConverter.addDays(initial, 7);
            map = new HashMap<Object, Object>();
            map.put("x", i);
            map.put("y", countRangeDate(initial, initialPlusWeek));
            list.add(map);
            initial = initialPlusWeek;
            i++;
        }
        return gsonObj.toJson(list);
    }
    public static String investmentPerMonth() {
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        Date initial = getAllInvestment().get(0).getInv_date();
        int i = 1;
        while (initial.before(new Date())) {
            Date initialPlusMonth = SimpleDateConverter.addMonth(initial, 1);
            map = new HashMap<Object, Object>();
            map.put("x", i);
            map.put("y", countRangeDate(initial, initialPlusMonth));
            list.add(map);
            initial = initialPlusMonth;
            i++;
        }
        return gsonObj.toJson(list);
    }

    public static int countRangeDate(Date statTime, Date endTime) {
        int count = 0;

        for (Map.Entry<String, Investment> investMap : INVESTMENTS.entrySet()) {
            if (investMap.getValue().getInv_date().after(statTime) && investMap.getValue().getInv_date().before(endTime)) {
                count++;
            }
        }
        return count;
    }
//==================================GETTER AND SETTER===========================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        InvestmentDAOimp.LOGGER = LOGGER;
    }

    public static LinkedHashMap<String, Investment> getINVESTMENTS() {
        return INVESTMENTS;
    }

    public static void setINVESTMENTS(LinkedHashMap<String, Investment> INVESTMENTS) {
        InvestmentDAOimp.INVESTMENTS = INVESTMENTS;
    }

    public Date getToCheck() {
        return toCheck;
    }

    public void setToCheck(Date toCheck) {
        this.toCheck = toCheck;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public double getInWorld() {
        return inInvestmentWorld();
    }

    public void setInWorld(double inWorld) {
        this.inWorld = inWorld;
    }

    public double getInCirculation() {
        return inCirculationStore();
    }

    public void setInCirculation(double inCirculation) {
        this.inCirculation = inCirculation;
    }

    public double getInSys() {
        return inSystem();
    }

    public void setInSys(double inSys) {
        this.inSys = inSys;
    }

    public static void main(String[] args) {
        System.out.println("Green " + countGreenTicket());
        System.out.println("Orange " + countOrangeTicket());
        System.out.println("Red " + countRedTicket());
    }
}
