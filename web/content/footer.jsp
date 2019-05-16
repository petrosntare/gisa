<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.gisainvestment.utility.SimpleDateConverter"%>
<!-- Footer -->
<div class="footer text-muted text-center">
    <%int d = 0;
        try {
            d = Calendar.getInstance().get(Calendar.YEAR);
        } catch (Exception e) {
    %>
    <div class="login-form alert bg-danger alert-styled-left">
        <button type="button" class="close" data-dismiss="alert"></button>
        <span class="text-semibold"></span> <h4>Error!  Please Contact the Administrator</h4> <a href="mailto:sysadministrator@gisainvestment.com?subject=login page&body=<%=e.getMessage()%>" class="alert-link icon-mail5"> Click here to report this</a>.
    </div>

    <%
        }
    %>
    &copy; <%=d%>. <a href="http://www.gisainvestment.com">GISA Investment Ltd</a> Developed by <a href="http://www.opentek.rw" target="_blank">OpenTek Ltd</a>
</div>
<!-- /footer -->