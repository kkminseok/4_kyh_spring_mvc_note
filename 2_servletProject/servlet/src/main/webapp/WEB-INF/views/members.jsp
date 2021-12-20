<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>height</th>
  <th>weight</th>
  <th>bmi</th>
  </thead>
  <tbody>
  <!-- for문 사용하는것을 보자!-->
  <c:forEach var="item" items="${members}">
    <tr>
      <td>${item.id}</td>
      <td>${item.username}</td>
      <td>${item.height}</td>
      <td>${item.weight}</td>
      <td>${item.bmi}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
