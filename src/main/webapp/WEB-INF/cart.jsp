
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Cart</title>
</head>
<body>


<h2 class="d-flex justify-content-center align-items-center" > Your Cart
</h2>
<table id = "data" class="table table-primary table-hover">
    <tr>
        <th>Nazov</th>
        <th>Cena za ks</th>
        <th>V kosiku ks</th>
        <th>Cena spolu</th>
        <th></th><th></th><th></th>

    </tr>


    <c:forEach items="${cartItems}" var="cartItem">
        <tr>
            <td>${cartItem.article.nazov}</td>
            <td>${cartItem.article.cena}</td>
            <td>${cartItem.poc_ks}</td>
            <td>${cartItem.poc_ks * cartItem.article.cena} EUR</td>


         <%--   <td>   PRIDAT VYMAZANIE ITEMU Z KOSIKA
                <form action="AddItem">
                    <input type="hidden" name="itemToAddId" value="${article.id}">
                    <input type="number" name="numOfItems" max = "${article.ks}" min="1" value="1">
                    <input type="submit" value="Add to cart" class="btn btn-success btn-sm">
                </form>
            </td>--%>

        </tr>
    </c:forEach>


</table>

<form action="home" class="d-flex justify-content-center align-items-center" >
    <input type="submit" value="CONTINUE SHOPPING" class="btn btn-primary btn-lg btn-block">
</form>

<form action="SendOrder" class="d-flex justify-content-center align-items-center" >
    <input type="submit" value="CONFIRM ORDER" class="btn btn-primary btn-warning btn-block">
</form>


</body>
</html>
