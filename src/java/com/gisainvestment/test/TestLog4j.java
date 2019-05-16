/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.test;

import com.gisainvestment.DAO.AccountDAOimp;
import com.gisainvestment.config.Log4jConf;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class TestLog4j {
    
    private static Logger LOGGER = Logger.getLogger(TestLog4j.class);
    private final TestLog4j2 testLog4j2 = new TestLog4j2();
    static {
        new Log4jConf().loadLog4j();
    }
    public static Logger getLOGGER() {
        return LOGGER;
    }
    
    public static void setLOGGER(Logger LOGGER) {
        TestLog4j.LOGGER = LOGGER;
    }
    
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 3; i++) {
                AccountDAOimp.getAllAccount();
                LOGGER.error("\n====== i=" + i+""+AccountDAOimp.getAllAccount());
            }
            
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }
}
