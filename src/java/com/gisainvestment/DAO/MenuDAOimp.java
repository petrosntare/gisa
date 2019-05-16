/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import java.util.*;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Menu;

/**
 *
 * @author McP
 */

public class MenuDAOimp {

    private static final Map<String, Menu> menus = new HashMap<>();

    static {
        new Log4jConf().loadLog4j();
        loadmenu();
    }

    public static void loadmenu() {

        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("select * from menu").list();
        List<Menu> menuL = session.createCriteria(Menu.class).list();
        for (Menu menuinlist : menuL) {
            menus.put(menuinlist.getCode(), menuinlist);
        }
        HibernateUtil.getSession().getSessionFactory().close();
        session.close();
    }

    public List<Menu> getMenuList() {
        //Session session = HibernateUtil.getSession();
        List<Menu> menulist = new ArrayList<>(); //= session.createCriteria(Menu.class).list();
        for (Map.Entry< String, Menu> m : menus.entrySet()) {
            Menu menu = m.getValue();
            menulist.add(menu);
        }
        //HibernateUtil.getSession().getSessionFactory().close();
        //session.close();
        return menulist;
    }

    public void create(Menu menu) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(menu);
        session.getTransaction().commit();
        HibernateUtil.getSession().getSessionFactory().close();
        session.close();
        menus.put(menu.getCode(), menu);
    }

    public Menu getRootMenu() {
//        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("FROM Menu where parent= null").list();
        Menu menu = new Menu();
//        for (Menu menuinlist : menuL) {
//            menu = menuinlist;
//        }
//        System.out.println("getRootMenu() Executed !!" + menu.getCode());
//        HibernateUtil.getSession().getSessionFactory().close();
//        session.close();

        for (Map.Entry<String, Menu> menuInMap : menus.entrySet()) {
            if (menuInMap.getValue().getParent() == null) {
                menu = menuInMap.getValue();
                //System.out.println("Parrent has null " + menuInMap.getValue());
            }
        }

        return menu;
    }

    public List<Menu> getMenu(Menu menu) {
//        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("select menu FROM Menu menu where menu.code=:code").setString("code", menu.getCode()).list();
//        HibernateUtil.getSession().getSessionFactory().close();
//        session.close();
//        
        List<Menu> menuL = new ArrayList<>();
        for (Map.Entry<String, Menu> menuInMap : menus.entrySet()) {
            if (menu.getCode().equalsIgnoreCase(menuInMap.getValue().getCode())) {
                menuL.add(menuInMap.getValue());
            }
        }

        return menuL;
    }

    public Menu getMenuByCode(String code) {
//        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("select menu FROM Menu menu where menu.code=:code").setString("code", code).list();
//        Menu menu = (Menu) session.get(Menu.class, menuL.get(0).getId());
//        HibernateUtil.getSession().getSessionFactory().close();
//        session.close();

        Menu menu = null;
        for (Map.Entry<String, Menu> menuInMap : menus.entrySet()) {
            if (code.equalsIgnoreCase(menuInMap.getValue().getCode())) {
                menu = menuInMap.getValue();
            }
        }
        return menu;
    }

    public Menu getMenuByParent(Menu menu) {
//        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("select menu FROM Menu menu where menu.code=:code").setString("code", menu.getParent()).list();
//        Menu menudb = (Menu) session.get(Menu.class, menuL.get(0).getId());
//        HibernateUtil.getSession().getSessionFactory().close();
//        session.close();
//        
        Menu m = null;
        for (Map.Entry<String, Menu> menuInMap : menus.entrySet()) {
            if (menu.getParent().equalsIgnoreCase(menuInMap.getValue().getCode())) {
                m = menuInMap.getValue();
            }
        }
        return m;
    }

    public List<Menu> getChild(Menu child) {
//        Session session = HibernateUtil.getSession();
//        List<Menu> menuL = session.createQuery("select content FROM Menu menu where menu.parent=:parent").setString("parent", child.getCode()).list();
//        HibernateUtil.getSession().getSessionFactory().close();
//        session.close();
//        
        List<Menu> menu = null;
        for (Map.Entry<String, Menu> menuInMap : menus.entrySet()) {
            if (child.getCode().equalsIgnoreCase(menuInMap.getValue().getParent())) {
                menu.add(menuInMap.getValue());
            }
        }
        return menu;
    }

}
