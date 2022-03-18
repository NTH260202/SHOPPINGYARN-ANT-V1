<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/18/2022
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<html>
<head>
    <title>Error Page</title>

</head>
<body>
    <c:set var="role" value="${sessionScope.USER.admin}"/>
    <c:if test="${not empty role}">
        <c:if test="${role}">
            <c:url var="homepage" value="searchPage"/>
        </c:if>
        <c:if test="${role == false}">
            <c:url var="homepage" value="viewProduct"/>
        </c:if>
    </c:if>
    <c:if test="${empty role}">
         <c:url var="homepage" value="loginPage"/>
    </c:if>
    <h1><span style="color: red; "> ERROR: Page is not found! </span></h1>
    <p>Please return to the <a href="${homepage}">Homepage</a></p>
</body>
</html>
