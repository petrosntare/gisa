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

        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/datatables_data_sources.js"/>"></script>



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
                            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Home</span> - Accounts</h4>
                        </div>

                        <div class="heading-elements">
                            <div class="heading-btn-group">
                                <a href="accountresetAll.htm" class="btn btn-float has-text"><i class="icon-user-lock text-primary"></i> <span>Reset all password</span></a>
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
                                    <s:if test="hasActionMessages()">
                                        <div class="alert alert-success no-border">
                                            <button type="button" class="close" data-dismiss="alert"><span>&times;</span><span class="sr-only">Close</span></button>
                                            <s:actionmessage/>
                                        </div>
                                    </s:if>
                                    <s:elseif test="hasActionErrors()">
                                        <div class="alert alert-danger no-border">
                                            <button type="button" class="close" data-dismiss="alert"><span>&times;</span><span class="sr-only">Close</span></button>
                                            <s:actionerror/>
                                        </div>
                                    </s:elseif>

                                    <table class="table datatable-html">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Registration Date</th>
                                                <th>Reference</th>
                                                <th>Phone</th>
                                                <th>Identification</th>
                                                <th>Status</th>
                                                <th>Operation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <s:iterator value="accounts" var="acc">
                                                <s:date name="#acc.endTime" var="format1" format="dd/MM/yyyy HH:mm:ss"/>
                                                <s:if test="#acc.status == 'Active'">
                                                    <tr>
                                                        <td><s:property value="#acc.id"/></td>
                                                        <td><s:property value="#format1"/></td>
                                                        <td>
                                                            <a href="investsingleReference.htm?reference=<s:property value="#acc.reference"/>">
                                                                <s:property value="#acc.reference"/>
                                                            </a>
                                                        </td>
                                                        <td><s:property value="#acc.phone"/></td>
                                                        <td><s:property value="#acc.identification"/></td>
                                                        <td>
                                                            <span class="label label-success"><s:property value="#acc.status"/></span>
                                                        </td>
                                                        <td class="text-center">
                                                            <ul class="icons-list">
                                                                <li class="dropdown">
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                        <i class="icon-menu9"></i>
                                                                    </a>
                                                                    <ul class="dropdown-menu dropdown-menu-right">
                                                                        <li><a href="accountresetSingle.htm?phone=<s:property value="#acc.phone"/>"><i class="icon-user-lock"></i> Reset Password</a></li>
                                                                    </ul>
                                                                </li>
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                </s:if>
                                                <s:else>
                                                    <tr>
                                                        <td><s:property value="#acc.id"/></td>
                                                        <td><s:property value="#format1"/></td>
                                                        <td>
                                                            <a href="investsingleReference.htm?reference=<s:property value="#acc.reference"/>">
                                                                <s:property value="#acc.reference"/>
                                                            </a>
                                                        </td>
                                                        <td><s:property value="#acc.phone"/></td>
                                                        <td><s:property value="#acc.identification"/></td>
                                                        <td>
                                                            <span class="label label-danger"><s:property value="#acc.status"/></span>
                                                        </td>
                                                        <td class="text-center">
                                                            <ul class="icons-list">
                                                                <li class="dropdown">
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                        <i class="icon-menu9"></i>
                                                                    </a>
                                                                    <ul class="dropdown-menu dropdown-menu-right">
                                                                        <li><a href="accountresetSingle.htm?phone=<s:property value="#acc.phone"/>"><i class="icon-user-lock"></i> Reset Password</a></li>
                                                                    </ul>
                                                                </li>
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                </s:else>
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
