/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.wService;

import com.gisainvestment.DAO.CommandService;
import com.gisainvestment.domain.COMMAND;
import java.io.StringWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXB;

/**
 * REST Web Service
 *
 * @author mcpaul
 */
@Path("/api")
public class UssdResource {

    CommandService command = new CommandService();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UssdResource
     */
    public UssdResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.gisainvestment.wService.UssdResource
     *
     * @return an instance of java.lang.String
     */
    @Path("/ussd")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        StringWriter writer = new StringWriter();
        JAXB.marshal(command.getcommandTest(), writer); // marshal[Convert] Command object to XML
        return writer.toString(); // return XML as String
    }

    @POST
    @Path("/ussd/core/{INPUT}/{MSISDN}/{SESSIONID}/{AGENTID}/{NEWREQUEST}/{SPID}/{FROMMULTIUSSD}/{Resume}")
    @Produces(MediaType.APPLICATION_XML)
    public String postCommandXml(
            @PathParam("MSISDN") String MSISDN,
            @PathParam("SESSIONID") String SESSIONID,
            @PathParam("NEWREQUEST") String NEWREQUEST,
            @PathParam("AGENTID") String AGENTID,
            @PathParam("INPUT") String INPUT,
            @PathParam("SPID") String SPID,
            @PathParam("FROMMULTIUSSD") String FROMMULTIUSSD,
            @PathParam("Resume") String Resume
    ) {

        System.out.println("===========================================");
        System.out.println("INPUT " + INPUT);
        System.out.println("MSISDN " + MSISDN);
        System.out.println("SESSIONID " + SESSIONID);
        System.out.println("AGENTID " + AGENTID);
        System.out.println("NEWREQUEST " + NEWREQUEST);
        System.out.println("SPID " + SPID);
        System.out.println("FROMMULTIUSSD " + FROMMULTIUSSD);
        System.out.println("===========================================");
        COMMAND com = new COMMAND(MSISDN, SESSIONID, NEWREQUEST, AGENTID, INPUT, SPID, FROMMULTIUSSD, false);

        System.out.println("\nCLIENT INPUT REQUEST-->GISA=======\n" + com);
        System.out.println("\n=======\n");

        COMMAND response = command.getCommand(com);
        System.out.println("\nREAL RESPONSE -->GISA=======\n" + response);
        System.out.println("\n----------------------------");

        StringWriter writer = new StringWriter();
        JAXB.marshal(response, writer); // marshal[Convert] Command Object to XML

        return writer.toString();
    }
    @GET
    @Path("/ussd/core/{INPUT}/{MSISDN}/{SESSIONID}/{AGENTID}/{NEWREQUEST}/{SPID}/{FROMMULTIUSSD}/{Resume}")
    @Produces(MediaType.APPLICATION_XML)
    public String getCommandXml(
            @PathParam("INPUT") String INPUT,
            @PathParam("MSISDN") String MSISDN,
            @PathParam("SESSIONID") String SESSIONID,
            @PathParam("AGENTID") String AGENTID,
            @PathParam("NEWREQUEST") String NEWREQUEST,
            @PathParam("SPID") String SPID,
            @PathParam("FROMMULTIUSSD") String FROMMULTIUSSD,
            @PathParam("Resume") String Resume
    ) {

        System.out.println("===========================================");
        System.out.println("INPUT " + INPUT);
        System.out.println("MSISDN " + MSISDN);
        System.out.println("SESSIONID " + SESSIONID);
        System.out.println("AGENTID " + AGENTID);
        System.out.println("NEWREQUEST " + NEWREQUEST);
        System.out.println("SPID " + SPID);
        System.out.println("FROMMULTIUSSD " + FROMMULTIUSSD);
        System.out.println("===========================================");
        COMMAND com = new COMMAND(MSISDN, SESSIONID, NEWREQUEST, AGENTID, INPUT, SPID, FROMMULTIUSSD, false);

        System.out.println("\nCLIENT INPUT REQUEST-->GISA=======\n" + com);
        System.out.println("\n=======\n");

        COMMAND response = command.getCommand(com);
        System.out.println("\nREAL RESPONSE -->GISA=======\n" + response);
        System.out.println("\n----------------------------");

        StringWriter writer = new StringWriter();
        JAXB.marshal(response, writer); // marshal[Convert] Command Object to XML

        return writer.toString();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    @Path("/ussd/core/post")
    public String postCommandXml(COMMAND com) throws Exception {

        COMMAND response = command.getCommand(com);
        StringWriter writer = new StringWriter();
        JAXB.marshal(response, writer); // marshal[Convert] Command Object to XML
        return writer.toString();
    }

}
