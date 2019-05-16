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
import javax.persistence.Temporal;

/**
 *
 * @author mcpaul
 */
@Entity(name = "subscriber")
public class Subscriber implements Serializable {

    @Id
    private long id;
    private String telephone;
    private String status;
    private Date subsciption_date;

    public Subscriber(long id, String telephone, String status, Date subsciption_date) {
        this.id = id;
        this.telephone = telephone;
        this.status = status;
        this.subsciption_date = subsciption_date;
    }

    public Subscriber() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubsciption_date() {
        return subsciption_date;
    }

    public void setSubsciption_date(Date subsciption_date) {
        this.subsciption_date = subsciption_date;
    }

    @Override
    public String toString() {
        return "Subscriber{" + "id=" + id + ", telephone=" + telephone + ", status=" + status + ", subsciption_date=" + subsciption_date + '}';
    }

}
