/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.home;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LTD
 */
public class UssdSession {
    private static final Map<String, UssdFlow> session = new HashMap<String, UssdFlow>();
    
    private void UssdSession() {
    }

    public static UssdFlow getFlow(String sessionId) {
        return session.get(sessionId);
    }

    public static void setFlow(UssdFlow flow) {
        session.put(flow.getSessionId(), flow);
    }

    public static void removeFlow(UssdFlow flow) {
        session.remove(flow.getSessionId());
    }
}
