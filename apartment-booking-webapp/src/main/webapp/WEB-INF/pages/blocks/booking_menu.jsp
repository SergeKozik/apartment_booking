<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form modelAttribute="booking_params" action="${pageContext.request.contextPath}/show-homes.html" method="post" cssClass="booking-menu">
    <ul>
        <s:message code="label.address-piece" var="addressTitle"/>
        <s:message code="label.checkin" var="checkinTitle"/>
        <s:message code="label.checkout" var="checkoutTitle"/>
        <s:message code="label.accommodates-min" var="guestsTitle"/>
        <li><spring:input path="owner.addressPiece" cssClass="address-input" title="${addressTitle}" value="${addressTitle}"/></li>
        <li><spring:input path="timeBooking.checkIn" title="${checkinTitle}"/></li>
        <li><spring:input path="timeBooking.checkOut" title="${checkoutTitle}"/></li>
        <li><spring:input path="space.accomodatesMin" title="${guestsTitle}" value="${guestsTitle}"/></li>
    </ul>
    <ul class="booking-submenu">
        <li><label for="roomtype-id"><s:message code="label.room-type"/></label></li>
        <li><label for="pricerange-id"><s:message code="label.price-range"/></label></li>
        <li><label for="moreoptions-id"><s:message code="label.more-filters"/></label> </li>
    </ul>
    <hr>
    <ul class="booking-details">
        <li>
            <input type="radio" name="radio-details" id="roomtype-id"/>
            <div class="booking-submenu-form">
                <spring:checkboxes items="${room_types}" path="space.roomTypePiece"/>
            </div>
        </li>
        <li>
            <input type="radio" name="radio-details" id="pricerange-id"/>
            <div class="booking-submenu-form">
                <s:message code="label.minimum-price"/>
                <spring:input path="price.dailyMin"/>
                <hr>
                <s:message code="label.maximum-price"/>
                <spring:input path="price.dailyMax"/>
            </div>
        </li>
        <li>
            <input type="radio" name="radio-details" id="moreoptions-id">
            <div class="booking-submenu-form">
                <s:message code="label.title-beds"/>
                <spring:input path="space.bedsMin"/>
                <hr>
                <s:message code="label.title-amenities"/>
                <s:message code="label.amenities-wifi"/>
                <spring:checkbox path="amenity.wifiFlag"/>
                <s:message code="label.amenities-parking"/>
                <spring:checkbox path="amenity.freeParkingFlag"/>
            </div>
        </li>
    </ul>
    <s:message code="label.button.submit" var="msg_submit"/>
    <input class="button-search" type="submit" value="${msg_submit}">
    <spring:errors path="*" cssClass="custom-errors"/>
</spring:form>
