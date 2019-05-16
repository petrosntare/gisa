<%-- 
    Document   : page_upload_payments
    Created on : Jul 8, 2017, 9:52:35 AM
    Author     : mcpaul
--%>
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



        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/uploader_bootstrap.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/uploaders/fileinput.min.js"/>"></script>

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
                            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Home</span> - UPLOAD PAYED TICKET</h4>
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

                            <!-- /content area -->
                            <div class="content">
                                <!-- HTML sourced data -->
                                <div class="panel-body">
                                    <jsp:include page="feedback_message.jsp"/>
                                    <!-- Marketing campaigns -->
                                    <s:form cssClass="form-horizontal" action="paymentupload.htm" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label text-semibold">Upload tickets here:</label>
                                            <div class="col-lg-10">
                                                <s:file name="ExcelFile" cssClass="file-input"></s:file>
                                                    <span class="help-block">Allow only specific file extensions. Only <code>xls</code> extension is allowed.</span>
                                                </div>
                                            </div>
                                    </s:form>
                                </div>
                                <!-- /HTML sourced data -->
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
