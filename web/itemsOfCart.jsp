<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/20/2022
  Time: 5:32 PM
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
  <h1>Your Cart</h1>
  <c:set var="product_list" value="${sessionScope.PRODUCT_LIST}"/>
  <c:set var="cart" value="${sessionScope.CART}"/>
  <c:set var="error" value="${requestScope.ERROR_MESSAGE}"/>
  <c:set var="user" value="${sessionScope.USER}"/>

  <c:if test="${not empty user}">
    <span style="color: red; "> Welcome, ${user.firstname} </span><br/>
    <a href="logout">Log Out</a>
  </c:if>

  <c:if test="${empty user}">
    <a href="logout">Log In</a>
  </c:if>

  <c:if test="${not empty cart}">
    <c:set var="cartItems" value="${cart.items}"/>
    <c:if test="${not empty cartItems}">
      <form action="deleteItems">
        <table>
          <thead>
            <tr>
              <th>No.</th>
              <th>Name</th>
              <th>Quantity</th>
              <th>Select</th>
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
                <td>
                  <input type="checkbox" name="removeItems" value="${item.key}">
                </td>
              </tr>
            </c:forEach>
            <tr>
              <td colspan="3">
                <a href="viewProduct">Add More Item</a>
              </td>
              <td>
                <button type="submit">Remove Item</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <form action="checkout" method="POST">
        <button type="submit">Check Out</button>
      </form>
      <span style="color: red; ">${error.quantityIsInvalid}</span><br/>
    </c:if>
  </c:if>
  <c:if test="${empty cartItems}">
    <h2>No items here!</h2>
    <a href="viewProduct">Shopping Now!!</a>
  </c:if>
</body>
</html>
