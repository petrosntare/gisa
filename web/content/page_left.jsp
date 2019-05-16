<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- Main sidebar -->
<div class="sidebar sidebar-main">
    <div class="sidebar-content">

        <!-- User menu -->
        <div class="sidebar-user">
            <div class="category-content">
                <div class="media">
                    <a href="#" class="media-left"><img src="<s:url value="/content/assets/images/placeholder.jpg"/>" class="img-circle img-sm" alt=""></a>
                    <div class="media-body">
                        <span class="media-heading text-semibold">Patrick Mugambi</span>
                        <div class="text-size-mini text-muted">
                            <i class="icon-pin text-size-small"></i> &nbsp;Kimironko, KGL
                        </div>
                    </div>

                    <div class="media-right media-middle">
                        <ul class="icons-list">
                            <li>
                                <a href="#"><i class="icon-cog3"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- /user menu -->


        <!-- Main navigation -->
        <div class="sidebar-category sidebar-category-visible">
            <s:if test="#session.user_in_session.permission=='administrator'">
                <div class="category-content no-padding">
                    <ul class="navigation navigation-main navigation-accordion">
                        <!-- Main -->
                        <li class="navigation-header"><span>Main</span> <i class="icon-menu" title="Main pages"></i></li>
                        <li><a href="navadminHomePage.htm"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                        <li><a href="navaccountAllPage.htm"><i class="icon-cash3"></i> <span>Lenders</span></a></li>
                        <li>
                            <a href="#"><i class="icon-stack"></i> <span>Tickets </span></a>
                            <ul>
                                <li><a href="navinvestmentPage.htm">All Tickets</a></li>
                                <li><a href="navreadytobePayedPage.htm">Ready Tickets</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="icon-price-tag"></i> <span>Payments </span></a>
                            <ul>
                                <li><a href="navPaymentPage.htm"><i class="icon-cash3"></i> <span>All Payments</span></a></li>
                                <li><a href="navPaymentUploadPage.htm"><i class="icon-upload10"></i> <span>Upload Payments</span></a></li>
                            </ul>
                        </li>
                        

                    </ul>
                </div>
            </s:if>
            <s:elseif test="#session.user_in_session.permission=='finance'">
                <div class="category-content no-padding">
                    <ul class="navigation navigation-main navigation-accordion">
                        <!-- Main -->
                        <li class="navigation-header"><span>Main</span> <i class="icon-menu" title="Main pages"></i></li>
                        <li><a href="navfinanceHomePage.htm"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                        <li>
                            <a href="#"><i class="icon-stack"></i> <span>Tickets </span></a>
                            <ul>
                                <li><a href="navinvestmentPage.htm">All Tickets</a></li>
                                <li><a href="navreadytobePayedPage.htm">Ready Tickets</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="icon-price-tag"></i> <span>Payments </span></a>
                            <ul>
                                <li><a href="navPaymentPage.htm"><i class="icon-cash3"></i> <span>All Payments</span></a></li>
                                <li><a href="navPaymentUploadPage.htm"><i class="icon-upload10"></i> <span>Upload Payments</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </s:elseif>
            <s:elseif test="#session.user_in_session.permission=='accounting'">
                <div class="category-content no-padding">
                    <ul class="navigation navigation-main navigation-accordion">
                        <!-- Main -->
                        <li class="navigation-header"><span>Main</span> <i class="icon-menu" title="Main pages"></i></li>
                        <li><a href="navaccountingHomePage.htm"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                        <li>
                            <a href="#"><i class="icon-stack"></i> <span>Tickets </span></a>
                            <ul>
                                <li><a href="navreadytobePayedPage.htm">Ready Tickets</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="icon-price-tag"></i> <span>Payments </span></a>
                            <ul>
                                <li><a href="navPaymentPage.htm"><i class="icon-cash3"></i> <span>All Payments</span></a></li>
                                <li><a href="navPaymentUploadPage.htm"><i class="icon-upload10"></i> <span>Upload Payments</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </s:elseif>
        </div>
        <!-- /main navigation -->

    </div>
</div>
<!-- /main sidebar -->
