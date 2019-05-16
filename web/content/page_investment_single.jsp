
<%@page import="com.gisainvestment.domain.Investment"%>
<%@page import="com.gisainvestment.DAO.InvestmentDAOimp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GISA - Investment</title>

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
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/tables/footable/footable.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/table_responsive.js"/>"></script>
        <!-- /theme JS files -->

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
                            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Home</span> - Single Ticket</h4>
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
                        <div class="col-lg-6">

                            <!-- Marketing campaigns -->

                            <div class="content">

                                <!-- Basic responsive table -->
                                <div class="panel panel-flat">

                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <tbody>
                                                <s:iterator value="singleinvestment" var="inv">
                                                    <s:date name="#inv.inv_date" var="format1" format="dd/MM/yyyy HH:mm:ss"/>
                                                    <s:date name="#inv.inv_returnDate" var="format2" format="dd/MM/yyyy HH:mm:ss"/>
                                                    <s:if test="#inv.status=='success'">
                                                        <s:if test="#inv.transactionStatus=='success'">
                                                            <tr>
                                                                <th>ID</th>
                                                                <td><s:property value="#inv.id"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Reference</th>
                                                                <td>
                                                                    <a href="investsingleReference.htm?reference=<s:property value="#inv.reference"/>">
                                                                        <s:property value="#inv.reference"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket</th>
                                                                <td>
                                                                    <a href="investsingleTicket.htm?ticket=<s:property value="#inv.ticket"/>">
                                                                        <s:property value="#inv.ticket"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>Deposit Date </th>
                                                                <td><s:property value="#format1"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Payment Date</th>
                                                                <td><s:property value="#format2"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Lent Amount</th>
                                                                <td><s:property value="#inv.amount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Due Payment</th>
                                                                <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Total Balance</th>
                                                                <td><s:property value="#inv.balance"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Source</th>
                                                                <td><s:property value="#inv.source"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Transaction Status</th>
                                                                <td><span class="label label-success"><s:property value="#inv.transactionStatus"/></span></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket Status</th>
                                                                <td><span class="label label-success"><s:property value="#inv.status"/></span></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <th>ID</th>
                                                                <td><s:property value="#inv.id"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Reference</th>
                                                                <td>
                                                                    <a href="investsingleReference.htm?reference=<s:property value="#inv.reference"/>">
                                                                        <s:property value="#inv.reference"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket</th>
                                                                <td><s:property value="#inv.ticket"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Deposit Date </th>
                                                                <td><s:property value="#format1"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Payment Date</th>
                                                                <td><s:property value="#format2"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Lent Amount</th>
                                                                <td><s:property value="#inv.amount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Due Payment</th>
                                                                <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Total Balance</th>
                                                                <td><s:property value="#inv.balance"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Source</th>
                                                                <td><s:property value="#inv.source"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Transaction Status</th>
                                                                <td><span class="label label-danger"><s:property value="#inv.transactionStatus"/></span></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket Status</th>
                                                                <td><span class="label label-success"><s:property value="#inv.status"/></span></td>
                                                            </tr>
                                                        </s:else>
                                                    </s:if>
                                                    <s:else>
                                                        <s:if test="#inv.transactionStatus=='success'">
                                                            <tr>
                                                                <th>ID</th>
                                                                <td><s:property value="#inv.id"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Reference</th>
                                                                <td>
                                                                    <a href="investsingleReference.htm?reference=<s:property value="#inv.reference"/>">
                                                                        <s:property value="#inv.reference"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket</th>
                                                                <td><s:property value="#inv.ticket"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Deposit Date </th>
                                                                <td><s:property value="#format1"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Payment Date</th>
                                                                <td><s:property value="#format2"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Lent Amount</th>
                                                                <td><s:property value="#inv.amount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Due Payment</th>
                                                                <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Total Balance</th>
                                                                <td><s:property value="#inv.balance"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Source</th>
                                                                <td><s:property value="#inv.source"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Transaction Status</th>
                                                                <td><span class="label label-success"><s:property value="#inv.transactionStatus"/></span></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket Status</th>
                                                                <td><span class="label label-danger"><s:property value="#inv.status"/></span></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <th>ID</th>
                                                                <td><s:property value="#inv.id"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Reference</th>
                                                                <td>
                                                                    <a href="investsingleReference.htm?reference=<s:property value="#inv.reference"/>">
                                                                        <s:property value="#inv.reference"/>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket</th>
                                                                <td><s:property value="#inv.ticket"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Deposit Date </th>
                                                                <td><s:property value="#format1"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Payment Date</th>
                                                                <td><s:property value="#format2"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Lent Amount</th>
                                                                <td><s:property value="#inv.amount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Due Payment</th>
                                                                <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Total Balance</th>
                                                                <td><s:property value="#inv.balance"/>(Frw)</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Source</th>
                                                                <td><s:property value="#inv.source"/></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Transaction Status</th>
                                                                <td><span class="label label-danger"><s:property value="#inv.transactionStatus"/></span></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Ticket Status</th>
                                                                <td><span class="label label-danger"><s:property value="#inv.status"/></span></td>
                                                            </tr>
                                                        </s:else>
                                                    </s:else>
                                                </s:iterator>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- /basic responsive table -->
                            </div>
                            <!-- /content area -->
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
