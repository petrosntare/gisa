<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <title>File Download</title>
    <body>
        <h3>Struts 2 file download example</h3>
        <%--<s:url id="fileDownload" action="download"></s:url>
            <h4>
                Download file - <s:a href="%{fileDownload}">Oltranz_file.xlsx</s:a>
            </h4>
            _____________________________________________________________________________________________
            <!--<h2 style="color: green"><s:text name="label.welcome" /></h2>-->
        <s:form method="post" action="filedownloadCSVFile">
            <s:submit value="Download XLS" />
        </s:form>--%>

        <s:form action="userInfoupload.htm" method="post" enctype="multipart/form-data">
            Excel  <s:file name="ExcelFile"></s:file>
            <s:submit value="upload"></s:submit>
        </s:form>
        <s:actionmessage/>
    </body>
</html>
