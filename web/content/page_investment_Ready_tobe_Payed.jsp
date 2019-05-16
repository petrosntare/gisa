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
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/tables/datatables/datatables.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/forms/selects/select2.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/notifications/bootbox.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/notifications/sweet_alert.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/datatables_data_sources.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/components_modals.js"/>"></script>
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
                            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Home</span> - READY TICKETS</h4>
                        </div>

                        <div class="heading-elements">
                            <div class="heading-btn-group">
                                <a href="investdownloadExcel.htm" class="btn btn-float has-text"><i class="icon-file-spreadsheet text-primary"></i> <span>Export Full</span></a>
                                <a href="investdownloadExcelMinified.htm" class="btn btn-float has-text"><i class="icon-file-spreadsheet text-primary"></i> <span>Export Mini</span></a>
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

                        <div class="col-lg-12">

                            <!-- Marketing campaigns -->

                            <div class="content">
                                <!-- HTML sourced data -->
                                <div class="panel panel-flat">
                                    <jsp:include page="feedback_message.jsp"/>
                                    <table class="table datatable-html table-bordered">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Deposit Date</th>
                                                <th>Payment Date</th>
                                                <th>Ticket</th>
                                                <th>Rendered Amount</th>
                                                <th>Due Payment</th>
                                                <th>Lender Phone</th>
                                                <th>Remaining Days</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <s:iterator value="investmentToPay" var="inv">
                                                <s:bean name="com.gisainvestment.DAO.InvestmentDAOimp" id="checkdate">
                                                    <s:param name="toCheck" value="#inv.inv_date"/>
                                                </s:bean>
                                                <s:date name="#inv.inv_date" var="format1" format="dd/MM/yyyy"/>
                                                <s:date name="#inv.inv_returnDate" var="format2" format="dd/MM/yyyy"/>

                                                <s:if test="#checkdate.difference >= 16 && #checkdate.difference <= 22">
                                                    <tr>
                                                        <td><s:property value="#inv.id"/></td>
                                                        <td><s:property value="#format1"/></td>
                                                        <td><s:property value="#format2"/></td>
                                                        <td>
                                                            <a href="investsingleTicket.htm?ticket=<s:property value="#inv.ticket"/>">
                                                                <s:property value="#inv.ticket"/>
                                                            </a>
                                                        </td>
                                                        <td><s:property value="#inv.amount"/>(Frw)</td>
                                                        <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                        <td><s:property value="#inv.source"/></td>
                                                        <td class="bg-success-600">
                                                            <s:property value="#checkdate.difference"/>
                                                        </td>
                                                    </tr>
                                                </s:if>
                                                <s:elseif test="#checkdate.difference >= 23 && #checkdate.difference <= 26">
                                                    <tr>
                                                        <td><s:property value="#inv.id"/></td>
                                                        <td><s:property value="#format1"/></td>
                                                        <td><s:property value="#format2"/></td>
                                                        <td>
                                                            <a href="investsingleTicket.htm?ticket=<s:property value="#inv.ticket"/>">
                                                                <s:property value="#inv.ticket"/>
                                                            </a>
                                                        </td>
                                                        <td><s:property value="#inv.amount"/>(Frw)</td>
                                                        <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                        <td><s:property value="#inv.source"/></td>
                                                        <td class="bg-orange-400">
                                                            <s:property value="#checkdate.difference"/>
                                                        </td>
                                                    </tr>
                                                </s:elseif>
                                                <s:elseif test="#checkdate.difference >= 27 && #checkdate.difference <= 30">
                                                    <tr>
                                                        <td><s:property value="#inv.id"/></td>
                                                        <td><s:property value="#format1"/></td>
                                                        <td><s:property value="#format2"/></td>
                                                        <td>
                                                            <a href="investsingleTicket.htm?ticket=<s:property value="#inv.ticket"/>">
                                                                <s:property value="#inv.ticket"/>
                                                            </a>
                                                        </td>
                                                        <td><s:property value="#inv.amount"/>(Frw)</td>
                                                        <td><s:property value="#inv.receivableAmount"/>(Frw)</td>
                                                        <td><s:property value="#inv.source"/></td>
                                                        <td class="bg-danger-800">
                                                            <s:property value="#checkdate.difference"/>
                                                        </td>
                                                    </tr>
                                                </s:elseif>
                                                
                                            </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /HTML sourced data -->
                            </div>
                            <!-- /content area -->

                        </div>
                    </div>
                    <!-- /dashboard content -->
                    <!-- Iconified modal -->
                    <!--                    <div id="modal_iconified" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h5 class="modal-title"><i class="icon-menu7"></i> &nbsp;Ticket Number: </h5>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="table-responsive">
                                                            <table class="table table-bordered">
                                                                <tbody>
                                                                    ???
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                    
                                                    <div class="modal-footer">
                                                        <button class="btn btn-link" data-dismiss="modal"><i class="icon-cross"></i> Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>-->
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /iconified modal -->
                    <jsp:include page="footer.jsp"/>

                </div>
                <!-- /content area -->

            </div>
            <!-- /main content -->

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->