<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="header">
    <div class="header-menu"><tiles:insertAttribute name="main-menu"/></div>
    <div class="header-menu booking"><tiles:insertAttribute name="booking-menu"/></div>
</div>

