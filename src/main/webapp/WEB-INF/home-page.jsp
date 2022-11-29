<%--
  Created by IntelliJ IDEA.
  User: Radko
  Date: 19 Nov 2022
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Forum page</title>
</head>
<body>

<table id = "data" class="table table-primary table-hover">
    <tr>
        <th>Nazov</th>
        <th>Cena</th>
        <th>Na sklade</th>
        <th></th><th></th><th></th>

    </tr>


    <c:forEach items="${tovarList}" var="tovar">
        <tr>
            <td>${tovar.nazov}</td>
            <td>${tovar.cena}</td>
            <td>${tovar.ks}</td>
            <td>
                    <form action="AddItem">
                        <input type="hidden" name="itemToAddId" value="${tovar.id}">
                        <input type="number" name="numOfItems" max = "${tovar.ks}" min="1" value="1">
                        <input type="submit" value="Add to cart" class="btn btn-success btn-sm">
                    </form>
            </td>

        </tr>
    </c:forEach>


</table>


<form action="home" class="d-flex justify-content-center align-items-center" >
    <input type="hidden" name="operacia" value="logout">
    <input type="submit" value="LOGOUT" class="btn btn-primary btn-lg btn-block">
</form>


<form action="cart" class="d-flex justify-content-center align-items-center" >
    <input type="hidden" name="operacia" value="logout">
    <input type="submit" value="CHECK CART" class="btn btn-primary btn-lg btn-block">
</form>



</body>
</html>
