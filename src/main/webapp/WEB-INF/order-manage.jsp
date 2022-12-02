<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Manage Orders</title>
</head>
<body>

<h2> Orders of all users
</h2>




<c:forEach items="${orderList}" var="order">
<table id = "${order.id}" class="table table-primary table-hover">

<tr>
            <th>User ID</th>
            <th>Date and time</th>
            <th>Sum</th>
            <th>Status</th>
            <th>
                <form action="DeleteOrder" >
                    <input type="hidden" value="${order.id}" name="deleteID">
                    <input type="submit" value="DELETE" class="btn btn-primary btn-danger">
                </form>
            </th>
        </tr>

        <tr>
            <td>${order.id_pouz}</td>
            <td>${order.date}</td>
            <td>${order.celkova_suma}</td>
            <td>
                <form action="UpdateOrder"  >
                    <input type="hidden" value="${order.id}" name="updateID">
                    <input type="text" value="${order.stav}" name="orderStatus">
                <input type="submit" value="UPDATE" class="btn btn-primary btn-warning">
                </form>
            </td>
        </tr>

        <tr>
            <th>Article</th>
            <th>Price per piece</th>
            <th>Num. bought</th>
            <th>Sum per item</th>
        </tr>

        <c:forEach items="${order.orderItems}" var="orderItem">

            <tr>
                <td>${orderItem.tovar.nazov}</td>
                <td>${orderItem.cena_kus}</td>
                <td>${orderItem.poc_ks}</td>
                <td>${orderItem.cena_spolu} EUR</td>

            </tr>

        </c:forEach>
        <tr><th></th><th></th><th></th><th></th>        </tr>
</table>

</c:forEach>

<form action="home" class="d-flex justify-content-center align-items-center" >
    <input type="submit" value="HOME PAGE" class="btn btn-primary btn-lg btn-block">
</form>

</body>
</html>
