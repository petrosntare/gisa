/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.wService;

import com.gisainvestment.DAO.CommandService;
import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.DAO.PaymentClient;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.CommandPaymentGW;
import com.gisainvestment.domain.COMMAND;
import com.gisainvestment.domain.Investment;
import java.io.StringWriter;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXB;
import org.apache.log4j.Logger;

@Path("xml")
public class XmlResource {

    @Context
    private UriInfo context;
    CommandService command = new CommandService();
    PaymentClient payment = new PaymentClient();
    static Logger LOG = Logger.getLogger(XmlResource.class.getName());

    static {
        new Log4jConf().loadLog4j();
    }

    public XmlResource() {
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String postOnlyXML(COMMAND incomingXML) {

        try {
            LOG.error("IncomingUSSDXML \n:" + incomingXML);
            COMMAND response = command.getCommand(incomingXML);
            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();
        } catch (Exception e) {
            LOG.error("Exception Error " + e.toString());
            COMMAND response = new COMMAND("The system is not responding, Please try again later");
            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();
        }
    }

    @Path("momogw")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String PostPymentGwResponse(CommandPaymentGW incomingXML) {
        try {
            LOG.error("API PAYMENT RESPONSE\n--------------------------\n:" + incomingXML);
            CommandPaymentGW response = payment.receivePaymentResponse(incomingXML);
            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();

        } catch (Exception e) {
            LOG.error("CATCH EXCEPTION From " + XmlResource.class.getName() + " :-->PostPymentGwResponse() : " + e.toString());
            Investment inv = InvestmentDAOimp.checkTicket(incomingXML.getTRANSID());
            Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd ibabajwe no kubamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
            Sender.sendRouteSMS(inv.getSource(), s);
            CommandPaymentGW response = new CommandPaymentGW();
            response.setTRANSID(incomingXML.getTRANSID());
            response.setCONTRACTID(incomingXML.getCONTRACTID());
            response.setSTATUSCODE("306");
            response.setDESCR("fail");
            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();
        }
    }

    @Path("momogwtest")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String PostPymentGwResponseTest(CommandPaymentGW incomingXML) {
        try {
            LOG.error("IncomingPaymentXMLResponse \n:" + incomingXML);

            CommandPaymentGW response = new CommandPaymentGW();
            response.setRESPONDERSTATUS("105");
            response.setREQUESTSTATUS("101");
            response.setREQUESTSTATUSDESC("Success");
            response.setTRANSID(incomingXML.getTRANSID());

            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();

        } catch (Exception e) {
            LOG.error("Error From " + XmlResource.class.getName() + " :-->PostPymentGwResponse() : " + e.toString());
            COMMAND response = new COMMAND("The system is not responding, Please try again later");
            StringWriter writer = new StringWriter();
            JAXB.marshal(response, writer);
            return writer.toString();
        }
    }
}
