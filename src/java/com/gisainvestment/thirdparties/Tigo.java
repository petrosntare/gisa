/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.thirdparties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author McP
 */
public class Tigo {

    private String idNumber;
    private String name;
    private String phone;
    private double balance;
    private String password;
    private static final Map<String, Tigo> TigoAccounts = new HashMap<>();

    static {
        init();
    }

    public static void init() {
        Tigo t1 = new Tigo("1234567890123456", "Fabrice DUSINE", "0724109822", 10000, "passwd1");
        TigoAccounts.put(t1.phone, t1);
        Tigo t2 = new Tigo("1234567890123456", "Olivier HABINEZA", "0728546013", 50000, "passwd2");
        TigoAccounts.put(t2.phone, t2);
        Tigo t3 = new Tigo("1234567890123456", "Jeannette UWASE", "0728000001", 20000, "passwd3");
        TigoAccounts.put(t3.phone, t3);
        Tigo t4 = new Tigo("1234567890123456", "GISA INVESTMENT", "0723453253", 20000, "passwd4");
        TigoAccounts.put(t4.phone, t4);
    }

    public static Tigo getTigoAccount(String phone) {
        return TigoAccounts.get(phone);
    }

    public static String placeMoney(String phone, double amount, String passwrd) {
        Tigo tigo = getTigoAccount(phone);
        if (!tigo.getPassword().equals(passwrd)) {
            return "Dear " + tigo.getName() + " your password is not correct. please try again latter.";
        } else if (tigo.getBalance() < amount) {
            return "Dear " + tigo.getName() + " your balance is = " + tigo.getBalance() + " and is insufficient";
        } else {
            Tigo gisa = getTigoAccount("0723453253");
            gisa.setBalance(gisa.getBalance() + amount);
            tigo.setBalance(tigo.getBalance() - amount);
            return " Dear " + tigo.getName() + " the amount of " + amount + " was placed. Your new balance is = " + tigo.getBalance();
        }
    }

    //===============================================
    public Tigo() {
    }

    public Tigo(String idNumber, String name, String phone, double balance, String password) {
        this.idNumber = idNumber;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tigo other = (Tigo) obj;
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tigo{" + "idNumber=" + idNumber + ", name=" + name + ", phone=" + phone + ", balance=" + balance + '}';
    }

}
