<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 3/12/2022
  Time: 1:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Yarn Store</title>
</head>
<body>
<h1>Your Bills</h1>
<c:set var="product_list" value="${sessionScope.PRODUCT_LIST}"/>
<c:set var="cart" value="${sessionScope.CART}"/>
<c:set var="total" value="${sessionScope.TOTAL_BILL}"/>
<c:set var="user" value="${sessionScope.USER}"/>

<c:if test="${not empty user}">
    <span style="color: red; "> Welcome, ${user.firstname} </span><br/>
    <a href="logout">Log Out</a>
</c:if>

<c:if test="${not empty cart}">
    <c:set var="cartItems" value="${cart.items}"/>
    <c:if test="${not empty cartItems}">
            <table>
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="index" var="item" items="${cartItems}">
                    <tr>
                        <td>${index.count}</td>
                        <td>
                                <%--                    ${item.key}--%>
                            <c:forEach var="product" items="${product_list}">
                                <c:if test="${item.key == product.id}">
                                    ${product.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td align="right">${item.value}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2">
                        Total
                    </td>
                    <td>
                        ${total}
                    </td>
                </tr>
                </tbody>
            </table>
    </c:if>
</c:if>
<c:remove var="CART" scope="session"/>
<c:remove var="TOTAL_BILL" scope="session"/>
<c:if test="${empty cartItems}">
    <h2>No items here!</h2>
</c:if>
<a href="viewProduct">Shopping Now!!</a>
<c:remove var="cart" scope="session"/>
<c:remove var="total" scope="session"/>
</body>
</html>
