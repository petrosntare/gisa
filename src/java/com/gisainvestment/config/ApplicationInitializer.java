/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.config;

import com.gisainvestment.DAO.AccountDAOimp;
import com.gisainvestment.DAO.ChoiceDAOimp;
import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.DAO.MenuDAOimp;
import com.gisainvestment.DAO.PaymentDAO;
import com.gisainvestment.DAO.UserDAO;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class ApplicationInitializer implements ServletContextListener {

    private static Logger LOGGER = Logger.getLogger(ApplicationInitializer.class);

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        ApplicationInitializer.LOGGER = LOGGER;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.error(">>>>>>>>>>>>>Start initializing application DAO...<<<<<<<<<<<<<<<<");
//        ServletContext context = sce.getServletContext();
//        context.setAttribute("account", new AccountDAOimp());
//        context.setAttribute("choise", new ChoiceDAOimp());
//        context.setAttribute("investment", new InvestmentDAOimp());
//        context.setAttribute("menu", new MenuDAOimp());
//        context.setAttribute("payment", new PaymentDAO());
//        context.setAttribute("user", new UserDAO());
        LOGGER.error(">>>>>>>>>>>>>End initializing application DAO...<<<<<<<<<<<<<<<<");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
