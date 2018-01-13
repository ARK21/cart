<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="storage.StoreGenerator" %>
<html>
<head>
    <title>Books store</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Welcome to our books shop</h1>
<% StoreGenerator store = new StoreGenerator(); %>
<div class="mainContainer">
    <table>
        <caption>Enable books</caption>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Buy</th>
        </tr>
        <c:forEach var="p" items="<%=store.getStore().values()%>">
            <tr>
                <td>${p.id}</td>
                <td>${p.title}</td>
                <td>${p.author}</td>
                <td>${p.price} $</td>
                <td><a href="CartController?id=${p.id}&action=ordernow">Order Now</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="move"><a href="cart.jsp">Show my cart</a></div>
</div>
</body>
</html>
