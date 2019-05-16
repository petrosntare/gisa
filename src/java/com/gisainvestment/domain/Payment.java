/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mcpaul
 */
@Entity(name = "payment")
public class Payment implements Serializable {

    @Id
    private int id;
    private String ticket;
    private Date due_date;
    private String approved_by;
    private String status;
    private double tax;

    public Payment(int id, String ticket, Date due_date, String approved_by, String status, double tax) {
        this.id = id;
        this.ticket = ticket;
        this.due_date = due_date;
        this.approved_by = approved_by;
        this.status = status;
        this.tax = tax;
    }

    

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", ticket=" + ticket + ", due_date=" + due_date + ", approved_by=" + approved_by + ", status=" + status + ", tax=" + tax + '}';
    }


}
