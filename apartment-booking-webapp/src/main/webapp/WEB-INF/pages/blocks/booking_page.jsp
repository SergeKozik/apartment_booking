<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <input type="hidden" value="${apartment.space.accommodates}" id="id_accommodates_max">
    <input type="hidden" value="${apartment.id}" id="id_apartment">
    <span class="caption-house"><c:out value="${apartment.owner.title}"/></span>
    <br>
    <span class="address-house"><c:out value="${apartment.owner.address}"/></span>
    <table>
        <tr>
            <td><s:message code="text.room-type"/></td>
            <td><c:out value="${roomType_local}"/></td>
        </tr>
        <tr>
            <td><s:message code="text.beds"/></td>
            <td><c:out value="${apartment.space.beds}"/></td>
        </tr>
        <tr>
            <td><s:message code="text.title-accommodates-max"/></td>
            <td><c:out value="${apartment.space.accommodates}"/></td>
        </tr>
        <tr>
            <td><s:message code="text.price-daily"/></td>
            <td>
                <fmt:formatNumber minFractionDigits="2" value="${apartment.price.daily}"/>
                <s:message code="text.currency-code-dollarusd"/>
            </td>
        </tr>
    </table>
    <spring:form modelAttribute="apartment_booking" action="${pageContext.request.contextPath}/user/book-apartment.html" method="post">
        <c:url value="/rest/available/time" var="url_time_api"/>
        <table>
            <tr>
                <td><s:message code="label.booking.date-checkin"/></td>
                <td><spring:input path="checkIn" id="id_check_in" onchange="ask_available(${apartment.id}, 'id_check_in', 'id_check_out','yes_time_id','yes_time_id')"/></td>
            </tr>
            <tr>
                <td><s:message code="label.booking.date-checkout"/></td>
                <td>
                    <spring:input path="checkOut" id="id_check_out" onchange="ask_available(${apartment.id}, 'id_check_in', 'id_check_out','yes_time_id','yes_time_id')"/>
                    <div class="available-true" id="yes_time_id"><s:message code="message.available.time-ok"/></div>
                    <div class="available-false" id="no_time_id"><s:message code="message.available.time-no"/></div>
                </td>
            </tr>
            <tr>
                <td><s:message code="label.booking.guests"/></td>
                <td>
                    <c:url value="/rest/price" var="url_guests_api"/>
                    <input type="button" value="+" onclick="increase_guests('id_guest_number','id_accommodates_max'); update_price(${url_guests_api}, ${apartment.id}, 'id_guest_number', 'id_check_in', 'id_check_out', 'id_total_price')">
                    <spring:input path="guests" readonly="true" id="id_guest_number"/>
                    <input type="button" value="-" onclick="decrease_guests('id_guest_number'); update_price(${url_guests_api}, ${apartment.id}, 'id_guest_number', 'id_check_in', 'id_check_out', 'id_total_price')">
                </td>
            </tr>
            <tr>
                <td><s:message code="label.booking.total-price"/></td>
                <td><div class="total-price" id="id_total_price"></div></td>
            </tr>
        </table>
        <s:message code="label.button.book" var="msg_book"/>
        <input type="submit" value="${msg_book}">
    </spring:form>
