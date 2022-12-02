<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

  <title>User Management</title>
</head>
<body>

<h1>User Management</h1>


<table id = "user-data" class="table table-primary table-hover">
  <tr>
    <th>User ID</th>
    <th>Login</th>
    <th>Address</th>
    <th>Discount</th>
    <th>Name</th>
    <th>Surrname</th>
    <th>Is Admin</th>
    <th>Change role</th>
    <th>Description</th>
  </tr>
<c:forEach items="${userList}" var="user">
    <tr>
      <td>${user.id}</td>
      <td>${user.login}</td>
      <td>${user.adresa}</td>
      <td>
      <form action="UpdateUser"  >
        <input type="hidden" value="${user.id}" name="userID">
        <input type="hidden" value="updateDisc" name="action">
        <input type="number" value="${user.zlava}" name="userDisc" min="0" max="100">
        <input type="submit" value="UPDATE" class="btn btn-primary btn-warning">
      </form>
      </td>
      <td>${user.meno}</td>
      <td>${user.priezvisko}</td>
      <td>${user.je_admin}</td>
      <td>
        <form action="UpdateUser"  >
          <input type="hidden" value="${user.id}" name="userID">
          <input type="hidden" value="changeRole" name="action">
          <input type="submit" value="CHANGE" class="btn btn-primary btn-warning">
        </form>
      </td>
      <td>${user.poznamky}</td>


    </tr>


</c:forEach>
</table>


<form action="home" class="d-flex justify-content-center align-items-center" >
  <input type="submit" value="HOME PAGE" class="btn btn-primary btn-lg btn-block">
</form>





</body>
</html>
