/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import org.hibernate.Session;
import com.gisainvestment.config.HibernateUtil;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Users;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author McP
 */
public class UserDAO {

    private static Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final LinkedHashMap<String, Users> USERS = new LinkedHashMap<>();

    static {
        new Log4jConf().loadLog4j();
        loadUsers();
    }

    public static Users checkLogin(Users u) {
        try {
            Users user = new Users();
            for (Map.Entry<String, Users> userInMap : USERS.entrySet()) {
                if (userInMap.getValue().getUsername().equals(u.getUsername())
                        && userInMap.getValue().getPassword().equals(u.getPassword())) {
                    user = (Users) userInMap.getValue();
                }
            }
            return user;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static void loadUsers() {
        try {
            Session session = HibernateUtil.getSession();
            List<Users> users = session.createCriteria(Users.class).list();
            for (Users user : users) {
                USERS.put(user.getId() + "", user);
            }
            session.flush();
            session.close();
        } catch (HibernateException e) {
            LOGGER.error(e.toString());
        }
    }
    public static int countUsers() {
        try {
            return USERS.size();
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return 0;
        }
    }
    public static List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        try {
            for (Map.Entry<String, Users> usersInMap : USERS.entrySet()) {
                users.add(usersInMap.getValue());
            }
            return users;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
    }
//=============================GETTER AND SETTER================================
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        UserDAO.LOGGER = LOGGER;
    }

}
