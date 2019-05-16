/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.DAO;

import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.CommandPaymentGW;
import com.gisainvestment.home.UssdFlow;
import com.gisainvestment.utility.Locator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class PaymentClient implements Runnable {

    static Logger LOG = Logger.getLogger(PaymentClient.class.getName());
    public static Client client = ClientBuilder.newClient();//1198880089734023
    private CommandPaymentGW command = new CommandPaymentGW();

    static {
        new Log4jConf().loadLog4j();
    }

    public PaymentClient() {
    }

    public PaymentClient(CommandPaymentGW command) {
        this.command = command;
    }

    public static void main(String[] args) throws UnirestException {
//        WebTarget target = clientt.target("http://localhost:8080/gisa/endpoints/xml/momogwtest");
//        WebTarget target = client.target(Locator.WS_PAYMENT_URL);

        CommandPaymentGW command = new CommandPaymentGW();
        command.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
        command.setPAYINGACCOUNTIDATSP("250783453253");
        command.setPAYMENTSPID("2484");
        command.setDESCR("test payment 3");
        command.setTRANSID("11988800897340223");
        command.setAMOUNT(500);

        MultivaluedMap multi = new MultivaluedHashMap();
        multi.add("CMD", "001");
        multi.add("Domain", "paymentgw");
        multi.add("Content-Type", "application/xml");
//        COMMAND comm = (COMMAND) target.request(MediaType.APPLICATION_XML).headers(multi).post(Entity.entity(command1, MediaType.APPLICATION_XML), COMMAND.class);
//        CommandPaymentGW comm = (CommandPaymentGW) target.request(MediaType.APPLICATION_XML).headers(multi).post(Entity.entity(command, MediaType.APPLICATION_XML), CommandPaymentGW.class);

//        WebTarget targett = client.target("http://localhost:8080/web/resource");
//        WebTarget target = client.target(Locator.WS_PAYMENT_URL);
//        Invocation.Builder builder;
//        builder = target.request("application/xml");
//        builder.header("CMD", "001");
//        builder.header("Domain", "paymentgw");
//        builder.header("Content-Type", "application/xml");
//        Invocation i;
//        i = builder.buildPost(Entity.entity(command, MediaType.APPLICATION_XML));
//        Response r = i.invoke();
//        GenericType<CommandPaymentGW> genericType = new GenericType<CommandPaymentGW>() {};
//        CommandPaymentGW comm = r.readEntity(genericType); //here it is thrown
        HttpResponse<String> response = Unirest.post(Locator.WS_PAYMENT_URL)
                .header("cmd", "001")
                .header("domain", "paymentgw")
                .header("content-type", "application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<COMMAND>\n  "
                        + "<CONTRACTID>" + Locator.WS_OLTRANZ_API_CONTRACTID + "</CONTRACTID>\t\n  "
                        + "<PAYINGACCOUNTIDATSP>250722437126</PAYINGACCOUNTIDATSP>\n  "
                        + "<PAYMENTSPID>" + Locator.WS_TIGO_PAYMENTSPID + "</PAYMENTSPID>\n  "
                        + "<DESCR>paymentforgisa 7</DESCR>\n  "
                        + "<TRANSID>11988800897340200</TRANSID>\n  "
                        + "<AMOUNT>600</AMOUNT>\n"
                        + "</COMMAND>")
                .asString();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandPaymentGW.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(response.getBody());
            CommandPaymentGW comm = (CommandPaymentGW) unmarshaller.unmarshal(reader);
            System.out.println("From Server: \n" + comm);
        } catch (JAXBException e) {
            System.out.println("From Server: \n" + e.toString());
        }

    }

    public boolean sendPaymentRequest() {
        try {
            HttpResponse<String> response = Unirest.post(Locator.WS_PAYMENT_URL)
                    .header("cmd", "001")
                    .header("domain", "paymentgw")
                    .header("content-type", "application/xml")
                    .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                            + "<COMMAND>\n  "
                            + "<CONTRACTID>" + command.getCONTRACTID() + "</CONTRACTID>\t\n  "
                            + "<PAYINGACCOUNTIDATSP>" + command.getPAYINGACCOUNTIDATSP() + "</PAYINGACCOUNTIDATSP>\n  "
                            + "<PAYMENTSPID>" + command.getPAYMENTSPID() + "</PAYMENTSPID>\n  "
                            + "<DESCR>" + command.getDESCR() + "</DESCR>\n  "
                            + "<TRANSID>" + command.getTRANSID() + "</TRANSID>\n  "
                            + "<AMOUNT>" + command.getAMOUNT() + "</AMOUNT>\n"
                            + "</COMMAND>")
                    .asString();
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandPaymentGW.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(response.getBody());
            CommandPaymentGW com = (CommandPaymentGW) unmarshaller.unmarshal(reader);

            LOG.error("From sendPaymentRequest() SEND: \n" + command);
            command.setRESPONDERSTATUS(com.getRESPONDERSTATUS());
            command.setREQUESTSTATUS(com.getREQUESTSTATUS());
            command.setREQUESTSTATUSDESC(com.getREQUESTSTATUSDESC());
            LOG.error("From sendPaymentRequest() RECEIVED: \n" + command);
            if (command.getRESPONDERSTATUS().equals("100") && command.getREQUESTSTATUS().equals("301")) {
                return true;
            } else {
                return false;
            }
        } catch (UnirestException | JAXBException e) {
            LOG.error("ERROR From sendPaymentRequest() : \n" + e.toString());
            UssdFlow.validateTicket(command);
            return false;
        }
    }

    public void initiatePaymentRequestTread(CommandPaymentGW request) {

        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        threadExecutor.execute(new PaymentClient(request));
        threadExecutor.shutdown();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            if (sendPaymentRequest()) {
                LOG.error("sendPaymentRequest() RECEIVED SUCCESS");
            } else {
                LOG.error("sendPayemtnRequest() FAIL " + command);
                UssdFlow.validateTicket(command);
            }

        } catch (InterruptedException ex) {
            LOG.error(ex.toString());
        }

    }

    public CommandPaymentGW receivePaymentResponse(CommandPaymentGW payment) {
        return UssdFlow.validateTicket(payment);
    }

//INCAPSULATION 
    public CommandPaymentGW getCommand() {
        return command;
    }

    public void setCommand(CommandPaymentGW command) {
        this.command = command;
    }

}
