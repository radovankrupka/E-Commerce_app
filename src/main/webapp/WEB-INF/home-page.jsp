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
        <th>Date</th>
        <th>Author</th>
        <th>Post</th>
        <th></th><th></th><th></th>

    </tr>


    <c:forEach items="${userPostList}" var="userPost">
        <tr>
            <td>${userPost.post.posted_at}</td>
            <td>${userPost.user.nickname}</td>

            <td>
                <c:if test="${userPost.user.id != user.id}">
                <form action="home">
                    <input type="hidden" name="ban" value="${userPost.user.id}">
                    <input type="submit" value="BAN USER" class="btn btn-danger btn-sm">
                </form>
                </c:if>
            </td>

            <td>${userPost.post.post_text}</td>

                <td>
                    <c:if test="${userPost.user.id == user.id}">
                    <form action="remove">
                        <input type="hidden" name="post_id" value="${userPost.post.id}">
                        <input type="submit" value="REMOVE POST" class="btn btn-warning btn-sm">
                    </form>
                    </c:if>
                </td>



        </tr>
    </c:forEach>


</table>


<c:if test="${act == 'add'}">

    YOUR POST:
    <br>
    <form name="post-form" method="get" action="save">

   <textarea rows = "4" cols = "40" name = "post-text" placeholder="Enter text of your post here: ">
   </textarea>
        <br>
        <input type="submit" value="SUBMIT"  class="btn btn-primary btn-lg btn-block ">

    </form>
</c:if>

<div class="d-flex justify-content-center align-items-center">
    <form action="home" >
        <input type="hidden" name="act" value="add">
        <input type="submit" value="ADD POST" class="btn btn-primary btn-lg btn-block">
    </form>
</div>


<form action="banned" class="d-flex justify-content-center align-items-center" >
    <input type="submit" value="TABLE OF SINNERS" class="btn btn-danger btn-lg btn-block">
</form>




<form action="home" class="d-flex justify-content-center align-items-center" >
    <input type="hidden" name="operacia" value="logout">
    <input type="submit" value="LOGOUT" class="btn btn-primary btn-lg btn-block">
</form>







</body>
</html>
