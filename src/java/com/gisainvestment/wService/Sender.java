/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.wService;

import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.utility.Locator;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.log4j.Logger;

/**
 *
 * @author mcpaul
 */
public class Sender {

    static {
        new Log4jConf().loadLog4j();
    }
    static Logger LOG = Logger.getLogger(Sender.class.getName());
    // Username that is to be used for submission
    String username;
    // password that is to be used along with username
    String password;
    // Message content that is to be transmitted
    String message;
    /**
     * What type of the message that is to be sent
     * <ul>
     * <li>0:means plain text</li>
     * <li>1:means flash</li>
     * <li>2:means Unicode (Message content should be in Hex)</li>
     * <li>6:means Unicode Flash (Message content should be in Hex)</li>
     * </ul>
     */
    String type;
    /**
     * Require DLR or not
     * <ul>
     * <li>0:means DLR is not Required</li>
     * <li>1:means DLR is Required</li>
     * </ul>
     */
    String dlr;
    /**
     * Destinations to which message is to be sent For submitting more than one
     * destination at once destinations should be comma separated Like
     * 91999000123,91999000124
     */
    String destination;
    // Sender Id to be used for submitting the message
    String source;
    // To what server you need to connect to for submission
    String server;
    // Port that is to be used like 8080 or 8000
    int port;

    public Sender() {
    }

    public Sender(String message) {
        this.message = message;
    }

    public Sender(String server, int port, String username, String password, String message, String dlr, String type, String destination, String source) {
        this.username = username;
        this.password = password;
        this.message = message;
        this.dlr = dlr;
        this.type = type;
        this.destination = destination;
        this.source = source;
        this.server = server;
        this.port = port;
    }

    private boolean submitMessage() {
        boolean valid = false;
        try {
// Url that will be called to submit the message
            URL sendUrl = new URL("http://" + this.server + ":" + this.port
                    + "/bulksms/bulksms");
            HttpURLConnection httpConnection = (HttpURLConnection) sendUrl
                    .openConnection();
// This method sets the method type to POST so that will be send as a POST request
            httpConnection.setRequestMethod("POST");
// This method is set as true wince we intend to send input to the server
            httpConnection.setDoInput(true);
// This method implies that we intend to receive data from server.
            httpConnection.setDoOutput(true);
// Implies do not use cached data
            httpConnection.setUseCaches(false);
// Data that will be sent over the stream to the server.
            DataOutputStream dataStreamToServer = new DataOutputStream(
                    httpConnection.getOutputStream());
            dataStreamToServer.writeBytes(
                    "username=" + URLEncoder.encode(this.username, "UTF-8")
                    + "&password=" + URLEncoder.encode(this.password, "UTF-8")
                    + "&type=" + URLEncoder.encode(this.type, "UTF-8") + ""
                    + "&dlr=" + URLEncoder.encode(this.dlr, "UTF-8") + ""
                    + "&destination=" + URLEncoder.encode(this.destination, "UTF-8") + ""
                    + "&source=" + URLEncoder.encode(this.source, "UTF-8") + ""
                    + "&message=" + URLEncoder.encode(this.message, "UTF-8"));
            dataStreamToServer.flush();
            dataStreamToServer.close();
// Here take the output value of the server.
            BufferedReader dataStreamFromUrl = new BufferedReader(
                    new InputStreamReader(httpConnection.getInputStream()));
            String dataFromUrl = "", dataBuffer = "";
// Writing information from the stream to the buffer
            while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
                dataFromUrl += dataBuffer;
            }
            /**
             * Now dataFromUrl variable contains the Response received from the
             * server so we can parse the response and process it accordingly.
             */
            dataStreamFromUrl.close();
            LOG.error("Response FROM routeSMS: \n" + dataFromUrl);
            String response = dataFromUrl.substring(0, 4);
            String phone = dataFromUrl.substring(5, 17);
            switch (response) {
                case "1701":
                    valid = true;
                    LOG.error("SMS OK");
                    break;
                case "1702":
                    LOG.error("Invalid URL Error, This means that one of the parameters was not provided or left blank");
                    break;
                case "1703":
                    LOG.error("Invalid value in username or password field");
                    break;
                case "1704":
                    LOG.error("Invalid value in type field");
                    break;
                case "1705":
                    LOG.error("Invalid Message");
                    break;
                case "1706":
                    LOG.error("Invalid Destination");
                    break;
                case "1707":
                    LOG.error("Invalid Source (Sender)");
                    break;
                case "1708":
                    LOG.error("Invalid value for dlr field");
                    break;
                case "1709":
                    LOG.error("User validation failed");
                    break;
                case "1710":
                    LOG.error("Internal Error");
                    break;
                case "1715":
                    LOG.error("Insufficient Credit");
                    break;
                case "1725":
                    LOG.error("Response timeout");
                    break;
                default:
                    break;
            }
            LOG.error("SMS SEND to " + phone + " " + this.message);
            return valid;
        } catch (IOException ex) {
            LOG.error(ex.toString());
            return valid;

        }

    }

    public static void main(String[] args) {
        try {
// Below exmaple is for sending Plain text
//            Sender s = new Sender("121.241.242.114", 8080, "cyu-gisa",
//                    "itdc@SYS", "test for unicode", "1", "0", "250788298465",
//                    "GISA");
//            s.submitMessage();
//            String api = "http://121.241.242.114:8080/bulksms/bulksms?username=XXXX&password=YYYYY&type=Y&dlr=Z&destination=QQQQQQQQQ&source=RRRR&message=SSSSSSSS";
// Below exmaple is for sending unicode
//            Sender s1 = new Sender("smpp2.routesms.com", 8080, "xxxx",
//                    "xxx", convertToUnicode("test for unicode").toString(),
//                    "1", "2", "919869533416", "Update");
//            s1.submitMessage();
            String spl = "1701|250783453253|d100f329-1cbd-4e55-9cf0-2936cb701da9";
            String arr = spl.substring(0, 4);
            String arr2 = spl.substring(5, 17);
            System.out.println(arr2);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public static void sendRouteSMS(String DestinationPhone, Sender s) {
        try {
            Sender send = new Sender(
                    Locator.ROUTESMS_HOST,
                    Locator.ROUTESMS_PORT,
                    Locator.ROUTESMS_USERNAME,
                    Locator.ROUTESMS_PASSWORD,
                    s.getMessage(),
                    Locator.ROUTESMS_TYPE,
                    Locator.ROUTESMS_DELIVERY,
                    DestinationPhone,
                    Locator.ROUTESMS_SENDER);
            if (send.submitMessage()) {
                LOG.error(s.getMessage());
            } else {
                LOG.error("SEND SMS FAIL");
            }

        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    //ENCAPSULATION
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDlr() {
        return dlr;
    }

    public void setDlr(String dlr) {
        this.dlr = dlr;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
