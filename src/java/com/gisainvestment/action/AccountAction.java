/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action;

import com.gisainvestment.DAO.AccountDAOimp;
import com.gisainvestment.domain.Account;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class AccountAction extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(AccountAction.class);
    private Account account = new Account();
    private String phone;
    private List<Account> accounts = new ArrayList<>();

    public String resetSingle() {
        try {
            account = AccountDAOimp.getAccount(phone);
            AccountDAOimp.resetPasswordSingle(account);
            accounts = AccountDAOimp.getAllAccount();
            addActionMessage("Well done! account password has been updated successfully.");
        } catch (Exception e) {
            LOGGER.error(e.toString());
            addActionError("Fail to update password.");
        }
        return "resetSingle";

    }

    public String resetAll() {
        try {
            AccountDAOimp.resetPasswords(AccountDAOimp.getAllAccount());
            accounts = AccountDAOimp.getAllAccount();
            addActionMessage("Well done! All accounts passwords has been updated successfully.");
        } catch (Exception e) {
            LOGGER.error(e.toString());
            addActionError("Fail to update passwords.");
        }
        return "reset";
    }

    public String getAll() {
        try {
            accounts = AccountDAOimp.getAllAccount();
            return SUCCESS;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ERROR;
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        AccountAction.LOGGER = LOGGER;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
