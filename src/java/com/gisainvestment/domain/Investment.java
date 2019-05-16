/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import com.gisainvestment.utility.SimpleDateConverter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author McP
 */
@Entity(name = "investment")
public class Investment implements Serializable, Comparable<Investment> {

    @Id
    private Long id;
    private String reference;
    private String ticket;
    private Date inv_date;
    private double amount;
    private Date inv_returnDate;
    private double receivableAmount;
    private double balance;
    private String source;
    private String transactionStatus;
    private String status;
    private double in_sys;
    private double in_circulation;
    private double in_investment_world;

    public Investment() {
    }

    public Investment(Long id, String reference, String ticket, Date inv_date, double amount, Date inv_returnDate, double receivableAmount, double balance, String source, String transactionStatus, String status, double in_sys, double in_circulation, double in_investment_world) {
        this.id = id;
        this.reference = reference;
        this.ticket = ticket;
        this.inv_date = inv_date;
        this.amount = amount;
        this.inv_returnDate = inv_returnDate;
        this.receivableAmount = receivableAmount;
        this.balance = balance;
        this.source = source;
        this.transactionStatus = transactionStatus;
        this.status = status;
        this.in_sys = in_sys;
        this.in_circulation = in_circulation;
        this.in_investment_world = in_investment_world;
    }

    public Investment(String ticket, double amount, double receivableAmount, String source) {
        this.ticket = ticket;
        this.amount = amount;
        this.receivableAmount = receivableAmount;
        this.source = source;
    }

    public static String generateTicketNumber() {
        Random random = new Random();
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "" + random.nextInt(9)).substring(0, 12).toUpperCase();
    }
    //++++++++++++++++++++++++++GETTER AND SETTER++++++++++++++++++++++++++++

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getInv_date() {
        return inv_date;
    }

    public void setInv_date(Date inv_date) {
        this.inv_date = inv_date;
    }

    public Date getInv_returnDate() {
        return inv_returnDate;
    }

    public void setInv_returnDate(Date inv_returnDate) {
        this.inv_returnDate = inv_returnDate;
    }

    public double getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(double receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getIn_sys() {
        return in_sys;
    }

    public void setIn_sys(double in_sys) {
        this.in_sys = in_sys;
    }

    public double getIn_circulation() {
        return in_circulation;
    }

    public void setIn_circulation(double in_circulation) {
        this.in_circulation = in_circulation;
    }

    public double getIn_investment_world() {
        return in_investment_world;
    }

    public void setIn_investment_world(double in_investment_world) {
        this.in_investment_world = in_investment_world;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.reference);
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
        final Investment other = (Investment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Investment{" + "id=" + id + ", ticket=" + ticket + ", reference=" + reference + ", inv_date=" + inv_date + ", amount=" + amount + ", inv_returnDate=" + inv_returnDate + ", receivableAmount=" + receivableAmount + ", balance=" + balance + ", source=" + source + ", transactionStatus=" + transactionStatus + ", status=" + status + ", in_sys=" + in_sys + ", in_circulation=" + in_circulation + ", in_investment_world=" + in_investment_world + '}';
    }

    

    @Override
    public int compareTo(Investment o) {
        return this.inv_date.compareTo(o.inv_date);
    }

}
