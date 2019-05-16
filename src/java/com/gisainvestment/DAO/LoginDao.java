/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.gisainvestment.domain.Users;

/**
 *
 * @author McP
 */

public class LoginDao extends ActionSupport implements SessionAware, ModelDriven {

    private String username;
    private String password;
    private String fmsg;
    private Users user;
    private Map<String, Object> session;

    public String loginCheck() {

        user = UserDAO.checkLogin(new Users(username, password));
        if (user == null) {
            fmsg = "* Username or Password incorrect";
            return "fail";
        } else if (user.getPermission().equalsIgnoreCase("ADMINISTRATOR")) {
            //session.put("ADMINISTRATOR", user);
            return "dashboard_admin";
        } else if (user.getPermission().equalsIgnoreCase("USER")) {
            //session.put("USER", user);
            return "dashboard_user";
        } else {
            return "fail";
        }
    }
    public String login() {

        user = UserDAO.checkLogin(new Users(username, password));
        if (user == null) {
            addActionMessage("Username or Password incorrect");
            return "fail";
        } else if (user.getPermission().equalsIgnoreCase("ADMINISTRATOR")) {
            session.put("ADMINISTRATOR", user);
            return "dashboard_admin";
        } else if (user.getPermission().equalsIgnoreCase("USER")) {
            session.put("USER", user);
            return "dashboard_user";
        } else {
            return "fail";
        }
    }

    /* start getter and setter*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFmsg() {
        return fmsg;
    }

    public void setFmsg(String fmsg) {
        this.fmsg = fmsg;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    /* end getter and setter*/

    @Override
    public Object getModel() {
        return user;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /*end implementation method*/
}
