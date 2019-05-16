/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mcpaul
 */
@XmlRootElement
@Entity(name = "sms_message")
public class SmsMessage implements Serializable {

    @Id
    private long id;
    private String src;
    private String dest;
    private String message;
    private String wait;
    private String contractId;
    private String code;
    private String descr;
    private String status;

    public SmsMessage() {
    }

    public SmsMessage(long id, String src, String dest, String message, String wait, String contractId, String code, String descr, String status) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.message = message;
        this.wait = wait;
        this.contractId = contractId;
        this.code = code;
        this.descr = descr;
        this.status = status;
    }

   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SmsMessage{" + "id=" + id + ", src=" + src + ", dest=" + dest + ", message=" + message + ", wait=" + wait + ", contractId=" + contractId + ", code=" + code + ", descr=" + descr + ", status=" + status + '}';
    }


}
