<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="apartment-list">
    <c:forEach var="entry" items="${apartments}">
    <div class="apartment-card">
        <fmt:formatNumber minFractionDigits="2" value="${entry.price.daily}"/>
        <s:message code="text.currency-code-dollarusd"/>
        <span class="caption-house"><c:out value="${entry.owner.title}"/></span>
        <br>
        <s:message code="${entry.space.roomType.propertiesCode}"/>
        <br>
        <c:out value="${entry.space.beds}"/> <s:message code="text.beds"/>
        <form action="${pageContext.request.contextPath}/user/book/${entry.id}">
            <s:message code="label.button.book" var="msg_book"/>
            <input type="submit" value="${msg_book}">
        </form>
    </div>
    </c:forEach>
</div>
            
