<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tiles:insertAttribute name="include"/>
    <title>
        <s:message code="message.application.title"/>
    </title>
</head>
<body>
<div class="wrapper">
    <tiles:insertAttribute name="header"/>
    <div class="main">
        <div class="box content">
            <tiles:insertAttribute name="main-content"/>
        </div>
    </div>
</div>
</body>
</html>
