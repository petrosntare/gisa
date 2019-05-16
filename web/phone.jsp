<%@page import="com.gisainvestment.domain.Choice"%>
<%@page import="com.gisainvestment.home.PhoneNumberGenerator"%>
<%@page import="com.gisainvestment.home.UssdSession"%>
<%@page import="com.gisainvestment.home.UssdFlow"%>
<%@page import="java.util.List"%> 
<%--<%@taglib prefix="s" uri="/struts-tags" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Gisa investment" />
        <title>GISA INVESTMENT</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="shortcut icon" href="../favicon.png">
    </head>
    <body>
        <div class="half dark">
            <div class="iphone-container">
                <div class="phone-shape small">
                    <span class="button-one buttons"></span>
                    <span class="button-two buttons"></span>
                    <span class="button-three buttons"></span>
                    <span class="button-four button last"></span>
                    <div class="top-details">
                        <span class="camera"></span>
                        <span class="circle"></span>
                        <span class="speaker"></span>
                    </div>
                    <div class="phone-screen">
                        <div class="head">GISA INVESTMENT</div>
                        <div class="ussdcontent"> 
                            <% try {%>
                            <div id="output">
                                <center>
                                    <%

                                        //MOD
                                        Object input = request.getParameter("input");
                                        String phoneNumber = request.getParameter("phone");
                                        //END
                                        UssdFlow flow = (UssdFlow) UssdSession.getFlow(phoneNumber);
                                        if (flow == null) {
                                            if (input != null && input.equals("*2#")) {
                                                phoneNumber = PhoneNumberGenerator.getPhoneNumber();
                                                System.out.println("Tele4ne: " + phoneNumber);
                                                flow = new UssdFlow(phoneNumber,"14666725080935241");
                                                UssdSession.setFlow(flow);
                                            }
                                        } else {
                                            flow.setInput(input);
                                        }
                                        if (flow != null) {
                                            String msg = flow.getMessage();
                                            String qn = flow.getQuestion();
                                            List choices = flow.getChoices1();
                                            if (msg != null) {
                                                //Output
                                                out.println(msg + "<br/><br/>");
                                            }
                                            if (qn != null) {
                                                //Output
                                                out.println(qn + "<br/><br/>");
                                            }
                                            int i = 0;
                                            //                            if (choices != null) {
                                            //                                for (Choice item : choices) {
                                            //                                    //Output
                                            //                                    out.println(++i + "-" + item.getQuestion() + "<br/>");
                                            //                                }
                                            //                            }

                                            if (choices != null) {
                                                for (Object item : choices) {
                                                    //Output
                                                    out.println(++i + "-" + item + "<br/>");
                                                }
                                            }
                                        }

                                    %>
                                </center>
                            </div>
                            <div id="input">
                                <form action="phone.jsp" method="post">
                                    <input type="hidden" name="phone" value="<%=phoneNumber%>"/>
                                    <input type="text" name="input" value="" autocomplete="off" />
                                    <input type="submit" value="Send"/>
                                </form>
                            </div>
                            <%
                                } catch (Exception e) {
                                    out.println("<div id='output'>");
                                    out.println("<center>");
                                    out.println("Error! Please Contact the administrator: sysadministrator@gisainvestment.com");
                                    out.println("</center>");
                                    out.println("</div>");
                                }
                            %> 
                        </div>
                    </div>
                    <div class="circle-button"></div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/scripts.js"></script>
    </body>
</html>