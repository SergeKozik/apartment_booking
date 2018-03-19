<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form modelAttribute="change_profile" action="${pageContext.request.contextPath}/user/change-profile.html" method="post">
    <table>
        <tr>
            <td><s:message code="label.caption.email"/></td>
            <td><spring:input path="email" readonly="true"/></td>
        </tr>
        <tr>
            <td><s:message code="label.caption.nick"/></td>
            <td><spring:input path="nick"/></td>
        </tr>
        <tr>
            <td><s:message code="label.caption.password"/></td>
            <td><spring:password path="password"/></td>
        </tr>
        <tr>
            <td><s:message code="label.caption.password-confirm"/></td>
            <td><spring:password path="passwordConfirm"/></td>
        </tr>
        <tr>
            <td><s:message code="label.caption.password-old"/></td>
            <td><spring:password path="oldPassword"/></td>
        </tr>
    </table>
    <div class="custom-errors">
        <spring:errors path="*"/>
        <c:out value="${profile_error_message}"/>
    </div>
    <div class="custom-report">
        <c:out value="${profile_success_message}"/>
    </div>
    <s:message code="label.button.save" var="msg_save"/>
    <input type="submit" value="${msg_save}">
</spring:form>
