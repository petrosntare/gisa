/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import static com.gisainvestment.DAO.SmsService.LOG;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.domain.Subscriber;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mcpaul
 */
public class SubscriberDAO {

    public static boolean create(Subscriber subs) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(subs);
            session.getTransaction().commit();
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
            return true;
        } catch (Exception e) {
            LOG.error(e.toString());
            return false;
        }
    }

    public static Long countSubscriber() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String queryString = "select count(*) from subscriber";
        Query query = session.createQuery(queryString);
        Object queryResult = query.uniqueResult();
        Long user = (Long) queryResult;
        session.getTransaction().commit();
        session.close();
        return user + 1;
    }
}
