/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action.interceptor;

import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Users;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class AuthenticationInterceptor implements Interceptor {

    private static Logger LOGGER = Logger.getLogger(AuthenticationInterceptor.class);

    static {
        new Log4jConf().loadLog4j();
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        AuthenticationInterceptor.LOGGER = LOGGER;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        Users users = (Users) session.get("user_in_session");
        if (users == null) {
            return "loginPage";
        } else {
            return actionInvocation.invoke();
        }
    }
}
