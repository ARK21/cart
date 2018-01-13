<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Succeed</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<p>Your order has been successfully registered. Your order id: <strong>#${sessionScope.order_id}</strong></p>
<div class="mainContainer">
<table>
    <caption>Your order</caption>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.cart}">
        <c:set var="number" value="${number + 1}"></c:set>
        <tr>
            <td>${number}.</td>
            <td>${item.book.id}</td>
            <td>${item.book.title}</td>
            <td>${item.book.author}</td>
            <td>${item.book.price} $</td>
            <td>${item.quantity}</td>
            <td>${item.quantity * item.book.price} $</td>
            </tr>
    </c:forEach>
    <tr>
        <td colspan="6" align="right">Total price</td>
        <td>${sessionScope.totalPrice} $</td>
    </tr>
</table>
</div>
<p>Thank you.</p>
</body>
</html>
