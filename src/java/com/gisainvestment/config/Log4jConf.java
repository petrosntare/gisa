/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author mcpaul
 */
public class Log4jConf {

    private static Logger LOGGER = Logger.getLogger(Log4jConf.class);
    public void loadLog4j() {
        try {
            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("logs.properties"));
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        Log4jConf.LOGGER = LOGGER;
    }
}
