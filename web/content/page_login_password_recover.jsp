<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GISA - Password Recovery</title>

        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/icons/icomoon/styles.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/core.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/components.css"/>" rel="stylesheet" type="text/css">
        <link href="<s:url value="/content/assets/css/colors.css"/>" rel="stylesheet" type="text/css">        
        <link href="<s:url value="/content/assets/css/custom.css"/>" rel="stylesheet" type="text/css">
        <!-- /global stylesheets -->

        <!-- Core JS files -->
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/pace.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/libraries/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/content/assets/js/plugins/loaders/blockui.min.js"/>"></script>
        <!-- /core JS files -->

        <!-- Theme JS files -->
        <script type="text/javascript" src="<s:url value="/content/assets/js/core/app.js"/>"></script>
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
                        <!-- Password recovery -->
                        <form action="#">
                            <div class="panel panel-body login-form">
                                <div class="text-center">
                                    <div><img width="100" height="100" src="<s:url value="/content/assets/images/GisaInvestmentLogo.png"/>"/></div>
                                    <h5 class="content-group">Login to your account <small class="display-block">Your credentials</small></h5>
                                </div>

                                <div class="form-group has-feedback">
                                    <input type="email" class="form-control" placeholder="Your email">
                                    <div class="form-control-feedback">
                                        <i class="icon-mail5 text-muted"></i>
                                    </div>
                                </div>

                                <button type="submit" class="btn bg-blue btn-block">Reset password <i class="icon-arrow-right14 position-right"></i></button>
                            </div>
                        </form>
                        <!-- /password recovery -->


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