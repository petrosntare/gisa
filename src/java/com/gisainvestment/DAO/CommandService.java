/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.COMMAND;
import com.gisainvestment.domain.Choice;
import java.util.ArrayList;
import java.util.List;
import com.gisainvestment.home.UssdFlow;
import com.gisainvestment.home.UssdSession;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class CommandService {

    static Logger LOG = Logger.getLogger(CommandService.class.getName());

    static {
        new Log4jConf().loadLog4j();
    }

    public COMMAND getCommand(COMMAND command) {

        COMMAND com = null;
        try {
            String input = command.getINPUT();
            String phoneNumber = command.getMSISDN();
            String sessionId = command.getSESSIONID();
//            LOG.error("session ID: " + sessionId);
//            LOG.error("Phone: " + phoneNumber);
            //------------------------

            List<String> menus = new ArrayList<>();

            String msg = "";
            String qn = "";
            String freeflow = "";
            String newrequest = "";

            UssdFlow flow = (UssdFlow) UssdSession.getFlow(sessionId);
            if (flow == null) {

                flow = new UssdFlow(phoneNumber, sessionId);
                UssdSession.setFlow(flow);

            } else {
                flow.setInput(input);
            }
            if (flow != null) {
                msg = flow.getMessage();
                qn = flow.getQuestion();
                freeflow = flow.getFREEFLOW();
                newrequest = flow.getFREEFLOW();

                List<Choice> choices = flow.getChoices1();

                int i = 0;
                if (choices != null) {
                    for (Choice item : choices) {
                        menus.add("^" + (choices.indexOf(item) + 1) + ". " + item.getQuestion());
//                        LOG.error("==== 3 "+item);
                    }
                }
                if (msg != null && qn == null) {
                    com = new COMMAND(command.getMSISDN(), command.getSESSIONID(), freeflow, msg, newrequest, menus);
                } else if (qn != null && msg == null) {
                    com = new COMMAND(command.getMSISDN(), command.getSESSIONID(), freeflow, qn, newrequest, menus);
                } else if (qn != null && msg != null) {
                    com = new COMMAND(command.getMSISDN(), command.getSESSIONID(), freeflow, qn + " " + msg, newrequest, menus);
                }

            }
            return com;
        } catch (Exception e) {
            LOG.error("Error from " + CommandService.class.getName() + " in getCommand()\n " + e);
            return com;
        }
    }

    /*
        This function getcommandTest create a list of a static strings 
        then create the command object to hold many values along with the created list
     */
    public COMMAND getcommandTest() {
        List<String> menus = new ArrayList<>();
        menus.add("1. KINYARWANDA");
        menus.add("2. ENGLISH");
        menus.add("3. FRANCAIS");

        COMMAND com = new COMMAND("250788312609", "14666725080935241", "FREEFLOW", "MESSAGE", "NEWREQUEST", menus);
        return com;
    }

}
