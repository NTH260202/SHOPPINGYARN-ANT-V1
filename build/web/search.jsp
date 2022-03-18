<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/13/2022
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Page</title>
</head>
<body>
<h1>Search Page</h1>
<span style="color: red; "> Welcome, ${sessionScope.USER.firstname} </span>
<form action="searchAccount">
    Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/><br/>
    <input type="submit"/>
</form>

    <c:set var = "searchValue" value = "${param.txtSearchValue}"/>
    <c:if test="${not empty searchValue}">
        <c:set var = "result" value = "${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty result}">
            <table>
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>First name</th>
                    <th>IsAdmin</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach varStatus="index" var="accounts" items="${result}">
                        <c:url var="deleteURL" value="deleteAccount">
                            <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                            <c:param name="deleteValue" value="${accounts.username}"/>
                        </c:url>
                        <form action="updateAccount" method="POST">
                            <tr>
                                <td>${index.count}</td>
                                <td>
                                        ${accounts.username}
                                    <input type="hidden" name="updatePK" value="${accounts.username}">
                                </td>
                                <td>${accounts.firstname}</td>
                                <td>
                                    <c:if test="${not accounts.admin}">
                                        <input type="checkbox" name="isAdmin" value="true" >
                                    </c:if>
                                    <c:if test="${accounts.admin == true}">
                                        <input type="checkbox" name="isAdmin" value="true" checked>
                                    </c:if>
                                </td>
                                <td><a href="${deleteURL}">Delete</a></td>
                                <td>
                                    <button name="updateParam" value="${param.txtSearchValue}">Update</button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>No result is matched!</h2>
        </c:if>
    </c:if>

    <a href="logout">Log Out</a>
</body>
</html>
