<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Cart</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="mainContainer">
   <div class="move"><a href="index.jsp">Continue Shopping</a></div>
    <table>
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
            <c:set var="totalPrice" value="${totalPrice + item.quantity * item.book.price}"></c:set>
            <tr>
                <td>
                    <a href="CartController?id=${item.book.id}&action=delete">Delete</a><br>
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
            <td colspan="6" align="right">Total price</td>
            <td>${totalPrice} $</td>
        </tr>
    </table>
</div>
<div class="container">
    <p>To confirm order enter your data</p>
    <form action="CartToSql" method="post">
        <input type="hidden" name="total_price" value="${totalPrice}">
        <input class="inputForm" type="text" name="full_name" placeholder="Full name">
        <input class="inputForm" type="text" name="phone_number" placeholder="Phone number">
        <input class="submit" type="submit" value="Confirm" name="proceed">
    </form>
</div>
</body>
</html>
