/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.thirdparties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;

/**
 *
 * @author McP
 */
public class Mtn {

    private static final Map<String, MoMo> MtnAccounts = new HashMap<>();

    static {
        loadAcount();
    }

    /**
     * Before sending money, Check the validity of the phone and password
     * entered
     *
     * @param sendPhone
     * @param receivePhone
     * @param amount
     * @param pwd_for_sender
     * @return
     */
    public static String sendMomo(String sendPhone, String receivePhone, double amount, String pwd_for_sender) {
        
        MoMo send = getMtnAccount(sendPhone);
        MoMo receive = getMtnAccount(receivePhone);

        if (!send.getPassword().equals(pwd_for_sender)) {
            return "Dear " + send.getName() + " your password is not correct. please try again later.";
        } else if (send.getBalance() < amount) {
            return "Dear " + send.getName() + " your balance is = " + send.getBalance() + " and is insufficient";
        } else {
            receive.setBalance(receive.getBalance() + amount);
            send.setBalance(send.getBalance() - amount);
            return " Dear " + send.getName() + " the amount of " + amount + " was placed. Your new balance is = " + send.getBalance();
        }
    }

    public static void loadAcount() {

        Session session = HibernateUtil.getSession();
        List<MoMo> momoList = session.createCriteria(MoMo.class).list();
        for (MoMo momoinlist : momoList) {
            MtnAccounts.put(momoinlist.getPhone(), momoinlist);
        }
        HibernateUtil.getSession().getSessionFactory().close();
        session.close();
    }

    public static MoMo getMtnAccount(String phone) {
        return MtnAccounts.get(phone);
    }

//    public static String placeMoney(String phone, double amount, String passwrd) {
//        MoMo mtn = getMtnAccount(phone);
//        if (!mtn.getPassword().equals(passwrd)) {
//            return "Dear " + mtn.getName() + " your password is not correct. please try again latter.";
//        } else if (mtn.getBalance() < amount) {
//            return "Dear " + mtn.getName() + " your balance is = " + mtn.getBalance() + " and is insufficient";
//        } else {
//            MoMo gisa = getMtnAccount("0783453253");
//            gisa.setBalance(gisa.getBalance() + amount);
//            mtn.setBalance(mtn.getBalance() - amount);
//            return " Dear " + mtn.getName() + " the amount of " + amount + " was placed. Your new balance is = " + mtn.getBalance();
//        }
//    }
}
