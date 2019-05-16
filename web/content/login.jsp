<%@page import="com.gisainvestment.domain.Account"%>
<%@page import="com.gisainvestment.DAO.AccountDAOimp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GISA - Authentication</title>

        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="<s:url value='/content/assets/css/icons/icomoon/styles.css'/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/core.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/components.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/colors.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/custom.css"/>" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="../favicon.png">

        <!-- /global stylesheets -->

        <!-- Core JS files -->
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/pace.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/blockui.min.js"/>"></script>
        <!-- /core JS files -->

        <!-- Theme JS files -->
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/forms/styling/uniform.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/pages/login.js"/>"></script>
        <!-- /theme JS files -->

    </head>

    <body class="login-container">


        <!-- Page container -->
        <div class="page-container">

            <!-- Page content -->
            <div class="page-content">

                <!-- Main content -->
                <div class="content-wrapper">

                    <!-- Content area -->
                   <div class="content">
                        <!--<div class="panel panel-heading">-->

                        <!--</div>-->
                        <!-- Advanced login -->
                        <!--<div class="panel panel-heading">-->
                        <!--</div>-->
                        <form action="userlogin.htm" method="post">

                            <div class="panel panel-body login-form">
                                <div class="text-center">
                                    <div><img width="100" height="100" src="<s:url value="/content/assets/images/GisaInvestmentLogo.png"/>"/></div>
                                    <h5 class="content-group">Login to your account <small class="display-block">Your credentials</small></h5>
                                    <jsp:include page="feedback_message.jsp"/>
                                </div>

                                <div class="form-group has-feedback has-feedback-left">
                                    <input type="text" class="form-control" placeholder="Username" name="user.username">
                                    <div class="form-control-feedback">
                                        <i class="icon-user text-muted"></i>
                                    </div>
                                </div>

                                <div class="form-group has-feedback has-feedback-left">
                                    <input type="password" class="form-control" placeholder="Password" name="user.password">
                                    <div class="form-control-feedback">
                                        <i class="icon-lock2 text-muted"></i>
                                    </div>
                                </div>

                                <div class="form-group login-options">
                                    <div class="row">
                                        <div class="col-sm-6">
                                        </div>

                                        <div class="col-sm-6 text-right">
                                            <a href="<s:url value="navpasswordRecoveryPage.htm"/>">Forgot password?</a>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn bg-blue btn-block">Login <i class="icon-arrow-right14 position-right"></i></button>
                                </div>

                                <div class="content-divider text-muted form-group"><span>Don't have an account?</span></div>
                                <a href="mailto:sysadministrator@gisainvestment.com?subject=Request account" class="btn btn-default btn-block content-group"><i class="icon-mail5"></i> Contact Administrator</a>
                            </div>
                        </form>
                        <!-- /advanced login -->
                        <jsp:include page="footer.jsp"/>
                    </div>
                    <!-- /content area -->

                </div>
                <!-- /main content -->

            </div>
            <!-- /page content -->

        </div>
        <!-- /page container -->
    </body>

</html>