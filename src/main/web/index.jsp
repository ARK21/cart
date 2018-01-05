<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="storage.Store" %>
<html>
<head>
    <title>My Servlet</title>
</head>
<body>
<h1>Books store</h1>
    <%  Store store = new Store();    %>
    <table cellpadding="2" cellspacing="2" border="1">
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
                <td>${p.price}</td>
                <td>Order Now</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
