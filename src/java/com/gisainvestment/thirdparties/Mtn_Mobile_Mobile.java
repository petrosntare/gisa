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
public class Mtn_Mobile_Mobile {

    private String phoneNumber;
    private String names;
    private String idCard;
    private double balance;
    public static final Map<String, Mtn_Mobile_Mobile> accounts = new HashMap<>();

    static {
        initializeAccounts();
    }

    public static String placeMoney(String phoneNumber, double amount) {
        Mtn_Mobile_Mobile clientAccount = getAccount(phoneNumber);
        if (clientAccount.getBalance() > amount) {
            Mtn_Mobile_Mobile gisa = accounts.get("0783312146");
            gisa.setBalance(amount + gisa.getBalance());
            clientAccount.setBalance(clientAccount.getBalance() - amount);
            return " Dear " + clientAccount.getNames() + " the amount of" + amount + " was placed. Your new balance is =" + clientAccount.getBalance();
        } else {
            return "Dear " + clientAccount.getNames() + " your balance is =" + clientAccount.getBalance() + " and is insufficient";
        }
    }

    public static Mtn_Mobile_Mobile getAccount(String phoneNumber) {
        return accounts.get(phoneNumber);
    }

    public static void initializeAccounts() {
        Mtn_Mobile_Mobile m1 = new Mtn_Mobile_Mobile("0788683616", "Yvan GAKUBA", "1199180003816784", 50000);
        accounts.put(m1.getPhoneNumber(), m1);
        Mtn_Mobile_Mobile m2 = new Mtn_Mobile_Mobile("078453253", "Jimmy Jean Paul MUPAGASI", "1198880003816778", 10000);
        accounts.put(m2.getPhoneNumber(), m2);
        Mtn_Mobile_Mobile gisa = new Mtn_Mobile_Mobile("0783312146", "Patrick MUGAMBI", "1198880003816778", 1000000);
        accounts.put(gisa.getPhoneNumber(), gisa);
    }

    public Mtn_Mobile_Mobile() {
    }

    public Mtn_Mobile_Mobile(String phoneNumber, String names, String idCard, double balance) {
        this.phoneNumber = phoneNumber;
        this.names = names;
        this.idCard = idCard;
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.phoneNumber);
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
        final Mtn_Mobile_Mobile other = (Mtn_Mobile_Mobile) obj;
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return balance+" ";
    }

}
