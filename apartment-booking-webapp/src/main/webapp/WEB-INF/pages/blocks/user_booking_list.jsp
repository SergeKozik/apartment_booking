<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table>
    <tr>
        <td>
            <s:message code="label.booking.apt-name"/>
        </td>
        <td>
            <s:message code="label.booking.date-checkin"/>
        </td>
        <td>
            <s:message code="label.booking.date-checkout"/>
        </td>
        <td>
            <s:message code="label.booking.guests"/>
        </td>
        <td>
            <s:message code="label.booking.price"/>
        </td>
    </tr>
    <c:forEach var="entry" items="${bookings}">
        <tr>
            <td>
                <c:out value="${entry.apartmentName}"/>
            </td>
            <td>
                <c:out value="${entry.checkIn}"/>
            </td>
            <td>
                <c:out value="${entry.checkOut}"/>
            </td>
            <td>
                <c:out value="${entry.guests}"/>
            </td>
            <td>
                <fmt:formatNumber minFractionDigits="2" value="${entry.price}"/>
                <s:message code="text.currency-code-dollarusd"/>
            </td>
        </tr>
    </c:forEach>
</table>
