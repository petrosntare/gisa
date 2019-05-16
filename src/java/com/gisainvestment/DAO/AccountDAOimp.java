/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import java.util.List;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Account;
import static com.gisainvestment.domain.Account.generateNewPassword;
import com.gisainvestment.security.PasswordEncrypter;
import com.gisainvestment.wService.Sender;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author McP
 */
public class AccountDAOimp {

    private static Logger LOGGER = Logger.getLogger(AccountDAOimp.class);
    private static final LinkedHashMap<String, Account> ACCOUNTS = new LinkedHashMap<>();

    static {
        new Log4jConf().loadLog4j();
        loadAccount();
    }

    public static boolean create(Account account) {
        try {
            account.setPassword(PasswordEncrypter.securePassword(account.getPassword()));
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            ACCOUNTS.put(account.getId().toString(), account);
            return true;
        } catch (HibernateException e) {
            LOGGER.error("Exception "+e.toString());
            return false;
        }
    }

    public static boolean update(Account account) {
        try {
            account.setPassword(PasswordEncrypter.securePassword(account.getPassword()));
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            ACCOUNTS.put(account.getId().toString(), account);
            return true;
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
            return false;
        }
    }

    public static Account getAccount(Account acc) {
        try {
            Account account = null;
            acc.setPassword(PasswordEncrypter.securePassword(acc.getPassword()));
            for (Map.Entry<String, Account> accountInMap : ACCOUNTS.entrySet()) {
                if (accountInMap.getValue().getPhone().equals(acc.getPhone())
                        && accountInMap.getValue().getPassword().equals(acc.getPassword())
                        && accountInMap.getValue().getStatus().equals("Active")) {
                    account = accountInMap.getValue();
                }
            }
            return account;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static Account getAccount(String phone) {
        try {
            Account account = null;
            for (Map.Entry<String, Account> accountInMap : ACCOUNTS.entrySet()) {
                if (accountInMap.getValue().getPhone().equals(phone)) {
                    account = (Account) accountInMap.getValue();
                }
            }
            return account;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }

    }

    public static Long countAccount() {
        try {
            return Long.valueOf(ACCOUNTS.size());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static void loadAccount() {
        try {
            Session session = HibernateUtil.getSession();
            List<Account> account = session.createCriteria(Account.class).list();
            for (Account acc : account) {
                ACCOUNTS.put(acc.getId().toString(), acc);
            }
            session.flush();
            session.close();
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
        }
    }

    public static List<Account> getAllAccount() {
        List<Account> account = new ArrayList<>();
        try {
            for (Map.Entry<String, Account> accountInMap : ACCOUNTS.entrySet()) {
                account.add(accountInMap.getValue());
            }
            return account;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static void resetPasswords(List<Account> accounts) {
        try {
            for (Account account : accounts) {
                account.setPassword(generateNewPassword());
                Sender s = new Sender("Murwego rwo kongera ubudahangarwa bwa konti yawe muri GISA Investment,"
                        + "uhawe ijambo ry'ibanga rishya ariryo: " + account.getPassword() + " Hindura ijambo ry'ibanga unyunze muri konti.");
                if (update(account)) {
                    Sender.sendRouteSMS(account.getPhone(), s);
                }

            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

    public static boolean resetPassword(Account account) {
        boolean valid = false;
        try {
            Sender s = new Sender("Mukiriya mwiza, musabye guhindura ijambo ry'ibanga rya konti yawe muri GISA Investment."
                    + "Uhawe ijambo ry'ibanga rishya ariryo: " + account.getPassword());          

            if (update(account)) {
                Sender.sendRouteSMS(account.getPhone(), s);
                valid = true;
            }

        } catch (Exception e) {
            LOGGER.error(e.toString());
            valid = false;
        }
        return valid;
    }

    public static boolean resetPasswordSingle(Account account) {
        boolean valid = false;
        try {
            account.setPassword(generateNewPassword());
            Sender s = new Sender("Murwego rwo kongera ubudahangarwa bwa konti yawe muri GISA Investment,"
                    + "Uhawe ijambo ry'ibanga rishya ariryo: " + account.getPassword());
            LOGGER.error(account.getPassword());
            if (update(account)) {
                Sender.sendRouteSMS(account.getPhone(), s);
                valid = true;
            }

        } catch (Exception e) {
            LOGGER.error(e.toString());
            valid = false;
        }
        return valid;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        AccountDAOimp.LOGGER = LOGGER;
    }
}
//250783453253 rd9vmk
//250783453252 9406au
