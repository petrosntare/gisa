/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
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
@Entity(name = "account")
public class Account implements Serializable {

    @Id
    private Long id;
    private String reference;
    private String phone;
    private String password;
    private String identification;
    private Date startTime;
    private Date endTime;
    private String status;

    public Account(Long id, String reference, String phone, String password, String identification, Date startTime, Date endTime, String status) {
        this.id = id;
        this.reference = reference;
        this.phone = phone;
        this.password = password;
        this.identification = identification;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public static String generateReferenceNumber() {
        Random random = new Random();
        UUID randomUUID = UUID.randomUUID();
        return "REF-" + randomUUID.toString().replaceAll("-", "" + random.nextInt(9)).substring(0, 9).toUpperCase();
    }

    public static String generateNewPassword() {
        return new BigInteger(130, new SecureRandom()).toString(32).substring(0, 6);
    }

    public Account(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", reference=" + reference + ", phone=" + phone + ", password=" + password + ", identification=" + identification + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.reference);
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
        final Account other = (Account) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return true;
    }

}
