<%@ page import="hello.servlet.basic.domain.MemberRepository" %>
<%@ page import="hello.servlet.basic.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    //확인용
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int height = Integer.parseInt(request.getParameter("height"));
    int weight = Integer.parseInt(request.getParameter("weight"));

    Member member = new Member(username,height,weight);
    memberRepository.save(member);


%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>height = <%=member.getHeight()%></li>
    <li>weight = <%=member.getWeight()%></li>
    <li>BMI = <%=member.getBmi()%></li>
</ul>
</body>
</html>
