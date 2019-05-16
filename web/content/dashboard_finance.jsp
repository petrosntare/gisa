<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GISA - Dashboard</title>

        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/icons/icomoon/styles.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/core.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/components.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/colors.css"/>" rel="stylesheet" type="text/css">
        <!-- /global stylesheets -->

        <!-- Core JS files -->
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/pace.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/blockui.min.js"/>"></script>
        <!-- /core JS files -->

        <!-- Theme JS files -->
        
        <script type="text/javascript" src="<s:url value="/content/assets/js/canvasjs.min.js"/>"></script>
        

        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <!-- /theme JS files -->
        <style>
            #chartContainerInvestment{
                padding :0% 2% 50% 0%;
            }
        </style>
        <script>
            window.setInterval(function () {
                ajaxCallTicketOrangeFunction();  //calling every 5 seconds
                ajaxCallCashTicketOrangeFunction();  //calling every 5 seconds
                ajaxCallTicketGreenFunction();  //calling every 5 seconds
                ajaxCallCashTicketGreenFunction();  //calling every 5 seconds
                ajaxCallTicketRedFunction();  //calling every 5 seconds
                ajaxCallCashTicketRedFunction();  //calling every 5 seconds
            }, 5000);

            function ajaxCallTicketOrangeFunction() {
                $.getJSON("${pageContext.request.contextPath}/TicketOrange", function (json) {
                    $('#TicketOrange').text(json);
                });
            }
            function ajaxCallCashTicketOrangeFunction() {
                $.getJSON("${pageContext.request.contextPath}/CashTicketOrange", function (json) {
                    $('#CashTicketOrange').text(json);
                });
            }
            function ajaxCallTicketGreenFunction() {
                $.getJSON("${pageContext.request.contextPath}/TicketGreen", function (json) {
                    $('#TicketGreen').text(json);
                });
            }
            function ajaxCallCashTicketGreenFunction() {
                $.getJSON("${pageContext.request.contextPath}/CashTicketGreen", function (json) {
                    $('#CashTicketGreen').text(json);
                });
            }

            function ajaxCallTicketRedFunction() {
                $.getJSON("${pageContext.request.contextPath}/TicketRed", function (json) {
                    $('#TicketRed').text(json);
                });
            }
            function ajaxCallCashTicketRedFunction() {
                $.getJSON("${pageContext.request.contextPath}/CashTicketRed", function (json) {
                    $('#CashTicketRed').text(json);
                });
            }
            var dataLenders = [];
            $.getJSON("${pageContext.request.contextPath}/investmentPerMonth", function (result) {
                for (var i = 0; i < result.length; i++) {
                    dataLenders.push({"label": result[i].x, "y": result[i].y});
                }

                var chart = new CanvasJS.Chart("chartContainerInvestment", {
                    theme: "theme2",
                    zoomEnabled: true,
                    animationEnabled: true,
                    title: {
                        text: "N# of Tickets per Month"
                    },
                    subtitles: [
                        {
                            text: "Monthly data"
                        }
                    ],
                    axisY: {
                        title: "Total N# of Ticket"
                    },
                    axisX: {
                        title: "Month",
                        interval: 1,
                        intervalType: "month"
                    },
                    data: [{
                            type: "line",
                            dataPoints: dataLenders
                        }]
                });
                chart.render();
            });
        </script>
    </head>
    <jsp:include page="header.jsp"/>


    <!-- Page container -->
    <div class="page-container">

        <!-- Page content -->
        <div class="page-content">

            <jsp:include page="page_left.jsp"/>

            <!-- Main content -->
            <div class="content-wrapper">

                <!-- Page header -->
                <div class="page-header page-header-default">
                    <div class="page-header-content">
                        <div class="page-title">
                            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Home</span> - Dashboard</h4>
                        </div>

                        <div class="heading-elements">
                            <div class="heading-btn-group">
                                <!--                                <a href="#" class="btn btn-link btn-float has-text"><i class="icon-bars-alt text-primary"></i><span>Statistics</span></a>
                                                                <a href="#" class="btn btn-link btn-float has-text"><i class="icon-calculator text-primary"></i> <span>Invoices</span></a>
                                                                <a href="#" class="btn btn-link btn-float has-text"><i class="icon-calendar5 text-primary"></i> <span>Schedule</span></a>-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page header -->


                <!-- Content area -->
                <div class="content">

                    <!-- Main charts -->

                    <!-- /main charts -->


                    <!-- Dashboard content -->
                    <div class="row">
                        <div class="col-lg-9">

                            <!-- Marketing campaigns -->

                            <!-- /marketing campaigns -->


                            <!-- Quick stats boxes -->
                            <div class="row">
                                <div class="col-lg-4">
                                    <!-- Today's revenue -->
                                    <div class="panel bg-green-400">
                                        <div class="panel-body">
                                            <h5 id="TicketGreen" class="no-margin">n/a</h5>
                                            <div class="text-muted text-size-small"><span id="CashTicketGreen" class="text-muted text-size-small"></span> Frw</div>
                                            <div class="text-muted text-size-small">Green Tickets</div>
                                            <div class="text-muted text-size-small">Remaining Days: Between 16-22</div>
                                        </div>
                                    </div>
                                    <!-- Today's revenue -->
                                </div>
                                <div class="col-lg-4">
                                    <!-- Today's revenue -->
                                    <div class="panel bg-orange-400">
                                        <div class="panel-body">
                                            <h5 id="TicketOrange" class="no-margin">n/a</h5>
                                            <div class="text-muted text-size-small"><span id="CashTicketOrange" class="text-muted text-size-small"></span> Frw</div>
                                            <div class="text-muted text-size-small">Orange Tickets</div>
                                            <div class="text-muted text-size-small">Remaining Days: Between 23-26</div>
                                        </div>
                                    </div>
                                    <!-- Today's revenue -->
                                </div>

                                <div class="col-lg-4">
                                    <!-- Today's revenue -->
                                    <div class="panel bg-danger-400">
                                        <div class="panel-body">
                                            <h5 id="TicketRed" class="no-margin">n/a</h5>
                                            <div class="text-muted text-size-small"><span id="CashTicketRed" class="text-muted text-size-small"></span> Frw</div>
                                            <div class="text-muted text-size-small">Red Tickets</div>
                                            <div class="text-muted text-size-small">Remaining Days: Between 27-30</div>
                                        </div>
                                    </div>
                                    <!-- /today's revenue -->
                                </div>
                            </div>
                            <!-- /quick stats boxes -->

                            <div id="chartContainerInvestment">
                            </div>

                            <!-- Support tickets -->
                            <div id="chartContainertaxes">
                            </div>
                            <!-- /support tickets -->


                            <!-- Latest posts -->

                            <!-- /latest posts -->

                        </div>
                        <div class="col-lg-3"> 
                            <div class="content-group">

                                <!-- Today's revenue -->
                                <div class="panel bg-teal-400">
                                    <div class="panel-body">
                                        <h3 class="no-margin">
                                            <s:bean name="com.gisainvestment.DAO.PaymentDAO" id="tax"></s:bean>
                                            <s:property value="#tax.taxes"/> Frw
                                        </h3>

                                        <div class="text-muted text-size-small">
                                            Total Taxes
                                        </div>
                                    </div>
                                </div>
                                <div class="panel bg-brown-400">
                                    <div class="panel-body">
                                        <h3 class="no-margin">
                                            <s:bean name="com.gisainvestment.DAO.InvestmentDAOimp" id="amount"></s:bean>
                                            <s:property value="#amount.inWorld"/> Frw
                                        </h3>

                                        <div class="text-muted text-size-small">
                                            Investment World
                                        </div>
                                    </div>
                                </div>
                                <div class="panel bg-blue-400">
                                    <div class="panel-body">
                                        <h3 class="no-margin"><s:property value="#amount.inCirculation"/> Frw</h3>
                                        <div class="text-muted text-size-small">
                                            Circulation Store
                                        </div>
                                    </div>
                                </div>
                                <div class="panel bg-slate-400">
                                    <div class="panel-body">

                                        <h3 class="no-margin"><s:property value="#amount.inSys"/> Frw</h3>
                                        <div class="text-muted text-size-small">
                                            Total in System
                                        </div>
                                    </div>
                                </div>
                                <!-- /today's revenue -->
                            </div>
                        </div>             
                    </div>
                    <!-- /dashboard content -->

                    <jsp:include page="footer.jsp"/>

                </div>
                <!-- /content area -->

            </div>
            <!-- /main content -->

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->
