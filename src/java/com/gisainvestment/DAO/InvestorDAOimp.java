/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.domain.Investor;

/**
 *
 * @author McP
 */

public class InvestorDAOimp {

    
    
    
    public static void create(Investor investor) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(investor);
        session.getTransaction().commit();
//        HibernateUtil.getSession().getSessionFactory().close();
        session.close();        
    }

    public static Long countInvestor() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String queryString = "select count(*) from Investor";
        Query query = session.createQuery(queryString);
        Object queryResult = query.uniqueResult();
        Long user = (Long) queryResult;
        session.getTransaction().commit();
        session.close();        
        return user;
    }

    public static Investor checkPresence(Investor investor) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String queryString = "from Investor where phone=:phone";
        Query query = session.createQuery(queryString);
        query.setString("phone", investor.getPhone());
        Object queryResult = query.uniqueResult();
        Investor invest = (Investor) queryResult;
        session.getTransaction().commit();
        session.close();        
        return invest;
    }
    public static Investor checkPhoneOwner(String Phone) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String queryString = "from Investor where phone=:phone";
        Query query = session.createQuery(queryString);
        query.setString("phone", Phone);
        Object queryResult = query.uniqueResult();
        Investor invest = (Investor) queryResult;
        session.getTransaction().commit();
        session.close();        
        return invest;
    }

    public static List<Investor> getAllInvestor() {        
        Session session = HibernateUtil.getSession();
        List<Investor> investor = session.createCriteria(Investor.class).list();
        HibernateUtil.getSession().getSessionFactory().close();
        session.close();        
        return investor;
    }
    
}
