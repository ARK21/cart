<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Cart</title>
</head>
<body>
<a href="index.jsp">Continue Shopping</a>
<table cellspacing="2" cellpadding="2" border="1">
    <caption>My cart</caption>
    <tr>
        <th>Option</th>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Sub Total</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.cart}">
        <c:set var="sum" value="${sum + item.quantity * item.book.price}"></c:set>
        <tr>
            <td>
                <a href="CartController?id=${item.book.id}&action=delete">Delete</a>
                <a href="CartController?id=${item.book.id}&action=ordernow">Order</a>
            </td>
            <td>${item.book.id}</td>
            <td>${item.book.title}</td>
            <td>${item.book.author}</td>
            <td>${item.book.price} $</td>
            <td>${item.quantity}</td>
            <td>${item.quantity * item.book.price} $</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6" align="right">Sum</td>
        <td>${sum} $</td>
    </tr>
</table>

<form action="CartToSql" method="post">
    <input type="hidden" name="sumOrderValue" value="${sum}">
    <div class="clientInfo">
        <input type="text" name="full_name" placeholder="Full name">
        <input type="text" name="phone_number" placeholder="Phone number">
        <input type="submit" value="Proceed to checkout" name="proceed">
    </div>
</form>
</body>
</html>
