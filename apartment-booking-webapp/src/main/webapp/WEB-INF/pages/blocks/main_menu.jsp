<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="user-menu">
    <a href="${pageContext.request.contextPath}/main.html">
        <s:message code="label.menu.main-homes"/>
    </a>
    <security:authorize access="isAuthenticated()">
        <ul>
            <li>
                <a href="#">
                    <s:message code="label.menu.private-area"/>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/profile.html">
                    <s:message code="label.menu.user-profile"/>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/booking-list.html">
                    <s:message code="label.menu.user-bookings"/>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout.html">
                    <s:message code="label.menu.user-logout"/>
                </a>
            </li>
        </ul>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <a href="${pageContext.request.contextPath}/register.html">
            <s:message code="label.menu.user-register"/>
        </a>
        <a href="${pageContext.request.contextPath}/login.html">
            <s:message code="label.menu.user-login"/>
        </a>
    </security:authorize>
</div>