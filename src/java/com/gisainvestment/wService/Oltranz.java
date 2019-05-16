/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.wService;

import com.gisainvestment.DAO.CommandService;
import com.gisainvestment.domain.COMMAND;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mcpaul
 */
//dedicated to oltranz
@Path("/oltranz")
public class Oltranz {

    CommandService command = new CommandService();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public COMMAND getcommandTest() {
        return command.getcommandTest();
    }


//    @GET
//    @Path("/app/core")
//    @Produces(MediaType.APPLICATION_XML)
//    public List<COMMAND> getCommand(
//            @QueryParam("INPUT") String INPUT,
//            @QueryParam("MSISDN") String MSISDN,
//            @QueryParam("SESSIONID") String SESSIONID,
//            @QueryParam("AGENTID") String AGENTID,
//            @QueryParam("NEWREQUEST") String NEWREQUEST,
//            @QueryParam("SPID") String SPID,
//            @QueryParam("FROMMULTIUSSD") String FROMMULTIUSSD,
//            @QueryParam("Resume") String Resume
//    ) {
//
//        COMMAND com = new COMMAND();
//        com.setINPUT(INPUT);
//        com.setMSISDN(MSISDN);
//        com.setSESSIONID(SESSIONID);
//        com.setAGENTID(AGENTID);
//        com.setNEWREQUEST(NEWREQUEST);
//        com.setSPID(SPID);
//        com.setFROMMULTIUSSD(FROMMULTIUSSD);
//        com.setRESUME(false);
//        System.out.println("CLIENT INPUT REQUEST-->GISA=======\n" + com);
//        System.out.println("\n=======\n");
//        System.out.println("REAL RESPONSE -->GISA=======\n" + command.getCommand());
//        System.out.println("\n----------------------------");
//        
//        return command.getCommand(com);
//    }

}
