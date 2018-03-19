<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form modelAttribute="register_user" cssClass="register-form" action="${pageContext.request.contextPath}/register-user.html" method="post">
    <table>
        <tr>
            <td><s:message code="label.user-email"/></td>
            <td><spring:input path="email"/></td>
        </tr>
        <tr>
            <td><s:message code="label.user-password"/></td>
            <td><spring:input path="password"/></td>
        </tr>
        <tr>
            <td><s:message code="label.user-repeat-password"/></td>
            <td><spring:input path="passwordConfirm"/></td>
        </tr>
    </table>
    <div class="custom-errors">
        <spring:errors path="*"/>
        <c:out value="${register_error_message}"/>
    </div>
    <input class="button-custom" type="submit">
</spring:form>
