<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 3/3/2022
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <c:set var="errorMessgae" value="${requestScope.ERROR_MESSAGE}"/>
        <form action="register" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}"/> (e.g. 6-20 chars)
            ${errorMessgae.usernameLengthErr} ${errorMessgae.usernameIsExisted} <br/>
            Password* <input type="password" name="txtPassword" value=""/> (e.g. 6-30 chars)
            ${errorMessgae.passwordLengthErr}<br/>
            Confirm* <input type="password" name="txtConfirm" value=""/>
            ${errorMessgae.confirmPasswordNotMatched} <br/>
            First name* <input type="text" name="txtFirstname" value="${param.txtFirstname}"/> (e.g. 2-10 chars)
            ${errorMessgae.firstnameLengthErr}<br/>
            Last name* <input type="text" name="txtLastname" value="${param.txtLastname}"/> (e.g. 6-40 chars)
            ${errorMessgae.lastnameLengthErr}<br/>
            <input type="submit"/>
            <input type="reset" value="Reset"/><br/>
            <button><a href="loginPage">Cancel</a></button>
        </form>
    </body>
</html>
