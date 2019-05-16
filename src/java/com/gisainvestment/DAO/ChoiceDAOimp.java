/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Choice;
import com.gisainvestment.domain.Menu;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author McP
 */
public class ChoiceDAOimp {
    
    private static final LinkedHashMap<String, Choice> CHOICES = new LinkedHashMap<>();
    static Logger LOG = Logger.getLogger(ChoiceDAOimp.class.getName());
    
    static {
        loadChoices();
        new Log4jConf().loadLog4j();
        
    }
    
    public static void loadChoices() {
        try {
            Session session = HibernateUtil.getSession();
            List<Choice> choicel = session.createCriteria(Choice.class).list();
            for (Choice choiceInList : choicel) {
                CHOICES.put(choiceInList.getId() + "", choiceInList);
            }
            HibernateUtil.getSession().getSessionFactory().close();
            session.close();
        } catch (HibernateException e) {
            LOG.error(e.toString());
        }
        
    }
    
    public void create(Choice choice) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(choice);
        session.getTransaction().commit();
        HibernateUtil.getSession().getSessionFactory().close();
        session.close();
        CHOICES.put(choice.getId() + "", choice);
        
    }
    
   
    public List<Choice> getChoiceList() {
        
        List<Choice> choice = new ArrayList<>();
        for (Map.Entry<String, Choice> choiceInMap : CHOICES.entrySet()) {
            choice.add(choiceInMap.getValue());
        }
        
        return choice;
        
    }
    
    public List<Choice> getChoiceByMenu(Menu menu) {
        List<Choice> choice = new ArrayList<>();
        for (Map.Entry<String, Choice> choiceInMap : CHOICES.entrySet()) {
            if (choiceInMap.getValue().getMenucode().equalsIgnoreCase(menu.getCode())) {
                choice.add(choiceInMap.getValue());
            }
        }
        return choice;
    }
    
    public List<Choice> getChoicesByMenu(Menu menu) {
        List<Choice> choice = new ArrayList<>();
        for (Map.Entry<String, Choice> choiceInMap : CHOICES.entrySet()) {
            if (choiceInMap.getValue().getMenucode().equalsIgnoreCase(menu.getCode())) {
                choice.add(choiceInMap.getValue());
            }
        }
        
        return choice;
    }
    
    public static List<Choice> getChoicesByMenu(Menu menu, String phone) {
        List<Choice> choice = new ArrayList<>();
        for (Map.Entry<String, Choice> choiceInMap : CHOICES.entrySet()) {
            if (choiceInMap.getValue().getMenucode().equalsIgnoreCase(menu.getCode())) {
                if (phone.startsWith("25078") && choiceInMap.getValue().getQuestion().contains("MTN")) {
                    choice.add(choiceInMap.getValue());
                } else if (phone.startsWith("25072") && choiceInMap.getValue().getQuestion().contains("TIGO")) {
                    choice.add(choiceInMap.getValue());
                } else if (phone.startsWith("25073") && choiceInMap.getValue().getQuestion().contains("AIRTEL")) {
                    choice.add(choiceInMap.getValue());
                } else if (choiceInMap.getValue().getQuestion().contains("BANKI ya KIGALI")) {
                    choice.add(choiceInMap.getValue());
                } else if (choiceInMap.getValue().getQuestion().contains("EQUITY")) {
                    choice.add(choiceInMap.getValue());
                }
            }
        }
        
        return choice;
    }
    
}
