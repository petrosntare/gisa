<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div>
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
</div>