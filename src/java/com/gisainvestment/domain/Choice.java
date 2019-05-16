/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author McP
 */
@Entity(name="choice")
public class Choice {

    @Id
    private int id;
    private int menuId;
    private String menucode;
    private String question;

    public Choice() {
    }

    public Choice(String question) {
        this.question = question;
    }

    public Choice(int id, int menuId, String question) {
        this.id = id;
        this.menuId = menuId;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Choice{" + "id=" + id + ", menuId=" + menuId + ", menucode=" + menucode + ", question=" + question + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.menucode);
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
        final Choice other = (Choice) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.menucode, other.menucode)) {
            return false;
        }
        return true;
    }

}
