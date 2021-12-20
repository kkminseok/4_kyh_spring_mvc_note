<%@ page import="hello.servlet.basic.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
  <!-- 정석으로 꺼내는 법-->
  <li>id : <%=((Member)request.getAttribute("member")).getId()%></li>
  <li>username : ${member.username}</li>
  <li>height : ${member.height}</li>
  <li>weight : ${member.weight}</li>
  <li>bmi : ${member.getBmi()}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
