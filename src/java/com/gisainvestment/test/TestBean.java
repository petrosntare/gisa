package com.gisainvestment.test;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gisainvestment.test;
//
//import javax.faces.bean.ManagedBean;
//import com.gisainvestment.DomainDAO.UserDAO;
//import com.gisainvestment.domain.User;
//
///**
// *
// * @author McP
// */
////@ManagedBean(name = "loginbean")
//public class TestBean {
//
//    private String username;
//    private String password;
//    private String fmsg;
//    private User user;
//
//    public String loginCheck() {
//        
//        user = UserDAO.checkLogin(new User(username, password));
//        if (user == null) {
//            fmsg="Username or Password incorrect";
//            return "fail";
//        } else if (user.getPermission().equalsIgnoreCase("ADMINISTRATOR")) {
//            //session.put("ADMINISTRATOR", user);
//            return "dashboard_admin";
//        } else if (user.getPermission().equalsIgnoreCase("USER")) {
//            //session.put("USER", user);
//            return "dashboard_user";
//        } else {
//            return "fail";
//        }
//    
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getFmsg() {
//        return fmsg;
//    }
//
//    public void setFmsg(String fmsg) {
//        this.fmsg = fmsg;
//    }
//
//   
//}
