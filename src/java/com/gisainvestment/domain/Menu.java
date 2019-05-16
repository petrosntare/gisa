/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author McP
 */
@Entity(name="menu")
public class Menu {

    @Id
    private int id;
    private String code;
    private String name;
    private String parent;
    private static final Map<String, Menu> menus = new HashMap<>();

    public Menu() {
    }

    public Menu(String code) {
        this.code = code;
    }

    public Menu(int id, String code, String name, String parent) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", code=" + code + ", name=" + name + ", parent=" + parent + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final Menu other = (Menu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
