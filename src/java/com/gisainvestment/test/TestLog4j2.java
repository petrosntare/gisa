/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author mcpaul
 */
public class TestLog4j2 {

    private static Logger LOGGER = Logger.getLogger(TestLog4j2.class);

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        TestLog4j2.LOGGER = LOGGER;
    }

    static {
        new TestLog4j2().loadLog4j();
    }

    public void loadLog4j() {
        try {
            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

}
